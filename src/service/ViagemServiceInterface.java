package service;

import model.Cliente;
import model.Motorista;
import model.Viagem;

import java.util.List;

public interface ViagemServiceInterface {
    Viagem chamarViagem(String origem, String destino, double valor, String nomeCliente);
    Viagem chamarViagemEntrega(String origem, String destino, double valor, String encomenda);
    void cancelarViagem(Viagem viagem);
    Viagem buscarViagemPorDestino(String destino);
    void iniciarViagem();
    List<Viagem> listarViagens();
    void deleteByDestino(String destino);
    Viagem finalizarViagem(String destino);
}
