package service;

import exceptions.VeiculoNaoDisponivelException;
import exceptions.MotoristaNaoDisponivelException;
import exceptions.SalvaFalhaException;
import model.Cliente;
import model.Motorista;
import model.VeiculoEconomico;
import model.Viagem;
import repository.VeiculoEconomicoRepository;
import repository.ViagemRepository;

import java.util.List;

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
        try {
            veiculoEconomicoRepository.save(veiculo);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao salvar veículo econômico.", e);
        }
    }

    public VeiculoEconomico findByPlaca(String placa) {
        try {
            return veiculoEconomicoRepository.findByPlaca(placa);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar veículo econômico.", e);
        }
    }

    public List<VeiculoEconomico> findAll() {
        try {
            return veiculoEconomicoRepository.findAll();
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar veículos econômicos.", e);
        }
    }

    public void delete(VeiculoEconomico veiculo) {
        try {
            veiculoEconomicoRepository.delete(veiculo);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao deletar veículo econômico.", e);
        }
    }

    public Viagem chamarViagemComVeiculoEconomico(String origem, String destino, double valor, Cliente cliente) {
        List<VeiculoEconomico> veiculos = veiculoEconomicoRepository.findAll();
        if (veiculos.isEmpty()) {
            throw new VeiculoNaoDisponivelException("Nenhum veículo econômico disponível.");
        }

        VeiculoEconomico veiculoEscolhido = veiculos.get(0);
        Motorista motorista = veiculoEscolhido.getMotorista();

        if (motorista == null || !motorista.isDisponivel()) {
            throw new MotoristaNaoDisponivelException("Nenhum motorista disponível para o veículo econômico.");
        }

        Viagem viagem = viagemService.chamarViagem(origem, destino, valor, cliente);
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
        List<VeiculoEconomico> veiculos = veiculoEconomicoRepository.findAll();
        if (veiculos.isEmpty()) {
            throw new VeiculoNaoDisponivelException("Nenhum veículo econômico disponível.");
        }

        VeiculoEconomico veiculoEscolhido = veiculos.get(0);
        Motorista motorista = veiculoEscolhido.getMotorista();

        if (motorista == null || !motorista.isDisponivel()) {
            throw new MotoristaNaoDisponivelException("Nenhum motorista disponível para o veículo econômico.");
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