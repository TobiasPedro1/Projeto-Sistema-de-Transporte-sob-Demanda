package model;
import java.io.Serializable;

public class Viagem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String origem;
    private String destino;
    private double valor;
    private Veiculo veiculo;
    private Motorista motorista;

    public Viagem(String origem, String destino, double valor, Veiculo veiculo,Motorista motorista) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.veiculo = veiculo;
        this.motorista = motorista;
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

    public Motorista getMotorista() {
        return motorista;
    }

    @Override
    public String toString() {
        return "Viagem{" +
                "origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", valor=" + valor +
                ", veiculo=" + veiculo +
                ", motorista=" + motorista +
                '}';
    }
}
