
package service;

import exceptions.SaldoInsuficienteException;
import exceptions.SalvaFalhaException;
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

    public PagamentoDinheiro pagar(Cliente cliente, Motorista motorista, double valor) {
        var contaCliente = cliente.getConta();
        var contaMotorista = motorista.getConta();

        if (contaCliente.getSaldo() >= valor) {
            contaCliente.sacar(valor);
            contaMotorista.depositar(valor);
            System.out.println("Pagamento em dinheiro efetuado com sucesso!");

            PagamentoDinheiro pagamento = new PagamentoDinheiro(cliente, motorista, valor);
            try {
                pagamentoDinheiroRepository.save(pagamento);
            } catch (Exception e) {
                throw new SalvaFalhaException("Erro ao salvar pagamento.", e);
            }
            return pagamento;
        } else {
            throw new SaldoInsuficienteException("Dinheiro insuficiente para efetuar o pagamento");
        }
    }

    public void save(PagamentoDinheiro pagamento) {
        try {
            pagamentoDinheiroRepository.save(pagamento);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao salvar pagamento.", e);
        }
    }

    public PagamentoDinheiro findByData(LocalDateTime dataHoraPagamento) {
        try {
            return pagamentoDinheiroRepository.findByData(dataHoraPagamento);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar pagamento.", e);
        }
    }

    public List<PagamentoDinheiro> findAll() {
        try {
            return pagamentoDinheiroRepository.findAll();
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar pagamentos.", e);
        }
    }

    public void delete(PagamentoDinheiro pagamento) {
        try {
            pagamentoDinheiroRepository.delete(pagamento);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao deletar pagamento.", e);
        }
    }
}