package br.com.una.animanutri.model.service;

import br.com.una.animanutri.model.entities.PlanoAlimentar;

import java.util.List;
import java.util.Optional;

public interface PlanoAlimentarService {

    PlanoAlimentar salvar(PlanoAlimentar planoAlimentar);

    PlanoAlimentar alterar(Long id, PlanoAlimentar planoAlimentar);

    Optional<PlanoAlimentar> buscaPorId(Long id);

    List<PlanoAlimentar> buscaPorTodos();

    void removePorId(Long id);
}
