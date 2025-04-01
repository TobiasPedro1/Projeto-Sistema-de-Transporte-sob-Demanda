package service;

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
        veiculoLuxoRepository.save(veiculo);
    }

    public VeiculoLuxo findByPlaca(String placa) {
        return veiculoLuxoRepository.findByPlaca(placa);
    }

    public List<VeiculoLuxo> findAll() {
        return veiculoLuxoRepository.findAll();
    }

    public void delete(VeiculoLuxo veiculo) {
        veiculoLuxoRepository.delete(veiculo);
    }

    public Viagem chamarViagemComVeiculoLuxo(String origem, String destino, double valor, Cliente cliente) {
        List<VeiculoLuxo> veiculos = veiculoLuxoRepository.findAll();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo de luxo disponível.");
            return null;
        }

        VeiculoLuxo veiculoEscolhido = veiculos.get(0);
        Motorista motorista = veiculoEscolhido.getMotorista();

        if (motorista == null || !motorista.isDisponivel()) {
            System.out.println("Nenhum motorista disponível para o veículo de luxo.");
            return null;
        }

        Viagem viagem = viagemService.chamarViagem(origem, destino, valor, cliente);
        if (viagem != null) {
            viagemRepository.save(viagem);
        }
        return viagem;
    }
}