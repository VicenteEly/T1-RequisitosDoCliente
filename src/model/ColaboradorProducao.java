package model;

public class ColaboradorProducao extends Colaborador{

    private int quantidadeProduzida;
    private double valorPorUnidade;

    public double getValorPorUnidade() {
        return valorPorUnidade;
    }
    public void setValorPorUnidade(double valorPorUnidade) {
        if(valorPorUnidade < 0 ){
            throw new IllegalArgumentException("Erro! O valor por unidade não pode ser negativo.");
        }
        this.valorPorUnidade = valorPorUnidade;
    }
    public int getQuantidadeProduzida() {
        return quantidadeProduzida;
    }
    public void setQuantidadeProduzida(int quantidadeProduzida) {
        if (quantidadeProduzida < 0){
            throw new IllegalArgumentException("Erro! A quantidade produzida não pode ser negativa.");
        }
        this.quantidadeProduzida = quantidadeProduzida;
    }

     public ColaboradorProducao(String matricula, String nome, double salarioBase, int quantidadeProduzidaProducao, double valorPorUnidadeProducao){
         super(matricula, nome, salarioBase);
         setQuantidadeProduzida(quantidadeProduzidaProducao);
         setValorPorUnidade(valorPorUnidadeProducao);
     }


    @Override
    public String getTipoColaborador() {
        return "Produção";
    }

    @Override
    public double calculaAdicionais() {
        return quantidadeProduzida * valorPorUnidade;
    }
}
