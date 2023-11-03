package com.app.web.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Unidad {
	@EmbeddedId
    private UnidadPK unidadPK;
    
	private int cantidad;
	private int precioAlquiler;
	@ManyToMany(mappedBy = "unidades")
	private List<Alquiler> alquileres;

}
