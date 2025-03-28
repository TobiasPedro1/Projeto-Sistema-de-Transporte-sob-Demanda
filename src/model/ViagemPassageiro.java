package model;

import model.enums.StatusViagem;
import service.MotoristaService;

public class ViagemPassageiro extends Viagem {
    private String passageiro;

    public ViagemPassageiro(String origem, String destino, double valor, Veiculo veiculo, String passageiro, StatusViagem statusViagem, MotoristaService motoristaService) {
        super(origem, destino, valor, veiculo, statusViagem,motoristaService);
        this.passageiro = passageiro;
    }

    public String getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(String passageiro) {
        this.passageiro = passageiro;
    }


}
