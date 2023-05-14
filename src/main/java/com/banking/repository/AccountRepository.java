package com.banking.repository;

import com.banking.models.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

// This is the repository class for the Account model.
@Repository
// This is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
public interface AccountRepository extends CrudRepository<Account, Integer> {

    // This is used to get the account details of a user by the user id.
    @Query(value = "SELECT * FROM accounts WHERE user_id = :user_id", nativeQuery = true)
    List<Account> getUserAccountsById(@Param("user_id") int user_id); // This is used to get the account details of a user by the user id.

    // This is used to get the total balance of a user by the user id.
    @Query(value = "SELECT sum(balance) FROM accounts WHERE user_id = :user_id", nativeQuery = true)
    BigDecimal getTotalBalance(@Param("user_id") int user_id);

    // This is used to get the account balance of a user by the user id and account id.
    @Query(value = "SELECT balance FROM accounts WHERE user_id = :user_id AND account_id = :account_id", nativeQuery = true)
    double getAccountBalance(@Param("user_id") int user_id, @Param("account_id") int account_id);

    // This method is used to update the account balance of a user by the user id and account id.
    @Modifying // This is used to indicate that the annotated method modifies the state of the database.
    @Query(value = "UPDATE accounts SET balance = :new_balance WHERE account_id = :account_id", nativeQuery = true)
    @Transactional // This is used to indicate that the annotated method is a transactional method.
    void changeAccountBalanceById(@Param("new_balance") double new_balance, @Param("account_id") int account_id);


    // This method is used to insert a new bank account for a user.
    @Modifying // This is used to indicate that the annotated method modifies the state of the database.
    @Query(value = "INSERT INTO accounts(user_id, account_number, account_name, account_type) VALUES" +
            "(:user_id, :account_number, :account_name,:account_type )", nativeQuery = true)
    @Transactional // This is used to indicate that the annotated method is a transactional method.
    void createBankAccount(@Param("user_id") int user_id,
                           @Param("account_number") String account_number,
                           @Param("account_name") String account_name,
                           @Param("account_type") String account_type);
}
