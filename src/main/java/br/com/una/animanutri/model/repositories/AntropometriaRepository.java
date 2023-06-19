package br.com.una.animanutri.model.repositories;

import br.com.una.animanutri.model.entities.Antropometria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntropometriaRepository extends JpaRepository<Antropometria, String> {
}
