package com.example.produtos.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.produtos.Model.Usuario;
import com.example.produtos.Repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired /* é tipo o BeforeEach */
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuarios(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> retornarUsuario(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> retornarUsuarioPorId(Integer id){
        return usuarioRepository.findById(id);
    }

public Usuario atualizUsuario(Integer id, Usuario usuario){
    return usuarioRepository.findById(id)
        .map(usuarioNaoAtualizado -> {
            usuarioNaoAtualizado.setNome((usuario.getNome() == null) ? usuarioNaoAtualizado.getNome() : usuario.getNome());
            usuarioNaoAtualizado.setSenha((usuario.getSenha() == null) ? usuarioNaoAtualizado.getSenha() : usuario.getSenha());

            return usuarioRepository.save(usuarioNaoAtualizado);
        })
        .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrada com o id: " + id));
    }

    public boolean deleteUsuario(Integer id){
        try {
            usuarioRepository.deleteById(id);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

}
