package service;

import exceptions.AvaliacaoInvalida;
import exceptions.EntidadeNaoEncontrada;
import exceptions.SalvaFalhaException;
import model.Avaliacao;
import model.Cliente;
import model.Motorista;
import repository.AvaliacaoRepository;
import repository.MotoristaRepository;
import repository.ClienteRepository;

import java.util.List;

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
        if(nota < 0 || nota > 5) {
            throw new AvaliacaoInvalida("Nota deve ser entre 0 e 5.");
        }
        Avaliacao avaliacao = new Avaliacao(comentario, nota);
        Motorista motorista = motoristaRepository.motoristaFindByNome(nomeMotorista);
        if (motorista != null) {
            motoristaService.adicionarAvaliacaoMotorista(motorista, avaliacao);
            try {
                avaliacaoRepository.save(avaliacao);
            } catch (Exception e) {
                throw new SalvaFalhaException("Erro ao salvar avaliação.", e);
            }
        } else {
            throw new EntidadeNaoEncontrada("Motorista não encontrado.");
        }
    }

    public void avaliarCliente(String nome, String comentario, int nota) {
        if(nota < 0 || nota > 5) {
            throw new AvaliacaoInvalida("Nota deve ser entre 0 e 5.");
        }
        Avaliacao avaliacao = new Avaliacao(comentario, nota);
        Cliente cliente = clienteRepository.clienteFindByNome(nome);
        if (cliente != null) {
            clienteService.adicionarAvaliacao(cliente, avaliacao);
            avaliacaoRepository.save(avaliacao);
            try {
                avaliacaoRepository.save(avaliacao);
            } catch (Exception e) {
                throw new SalvaFalhaException("Erro ao salvar avaliação.", e);
            }
        } else {
            throw new EntidadeNaoEncontrada("Cliente não encontrado.");
        }
    }

    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    public void deletarAvaliacao(Avaliacao avaliacao) {
        if (avaliacao == null ) {
            throw new EntidadeNaoEncontrada("Avaliação não encontrada.");
        }
        try {
            avaliacaoRepository.delete(avaliacao);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao deletar a avaliação.", e);
        }
    }
}