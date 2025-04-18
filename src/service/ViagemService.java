package service;

import exceptions.MotoristaNaoDisponivelException;
import exceptions.ClienteNaoValidadoException;
import exceptions.SalvaFalhaException;
import model.*;
import repository.ClienteRepository;
import repository.MotoristaRepository;
import repository.ViagemRepository;

import java.util.List;

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
    public Viagem chamarViagem(String origem, String destino, double valor, String nomeCliente) {
        Motorista motorista = motoristaService.selecionarMotoristaAleatorio();
        Cliente cliente = clienteRepository.clienteFindByNome(nomeCliente);

        if (motorista == null || !motorista.isDisponivel()) {
            throw new MotoristaNaoDisponivelException("Nenhum motorista disponível no momento.");
        } else if (!cliente.isValidado()) {
            throw new ClienteNaoValidadoException("Cliente não validado.");
        }

        System.out.println("Motorista Selecionado: " + motorista.getNome());
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
    Motorista motorista = null;
    int tentativas = 0;
    int limiteTentativas = 20;

        while (tentativas < limiteTentativas) {
            motorista = motoristaService.selecionarMotoristaAleatorio();
            if (motorista == null) {
                System.out.println("Nenhum motorista disponível para entrega no momento.");
                return null;
            }
            // Filtra apenas motoristas com VeiculoEconomico ou VeiculoMoto
            if (motorista.getVeiculo() instanceof VeiculoEconomico || motorista.getVeiculo() instanceof VeiculoMoto) {
                break;
            }
            tentativas++;
        }

        if (tentativas == limiteTentativas) {
            System.out.println( "Nenhum motorista adequado encontrado. Verificar com adm se tem motoristas cadastrados com Veiculo do tipo Economico ou Moto.");
            return null;
        }

    System.out.println("Motorista Selecionado: " + motorista.getNome());
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
    public Viagem finalizarViagem(String destino) {
        Viagem viagem = buscarViagemPorDestino(destino);

        Motorista motorista = viagem.getMotorista();
        motorista.setDisponivel(true);

        try {
            motoristaRepository.save(motorista);
            viagemRepository.save(viagem);
            System.out.println("Viagem finalizada.");
            System.out.println("=================================================" +
                                "\nvalor da viagem a ser pago: " + viagem.getValor() +
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

    @Override
    public List<Viagem> listarViagens() {
        try {
            return viagemRepository.findAll();
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao listar viagens.", e);
        }
    }

    @Override
    public void deleteByDestino(String destino) {
        try {
            viagemRepository.deleteByDestino(destino);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao deletar viagem.", e);
        }
    }
}