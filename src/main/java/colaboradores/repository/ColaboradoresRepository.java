package colaboradores.repository;

import colaboradores.entity.Colaboradores;
import colaboradores.entity.TipoColaboradorEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColaboradoresRepository extends JpaRepository<Colaboradores, String> {

    List<Colaboradores> findByTipoColaborador(TipoColaboradorEnum tipoColaborador);
}
