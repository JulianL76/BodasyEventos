package com.app.web.services;

import java.util.List;
import java.util.Optional;

import com.app.web.entities.Cliente;

public interface ClienteService {
    List<Cliente> getAllClientes();
    Optional<Cliente> getClienteById(Integer cedula);
    Cliente saveCliente(Cliente cliente);
    void deleteCliente(Integer cedula);
}