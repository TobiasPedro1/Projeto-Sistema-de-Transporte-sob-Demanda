package model;

import service.MotoristaService;

public class ViagemPassageiro extends Viagem {
    private String passageiro;
    private Motorista motorista;

    public ViagemPassageiro(String origem, String destino, double valor, Veiculo veiculo, String passageiro, MotoristaService motoristaService) {
        super(origem, destino, valor, veiculo);
        this.passageiro = passageiro;
        //possivel alteração apos a criação do repositorio
        this.motorista = motoristaService.selecionarMotoristaAleatorio();
    }

    public String getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(String passageiro) {
        this.passageiro = passageiro;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }
}
