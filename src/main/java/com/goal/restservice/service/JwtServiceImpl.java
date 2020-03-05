package com.goal.restservice.service;

import com.goal.restservice.common.error.UnauthorizedException;
import com.goal.restservice.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Slf4j
@Service("jwtService")
@PropertySource(value = {"classpath:application.properties"})
public class JwtServiceImpl implements JwtService {

  private static final String ISSUER = "Naver";
  private static final String SUBJECT = "User";
  private static final String PRIVATE_CLAIM_ID = "userId";
  private static final String PRIVATE_CLAIM_EMAIL = "email";
  private static final int EXPIRED_DATE_DURATION = (1000 * 60 * 60 * 24);
  @Autowired StringRedisTemplate redisTemplate;
  @Value("${jwt.secret}")
  private String SALT;

  /**
   * Create a JWT Token including [Header] typ : JWT alg : HS256
   *
   * <p>[Payload] : Three REGISTERED claims and One PRIVATE Claim are included iss : Naver sub :
   * user exp : + 24h
   *
   * <p>userId : email :
   *
   * <p>[Signature]
   *
   * @param user
   * @return jwt token with signature
   */
  @Override
  public String createToken(User user) {

    return Jwts.builder()
        .setHeaderParam("typ", "JWT")
        .setHeaderParam("alg", "HS256")
        .setSubject(SUBJECT)
        .setIssuer(ISSUER)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRED_DATE_DURATION))
        .claim(PRIVATE_CLAIM_ID, user.getId())
        .claim(PRIVATE_CLAIM_EMAIL, user.getEmail())
        .signWith(SignatureAlgorithm.HS256, this.generateKey())
        .compact();
  }

  /**
   * If the jwt token was DIRTY, error will occur during the Jwts.parser() execution.
   *
   * <p>So with a Try-Catch statement, it can be determined to be able to use this token.
   */
  @Override
  public boolean isUsable(String jwt) {

    try {
      Claims claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt).getBody();

      boolean isExpired = claims.getExpiration().after(new Date());

      if (null != redisTemplate.opsForValue().get(jwt)) {
        log.info("로그아웃 처리된 사용자");
        return false;
      }

      return isExpired;

    } catch (Exception e) {

      if (log.isInfoEnabled()) {
        e.printStackTrace();
      } else {
        log.error(e.getMessage());
      }

      throw new UnauthorizedException(); // TODO :
    }
  }

  @Override
  public long getUserId() {
    Claims claims = get();
    Integer userId = (Integer) claims.get(PRIVATE_CLAIM_ID);
    return userId.longValue();
  }

  @Override
  public String getUserEmail() {
    Claims claims = get();
    return (String) claims.get(PRIVATE_CLAIM_EMAIL);
  }

  @Override
  public Date getExpireDate() {
    Claims claims = get();
    return claims.getExpiration();
  }

  /**
   * Get a list of claims of JWT token in the current http request.
   *
   * @return Claims - Ex) {sub=User, iss=Naver, exp=1581669094, userId=1, email=test@gmail.com}
   */
  @Override
  public Claims get() {

    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    String jwt = request.getHeader("Authorization").split(" ")[1];
    Jws<Claims> claims = null;

    try {
      claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
    } catch (Exception e) {
      if (log.isInfoEnabled()) {
        e.printStackTrace();
      } else {
        log.error(e.getMessage());
      }
      throw new UnauthorizedException();
    }

    return claims.getBody();
  }

  @Override
  public String getToken() {
    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    String jwt = request.getHeader("Authorization").split(" ")[1];

    return jwt;
  }

  private byte[] generateKey() {
    byte[] key = null;
    try {
      key = SALT.getBytes("UTF-8");
    } catch (UnsupportedEncodingException e) {
      if (log.isInfoEnabled()) e.printStackTrace();
      else log.error("Making JWT Key Error ::: {}", e.getMessage());
    }
    return key;
  }
}
