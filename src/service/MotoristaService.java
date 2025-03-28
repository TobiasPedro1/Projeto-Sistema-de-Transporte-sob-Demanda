package service;

import model.Avaliacao;
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


    public boolean validarMotorista(Motorista motorista){

        if(verificaCPF(motorista.getCpf()) && motorista.getHabilitacao().length() == 11){
            motorista.setValidado(true);
            return true;
        }else {
            motorista.setValidado(false);
            return false;
        }
    }

    public void adicionarAvaliacao(Motorista motorista ,Avaliacao avaliacao){
        motorista.adicionarAvaliacao(avaliacao);
    }


    //aleterar para repositorio assim que criado
    public Motorista selecionarMotoristaAleatorio(){
        List<Motorista> motoristas = this.motoristaRepository.findALL();
        if(motoristas.isEmpty()){
            // atualizar para exceção personalizada
            throw new IllegalStateException("Nenhum motorista disponível");
        }

        Random random = new Random();
        int index = random.nextInt(this.motoristaRepository.findALL().size());

        return this.motoristaRepository.findALL().get(index);

    }


}
