package service;

import exceptions.MotoristaNaoDisponivelException;
import exceptions.ClienteNaoValidadoException;
import exceptions.SalvaFalhaException;
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
            throw new MotoristaNaoDisponivelException("Nenhum motorista disponível no momento.");
        } else if (!cliente.isValidado()) {
            throw new ClienteNaoValidadoException("Cliente não validado.");
        }

        Viagem viagem = new Viagem(origem, destino, valor, motorista.getVeiculo(), motorista);
        motorista.setDisponivel(false);

        try {
            clienteRepository.save(cliente);
            motoristaRepository.save(motorista);
            viagemRepository.save(viagem);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao salvar dados da viagem.", e);
        }

        return viagem;
    }

    @Override
    public Viagem chamarViagemEntrega(String origem, String destino, double valor, String encomenda) {
        Motorista motorista = motoristaService.selecionarMotoristaAleatorio();

        if (motorista == null || !motorista.isDisponivel()) {
            throw new MotoristaNaoDisponivelException("Nenhum motorista disponível no momento.");
        }

        Viagem viagem = new Viagem(origem, destino, valor, motorista.getVeiculo(), motorista);
        motorista.setDisponivel(false);

        try {
            motoristaRepository.save(motorista);
            viagemRepository.save(viagem);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao salvar dados da viagem.", e);
        }

        return viagem;
    }

    @Override
    public void iniciarViagem() {
        System.out.println("Viagem iniciada.");
    }

    @Override
    public Viagem finalizarViagem(Viagem viagem) {
        Motorista motorista = viagem.getMotorista();
        motorista.setDisponivel(true);

        try {
            motoristaRepository.save(motorista);
            viagemRepository.save(viagem);
            System.out.println("Viagem finalizada.");
            System.out.println("=================================================" +
                    "valor da viagem a ser pago: " + viagem.getValor() +
                    "\n=================================================");
            return viagem;
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao salvar dados da viagem.", e);
        }
    }
    @Override
    public Viagem buscarViagemPorDestino(String destino) {
        try {
            return viagemRepository.findByDestino(destino);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar viagem pelo destino: " + destino, e);
        }
    }

    @Override
    public void cancelarViagem(Viagem viagem) {
        Motorista motorista = viagem.getMotorista();
        motorista.setDisponivel(true);

        try {
            motoristaRepository.save(motorista);
            viagemRepository.save(viagem);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao salvar dados da viagem.", e);
        }

        System.out.println("Viagem cancelada.");
    }
}