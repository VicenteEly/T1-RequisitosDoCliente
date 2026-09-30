package colaboradores.dto;

import java.math.BigDecimal;

/**
 * Payload de entrada para criar/atualizar uma comissao.
 * A coluna "comissao" nao vem aqui: ela e calculada pelo servico.
 */
public record ComissaoRequest(BigDecimal valorVendas, BigDecimal percentual) {
}
