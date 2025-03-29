package model;

import service.MotoristaService;

public class ViagemPassageiro extends Viagem {
    private String passageiro;

    public ViagemPassageiro(String origem, String destino, double valor, Veiculo veiculo, String passageiro, Motorista motorista) {
        super(origem, destino, valor, veiculo,motorista);
        this.passageiro = passageiro;
    }

    public String getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(String passageiro) {
        this.passageiro = passageiro;
    }


}
