package Modelo;

public class Cliente {
//Modelo do objeto cliente
private int cod;
private String nome;
private String cpf;
private double div;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Cliente() {
    }

    public Cliente(int cod, String nome, String cpf, double sal) {
        this.cod = cod;
        this.nome = nome;
        this.cpf = cpf;
        this.div = sal;
    }

    public Cliente(String nome, String cpf, double div) {
        this.nome = nome;
        this.cpf = cpf;
        this.div = div;
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

    public double getDiv() {
        return div;
    }

    public void setDiv(double divida) {
        this.div = divida;
    }


}
