package repository;

import model.PagamentoPix;

import java.time.LocalDateTime;
import java.util.List;

public interface PagamentoPixRepositoryInterface {
    void save(PagamentoPix pagamento);
    PagamentoPix findByChavePix(String chavePix);
    List<PagamentoPix> findAll();
    void delete(PagamentoPix pagamento);
}