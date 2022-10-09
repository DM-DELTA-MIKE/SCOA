/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deltamike.scoa.controller.almoxarifado.relatorio;

import deltamike.scoa.dtos.almoxarifado.relatorio.RelatorioEntradaDTO;
import deltamike.scoa.dtos.almoxarifado.relatorio.RelatorioSaidaDTO;
import deltamike.scoa.model.almoxarifado.produto.ProdutoModel;
import deltamike.scoa.model.almoxarifado.relatorio.RelatorioEntradaModel;
import deltamike.scoa.model.almoxarifado.relatorio.RelatorioModel;
import deltamike.scoa.model.almoxarifado.relatorio.RelatorioSaidaModel;
import deltamike.scoa.services.almoxarifado.relatorio.RelatorioService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/almoxarifado/relatorio")
public class RelatorioController {
    final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }
    
    @PostMapping("/entrada")
    public ResponseEntity<Object> saveRelatorioEntrada(@RequestBody @Valid RelatorioEntradaDTO relatorioEntradaDTO){
        RelatorioEntradaModel relatorioEntradaModel = new RelatorioEntradaModel();
        BeanUtils.copyProperties(relatorioEntradaDTO, relatorioEntradaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.relatorioService.save(relatorioEntradaModel));
    }
    
    @PostMapping("/saida")
    public ResponseEntity<Object> saveRelatorioSaida(@RequestBody @Valid RelatorioSaidaDTO relatorioSaidaDTO){
        RelatorioSaidaModel relatorioSaidaModel = new RelatorioSaidaModel();
        BeanUtils.copyProperties(relatorioSaidaDTO, relatorioSaidaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.relatorioService.save(relatorioSaidaModel));
    }
    
    @PutMapping("/{idRelatorio}/produto/{idProduto}")
    public ResponseEntity<Object> adicionarProdutoEmRelatorio(@PathVariable Integer idRelatorio, @PathVariable Integer idProduto){
        Optional<RelatorioModel> relatorioOptional = this.relatorioService.getById(idRelatorio);
        Optional<ProdutoModel> produtoOptional = this.relatorioService.getProdutoService().getById(idProduto);
        
        if (relatorioOptional.isEmpty() || produtoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso não encontrado");
        }
        
        RelatorioModel relatorioModel = relatorioOptional.get();
        ProdutoModel produtoModel = produtoOptional.get();
        
        relatorioModel.setProduto(produtoModel);
        return ResponseEntity.status(HttpStatus.OK).body(this.relatorioService.save(relatorioModel));
        
    }
    
    @GetMapping
    public ResponseEntity<List<RelatorioModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.relatorioService.getAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmprestimoById(@PathVariable Integer id){
        Optional<RelatorioModel> relatorioModel = this.relatorioService.getById(id);
        
        if (relatorioModel.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(relatorioModel.get());
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Emprestimo não encontrado");
    }
    
}