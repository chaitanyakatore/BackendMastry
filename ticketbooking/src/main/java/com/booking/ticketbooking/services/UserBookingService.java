package com.booking.ticketbooking.services;
import java.io.File;
import java.util.List;

import com.booking.ticketbooking.entities.User;

import aj.org.objectweb.asm.TypeReference;

public class UserBookingService {

    private User user;
    private List<User> userList;

    private static final String USER_PATH = "../localDb/users.json";

    private static final OBJECT_MAPPER = new ObjectMapper();
     

    public UserBookingService(User user1){
        this.user = user1;
        File users = new File(USER_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>(), {});

    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 ->{
            return user1.getName().equals((user.getName())) && UserServiceUtil.checkPassword(user.getPassword(), user1.getPassword()).findFirst(); 
            return foundUser.isPresent();
        })
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListtoFile();
            return Boolean.TRUE;
        }catch(IOException ex) {
            return Boolean.FALSE;
        }
    }


}
 