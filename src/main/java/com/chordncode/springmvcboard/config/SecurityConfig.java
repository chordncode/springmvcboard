package com.chordncode.springmvcboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

    private MemberLoginSuccessHandler memberLoginSuccessHandler;
    public SecurityConfig(MemberLoginSuccessHandler memberLoginSuccessHandler){
        this.memberLoginSuccessHandler = memberLoginSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        http.formLogin(login -> login
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/board")
                    .usernameParameter("memId")
                    .passwordParameter("memPw")
                    .successHandler(memberLoginSuccessHandler)
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
            );

        return http.build();

    }
    
}
