package model;

public class ContaBancaria {
    private String numeroConta;
    private Double saldo;

    //fazer um util para verificar se conta ja existe

    public ContaBancaria(String numeroConta){
        this.numeroConta = numeroConta;
        this.saldo = 0.0;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void depositar(Double valor) {
        if (valor > 0) {
            this.saldo += valor;
        } else {
            //lancar excecao
            System.out.println("Valor de depósito inválido");
        }
    }

    public void sacar(Double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
        } else {
            //lancar excecao
            System.out.println("Valor de saque inválido ou saldo insuficiente");
        }
    }


}
