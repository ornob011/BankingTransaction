package com.banking.controllers;

import com.banking.helpers.Token;
import com.banking.models.User;
import com.banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;


@Controller // This annotation is used to tell the spring that this class is a controller.
public class AuthController { // This class is used to handle the authentication related requests.

    @Autowired // This annotation is used to tell the spring to inject the dependency.
    private UserRepository userRepository; // This is the dependency of this class.

    @GetMapping("/login") // This annotation is used to map the request url.
    public ModelAndView getLogin(){ // This method is used to get the login page.

        System.out.println("In Login Page Controller");
        ModelAndView getLoginPage = new ModelAndView("login");
        // Set Token String:
        String token = Token.generateToken();
        // Send Token to View:
        System.out.println(token);
        getLoginPage.addObject("token", token);
        getLoginPage.addObject("PageTitle", "Login");
        return getLoginPage;
    }


    @PostMapping("/login") // This annotation is used to map the request url.
    public String login(@RequestParam("email")String email,
                        @RequestParam("password") String password,
                        @RequestParam("_token")String token,
                        Model model,
                        HttpSession session){ // This method is used to login the user.

        // Validate Input Fields
        if(email.isEmpty() || email == null || password.isEmpty() || password == null){
            model.addAttribute("error", "Email or Password Cannot be Empty");
            return "login";
        }

        String getEmailInDatabase = userRepository.getUserEmail(email); // Get Email In Database

        // Check If Email Exists
        if(getEmailInDatabase != null ){
            // Get Password In Database
            String getPasswordInDatabase = userRepository.getUserPassword(getEmailInDatabase); // Get Password In Database

            // Validate Password
            if(!BCrypt.checkpw(password, getPasswordInDatabase)){
                model.addAttribute("error", "Incorrect Password");
                return "login";
            }
            // End Of Validate Password.
        }else{
            model.addAttribute("error", "This Account Does Not Exist");
            return "login";
        }

        int verified = userRepository.isVerified(getEmailInDatabase); //  Get Verified Status

        // Check If Account is verified:
        if (verified != 1){
            String msg = "This Account is not yet Verified, please check email and verify account";
            model.addAttribute("error", msg);
            return "login";
        }
        // End of account verification check.

        // Proceed to login the user:
        User user = userRepository.getUserDetails(getEmailInDatabase); // Get User Details

        // Set Session Attributes:
        session.setAttribute("user", user);
        session.setAttribute("token", token);
        session.setAttribute("authenticated", true);

        return "redirect:/app/dashboard";

    }
    // End Of Authentication Method.

    @GetMapping("/logout") // This annotation is used to map the request url.
    public String logout(HttpSession session, RedirectAttributes redirectAttributes){ // This method is used to logout the user.
        session.invalidate(); // Invalidate Session
        redirectAttributes.addFlashAttribute("logged_out", "Logged out successfully"); // Set Logged Out Message
        return "redirect:/login";
    }
    // End Of Logout Method.
}
