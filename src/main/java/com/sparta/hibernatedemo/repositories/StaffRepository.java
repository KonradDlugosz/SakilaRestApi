package com.sparta.hibernatedemo.repositories;

import com.sparta.hibernatedemo.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
}