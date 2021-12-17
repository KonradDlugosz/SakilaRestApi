package com.sparta.sakila.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.sakila.entity.Store;
import com.sparta.sakila.entity.util.StoreAndStaff;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoreTest{

    Store theStore = makeHTTPRequest();

    private static Store makeHTTPRequest(){
        ObjectMapper mapper = new ObjectMapper();
        Store store = null;
        try {
            store = mapper.readValue(new URL("http://localhost:8080/sakila/store/getbyid/1"), Store.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return store;
    }

    @Test
    public void testGetOneID(){
        assertEquals(1, theStore.getId());
    }

    @Test
    public void testGetOneManagerID(){
        assertEquals("Mike", theStore.getManagerStaff().getFirstName());
    }

    @Test
    public void testGetOneAddressID(){
        assertEquals(1, theStore.getAddress().getId());
    }

    @Test
    public void testGetOneLastUpdate(){
        assertEquals("2021-12-15T12:32:12Z", theStore.getLastUpdate());
    }

    @Disabled
    @Test
    public void testCreateNewStore(){
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("http://localhost:8080/sakila/store/create"))
                .POST(HttpRequest.BodyPublishers.ofString("""
                        {
                               "id": 6,
                               "managerStaff": {
                                   "id": 35,
                                   "firstName": "UT",
                                   "lastName": "UT",
                                   "address": {
                                       "id": 17
                                   },
                                   "picture": null,
                                   "email": "Jon.Stephens@sakilastaff.com",
                                   "store": 2,
                                   "active": true,
                                   "username": "Jon",
                                   "password": null,
                                   "lastUpdate": "2006-02-15T03:57:16Z"
                               },
                               "address": {
                                   "id": 2
                               },
                               "lastUpdate": "2006-02-15T04:57:12Z"
                           }""".indent(1)))
                .header("content-type", "application/json")
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, response.statusCode());
    }

    @Disabled
    @Test
    public void deleteStore(){
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("http://localhost:8080/sakila/store/deletebyid/24"))
                .DELETE()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(200, response.statusCode());
    }

    @Test
    public void updateStore(){
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("http://localhost:8080/sakila/store/update"))
                .method("PATCH", HttpRequest.BodyPublishers.ofString("""
                        {
                               "id": 23,
                               "managerStaff": {
                                   "id": 3
                               },
                               "address": {
                                   "id": 12
                               }
                           }""".indent(1)))
                .header("content-type", "application/json")
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(202, response.statusCode());
    }

    @Test
    public void testGetStoreAndAllStaff(){
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("http://localhost:8080/sakila/store/getstoreandstaff/1"))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper =new ObjectMapper();
        try {
            StoreAndStaff sns = mapper.readValue(response.body(), StoreAndStaff.class);
            assertEquals(1, sns.getStore().getId());
            assertTrue(sns.getAllStaff().size() > 1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}