package com.sparta.hibernatedemo.controllers;

import com.sparta.hibernatedemo.entities.Actor;
import com.sparta.hibernatedemo.entities.FilmActor;
import com.sparta.hibernatedemo.entities.FilmActorId;
import com.sparta.hibernatedemo.repositories.ActorRepository;
import com.sparta.hibernatedemo.repositories.FilmActorRepository;
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
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private FilmActorRepository filmActorRepository;

    @GetMapping(value = "/sakila/actors")
    public List<Actor> getActors(){
        return actorRepository.findAll();
    }

    @GetMapping(value = "/sakila/actors/{id}")
    public ResponseEntity<?> getActorById(@PathVariable Integer id){
        Optional<Actor> actor = actorRepository.findById(id);
        if(actor.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actor not found!");
        else
            return ResponseEntity.ok(actor.get());
    }

    @PostMapping(value = "/sakila/actors/add")
    public Actor createActor(@Valid @RequestBody Actor actor){
        return actorRepository.save(actor);
    }

    @DeleteMapping(value = "sakila/actors/{id}")
    public Map<String,Boolean> deleteActor(@PathVariable Integer id){
        Optional<Actor> actor = actorRepository.findById(id);
        if(actor.isPresent())
            actorRepository.delete(actor.get());
        else
            return null;

        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping(value = "sakila/actors/update")
    public ResponseEntity<Actor> updateActor(@Valid @RequestBody Actor actor){
        Optional<Actor> results = actorRepository.findById(actor.getId());
        if(results.isPresent()){
            results.get().setFirstName(actor.getFirstName());
            results.get().setLastName(actor.getLastName());
            results.get().setLastUpdate(actor.getLastUpdate());
            final Actor updatedActor = actorRepository.save(actor);
            return ResponseEntity.ok(updatedActor);
        } else
            return null;
    }

    @PatchMapping(value = "sakila/actors/update")
    public ResponseEntity<Actor> updateActorName(@RequestBody Actor newActor){
        Optional<Actor> actor = actorRepository.findById(newActor.getId());
        if(actor.isPresent()){
            actor.get().setFirstName(newActor.getFirstName());
            final Actor updatedActor = actorRepository.save(actor.get());
            return ResponseEntity.ok(updatedActor);
        } else
            return null;
    }

//    Pulls FilmActor Composite key in JSON

    @GetMapping(value = "/sakila/ckey")
    public List<FilmActor> getCkey(){
        return filmActorRepository.findAll();
    }
}
