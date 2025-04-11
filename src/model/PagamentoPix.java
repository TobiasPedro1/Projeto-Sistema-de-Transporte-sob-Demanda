package model;

public class PagamentoPix extends Pagamento implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

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
