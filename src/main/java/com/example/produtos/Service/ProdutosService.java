package com.example.produtos.Service;

import java.util.List;
import java.util.Optional;

import com.example.produtos.Model.Fornecedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.produtos.Model.Produto;
import com.example.produtos.Repository.ProdutosRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutosService {
    private final ProdutosRepository produtosRepository;

    @Autowired /* é tipo o BeforeEach */
    public ProdutosService(ProdutosRepository produtosRepository){
        this.produtosRepository = produtosRepository;
    }

    public Produto criarProdutos(Produto produtos) {
        System.out.println("Produto: " + produtos); // Verificação de debug
        if (produtos.getPreco() < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }

        Fornecedor fornecedor = produtos.getFornecedor();

        if (fornecedor != null) {
            if (!isValidCPF(fornecedor.getCPF())) {
                throw new IllegalArgumentException("CPF do fornecedor é inválido.");
            }
        }

        Produto salvo = produtosRepository.save(produtos);
        System.out.println("Produto salvo: " + salvo); // Verificação de debug
        return salvo;
    }


    public List<Produto> retornarProdutos(){
        return produtosRepository.findAll();
    }

    public Optional<Produto> retornarProdutosPorId(Integer id){
        return produtosRepository.findById(id);
    }

public Produto atualizProdutos(Integer id, Produto produto){
    return produtosRepository.findById(id)
        .map(produtoNaoAtualizada -> {
            produtoNaoAtualizada.setNome((produto.getNome() == null) ? produtoNaoAtualizada.getNome() : produto.getNome());
            produtoNaoAtualizada.setDescricao((produto.getDescricao() == null) ? produtoNaoAtualizada.getDescricao() : produto.getDescricao());
            produtoNaoAtualizada.setQualidade((produto.getQualidade() == null) ? produtoNaoAtualizada.getQualidade() : produto.getQualidade());
            produtoNaoAtualizada.setFornecedor((produto.getFornecedor() == null) ? produtoNaoAtualizada.getFornecedor() : produto.getFornecedor());
            produtoNaoAtualizada.setPreco((produto.getPreco() == 0) ? produtoNaoAtualizada.getPreco() : produto.getPreco());
            produtoNaoAtualizada.setCategoria((produto.getCategoria() == null) ? produtoNaoAtualizada.getCategoria() : produto.getCategoria());
            return produtosRepository.save(produtoNaoAtualizada);
        })
        .orElseThrow(() -> new EntityNotFoundException("Produtos não encontrada com o id: " + id));
    }

    public boolean deleteProduto(Integer id){
        try {
            produtosRepository.deleteById(id);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    private boolean isValidCPF(String cpf) {
        // Simples validação de exemplo, você pode implementar a lógica real ou usar bibliotecas específicas.
        return cpf != null && cpf.matches("\\d{11}") && !cpf.equals("00000000000");
    }

}
