package model;

import static utils.VerificaCpf.verificaCPF;

public class Motorista extends Pessoa {
    private String habilitacao;
    private boolean validado;

    public Motorista(String nome, String cpf, String habilitacao){
        super(nome, cpf);
        this.habilitacao = habilitacao;
        this.validado = false;
    }

    public String getHabilitação() {
        return habilitacao;
    }

    public void setHabilitação(String habilitacao) {
        this.habilitacao = habilitacao;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }


    public boolean validarMotorista(){

        if(verificaCPF(super.getCpf()) && this.habilitacao.length() == 11){
            this.validado = true;
            return true;
        }else {
            this.validado = false;
            return false;
        }

    }
}
