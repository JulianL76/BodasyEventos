package com.app.web.entities;

import java.io.Serializable;

import jakarta.persistence.*;
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