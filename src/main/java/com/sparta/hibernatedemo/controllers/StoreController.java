package com.sparta.hibernatedemo.controllers;

import com.sparta.hibernatedemo.entities.Staff;
import com.sparta.hibernatedemo.entities.Store;
import com.sparta.hibernatedemo.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;
    // private StaffRepository staffRepository;
    
    @GetMapping(value = "/sakila/stores")
    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }

    @GetMapping(value = "sakila/store/getbyid/{id}")
    public ResponseEntity<?> getStoreByID(@PathVariable int id){
        if (storeRepository.existsById(id)) {return new ResponseEntity<>(storeRepository.getById(id), HttpStatus.OK);}
        else return new ResponseEntity<>("Store with ID: " + id + " was not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "sakila/store/update")
    public ResponseEntity<?> updateStore(@RequestBody Store newState){
        if (storeRepository.existsById(newState.getId())){
            storeRepository.save(newState);
            return new ResponseEntity<>(newState, HttpStatus.ACCEPTED);
        }
        else return new ResponseEntity<>(newState, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "sakila/store/deletebyid/{id_to_delete}")
    public ResponseEntity<String> deleteStore(@PathVariable int id_to_delete){
        if (storeRepository.existsById(id_to_delete)){
            storeRepository.deleteById(id_to_delete);
            return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
        } else return new ResponseEntity<>("Store specified not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/sakila/store/create")
    public Store createNewStore(@RequestBody Store newStore){
        return storeRepository.save(newStore);
    }

    @GetMapping(value = "/sakila/store/getstoreandstaff/{id_of_store}")
    public ResponseEntity<?> getStoreAndStaff(@PathVariable int id_of_store){
        if (storeRepository.existsById(id_of_store)) {
            Store store = storeRepository.getById(id_of_store);
            // List<Staff> allStaff = staffRepository.findAllByID(id_of_store);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else return new ResponseEntity<>("Store with ID: " + id_of_store + " was not found", HttpStatus.NOT_FOUND);
    }

}

