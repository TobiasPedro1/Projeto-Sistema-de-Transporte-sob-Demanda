package repository;

import model.Veiculo;
import utils.SerializacaoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoRepository implements VeiculoRepositoryInterface {
    private static final String ARQUIVO = "dados/veiculos.ser";
    private List<Veiculo> carros;

    public VeiculoRepository(){
        this.carros = SerializacaoUtil.carregarDados(ARQUIVO);
        if(this.carros == null){
            this.carros = new ArrayList<>();
        }
    }

    @Override
    public void save(Veiculo carro) {
        carros.add(carro);
        salvarDados();
    }

    @Override
    public Veiculo findByPlaca(String placa) {
        Optional<Veiculo> carro = carros.stream()
                .filter(c -> c.getPlaca().equals(placa))
                .findFirst();
        return carro.orElse(null);
    }

    @Override
    public List<Veiculo> findAll() {
        return new ArrayList<>(carros);
    }

    @Override
    public void delete(Veiculo carro) {
        carros.remove(carro);
        salvarDados();
    }

    private void salvarDados(){
        SerializacaoUtil.salvarDados(carros, ARQUIVO);
    }
}