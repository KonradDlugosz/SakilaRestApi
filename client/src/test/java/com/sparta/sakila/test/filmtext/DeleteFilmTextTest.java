package com.sparta.sakila.test.filmtext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.sakila.entity.FilmText;
import com.sparta.sakila.entity.FilmTextAdd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeleteFilmTextTest {
    static int fId = 1001;
    static String fTitle ="Sparta Global";
    static String fDescription = "Where undergraduates comes for upskilling!";
    static FilmTextAdd filmTextAdd = null;

    // @DisplayName("Gets all the film details from the film Text")
    public static HttpResponse getFilmText(){
        FilmText filmAdd = new FilmText();
        filmAdd.setId(fId);
        filmAdd.setTitle(fTitle);
        filmAdd.setDescription(fDescription);
        String s = filmAdd.toJson();
        HttpRequest postRquest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/sakila/film_text/add"))
                .headers("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(s))
                .build();

        try {
             HttpClient.newHttpClient().send(postRquest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/sakila/film_text/delete/1001"))
                .headers("content-type", "application/json")
                .DELETE()
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
    public static FilmTextAdd jsonConverter(){
        HttpResponse<String> response =  getFilmText();
        if (response != null){
            String json = response.body();
            ObjectMapper mapper = new ObjectMapper();
            try {
                FilmTextAdd filmTextAdds = mapper.readValue(json, FilmTextAdd.class);
                return filmTextAdds;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @BeforeAll
    public static void getJson(){
        filmTextAdd = jsonConverter();
    }



    @DisplayName("Given that film has been inserted, return data id should match the inserted data")
    @Test
    public void getFilmTextID(){
        Assertions.assertEquals(1001, filmTextAdd.getFilm().getId());
    }

    @DisplayName("Given that film has been inserted, return film title should match the title of the inserted film")
    @Test
    public void getFilmTextTitleTest(){
        Assertions.assertEquals(fTitle,filmTextAdd.getFilm().getTitle());
    }

    @DisplayName("Given that film has been inserted, return film description should match the description of the inserted film")
    @Test
    public void getFilmTextDescriptionTest(){
        Assertions.assertEquals(fDescription,filmTextAdd.getFilm().getDescription());
    }

    @DisplayName("Given that film already exist, return should display message of it being existing already")
    @Test
    public void getFilmTextBeingExistAlready(){
        Assertions.assertFalse((filmTextAdd.getMessage().equals("No film exist with id " +fId)));
    }








}
