package com.banking.controllers;

import com.banking.helpers.GenAccountNumber;
import com.banking.models.User;
import com.banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

// This class is used to handle the account related requests.
@Controller // This annotation is used to tell the spring that this class is a controller.
@RequestMapping("/account") // This annotation is used to map the request url.
public class AccountController {

    @Autowired // This annotation is used to tell the spring to inject the dependency.
    private AccountRepository accountRepository; // This is the dependency of this class.

    @PostMapping("/create_account") // This annotation is used to map the request url.
    // This method is used to create the bank account.
    public String createAccount(@RequestParam("account_name") String accountName,
                                @RequestParam("account_type") String accountType,
                                RedirectAttributes redirectAttributes,
                                HttpSession session)// This annotation is used to map the request parameter.
    {

        // Check If Account Name And Type Is Empty
        if (accountName.isEmpty() || accountType.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Account Name and Type Cannot be Empty!");
            return "redirect:/app/dashboard";
        }

        // Get Logged In User
        User user = (User) session.getAttribute("user");

        // Generate Account Number
        int setAccountNumber = GenAccountNumber.generateAccountNumber();
        String bankAccountNumber = Integer.toString(setAccountNumber);

        // Create Bank Account
        accountRepository.createBankAccount(user.getUser_id(), bankAccountNumber, accountName, accountType);

        // Set Success Message
        redirectAttributes.addFlashAttribute("success", "Account Created Successfully!");
        return "redirect:/app/dashboard";
    }
}
