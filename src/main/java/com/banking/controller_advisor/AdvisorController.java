package com.banking.controller_advisor;

import com.banking.models.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

// This class is used to provide the default values to the controllers.
@ControllerAdvice
public class AdvisorController {

    // This method is used to provide the default values to the controllers.
    @ModelAttribute("registerUser")
    public User getUserDefaults() {
        return new User();
    }
}
