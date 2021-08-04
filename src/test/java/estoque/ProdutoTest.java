package estoque;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void deveRetornarExcecaoCriarProdutoNomeNulo() {
        try {
            Produto produto = new Produto(null,100,2.0f,50,200);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Nome é obrigatório", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoSetarNuloNomeProduto() {
        try {
            Produto produto = new Produto("Caneta",100,2.0f,50,200);
            produto.setNome(null);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Nome é obrigatório", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoCriarProdutoPrecoNegativo() {
        try {
            Produto produto = new Produto("Caneta",100,-0.01f,50,200);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Preço inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoSetarNegativoPrecoProduto() {
        try {
            Produto produto = new Produto("Caneta",100,2.0f,50,200);
            produto.setPrecoUnitario(-0.01f);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Preço inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoCriarProdutoQuantidadeEstoqueNegativo() {
        try {
            Produto produto = new Produto("Caneta",-1,2.0f,50,200);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Estoque inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoSetarNegativoQuantidadeEstoqueProduto() {
        try {
            Produto produto = new Produto("Caneta",100,2.0f,50,200);
            produto.setQuantidadeEstoque(-1);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Estoque inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoCriarProdutoEstoqueMaximoNegativo() {
        try {
            Produto produto = new Produto("Caneta",100,2.0f,50,-1);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Estoque inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoSetarNegativoEstoqueMaximoProduto() {
        try {
            Produto produto = new Produto("Caneta",100,2.0f,50,200);
            produto.setEstoqueMaximo(-1);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Estoque inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoCriarProdutoEstoqueMinimoNegativo() {
        try {
            Produto produto = new Produto("Caneta",100,2.0f,-1,200);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Estoque inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoSetarNegativoEstoqueMinimoProduto() {
        try {
            Produto produto = new Produto("Caneta", 100, 2.0f, 50, 200);
            produto.setEstoqueMinimo(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Estoque inválido", e.getMessage());
        }
    }

    @Test
    void deveDebitarEstoque(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        produto.debitarEstoque(5);

        assertEquals(95, produto.getQuantidadeEstoque());
    }

    @Test
    void deveCreditarEstoque(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        produto.creditarEstoque(5);

        assertEquals(105, produto.getQuantidadeEstoque());
    }

    @Test
    void deveVerificarEstoqueBaixo(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        produto.setQuantidadeEstoque(49);

        assertTrue(produto.verificarEstoqueBaixo());
    }

    @Test
    void deveVerificarEstoqueNaoBaixo(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        assertFalse(produto.verificarEstoqueBaixo());
    }

    @Test
    void deveVerificarEstoqueInsuficiente(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        assertTrue(produto.verificarEstoqueInsuficiente(101));
    }

    @Test
    void deveVerificarEstoqueNaoInsuficiente(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        assertFalse(produto.verificarEstoqueInsuficiente(100));
    }

    @Test
    void deveVerificarEstoqueExcedente(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        assertTrue(produto.verificarEstoqueExcedente(101));
    }

    @Test
    void deveVerificarEstoqueNaoExcedente(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        assertFalse(produto.verificarEstoqueExcedente(100));
    }

    @Test
    void deveCalcularValorVenda(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        assertEquals(10, produto.calcularValorVenda(5));
    }

    @Test
    void deveVerificarRegistrarHistorico(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        produto.registrarHistorico("Compra do produto");

        List<String> lista = new ArrayList<String>();
        lista.add("Compra do produto");

        assertEquals(lista, produto.getHistorico());
    }


    @Test
    void deveExibirHistoricVazio(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        List<String> lista = new ArrayList<String>();

        assertEquals(lista, produto.exibirHistorico());
    }

    @Test
    void deveExibirHistoricoUmaVenda(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        Cliente cliente1 = new Cliente("Marco","123");
        produto.vender("26/07/2021", cliente1, 10);

        List<String> lista = Arrays.asList("Valor da venda = 20.0","Venda do produto Caneta");

        assertEquals(lista, produto.exibirHistorico());
    }

    @Test
    void deveExibirHistoricoMaisDeUmaVenda(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        Cliente cliente1 = new Cliente("Marco","123");
        Cliente cliente2 = new Cliente("Antonio","456");
        produto.vender("26/07/2021", cliente1, 10);
        produto.vender("26/07/2021", cliente2, 30);

        List<String> lista = Arrays.asList("Valor da venda = 20.0","Venda do produto Caneta","Valor da venda = 60.0","Venda do produto Caneta");

        assertEquals(lista, produto.exibirHistorico());
    }

    @Test
    void deveExibirHistoricoUmaCompra(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        Fornecedor fornecedor = new Fornecedor("Antonio","456");
        produto.comprar("26/07/2021", fornecedor, 50,1.1f);

        List<String> lista = new ArrayList<String>();
        lista.add("Compra do produto Caneta");

        assertEquals(lista, produto.exibirHistorico());
    }

    @Test
    void deveExibirHistoricoMaisDeUmaCompra(){
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        Fornecedor fornecedor1 = new Fornecedor("Antonio","456");
        Fornecedor fornecedor2 = new Fornecedor("Antonio","456");
        produto.comprar("26/07/2021", fornecedor1, 50,1.1f);
        produto.comprar("26/07/2021", fornecedor2, 40,1.2f);

        List<String> lista = Arrays.asList("Compra do produto Caneta","Compra do produto Caneta");


        assertEquals(lista, produto.exibirHistorico());
    }

}