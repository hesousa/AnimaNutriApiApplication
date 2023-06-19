package br.com.una.animanutri.model.service;

import br.com.una.animanutri.model.entities.Antropometria;

import java.util.List;
import java.util.Optional;

public interface AntropometriaService {

    Antropometria salvar(Antropometria antropometria);

    Antropometria alterar(String data, Antropometria antropometria);

    Optional<Antropometria> buscaPorData(String data);

    List<Antropometria> buscaPorTodas();

    void removePorData(String data);
}
