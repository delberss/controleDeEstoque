package estoque;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendaTest {
    @Test
    void deveRetornarExcecaoCriarVendaClienteNulo() {
        try {
            Produto produto = new Produto("Caneta",100,2.0f,50,200);
            Venda venda = new Venda("28/07/21",null,produto,101);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Cliente é obrigatório", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoSetarNuloCliente() {
        try {
            Cliente cliente = new Cliente("Marco","456");
            Produto produto = new Produto("Caneta",100,2.0f,50,200);
            Venda venda = new Venda("28/07/21",cliente,produto,101);
            venda.setCliente(null);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Cliente é obrigatório", e.getMessage());
        }
    }

    @Test
    void deveVerificarNaoVenda() {
        Cliente cliente = new Cliente("Marco","456");
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        Venda venda = new Venda("28/07/21",cliente,produto,101);

        assertFalse(venda.vender(produto, venda.getQuantidade()));
    }

    @Test
    void deveRetornarEstoqueInsuficiente(){
        Cliente cliente = new Cliente("Marco","456");
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        Venda venda = new Venda("28/07/21",cliente,produto,101);

        venda.vender(produto,venda.getQuantidade());

        List<String> lista = new ArrayList<String>();
        lista.add("Estoque insuficiente");

        assertEquals(lista, produto.getHistorico());
    }

    @Test
    void deveVerificarVenda() {
        Cliente cliente = new Cliente("Marco","456");
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        Venda venda = new Venda("28/07/21",cliente,produto,99);

        assertTrue(venda.vender(produto, venda.getQuantidade()));
    }

    @Test
    void deveVerificarDebitoAoEstoque() {
        Cliente cliente = new Cliente("Marco","456");
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        Venda venda = new Venda("28/07/21",cliente,produto,99);

        venda.vender(produto, venda.getQuantidade());

        assertEquals(1,produto.getQuantidadeEstoque());
    }

    @Test
    void deveRetornarValorDaVenda() {
        Cliente cliente = new Cliente("Marco","456");
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        Venda venda = new Venda("28/07/21",cliente,produto,50);

        venda.vender(produto, venda.getQuantidade());

        List<String> lista = new ArrayList<String>();
        lista.add("Valor da venda = 100.0");

        assertEquals(lista, produto.getHistorico());
    }


    @Test
    void deveRetornarEstoqueBaixoPosVenda() {
        Cliente cliente = new Cliente("Marco","456");
        Produto produto = new Produto("Caneta",100,2.0f,50,200);
        Venda venda = new Venda("28/07/21",cliente,produto,51);

        venda.vender(produto, venda.getQuantidade());

        List<String> lista = Arrays.asList("Valor da venda = 102.0","Estoque baixo");

        assertEquals(lista, produto.getHistorico());
    }


}