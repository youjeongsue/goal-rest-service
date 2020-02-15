package com.goal.restservice.security;


import com.goal.restservice.common.error.UnauthorizedException;
import com.goal.restservice.service.JwtService;
import com.goal.restservice.web.rest.UserJWTController;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    private static final String HEADER_AUTH = "Authorization";

    private JwtService jwtService;


    public JwtInterceptor(JwtService jwtService){
        this.jwtService = jwtService;
    }

    /**
     * This function is called after a SubletDispatcher and before a Controller to check whether
     * 1) a token exists or not
     * 2) a token is expired or not
     * 3) a token is invalidated or not ; due to {@link UserJWTController#logout}
     *
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token;

        try {
            token = request.getHeader(HEADER_AUTH).split(" ")[1];
        }catch (ArrayIndexOutOfBoundsException ex){
            throw new UnauthorizedException();
        }

        if(token != null && jwtService.isUsable(token)){
            return true;
        } else {

            throw new UnauthorizedException();
        }
    }

}