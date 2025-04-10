package service;

import static utils.VerificaCpf.verificaCPF;
import model.Cliente;
import model.Avaliacao;
import repository.ClienteRepository;
import exceptions.CpfFalhaException;
import exceptions.EntidadeNaoEncontrada;
import exceptions.SalvaFalhaException;

import java.util.List;

public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void cadastrarCliente(Cliente cliente) {
        if (!validarCliente(cliente)) {
            throw new CpfFalhaException("CPF inválido ou já cadastrado.");
        } else {
            try {
                clienteRepository.save(cliente);
                System.out.println("Cliente adicionado com sucesso.");
            } catch (Exception e) {
                throw new SalvaFalhaException("Erro ao adicionar cliente.", e);
            }
        }
    }

    public boolean validarCliente(Cliente cliente) {
        if (verificaCPF(cliente.getCpf())) {
            try {
                cliente.setValidado(true);
                System.out.println("Cliente validado com sucesso.");
                clienteRepository.save(cliente);
                return true;
            } catch (Exception e) {
                throw new SalvaFalhaException("Erro ao salvar cliente.", e);
            }
        } else {
            cliente.setValidado(false);
            System.out.println("Falha na validação do cliente.");
            throw new CpfFalhaException("CPF do cliente é inválido.");
        }
    }

    public void adicionarAvaliacao(Cliente cliente, Avaliacao avaliacao) {
        if (cliente == null) {
            throw new EntidadeNaoEncontrada("Cliente não encontrado.");
        }
        cliente.adicionarAvaliacao(avaliacao);
    }

    public Cliente buscarClientePorCpf(String cpf) {
        try {
            return clienteRepository.clienteFindByCpf(cpf);
        } catch (Exception e) {
            throw new CpfFalhaException("Cliente não encontrado com o CPF: " + cpf);
        }
    }

    public Cliente buscarClientePorNome(String nome) {
        try {
            return clienteRepository.clienteFindByCpf(nome);
        } catch (Exception e) {
            throw new CpfFalhaException("Cliente não encontrado com o Nome: " + nome);
        }
    }

    public List<Cliente> listarClientes() {
        try {
            return clienteRepository.findALL();
        } catch (Exception e) {
            throw new CpfFalhaException("Nenhum cliente encontrado.");
        }
    }

    public void removerCliente(String cpf) {
        try {
            clienteRepository.clienteDeleteByCpf(cpf);
            System.out.println("Cliente removido com sucesso.");
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao remover cliente.", e);
        }
    }

    public void atualizarCliente(Cliente cliente) {
        try {
            clienteRepository.save(cliente);
            System.out.println("Cliente atualizado com sucesso.");
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao atualizar cliente.", e);
        }
    }
}