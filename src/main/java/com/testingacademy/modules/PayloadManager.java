package com.testingacademy.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.testingacademy.pojos.*;

public class PayloadManager {
    Gson gson;

    //Serialization and deserialization
    public String createPayloadBookingAsString(){
        Booking booking = new Booking();
        booking.setFirstname("James");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println("Booking id is 1:"+booking);
        // Java Object -> JSON String (byteStream) - Serlization
        gson = new Gson();
        String jsonStringpayload = gson.toJson(booking);
        System.out.println(jsonStringpayload);
        return jsonStringpayload;
    }
    public String createPayloadBookingAsStringFaker(){
        Faker faker = new Faker();
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.random().nextInt(1000));
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println("Booking id is 2:"+booking);
        // Java Object -> JSON String (byteStream) - Serlization
        gson = new Gson();
        String jsonStringpayload = gson.toJson(booking);
        System.out.println(jsonStringpayload);
        return jsonStringpayload;


    }
    public BookingResponse bookingResponseJava(String responseString) {
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }

    //get Token

    public String setAuthPayload(){
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String authPayload = gson.toJson(auth);
        System.out.println("Payload set to the"+authPayload);
        return authPayload;
    }

    public String getTokenfromJSON(String authResponse){
        gson = new Gson();
        AuthResponse authResponse1 = gson.fromJson(authResponse,AuthResponse.class );
        return authResponse1.getToken();

    }
    // get Booking ID
    public Booking getResponseFromJSON(String getResponse){
        gson = new Gson();
        // Response ( JSON) ->  Object TokenResponse
        // Deserialization
        Booking booking = gson.fromJson(getResponse,Booking.class);
        return booking;
    }
    public String fullUpdatePayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("jay");
        booking.setLastname("shankar");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);
    }


}
