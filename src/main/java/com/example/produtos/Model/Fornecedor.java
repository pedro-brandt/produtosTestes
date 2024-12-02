package com.example.produtos.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Fornecedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String CNPJ;

    @Column(nullable = false)
    private String CPF;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.DETACH)
    private List<Produto> produtos;

    public Fornecedor(int i, String nome) {
    }

    public Fornecedor(int i, String nome, String CPF) {
    }

    public Fornecedor() {

    }
}
