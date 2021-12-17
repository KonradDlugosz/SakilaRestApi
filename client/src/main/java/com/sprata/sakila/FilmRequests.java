package com.sprata.sakila;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.sakila.entity.Film;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class FilmRequests {

    public static void main(String[] args) {
        System.out.println(getOneFilmJasonBody());
    }

    public static HttpResponse<String> getOneFilmConnection() {
        String getOneFilmUrl = "http://localhost:8080/sakila/films/5";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(getOneFilmUrl)).build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static Film getOneFilmJasonBody() {
        String json = getOneFilmConnection().body();
        ObjectMapper mapper = new ObjectMapper();
        Film film = null;
        try {
            film = mapper.readValue(json, Film.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return film;
    }



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

    private static Film getObjectMapper(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Film film = null;
        try {
            film = mapper.readValue(json, Film.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return film;
    }



    public static Film postNewFilm(){
        String postUrl = "http://localhost:8080/sakila/films/add";
        HttpRequest request = null;
        try{
            request = HttpRequest.newBuilder().uri(URI.create(postUrl))
                    .header("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofFile(Path.of("addNewFilmBody.json")))
                    .build();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        String json = getResponse(request).body();
        return getObjectMapper(json);
    }

    public static Film putNewFilm(){
        String putUrl = "http://localhost:8080/sakila/films/update";
        HttpRequest request = null;
        try{
            request = HttpRequest.newBuilder().uri(URI.create(putUrl))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofFile(Path.of("updateFilmBody.json")))
                    .build();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        String json = getResponse(request).body();
        return getObjectMapper(json);
    }
}
