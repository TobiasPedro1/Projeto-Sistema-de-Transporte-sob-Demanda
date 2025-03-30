package repository;

import model.ContaBancaria;
import java.util.List;
import java.util.ArrayList;

public class ContaBancoRepository implements ContaBancoRepositoryInterface {
    private List<ContaBancaria> contasBancarias;

    public ContaBancoRepository() {
        this.contasBancarias = new ArrayList<>();
    }

    @Override
    public void save(ContaBancaria contaBancaria) {
        contasBancarias.add(contaBancaria);
    }

    @Override
    public ContaBancaria findByNumero(String numero) {
        for (ContaBancaria conta : contasBancarias) {
            if (conta.getNumeroConta().equals(numero)) {
                return conta;
            }
        }
        return null;
    }

    @Override
    public List<ContaBancaria> findAll() {
        return contasBancarias;
    }

    @Override
    public void deleteByNumero(String numero) {
        ContaBancaria conta = findByNumero(numero);
        if (conta != null) {
            contasBancarias.remove(conta);
        }
    }

}
