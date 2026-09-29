package colaboradores.repository;

import colaboradores.entity.Producao;
import colaboradores.entity.TipoColaboradorEnum;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProducaoRepository extends JpaRepository <Producao, String> {
    TipoColaboradorEnum findEnumByMatricula (String matricula);
}
