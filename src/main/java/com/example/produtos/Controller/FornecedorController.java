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

import com.example.produtos.Model.Fornecedor;
import com.example.produtos.Service.FornecedorService;

@RestController
@RequestMapping("fornecedor")
public class FornecedorController {
   private final FornecedorService fornecedorService;
   
   @Autowired
   public FornecedorController(FornecedorService fornecedorService){
    this.fornecedorService = fornecedorService;
   }

   @PostMapping
   public ResponseEntity<Fornecedor> postFornecedor(@RequestBody Fornecedor fornecedor){
    Fornecedor novoFornecedor = fornecedorService.criarFornecedor(fornecedor);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoFornecedor);
   }

   @GetMapping
   public ResponseEntity<List<Fornecedor>> getFornecedor(){
    List<Fornecedor> listaDeFornecedor = fornecedorService.retornarFornecedors();
    return ResponseEntity.status(HttpStatus.OK).body(listaDeFornecedor);
   }
   
   @GetMapping("/{id}")
   public ResponseEntity<Optional<Fornecedor>> getByIdFornecedor(@PathVariable Integer id){
      Optional<Fornecedor> fornecedor = fornecedorService.retornarFornecedorPorId(id);
      return ResponseEntity.status(HttpStatus.OK).body(fornecedor);
   }

   @PutMapping
   public ResponseEntity<Fornecedor> putByIdFornecedor(@PathVariable Integer id, @RequestBody Fornecedor novoFornecedor){
      Fornecedor fornecedor = fornecedorService.atualizFornecedor(id, novoFornecedor);
      return ResponseEntity.status(HttpStatus.OK).body(fornecedor);
   }
   @DeleteMapping
   public ResponseEntity<Void> putByIdCategoria(@PathVariable Integer id){
      Boolean deleted = fornecedorService.deleteFornecedor(id);
      if(deleted){
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      }else{
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
   }
}
