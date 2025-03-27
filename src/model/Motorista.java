package model;

import java.util.ArrayList;
import java.util.List;


public class Motorista extends Pessoa {
    private String habilitacao;
    private boolean validado;
    private ContaBancaria conta;
    private Veiculo veiculo;
    private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

    public Motorista(String nome, String cpf, String habilitacao, ContaBancaria conta, Veiculo veiculo){
        super(nome, cpf);
        this.habilitacao = habilitacao;
        this.validado = false;
        this.conta = conta;
        this.veiculo = veiculo;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    public void setHabilitacao(String habilitacao) {
        this.habilitacao = habilitacao;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public ContaBancaria getConta(){
        return conta;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao){
        this.avaliacoes.add(avaliacao);
    }


}
