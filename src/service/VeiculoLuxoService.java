package service;

import exceptions.VeiculoNaoDisponivelException;
import exceptions.MotoristaNaoDisponivelException;
import exceptions.SalvaFalhaException;
import model.Cliente;
import model.Motorista;
import model.VeiculoLuxo;
import model.Viagem;
import repository.VeiculoLuxoRepository;
import repository.ViagemRepository;

import java.util.List;

public class VeiculoLuxoService {

    private final VeiculoLuxoRepository veiculoLuxoRepository;
    private final ViagemService viagemService;
    private final ViagemRepository viagemRepository;

    public VeiculoLuxoService(VeiculoLuxoRepository veiculoLuxoRepository, ViagemService viagemService, ViagemRepository viagemRepository) {
        this.veiculoLuxoRepository = veiculoLuxoRepository;
        this.viagemService = viagemService;
        this.viagemRepository = viagemRepository;
    }

    public void save(VeiculoLuxo veiculo) {
        try {
            veiculoLuxoRepository.save(veiculo);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao salvar veículo de luxo.", e);
        }
    }

    public VeiculoLuxo findByPlaca(String placa) {
        try {
            return veiculoLuxoRepository.findByPlaca(placa);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar veículo de luxo.", e);
        }
    }

    public List<VeiculoLuxo> findAll() {
        try {
            return veiculoLuxoRepository.findAll();
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar veículos de luxo.", e);
        }
    }

    public void delete(VeiculoLuxo veiculo) {
        try {
            veiculoLuxoRepository.delete(veiculo);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao deletar veículo de luxo.", e);
        }
    }

    public Viagem chamarViagemComVeiculoLuxo(String origem, String destino, double valor, Cliente cliente) {
        List<VeiculoLuxo> veiculos = veiculoLuxoRepository.findAll();
        if (veiculos.isEmpty()) {
            throw new VeiculoNaoDisponivelException("Nenhum veículo de luxo disponível.");
        }

        VeiculoLuxo veiculoEscolhido = veiculos.get(0);
        Motorista motorista = veiculoEscolhido.getMotorista();

        if (motorista == null || !motorista.isDisponivel()) {
            throw new MotoristaNaoDisponivelException("Nenhum motorista disponível para o veículo de luxo.");
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