package service;

import model.Cliente;
import model.Motorista;
import model.Viagem;
import repository.ClienteRepository;
import repository.MotoristaRepository;
import repository.ViagemRepository;

public class ViagemService {
    private ClienteRepository clienteRepository;
    private MotoristaRepository motoristaRepository;
    private MotoristaService motoristaService;
    private ViagemRepository viagemRepository;


    public ViagemService(ClienteRepository clienteRepository, MotoristaRepository motoristaRepository, MotoristaService motoristaService, ViagemRepository viagemRepository) {
        this.clienteRepository = clienteRepository;
        this.motoristaRepository = motoristaRepository;
        this.motoristaService = motoristaService;
        this.viagemRepository = viagemRepository;
    }

    // Implementação do serviço de viagem

    public Viagem chamarViagem(String origem, String destino, double valor, Cliente cliente) {
        Motorista motorista = motoristaService.selecionarMotoristaAleatorio();

        if(motorista == null || !motorista.isDisponivel()){
            //lancar excecao
            System.out.println("Nenhum motorista disponível no momento.");
            return null;
        } else if (!cliente.isValidado()) {
            //lancar excecao
            System.out.println("Cliente não validado.");
            return null;
        }

        Viagem viagem = new Viagem(origem, destino, valor, motorista.getVeiculo(), motorista);

        clienteRepository.save(cliente);
        motoristaRepository.save(motorista);


        viagemRepository.save(viagem);

        System.out.println("Viagem chamada por: " + cliente.getNome() + ", de origem: "+origem + "com destino: "+destino + " com motorista: " + motorista.getNome());
        return viagem;
    }

    public void iniciarViagem() {
        System.out.println("Viagem iniciada.");
    }

    public void finalizarViagem() {
        System.out.println("Viagem finalizada.");
    }

    public void cancelarViagem() {
        System.out.println("Viagem cancelada.");
    }
}
