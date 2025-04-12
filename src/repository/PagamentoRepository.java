package repository;

import model.Pagamento;
import utils.SerializacaoUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PagamentoRepository implements PagamentoRepositoryInterface{
    private static final String ARQUIVO = "dados/pagamentos.ser";
    private List<Pagamento> pagamentoRepository = new ArrayList<>();

    public PagamentoRepository(){
        this.pagamentoRepository = SerializacaoUtil.carregarDados(ARQUIVO);
        if(this.pagamentoRepository == null){
            this.pagamentoRepository = new ArrayList<>();
        }
    }

    @Override
    public void save(Pagamento pagamento){
        pagamentoRepository.add(pagamento);
        salvarDados();
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
        salvarDados();
    }

    private void salvarDados(){
        SerializacaoUtil.salvarDados(pagamentoRepository, ARQUIVO);
    }

}
