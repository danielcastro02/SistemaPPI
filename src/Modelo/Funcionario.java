package Modelo;

public class Funcionario {
private int cod;
private String nome;
private String cpf;
private double sal;

    public Funcionario(String nome, String cpf, double sal) {
        this.nome = nome;
        this.cpf = cpf;
        this.sal = sal;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public Funcionario() {
    }

}
