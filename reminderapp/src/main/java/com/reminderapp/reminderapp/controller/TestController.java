package com.reminderapp.reminderapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reminderapp.reminderapp.service.BirthdayReminderService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private BirthdayReminderService birthdayReminderService;

    @GetMapping("/send")
    public String testEmail() {
        birthdayReminderService.sendBirthdayReminders();
        return "✅ Email process triggered!";
    }
}
