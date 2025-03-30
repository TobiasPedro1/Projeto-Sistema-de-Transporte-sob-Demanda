package repository;

import model.Veiculo;
import java.util.List;
import java.util.ArrayList;

public class CarroRepository implements CarroRepositoryInterface{

    private List<Veiculo> veiculos ;

    public CarroRepository() {
        this.veiculos = new ArrayList<>();
    }

    @Override
    public void save(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    @Override
    public Veiculo findByplaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    @Override
    public List<Veiculo> findAll() {
        return new ArrayList<>(veiculos);
    }

    @Override
    public void deleteByPlaca(String placa) {
        veiculos.removeIf(veiculo -> veiculo.getPlaca().equals(placa));
    }
}
