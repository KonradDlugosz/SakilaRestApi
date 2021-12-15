package com.sparta.hibernatedemo.controllers;

import com.sparta.hibernatedemo.entities.Film;

import com.sparta.hibernatedemo.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.sparta.hibernatedemo.entities.FilmText;
import com.sparta.hibernatedemo.repositories.FilmRepository;
import com.sparta.hibernatedemo.repositories.FilmTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;
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

    @PutMapping(value = "/sakila/films/update/{id}")
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

    @DeleteMapping(value = "/sakila/films/delete/{id}")
    public Map<String, Boolean> deleteFilm(@PathVariable Integer id) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isPresent())
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


    @PutMapping(value = "/sakila/fim_text/add")
    public ResponseEntity<?> addFilm() {
        return null;
    }


}

