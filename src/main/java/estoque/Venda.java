package estoque;

public class Venda extends Transacao{
    private Cliente cliente;

    public Venda(String dataVenda, Cliente cliente, Produto produto, int quantidadeVendida) {
        super(dataVenda, produto, quantidadeVendida);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente é obrigatório");
        }
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente é obrigatório");
        }
        this.cliente = cliente;
    }

    public boolean vender(Produto produto, int quantidadeVendida) {
        if (produto.verificarEstoqueInsuficiente(quantidadeVendida)) {
            produto.registrarHistorico("Estoque insuficiente");
            return false;
        }
        else {
            produto.debitarEstoque(quantidadeVendida);
            produto.registrarHistorico("Valor da venda = " + produto.calcularValorVenda(quantidadeVendida));
            if(produto.verificarEstoqueBaixo()){
                produto.registrarHistorico("Estoque baixo");
            }
            return true;
        }
    }
}
