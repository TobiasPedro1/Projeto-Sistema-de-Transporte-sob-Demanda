
package service;

import exceptions.BuscaVeiculoFalhaException;
import model.Veiculo;
import model.VeiculoSuv;
import model.VeiculoEconomico;
import model.VeiculoMoto;
import model.VeiculoLuxo;
import model.Motorista;
import repository.MotoristaRepository;
import repository.VeiculoRepository;
import repository.VeiculoSuvRepository;
import repository.VeiculoEconomicoRepository;
import repository.VeiculoMotoRepository;
import repository.VeiculoLuxoRepository;
import exceptions.SalvaFalhaException;

import java.util.List;

public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final VeiculoSuvRepository veiculoSuvRepository;
    private final VeiculoEconomicoRepository veiculoEconomicoRepository;
    private final VeiculoMotoRepository veiculoMotoRepository;
    private final VeiculoLuxoRepository veiculoLuxoRepository;
    private final MotoristaRepository motoristaRepository;

    public VeiculoService(VeiculoRepository veiculoRepository, VeiculoSuvRepository veiculoSuvRepository,
                          VeiculoEconomicoRepository veiculoEconomicoRepository, VeiculoMotoRepository veiculoMotoRepository,
                          VeiculoLuxoRepository veiculoLuxoRepository, MotoristaRepository motoristaRepository) {
        this.veiculoRepository = veiculoRepository;
        this.veiculoSuvRepository = veiculoSuvRepository;
        this.veiculoEconomicoRepository = veiculoEconomicoRepository;
        this.veiculoMotoRepository = veiculoMotoRepository;
        this.veiculoLuxoRepository = veiculoLuxoRepository;
        this.motoristaRepository = motoristaRepository;
    }

    public void save(Veiculo veiculo) {
        try {
            veiculoRepository.save(veiculo);
            if (veiculo instanceof VeiculoSuv) {
                veiculoSuvRepository.save((VeiculoSuv) veiculo);
            } else if (veiculo instanceof VeiculoEconomico) {
                veiculoEconomicoRepository.save((VeiculoEconomico) veiculo);
            } else if (veiculo instanceof VeiculoMoto) {
                veiculoMotoRepository.save((VeiculoMoto) veiculo);
            } else if (veiculo instanceof VeiculoLuxo) {
                veiculoLuxoRepository.save((VeiculoLuxo) veiculo);
            }
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao salvar veículo.", e);
        }
    }

    public Veiculo findByPlaca(String placa) {
        Veiculo veiculo = veiculoRepository.findByPlaca(placa);
        if (veiculo == null) {
            throw new BuscaVeiculoFalhaException("Veículo não encontrado.");
        }
        return veiculo;
    }

    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    public void delete(Veiculo veiculo) {
        try {
            veiculoRepository.delete(veiculo);
            if (veiculo instanceof VeiculoSuv) {
                veiculoSuvRepository.delete((VeiculoSuv) veiculo);
            } else if (veiculo instanceof VeiculoEconomico) {
                veiculoEconomicoRepository.delete((VeiculoEconomico) veiculo);
            } else if (veiculo instanceof VeiculoMoto) {
                veiculoMotoRepository.delete((VeiculoMoto) veiculo);
            } else if (veiculo instanceof VeiculoLuxo) {
                veiculoLuxoRepository.delete((VeiculoLuxo) veiculo);
            }
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao deletar veículo.", e);
        }
    }

    public void cadastrarVeiculo(String tipo, String placa, String marca, String modelo, int qtdDePassageiros, int ano, String nomeMotorista) {
        Motorista motorista = motoristaRepository.motoristaFindByNome(nomeMotorista);

        if (motorista == null) {
            System.out.println("Motorista não encontrado.");
            return;
        }

        Veiculo veiculo;
        switch (tipo.toLowerCase()) {
            case "economico" -> veiculo = new VeiculoEconomico(placa, marca, modelo, qtdDePassageiros, ano);
            case "suv" -> veiculo = new VeiculoSuv(placa, marca, modelo, qtdDePassageiros, ano);
            case "moto" -> veiculo = new VeiculoMoto(placa, marca, modelo, qtdDePassageiros, ano);
            case "luxo" -> veiculo = new VeiculoLuxo(placa, marca, modelo, qtdDePassageiros, ano);
            default -> throw new IllegalArgumentException("Tipo de veículo inválido.");
        }

        veiculo.setMotorista(motorista);
        motorista.setVeiculo(veiculo);
        motoristaRepository.save(motorista);
        veiculoRepository.save(veiculo);
        System.out.println("Veículo cadastrado com sucesso!");
    }

}