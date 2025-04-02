package service;

import exceptions.VeiculoNaoDisponivelException;
import exceptions.MotoristaNaoDisponivelException;
import exceptions.SalvaFalhaException;
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
        try {
            veiculoMotoRepository.save(veiculo);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao salvar veículo moto.", e);
        }
    }

    public VeiculoMoto findByPlaca(String placa) {
        try {
            return veiculoMotoRepository.findByPlaca(placa);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar veículo moto.", e);
        }
    }

    public List<VeiculoMoto> findAll() {
        try {
            return veiculoMotoRepository.findAll();
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar veículos moto.", e);
        }
    }

    public void delete(VeiculoMoto veiculo) {
        try {
            veiculoMotoRepository.delete(veiculo);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao deletar veículo moto.", e);
        }
    }

    public VeiculoMoto acharVeiculoAleatorio() {
        List<VeiculoMoto> veiculos = veiculoMotoRepository.findAll();
        if (veiculos.isEmpty()) {
            throw new VeiculoNaoDisponivelException("Nenhum veículo disponível");
        }

        Random random = new Random();
        int index = random.nextInt(veiculos.size());
        return veiculos.get(index);
    }

    public Viagem chamarViagemComVeiculoMoto(String origem, String destino, double valor, Cliente cliente) {
        List<VeiculoMoto> veiculos = veiculoMotoRepository.findAll();
        if (veiculos.isEmpty()) {
            throw new VeiculoNaoDisponivelException("Nenhum veículo moto disponível.");
        }

        VeiculoMoto veiculoEscolhido = veiculos.get(0);
        Motorista motorista = veiculoEscolhido.getMotorista();

        if (motorista == null || !motorista.isDisponivel()) {
            throw new MotoristaNaoDisponivelException("Nenhum motorista disponível para o veículo moto.");
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

    public Viagem viagemEntrega(String origem, String destino, double valor, Cliente cliente, String pacote) {
        List<VeiculoMoto> veiculos = veiculoMotoRepository.findAll();
        if (veiculos.isEmpty()) {
            throw new VeiculoNaoDisponivelException("Nenhum veículo moto disponível.");
        }

        VeiculoMoto veiculoEscolhido = veiculos.get(0);
        Motorista motorista = veiculoEscolhido.getMotorista();

        if (motorista == null || !motorista.isDisponivel()) {
            throw new MotoristaNaoDisponivelException("Nenhum motorista disponível para o veículo moto.");
        }

        Viagem viagem = viagemService.chamarViagemEntrega(origem, destino, valor, pacote);
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