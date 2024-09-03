package com.myapp.user_registration_api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${key.validation.regex}")
    private String keyValidationRegex;

    public String getKeyValidationRegex() {
        return keyValidationRegex;
    }
}
