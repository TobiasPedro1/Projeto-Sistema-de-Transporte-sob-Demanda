package model;

import model.enums.FormaDePagamento;
import java.time.LocalDateTime;

public class Pagamento {
    private Cliente cliente;
    private Motorista motorista;
    private double valor;
    private FormaDePagamento formaDePagamento;
    private LocalDateTime dataHoraPagamento;

    public Pagamento(){}

    public Pagamento(Cliente cliente, Motorista motorista, double valor,FormaDePagamento formaDePagamento){
        this.cliente = cliente;
        this.motorista = motorista;
        this.valor = valor;
        this.formaDePagamento = formaDePagamento;
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

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public LocalDateTime getDataHoraPagamento() {
        return dataHoraPagamento;
    }
}
