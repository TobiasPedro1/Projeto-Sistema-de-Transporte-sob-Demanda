package repository;

import model.Veiculo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoRepository implements VeiculoRepositoryInterface {
    private final List<Veiculo> carros = new ArrayList<>();

    @Override
    public void save(Veiculo carro) {
        carros.add(carro);
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
    }
}