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

import com.example.produtos.Model.Categoria;
import com.example.produtos.Service.CategoriaService;

@RestController
@RequestMapping("categoria")
public class CategoriaController {
   private final CategoriaService categoriaService;
   
   @Autowired
   public CategoriaController(CategoriaService categoriaService){
    this.categoriaService = categoriaService;
   }

   @PostMapping
   public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria){
    Categoria novaCategoria = categoriaService.criarCategoria(categoria);
    return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
   }

   @GetMapping
   public ResponseEntity<List<Categoria>> getCategoria(){
    List<Categoria> listaDeCategoria = categoriaService.retornarCategorias();
    return ResponseEntity.status(HttpStatus.OK).body(listaDeCategoria);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Optional<Categoria>> getByIdCategoria(@PathVariable Integer id){
      Optional<Categoria> categoria = categoriaService.retornarCategoriaPorId(id);
      return ResponseEntity.status(HttpStatus.OK).body(categoria);
   }

   @PutMapping
   public ResponseEntity<Categoria> putByIdCategoria(@PathVariable Integer id, @RequestBody Categoria novaCategoria){
      Categoria categoria = categoriaService.atualizCategoria(id, novaCategoria);
      return ResponseEntity.status(HttpStatus.OK).body(categoria);
   }
   @DeleteMapping
   public ResponseEntity<Void> putByIdCategoria(@PathVariable Integer id){
      Boolean deleted = categoriaService.deleteCategoria(id);
      if(deleted){
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      }else{
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
   }
}
