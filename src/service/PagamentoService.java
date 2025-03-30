package service;

import model.Cliente;
import model.Motorista;
import model.Pagamento;

public class PagamentoService {
    private ContaBancariaService contaBancariaService;

    public PagamentoService(){
        this.contaBancariaService = new ContaBancariaService();
    }

    public Pagamento pagarCorrida(Cliente cliente, Motorista motorista, double valor){
        String numeroContaCliente = cliente.getConta().getNumeroConta();
        String numeroContaMotorista = motorista.getConta().getNumeroConta();

        if(contaBancariaService.buscarContaPorNumero(numeroContaCliente).getSaldo() >= valor){
            contaBancariaService.buscarContaPorNumero(numeroContaCliente).sacar(valor);
            contaBancariaService.buscarContaPorNumero(numeroContaMotorista).depositar(valor);
            System.out.println("Pagamento efetuado com sucesso!");

            return new Pagamento(cliente, motorista, valor);
        } else {
            //lancar excecao especifica
            throw new RuntimeException("Saldo insuficiente para efetuar o pagamento");
        }
    }
}
