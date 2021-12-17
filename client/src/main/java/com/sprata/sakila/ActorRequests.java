package com.sprata.sakila;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprata.sakila.entity.Actor;
import com.sprata.sakila.entity.Staff;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.Map;

public class ActorRequests {



    public static Actor getActor(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Actor actor = null;
        try {
            actor = mapper.readValue(json, Actor.class);
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return actor;
    }//Done

    public static Actor getOneActors() {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sakila/actors/12"))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> resp = null;
        try {
            resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actor a = getActor(resp.body());
        return a;
    }//Done



    public static Actor createActor(){
        HttpRequest req = null;
        try {
            req = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/sakila/actors/add"))
                    .POST(HttpRequest.BodyPublishers.ofFile(Path.of("addingNewActor.json")))
                    .build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> resp = null;
        try {
            resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actor a = getActor(resp.body());
        return a;
    }


    public static Map<String,Boolean> deleteOneActor(){
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sakila/actors/201"))
                .DELETE()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> resp = null;
        try {
            resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ////Map<String, Boolean> a = getActor(resp.body());
        ////return a;

    }

    public static Actor putActor(){
        HttpRequest req = null;
        try {
            req = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/sakila/actors/update"))
                    .PUT(HttpRequest.BodyPublishers.ofFile(Path.of("updatingActor.json")))
                    .build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> resp = null;
        try {
            resp = client.send(req, HttpResponse.BodyHandlers.ofString()); //toString because Response body type set to String(2 lines above)
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actor a = getActor(resp.body());
        return a;
    }//Done

    public static Actor patchActor(){
        HttpRequest req = null;
        try {
            req = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/sakila/actors/update"))
                    .PUT(HttpRequest.BodyPublishers.ofFile(Path.of("updateFirstNameOnly.json")))
                    ////////////////////////////////
                    .build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> resp = null;
        try {
            resp = client.send(req, HttpResponse.BodyHandlers.ofString()); //toString because Response body type set to String(2 lines above)

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actor a = getActor(resp.body());
        return a;
    }
}
