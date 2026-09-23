package colaboradores.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "producao")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producao {

    @Column(name = "quantidadeProduzida")
    private int quantidadeProduzida;

    @Column(name = "valor")
    private BigDecimal valorUnidade;

    @Column(name = "total")
    private BigDecimal total;
}
