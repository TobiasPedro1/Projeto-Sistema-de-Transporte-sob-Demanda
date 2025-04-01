package service;

import model.VeiculoSuv;
import repository.VeiculoSuvRepository;

import java.util.List;

public class VeiculoSuvService {

    private final VeiculoSuvRepository veiculoSuvRepository;

    public VeiculoSuvService(VeiculoSuvRepository veiculoSuvRepository) {
        this.veiculoSuvRepository = veiculoSuvRepository;
    }

    public void save(VeiculoSuv veiculo) {
        veiculoSuvRepository.save(veiculo);
    }

    public VeiculoSuv findByPlaca(String placa) {
        return veiculoSuvRepository.findByPlaca(placa);
    }

    public List<VeiculoSuv> findAll() {
        return veiculoSuvRepository.findAll();
    }

    public void delete(VeiculoSuv veiculo) {
        veiculoSuvRepository.delete(veiculo);
    }
}