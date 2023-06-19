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
@Table(name = "tb_antropometria")
@Schema(name = "Antropometria")
public class Antropometria implements Serializable {

    @Schema(description = "Data da medição.",
            example = "2023-06-21", required = true)
    @Id
    private String data;

    @Schema(description = "Peso do indivíduo.",
            example = "75.5", required = true)
    private String peso;

    @Schema(description = "Massa gorda do indivíduo.",
            example = "20.3", required = true)
    @Column(name = "massa_gorda")
    private String massaGorda;

}
