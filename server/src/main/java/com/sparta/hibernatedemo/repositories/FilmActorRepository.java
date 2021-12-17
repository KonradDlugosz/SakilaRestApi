package com.sparta.hibernatedemo.repositories;

import com.sparta.hibernatedemo.entities.FilmActor;
import com.sparta.hibernatedemo.entities.FilmActorId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {
    //public List<FilmActor> findByActorId(int id);

   // public List<FilmActor> findByFilmId(int id);
}