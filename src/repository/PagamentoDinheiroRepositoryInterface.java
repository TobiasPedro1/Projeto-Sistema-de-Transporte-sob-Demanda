package repository;

import model.PagamentoDinheiro;

import java.time.LocalDateTime;
import java.util.List;

public interface PagamentoDinheiroRepositoryInterface {
    void save(PagamentoDinheiro pagamento);
    PagamentoDinheiro findByData(LocalDateTime dataHoraPagamento);
    List<PagamentoDinheiro> findAll();
    void delete(PagamentoDinheiro pagamento);
}