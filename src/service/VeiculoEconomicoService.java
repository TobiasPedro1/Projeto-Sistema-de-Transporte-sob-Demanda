package service;

import model.Cliente;
import model.Motorista;
import model.VeiculoEconomico;
import model.Viagem;
import repository.VeiculoEconomicoRepository;

import java.util.List;

public class VeiculoEconomicoService {

    private final VeiculoEconomicoRepository veiculoEconomicoRepository;
    private final ViagemService viagemService;

    public VeiculoEconomicoService(VeiculoEconomicoRepository veiculoEconomicoRepository, ViagemService viagemService) {
        this.veiculoEconomicoRepository = veiculoEconomicoRepository;
        this.viagemService = viagemService;
    }

    public void save(VeiculoEconomico veiculo) {
        veiculoEconomicoRepository.save(veiculo);
    }

    public VeiculoEconomico findByPlaca(String placa) {
        return veiculoEconomicoRepository.findByPlaca(placa);
    }

    public List<VeiculoEconomico> findAll() {
        return veiculoEconomicoRepository.findAll();
    }

    public void delete(VeiculoEconomico veiculo) {
        veiculoEconomicoRepository.delete(veiculo);
    }

    public Viagem chamarViagemComVeiculoEconomico(String origem, String destino, double valor, Cliente cliente) {
        List<VeiculoEconomico> veiculos = veiculoEconomicoRepository.findAll();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo econômico disponível.");
            return null;
        }

        VeiculoEconomico veiculoEscolhido = veiculos.get(0);
        Motorista motorista = veiculoEscolhido.getMotorista();

        if (motorista == null || !motorista.isDisponivel()) {
            System.out.println("Nenhum motorista disponível para o veículo econômico.");
            return null;
        }

        return viagemService.chamarViagem(origem, destino, valor, cliente);
    }
}