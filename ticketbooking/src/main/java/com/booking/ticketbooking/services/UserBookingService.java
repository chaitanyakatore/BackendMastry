package com.booking.ticketbooking.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.booking.ticketbooking.entities.Train;
import com.booking.ticketbooking.entities.User;
import com.booking.ticketbooking.util.UserServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserBookingService {

    private User user;
    private List<User> userList;

    // look for this things
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

    public void fetchBookings(){
        Optional<User> userFetched = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();
        if(userFetched.isPresent()){
            userFetched.get().printTickets();
        }
    }

        // todo: Complete this function
         public Boolean cancelBooking(String ticketId){
    
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the ticket id to cancel");
        ticketId = s.next();

        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticket ID cannot be null or empty.");
            return Boolean.FALSE;
        }

        String finalTicketId1 = ticketId;  //Because strings are immutable
        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId1));

        String finalTicketId = ticketId;
        user.getTicketsBooked().removeIf(Ticket -> Ticket.getTicketId().equals(finalTicketId));
        if (removed) {
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return Boolean.TRUE;
        }else{
        System.out.println("No ticket found with ID " + ticketId);
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String source, String destination){
        try{
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        }catch(IOException ex){
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train){
            return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }
}
