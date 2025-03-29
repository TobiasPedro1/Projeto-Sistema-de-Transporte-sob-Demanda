package repository;

import model.Viagem;
import java.util.ArrayList;
import java.util.List;

public class ViagemRepositorio implements ViagemRepositoryInterface {
    private List<Viagem> viagens = new ArrayList<>();

    @Override
    public List<Viagem> findAll() {
        return new ArrayList<>(viagens);
    }

    @Override
    public Viagem findByDestino(String destino) {
        for (Viagem viagem : viagens) {
            if (viagem.getDestino().equals(destino)) {
                return viagem;
            }
        }
        return null;
    }

    @Override
    public void save(Viagem viagem) {
        viagens.add(viagem);
    }

    @Override
    public void deleteByDestino(String destino){
        if(viagens.removeIf( viagem -> viagem.getDestino().equals(destino))){
            System.out.println("Viagem removida com sucesso!");
        } else {
            System.out.println("Viagem não encontrada!");
        }
    }

}
