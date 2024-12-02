package com.example.produtos;

import static org.junit.jupiter.api.Assertions.*;
import com.example.produtos.Model.Categoria;
import com.example.produtos.Model.Fornecedor;
import com.example.produtos.Model.Produto;
import com.example.produtos.Repository.CategoriaRepository;
import com.example.produtos.Repository.FornecedorRepository;
import com.example.produtos.Repository.ProdutosRepository;
import com.example.produtos.Service.ProdutosService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class ProdutoServiceIntegracaoTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ProdutosRepository produtoRepository;

    @Autowired
    private ProdutosService produtoService;

    //1.4 Associação de Produto a Categoria e Fornecedor
    @Test
    public void associarProdutoCategoriaFornecedor() {
        // Criar e salvar categoria
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        // Criar e salvar fornecedor
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome("Fornecedor A");
        fornecedor.setCPF("99999999900");
        Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);

        // Criar o produto associado
        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição Teste");
        produto.setQualidade("Alta");
        produto.setPreco(100.00);
        produto.setCategoria(categoriaSalva);
        produto.setFornecedor(fornecedorSalvo);

        Produto salvo = produtoService.criarProdutos(produto);

        // Verificações
        assertNotNull(salvo);
        assertEquals("Produto Teste", salvo.getNome());
        assertEquals("Eletrônicos", salvo.getCategoria().getNome());
        assertEquals("Fornecedor A", salvo.getFornecedor().getNome());
    }

    //1.5 Cadastro com Categoria Inexistente
    @Test
    public void cadastroComCategoriaInexistente() {
        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição Teste");
        produto.setQualidade("Alta");
        produto.setPreco(100.00);
        produto.setFornecedor(null);

        // Setar uma categoria com ID inexistente
        Categoria categoria = new Categoria();
        categoria.setId(999);
        produto.setCategoria(categoria);

        // Verificar que uma exceção é lançada
        Exception exception = assertThrows(RuntimeException.class, () -> {
            produtoService.criarProdutos(produto);
        });
    }

    //1.6 Cadastro de Produto com CPF Inconsistente
    @Test
    public void cadastrarProdutoCPFInvalido() {
        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição Teste");
        produto.setQualidade("Alta");
        produto.setPreco(100.00);

        // Criar fornecedor com CPF inválido
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome("Fornecedor Invalido");
        fornecedor.setCPF("00000000000"); // CPF inválido como exemplo
        fornecedorRepository.save(fornecedor);

        produto.setFornecedor(fornecedor);

        // Verificar que uma exceção é lançada devido ao CPF inválido
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            produtoService.criarProdutos(produto);
        });

        assertEquals("CPF do fornecedor é inválido.", exception.getMessage());
    }

}