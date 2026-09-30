package colaboradores.repository;

import colaboradores.entity.Comissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComissaoRepository extends JpaRepository<Comissao, Long> {

    List<Comissao> findByColaboradorMatricula(String matricula);
}
