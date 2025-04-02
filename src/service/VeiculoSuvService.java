package service;

import exceptions.VeiculoNaoDisponivelException;
import exceptions.MotoristaNaoDisponivelException;
import exceptions.SalvaFalhaException;
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
        try {
            veiculoSuvRepository.save(veiculo);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao salvar veículo SUV.", e);
        }
    }

    public VeiculoSuv findByPlaca(String placa) {
        try {
            return veiculoSuvRepository.findByPlaca(placa);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar veículo SUV.", e);
        }
    }

    public List<VeiculoSuv> findAll() {
        try {
            return veiculoSuvRepository.findAll();
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar veículos SUV.", e);
        }
    }

    public void delete(VeiculoSuv veiculo) {
        try {
            veiculoSuvRepository.delete(veiculo);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao deletar veículo SUV.", e);
        }
    }

    public VeiculoSuv acharVeiculoAleatorio() {
        List<VeiculoSuv> veiculos = veiculoSuvRepository.findAll();
        if (veiculos.isEmpty()) {
            throw new VeiculoNaoDisponivelException("Nenhum veículo disponível");
        }

        Random random = new Random();
        int index = random.nextInt(veiculos.size());
        return veiculos.get(index);
    }

    public Viagem chamarViagemComVeiculoSuv(String origem, String destino, double valor, Cliente cliente) {
        List<VeiculoSuv> veiculos = veiculoSuvRepository.findAll();
        if (veiculos.isEmpty()) {
            throw new VeiculoNaoDisponivelException("Nenhum veículo SUV disponível.");
        }

        VeiculoSuv veiculoEscolhido = veiculos.get(0);
        Motorista motorista = veiculoEscolhido.getMotorista();

        if (motorista == null || !motorista.isDisponivel()) {
            throw new MotoristaNaoDisponivelException("Nenhum motorista disponível para o veículo SUV.");
        }

        Viagem viagem = viagemService.chamarViagem(origem, destino, valor, cliente.getNome());
        if (viagem != null) {
            try {
                viagemRepository.save(viagem);
            } catch (Exception e) {
                throw new SalvaFalhaException("Erro ao salvar viagem.", e);
            }
        }
        return viagem;
    }
}