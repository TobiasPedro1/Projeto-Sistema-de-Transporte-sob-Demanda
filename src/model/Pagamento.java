package model;

public class Pagamento {
    public static void pagarCorrida(Cliente cliente, Motorista motorista, double valor){
        if(cliente.getConta().getSaldo() >= valor){
            cliente.getConta().sacar(valor);
            motorista.getConta().depositar(valor);
            System.out.println("Pagamento efetuado com sucesso!");
        } else {
            //lancar excecao
            System.out.println("Saldo insuficiente para efetuar o pagamento");
        }
    }
}
