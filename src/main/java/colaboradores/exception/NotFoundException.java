package colaboradores.exception;

/**
 * Lançada quando um registro pedido nao existe.
 * Mapeada para HTTP 404 pelo {@link ApiExceptionHandler}.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String mensagem) {
        super(mensagem);
    }
}
