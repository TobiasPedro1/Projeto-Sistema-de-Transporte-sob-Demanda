package service;

import model.*;
import repository.*;
import exceptions.*;

import java.time.LocalDateTime;
import java.util.List;


public class Facade {
    private final ClienteService clienteService;
    private final MotoristaService motoristaService;
    private final VeiculoService veiculoService;
    private final ViagemService viagemService;
    private final VeiculoSuvService veiculoSuvService;
    private final VeiculoEconomicoService veiculoEconomicoService;
    private final VeiculoMotoService veiculoMotoService;
    private final VeiculoLuxoService veiculoLuxoService;
    private final AvaliacaoService avaliacaoService;
    private final ContaBancariaService contaBancariaService;
    private final PagamentoCreditoService pagamentoCreditoService;
    private final PagamentoDinheiroService pagamentoDinheiroService;
    private final PagamentoPixService pagamentoPixService;
    private final PagamentoService pagamentoService;

    public Facade(){
        ClienteRepository clienteRepository = new ClienteRepository();
        MotoristaRepository motoristaRepository = new MotoristaRepository();
        VeiculoRepository veiculoRepository = new VeiculoRepository();
        ViagemRepository viagemRepository = new ViagemRepository();
        VeiculoSuvRepository veiculoSuvRepository = new VeiculoSuvRepository();
        VeiculoEconomicoRepository veiculoEconomicoRepository = new VeiculoEconomicoRepository();
        VeiculoMotoRepository veiculoMotoRepository = new VeiculoMotoRepository();
        VeiculoLuxoRepository veiculoLuxoRepository = new VeiculoLuxoRepository();
        AvaliacaoRepository avaliacaoRepository = new AvaliacaoRepository();
        ContaBancoRepository contaBancoRepository = new ContaBancoRepository();
        PagamentoCreditoRepository pagamentoCreditoRepository = new PagamentoCreditoRepository();
        PagamentoDinheiroRepository pagamentoDinheiroRepository = new PagamentoDinheiroRepository();
        PagamentoPixRepository pagamentoPixRepository = new PagamentoPixRepository();
        PagamentoRepository pagamentoRepository = new PagamentoRepository();

        this.clienteService = new ClienteService(clienteRepository, contaBancoRepository);
        this.motoristaService = new MotoristaService(motoristaRepository, contaBancoRepository, veiculoRepository, avaliacaoRepository);
        this.veiculoService = new VeiculoService(veiculoRepository, veiculoSuvRepository, veiculoEconomicoRepository, veiculoMotoRepository, veiculoLuxoRepository, motoristaRepository);
        this.viagemService = new ViagemService(clienteRepository, motoristaRepository, motoristaService, viagemRepository);
        this.veiculoSuvService = new VeiculoSuvService(veiculoSuvRepository, viagemService, viagemRepository);
        this.veiculoEconomicoService = new VeiculoEconomicoService(veiculoEconomicoRepository, viagemService, viagemRepository);
        this.veiculoMotoService = new VeiculoMotoService(veiculoMotoRepository, viagemService, viagemRepository);
        this.veiculoLuxoService = new VeiculoLuxoService(veiculoLuxoRepository, viagemService, viagemRepository);
        this.avaliacaoService = new AvaliacaoService(avaliacaoRepository,  motoristaRepository,  clienteRepository,  motoristaService, clienteService);
        this.contaBancariaService = new ContaBancariaService(contaBancoRepository);
        this.pagamentoCreditoService = new PagamentoCreditoService(contaBancariaService, pagamentoCreditoRepository, clienteRepository, motoristaRepository, pagamentoRepository);
        this.pagamentoDinheiroService = new PagamentoDinheiroService(contaBancariaService, pagamentoDinheiroRepository, clienteRepository, motoristaRepository, pagamentoRepository);
        this.pagamentoPixService = new PagamentoPixService(contaBancariaService,pagamentoPixRepository, clienteRepository, motoristaRepository, pagamentoRepository);
        this.pagamentoService = new PagamentoService(contaBancariaService, pagamentoRepository, motoristaRepository, clienteRepository);

    }

    public void cadastrarCliente(String nome, String cpf, String numConta, double saldo, String pix, String cartao) {
        clienteService.cadastrarCliente(new Cliente(nome, cpf, new ContaBancaria(numConta, saldo, pix, cartao)));
        contaBancariaService.criarConta(numConta, saldo, pix, cartao);
    }

