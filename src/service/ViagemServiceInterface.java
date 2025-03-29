package service;

import model.Cliente;
import model.Motorista;
import model.Viagem;

public interface ViagemServiceInterface {
    Viagem chamarViagem(String origem, String destino, double valor, Cliente cliente);
    Viagem chamarViagemEntrega(String origem, String destino, double valor, String encomenda);
    void cancelarViagem(Viagem viagem);
    void iniciarViagem();
    void finalizarViagem(Viagem viagem);
}
