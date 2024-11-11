package com.example.franchiseapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table("branches")  // Asegúrate de que el nombre de la tabla en la base de datos sea correcto
@Data
public class Branch {

    @Id
    private Long id;
    private String name;
    private Long franchiseId;

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

    public void setFranchiseId(Long franchiseId) {  // Agregar el setter
        this.franchiseId = franchiseId;
    }
}
