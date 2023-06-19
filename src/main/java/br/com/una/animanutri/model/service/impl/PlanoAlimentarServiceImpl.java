package br.com.una.animanutri.model.service.impl;

import br.com.una.animanutri.model.entities.PlanoAlimentar;
import br.com.una.animanutri.model.repositories.PlanoAlimentarRepository;
import br.com.una.animanutri.model.service.PlanoAlimentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoAlimentarServiceImpl implements PlanoAlimentarService {

    @Autowired
    private PlanoAlimentarRepository planoAlimentarRepository;

    @Override
    public PlanoAlimentar salvar(PlanoAlimentar planoAlimentar) {
        return planoAlimentarRepository.save(planoAlimentar);
    }

    @Override
    public PlanoAlimentar alterar(Long id, PlanoAlimentar planoAlimentar) {
        planoAlimentar.setId(id);
        return planoAlimentarRepository.save(planoAlimentar);
    }

    @Override
    public Optional<PlanoAlimentar> buscaPorId(Long id) {
        return planoAlimentarRepository.findById(id);
    }

    @Override
    public List<PlanoAlimentar> buscaPorTodos() {
        return planoAlimentarRepository.findAll();
    }

    @Override
    public void removePorId(Long id) {
        planoAlimentarRepository.deleteById(id);
    }
}
