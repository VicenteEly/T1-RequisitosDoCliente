package colaboradores.service;

import colaboradores.entity.Colaboradores;
import colaboradores.entity.Comissao;
import colaboradores.entity.Producao;
import colaboradores.exception.NotFoundException;
import colaboradores.repository.ColaboradoresRepository;
import colaboradores.repository.ComissaoRepository;
import colaboradores.repository.ProducaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColaboradoresService {

    private final ColaboradoresRepository colaboradoresRepository;
    private final ComissaoRepository comissaoRepository;
    private final ProducaoRepository producaoRepository;

    public ColaboradoresService(ColaboradoresRepository colaboradoresRepository,
                                ComissaoRepository comissaoRepository,
                                ProducaoRepository producaoRepository) {
        this.colaboradoresRepository = colaboradoresRepository;
        this.comissaoRepository = comissaoRepository;
        this.producaoRepository = producaoRepository;
    }

    public List<Colaboradores> listar() {
        return colaboradoresRepository.findAll();
    }

    public Colaboradores buscar(String matricula) {
        return colaboradoresRepository.findById(matricula)
                .orElseThrow(() -> new NotFoundException(
                        "Colaborador nao encontrado para a matricula " + matricula));
    }

    @Transactional
    public Colaboradores salvar(Colaboradores colaborador) {
        validar(colaborador);

        if (colaboradoresRepository.existsById(colaborador.getMatricula())) {
            throw new IllegalArgumentException(
                    "Ja existe um colaborador cadastrado com a matricula " + colaborador.getMatricula());
        }

        return colaboradoresRepository.save(colaborador);
    }

    @Transactional
    public Colaboradores atualizar(String matricula, Colaboradores dados) {
        Colaboradores existente = buscar(matricula);

        dados.setMatricula(matricula);
        validar(dados);

        existente.setNome(dados.getNome());
        existente.setSalario(dados.getSalario());
        existente.setTipoColaborador(dados.getTipoColaborador());

        return colaboradoresRepository.save(existente);
    }

    /**
     * Remove o colaborador e, junto, as comissoes e producoes vinculadas a ele
     * para nao violar a chave estrangeira.
     */
    @Transactional
    public void remover(String matricula) {
        Colaboradores existente = buscar(matricula);

        List<Comissao> comissoes = comissaoRepository.findByColaboradorMatricula(matricula);
        List<Producao> producoes = producaoRepository.findByColaboradorMatricula(matricula);

        if (!comissoes.isEmpty()) {
            comissaoRepository.deleteAll(comissoes);
        }
        if (!producoes.isEmpty()) {
            producaoRepository.deleteAll(producoes);
        }

        colaboradoresRepository.delete(existente);
    }

    public List<Comissao> comissoesDoColaborador(String matricula) {
        buscar(matricula);
        return comissaoRepository.findByColaboradorMatricula(matricula);
    }

    public List<Producao> producoesDoColaborador(String matricula) {
        buscar(matricula);
        return producaoRepository.findByColaboradorMatricula(matricula);
    }

    private void validar(Colaboradores colaborador) {
        if (colaborador.getMatricula() == null || colaborador.getMatricula().isBlank()) {
            throw new IllegalArgumentException("A matricula e obrigatoria.");
        }
        if (colaborador.getNome() == null || colaborador.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome e obrigatorio.");
        }
        if (colaborador.getTipoColaborador() == null) {
            throw new IllegalArgumentException(
                    "O tipo de colaborador e obrigatorio (PADRAO, COMISSIONADO ou PRODUCAO).");
        }
        if (colaborador.getSalario() == null) {
            throw new IllegalArgumentException("O salario e obrigatorio.");
        }
        if (colaborador.getSalario().signum() < 0) {
            throw new IllegalArgumentException("O salario nao pode ser negativo.");
        }
    }
}
