package ui.admin;

import model.ContaBancaria;
import model.Motorista;
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
            System.out.println("6. Gerenciar Viagens");
            System.out.println("7. Gerenciar Contas");
            System.out.println("0. Sair para Menu Pricipal");
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
                case 6 -> gerenciarViagens();
                case 7 -> gerenciarContasBancarias();
                case 0 -> System.out.println("Voltando ao Menu Principal...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void gerenciarMotoristas() {
        System.out.println("========= Gerenciar Motoristas =========");
        int opcao;
        do {
            System.out.println("1. Listar Todos os Motoristas");
            System.out.println("2. Procurar Motorista por Nome");
            System.out.println("3. Deletar Motorista");
            System.out.println("4. Editar Motorista");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1 -> listarMotoristas();
                case 2 -> procurarMotoristaPorNome();
                case 3 -> deletarMotorista();
                case 4 -> editarMotorista();
                case 0 -> System.out.println("Voltando ao menu anterior...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void listarMotoristas() {
        System.out.println("========= Listar Todos os Motoristas =========");
        var motoristas = facade.listarMotoristas();

        // Remover duplicatas, se houver
        var motoristasUnicos = motoristas.stream().distinct().toList();

        if (motoristasUnicos.isEmpty()) {
            System.out.println("Nenhum motorista encontrado.");
        } else {
            motoristasUnicos.forEach(System.out::println);
        }
    }

    private void procurarMotoristaPorNome() {
        System.out.println("========= Procurar Motorista por Nome =========");
        System.out.print("Digite o nome do motorista: ");
        String nome = teclado.nextLine();

        try {
            var motorista = facade.buscarMotoristaPorNome(nome);
            System.out.println("Motorista encontrado: " + motorista);
        } catch (Exception e) {
            System.out.println("Erro ao buscar o motorista: " + e.getMessage());
        }
    }

    private void deletarMotorista() {
        System.out.println("========= Deletar Motorista =========");
        System.out.print("Digite o CPF do motorista a ser deletado: ");
        String cpf = teclado.nextLine();

        try {
            facade.deletarMotorista(cpf);
//            System.out.println("Motorista deletado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao deletar o motorista: " + e.getMessage());
        }
    }

    private void editarMotorista() {
        System.out.println("========= Editar Motorista =========");
        System.out.print("Digite o nome do motorista a ser editado: ");
        String nome = teclado.nextLine();

        Motorista motorista = facade.buscarMotoristaPorNome(nome);
        if (motorista != null) {
            System.out.print("Digite o novo nome (ou pressione Enter para manter o atual): ");
            String novoNome = teclado.nextLine();
            if (!novoNome.isEmpty()) {
                motorista.setNome(novoNome);
            }

            System.out.print("Digite o novo CPF (ou pressione Enter para manter o atual): ");
            String novoCpf = teclado.nextLine();
            if (!novoCpf.isEmpty()) {
                motorista.setCpf(novoCpf);
            }
            System.out.print("Digite a nova habilitação (ou pressione Enter para manter a atual): ");
            String novaHabilitacao = teclado.nextLine();
            if (!novaHabilitacao.isEmpty()) {
                motorista.setHabilitacao(novaHabilitacao);
            }
            facade.atualizarMotorista(motorista);
            System.out.println("Motorista atualizado com sucesso!");
        } else {
            System.out.println("Motorista não encontrado.");
        }
    }


    private void gerenciarClientes() {
        System.out.println("========= Gerenciar Clientes =========");
        int opcao;
        do {
            System.out.println("1. Listar Todos os Clientes");
            System.out.println("2. Procurar Cliente por Nome");
            System.out.println("3. Deletar Cliente");
            System.out.println("4. Editar Cliente");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1 -> listarClientes();
                case 2 -> procurarClientePorNome();
                case 3 -> deletarCliente();
                case 4 -> editarCliente();
                case 0 -> System.out.println("Voltando ao menu anterior...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void listarClientes() {
        System.out.println("========= Listar Todos os Clientes =========");
        var clientes = facade.listarClientes();

        var clientesUnicos = clientes.stream().distinct().toList();

        if (clientesUnicos.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
        } else {
            clientesUnicos.forEach(System.out::println);
        }
    }
    private void procurarClientePorNome() {
        System.out.println("========= Procurar Cliente por Nome =========");
        System.out.print("Digite o nome do cliente: ");
        String nome = teclado.nextLine();

        try {
            var cliente = facade.buscarClientePorNome(nome);
            System.out.println("Cliente encontrado: " + cliente);
        } catch (Exception e) {
            System.out.println("Erro ao buscar o cliente: " + e.getMessage());
        }
    }

    private void deletarCliente() {
        System.out.println("========= Deletar Cliente =========");
        System.out.print("Digite o CPF do cliente a ser deletado: ");
        String cpf = teclado.nextLine();

        try {
            facade.deletarCliente(cpf);
            System.out.println("Cliente deletado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao deletar o cliente: " + e.getMessage());
        }
    }

    private void editarCliente() {
        System.out.println("========= Editar Cliente =========");
        System.out.print("Digite o nome do cliente a ser editado: ");
        String nome = teclado.nextLine();

        var cliente = facade.buscarClientePorNome(nome);
        if (cliente != null) {
            System.out.print("Digite o novo nome (ou pressione Enter para manter o atual): ");
            String novoNome = teclado.nextLine();
            if (!novoNome.isEmpty()) {
                cliente.setNome(novoNome);
            }

            System.out.print("Digite o novo CPF (ou pressione Enter para manter o atual): ");
            String novoCpf = teclado.nextLine();
            if (!novoCpf.isEmpty()) {
                cliente.setCpf(novoCpf);
            }

            facade.atualizarCliente(cliente);
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void gerenciarVeiculos() {
        System.out.println("========= Gerenciar Veículos =========");

        int opcao;
        do {
            System.out.println("1. Listar Veículos");
            System.out.println("2. Listar Veículo por Placa");
            System.out.println("3. Remover Veículo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1 -> listarVeiculos();
                case 2 -> listarVeiculoPorPlaca();
                case 3 -> removerVeiculo();
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

    private void listarVeiculoPorPlaca(){
        System.out.println("========= Listar Veículo por Placa =========");
        System.out.print("Digite a placa do veículo: ");
        String placa = teclado.nextLine();

        try {
            var veiculo = facade.buscarVeiculoPorPlaca(placa);
            System.out.println("Veículo encontrado: " + veiculo);
        } catch (Exception e) {
            System.out.println("Erro ao buscar o veículo: " + e.getMessage());
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

    private void gerenciarViagens() {
        System.out.println("========= Gerenciar Viagens =========");
        int opcao;
        do {
            System.out.println("1. Listar Viagens");
            System.out.println("2. Cancelar Viagem");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1 -> listarViagens();
                case 2 -> cancelarViagem();
                case 0 -> System.out.println("Voltando ao menu anterior...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

   private void listarViagens() {
        System.out.println("========= Listar Viagens =========");
        var viagens = facade.listarViagens();

        // Remover duplicatas, se houver
        var viagensUnicas = viagens.stream().distinct().toList();

        if (viagensUnicas.isEmpty()) {
            System.out.println("Nenhuma viagem encontrada.");
        } else {
            viagensUnicas.forEach(System.out::println);
        }
    }

    private void cancelarViagem() {
        System.out.println("========= Cancelar Viagem =========");
        System.out.print("Digite o Destino da viagem a ser cancelada: ");
        String destino = teclado.nextLine();

        try {
            facade.deletarViagem(destino);
            System.out.println("Viagem cancelada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cancelar a viagem: " + e.getMessage());
        }
    }

    private void gerenciarContasBancarias() {
        System.out.println("========= Gerenciar Contas Bancárias =========");
        int opcao;
        do {
            System.out.println("1. Listar Contas Bancárias");
            System.out.println("2. Atualizar Saldo");
            System.out.println("3. Buscar Conta por Número");
            System.out.println("4. excluir Conta Bancária");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1 -> listarContasBancarias();
                case 2 -> atualizarSaldo();
                case 3 -> buscarContaPorNumero();
                case 4 -> excluirContaBancaria();
                case 0 -> System.out.println("Voltando ao menu anterior...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void listarContasBancarias() {
        System.out.println("========= Listar Contas Bancárias =========");
        var contas = facade.listarContasBancarias();
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta bancária encontrada.");
        } else {
            contas.forEach(System.out::println);
        }
    }

    private void buscarContaPorNumero() {
        System.out.println("========= Buscar Conta por Número =========");
        System.out.print("Digite o número da conta bancária: ");
        String numeroConta = teclado.nextLine();

        try {
            var conta = facade.buscarContaPorNumConta(numeroConta);
            System.out.println("Conta encontrada: " + conta);
        } catch (Exception e) {
            System.out.println("Erro ao buscar a conta: " + e.getMessage());
        }
    }

    private void atualizarSaldo() {
        System.out.println("========= Atualizar Saldo =========");
        System.out.print("Digite o número da conta bancária: ");
        String numeroConta = teclado.nextLine();
        System.out.print("Digite o novo saldo: ");
        double novoSaldo = teclado.nextDouble();
        teclado.nextLine();

        try {
            ContaBancaria contaBancaria = facade.buscarContaPorNumConta(numeroConta);
            contaBancaria.setSaldo(novoSaldo);
//            facade.depositarContaBancaria(numeroConta, novoSaldo);
            System.out.println("Saldo atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o saldo: " + e.getMessage());
        }
    }

    private void excluirContaBancaria() {
        System.out.println("========= Excluir Conta Bancária =========");
        System.out.print("Digite o número da conta bancária a ser excluída: ");
        String numeroConta = teclado.nextLine();

        try {
            facade.excluirContaBancaria(numeroConta);
            System.out.println("Conta bancária excluída com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir a conta bancária: " + e.getMessage());
        }
    }
}