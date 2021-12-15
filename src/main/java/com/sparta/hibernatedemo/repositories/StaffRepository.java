package com.sparta.hibernatedemo.repositories;

import com.sparta.hibernatedemo.entities.Staff;
import com.sparta.hibernatedemo.entities.Store;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    List<Staff> store_id(Integer store_id);
}