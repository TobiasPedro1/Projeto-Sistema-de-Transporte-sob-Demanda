package service;

import model.Cliente;
import model.Motorista;
import model.Viagem;
import repository.ClienteRepository;
import repository.MotoristaRepository;
import repository.ViagemRepository;

public class ViagemService implements ViagemServiceInterface {
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

    @Override
    public Viagem chamarViagem(String origem, String destino, double valor, Cliente cliente) {
        Motorista motorista = motoristaService.selecionarMotoristaAleatorio();

        if (motorista == null || !motorista.isDisponivel()) {
            System.out.println("Nenhum motorista disponível no momento.");
            return null;
        } else if (!cliente.isValidado()) {
            System.out.println("Cliente não validado.");
            return null;
        }

        Viagem viagem = new Viagem(origem, destino, valor, motorista.getVeiculo(), motorista);
        motorista.setDisponivel(false);

        clienteRepository.save(cliente);
        motoristaRepository.save(motorista);
        viagemRepository.save(viagem);

        System.out.println("Viagem chamada por: " + cliente.getNome() + ", de origem: " + origem + " com destino: " + destino + " com motorista: " + motorista.getNome());
        return viagem;
    }

    @Override
    public Viagem chamarViagemEntrega(String origem, String destino, double valor, String encomenda) {
        Motorista motorista = motoristaService.selecionarMotoristaAleatorio();

        if (motorista == null || !motorista.isDisponivel()) {
            System.out.println("Nenhum motorista disponível no momento.");
            return null;
        }

        Viagem viagem = new Viagem(origem, destino, valor, motorista.getVeiculo(), motorista);
        motorista.setDisponivel(false);

        motoristaRepository.save(motorista);
        viagemRepository.save(viagem);

        System.out.println("Viagem de entrega chamada de origem: " + origem + " com destino: " + destino + " com motorista: " + motorista.getNome() + " do pacote: " + encomenda);
        return viagem;
    }

    @Override
    public void iniciarViagem() {
        System.out.println("Viagem iniciada.");
    }

    @Override
    public void finalizarViagem(Viagem viagem) {
        Motorista motorista = viagem.getMotorista();
        motorista.setDisponivel(true);
        motoristaRepository.save(motorista);
        viagemRepository.save(viagem);
        System.out.println("Viagem finalizada.");
        System.out.println("=================================================" +
                            "valor da viagem a ser pago: " + viagem.getValor() +
                         "\n=================================================");

    }

    @Override
    public void cancelarViagem(Viagem viagem) {
        Motorista motorista = viagem.getMotorista();
        motorista.setDisponivel(true);
        motoristaRepository.save(motorista);
        viagemRepository.save(viagem);
        System.out.println("Viagem cancelada.");
    }
}