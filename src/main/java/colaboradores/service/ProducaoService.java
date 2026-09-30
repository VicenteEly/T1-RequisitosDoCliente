package colaboradores.service;

import colaboradores.dto.ProducaoRequest;
import colaboradores.entity.Colaboradores;
import colaboradores.entity.Producao;
import colaboradores.entity.TipoColaboradorEnum;
import colaboradores.exception.NotFoundException;
import colaboradores.repository.ColaboradoresRepository;
import colaboradores.repository.ProducaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProducaoService {

    private final ProducaoRepository producaoRepository;
    private final ColaboradoresRepository colaboradoresRepository;

    public ProducaoService(ProducaoRepository producaoRepository,
                           ColaboradoresRepository colaboradoresRepository) {
        this.producaoRepository = producaoRepository;
        this.colaboradoresRepository = colaboradoresRepository;
    }

    public List<Producao> listar() {
        return producaoRepository.findAll();
    }

    public Producao buscar(Long id) {
        return producaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producao nao encontrada para o id " + id));
    }

    @Transactional
    public Producao criar(String matricula, ProducaoRequest request) {
        Colaboradores colaborador = colaboradoresRepository.findById(matricula)
                .orElseThrow(() -> new NotFoundException(
                        "Colaborador nao encontrado para a matricula " + matricula));

        exigirProducao(colaborador);
        validar(request);

        Producao producao = Producao.builder()
                .quantidadeProduzida(request.quantidadeProduzida())
                .valorUnidade(request.valorUnidade())
                .total(calcular(request.quantidadeProduzida(), request.valorUnidade()))
                .colaborador(colaborador)
                .build();

        return producaoRepository.save(producao);
    }

    @Transactional
    public Producao atualizar(Long id, ProducaoRequest request) {
        Producao existente = buscar(id);

        exigirProducao(existente.getColaborador());
        validar(request);

        existente.setQuantidadeProduzida(request.quantidadeProduzida());
        existente.setValorUnidade(request.valorUnidade());
        existente.setTotal(calcular(request.quantidadeProduzida(), request.valorUnidade()));

        return producaoRepository.save(existente);
    }

    @Transactional
    public void remover(Long id) {
        producaoRepository.delete(buscar(id));
    }

    /**
     * total = quantidadeProduzida x valorUnidade
     */
    private BigDecimal calcular(int quantidadeProduzida, BigDecimal valorUnidade) {
        return BigDecimal.valueOf(quantidadeProduzida).multiply(valorUnidade);
    }

    private void exigirProducao(Colaboradores colaborador) {
        if (colaborador.getTipoColaborador() != TipoColaboradorEnum.PRODUCAO) {
            throw new IllegalArgumentException(
                    "Producao permitida apenas para colaboradores do tipo PRODUCAO. "
                            + "A matricula " + colaborador.getMatricula() + " e do tipo "
                            + colaborador.getTipoColaborador() + ".");
        }
    }

    private void validar(ProducaoRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Os dados da producao sao obrigatorios.");
        }
        if (request.quantidadeProduzida() <= 0) {
            throw new IllegalArgumentException("A quantidade produzida deve ser maior que zero.");
        }
        if (request.valorUnidade() == null) {
            throw new IllegalArgumentException("O valor da unidade e obrigatorio.");
        }
        if (request.valorUnidade().signum() < 0) {
            throw new IllegalArgumentException("O valor da unidade nao pode ser negativo.");
        }
    }
}
