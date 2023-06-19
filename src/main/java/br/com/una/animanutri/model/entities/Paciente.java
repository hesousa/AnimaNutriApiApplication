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

    @Schema(description = "Email do Paciente.",
            example = "marcos.silva@exemplo.com", required = true)
    private String email;

    @Schema(description = "Idade do Paciente.",
            example = "30", required = true)
    private Integer idade;

    @Schema(description = "Sexo do Paciente.",
            example = "Masculino", required = true)
    private String sexo;

    @Schema(description = "Telefone do Paciente.",
            example = "(11) 98765-4321", required = true)
    private String telefone;

    @Schema(description = "Anamnese do Paciente.",
            example = "Paciente apresenta sintomas de...", required = false)
    @Column(length = 2000) // Este campo pode ser longo, então é definido um tamanho maior
    private String anamnese;

    @Schema(description = "Associação da Classe Aluno",
            example = "1", required = true, ref = "Aluno")
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

}
