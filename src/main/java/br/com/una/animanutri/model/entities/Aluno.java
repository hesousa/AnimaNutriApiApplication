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
    @Schema(description = "Identificação única do Aluno.", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nome do Aluno", required = true)
    private String nome;

    @Schema(description = "Idade do Aluno", required = true)
    private int idade;

    @Schema(description = "Número de celular do Aluno", required = true)
    private String celular;

    @Schema(description = "Email do Aluno", required = true)
    private String email;

    @Schema(description = "Sexo do Aluno", required = true)
    private String sexo;

    @Schema(description = "Data de Nascimento do Aluno")
    private LocalDateTime dataNascimento;
}
