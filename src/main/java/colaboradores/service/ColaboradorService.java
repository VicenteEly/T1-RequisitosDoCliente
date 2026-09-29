package colaboradores.service;

import colaboradores.controller.ColaboradorController;
import colaboradores.entity.Colaboradores;
import colaboradores.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ColaboradorService {
    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public List<Colaboradores> getAllColaboradores () {
        return colaboradorRepository.findAll();
    }
}
