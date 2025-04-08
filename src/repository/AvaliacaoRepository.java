package repository;

import model.Avaliacao;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoRepository implements AvaliacaoRepositoryInterface {
    List<Avaliacao> avalicaoRepository = new ArrayList<>();

    @Override
    public void save(Avaliacao avaliacao) {
        avalicaoRepository.add(avaliacao);
    }
    @Override
    public Avaliacao findByComentario(String comentario) {
        for (Avaliacao avaliacao : avalicaoRepository) {
            if (avaliacao.getComentario().equals(comentario)) {
                return avaliacao;
            }
        }
        return null;
    }
    @Override
    public List<Avaliacao> findAll() {
        return avalicaoRepository;
    }
    @Override
 public void delete(Avaliacao avaliacao) {
     if (avaliacao != null && avalicaoRepository.contains(avaliacao)) {
         avalicaoRepository.remove(avaliacao);
     } else {
         System.out.println("Avaliação não encontrada ou inválida.");
     }
 }
}
