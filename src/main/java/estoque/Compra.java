package estoque;

public class Compra extends Transacao{
    private float precoUnitario;
    private Fornecedor fornecedor;

    public Compra(String dataCompra, Produto produto,Fornecedor fornecedor, int quantidadeCompra, float precoUnitario) {
        super(dataCompra, produto, quantidadeCompra);
        if (fornecedor == null) {
            throw new IllegalArgumentException("Fornecedor é obrigatório");
        }
        this.precoUnitario = precoUnitario;
        this.fornecedor = fornecedor;
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }
    public void setPrecoUnitario(float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new IllegalArgumentException("Fornecedor é obrigatório");
        }
        this.fornecedor = fornecedor;
    }

    public boolean comprar(Produto produto, int quantidadeCompra) {
        if (produto.verificarEstoqueExcedente(quantidadeCompra)) {
            produto.registrarHistorico("Estoque excedente");
            return false;
        }
        else{
            produto.creditarEstoque(quantidadeCompra);
            return true;
        }
    }
}
