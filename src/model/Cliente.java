package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private boolean validado;
    private ContaBancaria conta;
    private List<Avaliacao> avaliacao = new ArrayList<>();


    public Cliente(String nome, String cpf, ContaBancaria conta){
        super(nome, cpf);
        this.validado = false;
        this.conta = conta;
    }


    public boolean isValidado(){
        return validado;
    }

    public void setValidado (boolean validado){
        this.validado = validado;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public List<Avaliacao> getAvaliacao() {
        return avaliacao;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao){
        this.avaliacao.add(avaliacao);
    }

    @Override
    public String toString() {
        return "Cliente:" + super.toString() +
                ", validado: " + validado +
                ", conta: " + conta +
                ", avaliacao: " + avaliacao;
    }
}
