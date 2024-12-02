package com.example.produtos.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.produtos.Model.Categoria;
import com.example.produtos.Repository.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Autowired /* é tipo o BeforeEach */
    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria criarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> retornarCategorias(){
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> retornarCategoriaPorId(Integer id){
        return categoriaRepository.findById(id);
    }

public Categoria atualizCategoria(Integer id, Categoria categoria) {
    return categoriaRepository.findById(id)
        .map(categoriaNaoAtualizada -> {
            categoriaNaoAtualizada.setNome((categoria.getNome() == null) ? categoriaNaoAtualizada.getNome() : categoria.getNome());
            categoriaNaoAtualizada.setProdutos((categoria.getProdutos() == null) ? categoriaNaoAtualizada.getProdutos() : categoria.getProdutos());
            return categoriaRepository.save(categoriaNaoAtualizada);
        })
        .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o id: " + id));
    }
 

    public boolean deleteCategoria(Integer id){
        try {
            categoriaRepository.deleteById(id);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

}

/*fazer nos outros do service */