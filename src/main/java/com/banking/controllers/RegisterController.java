package com.banking.controllers;

import com.banking.helpers.HTML;
import com.banking.helpers.Token;
import com.banking.mailMessenger.MailMessenger;
import com.banking.models.User;
import com.banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Random;


@Controller // This annotation is used to tell the spring that this class is a controller.
public class RegisterController { // This class is used to handle the registration related requests.

    @Autowired // This annotation is used to tell the spring to inject the dependency.
    private UserRepository userRepository; // This is the dependency of this class.

    @GetMapping("/register") // This annotation is used to map the request url.
    public ModelAndView getRegister(){ // This method is used to get the register page.
        ModelAndView getRegisterPage = new ModelAndView("register");
        System.out.println("In Register Page Controller");
        getRegisterPage.addObject("PageTitle", "Register");
        return getRegisterPage;
    }

    @PostMapping("/register") // This annotation is used to map the request url.
    public ModelAndView register(@Valid @ModelAttribute("registerUser")User user,
                                 BindingResult result,
                                 @RequestParam("first_name") String first_name,
                                 @RequestParam("last_name") String last_name,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 @RequestParam("confirm_password") String confirm_password) throws MessagingException {

        ModelAndView registrationPage = new ModelAndView("register");

        // Check For Errors.
        if(result.hasErrors() && confirm_password.isEmpty()){
            registrationPage.addObject("confirm_pass", "The confirm Field is required");
            return registrationPage;
        }

        // Check for password mismatch.
        if(!password.equals(confirm_password)){
            registrationPage.addObject("passwordMisMatch", "passwords do not match");
            return registrationPage;
        }

        // Get Token String.
        String token = Token.generateToken();

        // Generate Random Code.
        Random rand = new Random();
        int bound = 123;
        int code = bound * rand.nextInt(bound);

        // Get Email Body.
        String emailBody = HTML.htmlEmailTemplate(token, code);

        // Hash Password.
        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        // Register User.
        userRepository.registerUser(first_name, last_name, email, hashed_password, token, code);

        // Send Email Notification.
        MailMessenger.htmlEmailMessenger("ornob011@gmail.com", email, "Verify Account", emailBody);

        // Return to Registration Page.
        String successMessage = "Account Registered Successfully, Please Check your Email and Verify Account!";
        registrationPage.addObject("success", successMessage);
        return registrationPage;
    }
}
