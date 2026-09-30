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
 * Registro de comissao de um colaborador COMISSIONADO.
 * A coluna "comissao" e calculada: valorVendas x percentual / 100.
 */
@Entity
@Table(name = "comissao")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "valorVendas", precision = 12, scale = 2)
    private BigDecimal valorVendas;

    @Column(name = "percentual", precision = 5, scale = 2)
    private BigDecimal percentual;

    @Column(name = "comissao", precision = 12, scale = 2)
    private BigDecimal comissao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMatricula", referencedColumnName = "matricula")
    private Colaboradores colaborador;
}
