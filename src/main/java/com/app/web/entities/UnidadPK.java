package com.app.web.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class UnidadPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "idTraje")
    private Traje traje;

    @ManyToOne
    @JoinColumn(name = "idTalla")
    private Talla talla;
}