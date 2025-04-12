package repository;

import exceptions.CpfFalhaException;
import model.Motorista;
import utils.SerializacaoUtil;

import java.util.ArrayList;
import java.util.List;

public class MotoristaRepository implements MotoristaRepositoryInterface{
    private static final String ARQUIVO = "C:\\Users\\pedro\\OneDrive\\Documentos\\JAVA\\Sistema de Transporte sob Demanda\\Projeto - Sistema de Transporte sob Demanda\\dados\\motoristas.ser";

    private static MotoristaRepository instance;
    private List<Motorista> motoristas;

    public MotoristaRepository(){
        this.motoristas = SerializacaoUtil.carregarDados(ARQUIVO);
        if(this.motoristas == null){
            this.motoristas = new ArrayList<>();
        }
    }


    @Override
    public List<Motorista> findALL(){
        return new ArrayList<>(motoristas);
    }

    @Override
    public  MotoristaRepository getInstance() {
        if (instance == null) {
            instance = new MotoristaRepository();
        }
        return instance;
    }


    @Override
    public Motorista motoristaFindByNome(String nome) {
        for (Motorista motorista : motoristas) {
            if (motorista.getNome().equals(nome)) {
                System.out.println("Motorista encontrado!");
                return motorista;
            }
        }
        throw new CpfFalhaException("Motorista não encontrado com o nome: " + nome);
    }

    @Override
    public Motorista motoristaFindByCpf(String cpf) {
        for (Motorista motorista : motoristas) {
            if (motorista.getCpf().equals(cpf)) {
                System.out.println("Motorista encontrado!");
                return motorista;
            }
        }
        throw new CpfFalhaException("Motorista não encontrado com o CPF: " + cpf);
    }

    @Override
    public void save(Motorista motorista) {
        motoristas.add(motorista);
        salvarDados();
    }

    @Override
    public void deleteByCpf(String nome) {
        if( motoristas.removeIf(motorista -> motorista.getCpf().equals(nome))){
            salvarDados();
            System.out.println("Motorista removido com sucesso!");

        }
    }

    private void salvarDados(){
        SerializacaoUtil.salvarDados(motoristas, ARQUIVO);
    }
}
