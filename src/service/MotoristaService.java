package service;

import exceptions.CpfFalhaException;
import exceptions.MotoristaInvalidoException;
import exceptions.MotoristaNaoEncontradoException;
import exceptions.SalvaFalhaException;
import model.Avaliacao;
import model.Cliente;
import model.Motorista;
import static utils.VerificaCpf.verificaCPF;
import repository.MotoristaRepository;

import java.util.List;
import java.util.Random;

public class MotoristaService {
    //aleterar para repositorio assim que criado
    private MotoristaRepository motoristaRepository;

    public MotoristaService (MotoristaRepository motoristaRepository){
        this.motoristaRepository = motoristaRepository;
    }

    public void cadastrarMotorista(Motorista motorista) {
        try {
            motoristaRepository.save(motorista);
            System.out.println("Motorista adicionado com sucesso.");
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao adicionar Motorista.", e);
        }
    }

    public boolean validarMotorista(Motorista motorista){

        if(verificaCPF(motorista.getCpf()) && motorista.getHabilitacao().length() == 11){
            motorista.setValidado(true);
            return true;
        }else {
            motorista.setValidado(false);
            throw new MotoristaInvalidoException("Motorista inválido: CPF ou habilitação inválidos incorretos");
        }
    }

    public void adicionarAvaliacaoMotorista(Motorista motorista ,Avaliacao avaliacao){
        motorista.adicionarAvaliacao(avaliacao);
    }

    //aleterar para repositorio assim que criado
    public Motorista selecionarMotoristaAleatorio(){
        List<Motorista> motoristas = this.motoristaRepository.findALL();
        if(motoristas.isEmpty()){
            throw new MotoristaNaoEncontradoException("Nenhum motorista disponível");
        }

        Random random = new Random();
        int index = random.nextInt(this.motoristaRepository.findALL().size());

        return this.motoristaRepository.findALL().get(index);

    }

    public Motorista buscarMotoristaPorCpf(String cpf) {
        try {
            return motoristaRepository.motoristaFindByCpf(cpf);
        } catch (Exception e) {
            throw new CpfFalhaException("Motorista não encontrado com o CPF: " + cpf);
        }
    }

}
