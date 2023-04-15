package com.chordncode.springmvcboard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value="file:${user.home}/Desktop/dev/springmail.properties", ignoreResourceNotFound = true)
public class MailConfig {
    
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;

    @Value("${spring.mail.properties.mail.smtp.ssl.enable}")
    private boolean enable;

    @Value("${spring.mail.properties.mail.smtp.ssl.trust}")
    private String trust;

}
