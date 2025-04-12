package service;

import exceptions.SaldoInsuficienteException;
import exceptions.SalvaFalhaException;
import model.Cliente;
import model.Motorista;
import model.Pagamento;
import repository.ClienteRepository;
import repository.MotoristaRepository;
import repository.PagamentoRepository;

import java.time.LocalDateTime;
import java.util.List;

public class PagamentoService {
    private final ContaBancariaService contaBancariaService;
    private final PagamentoRepository pagamentoRepository;
    private final MotoristaRepository motoristaRepository;
    private final ClienteRepository clienteRepository;

    public PagamentoService(ContaBancariaService contaBancariaService, PagamentoRepository pagamentoRepository,
                            MotoristaRepository motoristaRepository, ClienteRepository clienteRepository ) {
        this.contaBancariaService = contaBancariaService;
        this.pagamentoRepository = pagamentoRepository;
        this.motoristaRepository = motoristaRepository;
        this.clienteRepository = clienteRepository;
    }

    public Pagamento pagarCorrida(String clienteNome, String motoristaNome, double valor) {
        Motorista motorista = motoristaRepository.motoristaFindByNome(motoristaNome);
        Cliente cliente = clienteRepository.clienteFindByNome(clienteNome);

        String numeroContaCliente = cliente.getConta().getNumeroConta();
        String numeroContaMotorista = motorista.getConta().getNumeroConta();

        if (contaBancariaService.buscarContaPorNumero(numeroContaCliente).getSaldo() >= valor) {
            contaBancariaService.sacar(numeroContaCliente, valor);
            contaBancariaService.depositar(numeroContaMotorista, valor);
            System.out.println("Pagamento efetuado com sucesso!");

            Pagamento pagamento = new Pagamento(cliente, motorista, valor);
            try {
                pagamentoRepository.save(pagamento);
            } catch (Exception e) {
                throw new SalvaFalhaException("Erro ao salvar pagamento.", e);
            }
            return pagamento;
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente para efetuar o pagamento");
        }
    }

    public void save(Pagamento pagamento) {
        try {
            pagamentoRepository.save(pagamento);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao salvar pagamento.", e);
        }
    }

    public  List<Pagamento> findByData(LocalDateTime data) {
        try {
            List<Pagamento> pagamentos = pagamentoRepository.findByData(data);
            if (pagamentos == null || pagamentos.isEmpty()) {
                throw new SalvaFalhaException("Pagamento não encontrado", null);
            }
            return pagamentos;
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar pagamento.", e);
        }
    }

    public List<Pagamento> findAll() {
        try {
            return pagamentoRepository.findAll();
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar pagamentos.", e);
        }
    }

    public void delete(Pagamento pagamento) {
        try {
            pagamentoRepository.delete(pagamento);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao deletar pagamento.", e);
        }
    }
}