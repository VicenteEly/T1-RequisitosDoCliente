package colaboradores.repository;

import colaboradores.entity.Comissao;
import colaboradores.entity.TipoColaboradorEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComissaoRepository extends JpaRepository<Comissao, String> {
    TipoColaboradorEnum findEnumByMatricula (String matricula);
}

