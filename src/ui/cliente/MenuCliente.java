package ui.cliente;

import java.util.Scanner;
import service.Facade;

public class MenuCliente {
    private Facade facade;
    private Scanner teclado;

    public MenuCliente (Facade facade){
        this.facade = facade;
        this.teclado = new Scanner(System.in);
    }

    public void exibirMenu(){
        int opcao;
        do{
            System.out.println("========= Menu Cliente ========="+
                                "1. Cadastrar Cliente.\n"+
                                "2. Chamar Viagem.\n"+
                                "3. Chamar Viagem Entrega.\n"+
                    // finalizaar viagem
                    // cadastrar conta
                                "4. Pagar Viagem.\n"+
                                "5. Avaliar Viagem.\n"+
                                "0. Voltar ao Menu Principal.\n"+
                               "================================");
            System.out.print("Escolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch(opcao){
                case 1 -> cadastrarCliente();
                case 2 -> chamarViagem();
                case 3 -> new MenuPagamentoCliente(facade).exibirMenu();
                case 4 -> new MenuAvaliacao(facade).avaliarViagemDoCliente();
                case 0 -> System.out.println("Voltando ao Menu Principal...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }

        }while(opcao != 0);
    }

    public void cadastrarCliente(){
        System.out.println("========= Cadastrar Cliente =========");
        System.out.print("Digite o nome do cliente: ");
        String nome = teclado.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        String cpf = teclado.nextLine();
        System.out.print("Digite o numero da conta bancaria: ");
        String contaBancaria = teclado.nextLine();
        System.out.print("Digite o saldo inicial da conta bancaria: ");
        double saldo = teclado.nextDouble();
        teclado.nextLine();
        facade.cadastrarCliente(nome, cpf, contaBancaria, saldo);
    }

    public void chamarViagem(){
        System.out.println("========= Chamar Viagem =========");
        System.out.println("Informe a origem da viagem: ");
        String origem = teclado.nextLine();
        System.out.print("Digite o destino da viagem: ");
        String destino = teclado.nextLine();
        System.out.print("Digite o valor da viagem: ");
        double valor = teclado.nextDouble();
        teclado.nextLine();
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = teclado.nextLine();

        facade.chamarViagem(origem, destino, valor, nomeCliente);
    }

    public void chamarViagemEntrega(){

        System.out.println("========= Chamar Viagem Entrega =========");
        System.out.print("Digite a encomenda que será transportada: ");
        String encomenda = teclado.nextLine();
        System.out.print("Digite o valor da entrega: ");
        double valor = teclado.nextDouble();
        teclado.nextLine();
        System.out.print("Digite o endereco de origem: ");
        String origem = teclado.nextLine();
        System.out.print("Digite o endereco de destino: ");
        String destino = teclado.nextLine();

        facade.chamarViagemEntrega(valor, origem, destino, encomenda);
    }


}
