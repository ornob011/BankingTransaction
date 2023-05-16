package com.banking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import java.util.Properties;

// This class is used to configure the mail.
@Configuration // Defines the configuration for the data source used by the application context.
@PropertySource("classpath:application.properties") // Used to load the properties file.
@Component // Represents service for managing email configuration.
public class MailConfig {

    // Get the email username and password from the application.properties file.
    @Value("${email.username}")
    private String emailUsername;

    @Value("${email.password}")
    private String emailPassword;

    // Configure the mail.
    @Bean // This annotation is used to indicate that a method produces a bean to be managed by the Spring container.
    public JavaMailSenderImpl getMailConfig() {

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
        emailConfig.setUsername(emailUsername);
        emailConfig.setPassword(emailPassword);

        return emailConfig;
    }
    // End Of Email Config Method.
}
