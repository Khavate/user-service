package com.example.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseService {

    @Autowired
    protected ObjectMapper objectMapper;

    protected <T> T convert(Object fromValue, Class<T> toValueType) {
        return objectMapper.convertValue(fromValue, toValueType);
    }
}
