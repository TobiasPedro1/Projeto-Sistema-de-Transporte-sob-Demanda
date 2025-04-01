package repository;

import model.ContaBancaria;

import java.util.List;

public interface ContaBancoRepositoryInterface {
    void save(ContaBancaria contaBancaria);
    ContaBancaria findByNumero(String numero);
    List<ContaBancaria> findAll();
    ContaBancaria findByChavePix(String chavePix);
    void deleteByNumero(String numero);
}
