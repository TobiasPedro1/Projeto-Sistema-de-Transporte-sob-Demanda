package model;

public class ContaBancaria {
    private String numeroConta;
    private Double saldo;
    private String pagamentoPix;
    private String pagamentoCredito;

    public ContaBancaria(String numeroConta, double saldo, String pagamentoPix, String pagamentoCredito) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.pagamentoPix = pagamentoPix;
        this.pagamentoCredito = pagamentoCredito;
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

    public String getChavePix() {
        return pagamentoPix;
    }

    public void setChavePix(String pagamentoPix) {
        this.pagamentoPix = pagamentoPix;
    }

    public String getCartaoCredito() {
        return pagamentoCredito;
    }

    public void setCartaoCredito(String pagamentoCredito) {
        this.pagamentoCredito = pagamentoCredito;
    }

    public void depositar(Double valor) {
        if (valor > 0) {
            this.saldo += valor;
        } else {
            System.out.println("Valor de depósito inválido");
        }
    }

    public void sacar(Double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
        } else {
            System.out.println("Valor de saque inválido ou saldo insuficiente");
        }
    }

    @Override
    public String toString() {
        return
                "numeroConta: " + numeroConta +
                ", saldo: " + saldo +
                ", Pix: " + pagamentoPix +
                ", Cartão: " + pagamentoCredito;
    }
}