package com.sparta.hibernatedemo.repositories;

import com.sparta.hibernatedemo.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    List<Staff> store_id(Integer store_id);
}