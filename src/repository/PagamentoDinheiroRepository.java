package repository;

import model.PagamentoDinheiro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDinheiroRepository implements PagamentoDinheiroRepositoryInterface {

    private List<PagamentoDinheiro> pagamentoDinheiroRepository = new ArrayList<>();

    @Override
    public void save(PagamentoDinheiro pagamento) {
        pagamentoDinheiroRepository.add(pagamento);
    }

    @Override
    public PagamentoDinheiro findByData(LocalDateTime dataHoraPagamento) {
        for (PagamentoDinheiro pagamento : pagamentoDinheiroRepository) {
            if (pagamento.getDataHoraPagamento().equals(dataHoraPagamento)) {
                return pagamento;
            }
        }
        return null;
    }

    @Override
    public List<PagamentoDinheiro> findAll() {
        return pagamentoDinheiroRepository;
    }

    @Override
    public void delete(PagamentoDinheiro pagamento) {
        pagamentoDinheiroRepository.remove(pagamento);
    }
}