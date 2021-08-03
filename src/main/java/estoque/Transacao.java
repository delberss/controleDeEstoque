package estoque;

public class Transacao {
    private String dataTransacao;
    private int quantidade;
    private Produto produto;

    public Transacao(String dataTransacao, Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto é obrigatório");
        }
        this.dataTransacao = dataTransacao;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public String getDataTransacao() {
        return dataTransacao;
    }
    public void setDataTransacao(String dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto é obrigatório");
        }
        this.produto = produto;
    }
}
