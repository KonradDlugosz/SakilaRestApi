package com.sparta.sakila.test.filmtext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprata.sakila.entity.FilmText;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

public class OneFilmTextTest {


    static FilmText filmText = null;

    // @DisplayName("Gets all the film details from the film Text")
    public static HttpResponse getFilmText(){
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/sakila/film_text/1")).build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> resq = null;
        try {
            resq = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return  resq;
    }



    //converts JSon into hash map
    public static FilmText jsonConverter(){
        HttpResponse<String> response =  getFilmText();
        if (response != null){
            String json = response.body();
            ObjectMapper mapper = new ObjectMapper();
            try {
                FilmText filmText = mapper.readValue(json,FilmText.class);
                return filmText;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    @BeforeAll
    public static void getFilmTextObject(){
        filmText = jsonConverter();
    }


    @DisplayName("Given an film id one, return is film ID of One")
    @Test
    public void getFilmTextID(){
        Assertions.assertEquals(1, filmText.getId());
    }

    @DisplayName("Given film id one, return film title")
    @Test
    public void getFilmTitleTest(){
        Assertions.assertEquals("ACADEMY DINOSAUR", filmText.getTitle());
    }

    @DisplayName("Given film ID one, return film description")
    public void getFilmDescriptionTest(){
        Assertions.assertEquals("A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies", filmText.getDescription());
    }








}
