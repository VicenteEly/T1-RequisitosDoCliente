package model;



public abstract class Colaborador {

    private String matricula;
    private String nome;
    private double salarioBase;
    private String tipoColaborador;




    public Colaborador(String matriculaColaborador, String nomeDoColaborador, Double salarioBaseDoColaborador, String tipoDeColaborador) {

        this.matricula = matriculaColaborador;
        this.nome = nomeDoColaborador;
        this.salarioBase = salarioBaseDoColaborador;
        this.tipoColaborador = tipoDeColaborador;

        if (nome == null || nome.trim().isEmpty()){ // Precisa do perador logico (|| = OU) pois apenas, 'nome.trim().isEmpty()' assum que o nome não será null
            throw new IllegalArgumentException("Erro! O nome do colaborador é obrigatório.");
        }
        if (salarioBase < 0) {
            throw new IllegalArgumentException("Erro! O Valor do salario está incorreto.");
        }
    }


    public abstract double calculaAdicionais();

    public double calculaSalarioTotal(){
        double salarioTotal = (salarioBase + calculaAdicionais());
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(Double salarioBase) {
        this.salarioBase = salarioBase;
    }


}

