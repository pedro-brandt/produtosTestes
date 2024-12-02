package com.example.produtos;

import static org.junit.jupiter.api.Assertions.*;
import com.example.produtos.Model.Produto;
import com.example.produtos.Service.ProdutosService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
public class ProdutoServiceTest {

    @Autowired
    private ProdutosService produtoService;

    //1.1 Cadastro de Produto com Dados Válidos
    @Test
    public void cadastrarProdutoDadosValidos() {
        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição Teste");
        produto.setQualidade("Alta");
        produto.setPreco(100.00);
        produto.setCategoria(null);
        produto.setFornecedor(null);

        Produto salvo = produtoService.criarProdutos(produto);

        assertNotNull(salvo.getIndentificador());
        assertEquals("Produto Teste", salvo.getNome());
    }

    //1.2 Cadastro de Produto com Nome Nulo
    @Test
    public void cadastrarProdutoNomeNulo() {
        Produto produto = new Produto();
        produto.setNome(null);
        produto.setDescricao("Descrição Teste");
        produto.setQualidade("Alta");
        produto.setPreco(100.00);
        produto.setCategoria(null);
        produto.setFornecedor(null);

        assertThrows(DataIntegrityViolationException.class, () -> {
            produtoService.criarProdutos(produto);
        });
    }

    //1.3. Cadastro de Produto com Preço Negativo
    @Test
    public void cadastrarProdutoPrecoNegativo() {
        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição Teste");
        produto.setQualidade("Alta");
        produto.setPreco(-10.00);
        produto.setCategoria(null);
        produto.setFornecedor(null);

        assertThrows(IllegalArgumentException.class, () -> {
            produtoService.criarProdutos(produto);
        });
    }
}
