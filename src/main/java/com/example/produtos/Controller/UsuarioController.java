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

import com.example.produtos.Model.Usuario;
import com.example.produtos.Service.UsuarioService;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
   private final UsuarioService usuarioService;

     @Autowired
   public UsuarioController(UsuarioService usuarioService){
    this.usuarioService = usuarioService;
   }

   @PostMapping
   public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario){
    Usuario novoUsuario = usuarioService.criarUsuarios(usuario);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
   }

      @GetMapping
   public ResponseEntity<List<Usuario>> getUsuario(){
    List<Usuario> listaDeUsuarios = usuarioService.retornarUsuario();
    return ResponseEntity.status(HttpStatus.OK).body(listaDeUsuarios);
    
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getByIdUsuario(@PathVariable Integer id){
       Optional<Usuario> usuario = usuarioService.retornarUsuarioPorId(id);
       return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }
 
    @PutMapping
    public ResponseEntity<Usuario> putByIdUsuario(@PathVariable Integer id, @RequestBody Usuario novoUsuario){
       Usuario usuario = usuarioService.atualizUsuario(id, novoUsuario);
       return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }
    @DeleteMapping
    public ResponseEntity<Void> putByIdCategoria(@PathVariable Integer id){
       Boolean deleted = usuarioService.deleteUsuario(id);
       if(deleted){
          return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
       }else{
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }
 }
 