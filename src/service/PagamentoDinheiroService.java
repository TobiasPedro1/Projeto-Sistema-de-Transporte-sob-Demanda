
package service;

import exceptions.SaldoInsuficienteException;
import exceptions.SalvaFalhaException;
import model.Cliente;
import model.Motorista;
import model.PagamentoDinheiro;
import repository.ClienteRepository;
import repository.MotoristaRepository;
import repository.PagamentoDinheiroRepository;
import repository.PagamentoRepository;

import java.time.LocalDateTime;
import java.util.List;

public class PagamentoDinheiroService {

    private final ContaBancariaService contaBancariaService;
    private final PagamentoDinheiroRepository pagamentoDinheiroRepository;
    private final ClienteRepository clienteRepository;
    private final MotoristaRepository motoristaRepository;
    private final PagamentoRepository pagamentoRepository;

    public PagamentoDinheiroService(ContaBancariaService contaBancariaService, PagamentoDinheiroRepository pagamentoDinheiroRepository,
                                    ClienteRepository clienteRepository, MotoristaRepository motoristaRepository, PagamentoRepository pagamentoRepository) {
        this.contaBancariaService = contaBancariaService;
        this.pagamentoDinheiroRepository = pagamentoDinheiroRepository;
        this.clienteRepository = clienteRepository;
        this.motoristaRepository = motoristaRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    public PagamentoDinheiro pagar(String nomeCliente, String nomeMotorista, double valor) {
        Motorista motoristaobjt;
        Cliente clienteobjt;

        motoristaobjt = motoristaRepository.motoristaFindByNome(nomeMotorista);
        clienteobjt = clienteRepository.clienteFindByNome(nomeCliente);

        var contaCliente = clienteobjt.getConta();
        var contaMotorista = motoristaobjt.getConta();

        if (contaCliente.getSaldo() >= valor) {
            contaCliente.sacar(valor);
            contaMotorista.depositar(valor);
            System.out.println("Pagamento em dinheiro efetuado com sucesso!");

            PagamentoDinheiro pagamento = new PagamentoDinheiro(clienteobjt, motoristaobjt, valor);
            try {
                pagamentoRepository.save(pagamento);
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