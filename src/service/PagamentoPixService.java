package service;

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
            pagamentoPixRepository.save(pagamento);
            return pagamento;
        } else {
            throw new RuntimeException("Saldo insuficiente para efetuar o pagamento");
        }
    }

    public void save(PagamentoPix pagamento) {
        pagamentoPixRepository.save(pagamento);
    }

    public PagamentoPix findByChavePix(String chavePix) {
        return pagamentoPixRepository.findByChavePix(chavePix);
    }

    public List<PagamentoPix> findAll() {
        return pagamentoPixRepository.findAll();
    }

    public void delete(PagamentoPix pagamento) {
        pagamentoPixRepository.delete(pagamento);
    }
}