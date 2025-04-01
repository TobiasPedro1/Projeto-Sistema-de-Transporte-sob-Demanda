package service;

import model.Cliente;
import model.Motorista;
import model.VeiculoMoto;
import model.Viagem;
import repository.VeiculoMotoRepository;
import repository.ViagemRepository;

import java.util.List;
import java.util.Random;

public class VeiculoMotoService {

    private final VeiculoMotoRepository veiculoMotoRepository;
    private final ViagemService viagemService;
    private final ViagemRepository viagemRepository;

    public VeiculoMotoService(VeiculoMotoRepository veiculoMotoRepository, ViagemService viagemService, ViagemRepository viagemRepository) {
        this.veiculoMotoRepository = veiculoMotoRepository;
        this.viagemService = viagemService;
        this.viagemRepository = viagemRepository;
    }

    public void save(VeiculoMoto veiculo) {
        veiculoMotoRepository.save(veiculo);
    }

    public VeiculoMoto findByPlaca(String placa) {
        return veiculoMotoRepository.findByPlaca(placa);
    }

    public List<VeiculoMoto> findAll() {
        return veiculoMotoRepository.findAll();
    }

    public void delete(VeiculoMoto veiculo) {
        veiculoMotoRepository.delete(veiculo);
    }

    public VeiculoMoto acharVeiculoAleatorio() {
        List<VeiculoMoto> veiculos = veiculoMotoRepository.findAll();
        if (veiculos.isEmpty()) {
            throw new IllegalStateException("Nenhum veículo disponível");
        }

        Random random = new Random();
        int index = random.nextInt(veiculos.size());
        return veiculos.get(index);
    }

    public Viagem chamarViagemComVeiculoMoto(String origem, String destino, double valor, Cliente cliente) {
        List<VeiculoMoto> veiculos = veiculoMotoRepository.findAll();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo moto disponível.");
            return null;
        }

        VeiculoMoto veiculoEscolhido = veiculos.get(0); // Escolhe o primeiro veículo disponível
        Motorista motorista = veiculoEscolhido.getMotorista();

        if (motorista == null || !motorista.isDisponivel()) {
            System.out.println("Nenhum motorista disponível para o veículo moto.");
            return null;
        }

        Viagem viagem = viagemService.chamarViagem(origem, destino, valor, cliente);
        if (viagem != null) {
            viagemRepository.save(viagem);
        }
        return viagem;
    }

    public Viagem viagemEntrega(String origem, String destino, double valor, Cliente cliente, String pacote) {
        List<VeiculoMoto> veiculos = veiculoMotoRepository.findAll();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo moto disponível.");
            return null;
        }

        VeiculoMoto veiculoEscolhido = veiculos.get(0); // Escolhe o primeiro veículo disponível
        Motorista motorista = veiculoEscolhido.getMotorista();

        if (motorista == null || !motorista.isDisponivel()) {
            System.out.println("Nenhum motorista disponível para o veículo moto.");
            return null;
        }

        Viagem viagem = viagemService.chamarViagemEntrega(origem, destino, valor, pacote);
        if (viagem != null) {
            viagemRepository.save(viagem);
        }
        return viagem;
    }
}