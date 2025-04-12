package repository;

import model.ContaBancaria;
import utils.SerializacaoUtil;
import java.util.List;
import java.util.ArrayList;

public class ContaBancoRepository implements ContaBancoRepositoryInterface {
    private static final String ARQUIVO = "dados/contas.ser";
    private List<ContaBancaria> contasBancarias;

    public ContaBancoRepository() {
        this.contasBancarias = SerializacaoUtil.carregarDados(ARQUIVO);
        if (this.contasBancarias == null) {
            this.contasBancarias = new ArrayList<>();
        }
    }

    @Override
    public void save(ContaBancaria contaBancaria) {
        contasBancarias.add(contaBancaria);
        salvarDados();
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
            salvarDados();
        }
    }

    public ContaBancaria findByChavePix(String chavePix) {
        for (ContaBancaria conta : contasBancarias) {
            if (conta.getChavePix().equals(chavePix)) {
                return conta;
            }
        }
        return null;
    }

    private void salvarDados() {
        SerializacaoUtil.salvarDados(contasBancarias, ARQUIVO);
    }
}