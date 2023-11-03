package com.app.web.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cliente {
    @Id
    private int cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String direccion;
    private String telefono;
    
    @OneToMany(mappedBy = "cliente")
    private List<Alquiler> alquileres;
}
