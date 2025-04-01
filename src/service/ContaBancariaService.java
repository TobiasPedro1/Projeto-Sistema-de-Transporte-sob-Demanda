package service;

import model.ContaBancaria;
import repository.ContaBancoRepository;

public class ContaBancariaService {
    private ContaBancoRepository contaBancoRepository;

    public ContaBancariaService() {
        this.contaBancoRepository = new ContaBancoRepository();
    }

    public void criarConta(String numeroConta) {
        ContaBancaria contaBancaria = new ContaBancaria(numeroConta);
        contaBancoRepository.save(contaBancaria);
    }

    public ContaBancaria buscarContaPorNumero(String numeroConta) {
        return contaBancoRepository.findByNumero(numeroConta);
    }

    public ContaBancaria buscarContaPorChavePix(String chavePix) {
        return contaBancoRepository.findByChavePix(chavePix);
    }

    public void depositar(String numeroConta, Double valor) {
        ContaBancaria contaBancaria = contaBancoRepository.findByNumero(numeroConta);
        if (contaBancaria != null) {
            contaBancaria.depositar(valor);
        } else {
            System.out.println("Conta não encontrada");
        }
    }

    public void sacar(String numeroConta, Double valor) {
        ContaBancaria contaBancaria = contaBancoRepository.findByNumero(numeroConta);
        if (contaBancaria != null) {
            contaBancaria.sacar(valor);
        } else {
            System.out.println("Conta não encontrada");
        }
    }

    public void deletarConta(String numeroConta) {
        contaBancoRepository.deleteByNumero(numeroConta);
    }
}