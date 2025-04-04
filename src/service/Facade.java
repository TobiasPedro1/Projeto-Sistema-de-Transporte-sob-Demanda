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

        this.clienteService = new ClienteService(clienteRepository);
        this.motoristaService = new MotoristaService(motoristaRepository);
        this.veiculoService = new VeiculoService(veiculoRepository, veiculoSuvRepository, veiculoEconomicoRepository, veiculoMotoRepository, veiculoLuxoRepository);
        this.viagemService = new ViagemService(clienteRepository, motoristaRepository, motoristaService, viagemRepository);
        this.veiculoSuvService = new VeiculoSuvService(veiculoSuvRepository, viagemService, viagemRepository);
        this.veiculoEconomicoService = new VeiculoEconomicoService(veiculoEconomicoRepository, viagemService, viagemRepository);
        this.veiculoMotoService = new VeiculoMotoService(veiculoMotoRepository, viagemService, viagemRepository);
        this.veiculoLuxoService = new VeiculoLuxoService(veiculoLuxoRepository, viagemService, viagemRepository);
        this.avaliacaoService = new AvaliacaoService(avaliacaoRepository,  motoristaRepository,  clienteRepository,  motoristaService, clienteService);
        this.contaBancariaService = new ContaBancariaService(contaBancoRepository);
        this.pagamentoCreditoService = new PagamentoCreditoService(contaBancariaService, pagamentoCreditoRepository);
        this.pagamentoDinheiroService = new PagamentoDinheiroService(contaBancariaService, pagamentoDinheiroRepository);
        this.pagamentoPixService = new PagamentoPixService(contaBancariaService,pagamentoPixRepository);
        this.pagamentoService = new PagamentoService(contaBancariaService, pagamentoRepository);

    }

    public void cadastrarCliente(String nome, String cpf, String numConta) {
        clienteService.cadastrarCliente(new Cliente(nome, cpf, new ContaBancaria(numConta)));
    }

    public boolean validarCliente(String cpf) {
        return clienteService.validarCliente(clienteService.buscarClientePorCpf(cpf));
    }

    public void cadastrarMotorista(String nome, String cpf, String habilitacao, String contaBancaria){
            motoristaService.cadastrarMotorista(new Motorista(nome, cpf, habilitacao, new ContaBancaria(contaBancaria)));
    }

    public boolean validarMotorista(String cpf) {
        return motoristaService.validarMotorista(motoristaService.buscarMotoristaPorCpf(cpf));
    }

    public Motorista selecionarMotoristaAleatorio() {
        return motoristaService.selecionarMotoristaAleatorio();
    }

    public void cadastrarVeiculo(String tipo, String placa, String marca, String modelo, int qtdDePassageiros, int ano, String cpfMotorista) {
        veiculoService.cadastrarVeiculo(tipo, placa, marca, modelo, qtdDePassageiros, ano, cpfMotorista);
    }

/*
    public void cadastrarVeiculoEconomico(String placa, String marca, String modelo, int qtdDePassageiros, int ano, String cpfMotorista) {
        MotoristaRepository motoristaRepository = new MotoristaRepository();
        Motorista motorista = motoristaRepository.motoristaFindByCpf(cpfMotorista);
        VeiculoEconomico veiculoEconomico = new VeiculoEconomico(placa, marca, modelo, qtdDePassageiros, ano);
        motorista.setVeiculo(veiculoEconomico);
        veiculoService.save(veiculoEconomico);
        veiculoEconomicoService.save(veiculoEconomico);
    }

    public void cadastrarVeiculoSuv(String placa, String marca, String modelo, int qtdDePassageiros, int ano, String cpfMotorista) {
        MotoristaRepository motoristaRepository = new MotoristaRepository();
        Motorista motorista = motoristaRepository.motoristaFindByCpf(cpfMotorista);
        VeiculoSuv veiculoSuv = new VeiculoSuv(placa, marca, modelo, qtdDePassageiros, ano);
        motorista.setVeiculo(veiculoSuv);
        veiculoService.save(veiculoSuv);
        veiculoSuvService.save(veiculoSuv);
    }

    public void cadastrarVeiculoMoto(String placa, String marca, String modelo, int qtdDePassageiros, int ano, String cpfMotorista) {
        MotoristaRepository motoristaRepository = new MotoristaRepository();
        Motorista motorista = motoristaRepository.motoristaFindByCpf(cpfMotorista);
        VeiculoMoto veiculoMoto = new VeiculoMoto(placa, marca, modelo, qtdDePassageiros, ano);
        motorista.setVeiculo(veiculoMoto);
        veiculoService.save(veiculoMoto);
        veiculoMotoService.save(veiculoMoto);
    }

    public void cadastrarVeiculoLuxo(String placa, String marca, String modelo, int qtdDePassageiros, int ano, String cpfMotorista) {
        MotoristaRepository motoristaRepository = new MotoristaRepository();
        Motorista motorista = motoristaRepository.motoristaFindByCpf(cpfMotorista);
        VeiculoLuxo veiculoLuxo = new VeiculoLuxo(placa, marca, modelo, qtdDePassageiros, ano);
        motorista.setVeiculo(veiculoLuxo);
        veiculoService.save(veiculoLuxo);
        veiculoLuxoService.save(veiculoLuxo);
    }*/

    // VER COMO VAI FICAR NO MENU DO CLIENTE (ALTERA PARA INDIVIDUAL OU NÃO)
