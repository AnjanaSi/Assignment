package com.todoapp.todoApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todoapp.todoApp.entity.ForgotPassword;
import com.todoapp.todoApp.entity.User;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {
    
    @Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.user = ?2")
    Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);
}
