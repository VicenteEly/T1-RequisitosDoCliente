package colaboradores.entity;

/**
 * Permissoes de remuneracao de cada tipo de colaborador.
 *
 * PADRAO       -> recebe apenas salario fixo.
 * COMISSIONADO -> salario fixo + comissao sobre vendas.
 * PRODUCAO     -> salario fixo + pagamento por quantidade produzida.
 */
public enum TipoColaboradorEnum {
    PADRAO,
    COMISSIONADO,
    PRODUCAO
}
