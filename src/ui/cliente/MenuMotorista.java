package ui.cliente;

import java.util.Scanner;
import service.Facade;

public class MenuMotorista {
    private Scanner teclado;
    private Facade facade;

    public MenuMotorista(Facade facade){
        this.teclado = new Scanner(System.in);
        this.facade = facade;
    }

    public void exibirMenu(){
        int opcao;
        do{
            System.out.println("========= Menu Motorista ========="+
                                "1. Cadastrar Motorista.\n"+
                                "2. Cadastrar Veiculo\n"+
                                "3. Avaliar Viagem.\n"+
                                "0. Voltar ao Menu Principal.\n"+
                               "================================");
            System.out.print("Escolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch(opcao){
                case 1 -> cadastrarMotorista();
                case 2 -> cadastrarVeiculo();
                case 3 -> avaliarViagemMotorista();
                case 0 -> System.out.println("Voltando ao Menu Principal...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }

        }while(opcao != 0);
    }

    public void cadastrarMotorista(){
        System.out.println("========= Cadastrar Motorista =========");
        System.out.print("Digite o nome do motorista: ");
        String nome = teclado.nextLine();
        System.out.print("Digite o CPF do motorista: ");
        String cpf = teclado.nextLine();
        System.out.println("Digite o numero da habilitacao do motorista: ");
        String habilitacao = teclado.nextLine();
        System.out.print("Digite o numero da conta bancaria: ");
        String contaBancaria = teclado.nextLine();
        System.out.print("Digite o saldo inicial da conta bancaria: ");
        double saldo = teclado.nextDouble();
        teclado.nextLine();
        facade.cadastrarMotorista(nome, cpf, habilitacao, contaBancaria, saldo);
    }

    public void cadastrarVeiculo() {
        System.out.println("========= Cadastrar Veículo =========");
        System.out.println("Escolha o tipo de veículo:");
        System.out.println("1. Veículo Moto");
        System.out.println("2. Veículo SUV");
        System.out.println("3. Veículo Luxo");
        System.out.println("4. Veículo Econômico");
        System.out.print("Digite a opção: ");
        int tipoVeiculo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Digite a placa do veículo: ");
        String placa = teclado.nextLine();
        System.out.println("Digite a marca do veículo: ");
        String marca = teclado.nextLine();
        System.out.print("Digite o modelo do veículo: ");
        String modelo = teclado.nextLine();
        System.out.print("Digite a cor do veículo: ");
        String cor = teclado.nextLine();
        System.out.print("Digite o ano do veículo: ");
        int ano = teclado.nextInt();
        teclado.nextLine();
        System.out.print("Informe a quantidade máxima de passageiros: ");
        int qtdDePassageiros = teclado.nextInt();
        teclado.nextLine();
        System.out.print("Digite o CPF do motorista: ");
        String cpfMotorista = teclado.nextLine();


        switch (tipoVeiculo) {
            case 1 -> facade.cadastrarVeiculo("moto",placa, marca, modelo, qtdDePassageiros, ano, cpfMotorista);
            case 2 -> facade.cadastrarVeiculo("suv",placa, marca, modelo, qtdDePassageiros, ano, cpfMotorista);
            case 3 -> facade.cadastrarVeiculo("luxo",placa, marca, modelo, qtdDePassageiros, ano, cpfMotorista);
            case 4 -> facade.cadastrarVeiculo("economico",placa, marca, modelo, qtdDePassageiros, ano, cpfMotorista);
            default -> System.out.println("Opção inválida! Tente novamente.");
        }
    }

    public void avaliarViagemMotorista(){
        System.out.println("========= Avaliar Viagem =========");
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = teclado.nextLine();
        System.out.print("Digite a nota do motorista (1 a 5): ");
        int nota = teclado.nextInt();
        teclado.nextLine();
        System.out.print("Digite o comentario da viagem: ");
        String comentario = teclado.nextLine();

        facade.avaliarCliente(nomeCliente, comentario, nota);
    }
}