    public Cliente buscarClientePorNome(String nome) {
        return clienteService.buscarClientePorNome(nome);
    }

    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    public void deletarCliente(String cpf) {
        clienteService.removerCliente(cpf);
    }

    public void atualizarCliente(Cliente cliente) {
        clienteService.atualizarCliente(cliente);
    }

    public void cadastrarMotorista(String nome, String cpf, String habilitacao, String contaBancaria, double saldo, String pix, String cartao) {
            motoristaService.cadastrarMotorista(new Motorista(nome, cpf, habilitacao, new ContaBancaria(contaBancaria, saldo, pix, cartao)));
            contaBancariaService.criarConta(contaBancaria, saldo, pix, cartao);
    }

    public Motorista buscarMotoristaPorNome(String nome) {
        return motoristaService.buscarMotoristaPorNome(nome);
    }

    public void atualizarMotorista(Motorista motorista) {
        motoristaService.atualizarMotorista(motorista);
    }

    public void deletarMotorista(String cpf) {
        motoristaService.removerMotorista(cpf);
    }

    public List<Motorista> listarMotoristas() {
        return motoristaService.listarMotoristas();
    }

    public void cadastrarVeiculo(String tipo, String placa, String marca, String modelo, int qtdDePassageiros, int ano, String cpfMotorista) {
        veiculoService.cadastrarVeiculo(tipo, placa, marca, modelo, qtdDePassageiros, ano, cpfMotorista);
    }

    public PagamentoCredito pagarCorridaComCartao(String nomeCliente, String nomeMotorista, double valor, String chavePixOuCartaoCliente, String chavePixOuCartaoMotorista) {
        return pagamentoCreditoService.pagar(nomeCliente, nomeMotorista, valor, chavePixOuCartaoCliente, chavePixOuCartaoMotorista);
    }

    public PagamentoDinheiro pagarCorridaComDinheiro(String nomeCliente, String nomeMotorista, double valor) {
        return pagamentoDinheiroService.pagar(nomeCliente, nomeMotorista, valor);
    }

    public PagamentoPix pagarCorridaComPix(String nomeCliente, String nomeMotorista, double valor, String chavePixCliente, String chavePixMotorista) {
        return pagamentoPixService.pagarCorrida(nomeCliente, nomeMotorista, valor, chavePixCliente, chavePixMotorista);
    }

    public List<Pagamento> procurarPagamentoPorData(LocalDateTime data) {
        return pagamentoService.findByData(data);
    }

    public List<Pagamento> listarPagamentos() {
        return pagamentoService.findAll();
    }

    public void deletarPagamento(Pagamento pagamento) {
        pagamentoService.delete(pagamento);
    }

    public Viagem chamarViagem(String origem, String destino, double valor, String nomeCliente) {
        return viagemService.chamarViagem(origem, destino, valor, nomeCliente);
    }

    public void iniciarViagem() {
        viagemService.iniciarViagem();
    }

    public Viagem chamarViagemEntrega( String origem, String destino, double valor, String encomenda) {
        return viagemService.chamarViagemEntrega( origem,destino ,valor , encomenda);
    }

    public Viagem finalizarViagem(String destino) {
        return viagemService.finalizarViagem(destino);
    }

    public List<Viagem> listarViagens() {
        return viagemService.listarViagens();
    }

    public void deletarViagem(String destino) {
        viagemService.deleteByDestino(destino);
    }

    public void avaliarMotorista(String nomeMotorista, String comentario, int nota) {
        avaliacaoService.avaliarMotorista(nomeMotorista, comentario, nota);
    }

    public void avaliarCliente(String nomeCliente, String comentario, int nota) {
        avaliacaoService.avaliarCliente(nomeCliente, comentario, nota);
    }

    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoService.listarAvaliacoes();
    }

    public void deletarAvaliacao(Avaliacao avaliacao) {
        avaliacaoService.deletarAvaliacao(avaliacao);
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoService.findAll();
    }

    public Veiculo buscarVeiculoPorPlaca(String placa) {
        return veiculoService.findByPlaca(placa);
    }

    public void deletarVeiculo(String placa) {
        Veiculo veiculo = veiculoService.findByPlaca(placa);
        veiculoService.delete(veiculo);
    }

    public List<ContaBancaria> listarContasBancarias() {
      return contaBancariaService.findAll();
    }

    public ContaBancaria buscarContaPorNumConta(String numeroConta) {
        return contaBancariaService.buscarContaPorNumero(numeroConta);
    }

    public void excluirContaBancaria(String numeroConta) {
        contaBancariaService.deletarConta(numeroConta);
    }
}
