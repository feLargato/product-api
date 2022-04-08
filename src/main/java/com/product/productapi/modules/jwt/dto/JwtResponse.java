package com.product.productapi.modules.jwt.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private Integer id;
    private String name;
    private String email;

    public static JwtResponse getUser(Claims claim) {
        try{
            return new ObjectMapper().convertValue(claim.get("authenticatedUser"), JwtResponse.class);

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
