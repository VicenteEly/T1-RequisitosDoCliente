package colaboradores.service;

import colaboradores.entity.Comissao;
import colaboradores.entity.Producao;
import colaboradores.entity.TipoColaboradorEnum;
import colaboradores.repository.ComissaoRepository;
import colaboradores.repository.ProducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
@service
public class PagamentoService {
       @Autowired
    private ComissaoRepository comissaoRepository;

       public Comissao postComissao(Comissao dadosComissao){
           return comissaoRepository.save(dadosComissao);
       }


       @Autowired
    private ProducaoRepository producaoRepository;

    public Producao postProducao(Producao dadosProducao){
        return producaoRepository.save(dadosProducao);
    }
}
