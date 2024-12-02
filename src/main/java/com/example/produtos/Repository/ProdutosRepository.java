package com.example.produtos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.produtos.Model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Integer>{
    
}
