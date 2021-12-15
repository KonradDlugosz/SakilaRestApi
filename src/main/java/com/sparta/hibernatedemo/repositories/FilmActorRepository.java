package com.sparta.hibernatedemo.repositories;

import com.sparta.hibernatedemo.entities.FilmActor;
import com.sparta.hibernatedemo.entities.FilmActorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {
}