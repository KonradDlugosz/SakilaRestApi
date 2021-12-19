package com.sparta.hibernatedemo.repositories;

import com.sparta.hibernatedemo.entities.FilmText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmTextRepository extends JpaRepository<FilmText, Integer> {
}