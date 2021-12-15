package com.sparta.hibernatedemo.controllers;


import com.sparta.hibernatedemo.entities.Staff;
import com.sparta.hibernatedemo.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/sakila")
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping(value = "/staff/all")
    public List<Staff> getAllStaff(){
        return staffRepository.findAll();
    }

    @GetMapping(value = "/staff/one")
    public ResponseEntity<?> getStaffById(@RequestParam Integer id){
        Optional<Staff> staff = staffRepository.findById(id);
        if(staff.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Staff not found!");
        else
            return ResponseEntity.ok(staff.get());
    }

    @DeleteMapping(value = "/staff/delete")
    public Map<String, Boolean> removeStaff(@RequestParam Integer id){
        Optional<Staff> staff = staffRepository.findById(id);
        Map<String,Boolean> response = new HashMap<>();

        if(staff.isPresent()){
            staffRepository.delete(staff.get());
            response.put("Deleted", Boolean.TRUE);
        } else {
            response.put("Deleted", Boolean.FALSE);
        }
        return response;
    }

    @PostMapping(value = "/staff/create")
    public Staff createStaff(@RequestBody Staff staff){
        return staffRepository.save(staff);
    }

    @PutMapping(value = "/staff/update")
    public ResponseEntity<Staff> updateStaff(@RequestBody Staff staff){
        Optional<Staff> oldState = staffRepository.findById(staff.getId());
        if(oldState.isPresent()){
            staffRepository.save(staff);
        }
        return ResponseEntity.ok(staff);
    }

}