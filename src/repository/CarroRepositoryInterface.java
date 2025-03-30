package repository;

import model.Veiculo;
import java.util.List;

public interface CarroRepositoryInterface {
    void save(Veiculo veiculo);
    Veiculo findByplaca(String placa);
    List<Veiculo> findAll();
    void deleteByPlaca(String placa);
}
