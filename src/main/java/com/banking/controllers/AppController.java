package com.banking.controllers;

import com.banking.models.Account;
import com.banking.models.PaymentHistory;
import com.banking.models.TransactionHistory;
import com.banking.models.User;
import com.banking.repository.AccountRepository;
import com.banking.repository.PaymentHistoryRepository;
import com.banking.repository.TransactHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

// This class is used to handle the app related requests.
@Controller // This annotation is used to tell the spring that this class is a controller.
@RequestMapping("/app") // This annotation is used to map the request url.
public class AppController {

    @Autowired // This annotation is used to tell the spring to inject the dependency.
    private AccountRepository accountRepository; // This is the dependency of this class.

    @Autowired // This annotation is used to tell the spring to inject the dependency.
    private PaymentHistoryRepository paymentHistoryRepository; // This is the dependency of this class.

    @Autowired // This annotation is used to tell the spring to inject the dependency.
    private TransactHistoryRepository transactHistoryRepository; // This is the dependency of this class.

    User user;


    @GetMapping("/dashboard") // This annotation is used to map the request url.
    public ModelAndView getDashboard(HttpSession session){ // This method is used to get the dashboard page.
        ModelAndView getDashboardPage = new ModelAndView("dashboard");

        // Get the details of the logged in user
        user = (User)session.getAttribute("user");

        // Get The accounts of the logged in user
        List<Account> getUserAccounts = accountRepository.getUserAccountsById(user.getUser_id());

        // Get Balance
        BigDecimal totalAccountsBalance = accountRepository.getTotalBalance(user.getUser_id());

        // Set The View/Objects
        getDashboardPage.addObject("userAccounts", getUserAccounts);
        getDashboardPage.addObject("totalBalance", totalAccountsBalance);

        return getDashboardPage;
    }


    @GetMapping("/payment_history") // This annotation is used to map the request url.
    public ModelAndView getPaymentHistory(HttpSession session){ // This method is used to get the payment history page.
        // Set View
        ModelAndView getPaymentHistoryPage = new ModelAndView("paymentHistory");

        // Get Logged In User
        user = (User) session.getAttribute("user");

        // Get Payment History/Records:
        List<PaymentHistory> userPaymentHistory = paymentHistoryRepository.getPaymentRecordsById(user.getUser_id());

        getPaymentHistoryPage.addObject("payment_history", userPaymentHistory);

        return getPaymentHistoryPage;

    }


    @GetMapping("/transact_history") // This annotation is used to map the request url.
    public ModelAndView getTransactHistory(HttpSession session){ // This method is used to get the transaction history page.
        // Set View
        ModelAndView getTransactHistoryPage = new ModelAndView("transactHistory");

        // Get Logged In User
        user = (User) session.getAttribute("user");

        // Get Payment History/Records:
        List<TransactionHistory> userTransactHistory = transactHistoryRepository.getTransactionRecordsById(user.getUser_id());

        getTransactHistoryPage.addObject("transact_history", userTransactHistory);

        return getTransactHistoryPage;

    }

}
