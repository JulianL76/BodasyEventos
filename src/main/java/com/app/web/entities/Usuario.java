package com.app.web.entities;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Usuario {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String nombre;
   private String email;
   private String password;
   @ManyToOne
   @JoinColumn(name = "idRol")
   private Rol rol;
}
