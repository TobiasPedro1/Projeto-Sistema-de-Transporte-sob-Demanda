package ui.cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import exceptions.SalvaFalhaException;
import model.Pagamento;
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
        do {
            System.out.println("========= Menu Pagamento =========");
            System.out.println("1. Pagar Viagem.");
            System.out.println("2. Pagar Entrega.");
//            System.out.println("3. Listar Pagamentos.");
//            System.out.println("4. Buscar Pagamento por Data.");
//            System.out.println("5. Deletar Pagamento.");
            System.out.println("0. Voltar ao Menu Principal.");
            System.out.println("==================================");
            System.out.print("Escolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
//                case 1 -> pagarViagem();
//                case 2 -> pagarEntrega();
//                case 3 -> listarPagamentos();
//                case 4 -> buscarPagamentoPorData();
//                case 5 -> deletarPagamento();
                case 0 -> System.out.println("Voltando ao Menu Principal...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    //receber valores por parametros
    public void pagarViagem(String nomeClienteEntrada, String nomeMotoristaEntrada, double valorEntrada,
                            String pixMotorista, String cartaoMotorista, String pixCliente, String cartaoCliente){
        System.out.println("========= Pagar Viagem =========");
//        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = nomeClienteEntrada;
//        System.out.print("digite o nome do motorista: ");
        String nomeMotorista = nomeMotoristaEntrada;
//        System.out.print("Confirme o valor da viagem: ");
        double valor = valorEntrada;
//        teclado.nextLine();
        System.out.print("Informe o metodo de pagamento: Pix, Cartao ou Dinheiro: ");
        String metodoPagamento = teclado.nextLine().toLowerCase();
        String pixOuCartaoCliente = "";
        String pixOuCartaoMotorista = "";
        if(metodoPagamento.equals("cartao")){
//            System.out.print("Informe o Cartão do cliente: ");
            pixOuCartaoCliente = cartaoCliente;
//            System.out.print("Informe o Cartão do motorista: ");
            pixOuCartaoMotorista = cartaoMotorista;
            facade.pagarCorridaComCartao(nomeCliente, nomeMotorista, valor, pixOuCartaoCliente, pixOuCartaoMotorista);
        } else if (metodoPagamento.equals("pix")) {
//            System.out.print("Informe o Pix do cliente: ");
            pixOuCartaoCliente = pixCliente;
//            System.out.print("Informe o Pix do motorista: ");
            pixOuCartaoMotorista = pixMotorista;
            facade.pagarCorridaComPix(nomeCliente, nomeMotorista, valor, pixOuCartaoCliente, pixOuCartaoMotorista);
        }else {
            facade.pagarCorridaComDinheiro(nomeCliente, nomeMotorista ,valor);
        }
    }

    public void pagarEntrega(String nomeClienteEntrada, String nomeMotoristaEntrada, double valorEntrada,
                             String pixMotorista, String cartaoMotorista, String pixCliente, String cartaoCliente){
        System.out.println("========= Pagar Viagem Entrga =========");
//        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = nomeClienteEntrada;
//        System.out.println("digite o nome do motorista: ");
        String nomeMotorista = nomeMotoristaEntrada;
//        System.out.print("Digite o valor da viagem: ");
        double valor = valorEntrada;
        System.out.println("Informe o metodo de pagamento: Pix, Cartao ou Dinheiro");
        String metodoPagamento = teclado.nextLine().toLowerCase();
        String pixOuCartaoCliente = "";
        String pixOuCartaoMotorista = "";
        if(metodoPagamento.equals("cartao")){
//            System.out.println("Informe o Cartão do cliente:");
            pixOuCartaoCliente = cartaoCliente;
//            System.out.println("Informe o Cartão do motorista:");
            pixOuCartaoMotorista = cartaoMotorista;
            facade.pagarCorridaComCartao(nomeCliente, nomeMotorista, valor, pixOuCartaoCliente, pixOuCartaoMotorista);
        } else if (metodoPagamento.equals("pix")) {
//            System.out.println("Informe o Pix do cliente:");
            pixOuCartaoCliente = pixCliente;
//            System.out.println("Informe o Pix do motorista:");
            pixOuCartaoMotorista = pixMotorista;
            facade.pagarCorridaComPix(nomeCliente, nomeMotorista, valor, pixOuCartaoCliente, pixOuCartaoMotorista);
        }else {
            facade.pagarCorridaComDinheiro(nomeCliente, nomeMotorista ,valor);
        }
    }

    public void listarPagamentos() {
        System.out.println("========= Listar Pagamentos =========");
        List<Pagamento> pagamentos = facade.listarPagamentos();
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento encontrado.");
        } else {
            for (Pagamento pagamento : pagamentos) {
                System.out.println(pagamento);
            }
        }
    }

    public void buscarPagamentoPorData() {
        System.out.println("========= Buscar Pagamento =========");
        System.out.print("Digite a data do pagamento (formato: dd/MM/yyyy): ");
        String dataInput = teclado.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataInput, formatter);
            List<Pagamento> pagamentos = facade.procurarPagamentoPorData(data.atStartOfDay());
            if (pagamentos.isEmpty()) {
                throw new SalvaFalhaException("Nenhum pagamento encontrado para a data informada.", null);
            } else {
                System.out.println("Pagamentos encontrados:");
                for (Pagamento pagamento : pagamentos) {
                    System.out.println(pagamento);
                }
            }
        } catch (SalvaFalhaException e) {
            System.out.println("Erro ao buscar pagamentos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao buscar pagamentos: " + e.getMessage());
        }
    }

    public void deletarPagamento() {
        System.out.println("========= Deletar Pagamento =========");
        System.out.print("Digite a data do pagamento (formato: dd/MM/yyyy): ");
        String dataInput = teclado.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataInput, formatter);
            List<Pagamento> pagamentos = facade.procurarPagamentoPorData(data.atStartOfDay());
            if (pagamentos.isEmpty()) {
                throw new SalvaFalhaException("Nenhum pagamento encontrado para a data informada.", null);
            } else {
                System.out.println("Pagamentos encontrados:");
                for (int i = 0; i < pagamentos.size(); i++) {
                    System.out.println((i + 1) + ". " + pagamentos.get(i));
                }
                System.out.print("Escolha o número do pagamento para deletar: ");
                int escolha = teclado.nextInt();
                teclado.nextLine();
                if (escolha > 0 && escolha <= pagamentos.size()) {
                    facade.deletarPagamento(pagamentos.get(escolha - 1));
                    System.out.println("Pagamento deletado com sucesso!");
                } else {
                    throw new SalvaFalhaException("Opção inválida.", null);
                }
            }
        } catch (SalvaFalhaException e) {
            System.out.println("Erro ao deletar pagamento: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao deletar pagamento: " + e.getMessage());
        }
    }

}
