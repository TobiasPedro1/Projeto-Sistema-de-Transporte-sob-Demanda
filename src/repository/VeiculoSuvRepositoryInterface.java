package repository;

import model.VeiculoSuv;

import java.util.List;

public interface VeiculoSuvRepositoryInterface {
    void save(VeiculoSuv veiculo);
    VeiculoSuv findByPlaca(String placa);
    List<VeiculoSuv> findAll();
    void delete(VeiculoSuv veiculo);
}