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
    public List<Pagamento> findByData(LocalDateTime data) {
        return pagamentoRepository.stream()
                .filter(p -> p.getDataHoraPagamento().equals(data))
                .toList();
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
