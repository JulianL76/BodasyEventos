package com.app.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.entities.Traje;
import com.app.web.repository.TrajeRepository;

@Service
public class TrajeService {

    @Autowired
    private TrajeRepository trajeRepository;

    public List<Traje> obtenerTodosTrajes() {
        return trajeRepository.findAll();
    }

    public Traje obtenerTrajePorId(Integer id) {
        return trajeRepository.findById(id).orElse(null);
    }

    public void guardarTraje(Traje traje) {
        trajeRepository.save(traje);
    }

    public void actualizarTraje(Integer id, Traje traje) {
        if (trajeRepository.existsById(id)) {
            traje.setIdTraje(id);
            trajeRepository.save(traje);
        }
    }

    public void borrarTraje(Integer id) {
        trajeRepository.deleteById(id);
    }
}