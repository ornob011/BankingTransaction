package com.banking.repository;

import com.banking.models.TransactionHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

// This is the repository class for the TransactionHistory model.
@Repository // This is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
public interface TransactHistoryRepository extends CrudRepository<TransactionHistory, Integer> {

    // This is used to get the transaction records of a user by the user id.
    @Query(value = "SELECT * FROM v_transaction_history WHERE user_id = :user_id", nativeQuery = true)
    List<TransactionHistory> getTransactionRecordsById(@Param("user_id")int user_id);
}
