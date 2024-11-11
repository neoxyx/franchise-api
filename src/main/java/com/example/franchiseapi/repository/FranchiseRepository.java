package com.example.franchiseapi.repository;

import com.example.franchiseapi.model.Franchise;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends ReactiveCrudRepository<Franchise, Long> {
}