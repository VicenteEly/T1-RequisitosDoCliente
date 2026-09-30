package colaboradores.dto;

import java.math.BigDecimal;

/**
 * Payload de entrada para criar/atualizar uma producao.
 * A coluna "total" nao vem aqui: ela e calculada pelo servico.
 */
public record ProducaoRequest(int quantidadeProduzida, BigDecimal valorUnidade) {
}
