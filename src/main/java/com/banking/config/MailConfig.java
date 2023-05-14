package com.banking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

// This class is used to configure the mail.
public class MailConfig {

    @Bean
    public static JavaMailSenderImpl getMailConfig() {
        JavaMailSenderImpl emailConfig = new JavaMailSenderImpl();

        // Set Mail Properties:
        Properties props = emailConfig.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        // Set Mail Credentials:
        emailConfig.setHost("smtp.gmail.com");
        emailConfig.setPort(587);
        emailConfig.setUsername("ornob011@gmail.com");
        emailConfig.setPassword("krexmzoojaxqpmqq");

        return emailConfig;
    }
    // End Of Email Config Method.
}
