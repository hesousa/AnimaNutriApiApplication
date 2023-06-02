package br.com.una.animanutri.model.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "tb_aluno")
@Schema(name = "Aluno")
public class Aluno implements Serializable {

    @Schema(description = "Identificação unica do Aluno.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Associação da Classe Professor",
            example = "1", required = true, ref = "Professor")
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Schema(description = "Data e hora de nascimento do Aluno.",
            example = "Marcos Silva Jonimo", required = true)
    @Column(name = "data_nascimento")
    private LocalDateTime dataNascimento;

}
