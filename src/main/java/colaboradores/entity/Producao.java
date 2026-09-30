package colaboradores.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Registro de producao de um colaborador do tipo PRODUCAO.
 * A coluna "total" e calculada: quantidadeProduzida x valorUnidade.
 */
@Entity
@Table(name = "producao")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Producao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantidadeProduzida")
    private int quantidadeProduzida;

    @Column(name = "valor", precision = 12, scale = 2)
    private BigDecimal valorUnidade;

    @Column(name = "total", precision = 12, scale = 2)
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMatricula", referencedColumnName = "matricula")
    private Colaboradores colaborador;
}
