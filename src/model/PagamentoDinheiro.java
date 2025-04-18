package model;

public class PagamentoDinheiro extends Pagamento implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private double dinheiroEspecie;

    public PagamentoDinheiro(Cliente cliente, Motorista motorista, double valor){
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
