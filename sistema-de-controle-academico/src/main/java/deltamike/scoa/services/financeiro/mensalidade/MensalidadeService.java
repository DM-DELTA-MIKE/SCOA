/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deltamike.scoa.services.financeiro.mensalidade;

import deltamike.scoa.model.financeiro.mensalidade.MensalidadeModel;
import deltamike.scoa.repositories.financeiro.mensalidade.MensalidadeRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author rodri
 */
@Service
public class MensalidadeService {
    final MensalidadeRepository mensalidadeRepository;

    public MensalidadeService(MensalidadeRepository mensalidadeRepository) {
        this.mensalidadeRepository = mensalidadeRepository;
    }
    
    @Transactional
    public MensalidadeModel save(MensalidadeModel mensalidade){
        return this.mensalidadeRepository.saveAndFlush(mensalidade);
    }
    
    @Transactional
    public void delete(MensalidadeModel mensalidade){
        this.mensalidadeRepository.delete(mensalidade);
    }
    
    public Optional<MensalidadeModel> getById(Integer id){
        return this.mensalidadeRepository.findById(id);
    }
    
    public List<MensalidadeModel> getAll(){
        return this.mensalidadeRepository.findAll();
    }
}
