package service;

import exceptions.SaldoInsuficienteException;
import exceptions.SalvaFalhaException;
import model.Cliente;
import model.Motorista;
import model.Pagamento;
import repository.PagamentoRepository;

import java.time.LocalDateTime;
import java.util.List;

public class PagamentoService {
    private final ContaBancariaService contaBancariaService;
    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(ContaBancariaService contaBancariaService, PagamentoRepository pagamentoRepository) {
        this.contaBancariaService = contaBancariaService;
        this.pagamentoRepository = pagamentoRepository;
    }

    public Pagamento pagarCorrida(Cliente cliente, Motorista motorista, double valor) {
        String numeroContaCliente = cliente.getConta().getNumeroConta();
        String numeroContaMotorista = motorista.getConta().getNumeroConta();

        if (contaBancariaService.buscarContaPorNumero(numeroContaCliente).getSaldo() >= valor) {
            contaBancariaService.buscarContaPorNumero(numeroContaCliente).sacar(valor);
            contaBancariaService.buscarContaPorNumero(numeroContaMotorista).depositar(valor);
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

    public Pagamento findByData(LocalDateTime data) {
        try {
            Pagamento pagamento = pagamentoRepository.findByData(data);
            if (pagamento == null) {
                throw new SalvaFalhaException("Pagamento não encontrado", null);
            }
            return pagamento;
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