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

/*Pessoa -> classe */
@Data /* Data gera geters, seters e construtores automaticamente, para não escrevelos */
@Entity /*vai ser mapeado de acordo */
public class Categoria {

    @Id /*anotação principal para identificar a chame primaria da entidade */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.DETACH)
    private List<Produto> produtos;


    public Categoria(int id, String nome) {
    }

    public Categoria() {

    }
}