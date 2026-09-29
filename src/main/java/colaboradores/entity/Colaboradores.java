package colaboradores.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Table(name = "colaboradores")
@Entity  //Define a classe como uma entidade
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Colaboradores {

    @Id
    @OneToOne
    @Column(name = "matricula") //@Column tem que estar colada com a variavel que se refere.
    private String matricula;

    @Column(name = "nome")
    private String nome;

    @Column(name = "salario")
    private BigDecimal salario;

    @Enumerated(EnumType.STRING) //Enumerated(EnumType.) Declara qual tipo de dado o Enum pertence ( String ou Integer )
    @Column(name = "tipo")
    private TipoColaboradorEnum tipoColaborador;
}

