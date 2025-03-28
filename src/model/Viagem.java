package model;

import model.enums.StatusViagem;
import service.MotoristaService;

public class Viagem {
    private String origem;
    private String destino;
    private double valor;
    private Veiculo veiculo;
    private Motorista motorista;
    private StatusViagem statusViagem;


    public Viagem(String origem, String destino, double valor, Veiculo veiculo, StatusViagem statusViagem,MotoristaService motoristaService) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.veiculo = veiculo;
        this.statusViagem = statusViagem;
        //possivel alteração apos a criação do repositorio
        this.motorista = motoristaService.selecionarMotoristaAleatorio();
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public StatusViagem getStatusViagem() {
        return statusViagem;
    }

    public void setStatusViagem(StatusViagem statusViagem) {
        this.statusViagem = statusViagem;
    }

    public Motorista getMotorista() {
        return motorista;
    }

}
