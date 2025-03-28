package repository;

import model.Cliente;
import java.util.List;

public interface ClienteRepositoryInterface {
    List<Cliente> findALL();
    Cliente clienteFindByName(String nome);
    void save(Cliente cliente);
    void clienteDeleteByName(String nome);
}
