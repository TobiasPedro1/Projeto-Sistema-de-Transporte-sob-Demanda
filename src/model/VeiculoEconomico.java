package model;

public class VeiculoEconomico extends Veiculo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public VeiculoEconomico(String placa, String marca, String modelo, int qtdDePassageiros, int ano){
        super(placa, marca, modelo, qtdDePassageiros, ano);

    }
}
