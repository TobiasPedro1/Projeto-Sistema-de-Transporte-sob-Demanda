package ui.cliente;

import java.util.Scanner;
import service.Facade;

public class MenuAvaliacaoCliente {
    private Facade facade;
    private Scanner teclado;

    public MenuAvaliacaoCliente (Facade facade){
        this.facade = facade;
        this.teclado = new Scanner(System.in);
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
        System.out.println("Avaliação enviada com sucesso!");
    }

}
