package service;

import model.Avaliacao;
import model.Cliente;
import model.Motorista;
import repository.AvaliacaoRepository;
import repository.MotoristaRepository;
import repository.ClienteRepository;
import service.MotoristaService;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoService {
    private AvaliacaoRepository avaliacaoRepository;
    private MotoristaRepository motoristaRepository;
    private ClienteRepository clienteRepository;

    public AvaliacaoService (AvaliacaoRepository avaliacaoRepository, MotoristaRepository motoristaRepository, ClienteRepository clienteRepository){
        this.avaliacaoRepository = avaliacaoRepository;
        this.motoristaRepository = motoristaRepository;
        this.clienteRepository = clienteRepository;
    }

    public void avaliaMotorista(String nomeMotorista, String comentario, int nota) {
        Avaliacao avaliacao = new Avaliacao(comentario, nota);
        Motorista motorista = motoristaRepository.motoristaFindByName(nomeMotorista);
        MotoristaService motoristaService = new MotoristaService(motoristaRepository);
        if(motorista != null){
            motoristaService.adicionarAvaliacaoMotorista(motorista, avaliacao);
            avaliacaoRepository.save(avaliacao);
        }else{
            System.out.println("Motorista não encontrado.");
        }

    }


    public void avaliarCliente(String nomeCliente, String comentario, int nota) {
        Avaliacao avaliacao = new Avaliacao(comentario, nota);
        ClienteService clienteService = new ClienteService(clienteRepository);
        Cliente cliente = clienteRepository.clienteFindByName(nomeCliente);
        if(cliente !=null ) {
            clienteService.adicionarAvaliacao(cliente, avaliacao);
            avaliacaoRepository.save(avaliacao);
        } else{
            System.out.println("Cliente não encontrado.");
        }


    }



}
