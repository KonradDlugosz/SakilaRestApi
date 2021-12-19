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

public class PatchFilmTextTest {
        static int fId = 1001;
        static String fTitle ="Sparta Global";
        static String fDescription = "Where undergraduates comes for upskilling!";
        static FilmTextAdd filmTextAdd = null;
        static FilmText filmAdd = new FilmText();
        // @DisplayName("Gets all the film details from the film Text")
        public static HttpResponse getFilmText(){

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
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            filmAdd.setDescription("This is updated Description");
            s = filmAdd.toJson();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/sakila/film_text/update"))
                    .headers("content-type", "application/json")
                    .method("PATCH",HttpRequest.BodyPublishers.ofString(s))
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


        @DisplayName("Given that film has been one had been updated, return data id should match the inserted data")
        @Test
        public void getFilmTextID(){
            Assertions.assertEquals(filmAdd.getId(), filmTextAdd.getFilm().getId());
        }

        @DisplayName("Given that film has been updated , return film title should match the title of the of updated film")
        @Test
        public void getFilmTextTitleTest(){
            Assertions.assertEquals(filmAdd.getTitle(),filmTextAdd.getFilm().getTitle());
        }

        @DisplayName("Given that film has been updated, return film description should match the new description of the film")
        @Test
        public void getFilmTextDescriptionTest(){
            Assertions.assertEquals(filmAdd.getDescription(),filmTextAdd.getFilm().getDescription());
        }














}
