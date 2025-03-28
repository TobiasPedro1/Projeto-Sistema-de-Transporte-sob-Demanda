package service;

import model.Avaliacao;
import model.Motorista;
import static utils.VerificaCpf.verificaCPF;
import java.util.List;
import java.util.Random;

public class MotoristaService {
    //aleterar para repositorio assim que criado
    private List<Motorista> motoristasList;


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
        Random random = new Random();
        int index = random.nextInt(this.motoristasList.size());

        return this.motoristasList.get(index);

    }


}
