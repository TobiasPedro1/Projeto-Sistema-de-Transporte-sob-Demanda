package ui;

import exceptions.*;
import service.Facade;
import ui.admin.MenuAdm;
import ui.cliente.*;

import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Facade facade = new Facade();

        // Instancia os menus
        MenuMotorista menuMotorista = new MenuMotorista(facade);
        MenuCliente menuCliente = new MenuCliente(facade);
        MenuPagamentoCliente menuPagamentoCliente = new MenuPagamentoCliente(facade);
        MenuAvaliacao menuAvaliacao = new MenuAvaliacao(facade);
        MenuAdm menuAdm = new MenuAdm(facade);

        int opcao;
    try {
        do {
            System.out.println("============= Bem-vindo ao sistema de transporte! ==============");
            System.out.println("Escolha uma opção a seguir:");
            System.out.println("1. Menu Cliente");
            System.out.println("2. Menu Motorista");
            System.out.println("3. Menu Administrador");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1 -> menuCliente.exibirMenu();
                case 2 -> menuMotorista.exibirMenu();
                case 3 -> menuAdm.exibirMenu();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);
         teclado.close();
    } catch (AvaliacaoInvalida e) {
        System.out.println("Erro na avaliação: " + e.getMessage());
    } catch (BuscaVeiculoFalhaException e) {
        System.out.println("Erro ao buscar veículo: " + e.getMessage());
    } catch (CpfFalhaException e) {
        System.out.println("Erro no CPF: " + e.getMessage());
    } catch (SalvaFalhaException e) {
        System.out.println("Erro ao salvar: " + e.getMessage());
    } catch (ContaNaoEncontradaException e) {
        System.out.println("Conta não encontrada: " + e.getMessage());
    } catch(ClienteNaoValidadoException e){
        System.out.println("Cliente não validado: " + e.getMessage());
    } catch(EntidadeNaoEncontrada e) {
        System.out.println("Erro ao procurar a entidade: " + e.getMessage());
    } catch (MotoristaInvalidoException e) {
        System.out.println("Motorista inválido: " + e.getMessage());
    } catch (MotoristaNaoDisponivelException e) {
        System.out.println("Motorista não disponível: " + e.getMessage());
    } catch (MotoristaNaoEncontradoException e) {
        System.out.println("Motorista não encontrado: " + e.getMessage());
    } catch (OperacaoInvalidaExecption e) {
        System.out.println("Operação inválida: " + e.getMessage());
    } catch (PagamentoNaoEncontradoException e){
        System.out.println("Pagamento não encontrado: " + e.getMessage());
    } catch (SaldoInsuficienteException e) {
        System.out.println("Saldo insuficiente: " + e.getMessage());
    } catch (VeiculoNaoDisponivelException e) {
        System.out.printf("Veículo não disponível: " + e.getMessage());
    } catch (RuntimeException e) {
        System.out.println("Ocorreu um erro de execução: " + e.getMessage());
    } catch (Exception e){
        System.out.println("Erro inesperado: " + e.getMessage());
    }finally {
        System.out.println("Sistema encerrado.");
        teclado.close();
    }

    }



}