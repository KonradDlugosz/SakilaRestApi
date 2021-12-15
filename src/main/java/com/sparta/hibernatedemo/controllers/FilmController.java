package com.sparta.hibernatedemo.controllers;

import com.sparta.hibernatedemo.entities.Film;
import com.sparta.hibernatedemo.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping(value = "/sakila/films")
    public List<Film> getFilms(){
        return filmRepository.findAll();
    }
}
