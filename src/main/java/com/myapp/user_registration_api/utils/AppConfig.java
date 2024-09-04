package com.myapp.user_registration_api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class AppConfig {

    @Value("${key.validation.regex.regexp}")
    private String keyValidationRegex;

    public String getKeyValidationRegex() {
        return keyValidationRegex;
    }
}
