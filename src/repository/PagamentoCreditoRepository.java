package repository;

import model.PagamentoCredito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PagamentoCreditoRepository implements PagamentoCreditoRepositoryInterface {

    private List<PagamentoCredito> pagamentoCreditoRepository = new ArrayList<>();

    @Override
    public void save(PagamentoCredito pagamento) {
        pagamentoCreditoRepository.add(pagamento);
    }

    @Override
    public PagamentoCredito findByData(LocalDateTime dataHoraPagamento) {
        for (PagamentoCredito pagamento : pagamentoCreditoRepository) {
            if (pagamento.getDataHoraPagamento().equals(dataHoraPagamento)) {
                return pagamento;
            }
        }
        return null;
    }

    @Override
    public List<PagamentoCredito> findAll() {
        return pagamentoCreditoRepository;
    }

    @Override
    public void delete(PagamentoCredito pagamento) {
        pagamentoCreditoRepository.remove(pagamento);
    }
}