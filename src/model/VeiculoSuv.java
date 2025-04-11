package model;

import java.io.Serializable;

public class VeiculoSuv extends Veiculo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public VeiculoSuv(String placa, String marca, String modelo, int qtdDePassageiros, int ano) {
        super(placa, marca, modelo, qtdDePassageiros, ano);
    }
}
