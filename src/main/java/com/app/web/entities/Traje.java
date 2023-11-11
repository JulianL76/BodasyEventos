package com.app.web.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Traje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTraje;
    private String nombre;
    private Integer cantidadTotal;
    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
    private List<String> imagenes;
    @ManyToMany
    @JoinTable(
        name = "Unidad",
        joinColumns = @JoinColumn(name = "idTraje"),
        inverseJoinColumns = @JoinColumn(name = "idTalla")
    )
    private List<Talla> tallas;
}
