package repository;

import model.Cliente;
import java.util.List;

public interface ClienteRepositoryInterface {
    List<Cliente> findALL();
    Cliente clienteFindByCpf(String cpf);
    Cliente clienteFindByNome(String nome);
    void save(Cliente cliente);
    void clienteDeleteByCpf(String cpf);
}
