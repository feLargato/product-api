package com.product.productapi.config.interceptor;

import com.product.productapi.config.exceptions.AuthenticationException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class FeignClientAuthInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
        var request = getRequest();
        template
                .header(AUTHORIZATION, request.getHeader(AUTHORIZATION));
    }

    private HttpServletRequest getRequest() {
        try {

            return ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes())
                    .getRequest();

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationException("The error occurred processing request. Error:" + e.getMessage());
        }
    }

}
