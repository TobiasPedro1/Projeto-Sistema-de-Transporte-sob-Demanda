package service;

import model.Cliente;
import model.Motorista;
import model.Pagamento;
import model.enums.FormaDePagamento;

public class PagamentoService {
    public Pagamento pagarCorrida(Cliente cliente, Motorista motorista, double valor, FormaDePagamento formaDePagamento){
        if(cliente.getConta().getSaldo() >= valor){
            cliente.getConta().sacar(valor);
            motorista.getConta().depositar(valor);
            System.out.println("Pagamento efetuado com sucesso!");

            return new Pagamento(cliente, motorista, valor, formaDePagamento );
        } else {
            //lancar excecao especifica
            throw new RuntimeException("Saldo insuficiente para efetuar o pagamento");
        }
    }
}
