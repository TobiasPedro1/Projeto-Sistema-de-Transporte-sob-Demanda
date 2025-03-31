package service;

import model.Avaliacao;
import model.Cliente;
import model.Motorista;
import repository.AvaliacaoRepository;
import repository.MotoristaRepository;
import repository.ClienteRepository;

public class AvaliacaoService {
    private AvaliacaoRepository avaliacaoRepository;
    private MotoristaRepository motoristaRepository;
    private ClienteRepository clienteRepository;
    private MotoristaService motoristaService;
    private ClienteService clienteService;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, MotoristaRepository motoristaRepository, ClienteRepository clienteRepository, MotoristaService motoristaService, ClienteService clienteService) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.motoristaRepository = motoristaRepository;
        this.clienteRepository = clienteRepository;
        this.motoristaService = motoristaService;
        this.clienteService = clienteService;
    }

    public void avaliarMotorista(String nomeMotorista, String comentario, int nota) {
        Avaliacao avaliacao = new Avaliacao(comentario, nota);
        Motorista motorista = motoristaRepository.motoristaFindByName(nomeMotorista);
        if (motorista != null) {
            motoristaService.adicionarAvaliacaoMotorista(motorista, avaliacao);
            avaliacaoRepository.save(avaliacao);
        } else {
            System.out.println("Motorista não encontrado.");
        }
    }

    public void avaliarCliente(String nomeCliente, String comentario, int nota) {
        Avaliacao avaliacao = new Avaliacao(comentario, nota);
        Cliente cliente = clienteRepository.clienteFindByName(nomeCliente);
        if (cliente != null) {
            clienteService.adicionarAvaliacao(cliente, avaliacao);
            avaliacaoRepository.save(avaliacao);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}