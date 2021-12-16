package com.sparta.hibernatedemo.repositories;

import com.sparta.hibernatedemo.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}