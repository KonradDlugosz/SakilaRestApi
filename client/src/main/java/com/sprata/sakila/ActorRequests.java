package com.sprata.sakila;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprata.sakila.entity.Actor;
import com.sprata.sakila.entity.Staff;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ActorRequests {

    public static Actor getOneActors(Integer id) throws IOException {
        //ObjectMapper mapper = new ObjectMapper();
        //Actor actor = mapper.readValue(new URL("http://localhost:8080/sakila/actors/{id}"), Actor.class);

        HttpRequest req = HttpRequest.newBuilder()  //creating a HttpBuilder, returning a new request builder
                .uri(URI.create("http://localhost:8080/sakila/actors/"+id))  //setting URI here
                //throws IllegealArguementException if URI scheme not supported
                .GET()  //ssets the request method of this builder to get. returns the builder and what is
                .build();
        HttpClient client = HttpClient.newHttpClient();
        //used to send requests and retrieve responses
        //creates the instances o the default HttpClient implementation
        //immutable + can send multiple requests
        //provides config info + resource sharing for all requests
        HttpResponse<String> resp = null;
        //HttpResponce<T> is an interface
        //not created directly but returned as a result for sending an HttpRequest
        //made when response status code and headers have been received , typically after the response body has also been received
        // This response will retrieve a response as a string
        try{
            resp = client.send(req, HttpResponse.BodyHandlers.ofString()); //toString because Response body type set to String(2 lines above)
            //send(HttpRequest, BodyHandler)
            //BodyHandler - needed to send each HttpRequest
            //determines how to handle the response body
        }catch(IOException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        String json = resp.body();  //returns the body of resp which is a string defined earlier
        //???how do we know that resp is in json mode???
        ObjectMapper mapper = new ObjectMapper();
        //provides functionality for reading and writing JSON
        Actor actor = mapper.readValue(json,Actor.class);
                                        //???json parser but how so we know it is compatible to pass through as json???
    }

    /*
    public static ___ getAllActors(){
    }

    public static ___ createActor(){
    }

    public static ___ deleteOneActor(){
    }

    public static __ putActor(){
    }

    public static patchActor(){
    }
     */


   
}
