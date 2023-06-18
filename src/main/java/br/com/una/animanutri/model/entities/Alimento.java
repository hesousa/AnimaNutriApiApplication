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
@Table(name = "tb_alimento")
@Schema(name = "Alimento")
public class Alimento implements Serializable {

    @Schema(description = "Identificação única do Alimento.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nome do Alimento.",
            example = "Maçã", required = true)
    @Column(name = "nome")
    private String nome;

    @Schema(description = "Porção do Alimento.",
            example = "100g", required = true)
    @Column(name = "porcao")
    private String porcao;

    @Schema(description = "Calorias do Alimento.",
            example = "52", required = true)
    @Column(name = "calorias")
    private Integer calorias;

    @Schema(description = "Medida caseira do Alimento.",
            example = "1 unidade", required = true)
    @Column(name = "medida_caseira")
    private String medidaCaseira;

    @Schema(description = "Unidade do Alimento.",
            example = "g", required = true)
    @Column(name = "unidade")
    private String unidade;

}
