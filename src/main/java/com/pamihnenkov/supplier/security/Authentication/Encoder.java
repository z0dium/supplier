package com.pamihnenkov.supplier.security.Authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class PasswordEncoderImpl {

    @Bean("passwordEncoder")
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
