package service;

import exceptions.SalvaFalhaException;
import model.Cliente;
import model.Motorista;
import model.PagamentoPix;
import model.ContaBancaria;
import repository.PagamentoPixRepository;

import java.util.List;

public class PagamentoPixService {

    private final ContaBancariaService contaBancariaService;
    private final PagamentoPixRepository pagamentoPixRepository;

    public PagamentoPixService(ContaBancariaService contaBancariaService, PagamentoPixRepository pagamentoPixRepository) {
        this.contaBancariaService = contaBancariaService;
        this.pagamentoPixRepository = pagamentoPixRepository;
    }

    public PagamentoPix pagarCorrida(Cliente cliente, Motorista motorista, double valor, String chavePixCliente, String chavePixMotorista) {
        ContaBancaria contaCliente = contaBancariaService.buscarContaPorChavePix(chavePixCliente);
        ContaBancaria contaMotorista = contaBancariaService.buscarContaPorChavePix(chavePixMotorista);

        if (contaCliente.getSaldo() >= valor) {
            contaCliente.sacar(valor);
            contaMotorista.depositar(valor);
            System.out.println("Pagamento efetuado com sucesso!");

            PagamentoPix pagamento = new PagamentoPix(cliente, motorista, valor, chavePixCliente);
            try {
                pagamentoPixRepository.save(pagamento);
            } catch (Exception e) {
                throw new SalvaFalhaException("Erro ao salvar pagamento.", e);
            }
            return pagamento;
        } else {
            throw new RuntimeException("Saldo insuficiente para efetuar o pagamento");
        }
    }

    public void save(PagamentoPix pagamento) {
        try {
            pagamentoPixRepository.save(pagamento);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao salvar pagamento.", e);
        }
    }

    public PagamentoPix findByChavePix(String chavePix) {
        try {
            return pagamentoPixRepository.findByChavePix(chavePix);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar Conta.", e);
        }
    }

    public List<PagamentoPix> findAll() {
        try {
            return pagamentoPixRepository.findAll();
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar pagamentos.", e);
        }
    }

    public void delete(PagamentoPix pagamento) {
        try {
            pagamentoPixRepository.delete(pagamento);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao deletar pagamento.", e);
        }
    }
}