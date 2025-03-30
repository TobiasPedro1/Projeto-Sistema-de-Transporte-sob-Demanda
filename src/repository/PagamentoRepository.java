package repository;

import model.Pagamento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PagamentoRepository implements PagamentoRepositoryInterface{

    private List<Pagamento> pagamentoRepository = new ArrayList<>();

    @Override
    public void save(Pagamento pagamento){
        pagamentoRepository.add(pagamento);
    }

    @Override
    public Pagamento findByData(LocalDateTime dataHoraPagamento){
        for (Pagamento pagamento : pagamentoRepository) {
            if (pagamento.getDataHoraPagamento().equals(dataHoraPagamento)) {
                return pagamento;
            }
        }
        return null;
    }

    @Override
    public List<Pagamento> findAll(){
        return pagamentoRepository;
    }

    @Override
    public void delete(Pagamento pagamento){
        pagamentoRepository.remove(pagamento);
    }


}
