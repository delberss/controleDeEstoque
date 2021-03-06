package estoque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoTest {
    @Test
    void deveRetornarExcecaoCriarTransacaoSemProduto() {
        try {
            Transacao transacao = new Transacao("02/08/2021",null,100);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Produto é obrigatório", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoSetarNuloProduto() {
        try {
            Produto produto = new Produto("Caneta",100,2f,200,250);
            Transacao transacao = new Transacao("02/08/2021",produto,100);
            transacao.setProduto(null);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Produto é obrigatório", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoCriarTransacaoQuantidadeNegativa() {
        try {
            Produto produto = new Produto("Caneta",100,2f,200,250);
            Transacao transacao = new Transacao("02/08/2021",produto,-1);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Quantidade inválida", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoSetarNegativaQuantidadeTransacao() {
        try {
            Produto produto = new Produto("Caneta",100,2f,200,250);
            Transacao transacao = new Transacao("02/08/2021",produto,100);
            transacao.setQuantidade(-1);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Quantidade inválida", e.getMessage());
        }
    }

}