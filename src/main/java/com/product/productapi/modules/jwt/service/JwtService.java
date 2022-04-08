package com.product.productapi.modules.jwt.service;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.product.productapi.config.exceptions.AuthenticationException;
import com.product.productapi.modules.jwt.dto.JwtResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class JwtService {

    private static final String SPACE = " ";

    @Value("${app-config.secrets.api-secret}")
    private String apiSecret;

    public void authorized(String token) {
        String extractedToken = getToken(token);
        try {
            var claims = Jwts
                    .parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(apiSecret.getBytes()))
                    .build()
                    .parseClaimsJws(extractedToken)
                    .getBody();

            JwtResponse user = JwtResponse.getUser(claims);
            if(isEmpty(user) || isEmpty(user.getId())) {
                throw new AuthenticationException("Invalid user");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationException("Error validating token. Error: " + e.getMessage());
        }
    }

    private String getToken(String token) {
       if(isEmpty(token)) {
           throw new AuthenticationException("Token not informed");
       }
       if(token.contains(SPACE)) {
          return token.split(SPACE)[1];
       }
       return token;
    }
}
