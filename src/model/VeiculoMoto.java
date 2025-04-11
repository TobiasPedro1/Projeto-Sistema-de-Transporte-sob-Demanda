package model;

public class VeiculoMoto extends Veiculo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public VeiculoMoto(String placa, String marca, String modelo, int qtdDePassageiros, int ano) {
        super(placa, marca, modelo, qtdDePassageiros, ano);

    }
}
