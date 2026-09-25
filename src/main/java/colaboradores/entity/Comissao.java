package colaboradores.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "comissao")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comissao {

    @Column(name = "valorVendas")
    private BigDecimal valorVendas;

    @Column(name = "percentual")
    private double porcentagem;

    @Column(name = "comissao")
    private BigDecimal comissao;

    @Column(name = "idMatricula")
    private String matriculaColaborador;

}
