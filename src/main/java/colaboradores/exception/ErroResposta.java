package colaboradores.exception;

import java.time.LocalDateTime;

/**
 * Resposta padrao de erro da API.
 */
public record ErroResposta(String timestamp, int status, String erro, String mensagem) {

    public static ErroResposta de(int status, String erro, String mensagem) {
        return new ErroResposta(LocalDateTime.now().toString(), status, erro, mensagem);
    }
}
