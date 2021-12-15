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

    @GetMapping(value = "sakila/store/{id}")
    public ResponseEntity<Store> getStoreByID(@PathVariable int id){
        if (repository.existsById(id)) {return new ResponseEntity<>(repository.getById(id), HttpStatus.OK);}
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "sakila/store/update")
    public ResponseEntity<Store> updateStore(@RequestBody Store newState){
        if (repository.existsById(newState.getId())){
            repository.save(newState);
            return new ResponseEntity<>(newState, HttpStatus.ACCEPTED);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "sakila/store/{id_to_delete}")
    public ResponseEntity<String> deleteStore(@PathVariable int id_to_delete){
        if (repository.existsById(id_to_delete)){
            repository.deleteById(id_to_delete);
            return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
        } else return new ResponseEntity<>("Store specified not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/sakila/store/create")
    public Store createNewStore(@RequestBody Store newStore){
        return repository.save(newStore);
    }
}

