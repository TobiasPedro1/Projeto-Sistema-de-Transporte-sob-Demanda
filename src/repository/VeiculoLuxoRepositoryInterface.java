package repository;

import model.VeiculoLuxo;

import java.util.List;

public interface VeiculoLuxoRepositoryInterface {
    void save(VeiculoLuxo veiculo);
    VeiculoLuxo findByPlaca(String placa);
    List<VeiculoLuxo> findAll();
    void delete(VeiculoLuxo veiculo);
}