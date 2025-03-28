package repository;

import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements ClienteRepositoryInterface {
    List<Cliente> clientes = new ArrayList<>();

    @Override
    public List<Cliente> findALL(){
        return new ArrayList<>(clientes);
    }

    @Override
    public Cliente clienteFindByName(String nome){
        for(Cliente cliente : clientes){
            if(cliente.getNome().equals(nome)){
                System.out.println("Cliente encontrado!");
                return cliente;
            }
        }
        //fazer lancamento de erro
        System.out.println("Cliente não encontrado!");
        return null;
    }

    @Override
    public void save(Cliente cliente){
        clientes.add(cliente);
    }

    @Override
    public void clienteDeleteByName(String nome){
        if( clientes.removeIf(cliente -> cliente.getNome().equals(nome))){
            System.out.println("Cliente removido com sucesso!");
        }else{
            //lançar erro!
            System.out.println("Cliente não encontrado!");
        }
    }

}
