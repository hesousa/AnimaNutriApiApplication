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
@Table(name = "tb_professor")
@Schema(name = "Professor")
public class Professor implements Serializable {

    @Schema(description = "Identificação unica do Professor.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nome referente ao Professor.",
            example = "Prof. Antônio Silva Jonimo", required = true)
    private String nome;
}
