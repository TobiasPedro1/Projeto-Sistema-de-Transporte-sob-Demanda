package repository;

import exceptions.CpfFalhaException;
import model.Motorista;
import java.util.ArrayList;
import java.util.List;

public class MotoristaRepository implements MotoristaRepositoryInterface{
    List<Motorista> motoristas = new ArrayList<>();

    @Override
    public List<Motorista> findALL(){
        return new ArrayList<>(motoristas);
    }

    @Override
    public Motorista motoristaFindByName(String nome) {
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
    }

    @Override
    public void deleteByName(String nome) {
        if( motoristas.removeIf(motorista -> motorista.getNome().equals(nome))){
            System.out.println("Motorista removido com sucesso!");

        }

    }
}
