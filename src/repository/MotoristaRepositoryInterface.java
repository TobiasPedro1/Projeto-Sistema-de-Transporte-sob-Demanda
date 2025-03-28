package repository;

import model.Motorista;
import java.util.List;

public interface MotoristaRepositoryInterface {
    List<Motorista> findALL();
    Motorista motoristaFindByName(String nome);
    void save(Motorista motorista);
    void deleteByName(String nome);
}
