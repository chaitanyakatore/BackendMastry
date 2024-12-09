package com.booking.ticketbooking.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.booking.ticketbooking.entities.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserBookingService {

    private User user;
    private List<User> userList;

    private static final String USER_PATH = "../localDb/users.json";

    // ObjectMapper should be initialized as a static constant
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        File users = new File(USER_PATH);
        
        // Corrected the use of ObjectMapper and TypeReference
        if (users.exists()) {
            userList = OBJECT_MAPPER.readValue(users, new TypeReference<List<User>>() {});
        } else {
            userList = List.of(); // Initialize an empty list if the file doesn't exist
        }
    }

    public Boolean loginUser() {
        // Fixed the return statement and syntax for stream filtering
        Optional<User> foundUser = userList.stream()
            .filter(user1 -> user1.getName().equals(user.getName()) 
                && UserServiceUtil.checkPassword(user.getPassword(), user1.getPassword()))
            .findFirst();
        
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            ex.printStackTrace(); // Log the exception for debugging
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        // Write the updated userList back to the JSON file
        OBJECT_MAPPER.writeValue(new File(USER_PATH), userList);
    }
}
