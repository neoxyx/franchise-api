package com.example.franchiseapi.service;

import com.example.franchiseapi.model.Branch;
import com.example.franchiseapi.model.Franchise;
import com.example.franchiseapi.model.Product;
import com.example.franchiseapi.repository.BranchRepository;
import com.example.franchiseapi.repository.FranchiseRepository;
import com.example.franchiseapi.repository.ProductRepository;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public FranchiseService(FranchiseRepository franchiseRepository, BranchRepository branchRepository,
            ProductRepository productRepository) {
        this.franchiseRepository = franchiseRepository;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
    }

    // Crear franquicia
    public Mono<Franchise> createFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    // Añadir sucursal a la franquicia
    public Mono<Branch> addBranchToFranchise(Long franchiseId, Branch branch) {
        branch.setFranchiseId(franchiseId);  // Establece la franquicia asociada a la sucursal
        return branchRepository.save(branch);  // Guarda la sucursal
    }

    // Añadir producto a la sucursal
    public Mono<Product> addProductToBranch(Long branchId, Product product) {
        product.setBranchId(branchId);  // Establece la sucursal asociada al producto
        return productRepository.save(product);  // Guarda el producto
    }


    // Eliminar producto de una sucursal
    public Mono<Void> deleteProductFromBranch(Long branchId, Long productId) {
        return productRepository.deleteByIdAndBranchId(productId, branchId);
    }

    // Actualizar stock del producto
    public Mono<Product> updateProductStock(Long productId, int stock) {
        return productRepository.findById(productId)
                .flatMap(product -> {
                    product.setStock(stock); // Actualiza el stock del producto
                    return productRepository.save(product);
                });
    }

    // Obtener los productos con mayor stock para una franquicia
    public Flux<Product> getTopStockedProductsForFranchise(Long franchiseId) {
        // Buscar las sucursales asociadas a la franquicia
        return branchRepository.findByFranchiseId(franchiseId)
            .flatMap(branch -> 
                // Buscar productos en esas sucursales
                productRepository.findByBranchIdIn(Arrays.asList(branch.getId()))
            );
    }
}
