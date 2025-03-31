package repository;

import model.PagamentoCredito;

import java.time.LocalDateTime;
import java.util.List;

public interface PagamentoCreditoRepositoryInterface {
    void save(PagamentoCredito pagamento);
    PagamentoCredito findByData(LocalDateTime dataHoraPagamento);
    List<PagamentoCredito> findAll();
    void delete(PagamentoCredito pagamento);
}