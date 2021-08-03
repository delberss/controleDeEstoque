package estoque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompraTest {
    Fornecedor fornecedor;
    Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto("Caneta",100,2.0f,50,200);
    }

    @Test
    void deveRetornarExcecaoCriarCompraFornecedorNulo() {
        try {
            Compra compra = new Compra("28/07/21",produto,null,101,100);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Fornecedor é obrigatório", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoSetarNuloFornecedor() {
        try {
            Fornecedor fornecedor = new Fornecedor("Marco", "123");
            Compra compra = new Compra("28/07/21",produto,fornecedor,101,100);
            compra.setFornecedor(null);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Fornecedor é obrigatório", e.getMessage());
        }
    }

    @Test
    void deveVerificarNaoCompra() {
        fornecedor = new Fornecedor("Marco","456");
        Compra compra = new Compra("28/07/21",produto,fornecedor,101,100);

        assertFalse(compra.comprar(produto, compra.getQuantidade()));
    }

    @Test
    void deveRetornarEstoqueExcedente(){
        fornecedor = new Fornecedor("Marco","456");
        Compra compra = new Compra("28/07/21",produto,fornecedor,101,100);

        compra.comprar(produto,compra.getQuantidade());

        List<String> lista = new ArrayList<String>();
        lista.add("Estoque excedente");

        assertEquals(lista, produto.getHistorico());
    }

    @Test
    void deveVerificarCompra() {
        fornecedor = new Fornecedor("Marco","456");
        Compra compra = new Compra("28/07/21",produto,fornecedor,99,100);

        assertTrue(compra.comprar(produto, compra.getQuantidade()));
    }

    @Test
    void deveVerificarCreditoAoEstoque() {
        fornecedor = new Fornecedor("Marco","456");
        Compra compra = new Compra("28/07/21",produto,fornecedor,99,100);

        compra.comprar(produto, compra.getQuantidade());

        assertEquals(199,produto.getQuantidadeEstoque());
    }

}