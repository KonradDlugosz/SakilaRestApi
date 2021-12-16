package com.sparta.hibernatedemo.repositories;

import com.sparta.hibernatedemo.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}