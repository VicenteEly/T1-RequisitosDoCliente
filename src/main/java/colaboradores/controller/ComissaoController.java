package colaboradores.controller;

import colaboradores.dto.ComissaoRequest;
import colaboradores.entity.Comissao;
import colaboradores.service.ComissaoService;
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
@RequestMapping("/api/comissoes")
public class ComissaoController {

    private final ComissaoService comissaoService;

    public ComissaoController(ComissaoService comissaoService) {
        this.comissaoService = comissaoService;
    }

    @GetMapping
    public List<Comissao> listar() {
        return comissaoService.listar();
    }

    @GetMapping("/{id}")
    public Comissao buscar(@PathVariable Long id) {
        return comissaoService.buscar(id);
    }

    /** Cria uma comissao para a matricula informada na URL. */
    @PostMapping("/{matricula}")
    @ResponseStatus(HttpStatus.CREATED)
    public Comissao criar(@PathVariable String matricula, @RequestBody ComissaoRequest request) {
        return comissaoService.criar(matricula, request);
    }

    @PutMapping("/{id}")
    public Comissao atualizar(@PathVariable Long id, @RequestBody ComissaoRequest request) {
        return comissaoService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        comissaoService.remover(id);
    }
}
