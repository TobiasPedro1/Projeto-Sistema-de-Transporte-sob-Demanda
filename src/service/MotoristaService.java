package service;

import model.Avaliacao;
import model.Motorista;
import static utils.VerificaCpf.verificaCPF;
import utils.VerificaCpf;

public class MotoristaService {
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
}
