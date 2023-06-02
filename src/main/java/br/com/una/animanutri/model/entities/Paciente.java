package br.com.una.animanutri.model.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "tb_paciente")
@Schema(name = "Paciente")
public class Paciente implements Serializable {

    @Schema(description = "Identificação unica do Paciente.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nome referente ao Paciente.",
            example = "Marcos Silva Jonimo", required = true)
    private String nome;

    @Schema(description = "Associação da Classe Aluno",
            example = "1", required = true, ref = "Aluno")
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

}
