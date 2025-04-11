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
            System.out.println("========= Menu Motorista =========\n"+
                                "1. Cadastrar Motorista.\n"+
//                                "2. Cadastrar Veiculo\n"+
                                "3. Avaliar Viagem.\n"+
                                "0. Voltar ao Menu Principal.\n"+
                               "================================");
            System.out.print("Escolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch(opcao){
                case 1 -> cadastrarMotorista();
//                case 2 -> cadastrarVeiculo();
                case 3 -> new MenuAvaliacao(facade).avaliarViagemDoMotorista();
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
        System.out.print("Digite os numeros (11 digitos) da habilitacao do motorista: ");
        String habilitacao = teclado.nextLine();
        System.out.print("Digite o numero da conta bancaria: ");
        String contaBancaria = teclado.nextLine();
        System.out.print("Digite o saldo inicial da conta bancaria: ");
        double saldo = teclado.nextDouble();
        teclado.nextLine();
        System.out.print("Informe o pix do motorista: ");
        String pix = teclado.nextLine();
        System.out.print("Informe o numero do cartao de credito do motorista: ");
        String numeroCartao = teclado.nextLine();

        facade.cadastrarMotorista(nome, cpf, habilitacao, contaBancaria, saldo, pix, numeroCartao);
        cadastrarVeiculo(nome);
    }

    public void cadastrarVeiculo(String nome) {
        System.out.println("========= Cadastrar Veículo =========");
        System.out.println("Escolha o tipo de veículo:");
        System.out.println("1. Veículo Moto");
        System.out.println("2. Veículo SUV");
        System.out.println("3. Veículo Luxo");
        System.out.println("4. Veículo Econômico");
        System.out.print("Digite a opção: ");
        int tipoVeiculo = teclado.nextInt();
        teclado.nextLine();

        if(tipoVeiculo <1 || tipoVeiculo > 4){
            System.out.println("Opção inválida! Tente novamente.");
            return;
        }

        System.out.print("Digite a placa do veículo: ");
        String placa = teclado.nextLine();
        System.out.print("Digite a marca do veículo: ");
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
        System.out.print("Digite o nome do motorista: ");
        String nomeMotorista = nome;


        switch (tipoVeiculo) {
            case 1 -> facade.cadastrarVeiculo("moto",placa, marca, modelo, qtdDePassageiros, ano, nomeMotorista);
            case 2 -> facade.cadastrarVeiculo("suv",placa, marca, modelo, qtdDePassageiros, ano, nomeMotorista);
            case 3 -> facade.cadastrarVeiculo("luxo",placa, marca, modelo, qtdDePassageiros, ano, nomeMotorista);
            case 4 -> facade.cadastrarVeiculo("economico",placa, marca, modelo, qtdDePassageiros, ano, nomeMotorista);
            default -> System.out.println("Opção inválida! Tente novamente.");
        }
    }


}
