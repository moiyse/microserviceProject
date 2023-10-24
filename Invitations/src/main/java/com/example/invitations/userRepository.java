package com.example.invitations;

import com.example.invitations.Equipe.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User, Integer>{

    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.email=:email and u.enabled=false")
    List<User> getUsersByEmailNotValid(String email);

    @Query("select u from User u where u.email=:email and u.enabled=true")
    List<User> getUsersByEmailValid(String email);

    Optional<User> getByEmail(String email);

    @Query("select count(u) = 1 from User u where u.email=:email and u.enabled=true")
    Boolean existsByEmail(String email);

    @Query("select count(u) = 1 from User u where u.email=:email and u.password=:password")
    Boolean checkCredentials(@Param("email")String email,@Param("password")String password);

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    Optional<User> findByVerificationCode(String code);

    List<User> getByEquipe(Equipe equipe);

    public Optional<User> findByResetPasswordToken(String token);

}
