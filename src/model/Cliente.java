package model;

import java.util.ArrayList;
import java.util.List;
import static utils.VerificaCpf.verificaCPF;

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

    public void adiconarAvaliacoes(Avaliacao avaliacao){
        this.avaliacao.add(avaliacao);
    }

    public boolean validarCliente(){
        if(verificaCPF(super.getCpf())){
            this.validado = true;
            return true;
        }else{
            this.validado = false;
            return false;
        }
    }

}
