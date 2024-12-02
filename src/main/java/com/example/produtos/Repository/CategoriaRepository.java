package com.example.produtos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.produtos.Model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
}
