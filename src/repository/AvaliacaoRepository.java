package repository;

import model.Avaliacao;
import utils.SerializacaoUtil;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoRepository implements AvaliacaoRepositoryInterface {
    private static final String ARQUIVO = "dados/avaliacoes.ser";
    List<Avaliacao> avalicaoRepository;

    public AvaliacaoRepository() {
        this.avalicaoRepository = SerializacaoUtil.carregarDados(ARQUIVO);
        if (this.avalicaoRepository == null) {
            this.avalicaoRepository = new ArrayList<>();
        }
    }

    @Override
    public void save(Avaliacao avaliacao) {
        avalicaoRepository.add(avaliacao);
        salvarDados();
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
        salvarDados();
     } else {
         System.out.println("Avaliação não encontrada ou inválida.");
     }
    }

    private void salvarDados(){
        SerializacaoUtil.salvarDados(avalicaoRepository, ARQUIVO);
    }

}
