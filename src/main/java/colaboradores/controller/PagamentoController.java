package colaboradores.controller;

import colaboradores.entity.Comissao;
import colaboradores.entity.Producao;
import colaboradores.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/pagamento") // Mapeia requisições web, para metodos controladores
public class PagamentoController {
    @Autowired
     private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<Comissao> postComissao(Comissao comissao){ //Funcao
        return ResponseEntity.ok(pagamentoService.postComissao(comissao));
    }

   @PostMapping //Funcao post, define que o retorno da funcao tem que ser ResponseEntity
   public ResponseEntity<Producao> postProducao(Producao producao){
       return ResponseEntity.ok(pagamentoService.postProducao(producao));
   }

}
