package com.banking.mailMessenger;

import com.banking.config.MailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

// This class is used to send the mail.
@Component
public class MailMessenger {

    @Autowired
    private JavaMailSender sender;

    public void htmlEmailMessenger(String from, String toMail, String subject, String body) throws MessagingException {
        // Set Mime Message
        MimeMessage message = sender.createMimeMessage();
        // Set Mime Message Helper
        MimeMessageHelper htmlMessage = new MimeMessageHelper(message, true);

        // Set Mail Attributes / Properties
        htmlMessage.setTo(toMail);
        htmlMessage.setFrom(from);
        htmlMessage.setSubject(subject);
        htmlMessage.setText(body, true);
        // Send Message
        sender.send(message);
    }
    // End Of HTML Email Message Method.
}
