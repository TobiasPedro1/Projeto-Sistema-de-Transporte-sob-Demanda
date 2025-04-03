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
                                "3. Pagar Viagem.\n"+
                                "4. Avaliar Viagem.\n"+
                                "0. Voltar ao Menu Principal.\n"+
                               "================================");
            System.out.print("Escolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch(opcao){
                case 1 -> cadastrarCliente();
                case 2 -> chamarViagem();
                case 3 -> pagarViagem();
                case 4 -> avaliarViagem();
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
        facade.cadastrarCliente(nome, cpf, contaBancaria);
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
        if(metodoPagamento.equals("Cartao")|| metodoPagamento.equals("Pix")){
            System.out.println("Informe o Pix ou cartão do cliente:");
            pixOuCartaoCliente = teclado.nextLine();
            System.out.println("Informe o Pix ou cartão do motorista:");
            pixOuCartaoMotorista = teclado.nextLine();
        }

        facade.pagarCorrida(nomeCliente, nomeMotorista ,valor, metodoPagamento, pixOuCartaoCliente, pixOuCartaoMotorista);
    }

    public void avaliarViagem(){
        System.out.println("========= Avaliar Viagem =========");
//        System.out.print("Digite o nome do cliente: ");
//        String nomeCliente = teclado.nextLine();
        System.out.print("Digite o nome do motorista: ");
        String nomeMotorista = teclado.nextLine();
        System.out.print("Digite a nota da viagem (1 a 5): ");
        int nota = teclado.nextInt();
        teclado.nextLine();
        System.out.print("Digite o comentario da viagem: ");
        String comentario = teclado.nextLine();

        facade.avaliarMotorista(nomeMotorista, comentario, nota);
    }

}
