package colaboradores.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "producao")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Producao {

    @Column(name = "quantidadeProduzida")
    private int quantidadeProduzida;

    @Column(name = "valor")
    private BigDecimal valorUnidade;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "idMatricula")
    private String matriculaColaborador;

}
