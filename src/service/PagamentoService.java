package service;

import model.Cliente;
import model.Motorista;
import model.Pagamento;
import repository.PagamentoRepository;

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
            pagamentoRepository.save(pagamento);
            return pagamento;
        } else {
            throw new RuntimeException("Saldo insuficiente para efetuar o pagamento");
        }
    }
}