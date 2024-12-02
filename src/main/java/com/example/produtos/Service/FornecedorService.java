package com.example.produtos.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.produtos.Model.Fornecedor;
import com.example.produtos.Repository.FornecedorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;

    @Autowired /* é tipo o BeforeEach */
    public FornecedorService(FornecedorRepository fornecedorRepository){
        this.fornecedorRepository = fornecedorRepository;
    }

    public Fornecedor criarFornecedor(Fornecedor fornecedor){
        return fornecedorRepository.save(fornecedor);
    }

    public List<Fornecedor> retornarFornecedors(){
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> retornarFornecedorPorId(Integer id){
        return fornecedorRepository.findById(id);
    }

public Fornecedor atualizFornecedor(Integer id, Fornecedor fornecedor) {
    return fornecedorRepository.findById(id)
        .map(fornecedorNaoAtualizada -> {
            fornecedorNaoAtualizada.setNome((fornecedor.getNome() == null) ? fornecedorNaoAtualizada.getNome() : fornecedor.getNome());
           fornecedorNaoAtualizada.setProdutos((fornecedor.getProdutos() == null) ? fornecedorNaoAtualizada.getProdutos() : fornecedor.getProdutos());
           fornecedorNaoAtualizada.setCNPJ((fornecedor.getCNPJ() == null) ? fornecedorNaoAtualizada.getCNPJ() : fornecedor.getCNPJ());
           fornecedorNaoAtualizada.setCPF((fornecedor.getCPF() == null) ? fornecedorNaoAtualizada.getCPF() : fornecedor.getCPF());
            return fornecedorRepository.save(fornecedorNaoAtualizada);
        })
        .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrada com o id: " + id));
    }

    public boolean deleteFornecedor(Integer id){
        try {
            fornecedorRepository.deleteById(id);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

}

/*fazer nos outros do service */