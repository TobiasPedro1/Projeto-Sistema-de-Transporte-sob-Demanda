package model;

public class PagamentoPix extends Pagamento {
    private String chavePix;

    public PagamentoPix(Cliente cliente, Motorista motorista, double valor, String chavePix){
        super(cliente, motorista, valor);
        this.chavePix = chavePix;
    }

    public PagamentoPix(String chavePix) {
        this.chavePix = chavePix;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

}
