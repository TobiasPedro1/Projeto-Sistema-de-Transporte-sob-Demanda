package repository;

import model.Viagem;
import utils.SerializacaoUtil;

import java.util.ArrayList;
import java.util.List;

public class ViagemRepository implements ViagemRepositoryInterface {
    private static final String ARQUIVO = "dados/viagens.ser";
    private List<Viagem> viagens;

    public ViagemRepository() {
        this.viagens = SerializacaoUtil.carregarDados(ARQUIVO);
        if (this.viagens == null) {
            this.viagens = new ArrayList<>();
        }
    }

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
        salvarDados();
    }

    @Override
    public void deleteByDestino(String destino){
        if(viagens.removeIf( viagem -> viagem.getDestino().equals(destino))){
            salvarDados();
            System.out.println("Viagem removida com sucesso!");
        } else {
            System.out.println("Viagem não encontrada!");
        }
    }

    private void salvarDados() {
        SerializacaoUtil.salvarDados(viagens, ARQUIVO);
    }

}
