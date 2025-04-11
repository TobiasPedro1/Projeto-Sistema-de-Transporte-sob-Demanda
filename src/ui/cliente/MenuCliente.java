package ui.cliente;

import java.util.Scanner;

import model.Cliente;
import model.Motorista;
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
            System.out.println("========= Menu Cliente =========\n"+
                                "1. Cadastrar Cliente.\n"+
                                "2. Chamar Viagem.\n"+
                                "3. Chamar Viagem Entrega.\n"+
                    // finalizaar viagem
                    // cadastrar conta
//                                "4. Pagar Viagem.\n"+
//                                "5. Avaliar Viagem.\n"+
                                "0. Voltar ao Menu Principal.\n"+
                               "================================");
            System.out.print("Escolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch(opcao){
                case 1 -> cadastrarCliente();
                case 2 -> chamarViagem();
                case 3 -> chamarViagemEntrega();
//                case 4 -> new MenuPagamentoCliente(facade).exibirMenu();
//                case 5 -> new MenuAvaliacao(facade).avaliarViagemDoCliente();
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
        System.out.print("Informe o pix do cliente: ");
        String pix = teclado.nextLine();
        System.out.print("Informe o numero do cartao de credito: ");
        String numeroCartao = teclado.nextLine();
        
        facade.cadastrarCliente(nome, cpf, contaBancaria, saldo, pix, numeroCartao);
    }

    public void chamarViagem(){
        try {
            System.out.println("========= Chamar Viagem =========");
            System.out.println("Informe o endereço de origem da viagem: ");
            String origem = teclado.nextLine();
            System.out.print("Informe o endereço de destino da viagem: ");
            String destino = teclado.nextLine();
            System.out.print("Informe o valor da viagem: ");
            double valor = teclado.nextDouble();
            teclado.nextLine();
            System.out.print("Informe o nome do cliente: ");
            String nomeCliente = teclado.nextLine();

            Motorista motorista = facade.chamarViagem(origem, destino, valor, nomeCliente).getMotorista();
            Cliente cliente = facade.buscarClientePorNome(nomeCliente);

            Thread.sleep(1000);
            facade.iniciarViagem();
            Thread.sleep(1000);
            facade.finalizarViagem(destino);
            Thread.sleep(1000);

            new MenuPagamentoCliente(facade).pagarViagem(nomeCliente, motorista.getNome(), valor, motorista.getConta().getChavePix(),
                    motorista.getConta().getCartaoCredito(), cliente.getConta().getChavePix(), cliente.getConta().getCartaoCredito() );
            // passar os valores por parametro
            new MenuAvaliacao(facade).avaliarViagemDoCliente(motorista.getNome());

        } catch (InterruptedException e) {
            System.out.println("A execução foi interrompida: " + e.getMessage());
        }

    }

    public void chamarViagemEntrega(){
        try {
            System.out.println("========= Chamar Viagem Entrega =========");
            System.out.print("Informe o nome do cliente: ");
            String nomeCliente = teclado.nextLine();
            System.out.print("Informe a encomenda que será transportada: ");
            String encomenda = teclado.nextLine();
            System.out.print("Informe o valor da Viagem de Entrega: ");
            double valor = teclado.nextDouble();
            teclado.nextLine();
            System.out.print("Digite o endereco de origem: ");
            String origem = teclado.nextLine();
            System.out.print("Digite o endereco de destino: ");
            String destino = teclado.nextLine();

            Motorista motorista = facade.chamarViagemEntrega(origem, destino, valor, encomenda).getMotorista();
            Cliente cliente = facade.buscarClientePorNome(nomeCliente);

            Thread.sleep(1000);
            facade.iniciarViagem();
            Thread.sleep(1000);
            facade.finalizarViagem(destino);
            Thread.sleep(1000);

            new MenuPagamentoCliente(facade).pagarEntrega(nomeCliente, motorista.getNome(), valor, motorista.getConta().getChavePix(),
                    motorista.getConta().getCartaoCredito(), cliente.getConta().getChavePix(), cliente.getConta().getCartaoCredito() );
            new MenuAvaliacao(facade).avaliarViagemDoCliente(motorista.getNome());
        } catch (InterruptedException e) {
            System.out.println("A execução foi interrompida: " + e.getMessage());
        }
//        facade.chamarViagemEntrega( origem, destino, valor, encomenda);
    }


}
