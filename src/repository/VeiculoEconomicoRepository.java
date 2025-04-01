package repository;

import model.VeiculoEconomico;

import java.util.ArrayList;
import java.util.List;

public class VeiculoEconomicoRepository implements VeiculoEconomicoRepositoryInterface {

    private List<VeiculoEconomico> veiculoEconomicoRepository = new ArrayList<>();

    @Override
    public void save(VeiculoEconomico veiculo) {
        veiculoEconomicoRepository.add(veiculo);
    }

    @Override
    public VeiculoEconomico findByPlaca(String placa) {
        for (VeiculoEconomico veiculo : veiculoEconomicoRepository) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    @Override
    public List<VeiculoEconomico> findAll() {
        return veiculoEconomicoRepository;
    }

    @Override
    public void delete(VeiculoEconomico veiculo) {
        veiculoEconomicoRepository.remove(veiculo);
    }
}