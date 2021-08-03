package estoque;

import java.util.ArrayList;
import java.util.List;

public class Produto {
    private String nome;
    private int quantidadeEstoque;
    private float precoUnitario;
    private int estoqueMinimo;
    private int estoqueMaximo;
    private List<String> historico;

    public Produto(String nome, int quantidadeEstoque, float precoUnitario, int estoqueMinimo, int estoqueMaximo) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (quantidadeEstoque < 0 || estoqueMinimo < 0 || estoqueMaximo < 0) {
            throw new IllegalArgumentException("Estoque inválido");
        }
        if (precoUnitario < 0) {
            throw new IllegalArgumentException("Preço inválido");
        }

        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.precoUnitario = precoUnitario;
        this.estoqueMinimo = estoqueMinimo;
        this.estoqueMaximo = estoqueMaximo;
        this.historico = new ArrayList<String>();
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        this.nome = nome;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        if (quantidadeEstoque < 0) {
            throw new IllegalArgumentException("Estoque inválido");
        }
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }
    public void setPrecoUnitario(float precoUnitario) {
        if (precoUnitario < 0) {
            throw new IllegalArgumentException("Preço inválido");
        }
        this.precoUnitario = precoUnitario;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }
    public void setEstoqueMinimo(int estoqueMinimo) {
        if (estoqueMinimo < 0) {
            throw new IllegalArgumentException("Estoque inválido");
        }
        this.estoqueMinimo = estoqueMinimo;
    }

    public int getEstoqueMaximo() {
        return estoqueMaximo;
    }
    public void setEstoqueMaximo(int estoqueMaximo) {
        if (estoqueMaximo < 0) {
            throw new IllegalArgumentException("Estoque inválido");
        }
        this.estoqueMaximo = estoqueMaximo;
    }

    public List<String> getHistorico() {
        return historico;
    }
    public void setHistorico(List<String> historico) {
        this.historico = historico;
    }




    public void registrarHistorico(String transacao){
        this.historico.add(transacao);
    }

    public List<String> exibirHistorico(){
        return this.historico;
    }

    public void debitarEstoque(int quantidade){
        this.quantidadeEstoque -= quantidade;
    }

    public void creditarEstoque(int quantidade){
        this.quantidadeEstoque += quantidade;
    }

    public boolean verificarEstoqueBaixo(){
        return this.quantidadeEstoque < this.estoqueMinimo;
    }

    public boolean verificarEstoqueInsuficiente(int quantidade){
        return quantidade > this.quantidadeEstoque;
    }

    public boolean verificarEstoqueExcedente(int quantidade){
        return (quantidade + this.quantidadeEstoque) > this.estoqueMaximo;
    }

    public float calcularValorVenda(int quantidade){
        return this.precoUnitario * quantidade;
    }

    public void vender(String dataVenda, Cliente cliente, int quantidadeVendida){
        Venda venda = new Venda(dataVenda,cliente,this, quantidadeVendida);

        if (venda.vender(this, quantidadeVendida)){
            registrarHistorico("Venda do produto " + this.nome);
        }
    }

    public void comprar(String dataCompra,Fornecedor fornecedor, int quantidadeCompra, float precoUnitario){
        Compra compra = new Compra(dataCompra, this, fornecedor, quantidadeCompra, precoUnitario);

        if(compra.comprar(this, quantidadeCompra)){
            registrarHistorico("Compra do produto " + this.nome);
        }
    }
}
