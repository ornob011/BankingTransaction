package com.banking.controllers;

import com.banking.models.User;
import com.banking.repository.AccountRepository;
import com.banking.repository.PaymentRepository;
import com.banking.repository.TransactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;


@Controller // This annotation is used to tell the spring that this class is a controller.
@RequestMapping("/transact") // This annotation is used to map the request url.
public class TransactController { // This class is used to handle the transaction related requests.

    @Autowired // This annotation is used to tell the spring to inject the dependency.
    private AccountRepository accountRepository; // This is the dependency of this class.

    @Autowired // This annotation is used to tell the spring to inject the dependency.
    private PaymentRepository paymentRepository; // This is the dependency of this class.
    @Autowired // This annotation is used to tell the spring to inject the dependency.
    private TransactRepository transactRepository; // This is the dependency of this class.

    User user;
    double currentBalance;
    double newBalance;
    LocalDateTime currentDateTime = LocalDateTime.now();

    // This method is used to handle the deposit request.
    @PostMapping("/deposit") // This annotation is used to map the request url.
    public String deposit(@RequestParam("deposit_amount") String depositAmount,
                          @RequestParam("account_id") String accountID,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        // Check If Deposit Amount And Account ID Is Empty.
        if (depositAmount.isEmpty() || accountID.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Deposit Amount or Account Depositing to Cannot Be Empty!");
            return "redirect:/app/dashboard";
        }
        // Get Logged In User.
        user = (User) session.getAttribute("user");

        // Get Current Account Balance.
        int acc_id = Integer.parseInt(accountID);
        double depositAmountValue = Double.parseDouble(depositAmount);

        //Check if deposit amount is 0
        if (depositAmountValue == 0) {
            redirectAttributes.addFlashAttribute("error", "Deposit Amount Cannot Be of 0 (Zero) Value");
            return "redirect:/app/dashboard";
        }

        // Update Account Balance.
        currentBalance = accountRepository.getAccountBalance(user.getUser_id(), acc_id);

        newBalance = currentBalance + depositAmountValue;

        // Update Account.
        accountRepository.changeAccountBalanceById(newBalance, acc_id);

        // Log Successful Transaction.
        transactRepository.logTransaction(acc_id, "Deposit", depositAmountValue, "online", "success", "Deposit Transaction Successful", currentDateTime);

        // Add Success Message.
        redirectAttributes.addFlashAttribute("success", "Amount Deposited Successfully");
        return "redirect:/app/dashboard";
    }
    // End Of Deposits.


