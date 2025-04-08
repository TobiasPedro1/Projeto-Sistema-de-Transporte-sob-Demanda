package repository;

import model.Avaliacao;

import java.util.List;

public interface AvaliacaoRepositoryInterface {
    void save(Avaliacao avaliacao);
    Avaliacao findByComentario(String comentario);
    List<Avaliacao> findAll();
    void delete(Avaliacao avaliacao);
}
