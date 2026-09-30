package colaboradores.service;

import colaboradores.dto.ComissaoRequest;
import colaboradores.entity.Colaboradores;
import colaboradores.entity.Comissao;
import colaboradores.entity.TipoColaboradorEnum;
import colaboradores.exception.NotFoundException;
import colaboradores.repository.ColaboradoresRepository;
import colaboradores.repository.ComissaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ComissaoService {

    private static final BigDecimal CEM = new BigDecimal("100");

    private final ComissaoRepository comissaoRepository;
    private final ColaboradoresRepository colaboradoresRepository;

    public ComissaoService(ComissaoRepository comissaoRepository,
                           ColaboradoresRepository colaboradoresRepository) {
        this.comissaoRepository = comissaoRepository;
        this.colaboradoresRepository = colaboradoresRepository;
    }

    public List<Comissao> listar() {
        return comissaoRepository.findAll();
    }

    public Comissao buscar(Long id) {
        return comissaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comissao nao encontrada para o id " + id));
    }

    @Transactional
    public Comissao criar(String matricula, ComissaoRequest request) {
        Colaboradores colaborador = colaboradoresRepository.findById(matricula)
                .orElseThrow(() -> new NotFoundException(
                        "Colaborador nao encontrado para a matricula " + matricula));

        exigirComissionado(colaborador);
        validar(request);

        Comissao comissao = Comissao.builder()
                .valorVendas(request.valorVendas())
                .percentual(request.percentual())
                .comissao(calcular(request.valorVendas(), request.percentual()))
                .colaborador(colaborador)
                .build();

        return comissaoRepository.save(comissao);
    }

    @Transactional
    public Comissao atualizar(Long id, ComissaoRequest request) {
        Comissao existente = buscar(id);

        exigirComissionado(existente.getColaborador());
        validar(request);

        existente.setValorVendas(request.valorVendas());
        existente.setPercentual(request.percentual());
        existente.setComissao(calcular(request.valorVendas(), request.percentual()));

        return comissaoRepository.save(existente);
    }

    @Transactional
    public void remover(Long id) {
        comissaoRepository.delete(buscar(id));
    }

    /**
     * comissao = valorVendas x percentual / 100
     */
    private BigDecimal calcular(BigDecimal valorVendas, BigDecimal percentual) {
        return valorVendas.multiply(percentual)
                .divide(CEM, 2, RoundingMode.HALF_UP);
    }

    private void exigirComissionado(Colaboradores colaborador) {
        if (colaborador.getTipoColaborador() != TipoColaboradorEnum.COMISSIONADO) {
            throw new IllegalArgumentException(
                    "Comissao permitida apenas para colaboradores do tipo COMISSIONADO. "
                            + "A matricula " + colaborador.getMatricula() + " e do tipo "
                            + colaborador.getTipoColaborador() + ".");
        }
    }

    private void validar(ComissaoRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Os dados da comissao sao obrigatorios.");
        }
        if (request.valorVendas() == null) {
            throw new IllegalArgumentException("O valor de vendas e obrigatorio.");
        }
        if (request.valorVendas().signum() < 0) {
            throw new IllegalArgumentException("O valor de vendas nao pode ser negativo.");
        }
        if (request.percentual() == null) {
            throw new IllegalArgumentException("O percentual de comissao e obrigatorio.");
        }
        if (request.percentual().signum() < 0) {
            throw new IllegalArgumentException("O percentual de comissao nao pode ser negativo.");
        }
        if (request.percentual().compareTo(CEM) > 0) {
            throw new IllegalArgumentException("O percentual de comissao nao pode ser maior que 100.");
        }
    }
}
