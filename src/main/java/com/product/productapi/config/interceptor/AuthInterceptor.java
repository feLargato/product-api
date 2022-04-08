package com.product.productapi.config.interceptor;

import com.product.productapi.modules.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    private static final String Authorization = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object handle) throws Exception {
        if(isOptions(httpServletRequest)) {
            return true;
        }

        String authorization = httpServletRequest.getHeader(Authorization);
        jwtService.authorized(authorization);
        return true;

    }


    private boolean isOptions(HttpServletRequest httpServletRequest) {
        return  HttpMethod.OPTIONS.name().equals(httpServletRequest.getMethod());
    }
}
