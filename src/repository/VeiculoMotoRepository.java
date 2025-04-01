package repository;

import model.VeiculoMoto;

import java.util.ArrayList;
import java.util.List;

public class VeiculoMotoRepository implements VeiculoMotoRepositoryInterface {

    private List<VeiculoMoto> veiculoMotoRepository = new ArrayList<>();

    @Override
    public void save(VeiculoMoto veiculo) {
        veiculoMotoRepository.add(veiculo);
    }

    @Override
    public VeiculoMoto findByPlaca(String placa) {
        for (VeiculoMoto veiculo : veiculoMotoRepository) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    @Override
    public List<VeiculoMoto> findAll() {
        return veiculoMotoRepository;
    }

    @Override
    public void delete(VeiculoMoto veiculo) {
        veiculoMotoRepository.remove(veiculo);
    }
}