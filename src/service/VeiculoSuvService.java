package service;

import model.Cliente;
import model.Motorista;
import model.VeiculoSuv;
import model.Viagem;
import repository.VeiculoSuvRepository;
import repository.ViagemRepository;

import java.util.List;
import java.util.Random;

public class VeiculoSuvService {

    private final VeiculoSuvRepository veiculoSuvRepository;
    private final ViagemService viagemService;
    private final ViagemRepository viagemRepository;

    public VeiculoSuvService(VeiculoSuvRepository veiculoSuvRepository, ViagemService viagemService, ViagemRepository viagemRepository) {
        this.veiculoSuvRepository = veiculoSuvRepository;
        this.viagemService = viagemService;
        this.viagemRepository = viagemRepository;
    }

    public void save(VeiculoSuv veiculo) {
        veiculoSuvRepository.save(veiculo);
    }

    public VeiculoSuv findByPlaca(String placa) {
        return veiculoSuvRepository.findByPlaca(placa);
    }

    public List<VeiculoSuv> findAll() {
        return veiculoSuvRepository.findAll();
    }

    public void delete(VeiculoSuv veiculo) {
        veiculoSuvRepository.delete(veiculo);
    }

    public VeiculoSuv acharVeiculoAleatorio() {
        List<VeiculoSuv> veiculos = veiculoSuvRepository.findAll();
        if (veiculos.isEmpty()) {
            throw new IllegalStateException("Nenhum veículo disponível");
        }

        Random random = new Random();
        int index = random.nextInt(veiculos.size());
        return veiculos.get(index);
    }

    public Viagem chamarViagemComVeiculoSuv(String origem, String destino, double valor, Cliente cliente) {
        List<VeiculoSuv> veiculos = veiculoSuvRepository.findAll();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo SUV disponível.");
            return null;
        }

        VeiculoSuv veiculoEscolhido = veiculos.get(0);
        Motorista motorista = veiculoEscolhido.getMotorista();

        if (motorista == null || !motorista.isDisponivel()) {
            System.out.println("Nenhum motorista disponível para o veículo SUV.");
            return null;
        }

        Viagem viagem = viagemService.chamarViagem(origem, destino, valor, cliente);
        if (viagem != null) {
            viagemRepository.save(viagem);
        }
        return viagem;
    }
}