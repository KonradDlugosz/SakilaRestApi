package com.sprata.sakila;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.sakila.entity.Staff;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.Map;

import static com.sprata.sakila.util.Constants.LOGGER;

public class StaffRequests {

    public static void main(String[] args) {
        System.out.println(deleteStaff());
    }

    /**
     * Base methods to get HttpResponse and deserialize JSON to Java Objects POJO
     * @param request -> Http request with headers, method like GET/POST/DELETE etc.
     * @return Staff Object containing JSON body
     */

    private static HttpResponse<String> getResponse(HttpRequest request) {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static Staff getObjectMapper(String json) {
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
     * GET by ID,
     * @return JSON body for staff with ID: 1
     */

    public static Staff getOneStaff(){
        String getOneStaffUrl = "http://localhost:8080/sakila/staff/one?id=1";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(getOneStaffUrl))
                .header("Content-Type", "application/json")
                .build();
        String json = getResponse(request).body();
        return getObjectMapper(json);
    }

    /**
     * POST Staff (INSERT)
     * @return JSON body for new staff
     */

    public static Staff postNewStaff(){
        String postUrl = "http://localhost:8080/sakila/staff/create";
        HttpRequest request= null;
        try {
            request = HttpRequest.newBuilder().uri(URI.create(postUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofFile(Path.of("addNewStaffBody.json")))
                    .build();
            LOGGER.info("POST request successful");
        } catch (FileNotFoundException e) {
            LOGGER.warn("POST request unsuccessful!");
            e.printStackTrace();
        }
        String json = getResponse(request).body();
        return getObjectMapper(json);
    }

    /**
     * PUT Staff (UPDATE)
     * @return JSON body for updated staff
     */

    public static Staff putStaff(){
        String putUrl = "http://localhost:8080/sakila/staff/update";
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder().uri(URI.create(putUrl))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofFile(Path.of("updateStaffBody.json")))
                    .build();
            LOGGER.info("PUT request successful");
        } catch (FileNotFoundException e) {
            LOGGER.warn("PUT request unsuccessful!");
            e.printStackTrace();
        }
        String json = getResponse(request).body();
        return getObjectMapper(json);
    }

    /**
     * DELETE Staff
     * @return JSON Body of deleted staff
     */

    public static Map<String, Boolean> deleteStaff(){
        String deleteUrl = "http://localhost:8080/sakila/staff/delete?id=34";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(deleteUrl))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
        String json = getResponse(request).body();

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, new TypeReference<>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
        } catch (JsonProcessingException e) {
            LOGGER.warn("Creating Map from JSON unsuccessful");
            e.printStackTrace();
        }
        return null;
    }

}
