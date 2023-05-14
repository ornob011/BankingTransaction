package com.banking.repository;

import com.banking.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// This is the repository class for the User model.
@Repository // This is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
public interface UserRepository  extends CrudRepository<User, Integer> {

    // This is used to get the user email address by the email.
    @Query(value = "SELECT email FROM users WHERE email = :email", nativeQuery = true)
    String getUserEmail(@Param("email")String email);

    // This is used to get the user password by the email.
    @Query(value = "SELECT password FROM users WHERE email = :email", nativeQuery = true)
    String getUserPassword(@Param("email")String email);

    // This is used to get the verified status by the email.
    @Query(value = "SELECT verified FROM users WHERE email = :email", nativeQuery = true)
    int isVerified(@Param("email")String email);

    // This is used to get the user details by the email.
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    User getUserDetails(@Param("email")String email);

    // This is used to insert the user details into the database.
    @Modifying // This is used to indicate that the annotated method is a modifying method.
    @Query(value = "INSERT INTO users (first_name, last_name, email, password, token, code) VALUES" +
            "(:first_name, :last_name, :email, :password, :token, :code)", nativeQuery = true )
    @Transactional // This is used to indicate that the annotated method is a transactional method.
    void registerUser(@Param("first_name")String first_name,
                      @Param("last_name") String last_name,
                      @Param("email")String email,
                      @Param("password")String password,
                      @Param("token") String token,
                      @Param("code")int code);

    @Modifying // This is used to indicate that the annotated method is a modifying method.
    @Query(value = "UPDATE users SET token=null, code=null, verified=1, verified_at=NOW(), updated_at=NOW() WHERE " +
            "token= :token AND code= :code", nativeQuery = true) // This is used to update the user details.
    @Transactional // This is used to indicate that the annotated method is a transactional method.
    void verifyAccount(@Param("token")String token, @Param("code") String code); // This is used to verify the user account.

    // This is used to get the token by the token.
    @Query(value = "SELECT token FROM users WHERE token = :token" , nativeQuery = true)
    String checkToken(@Param("token")String token);
}