//    public Pagamento pagarCorrida(String cliente, String motorista, double valor, String metodoPagamento, String chavePixOuCartaoCliente, String chavePixOuCartaoMotorista) {
//        Cliente clienteobj = clienteService.buscarClientePorNome(cliente);
//        Motorista motoristaobj = motoristaService.buscarMotoristaPorNome(motorista);
//        return switch (metodoPagamento.toLowerCase()) {
//            case "pix" -> pagamentoPixService.pagarCorrida(clienteobj, motoristaobj, valor, chavePixOuCartaoCliente, chavePixOuCartaoMotorista);
//            case "cartao" -> pagamentoCreditoService.pagar(clienteobj, motoristaobj, valor, chavePixOuCartaoCliente, chavePixOuCartaoMotorista);
//            case "dinheiro" -> pagamentoDinheiroService.pagar(clienteobj, motoristaobj, valor);
//            default -> throw new IllegalArgumentException("Método de pagamento inválido.");
//        };
//    }

    public PagamentoCredito pagarCorridaComCartao(String nomeCliente, String nomeMotorista, double valor, String chavePixOuCartaoCliente, String chavePixOuCartaoMotorista) {
        return pagamentoCreditoService.pagar(nomeCliente, nomeMotorista, valor, chavePixOuCartaoCliente, chavePixOuCartaoMotorista);
    }

    public PagamentoCredito procurarPagamentoCreditoPorData(LocalDateTime data) {
        return pagamentoCreditoService.findByData(data);
    }

    public List<PagamentoCredito> listarPagamentosCredito() {
        return pagamentoCreditoService.findAll();
    }

    public void deletarPagamentoCredito(PagamentoCredito pagamento) {
        pagamentoCreditoService.delete(pagamento);
    }

    public PagamentoDinheiro pagarCorridaComDinheiro(String nomeCliente, String nomeMotorista, double valor) {
        return pagamentoDinheiroService.pagar(nomeCliente, nomeMotorista, valor);
    }

    public PagamentoDinheiro procurarPagamentoDinheiroPorData(LocalDateTime data) {
        return pagamentoDinheiroService.findByData(data);
    }

    public List<PagamentoDinheiro> listarPagamentosDinheiro() {
        return pagamentoDinheiroService.findAll();
    }

    public void deletarPagamentoDinheiro(PagamentoDinheiro pagamento) {
        pagamentoDinheiroService.delete(pagamento);
    }

    public PagamentoPix pagarCorridaComPix(String nomeCliente, String nomeMotorista, double valor, String chavePixCliente, String chavePixMotorista) {
        return pagamentoPixService.pagarCorrida(nomeCliente, nomeMotorista, valor, chavePixCliente, chavePixMotorista);
    }

    public PagamentoPix procurarPagamentoPixPorChave(String chavePix) {
        return pagamentoPixService.findByChavePix(chavePix);
    }

    public List<PagamentoPix> listarPagamentosPix() {
        return pagamentoPixService.findAll();
    }

    public void deletarPagamentoPix(PagamentoPix pagamento) {
        pagamentoPixService.delete(pagamento);
    }

    public Pagamento pagarCorrida(Cliente cliente, Motorista motorista, double valor) {
        return pagamentoService.pagarCorrida(cliente, motorista, valor);
    }

    public Pagamento procurarPagamentoPorData(LocalDateTime data) {
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

    public Viagem chamarViagemEntrega( double valor, String origem, String destino, String encomenda) {
        return viagemService.chamarViagemEntrega( origem,destino ,valor , encomenda);
    }

    public Viagem finalizarViagem(Viagem viagem) {
        return viagemService.finalizarViagem(viagem);
    }

    public Viagem procurarViagemPorDestino(String destino) {
        return viagemService.buscarViagemPorDestino(destino);
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
}
