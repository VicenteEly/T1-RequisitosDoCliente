package colaboradores.controller;

import colaboradores.entity.Colaboradores;
import colaboradores.repository.ColaboradorRepository;
import colaboradores.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {
    @Autowired
    private ColaboradorService colaboradorService;
    @GetMapping
    public ResponseEntity<List<Colaboradores>> getAllColaboradores(){
        return ResponseEntity.ok(colaboradorService.getAllColaboradores());
    }
}