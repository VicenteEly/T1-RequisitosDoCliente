package colaboradores.repository;

import colaboradores.entity.Colaboradores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColaboradorRepository extends JpaRepository<Colaboradores, String> {
    List<Colaboradores> findAll();
}
