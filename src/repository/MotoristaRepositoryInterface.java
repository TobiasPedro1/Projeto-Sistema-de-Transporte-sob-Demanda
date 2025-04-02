package repository;

import model.Motorista;
import java.util.List;

public interface MotoristaRepositoryInterface {
    List<Motorista> findALL();
    Motorista motoristaFindByName(String nome);
    Motorista motoristaFindByCpf(String cpf);
    void save(Motorista motorista);
    void deleteByName(String nome);
}
