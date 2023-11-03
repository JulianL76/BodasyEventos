package com.app.web.entities;

import java.sql.Date;
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
public class Alquiler {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlquiler;
    private Date fechaInicio;
    private Date fechaFin;
    private int diasAlquilado;
    private int abono;
    private int depositoSeguro;
    private int saldoPendiente;
    
    @ManyToOne
    @JoinColumn(name = "cedulaCliente") 
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "idUsuario") 
    private Usuario usuario;
    
    @ManyToMany
    @JoinTable(
        name = "alquiler_unidad",
        joinColumns = @JoinColumn(name = "idAlquiler"),
        inverseJoinColumns = {
    	        @JoinColumn(name = "idTraje"),
    	        @JoinColumn(name = "idTalla")
    	    }
    )
    private List<Unidad> unidades;
}
