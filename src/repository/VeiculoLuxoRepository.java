package repository;

import model.VeiculoLuxo;

import java.util.ArrayList;
import java.util.List;

public class VeiculoLuxoRepository implements VeiculoLuxoRepositoryInterface {

    private List<VeiculoLuxo> veiculoLuxoRepository = new ArrayList<>();

    @Override
    public void save(VeiculoLuxo veiculo) {
        veiculoLuxoRepository.add(veiculo);
    }

    @Override
    public VeiculoLuxo findByPlaca(String placa) {
        for (VeiculoLuxo veiculo : veiculoLuxoRepository) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    @Override
    public List<VeiculoLuxo> findAll() {
        return veiculoLuxoRepository;
    }

    @Override
    public void delete(VeiculoLuxo veiculo) {
        veiculoLuxoRepository.remove(veiculo);
    }
}