    // This method is used to handle the withdrawal request.
    @PostMapping("/transfer") // This annotation is used to map the request url.
    public String transfer(@RequestParam("transfer_from") String transfer_from,
                           @RequestParam("transfer_to") String transfer_to,
                           @RequestParam("transfer_amount") String transfer_amount,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        // Init Error Message Value.
        String errorMessage;

        // Check for empty fields.
        if (transfer_from.isEmpty() || transfer_to.isEmpty() || transfer_amount.isEmpty()) {
            errorMessage = "The account transferring from and to along with the amount cannot be empty!";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        // Convert Variables.
        int transferFromId = Integer.parseInt(transfer_from);
        int transferToId = Integer.parseInt(transfer_to);
        double transferAmount = Double.parseDouble(transfer_amount);

        // Check if transferring into the same account.
        if (transferFromId == transferToId) {
            errorMessage = "Cannot Transfer Into The same Account, Please select the appropriate account to perform transfer";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        // Check for 0 value.
        if (transferAmount == 0) {
            errorMessage = "Cannot Transfer an amount of 0 (Zero) value, please enter a value greater than 0 (Zero) ";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        // Get Logged In User.
        user = (User) session.getAttribute("user");

        // Get Current Account Balance:
        double currentBalanceOfAccountTransferringFrom = accountRepository.getAccountBalance(user.getUser_id(), transferFromId);

        // Check if the user has sufficient funds to perform the transfer.
        if (currentBalanceOfAccountTransferringFrom < transferAmount) {
            errorMessage = "You Have insufficient Funds to perform this Transfer!";
            // Log Failed Transaction:
            transactRepository.logTransaction(transferFromId, "Transfer", transferAmount, "online", "failed", "Insufficient Funds", currentDateTime);
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        // Get Current Account Balance.
        double currentBalanceOfAccountTransferringTo = accountRepository.getAccountBalance(user.getUser_id(), transferToId);

        // Set New Balance.
        double newBalanceOfAccountTransferringFrom = currentBalanceOfAccountTransferringFrom - transferAmount;

        double newBalanceOfAccountTransferringTo = currentBalanceOfAccountTransferringTo + transferAmount;

        // Changed The Balance Of the Account Transferring From.
        accountRepository.changeAccountBalanceById(newBalanceOfAccountTransferringFrom, transferFromId);

        // Changed The Balance Of the Account Transferring To.
        accountRepository.changeAccountBalanceById(newBalanceOfAccountTransferringTo, transferToId);

        // Log Successful Transaction.
        transactRepository.logTransaction(transferFromId, "Transfer", transferAmount, "online", "success", "Transfer Transaction Successful", currentDateTime);

        String successMessage = "Amount Transferred Successfully!";
        redirectAttributes.addFlashAttribute("success", successMessage);
        return "redirect:/app/dashboard";
    }
    // End Of Transfer Method.

    // This method is used to handle the withdrawal request.
    @PostMapping("/withdraw") // This annotation is used to map the request url.
    public String withdraw(@RequestParam("withdrawal_amount") String withdrawalAmount,
                           @RequestParam("account_id") String accountID,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {

        String errorMessage;
        String successMessage;

        // Check for empty fields.
        if (withdrawalAmount.isEmpty() || accountID.isEmpty()) {
            errorMessage = "Withdrawal Amount and Account Withdrawing From Cannot be Empty ";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }
        // Convert Variables.
        double withdrawal_amount = Double.parseDouble(withdrawalAmount);
        int account_id = Integer.parseInt(accountID);

        // Check for 0 value.
        if (withdrawal_amount == 0) {
            errorMessage = "Withdrawal Amount Cannot be of 0 (Zero) value, please enter a value greater than 0 (Zero)";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        // Get Logged In User.
        user = (User) session.getAttribute("user");

        // Get Current Account Balance:
        currentBalance = accountRepository.getAccountBalance(user.getUser_id(), account_id);

        // Check if the user has sufficient funds to perform the transfer.
        if (currentBalance < withdrawal_amount) {
            errorMessage = "You Have insufficient Funds to perform this Withdrawal!";
            // Log Failed Transaction:
            transactRepository.logTransaction(account_id, "Withdrawal", withdrawal_amount, "online", "failed", "Insufficient Funds", currentDateTime);
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        // Set New Balance.
        newBalance = currentBalance - withdrawal_amount;

        // Update Account Balance.
        accountRepository.changeAccountBalanceById(newBalance, account_id);

        // Log Successful Transaction.
        transactRepository.logTransaction(account_id, "Withdrawal", withdrawal_amount, "online", "success", "Withdrawal Transaction Successful", currentDateTime);

        successMessage = "Withdrawal Successful!";
        redirectAttributes.addFlashAttribute("success", successMessage);
        return "redirect:/app/dashboard";
    }
    // End Of Withdrawal Method.

    // This method is used to handle the payment request.
    @PostMapping("/payment") // This annotation is used to map the request url.
    public String payment(@RequestParam("beneficiary") String beneficiary,
                          @RequestParam("account_number") String account_number,
                          @RequestParam("account_id") String account_id,
                          @RequestParam("reference") String reference,
                          @RequestParam("payment_amount") String payment_amount,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        String errorMessage;
        String successMessage;

        // Check for empty fields.
        if (beneficiary.isEmpty() || account_number.isEmpty() || account_id.isEmpty() || payment_amount.isEmpty()) {
            errorMessage = "Beneficiary, Account Number, Account Paying From and Payment Amount Cannot be Empty! ";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        // Convert Variables.
        int accountID = Integer.parseInt(account_id);
        double paymentAmount = Double.parseDouble(payment_amount);

        // Check for 0 value.
        if (paymentAmount == 0) {
            errorMessage = "Payment Amount Cannot be of 0 (Zero) value, please enter a value greater than 0 (Zero) ";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        // Get Logged In User.
        user = (User) session.getAttribute("user");

        // Get Current Account Balance:
        currentBalance = accountRepository.getAccountBalance(user.getUser_id(), accountID);

        // Check if the user has sufficient funds to perform the transfer.
        if (currentBalance < paymentAmount) {
            errorMessage = "You Have insufficient Funds to perform this payment";
            String reasonCode = "Could not Processed Payment due to insufficient funds!";
            paymentRepository.makePayment(accountID, beneficiary, account_number, paymentAmount, reference, "failed", reasonCode, currentDateTime);
            // Log Failed Transaction:
            transactRepository.logTransaction(accountID, "Payment", paymentAmount, "online", "failed", "Insufficient Funds", currentDateTime);
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        // Set New Balance to the account paying from.
        newBalance = currentBalance - paymentAmount;

        // Make Payment.
        String reasonCode = "Payment Processed Successfully!";
        paymentRepository.makePayment(accountID, beneficiary, account_number, paymentAmount, reference, "success", reasonCode, currentDateTime);

        // Update Account Paying From Balance.
        accountRepository.changeAccountBalanceById(newBalance, accountID);

        // Log Successful Transaction.
        transactRepository.logTransaction(accountID, "Payment", paymentAmount, "online", "success", "Payment Transaction Successful", currentDateTime);

        successMessage = reasonCode;
        redirectAttributes.addFlashAttribute("success", successMessage);
        return "redirect:/app/dashboard";
    }
    // End Of Payment Method.
}
