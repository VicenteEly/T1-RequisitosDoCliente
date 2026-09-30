package colaboradores.repository;

import colaboradores.entity.Producao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducaoRepository extends JpaRepository<Producao, Long> {

    List<Producao> findByColaboradorMatricula(String matricula);
}
