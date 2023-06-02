package br.com.una.animanutri.model.service.impl;

import br.com.una.animanutri.model.entities.Aluno;
import br.com.una.animanutri.model.repositories.AlunoRepository;
import br.com.una.animanutri.model.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno altera(Long id, Aluno aluno) {
        aluno.setId(id);
        return alunoRepository.save(aluno);
    }

    @Override
    public Optional<Aluno> buscaPorId(Long id) {
        return alunoRepository.findById(id);
    }

    @Override
    public List<Aluno> todos() {
        return alunoRepository.findAll();
    }

    @Override
    public void removePorId(Long id) {
        alunoRepository.deleteById(id);
    }
}
