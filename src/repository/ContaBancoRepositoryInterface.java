package repository;

import model.ContaBancaria;

import java.util.List;

public interface ContaBancoRepositoryInterface {
    void save(ContaBancaria contaBancaria);
    ContaBancaria findByNumero(String numero);
    List<ContaBancaria> findAll();
    void deleteByNumero(String numero);
}
