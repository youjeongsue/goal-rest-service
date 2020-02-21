package com.goal.restservice.web.rest;

import com.goal.restservice.common.error.EmailAlreadyUsedException;
import com.goal.restservice.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {


    @GetMapping("/error/1")
    private String defaultErrorResponse() throws IllegalAccessException {

        throw new IllegalAccessException();
    }

    @GetMapping("/error/2")
    private String customeErrorResponse() {

        return "test";
    }
}
