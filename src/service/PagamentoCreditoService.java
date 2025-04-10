package service;

import exceptions.SaldoInsuficienteException;
import exceptions.SalvaFalhaException;
import model.Cliente;
import model.Motorista;
import model.PagamentoCredito;
import repository.ClienteRepository;
import repository.MotoristaRepository;
import repository.PagamentoCreditoRepository;

import java.time.LocalDateTime;
import java.util.List;

public class PagamentoCreditoService {

    private final ContaBancariaService contaBancariaService;
    private final PagamentoCreditoRepository pagamentoCreditoRepository;
    private final ClienteRepository clienteRepository;
    private final MotoristaRepository motoristaRepository;

    public PagamentoCreditoService(ContaBancariaService contaBancariaService, PagamentoCreditoRepository pagamentoCreditoRepository, ClienteRepository clienteRepository, MotoristaRepository motoristaRepository) {
        this.contaBancariaService = contaBancariaService;
        this.pagamentoCreditoRepository = pagamentoCreditoRepository;
        this.clienteRepository = clienteRepository;
        this.motoristaRepository = motoristaRepository;
    }

    public PagamentoCredito pagar(String cliente, String motorista, double valor, String chavePixOuCartaoCliente, String chavePixOuCartaoMotorista) {
        Motorista motoristaobjt;
        Cliente clienteobjt;

        motoristaobjt = motoristaRepository.motoristaFindByNome(motorista);
        clienteobjt = clienteRepository.clienteFindByNome(cliente);

        var contaCliente = contaBancariaService.buscarContaPorChavePix(clienteobjt.getConta().getChavePix());
        var contaMotorista = contaBancariaService.buscarContaPorChavePix(clienteobjt.getConta().getChavePix());

        if (contaCliente.getSaldo() >= valor) {
            contaCliente.sacar(valor);
            contaMotorista.depositar(valor);
            System.out.println("Pagamento com crédito efetuado com sucesso!");

            PagamentoCredito pagamento = new PagamentoCredito(clienteobjt, motoristaobjt, valor, chavePixOuCartaoCliente);
            try {
                pagamentoCreditoRepository.save(pagamento);
            } catch (Exception e){
                throw new SalvaFalhaException("Erro ao salvar pagamento", e);
            }
            return pagamento;
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente para efetuar o pagamento");
        }
    }

    public void save(PagamentoCredito pagamento) {
        try {
            pagamentoCreditoRepository.save(pagamento);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao salvar pagamento.", e);
        }
    }

    public PagamentoCredito findByData(LocalDateTime dataHoraPagamento) {
        try {
            return pagamentoCreditoRepository.findByData(dataHoraPagamento);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar pagamento.", e);
        }
    }

    public List<PagamentoCredito> findAll() {
        try {
            return pagamentoCreditoRepository.findAll();
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao buscar pagamentos.", e);
        }
    }

    public void delete(PagamentoCredito pagamento) {
        try {
            pagamentoCreditoRepository.delete(pagamento);
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao deletar pagamento.", e);
        }
    }
}