package fr.arosaje.nosithouss.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CryptPassword {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
}
