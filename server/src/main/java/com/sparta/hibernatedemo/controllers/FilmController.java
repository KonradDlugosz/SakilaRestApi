package com.sparta.hibernatedemo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.hibernatedemo.entities.*;
import com.sparta.hibernatedemo.repositories.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FilmTextRepository filmTextRepository;

    @Autowired
    private FilmActorRepository filmActorRepository;

    @Autowired
    private FilmCategoryRepository filmCategoryRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ObjectMapper mapper;

    @GetMapping(value = "/sakila/films")
    public List<Film> getFilms() {
        return filmRepository.findAll();
    }

    @GetMapping(value = "/sakila/films/{id}")
    public ResponseEntity<?> getFilmById(@PathVariable Integer id) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Film not found!");
        else
            return ResponseEntity.ok(film.get());
    }

    @PutMapping(value = "/sakila/films/update")
    public ResponseEntity<Film> updateFilm(@Valid @RequestBody Film film) {
        Optional<Film> results = filmRepository.findById(film.getId());
        if (results.isPresent()) {
            results.get().setTitle(film.getTitle());
            results.get().setDescription(film.getDescription());
            results.get().setReleaseYear(film.getReleaseYear());
            results.get().setLanguage(film.getLanguage());
            results.get().setRentalDuration(film.getRentalDuration());
            results.get().setRentalRate(film.getRentalRate());
            results.get().setLength(film.getLength());
            results.get().setReplacementCost(film.getReplacementCost());
            results.get().setRating(film.getRating());
            results.get().setSpecialFeatures(film.getSpecialFeatures());
            results.get().setLastUpdate(film.getLastUpdate());
            final Film updatedFilm = filmRepository.save(film);
            return ResponseEntity.ok(updatedFilm);
        } else
            return null;
    }

    @DeleteMapping(value = "sakila/films/delete/{id}")
    public ResponseEntity<String> deleteFilm(@PathVariable Integer id){

        List<FilmActor> filmActors = filmActorRepository.findAll().stream().filter( s -> s.getId().getFilmId() == id).toList();
        filmActorRepository.deleteAllInBatch(filmActors);

        List<FilmCategory> filmCategories = filmCategoryRepository.findAll().stream().filter( s -> s.getId().getFilmId() == id).toList();
        filmCategoryRepository.deleteAllInBatch(filmCategories);

        List<Inventory> inventories = inventoryRepository.findAll().stream().filter( s -> s.getFilm().getId() == id).toList();

        List<Rental> rentals = rentalRepository.findAll().stream().filter(s -> s.getInventory().getFilm().getId() == id).toList();
        rentalRepository.deleteAllInBatch(rentals);

        inventoryRepository.deleteAllInBatch(inventories);
        Optional<Film> result = filmRepository.findById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");

        if (result.isPresent()){
            filmRepository.deleteById(id);
            return new ResponseEntity<String>("{\"message\":\"Film Deleted\"}",headers, HttpStatus.OK);
        }
        return new ResponseEntity<String>("{\"message\":\"Film does not exist\"}", headers, HttpStatus.OK);
    }

    @PostMapping(value = "/sakila/films/add")
    public ResponseEntity<Film> addNewFilm(@RequestBody Film newFilm){
        filmRepository.save(newFilm);
        return ResponseEntity.ok(newFilm);
    }




    @GetMapping(value = "sakila/film_text")
    public ResponseEntity<?> getAllFilm(){
        return ResponseEntity.of(Optional.of(filmTextRepository.findAll()));
    }


    @GetMapping(value = "sakila/film_text/{filmId}")
    public ResponseEntity<?> filmDescription(@PathVariable Integer filmId) {
        try {
            Optional<FilmText> filmText = filmTextRepository.findById(filmId);
            return ResponseEntity.of(Optional.of(filmText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



    @PostMapping(value = "/sakila/film_text/add")
    public ResponseEntity<?> addFilm(@RequestBody FilmText filmText){
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        Optional<FilmText> filmExist = filmTextRepository.findById(filmText.getId());
        if (filmExist.isPresent()){
            return new ResponseEntity("{\"message\":\"Film Already Exist\"}",headers,HttpStatus.OK);
        }
        FilmText fT = new FilmText();
        fT.setId(filmText.getId());
        fT.setTitle(filmText.getTitle());
        fT.setDescription(filmText.getDescription());
        filmTextRepository.save(fT);
        String message = "{\"message\":\"Film Added\",\"film\":";
        String values = "   {\"id\":\""
                +fT.getId()+
                "\",\"title\":\""
                +fT.getTitle()+
                "\",\"description\":\""
                +fT.getDescription()+
                "\"}";
        String bodyMessage = message + values + "}";
        return new ResponseEntity<String>(bodyMessage,headers,HttpStatus.OK);
    }

    @PatchMapping(value = "/sakila/film_text/update")
    public ResponseEntity<?> updateFilmText(@RequestBody FilmText film){
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        Optional<FilmText> filmExist  = filmTextRepository.findById(film.getId());
        if (filmExist.isEmpty()){
            return new ResponseEntity<String>("{\"message\":\"Film Does Not Exist with ID "+film.getId()+"\"}", headers, HttpStatus.OK);
        }
        filmExist.get().setDescription(film.getDescription());
        filmExist.get().setTitle(film.getTitle());
        filmTextRepository.save(filmExist.get());
        String message = "{\"message\":\"Film Updated\",\"film\":";
        String values = "   {\"id\":\""
                +film.getId()+
                "\",\"title\":\""
                +film.getTitle()+
                "\",\"description\":\""
                +film.getDescription()+
                "\"}";
        String bodyMessage = message + values + "}";
        return new ResponseEntity<String>(bodyMessage,headers,HttpStatus.OK);
    }


    @DeleteMapping(value = "/sakila/film_text/delete/{id}")
    public ResponseEntity<?> deleteFilmText(@PathVariable Integer id){
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        Optional<FilmText> filmText = filmTextRepository.findById(id);
        if ( filmText.isPresent()){
            filmTextRepository.delete(filmTextRepository.getById(id));
            String message = "{\"message\":\"Film Deleted\",\"film\":";
            String values = "{\"id\":\""
                    +filmText.get().getId()+
                    "\",\"title\":\""
                    +filmText.get().getTitle()+
                    "\",\"description\":\""
                    +filmText.get().getDescription()+
                    "\"}";
            String bodyMessage = message + values + "}";
            return new ResponseEntity<String>(bodyMessage,headers,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("{\"message\":\"No film exist with id " +id + "\"}", headers, HttpStatus.OK);
        }
    }

    //    GetFilmsbyActorID
    @GetMapping(value = "/sakila/films/byActorId/{id}")
    public JSONObject getFilmsbyActorId(@PathVariable Integer id) {
        List<FilmActor> filmsbyActorId = filmActorRepository.findAll().stream()
                .filter(s -> s.getId().getActorId() == id).toList();

        JSONObject actor = new JSONObject();
        JSONObject films = new JSONObject();

        actor.put("Actor id", filmsbyActorId.get(0).getId().getActorId());
        actor.put("Actor Name", actorRepository.getById(filmsbyActorId.get(0).getId().getActorId()).getFirstName()
                + " " + actorRepository.getById(filmsbyActorId.get(0).getId().getActorId()).getLastName()); // outputs Actor Name.

        for (int i = 0; i < filmsbyActorId.size(); i++) {
            films.put("Film id " + filmsbyActorId.get(i).getId().getFilmId() + " Film Name ",
                    filmRepository.getById(filmsbyActorId.get(i).getId().getFilmId()).getTitle()); // outputs Film ID and Name but put properly.

//            films.put("Film id" + "#" + i, filmsbyActorId.get(i).getId().getFilmId()); //
//            films.put("Film Name" + "#" + i, filmRepository.getById(filmsbyActorId.get(i).getId().getFilmId()).getTitle());
        }
        actor.put("Films", films);
        return actor;
    }

}

