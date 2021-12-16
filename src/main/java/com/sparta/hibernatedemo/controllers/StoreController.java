package com.sparta.hibernatedemo.controllers;

import com.sparta.hibernatedemo.entities.Staff;
import com.sparta.hibernatedemo.entities.Store;
import com.sparta.hibernatedemo.repositories.StaffRepository;
import com.sparta.hibernatedemo.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StaffRepository staffRepository;
    
    @GetMapping(value = "/sakila/stores")
    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }

    @GetMapping(value = "sakila/store/getbyid/{id}")
    public ResponseEntity<?> getStoreByID(@PathVariable int id){
        if (storeRepository.existsById(id)) {
            Store store = storeRepository.getById(id);
            Map<String, Object> responseJSON = new HashMap<>();
            responseJSON.put("lastUpdate", store.getLastUpdate());
            responseJSON.put("address", store.getAddress());
            responseJSON.put("managerInfo", Map.of("firstName", store.getManagerStaff().getFirstName(), "lastName", store.getManagerStaff().getLastName()));
            responseJSON.put("id", store.getId());
            return new ResponseEntity<>(responseJSON, HttpStatus.OK);
        }
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
            Store store = storeRepository.getById(id_to_delete);
            storeRepository.deleteById(id_to_delete);
            staffRepository.deleteById(store.getManagerStaff().getId());
            return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
        } else return new ResponseEntity<>("Store specified not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/sakila/store/create")
    public ResponseEntity<?> createNewStore(@RequestBody Store newStore){
        storeRepository.save(newStore);
        return new ResponseEntity<>("Store was added sucessfully", HttpStatus.CREATED);
    }

    @GetMapping(value = "/sakila/store/getstoreandstaff/{id_of_store}")
    public ResponseEntity<?> getStoreAndStaff(@PathVariable int id_of_store){
        if (storeRepository.existsById(id_of_store)) {
            Store store = storeRepository.getById(id_of_store);
            List<Staff> allStaff = staffRepository.store_id(id_of_store);
            List<?> responseBody = List.of(store, allStaff);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }
        else return new ResponseEntity<>("Store with ID: " + id_of_store + " was not found", HttpStatus.NOT_FOUND);
    }

}

