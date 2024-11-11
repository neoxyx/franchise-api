package com.example.franchiseapi.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("franchise")
public class Franchise {
    @Id
    private Long id;

    private String name;

    // Una franquicia puede tener varias sucursales (relación 1:N)
    private List<Branch> branches;

    // Si tienes productos asociados directamente con la franquicia, también podrías
    // incluir una lista de productos
    private List<Product> products; // Lista de productos asociados a la franquicia, si es necesario

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
