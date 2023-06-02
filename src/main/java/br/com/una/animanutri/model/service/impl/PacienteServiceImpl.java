package br.com.una.animanutri.model.service.impl;

import br.com.una.animanutri.model.entities.Paciente;
import br.com.una.animanutri.model.repositories.PacienteRepository;
import br.com.una.animanutri.model.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Paciente salvar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente altera(Long id, Paciente paciente) {
        paciente.setId(id);
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscaPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> todos() {
        return pacienteRepository.findAll();
    }

    @Override
    public void removePorId(Long id) {
        pacienteRepository.deleteById(id);
    }
}
