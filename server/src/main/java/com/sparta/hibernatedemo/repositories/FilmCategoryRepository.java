package com.sparta.hibernatedemo.repositories;

import com.sparta.hibernatedemo.entities.FilmCategory;
import com.sparta.hibernatedemo.entities.FilmCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryId> {
}