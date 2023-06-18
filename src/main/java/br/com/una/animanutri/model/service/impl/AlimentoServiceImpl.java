package br.com.una.animanutri.model.service.impl;

import br.com.una.animanutri.model.entities.Alimento;
import br.com.una.animanutri.model.repositories.AlimentoRepository;
import br.com.una.animanutri.model.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlimentoServiceImpl implements AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    @Override
    public Alimento salvar(Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

    @Override
    public Alimento altera(Long id, Alimento alimento) {
        alimento.setId(id);
        return alimentoRepository.save(alimento);
    }

    @Override
    public Optional<Alimento> buscaPorId(Long id) {
        return alimentoRepository.findById(id);
    }

    @Override
    public List<Alimento> todos() {
        return alimentoRepository.findAll();
    }

    @Override
    public void removePorId(Long id) {
        alimentoRepository.deleteById(id);
    }
}
