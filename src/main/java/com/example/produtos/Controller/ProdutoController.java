package com.example.produtos.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.produtos.Model.Produto;
import com.example.produtos.Service.ProdutosService;

@RestController
@RequestMapping("produto")
public class ProdutoController {
   private final ProdutosService produtosService;
   
   @Autowired
   public ProdutoController(ProdutosService produtosService){
    this.produtosService = produtosService;
   }

   @PostMapping
   public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
    Produto novoProduto = produtosService.criarProdutos(produto);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
   }

   @GetMapping
   public ResponseEntity<List<Produto>> getProdutos(){
    List<Produto> listaDeProdutos = produtosService.retornarProdutos();
    return ResponseEntity.status(HttpStatus.OK).body(listaDeProdutos);
   }
      
   @GetMapping("/{id}")
   public ResponseEntity<Optional<Produto>> getByIdProduto(@PathVariable Integer id){
      Optional<Produto> produto = produtosService.retornarProdutosPorId(id);
      return ResponseEntity.status(HttpStatus.OK).body(produto);
   }

   @PutMapping
   public ResponseEntity<Produto> putByIdProduto(@PathVariable Integer id, @RequestBody Produto novoProduto){
      Produto produto = produtosService.atualizProdutos(id, novoProduto);
      return ResponseEntity.status(HttpStatus.OK).body(produto);
   }
   @DeleteMapping
   public ResponseEntity<Void> putByIdCategoria(@PathVariable Integer id){
      Boolean deleted = produtosService.deleteProduto(id);
      if(deleted){
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      }else{
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
   }
}
