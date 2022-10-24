/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deltamike.scoa.controller.biblioteca.obra;

import deltamike.scoa.dtos.biblioteca.obra.ArtigoDTO;
import deltamike.scoa.dtos.biblioteca.obra.FilmeDTO;
import deltamike.scoa.dtos.biblioteca.obra.JornalDTO;
import deltamike.scoa.dtos.biblioteca.obra.LivroDTO;
import deltamike.scoa.dtos.biblioteca.obra.ManualDTO;
import deltamike.scoa.dtos.biblioteca.obra.RevistaDTO;
import deltamike.scoa.model.biblioteca.obra.ArtigoModel;
import deltamike.scoa.model.biblioteca.obra.FilmeModel;
import deltamike.scoa.model.biblioteca.obra.JornalModel;
import deltamike.scoa.model.biblioteca.obra.LivroModel;
import deltamike.scoa.model.biblioteca.obra.ManualModel;
import deltamike.scoa.model.biblioteca.obra.ObraModel;
import deltamike.scoa.model.biblioteca.obra.RevistaModel;
import deltamike.scoa.services.biblioteca.obra.ObraService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rodri
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/biblioteca/obra")
public class ObraController {
    final ObraService obraService;

    public ObraController(ObraService obraService) {
        this.obraService = obraService;
    }
    
    //Salva um artigo no banco de dados e redireciona para a pagina da biblioteca
    @PostMapping("/artigo")
    public ResponseEntity<Object> saveArtigo(@RequestBody @Valid ArtigoDTO artigoDTO){
        ArtigoModel artigo = new ArtigoModel();
        BeanUtils.copyProperties(artigoDTO, artigo);
        ObraModel retorno = this.obraService.save(artigo);
        return  ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }
    
    //Salva um filme no banco de dados e redireciona para a pagina da biblioteca
    @PostMapping("/filme")
    public String saveFilme(@ModelAttribute @Valid FilmeDTO filmeDTO){
        FilmeModel filme = new FilmeModel();
        BeanUtils.copyProperties(filmeDTO, filme);
        this.obraService.save(filme);
        return "redirect:/biblioteca";
    }
    
    //Serve a pagina de cadastro de filmes
    @GetMapping("/filme")
    public String cadastroFilme(Model model){
        model.addAttribute("filmedto", new FilmeDTO());
        return "registrar_filme";
    }
    
    
    @PostMapping("/jornal")
    public String saveJornal(@ModelAttribute @Valid JornalDTO jornalDTO){
        JornalModel jornal = new JornalModel();
        BeanUtils.copyProperties(jornalDTO, jornal);
        this.obraService.save(jornal);
        return "redirect:/biblioteca";
    }
    
    @GetMapping("/jornal")
    public String cadastroJornal(Model model){
        model.addAttribute("jornaldto", new JornalDTO());
        return "registrar_jornal";
    }
    
    @PostMapping("/livro")
    public String saveLivro(@ModelAttribute @Valid LivroDTO livroDTO){
        LivroModel livro = new LivroModel();
        BeanUtils.copyProperties(livroDTO, livro);
        this.obraService.save(livro);
        return "redirect:/biblioteca";
    }
    
    @GetMapping("/livro")
    public String cadastroLivro(Model model){
        model.addAttribute("livrodto", new LivroDTO());
        return "registrar_livro";
    }
    
    @PostMapping("/manual")
    public String saveManual(@ModelAttribute @Valid ManualDTO manualDTO){
        ManualModel manual = new ManualModel();
        BeanUtils.copyProperties(manualDTO, manual);
        this.obraService.save(manual);
        return "redirect:/biblioteca";
    }
    
    @GetMapping("/manual")
    public String cadastroManual(Model model){
        model.addAttribute("manualdto", new ManualDTO());
        return "registrar_manual";
    }
    
    @PostMapping("/revista")
    public String saveRevista(@ModelAttribute @Valid RevistaDTO revistaDTO){
        RevistaModel revista = new RevistaModel();
        BeanUtils.copyProperties(revistaDTO, revista);
        this.obraService.save(revista);
        return "redirect:/biblioteca";
    }
    
    @GetMapping("/revista")
    public String cadastroRevista(Model model){
        model.addAttribute("revistadto", new RevistaDTO());
        return "registrar_revista";
    }
    
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String delete_obra(@PathVariable Integer id){
        System.out.println("O ID QUE EU ESTOU TENTADO DELETAR É: " + id);
        
        Optional<ObraModel> alvo = this.obraService.getById(id);
        
        if (alvo.isPresent()){
            this.obraService.delete(alvo.get());
            return "redirect:/biblioteca";
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A obra em questão não existe na base de dados").toString();
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getObraById(@PathVariable Integer id){
        Optional<ObraModel> obraOptional = this.obraService.getById(id);
        if (obraOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(obraOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não encontrada");
    }
    
    @GetMapping
    public ResponseEntity<List<ObraModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.obraService.getAll());
    }
}
