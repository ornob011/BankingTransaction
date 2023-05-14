package com.banking.rest_controllers;

import com.banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController // This annotation is used to tell the spring that this class is a rest controller.
@RequestMapping("/api/auth") // This annotation is used to map the request url.
public class RestAuthApiController { // This class is used to handle the rest api requests.

    @Autowired // This annotation is used to tell the spring to inject the dependency.
    private UserRepository userRepository; // This is the dependency of this class.

    @GetMapping("/login") // This annotation is used to map the request url.
    public ResponseEntity<String> validateUserEmail(@PathVariable("email")String email){ // This method is used to validate the user email.
        // Get User Email
        String userEmail = userRepository.getUserEmail(email);
        // Init User Password
        String userPassword = null;

        // Check If Email Is Validate
        if(userEmail != null){
            userPassword = userRepository.getUserPassword(email);
            // Return Response
            return new ResponseEntity<>(userPassword, HttpStatus.OK);
        }else{
            // Return Response
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        // End Of Check If Email Is Validate.
    }
    // End of Validate User Login.
}
// End Of Rest Auth API Controller.
