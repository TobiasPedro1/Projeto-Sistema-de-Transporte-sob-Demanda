package service;

import exceptions.SalvaFalhaException;
import model.Cliente;
import model.Motorista;
import model.PagamentoPix;
import model.ContaBancaria;
import repository.ClienteRepository;
import repository.MotoristaRepository;
import repository.PagamentoPixRepository;
import repository.PagamentoRepository;

import java.util.List;

public class PagamentoPixService {

    private final ContaBancariaService contaBancariaService;
    private final PagamentoPixRepository pagamentoPixRepository;
    private final ClienteRepository clienteRepository;
    private final MotoristaRepository motoristaRepository;
    private final PagamentoRepository pagamentoRepository;

    public PagamentoPixService(ContaBancariaService contaBancariaService, PagamentoPixRepository pagamentoPixRepository,
                               ClienteRepository clienteRepository, MotoristaRepository motoristaRepository, PagamentoRepository pagamentoRepository) {
        this.contaBancariaService = contaBancariaService;
        this.pagamentoPixRepository = pagamentoPixRepository;
        this.clienteRepository = clienteRepository;
        this.motoristaRepository = motoristaRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    public PagamentoPix pagarCorrida(String cliente, String motorista, double valor, String chavePixCliente, String chavePixMotorista) {
        // Buscar cliente e motorista
        Motorista motoristaobjt = motoristaRepository.motoristaFindByNome(motorista);

        if (motoristaobjt == null) {
            throw new IllegalArgumentException("Motorista não encontrado: " + motorista);
        }

        Cliente clienteobjt = clienteRepository.clienteFindByNome(cliente);
        if (clienteobjt == null) {
            throw new IllegalArgumentException("Cliente não encontrado: " + cliente);
        }

        // Buscar contas bancárias
        ContaBancaria contaCliente = contaBancariaService.buscarContaPorNumero(clienteobjt.getConta().getNumeroConta());
        if (contaCliente == null) {
            throw new IllegalArgumentException("Conta bancária do cliente não encontrada.");
        }

        ContaBancaria contaMotorista = contaBancariaService.buscarContaPorChavePix(chavePixMotorista);
        if (contaMotorista == null) {
            throw new IllegalArgumentException("Conta bancária do motorista não encontrada.");
        }

        // Verificar saldo e realizar pagamento
        if (contaCliente.getSaldo() >= valor) {
            contaBancariaService.sacar(contaCliente.getNumeroConta(), valor);
            contaBancariaService.depositar(contaMotorista.getNumeroConta(), valor);
//            contaCliente.sacar(valor);
//            contaMotorista.depositar(valor);
            System.out.println("Pagamento efetuado com sucesso!");

            // Criar e salvar o pagamento
            PagamentoPix pagamento = new PagamentoPix(clienteobjt, motoristaobjt, valor, chavePixCliente);
            try {
                motoristaRepository.save(motoristaobjt);
                pagamentoRepository.save(pagamento);
                pagamentoPixRepository.save(pagamento);
            } catch (Exception e) {
                throw new SalvaFalhaException("Erro ao salvar pagamento.", e);
            }
            return pagamento;
        } else {
            throw new IllegalArgumentException("Saldo insuficiente para efetuar o pagamento.");
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