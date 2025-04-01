package service;

import model.Cliente;
import model.Motorista;
import model.VeiculoEconomico;
import model.Viagem;
import repository.VeiculoEconomicoRepository;
import repository.ViagemRepository;

import java.util.List;
import java.util.Random;

public class VeiculoEconomicoService {

    private final VeiculoEconomicoRepository veiculoEconomicoRepository;
    private final ViagemService viagemService;
    private final ViagemRepository viagemRepository;

    public VeiculoEconomicoService(VeiculoEconomicoRepository veiculoEconomicoRepository, ViagemService viagemService, ViagemRepository viagemRepository) {
        this.veiculoEconomicoRepository = veiculoEconomicoRepository;
        this.viagemService = viagemService;
        this.viagemRepository = viagemRepository;
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

        VeiculoEconomico veiculoEscolhido = veiculos.get(0); // Escolhe o primeiro veículo disponível
        Motorista motorista = veiculoEscolhido.getMotorista();

        if (motorista == null || !motorista.isDisponivel()) {
            System.out.println("Nenhum motorista disponível para o veículo econômico.");
            return null;
        }

        Viagem viagem = viagemService.chamarViagem(origem, destino, valor, cliente);
        if (viagem != null) {
            viagemRepository.save(viagem);
        }
        return viagem;
    }

    public Viagem viagemEntrega(String origem, String destino, double valor, Cliente cliente, String pacote) {
        List<VeiculoEconomico> veiculos = veiculoEconomicoRepository.findAll();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo econômico disponível.");
            return null;
        }

        VeiculoEconomico veiculoEscolhido = veiculos.get(0); // Escolhe o primeiro veículo disponível
        Motorista motorista = veiculoEscolhido.getMotorista();

        if (motorista == null || !motorista.isDisponivel()) {
            System.out.println("Nenhum motorista disponível para o veículo econômico.");
            return null;
        }

        Viagem viagem = viagemService.chamarViagemEntrega(origem, destino, valor, pacote);
        if (viagem != null) {
            viagemRepository.save(viagem);
        }
        return viagem;
    }
}