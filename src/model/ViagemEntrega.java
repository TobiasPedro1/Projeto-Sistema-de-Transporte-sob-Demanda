package model;

import model.enums.StatusViagem;
import service.MotoristaService;

public class ViagemEntrega extends Viagem {
    private String encomenda;


    public ViagemEntrega(String origem, String destino, double valor, Veiculo veiculo, String encomenda, StatusViagem statusViagem, MotoristaService motoristaService) {
        super(origem, destino, valor, veiculo, statusViagem, motoristaService );
        this.encomenda = encomenda;
    }

    public String getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(String encomenda) {
        this.encomenda = encomenda;
    }

}
