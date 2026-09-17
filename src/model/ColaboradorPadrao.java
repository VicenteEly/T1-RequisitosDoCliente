package model;

public class ColaboradorPadrao extends Colaborador{


    public ColaboradorPadrao(String matricula, String nome, double salarioBase){
       super(matricula, nome, salarioBase);

    }


    @Override // Esse Override ele informa o compilador que está implementando algum metodo da classe pai
    // Ele também garante que esta utilizando o PROPRIO METODO da classe pai
    public String getTipoColaborador() {
        return "";
    }

    @Override
    public double calculaAdicionais() {
        return 0;
    }
}
