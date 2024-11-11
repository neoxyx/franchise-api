package com.example.franchiseapi.controller;

import com.example.franchiseapi.model.Branch;
import com.example.franchiseapi.model.Franchise;
import com.example.franchiseapi.model.Product;
import com.example.franchiseapi.service.FranchiseService;

import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    // Crear franquicia
    @PostMapping
    public Mono<Franchise> createFranchise(@RequestBody String name) {
        Franchise franchise = new Franchise();
        franchise.setName(name); // Establece el nombre de la franquicia desde el cuerpo de la solicitud
        return franchiseService.createFranchise(franchise); // Pasa el objeto Franchise al servicio
    }

    // Añadir sucursal a la franquicia
    @PostMapping("/{franchiseId}/branches")
    public Mono<Branch> addBranchToFranchise(@PathVariable Long franchiseId, @RequestBody Branch branch) {
        branch.setFranchiseId(franchiseId); // Establece la franquicia asociada a la sucursal
        return franchiseService.addBranchToFranchise(franchiseId, branch); // Cambié String por Branch
    }

    // Añadir producto a la sucursal
    @PostMapping("/branches/{branchId}/products")
    public Mono<Product> addProductToBranch(@PathVariable Long branchId, @RequestBody Product product) {
        product.setBranchId(branchId); // Establece la sucursal asociada al producto
        return franchiseService.addProductToBranch(branchId, product); // Cambié Object por Product
    }

    // Eliminar producto de una sucursal
    @DeleteMapping("/branches/{branchId}/products/{productId}")
    public Mono<Void> deleteProductFromBranch(@PathVariable Long branchId, @PathVariable Long productId) {
        return franchiseService.deleteProductFromBranch(branchId, productId); // Implementa este método en el servicio
    }

    // Actualizar stock del producto
    @PutMapping("/products/{productId}")
    public Mono<Product> updateProductStock(@PathVariable Long productId, @RequestBody Product product) {
        return franchiseService.updateProductStock(productId, product.getStock()); // Implementa este método en el servicio
    }

    // Obtener los productos con mayor stock para una franquicia
    @GetMapping("/{franchiseId}/products/highest-stock")
    public Flux<Product> getHighestStockProduct(@PathVariable Long franchiseId) {
        return franchiseService.getTopStockedProductsForFranchise(franchiseId); // Implementa este método en el servicio
    }
}
