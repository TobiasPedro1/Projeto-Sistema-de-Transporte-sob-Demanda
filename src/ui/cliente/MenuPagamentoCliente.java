package ui.cliente;

import java.util.Scanner;
import service.Facade;

public class MenuPagamentoCliente {
    Facade facade;
    Scanner teclado;

    public MenuPagamentoCliente(Facade facade){
        this.facade = facade;
        this.teclado = new Scanner(System.in);
    }

    public void exibirMenu(){
        int opcao;
        do{
            System.out.println("========= Menu Pagamento ========="+
                                "1. Pagar Viagem.\n"+
                                "2. Pagar Entrega.\n"+
                                "0. Voltar ao Menu Principal.\n"+
                               "================================");
            System.out.print("Escolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch(opcao){
                case 1 -> pagarViagem();
                case 2 -> pagarEntrega();
                case 0 -> System.out.println("Voltando ao Menu Principal...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }

        }while(opcao != 0);
    }

    public void pagarViagem(){
        System.out.println("========= Pagar Viagem =========");
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = teclado.nextLine();
        System.out.println("digite o nome do motorista: ");
        String nomeMotorista = teclado.nextLine();
        System.out.print("Digite o valor da viagem: ");
        double valor = teclado.nextDouble();
        System.out.println("Informe o metodo de pagamento: Pix, Cartao ou Dinheiro");
        String metodoPagamento = teclado.nextLine().toLowerCase();
        String pixOuCartaoCliente = "";
        String pixOuCartaoMotorista = "";
        if(metodoPagamento.equals("cartao")){
            System.out.println("Informe o Cartão do cliente:");
            pixOuCartaoCliente = teclado.nextLine();
            System.out.println("Informe o Cartão ou cartão do motorista:");
            pixOuCartaoMotorista = teclado.nextLine();
            facade.pagarCorridaComCartao(nomeCliente, nomeMotorista, valor, pixOuCartaoCliente, pixOuCartaoMotorista);
        } else if (metodoPagamento.equals("pix")) {
            System.out.println("Informe o Pix do cliente:");
            pixOuCartaoCliente = teclado.nextLine();
            System.out.println("Informe o Pix do motorista:");
            pixOuCartaoMotorista = teclado.nextLine();
            facade.pagarCorridaComPix(nomeCliente, nomeMotorista, valor, pixOuCartaoCliente, pixOuCartaoMotorista);
        }else {
            facade.pagarCorridaComDinheiro(nomeCliente, nomeMotorista ,valor);
        }
    }

    public void pagarEntrega(){
        System.out.println("========= Pagar Viagem =========");
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = teclado.nextLine();
        System.out.println("digite o nome do motorista: ");
        String nomeMotorista = teclado.nextLine();
        System.out.print("Digite o valor da viagem: ");
        double valor = teclado.nextDouble();
        System.out.println("Informe o metodo de pagamento: Pix, Cartao ou Dinheiro");
        String metodoPagamento = teclado.nextLine().toLowerCase();
        String pixOuCartaoCliente = "";
        String pixOuCartaoMotorista = "";
        if(metodoPagamento.equals("cartao")){
            System.out.println("Informe o Cartão do cliente:");
            pixOuCartaoCliente = teclado.nextLine();
            System.out.println("Informe o Cartão ou cartão do motorista:");
            pixOuCartaoMotorista = teclado.nextLine();
            facade.pagarCorridaComCartao(nomeCliente, nomeMotorista, valor, pixOuCartaoCliente, pixOuCartaoMotorista);
        } else if (metodoPagamento.equals("pix")) {
            System.out.println("Informe o Pix do cliente:");
            pixOuCartaoCliente = teclado.nextLine();
            System.out.println("Informe o Pix do motorista:");
            pixOuCartaoMotorista = teclado.nextLine();
            facade.pagarCorridaComPix(nomeCliente, nomeMotorista, valor, pixOuCartaoCliente, pixOuCartaoMotorista);
        }else {
            facade.pagarCorridaComDinheiro(nomeCliente, nomeMotorista ,valor);
        }
    }

}
