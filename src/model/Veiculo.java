package model;

public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int qtdDePassageiros;
    private int ano;
    private Motorista motorista;

    public Veiculo(String placa, String marca, String modelo, int qtdDePassageiros, int ano) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.qtdDePassageiros = qtdDePassageiros;
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getQtdDePassageiros() {
        return qtdDePassageiros;
    }

    public void setQtdDePassageiros(int qtdDePassageiros) {
        this.qtdDePassageiros = qtdDePassageiros;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    @Override
    public String toString() {
        return "Veiculo: " +
                "placa: " + placa  +
                ", marca: " + marca +
                ", modelo: " + modelo +
                ", qtdDePassageiros: " + qtdDePassageiros +
                ", ano: " + ano +
                ", motorista:" + motorista ;
    }
}