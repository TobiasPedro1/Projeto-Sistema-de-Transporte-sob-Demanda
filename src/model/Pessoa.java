package model;

public class Pessoa implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String cpf;

    public Pessoa (String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "nome: " + nome  +
                ", cpf: " + cpf;
    }
}
