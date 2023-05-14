package com.banking.repository;

import com.banking.models.Transact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

// This is the repository class for the Transact model.
@Repository
public interface TransactRepository extends CrudRepository<Transact, Integer> {

    // This method is used to insert a new transaction record.
    @Modifying // This is used to indicate that the annotated method modifies the state of the database.
    @Transactional // This is used to indicate that the annotated method is a transactional method.
    @Query(value = "INSERT INTO transaction_history(account_id, transaction_type, amount, source, status, reason_code, created_at)" +
            "VALUES(:account_id, :transact_type, :amount, :source, :status, :reason_code, :created_at)", nativeQuery = true)
    void logTransaction(@Param("account_id")int account_id,
                        @Param("transact_type")String transact_type,
                        @Param("amount")double amount,
                        @Param("source")String source,
                        @Param("status")String status,
                        @Param("reason_code")String reason_code,
                        @Param("created_at") LocalDateTime created_at);
}
