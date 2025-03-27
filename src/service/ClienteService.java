package service;

import static utils.VerificaCpf.verificaCPF;
import model.Cliente;
import model.Avaliacao;

public class ClienteService {
    public boolean validarCliente(Cliente cliente){
        if(verificaCPF(cliente.getCpf())){
            cliente.setValidado(true);
            System.out.println("Cliente validado com sucesso.");
            return true;
        }else{
            cliente.setValidado(false);
            System.out.println("Falha na validação do cliente.");
            return false;
        }
    }

    public void adicionarAvaliacao(Cliente cliente, Avaliacao avaliacao){
        cliente.adicionarAvaliacao(avaliacao);
    }

}
