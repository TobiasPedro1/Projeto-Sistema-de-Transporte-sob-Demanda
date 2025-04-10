package repository;

import model.Motorista;
import java.util.List;

public interface MotoristaRepositoryInterface {
    List<Motorista> findALL();
    Motorista motoristaFindByNome(String nome);
    Motorista motoristaFindByCpf(String cpf);
    void save(Motorista motorista);
    void deleteByCpf(String cpf);
    MotoristaRepository getInstance();
}
