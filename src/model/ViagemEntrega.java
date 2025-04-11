package model;

import service.MotoristaService;

public class ViagemEntrega extends Viagem implements  java.io.Serializable {
    private final static long serialVersionUID = 1L;

    private String encomenda;

    public ViagemEntrega(String origem, String destino, double valor, Veiculo veiculo, String encomenda, Motorista motorista) {
        super(origem, destino, valor, veiculo, motorista );
        this.encomenda = encomenda;
    }

    public String getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(String encomenda) {
        this.encomenda = encomenda;
    }

}
