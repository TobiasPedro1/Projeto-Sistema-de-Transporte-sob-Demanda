package service;

import model.Cliente;
import model.Motorista;
import model.PagamentoCredito;
import repository.PagamentoCreditoRepository;

import java.time.LocalDateTime;
import java.util.List;

public class PagamentoCreditoService {

    private final ContaBancariaService contaBancariaService;
    private final PagamentoCreditoRepository pagamentoCreditoRepository;

    public PagamentoCreditoService(ContaBancariaService contaBancariaService, PagamentoCreditoRepository pagamentoCreditoRepository) {
        this.contaBancariaService = contaBancariaService;
        this.pagamentoCreditoRepository = pagamentoCreditoRepository;
    }

    public PagamentoCredito pagar(Cliente cliente, Motorista motorista, double valor, String numeroCartao) {
        var contaCliente = cliente.getConta();
        var contaMotorista = motorista.getConta();

        if (contaCliente.getSaldo() >= valor) {
            contaCliente.sacar(valor);
            contaMotorista.depositar(valor);
            System.out.println("Pagamento com crédito efetuado com sucesso!");

            PagamentoCredito pagamento = new PagamentoCredito(cliente, motorista, valor, numeroCartao);
            pagamentoCreditoRepository.save(pagamento);
            return pagamento;
        } else {
            throw new RuntimeException("Saldo insuficiente para efetuar o pagamento");
        }
    }

    public void save(PagamentoCredito pagamento) {
        pagamentoCreditoRepository.save(pagamento);
    }

    public PagamentoCredito findByData(LocalDateTime dataHoraPagamento) {
        return pagamentoCreditoRepository.findByData(dataHoraPagamento);
    }

    public List<PagamentoCredito> findAll() {
        return pagamentoCreditoRepository.findAll();
    }

    public void delete(PagamentoCredito pagamento) {
        pagamentoCreditoRepository.delete(pagamento);
    }
}