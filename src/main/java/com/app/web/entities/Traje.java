package com.app.web.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Traje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTraje;
    private String nombre;
    private int cantidadTotal;
    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
    private List<String> imagenes;
    @ManyToMany
    @JoinTable(name = "Unidad", joinColumns = @JoinColumn(name = "idTraje"), inverseJoinColumns = @JoinColumn(name = "idTalla"))
    private List<Talla> tallas;
}