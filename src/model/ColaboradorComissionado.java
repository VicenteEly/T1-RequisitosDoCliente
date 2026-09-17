package model;

public class ColaboradorComissionado extends Colaborador{

    private double valorVendas;
    private double percentualComissao;

    public ColaboradorComissionado (String matricula, String nome, double salario, double valorVendas, double percentualComissao){
        super(matricula, nome, salario);
        setValorVendas(valorVendas);
        setPercentualComissao(percentualComissao);
    }

    public double getValorVendas() {
        return valorVendas;
    }

    public void setValorVendas(double valorVendas) {
        if (valorVendas < 0){
            throw new IllegalArgumentException("Erro! O numero de vendas não pode ser negativo");
        }
        this.valorVendas = valorVendas;
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(double percentualComissao) {
        if (percentualComissao < 0){
            throw new IllegalArgumentException("Erro! O percentual de comissão não pode ser menor que zero.");
        }
        this.percentualComissao = percentualComissao;
    }




    @Override
    public String getTipoColaborador() {
        return "Comissionado";
    }

    @Override
    public double calculaAdicionais() {
        return valorVendas * (percentualComissao /100.0);
    }
}
