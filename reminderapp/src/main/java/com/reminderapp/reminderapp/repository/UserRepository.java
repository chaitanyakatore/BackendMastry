package com.reminderapp.reminderapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reminderapp.reminderapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByBirthdate(LocalDate birthdate);
}
