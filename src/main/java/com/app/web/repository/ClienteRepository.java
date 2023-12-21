package com.app.web.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.web.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    public Cliente findByCorreo(String correo);
    public Optional<Cliente> findById(Integer id);
}
