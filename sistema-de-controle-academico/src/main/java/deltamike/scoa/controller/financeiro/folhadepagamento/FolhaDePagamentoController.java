/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deltamike.scoa.controller.financeiro.folhadepagamento;

import deltamike.scoa.dtos.financeiro.folhadepagamento.FolhaDePagamentoDTO;
import deltamike.scoa.model.financeiro.folhadepagamento.FolhaDePagamentoModel;
import deltamike.scoa.services.financeiro.folhadepagamento.FolhaDePagamentoService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rodri
 */
@RestController
@RequestMapping("/financeiro/pagamento")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FolhaDePagamentoController {
    final FolhaDePagamentoService folhaDePagamentoService;

    public FolhaDePagamentoController(FolhaDePagamentoService folhaDePagamentoService) {
        this.folhaDePagamentoService = folhaDePagamentoService;
    }
    
    
    
    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody @Valid FolhaDePagamentoDTO folhaDePagamentoDTO){
        
        FolhaDePagamentoModel folhaDePagamentoModel = new FolhaDePagamentoModel();
        BeanUtils.copyProperties(folhaDePagamentoDTO, folhaDePagamentoModel);
        
        FolhaDePagamentoModel retorno = this.folhaDePagamentoService.save(folhaDePagamentoModel);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
        
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id){
        Optional<FolhaDePagamentoModel> optional = this.folhaDePagamentoService.getById(id);
        
        if (optional.isPresent()){
            this.folhaDePagamentoService.delete(optional.get());
            return ResponseEntity.status(HttpStatus.OK).body(optional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Folha de pagamento não encontrada");
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id){
        Optional<FolhaDePagamentoModel> optional = this.folhaDePagamentoService.getById(id);
        
        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optional.get());
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Folha de pagamento não encontrada");
    }
    
    @GetMapping
    public ResponseEntity<List<FolhaDePagamentoModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.folhaDePagamentoService.getAll());
    }
}
