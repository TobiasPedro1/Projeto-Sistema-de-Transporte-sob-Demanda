package repository;

import model.Motorista;
import java.util.List;

public interface MotoritaRepositoryInterface {
    List<Motorista> findALL();
    Motorista motoristaFindByName(String nome);
    void save(Motorista motorista);
    void deleteByName(String nome);
}
