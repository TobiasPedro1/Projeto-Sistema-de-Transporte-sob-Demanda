package repository;

import model.PagamentoPix;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PagamentoPixRepository implements PagamentoPixRepositoryInterface {

    private List<PagamentoPix> pagamentoPixRepository = new ArrayList<>();

    @Override
    public void save(PagamentoPix pagamento) {
        pagamentoPixRepository.add(pagamento);
    }

    @Override
    public PagamentoPix findByChavePix(String chavePix) {
        for (PagamentoPix pagamento : pagamentoPixRepository) {
            if (pagamento.getChavePix().equals(chavePix)) {
                return pagamento;
            }
        }
        return null;
    }

    @Override
    public List<PagamentoPix> findAll() {
        return pagamentoPixRepository;
    }

    @Override
    public void delete(PagamentoPix pagamento) {
        pagamentoPixRepository.remove(pagamento);
    }
}