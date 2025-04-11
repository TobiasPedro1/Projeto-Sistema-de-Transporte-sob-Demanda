package model;

public class PagamentoCredito extends Pagamento implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String numeroCartao;

    public PagamentoCredito(Cliente cliente, Motorista motorista, double valor, String numeroCartao) {
        super(cliente, motorista, valor);
        this.numeroCartao = numeroCartao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

}
