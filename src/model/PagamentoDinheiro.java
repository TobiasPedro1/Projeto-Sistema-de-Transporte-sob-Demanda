package model;

public class PagamentoDinheiro extends Pagamento {
    private double dinheiroEspecie;

    public PagamentoDinheiro(Cliente cliente, Motorista motorista, double valor, double dineheiroEspecie){
        super(cliente, motorista, valor);
        this.dinheiroEspecie = dinheiroEspecie;
    }

    public double getDinheiroEspecie(){
        return dinheiroEspecie;
    }

    public void setDinheiroEspecie(double dinheiroEspecie) {
        this.dinheiroEspecie = dinheiroEspecie;
    }

}
