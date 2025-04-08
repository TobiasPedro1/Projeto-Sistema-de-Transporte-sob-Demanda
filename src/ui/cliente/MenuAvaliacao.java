package ui.cliente;

import java.util.Scanner;
import service.Facade;

public class MenuAvaliacao {
    private Facade facade;
    private Scanner teclado;

    public MenuAvaliacao(Facade facade){
        this.facade = facade;
        this.teclado = new Scanner(System.in);
    }

    public void avaliarViagemDoCliente(){
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
        System.out.println("Avaliação enviada com sucesso!");
    }

    public void avaliarViagemDoMotorista(){
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

    public void listarAvaliacoes() {
        System.out.println("========= Listar Avaliações =========");
        var avaliacoes = facade.listarAvaliacoes();
        if (avaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliação encontrada.");
        } else {
            for (int i = 0; i < avaliacoes.size(); i++) {
                System.out.println((i + 1) + ". " + avaliacoes.get(i));
            }
        }
    }

    public void deletarAvaliacao() {
        System.out.println("========= Deletar Avaliação =========");
        var avaliacoes = facade.listarAvaliacoes();
        if (avaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliação encontrada para deletar.");
            return;
        }

        for (int i = 0; i < avaliacoes.size(); i++) {
            System.out.println((i + 1) + ". " + avaliacoes.get(i));
        }

        System.out.print("Escolha o número da avaliação para deletar: ");
        int escolha = teclado.nextInt();
        teclado.nextLine();

        if (escolha > 0 && escolha <= avaliacoes.size()) {
            try {
                facade.deletarAvaliacao(avaliacoes.get(escolha - 1));
                System.out.println("Avaliação deletada com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro ao deletar a avaliação: " + e.getMessage());
            }
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }
}
