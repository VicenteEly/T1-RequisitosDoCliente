package Model;



public abstract class Colaborador {

    private String matricula;
    private String nome;
    private Double SalarioBase;
    private String tipoColaborador;




    public Colaborador(String matriculaColaborador, String nomeDoColaborador, Double salarioBaseDoColaborador, String tipoDeColaborador) {

        this.matricula = matriculaColaborador;
        this.nome = nomeDoColaborador;
        this.SalarioBase = salarioBaseDoColaborador;
        this.tipoColaborador = tipoDeColaborador;

        if (nome.trim().isEmpty()){
            throw new IllegalArgumentException("Erro! Categoria (nome) não preenchida.");
        }
        if (SalarioBase < 0) {
            throw new IllegalArgumentException("Erro! O Valor do salario está incorreto.");
        }
    }
    public abstract double calculaAdicionais();

    public double calculaSalarioTotal(){
        double salarioTotal = (SalarioBase + calculaAdicionais());
        return salarioTotal;
    }

    public String getTipoColaborador() {
        return tipoColaborador;
    }

    public void setTipoColaborador(String tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSalarioBase() {
        return SalarioBase;
    }

    public void setSalarioBase(Double salarioBase) {
        SalarioBase = salarioBase;
    }


}

