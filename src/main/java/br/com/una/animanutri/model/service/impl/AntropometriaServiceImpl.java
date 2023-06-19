package br.com.una.animanutri.model.service.impl;

import br.com.una.animanutri.model.entities.Antropometria;
import br.com.una.animanutri.model.repositories.AntropometriaRepository;
import br.com.una.animanutri.model.service.AntropometriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AntropometriaServiceImpl implements AntropometriaService {

    @Autowired
    private AntropometriaRepository antropometriaRepository;

    @Override
    public Antropometria salvar(Antropometria antropometria) {
        return antropometriaRepository.save(antropometria);
    }

    @Override
    public Antropometria alterar(String data, Antropometria antropometria) {
        antropometria.setData(data);
        return antropometriaRepository.save(antropometria);
    }

    @Override
    public Optional<Antropometria> buscaPorData(String data) {
        return antropometriaRepository.findById(data);
    }

    @Override
    public List<Antropometria> buscaPorTodas() {
        return antropometriaRepository.findAll();
    }

    @Override
    public void removePorData(String data) {
        antropometriaRepository.deleteById(data);
    }
}
