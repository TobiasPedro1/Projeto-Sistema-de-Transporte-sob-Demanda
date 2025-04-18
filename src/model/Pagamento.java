package model;

import java.time.LocalDateTime;

public class Pagamento implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "Pagamento{" +
                "cliente: " + cliente.getNome() +
                ", motorista: " + motorista.getNome() +
                ", valor: " + valor +
                ", dataHoraPagamento: " + dataHoraPagamento +
                '}';
    }
}
