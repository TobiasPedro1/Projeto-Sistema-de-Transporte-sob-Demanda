package repository;

import java.time.LocalDateTime;
import java.util.List;
import model.Pagamento;

public interface PagamentoRepositoryInterface {
    void save(Pagamento pagamento);
    Pagamento findByData(LocalDateTime dataHoraPagamento);
    List<Pagamento> findAll();
    void delete(Pagamento pagamento);
}
