package service;

import static utils.VerificaCpf.verificaCPF;
import model.Cliente;
import model.Avaliacao;
import repository.ClienteRepository;
import exceptions.CpfClienteFalhaException;
import exceptions.EntidadeNaoEncontrada;
import exceptions.SalvaFalhaException;

public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public boolean validarCliente(Cliente cliente) {
        if (verificaCPF(cliente.getCpf())) {
            clienteRepository.save(cliente);
            cliente.setValidado(true);
            System.out.println("Cliente validado com sucesso.");
            return true;
        } else {
            cliente.setValidado(false);
            System.out.println("Falha na validação do cliente.");
            throw new CpfClienteFalhaException("CPF do cliente é inválido.");
        }
    }

    public void adicionarAvaliacao(Cliente cliente, Avaliacao avaliacao) {
        if (cliente == null) {
            throw new EntidadeNaoEncontrada("Cliente não encontrado.");
        }
        cliente.adicionarAvaliacao(avaliacao);
    }

    public void salvarCliente(Cliente cliente) {
        try {
            clienteRepository.save(cliente);
            System.out.println("Cliente salvo com sucesso.");
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao salvar cliente.", e);
        }
    }
}