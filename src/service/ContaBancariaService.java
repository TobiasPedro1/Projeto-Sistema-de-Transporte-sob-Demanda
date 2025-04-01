package service;

import exceptions.*;
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
        ContaBancaria contaBancaria = contaBancoRepository.findByNumero(numeroConta);
        if (contaBancaria == null) {
            throw new ContaNaoEncontradaException("Conta não encontrada.");
        }
        return contaBancaria;
    }

    public ContaBancaria buscarContaPorChavePix(String chavePix) {
        ContaBancaria contaBancaria = contaBancoRepository.findByChavePix(chavePix);
        if (contaBancaria == null) {
            throw new ContaNaoEncontradaException("Conta não encontrada.");
        }
        return contaBancaria;
    }

    public void depositar(String numeroConta, Double valor) {
        if(valor <= 0)  {
            throw new OperacaoInvalidaExecption("Valor de depósito inválido.");
        }
        ContaBancaria contaBancaria = contaBancoRepository.findByNumero(numeroConta);
        if (contaBancaria != null) {
            contaBancaria.depositar(valor);
        } else {
            throw new ContaNaoEncontradaException("Conta não encontrada.");
        }
    }

    public void sacar(String numeroConta, Double valor) {
        if(valor <= 0) {
            throw new OperacaoInvalidaExecption("Valor de saque deve ser positivo.");
        }
        ContaBancaria contaBancaria = contaBancoRepository.findByNumero(numeroConta);
        if (contaBancaria != null) {
            if (contaBancaria.getSaldo() < valor) {
                throw new SaldoInsuficienteException("Saldo insuficiente.");
            }
            contaBancaria.sacar(valor);
        } else {
            throw new ContaNaoEncontradaException("Conta não encontrada.");
        }
    }

    public void deletarConta(String numeroConta) {
        ContaBancaria contaBancaria = contaBancoRepository.findByNumero(numeroConta);
        if(contaBancaria == null) {
            throw new ContaNaoEncontradaException("Conta não encontrada.");
        }
        contaBancoRepository.deleteByNumero(numeroConta);
    }
}