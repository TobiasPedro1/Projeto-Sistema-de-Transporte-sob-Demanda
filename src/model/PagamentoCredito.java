package model;

public class PagamentoCredito extends Pagamento {
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
