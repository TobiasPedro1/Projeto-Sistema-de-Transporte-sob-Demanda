
package service;

import model.Cliente;
import model.Motorista;
import model.PagamentoDinheiro;
import repository.PagamentoDinheiroRepository;

import java.time.LocalDateTime;
import java.util.List;

public class PagamentoDinheiroService {

    private final ContaBancariaService contaBancariaService;
    private final PagamentoDinheiroRepository pagamentoDinheiroRepository;

    public PagamentoDinheiroService(ContaBancariaService contaBancariaService, PagamentoDinheiroRepository pagamentoDinheiroRepository) {
        this.contaBancariaService = contaBancariaService;
        this.pagamentoDinheiroRepository = pagamentoDinheiroRepository;
    }

    public PagamentoDinheiro pagar(Cliente cliente, Motorista motorista, double valor, double dinheiro) {
        var contaCliente = cliente.getConta();
        var contaMotorista = motorista.getConta();

        if (contaCliente.getSaldo() >= valor) {
            contaCliente.sacar(valor);
            contaMotorista.depositar(valor);
            System.out.println("Pagamento em dinheiro efetuado com sucesso!");

            PagamentoDinheiro pagamento = new PagamentoDinheiro(cliente, motorista, valor, dinheiro);
            pagamentoDinheiroRepository.save(pagamento);
            return pagamento;
        } else {
            throw new RuntimeException("Dinheiro insuficiente para efetuar o pagamento");
        }
    }

    public void save(PagamentoDinheiro pagamento) {
        pagamentoDinheiroRepository.save(pagamento);
    }

    public PagamentoDinheiro findByData(LocalDateTime dataHoraPagamento) {
        return pagamentoDinheiroRepository.findByData(dataHoraPagamento);
    }

    public List<PagamentoDinheiro> findAll() {
        return pagamentoDinheiroRepository.findAll();
    }

    public void delete(PagamentoDinheiro pagamento) {
        pagamentoDinheiroRepository.delete(pagamento);
    }
}