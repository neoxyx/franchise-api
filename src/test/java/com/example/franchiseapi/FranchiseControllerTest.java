package com.example.franchiseapi;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.franchiseapi.controller.FranchiseController;
import com.example.franchiseapi.model.Branch;
import com.example.franchiseapi.model.Franchise;
import com.example.franchiseapi.model.Product;
import com.example.franchiseapi.service.FranchiseService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class FranchiseControllerTest {
    @Mock
    private FranchiseService franchiseService;

    @InjectMocks
    private FranchiseController franchiseController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para crear una franquicia
    @Test
    void testCreateFranchise() {
        // Arrange
        String franchiseName = "Test Franchise";
        Franchise franchise = new Franchise();
        franchise.setName(franchiseName);

        when(franchiseService.createFranchise(any(Franchise.class)))
                .thenReturn(Mono.just(franchise)); // Simulamos el retorno del servicio

        // Act
        Mono<Franchise> result = franchiseController.createFranchise(franchiseName);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(f -> f.getName().equals(franchiseName))
                .verifyComplete();

        verify(franchiseService, times(1)).createFranchise(any(Franchise.class)); // Verifica que se llame al servicio
    }

    // Test para añadir una sucursal a la franquicia
    @Test
    void testAddBranchToFranchise() {
        // Arrange
        Long franchiseId = 1L;
        Branch branch = new Branch();
        branch.setFranchiseId(franchiseId);

        when(franchiseService.addBranchToFranchise(eq(franchiseId), any(Branch.class)))
                .thenReturn(Mono.just(branch));

        // Act
        Mono<Branch> result = franchiseController.addBranchToFranchise(franchiseId, branch);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(b -> b.getFranchiseId().equals(franchiseId))
                .verifyComplete();

        verify(franchiseService, times(1)).addBranchToFranchise(eq(franchiseId), any(Branch.class));
    }

    // Test para añadir producto a la sucursal
    @Test
    void testAddProductToBranch() {
        // Arrange
        Long branchId = 1L;
        Product product = new Product();
        product.setId(1L); // Asigna un id al producto
        product.setName("Product Name");
        product.setStock(10);
        product.setBranchId(branchId);

        when(franchiseService.addProductToBranch(eq(branchId), any(Product.class)))
                .thenReturn(Mono.just(product));

        // Act
        Mono<Product> result = franchiseController.addProductToBranch(branchId, product);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(p -> p.getId().equals(branchId))
                .verifyComplete();

        verify(franchiseService, times(1)).addProductToBranch(eq(branchId), any(Product.class));
    }

    // Test para eliminar producto de la sucursal
    @Test
    void testDeleteProductFromBranch() {
        // Arrange
        Long branchId = 1L;
        Long productId = 1L;

        when(franchiseService.deleteProductFromBranch(eq(branchId), eq(productId)))
                .thenReturn(Mono.empty());

        // Act
        Mono<Void> result = franchiseController.deleteProductFromBranch(branchId, productId);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(franchiseService, times(1)).deleteProductFromBranch(eq(branchId), eq(productId));
    }

    // Test para actualizar stock de producto
    @Test
    void testUpdateProductStock() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setStock(10);

        when(franchiseService.updateProductStock(eq(productId), eq(10)))
                .thenReturn(Mono.just(product));

        // Act
        Mono<Product> result = franchiseController.updateProductStock(productId, product);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(p -> p.getStock() == 10)
                .verifyComplete();

        verify(franchiseService, times(1)).updateProductStock(eq(productId), eq(10));
    }

    // Test para obtener productos con mayor stock
    @Test
    void testGetHighestStockProduct() {
        // Arrange
        Long franchiseId = 1L;
        Product product = new Product();
        product.setStock(100);

        when(franchiseService.getTopStockedProductsForFranchise(eq(franchiseId)))
                .thenReturn(Flux.just(product));

        // Act
        Flux<Product> result = franchiseController.getHighestStockProduct(franchiseId);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(p -> p.getStock() == 100)
                .verifyComplete();

        verify(franchiseService, times(1)).getTopStockedProductsForFranchise(eq(franchiseId));
    }
}
