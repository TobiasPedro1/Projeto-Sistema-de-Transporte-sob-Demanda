package model;

import java.time.LocalDateTime;

public class Pagamento {
    private Cliente cliente;
    private Motorista motorista;
    private double valor;
    private LocalDateTime dataHoraPagamento;

    public Pagamento(){}

    public Pagamento(Cliente cliente, Motorista motorista, double valor ){
        this.cliente = cliente;
        this.motorista = motorista;
        this.valor = valor;
        this.dataHoraPagamento = LocalDateTime.now();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getDataHoraPagamento() {
        return dataHoraPagamento;
    }
}
