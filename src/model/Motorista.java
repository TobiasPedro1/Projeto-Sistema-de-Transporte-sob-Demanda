package model;

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


    public void validarMotorista(String cpf,String habilitacao){
        // pegar o util verifica cpf

        if()

        this.validado = true;

    }
}
