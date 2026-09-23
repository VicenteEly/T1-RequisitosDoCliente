package colaboradores.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Table(name = "colaboradores")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Colaboradores {

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "nome")
    private String nome;

    @Column(name = "salario")
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoColaboradorEnum tipoColaborador;
}

