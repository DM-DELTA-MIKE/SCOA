/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deltamike.scoa.repositories.almoxarifado.produto;

import deltamike.scoa.model.almoxarifado.produto.ProdutoNaoConsumivelModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rodri
 */
public interface ProdutoNaoConsumivelRepository extends JpaRepository<ProdutoNaoConsumivelModel, String>{
    
}
