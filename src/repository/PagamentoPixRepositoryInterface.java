package repository;

import model.PagamentoPix;

import java.time.LocalDateTime;
import java.util.List;

public interface PagamentoPixRepositoryInterface {
    void save(PagamentoPix pagamento);
    PagamentoPix findByData(LocalDateTime dataHoraPagamento);
    List<PagamentoPix> findAll();
    void delete(PagamentoPix pagamento);
}