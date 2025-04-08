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
        // Implementar lógica para listar, adicionar ou remover veículos associados ao motorista
        System.out.println("Funcionalidade de gerenciamento de veículos ainda não implementada.");
    }

    private void gerenciarPagamentos() {
        System.out.println("========= Gerenciar Pagamentos =========");
        MenuPagamentoCliente menuPagamentoCliente = new MenuPagamentoCliente(facade);
        menuPagamentoCliente.exibirMenu();
    }
}