package com.example.franchiseapi.repository;

import java.util.List;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.example.franchiseapi.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends R2dbcRepository<Product, Long> {
    Flux<Product> findByBranchId(Long branchId); // Para obtener los productos de una sucursal
    // Definimos el método para eliminar un producto usando su ID y el ID de la sucursal
    Mono<Void> deleteByIdAndBranchId(Long productId, Long branchId);
    // Busca productos por la lista de sucursales
    Flux<Product> findByBranchIdIn(List<Long> branchIds);  // Busca productos por la lista de sucursales
}

