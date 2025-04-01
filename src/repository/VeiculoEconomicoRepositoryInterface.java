package repository;

import model.VeiculoEconomico;

import java.util.List;

public interface VeiculoEconomicoRepositoryInterface {
    void save(VeiculoEconomico veiculo);
    VeiculoEconomico findByPlaca(String placa);
    List<VeiculoEconomico> findAll();
    void delete(VeiculoEconomico veiculo);
}