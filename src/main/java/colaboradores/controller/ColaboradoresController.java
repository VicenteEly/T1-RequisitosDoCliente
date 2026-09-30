package colaboradores.controller;

import colaboradores.entity.Colaboradores;
import colaboradores.entity.Comissao;
import colaboradores.entity.Producao;
import colaboradores.service.ColaboradoresService;
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
@RequestMapping("/api/colaboradores")
public class ColaboradoresController {

    private final ColaboradoresService colaboradoresService;

    public ColaboradoresController(ColaboradoresService colaboradoresService) {
        this.colaboradoresService = colaboradoresService;
    }

    @GetMapping
    public List<Colaboradores> listar() {
        return colaboradoresService.listar();
    }

    @GetMapping("/{matricula}")
    public Colaboradores buscar(@PathVariable String matricula) {
        return colaboradoresService.buscar(matricula);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Colaboradores salvar(@RequestBody Colaboradores colaborador) {
        return colaboradoresService.salvar(colaborador);
    }

    @PutMapping("/{matricula}")
    public Colaboradores atualizar(@PathVariable String matricula,
                                   @RequestBody Colaboradores colaborador) {
        return colaboradoresService.atualizar(matricula, colaborador);
    }

    @DeleteMapping("/{matricula}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable String matricula) {
        colaboradoresService.remover(matricula);
    }

    @GetMapping("/{matricula}/comissoes")
    public List<Comissao> comissoesDoColaborador(@PathVariable String matricula) {
        return colaboradoresService.comissoesDoColaborador(matricula);
    }

    @GetMapping("/{matricula}/producoes")
    public List<Producao> producoesDoColaborador(@PathVariable String matricula) {
        return colaboradoresService.producoesDoColaborador(matricula);
    }
}
