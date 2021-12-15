package com.sparta.hibernatedemo.controllers;

import com.sparta.hibernatedemo.entities.Store;
import com.sparta.hibernatedemo.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StoreController {

    @Autowired
    private StoreRepository repository;

    @GetMapping(value = "/sakila/stores")
    public List<Store> getAllStores(){
        return repository.findAll();
    }

    @GetMapping(value = "sakila/store")
    public ResponseEntity<Store> getStoreByID(@RequestParam int id){
        if (repository.existsById(id)) return new ResponseEntity<>(repository.getById(id), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "sakila/store/update")
    public Store updateStore(@RequestBody Store newState){
        Optional<Store> oldState = repository.findById(newState.getId());
        if (oldState.isPresent()){
            repository.save(newState);
        }
        return newState;
    }

    @DeleteMapping(value = "sakila/store/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteStore(@RequestParam int id){
        repository.deleteById(id);
    }

    @PostMapping(value = "/sakila/store/create")
    public Store createNewStore(@RequestBody Store newStore){
        return repository.save(newStore);
    }
}

