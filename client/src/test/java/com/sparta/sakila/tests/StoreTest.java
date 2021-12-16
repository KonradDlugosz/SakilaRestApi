package com.sparta.sakila.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprata.sakila.entity.Store;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(1, theStore.getManagerStaff().getId());
    }

    @Test
    public void testGetOneAddressID(){
        assertEquals(1, theStore.getAddress().getId());
    }

    @Test
    public void testGetOneLastUpdate(){
        assertEquals("2021-12-15T12:32:12Z", theStore.getLastUpdate());
    }

}