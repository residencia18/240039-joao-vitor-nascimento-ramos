package br.com.cepedi.Voll.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestConfig {


    public static final int SERVE_PORT = 8081;
    public static final String CONTENT_TYPE_JSON = "application/json";

    public static final PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
