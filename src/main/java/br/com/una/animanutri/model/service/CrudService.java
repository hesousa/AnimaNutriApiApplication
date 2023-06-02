package br.com.una.animanutri.model.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID extends Serializable> {
    T salvar(T t);
    T altera(ID id, T t);
    Optional<T> buscaPorId(ID id);
    List<T> todos();
    void removePorId(ID id);
}
