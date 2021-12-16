package com.sparta.hibernatedemo.repositories;

import com.sparta.hibernatedemo.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}