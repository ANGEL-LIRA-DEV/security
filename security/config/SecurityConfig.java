package com.angel.security.config;

import com.angel.security.filter.JwtRequestFilter;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.angel.security.util.Util.*;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(crf -> crf.disable())

                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers(AUTHENDPOINT,
                                        REFRESHENDPOINT,
                                        MESSAGECREATE,
                                        PREREGISTERENDPOINT,
                                        VERIFYENDPOINT,
                                        ACTIVATEACCOUNTENDPOINT,
                                        CATESTADOENDPOINT,
                                        CATCIUDADENDPOINT,
                                        CATPAISESENDPOINT
                                ).permitAll()

                                .anyRequest().authenticated()

                        )

                .addFilterBefore(jwtRequestFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .sessionManagement((session) -> session
                        .sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)
                );

        return http.build();

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }

}
