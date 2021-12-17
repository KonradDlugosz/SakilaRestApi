package com.sprata.sakila;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprata.sakila.entity.Staff;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StaffRequests {

    /**
     * GET by ID,
     * @return JSON body for staff with ID: 1
     */
    public static HttpResponse<String> getOneStaffConnection(){
        String getOneStaffUrl = "http://localhost:8080/sakila/staff/one?id=1";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(getOneStaffUrl)).build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static Staff getOneStaffJsonBody(){
        String json = getOneStaffConnection().body();

        ObjectMapper mapper = new ObjectMapper();
        Staff staff = null;
        try {
            staff = mapper.readValue(json, Staff.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return staff;
    }

    /**
     * POST Staff
     * @return JSON body for new staff
     */

    public static HttpResponse<String> insertNewStaff(){
        String post = "http://localhost:8080/sakila/staff/create";
        //TODO Change to POST request.
        HttpRequest request= HttpRequest.newBuilder().uri(URI.create(post))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
}
