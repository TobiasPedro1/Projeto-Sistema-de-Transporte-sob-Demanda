package service;

import exceptions.*;
import model.ContaBancaria;
import repository.ContaBancoRepository;

import java.util.List;

public class ContaBancariaService {
    private final ContaBancoRepository contaBancoRepository;

    public ContaBancariaService(ContaBancoRepository contaBancoRepository) {
        this.contaBancoRepository = contaBancoRepository;
    }

    public void criarConta(String numeroConta, double saldo, String pagamentoPix, String pagamentoCredito) {
        ContaBancaria contaBancaria = new ContaBancaria(numeroConta, saldo, pagamentoPix, pagamentoCredito);
        contaBancoRepository.save(contaBancaria);
    }

    public List<ContaBancaria> findAll() {
        return contaBancoRepository.findAll();
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