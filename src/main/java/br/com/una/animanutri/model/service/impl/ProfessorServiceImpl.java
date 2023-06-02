package br.com.una.animanutri.model.service.impl;

import br.com.una.animanutri.model.entities.Professor;
import br.com.una.animanutri.model.repositories.ProfessorRepository;
import br.com.una.animanutri.model.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;
    @Override
    public Professor salvar(Professor professor) {
       return professorRepository.save(professor);
    }

    @Override
    public Professor altera(Long id, Professor professor) {
        professor.setId(id);
        return professorRepository.save(professor);
    }

    @Override
    public Optional<Professor> buscaPorId(Long id) {
        return professorRepository.findById(id);
    }

    @Override
    public List<Professor> todos() {
        return professorRepository.findAll();
    }

    @Override
    public void removePorId(Long id) {
        professorRepository.deleteById(id);
    }
}
