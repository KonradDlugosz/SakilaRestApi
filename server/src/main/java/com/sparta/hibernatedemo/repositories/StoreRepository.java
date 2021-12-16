package com.sparta.hibernatedemo.repositories;

import com.sparta.hibernatedemo.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}