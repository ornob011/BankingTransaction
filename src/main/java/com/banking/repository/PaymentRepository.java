package com.banking.repository;

import com.banking.models.Payment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

// This is the repository class for the Payment model.
@Repository
// This is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

    // This method is used to insert a new payment record.
    @Modifying // This is used to indicate that the annotated method modifies the state of the database.
    @Query(value = " INSERT INTO payments(account_id, beneficiary, beneficiary_acc_no, amount, reference_no, status, reason_code, created_at) " +
            "VALUES(:account_id,:beneficiary, :beneficiary_acc_no, :amount, :reference_no, :status, :reason_code, :created_at )", nativeQuery = true)
    @Transactional // This is used to indicate that the annotated method is a transactional method.
    void makePayment(@Param("account_id") int account_id,
                     @Param("beneficiary") String beneficiary,
                     @Param("beneficiary_acc_no") String beneficiary_acc_no,
                     @Param("amount") double amount,
                     @Param("reference_no") String reference_no,
                     @Param("status") String status,
                     @Param("reason_code") String reason_code,
                     @Param("created_at") LocalDateTime created_at);
}