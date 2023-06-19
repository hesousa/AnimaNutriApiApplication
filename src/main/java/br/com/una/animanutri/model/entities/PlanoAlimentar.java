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
@Table(name = "tb_plano_alimentar")
@Schema(name = "PlanoAlimentar")
public class PlanoAlimentar implements Serializable {

    @Schema(description = "Identificação única do Plano Alimentar.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Descrição do Plano Alimentar.",
            example = "O plano alimentar inclui uma dieta equilibrada com uma variedade de alimentos saudáveis.", required = true)
    @Column(length = 3000)
    private String descricao;

    // Outros campos e associações podem ser adicionados conforme necessário

}
