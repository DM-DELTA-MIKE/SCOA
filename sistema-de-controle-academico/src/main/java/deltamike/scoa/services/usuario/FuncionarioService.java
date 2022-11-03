/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deltamike.scoa.services.usuario;

import deltamike.scoa.model.usuario.FuncionarioModel;
import deltamike.scoa.model.usuario.UsuarioModel;
import deltamike.scoa.repositories.usuario.FuncionarioRepository;
import deltamike.scoa.services.almoxarifado.relatorio.RelatorioService;
import deltamike.scoa.services.biblioteca.emprestimo.EmprestimoService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author rodri
 */
 @Service
public class FuncionarioService {
    FuncionarioRepository funcionarioRepository;
     RelatorioService relatorioService;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, RelatorioService relatorioService) {
        this.funcionarioRepository = funcionarioRepository;
        this.relatorioService = relatorioService;
    }
    
     
    @Transactional
    public FuncionarioModel save(FuncionarioModel u){
        return this.funcionarioRepository.saveAndFlush(u);
    }
    
    @Transactional
    public void delete(FuncionarioModel u){
        this.funcionarioRepository.delete(u);
    }
    
    public void deleteById(Integer id){
        Optional<FuncionarioModel> userOptional = this.getById(id);
        
        if (userOptional.isPresent()){
            this.delete(userOptional.get());
        }
    }
    
    public boolean existsById(Integer id){
        return this.funcionarioRepository.existsById(id);
    }
    
    public List<FuncionarioModel> getAll(){
        return this.funcionarioRepository.findAll();
    }
    
    public Optional<FuncionarioModel> getById(Integer id){
        return this.funcionarioRepository.findById(id);
    }

    public RelatorioService getRelatorioService() {
        return relatorioService;
    }
    
}
