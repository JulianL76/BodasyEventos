package com.app.web.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    
    @ManyToMany(mappedBy = "permisos")
    private List<Rol> roles;
}
