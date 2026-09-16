package model;



public abstract class Colaborador {

    private String matricula;
    private String nome;
    private double salarioBase;


    public Colaborador(String matriculaColaborador, String nomeDoColaborador, double salarioBaseDoColaborador) {

        setNome(nomeDoColaborador);
        setSalarioBase(salarioBaseDoColaborador);
        if (matriculaColaborador == null || matriculaColaborador.trim().isEmpty()) { //Validar ANTES de Atribuir (Regra de Ouro da Programação Defensiva)
            throw new IllegalArgumentException("Erro! A matricula do colaborador é obrigatória.");
        } else
            this.matricula = matriculaColaborador;

    }

    public abstract String getTipoColaborador();

    public abstract double calculaAdicionais();

    public double calculaSalarioTotal() {
        double salarioTotal = (salarioBase + calculaAdicionais());
        return salarioTotal;
    }


    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) { // Precisa do perador logico (|| = OU) pois apenas, 'nome.trim().isEmpty()' assum que o nome não será null
            throw new IllegalArgumentException("Erro! O nome do colaborador é obrigatório.");
        } else this.nome = nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase < 0) {
            throw new IllegalArgumentException("Erro! O Valor do salario está incorreto.");
        } else this.salarioBase = salarioBase;
    }
}



