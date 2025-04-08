package ui.admin;

import ui.cliente.*;
import java.util.Scanner;
import service.Facade;

public class MenuAdm {
    private Facade facade;
    private Scanner teclado;

    public MenuAdm(Facade facade) {
        this.facade = facade;
        this.teclado = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("========= Menu Administrador =========");
            System.out.println("1. Gerenciar Motoristas");
            System.out.println("2. Gerenciar Clientes");
            System.out.println("3. Gerenciar Veículos");
            System.out.println("4. Gerenciar Pagamentos");
            System.out.println("5. Gerenciar Avaliações");
            System.out.println("0. Sair");
            System.out.println("======================================");
            System.out.print("Escolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1 -> gerenciarMotoristas();
                case 2 -> gerenciarClientes();
                case 3 -> gerenciarVeiculos();
                case 4 -> gerenciarPagamentos();
                case 5 -> gerenciarAvaliacoes();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void gerenciarMotoristas() {
        System.out.println("========= Gerenciar Motoristas =========");
        MenuMotorista menuMotorista = new MenuMotorista(facade);
        menuMotorista.exibirMenu();
    }

    private void gerenciarClientes() {
        System.out.println("========= Gerenciar Clientes =========");
        MenuCliente menuCliente = new MenuCliente(facade);
        menuCliente.exibirMenu();
    }

    private void gerenciarVeiculos() {
        System.out.println("========= Gerenciar Veículos =========");
        System.out.print("Digite o CPF do motorista para gerenciar os veículos: ");
        String cpfMotorista = teclado.nextLine();

        int opcao;
        do {
            System.out.println("1. Listar Veículos");
            System.out.println("2. Remover Veículo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1 -> listarVeiculos();
                case 2 -> removerVeiculo();
                case 0 -> System.out.println("Voltando ao menu anterior...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void listarVeiculos() {
        System.out.println("========= Listar Todos os Veículos =========");
        var veiculos = facade.listarVeiculos();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo encontrado.");
        } else {
            veiculos.forEach(System.out::println);
        }
    }

    private void removerVeiculo() {
        System.out.println("========= Remover Veículo =========");
        System.out.print("Digite a placa do veículo a ser removido: ");
        String placa = teclado.nextLine();

        try {
            facade.deletarVeiculo(placa);
            System.out.println("Veículo removido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover o veículo: " + e.getMessage());
        }
    }

    private void gerenciarPagamentos() {
        System.out.println("========= Gerenciar Pagamentos =========");
        int opcao;
        do {
            System.out.println("1. Listar Pagamentos");
            System.out.println("2. Buscar Pagamento por Data");
            System.out.println("3. Deletar Pagamento");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1 -> new MenuPagamentoCliente(facade).listarPagamentos();
                case 2 -> new MenuPagamentoCliente(facade).buscarPagamentoPorData();
                case 3 -> new MenuPagamentoCliente(facade).deletarPagamento();
                case 0 -> System.out.println("Voltando ao menu anterior...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void gerenciarAvaliacoes() {
        System.out.println("========= Gerenciar Avaliações =========");
        int opcao;
        do {
            System.out.println("1. Listar Avaliações");
            System.out.println("2. Deletar Avaliação");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1 -> new MenuAvaliacao(facade).listarAvaliacoes();
                case 2 -> new MenuAvaliacao(facade).deletarAvaliacao();
                case 0 -> System.out.println("Voltando ao menu anterior...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }
}