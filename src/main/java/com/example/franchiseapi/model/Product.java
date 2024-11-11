package com.example.franchiseapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("products")  // Asegúrate de que el nombre de la tabla en la base de datos sea correcto
public class Product {

    @Id
    private Long id;
    private String name;
    private int stock;

    @Column("branch_id")
    private Long branchId;  // ID de la sucursal a la que pertenece este producto

    // Métodos getter y setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
    
}
