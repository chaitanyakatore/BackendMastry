package com.reminderapp.reminderapp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.reminderapp.reminderapp.model.User;
import com.reminderapp.reminderapp.repository.UserRepository;

@Service
public class BirthdayReminderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 42 23 * * ?") // Runs at 11:30 PM
    public void sendBirthdayReminders() {
    System.out.println("Birthday Reminder Task Started at: " + LocalDateTime.now());

    LocalDate today = LocalDate.now();
    List<User> birthdayUsers = userRepository.findByBirthdate(today);

    if (birthdayUsers.isEmpty()) {
        System.out.println("No birthdays found for today.");
    } else {
        for (User user : birthdayUsers) {
            System.out.println("Sending email to: " + user.getEmail());
            emailService.sendBirthdayEmail(user.getEmail(), user.getName());
        }
    }
}

}
