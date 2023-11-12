package com.app.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.entities.Unidad;
import com.app.web.entities.UnidadPK;
import com.app.web.repository.UnidadRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadService {
    @Autowired
    private UnidadRepository unidadRepository;

    public List<Unidad> getAllUnidades() {
        return unidadRepository.findAll();
    }


    public Unidad saveUnidad(Unidad unidad) {
        return unidadRepository.save(unidad);
    }


}