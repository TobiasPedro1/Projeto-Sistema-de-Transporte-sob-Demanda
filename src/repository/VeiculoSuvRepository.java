package repository;

import model.VeiculoSuv;

import java.util.ArrayList;
import java.util.List;

public class VeiculoSuvRepository implements VeiculoSuvRepositoryInterface {

    private List<VeiculoSuv> veiculoSuvRepository = new ArrayList<>();

    @Override
    public void save(VeiculoSuv veiculo) {
        veiculoSuvRepository.add(veiculo);
    }

    @Override
    public VeiculoSuv findByPlaca(String placa) {
        for (VeiculoSuv veiculo : veiculoSuvRepository) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    @Override
    public List<VeiculoSuv> findAll() {
        return veiculoSuvRepository;
    }

    @Override
    public void delete(VeiculoSuv veiculo) {
        veiculoSuvRepository.remove(veiculo);
    }
}