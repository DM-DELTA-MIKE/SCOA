/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deltamike.scoa.controller.usuario;

import deltamike.scoa.dtos.usuario.FuncionarioDTO;
import deltamike.scoa.model.almoxarifado.relatorio.RelatorioModel;
import deltamike.scoa.model.usuario.FuncionarioModel;
import deltamike.scoa.services.usuario.FuncionarioService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rodri
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuario/funcionario")
public class FuncionarioController {
    final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
    
    @PostMapping()
    public ResponseEntity<FuncionarioModel> saveFuncionario(@RequestBody @Valid FuncionarioDTO funcionarioDTO){
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        BeanUtils.copyProperties(funcionarioDTO, funcionarioModel);
        //deletar usuario se existir identico, e copiar tudo para esta nova instância, e cadastrar novamente como funcionario...
        return ResponseEntity.status(HttpStatus.CREATED).body((FuncionarioModel)this.funcionarioService.save(funcionarioModel));
    }
    
    
    @PutMapping("{idFuncionario}/relatorio/{idRelatorio}")
    public ResponseEntity<Object> adicionarRelatorioEmFuncionario(@PathVariable Integer idFuncionario, @PathVariable Integer idRelatorio){
        Optional<FuncionarioModel> funcionarioOptional = this.funcionarioService.getById(idFuncionario);
        Optional<RelatorioModel> relatorioOptional = this.funcionarioService.getRelatorioService().getById(idRelatorio);
        
        if (funcionarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("funcionario não encontrado");
        }
        
        if (relatorioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Relatorio não encontrado");
        }
        
        FuncionarioModel funcionarioModel = funcionarioOptional.get();
        RelatorioModel  relatorioModel = relatorioOptional.get();
        
        relatorioModel.setFuncionario(funcionarioModel);
        
        List<RelatorioModel> relatorios = funcionarioModel.getRelatorios();
        if(!relatorios.contains(relatorioModel)){
            relatorios.add(relatorioModel);
        }
        
        funcionarioModel.setRelatorios(relatorios);
        
        FuncionarioModel retorno = this.funcionarioService.save(funcionarioModel);
        this.funcionarioService.getRelatorioService().save(relatorioModel);
        return ResponseEntity.status(HttpStatus.OK).body(retorno);
    }
    
}
