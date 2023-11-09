package com.app.web.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;
    @ManyToMany
    @JoinTable(
        name = "rol_permiso",
        joinColumns = @JoinColumn(name = "idRol"),
        inverseJoinColumns = @JoinColumn(name = "idPermiso")
    )
    private Set<Permiso> permisos;
    
}
