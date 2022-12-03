/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deltamike.scoa.controller.academico.turma;

import deltamike.scoa.dtos.academico.turma.TurmaDTO;
import deltamike.scoa.model.academico.curso.CursoModel;
import deltamike.scoa.model.academico.disciplina.DisciplinaModel;
import deltamike.scoa.model.academico.sala.SalaModel;
import deltamike.scoa.model.academico.turma.TurmaModel;
import deltamike.scoa.model.usuario.AlunoModel;
import deltamike.scoa.services.academico.turma.TurmaService;
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
@RequestMapping("/academico/turma")
public class TurmaController {
    final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }
    
    @PostMapping
    public ResponseEntity<Object> saveTurma(@RequestBody @Valid TurmaDTO turmaDTO){
        TurmaModel turmaModel = new TurmaModel();
        BeanUtils.copyProperties(turmaDTO, turmaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.turmaService.save(turmaModel));
    }
    
    @PutMapping("/{idTurma}/disciplina/{idDisciplina}")
    public ResponseEntity<Object> colocarDisciplinaEmTurma(@PathVariable Integer idTurma, @PathVariable Integer idDisciplina){
        Optional<TurmaModel> turmaOptional = this.turmaService.getById(idTurma);
        Optional<DisciplinaModel> disciplinaOptional = this.turmaService.getDisciplinaService().getById(idDisciplina);
    
        if(turmaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
        }
        if(disciplinaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disciplina não encontrada");
        }
        
        TurmaModel turmaModel = turmaOptional.get();
        DisciplinaModel disciplinaModel = disciplinaOptional.get();
        
        turmaModel.addDisciplina(disciplinaModel);

        TurmaModel retorno = this.turmaService.save(turmaModel);
        return ResponseEntity.status(HttpStatus.OK).body(retorno);
    }
    
    @DeleteMapping("/{idTurma}/disciplina/{idDisciplina}")
    public ResponseEntity<Object> removerDisciplinaDeTurma(@PathVariable Integer idTurma, @PathVariable Integer idDisciplina){
        Optional<TurmaModel> turmaOptional = this.turmaService.getById(idTurma);
        Optional<DisciplinaModel> disciplinaOptional = this.turmaService.getDisciplinaService().getById(idDisciplina);
    
        if(turmaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
        }
        if(disciplinaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disciplina não encontrada");
        }
        
        TurmaModel turmaModel = turmaOptional.get();
        DisciplinaModel disciplinaModel = disciplinaOptional.get();
        
        turmaModel.removeDisciplina(disciplinaModel);
        
        TurmaModel retorno = this.turmaService.save(turmaModel);
        return ResponseEntity.status(HttpStatus.OK).body(retorno);
    }
    
    @PutMapping("/{idTurma}/aluno/{idAluno}")
    public ResponseEntity<Object> colocarAlunoEmTurma(@PathVariable Integer idTurma, @PathVariable Integer idAluno){
        Optional<TurmaModel> turmaOptional = this.turmaService.getById(idTurma);
        Optional<AlunoModel> alunoOptional = this.turmaService.getAlunoService().getById(idAluno);
    
        if(turmaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
        }
        if(alunoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        
        TurmaModel turmaModel = turmaOptional.get();
        AlunoModel alunoModel = alunoOptional.get();
        
        turmaModel.addAluno(alunoModel);
        
        TurmaModel retorno = this.turmaService.save(turmaModel);
        return ResponseEntity.status(HttpStatus.OK).body(retorno);
    }
    
    @DeleteMapping("/{idTurma}/aluno/{idAluno}")
    public ResponseEntity<Object> removerAlunoDeTurma(@PathVariable Integer idTurma, @PathVariable Integer idAluno){
        Optional<TurmaModel> turmaOptional = this.turmaService.getById(idTurma);
        Optional<AlunoModel> alunoOptional = this.turmaService.getAlunoService().getById(idAluno);
    
        if(turmaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
        }
        if(alunoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        
        TurmaModel turmaModel = turmaOptional.get();
        AlunoModel alunoModel = alunoOptional.get();
        
        turmaModel.removeAluno( alunoModel);
        
        TurmaModel retorno = this.turmaService.save(turmaModel);
        return ResponseEntity.status(HttpStatus.OK).body(retorno);
    }
    
    @PutMapping("/{idTurma}/sala/{idSala}")
    public ResponseEntity<Object> colocarSalaEmTurma(@PathVariable Integer idTurma, @PathVariable Integer idSala){
        Optional<TurmaModel> turmaOptional = this.turmaService.getById(idTurma);
        Optional<SalaModel> salaOptional = this.turmaService.getSalaService().getById(idSala);
        
        if(turmaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
        }
        if(salaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sala não encontrada");
        }
        
        TurmaModel turmaModel = turmaOptional.get();
        SalaModel salaModel = salaOptional.get();
        
        turmaModel.setSala(salaModel);
        
        TurmaModel retorno = this.turmaService.save(turmaModel);
        return ResponseEntity.status(HttpStatus.OK).body(retorno);
    }
    
    @DeleteMapping("/{idTurma}/sala")
    public ResponseEntity<Object> removerSalaDeTurma(@PathVariable Integer idTurma, @PathVariable Integer idSala){
        Optional<TurmaModel> turmaOptional = this.turmaService.getById(idTurma);
        
        if(turmaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
        }
        
        TurmaModel turmaModel = turmaOptional.get();
        turmaModel.setSala(null);
        
        TurmaModel retorno = this.turmaService.save(turmaModel);
        return ResponseEntity.status(HttpStatus.OK).body(retorno);
    }
            
    @PutMapping("/{idTurma}/curso/{idCurso}")
    public ResponseEntity<Object> colocarCursoEmTurma(@PathVariable Integer idTurma, @PathVariable Integer idCurso){
        Optional<TurmaModel> turmaOptional = this.turmaService.getById(idTurma);
        Optional<CursoModel> cursoOptional = this.turmaService.getCursoService().getById(idCurso);
        
        if(turmaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
        }
        if(cursoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
        }
        
        TurmaModel turmaModel = turmaOptional.get();
        CursoModel cursoModel = cursoOptional.get();
        
        turmaModel.setCurso(cursoModel);
        
        TurmaModel retorno = this.turmaService.save(turmaModel);
        return ResponseEntity.status(HttpStatus.OK).body(retorno);
    }
    
    @DeleteMapping("/{idTurma}/curso")
    public ResponseEntity<Object> removerCursoDeTurma(@PathVariable Integer idTurma){
        Optional<TurmaModel> turmaOptional = this.turmaService.getById(idTurma);
        
        if(turmaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
        }
        
        TurmaModel turmaModel = turmaOptional.get();
        turmaModel.setCurso(null);
        
        TurmaModel retorno = this.turmaService.save(turmaModel);
        return ResponseEntity.status(HttpStatus.OK).body(retorno);
    }
    
    public ResponseEntity<Object> deleteTurma(@PathVariable Integer id){
        Optional<TurmaModel> turmaOptional = this.turmaService.getById(id);
        
        if(turmaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
        }
        
        TurmaModel turmaModel = turmaOptional.get();
        List<AlunoModel> alunos = turmaModel.getAlunos();
        List<DisciplinaModel> disciplinas = turmaModel.getDisciplinas();
        //limpa a relação turma-aluno
        for(int i = 0; i < alunos.size(); i = i + 1){
            AlunoModel aluno;
            try {
                aluno = alunos.get(i);
            } catch (IndexOutOfBoundsException e) {
                break;
            }
            
            turmaModel.removeAluno(aluno);
        }
        //limpa a relação turma-disciplina
        for (int i = 0; i < disciplinas.size(); i = i + 1){
            DisciplinaModel disciplina;
            
            try {
                disciplina = disciplinas.get(i);
            } catch (IndexOutOfBoundsException e) {
                break;
            }
            
            turmaModel.removeDisciplina(disciplina);
        }
        
        
        
        this.turmaService.delete(turmaModel);
        return ResponseEntity.status(HttpStatus.OK).body(turmaModel);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id){
        Optional<TurmaModel> turmaOptional = this.turmaService.getById(id);
        
        if(turmaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
        }
        
        TurmaModel turma = turmaOptional.get();
        return ResponseEntity.status(HttpStatus.OK).body(turma);
    }
    
    @GetMapping
    public ResponseEntity<List<TurmaModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.turmaService.getAll());
    }
    
}