package com.sparta.sakila.test.filmtext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprata.sakila.entity.FilmText;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class AllFilmTextTest {

    static HashMap<Integer, FilmText> filmTextHashMap = null;


   // @DisplayName("Gets all the film details from the film Text")
    public static HttpResponse getFilmText(){
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/sakila/film_text")).build();
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
    public static HashMap<Integer, FilmText> jsonConverter(){
        HttpResponse<String> response =  getFilmText();
        HashMap<Integer, FilmText> film_text = new HashMap<>();
        if (response != null){
            String json = response.body();
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<FilmText> filmTextsList = mapper.readValue(json, new TypeReference<>() {
                });
                for (FilmText filmText : filmTextsList){
                    film_text.put(filmText.getId(), filmText);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return film_text;
    }


    @BeforeAll
    public static void getHashMap(){
        filmTextHashMap =  jsonConverter();
    }

    @DisplayName("Test for all the films, checking if a film exist with id = 1")
    @Test
    public void filmTextContainFilmIdOfOne(){
        Assertions.assertTrue(filmTextHashMap.containsKey(1));
        Optional<Integer> id = filmTextHashMap.entrySet().stream()
                .filter(e -> e.getValue().getId() == 1)
                .map(Map.Entry::getKey).findFirst();
        Assertions.assertEquals(1,id.get());
    }

    @DisplayName("Film Text size of the table 1000")
    @Test
    public void filmTextSizeTest(){
        Assertions.assertEquals(filmTextHashMap.size(), 1000);
    }

    @DisplayName("Film Text contains certain film Titles")
    @ParameterizedTest
    @CsvSource({"UNTOUCHABLES SUNRISE", "UPRISING UPTOWN", "SMILE EARRING", "JINGLE SAGEBRUSH", "FANTASIA PARK", "BACKLASH UNDEFEATED", "ZHIVAGO CORE"})
    public void filmTextContainsSpecificFilmTitles(String ints){
        List<String> filmTitle = new ArrayList<>();
        filmTextHashMap.forEach((k, v) -> filmTitle.add(v.getTitle()));
        Assertions.assertTrue(filmTitle.contains(ints));
    }

    @DisplayName("Test for film text containing description")
    @Test
    public void filmTextDescriptionTest(){
        FilmText expectedFilm = new FilmText();
        expectedFilm.setId(566);
        expectedFilm.setTitle("MAUDE MOD");
        expectedFilm.setDescription("A Beautiful Documentary of a Forensic Psychologist And a Cat who must Reach a Astronaut in Nigeria");

        FilmText actualFilm  = new FilmText();
        actualFilm.setId((filmTextHashMap.get(566).getId()));
        actualFilm.setTitle((filmTextHashMap.get(566).getTitle()));
        actualFilm.setDescription((filmTextHashMap.get(566).getDescription()));


        Assertions.assertEquals(expectedFilm.getId(), actualFilm.getId());
        Assertions.assertEquals(expectedFilm.getTitle(), actualFilm.getTitle());
        Assertions.assertEquals(expectedFilm.getDescription(), actualFilm.getDescription());
    }















}
