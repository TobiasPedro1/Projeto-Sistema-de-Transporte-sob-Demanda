package repository;

import model.Viagem;
import java.util.List;

public interface ViagemRepositoryInterface {
    List<Viagem> findAll();
    Viagem findByDestino(String destino);
    void save(Viagem viagem);
    void deleteByDestino(String destino);

}
