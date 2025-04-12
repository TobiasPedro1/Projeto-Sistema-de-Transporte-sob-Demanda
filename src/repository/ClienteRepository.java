package repository;

import model.Cliente;
import utils.SerializacaoUtil;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements ClienteRepositoryInterface {
    private static final String ARQUVIO = "dados/clientes.ser";
    List<Cliente> clientes;

    public ClienteRepository(){
        this.clientes = SerializacaoUtil.carregarDados(ARQUVIO);
        if(this.clientes == null){
            this.clientes = new ArrayList<>();
        }
    }

    @Override
    public List<Cliente> findALL(){
        return new ArrayList<>(clientes);
    }

    @Override
    public Cliente clienteFindByCpf(String cpf){
        for(Cliente cliente : clientes){
            if(cliente.getCpf().equals(cpf)){
                System.out.println("Cliente encontrado!");
                return cliente;
            }
        }
        //fazer lancamento de erro
        System.out.println("Cliente não encontrado!");
        return null;
    }

    @Override
    public Cliente clienteFindByNome(String nome){
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
        salvarDados();
    }

    @Override
    public void clienteDeleteByCpf(String cpf){
        if( clientes.removeIf(cliente -> cliente.getCpf().equals(cpf))){
            salvarDados();
            System.out.println("Cliente removido com sucesso!");
        }else{
            //lançar erro!
            System.out.println("Cliente não encontrado!");
        }
    }

    private void salvarDados(){
        SerializacaoUtil.salvarDados(clientes, ARQUVIO);
    }

}
