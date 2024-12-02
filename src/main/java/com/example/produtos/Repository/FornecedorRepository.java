package com.example.produtos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.produtos.Model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
    
}
