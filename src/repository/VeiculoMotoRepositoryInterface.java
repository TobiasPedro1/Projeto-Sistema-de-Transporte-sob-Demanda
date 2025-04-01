package repository;

import model.VeiculoMoto;

import java.util.List;

public interface VeiculoMotoRepositoryInterface {
    void save(VeiculoMoto veiculo);
    VeiculoMoto findByPlaca(String placa);
    List<VeiculoMoto> findAll();
    void delete(VeiculoMoto veiculo);
}