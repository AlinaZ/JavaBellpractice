package com.example.demo.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

@ControllerAdvice(basePackages = "com.example.demo")
public class JSONResponseWrapper implements ResponseBodyAdvice {@Override
public boolean supports(MethodParameter methodParameter, Class aClass) {
    return true;
}

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        final HttpServletResponse servletResponse = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();

        if (servletResponse.getStatus() != 200) {
            return new ErrorWrapper<Object>(body);
        }

        return new DataWrapper<Object>(body);
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonSerialize
    private class DataWrapper<T> {
        private final Object data;

        public DataWrapper(Object data) {
            this.data = data;
        }
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonSerialize
    private class ErrorWrapper<T> {

        private final Object error;

        public ErrorWrapper(Object error) {
            this.error = error;
        }
    }
}

