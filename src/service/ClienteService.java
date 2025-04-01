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
            throw new CpfClienteFalhaException("CPF do cliente é inválido.");
        }
    }

    public void adicionarAvaliacao(Cliente cliente, Avaliacao avaliacao) {
        if (cliente == null) {
            throw new EntidadeNaoEncontrada("Cliente não encontrado.");
        }
        cliente.adicionarAvaliacao(avaliacao);
    }

}