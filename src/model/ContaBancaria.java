package model;

public class ContaBancaria {
    private String numeroConta;
    private Double saldo;
    private PagamentoPix pagamentoPix;
    private PagamentoCredito pagamentoCredito;

    public ContaBancaria(String numeroConta, double saldo) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
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

    public PagamentoPix getChavePix() {
        return pagamentoPix;
    }

    public void setChavePix(PagamentoPix pagamentoPix) {
        this.pagamentoPix = pagamentoPix;
    }

    public PagamentoCredito getCartaoCredito() {
        return pagamentoCredito;
    }

    public void setCartaoCredito(PagamentoCredito pagamentoCredito) {
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
}