package com.sparta.hibernatedemo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.hibernatedemo.entities.Staff;
import com.sparta.hibernatedemo.entities.Store;
import com.sparta.hibernatedemo.repositories.StaffRepository;
import com.sparta.hibernatedemo.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
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
            responseJSON.put("managerStaff", Map.of("firstName", store.getManagerStaff().getFirstName(), "lastName", store.getManagerStaff().getLastName()));
            responseJSON.put("id", store.getId());
            return new ResponseEntity<>(responseJSON, HttpStatus.OK);
        }
        else return new ResponseEntity<>("Store with ID: " + id + " was not found", HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "sakila/store/update")
    public ResponseEntity<?> updateStore(@RequestBody String newStateString){
        ObjectMapper mapper = new ObjectMapper();
        Store newStore = null;
        try {
            newStore = mapper.readValue(newStateString, Store.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (newStore!= null) {
            if (storeRepository.existsById(newStore.getId())) {
                newStore.setLastUpdate(Instant.now());
                newStore.setManagerStaff(storeRepository.getById(newStore.getId()).getManagerStaff());
                storeRepository.save(newStore);
                return new ResponseEntity<>(newStore, HttpStatus.ACCEPTED);
            } else return new ResponseEntity<>("Store with ID: " + newStore.getId() + " not found!", HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>("Unable to construct valid entity from provided data", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "sakila/store/delete/{id_to_delete}")
    public ResponseEntity<String> deleteStore(@PathVariable int id_to_delete){
        if (storeRepository.existsById(id_to_delete)){
            List<Staff> staffFromStore = staffRepository.findByStore_id(id_to_delete);
            for (Staff member: staffFromStore){
                member.setStore(storeRepository.getById(20));
            }
            staffRepository.saveAll(staffFromStore);
            storeRepository.deleteById(id_to_delete);
            return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
        } else return new ResponseEntity<>("Store specified not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/sakila/store/create")
    public ResponseEntity<String> createNewStore(@RequestBody String newStoreString){
        ObjectMapper mapper = new ObjectMapper();
        Store newStore = null;
        try {
            newStore = mapper.readValue(newStoreString, Store.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(newStore != null){
            Staff newStaff = newStore.getManagerStaff();
            newStaff.setLastUpdate(Instant.now());
            newStore.setLastUpdate(Instant.now());
            staffRepository.save(newStaff);
            storeRepository.save(newStore);
            return new ResponseEntity<>("Store was added sucessfully", HttpStatus.CREATED);
        }else{
         return new ResponseEntity<>("Given JSON Could not be parsed into a new Store", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/sakila/store/getstoreandstaff/{id_of_store}")
    public ResponseEntity<?> getStoreAndStaff(@PathVariable int id_of_store){
        if (storeRepository.existsById(id_of_store)) {
            Store store = storeRepository.getById(id_of_store);
            List<Staff> allStaff = staffRepository.findByStore_id(id_of_store);
            Map<String, Object> responseBody = new HashMap<>(Map.of("store", store, "staff", allStaff));
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }
        else return new ResponseEntity<>("Store with ID: " + id_of_store + " was not found", HttpStatus.NOT_FOUND);
    }

}

