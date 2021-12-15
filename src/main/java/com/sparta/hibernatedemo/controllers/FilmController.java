package com.sparta.hibernatedemo.controllers;

import com.sparta.hibernatedemo.entities.Actor;
import com.sparta.hibernatedemo.entities.Film;
import com.sparta.hibernatedemo.entities.FilmText;
import com.sparta.hibernatedemo.repositories.FilmRepository;
import com.sparta.hibernatedemo.repositories.FilmTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Map<String, Boolean> deleteFilm(@PathVariable Integer id){
        Optional<Film> film = filmRepository.findById(id);
        if(film.isPresent())
            filmRepository.delete(film.get());
        else
            return null;
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
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
        Optional<FilmText> filmExist = filmTextRepository.findById(filmText.getId());
        if (filmExist.isPresent()){
            return ResponseEntity.status(HttpStatus.IM_USED).body("Already Exist \n");
        }
        FilmText fT = new FilmText();
        fT.setId(filmText.getId());
        fT.setTitle(filmText.getTitle());
        fT.setDescription(filmText.getDescription());
        filmTextRepository.save(fT);
        return ResponseEntity.of(Optional.of(fT));
    }

    @PatchMapping(value = "/sakila/film_text/update")
    public ResponseEntity<?> updateFilmText(@RequestBody FilmText film){
        Optional<FilmText> filmExist  = filmTextRepository.findById(film.getId());
        if (filmExist.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(film);
        }
        filmExist.get().setDescription(film.getDescription());
        filmExist.get().setTitle(film.getTitle());
        final FilmText fT = filmTextRepository.save(filmExist.get());
        return ResponseEntity.ok(fT);
    }


    @DeleteMapping(value = "/sakila/film_text/delete/{id}")
    public ResponseEntity<FilmText> deleteFilmText(@PathVariable Integer id){
        Optional<FilmText> filmText = filmTextRepository.findById(id);
        if ( filmText.isPresent()){
            filmTextRepository.delete(filmTextRepository.getById(id));
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }
}

