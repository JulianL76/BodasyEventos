package com.app.web.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
@Entity
@Data
public class Talla {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTalla;
    private String nombre;
    @ManyToMany(mappedBy = "tallas")
    private List<Traje> trajes;
}
