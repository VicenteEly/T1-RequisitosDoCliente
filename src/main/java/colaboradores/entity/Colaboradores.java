package colaboradores.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Colaborador da empresa. A matricula e a chave primaria da entidade.
 */
@Entity
@Table(name = "colaboradores")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Colaboradores {

    @Id
    @Column(name = "matricula", nullable = false, length = 20)
    private String matricula;

    @Column(name = "nome", nullable = false, length = 120)
    private String nome;

    @Column(name = "salario", precision = 12, scale = 2)
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoColaboradorEnum tipoColaborador;
}
