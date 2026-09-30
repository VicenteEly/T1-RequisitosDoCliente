package colaboradores.controller;

import colaboradores.dto.ProducaoRequest;
import colaboradores.entity.Producao;
import colaboradores.service.ProducaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/producoes")
public class ProducaoController {

    private final ProducaoService producaoService;

    public ProducaoController(ProducaoService producaoService) {
        this.producaoService = producaoService;
    }

    @GetMapping
    public List<Producao> listar() {
        return producaoService.listar();
    }

    @GetMapping("/{id}")
    public Producao buscar(@PathVariable Long id) {
        return producaoService.buscar(id);
    }

    /** Cria uma producao para a matricula informada na URL. */
    @PostMapping("/{matricula}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producao criar(@PathVariable String matricula, @RequestBody ProducaoRequest request) {
        return producaoService.criar(matricula, request);
    }

    @PutMapping("/{id}")
    public Producao atualizar(@PathVariable Long id, @RequestBody ProducaoRequest request) {
        return producaoService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        producaoService.remover(id);
    }
}
