package repository;

import model.Veiculo;
import java.util.List;

public interface VeiculoRepositoryInterface {
    void save(Veiculo carro);
    Veiculo findByPlaca(String placa);
    List<Veiculo> findAll();
    void delete(Veiculo carro);
}