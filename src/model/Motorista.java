package model;

import java.util.ArrayList;
import java.util.List;


public class Motorista extends Pessoa {
    private String habilitacao;
    private boolean validado;
    private boolean disponivel;
    private ContaBancaria conta;
    private Veiculo veiculo;
    private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

    public Motorista(String nome, String cpf, String habilitacao, ContaBancaria conta){
        super(nome, cpf);
        this.habilitacao = habilitacao;
        this.validado = false;
        this.disponivel = true;
        this.conta = conta;
        this.veiculo = null;
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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao){
        this.avaliacoes.add(avaliacao);
    }
}
