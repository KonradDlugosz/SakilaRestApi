package com.sparta.sakila.test.filmtext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.sakila.entity.FilmText;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeleteFilmTextTest {

    static FilmText filmText = null;

    // @DisplayName("Gets all the film details from the film Text")
    public static HttpResponse getFilmText(){
               HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/sakila/film_text/delete/1001"))
                .headers("content-type", "application/json")
                .build();
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
    public static void getJson(){
        filmText = jsonConverter();
    }
}
