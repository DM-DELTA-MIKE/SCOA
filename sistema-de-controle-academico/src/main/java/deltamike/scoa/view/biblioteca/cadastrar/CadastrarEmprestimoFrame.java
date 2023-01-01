/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package deltamike.scoa.view.biblioteca.cadastrar;

import deltamike.scoa.controller.biblioteca.emprestimo.EmprestimoController;
import deltamike.scoa.controller.biblioteca.obra.ObraController;
import deltamike.scoa.controller.usuario.UsuarioController;
import deltamike.scoa.dtos.biblioteca.emprestimo.EmprestimoDTO;
import deltamike.scoa.model.biblioteca.emprestimo.EmprestimoModel;
import deltamike.scoa.model.biblioteca.obra.ObraModel;
import deltamike.scoa.model.usuario.UsuarioModel;
import deltamike.scoa.util.StringToLocalDateConverter;
import deltamike.scoa.view.Dashboard;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DateFormatter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

/**
 *
 * @author rodri
 */
public class CadastrarEmprestimoFrame extends javax.swing.JFrame {
    private List<ObraModel> listaDeObras;
    /**
     * Creates new form CadastrarEmprestimoFrame
     */
    public CadastrarEmprestimoFrame() {
        initComponents();
        
        //populando combo box de usuarios
        UsuarioController usuarioController = (UsuarioController) Dashboard.springAppContext.getBean("usuarioController");
        List<UsuarioModel> usuarios = usuarioController.getAll().getBody();
        
        for(UsuarioModel usuario : usuarios){
            this.UsuarioComboBox.addItem(usuario.getId());
        }
        
        //populando combo box de obras
        ObraController obraController = (ObraController) Dashboard.springAppContext.getBean("obraController");
        listaDeObras = obraController.getAll().getBody();
        
        for(ObraModel obra : listaDeObras){
            this.ObraComboBox.addItem(obra.getTitulo() + " (" + obra.getTIPO() + ")");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        UsuarioComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        ObraComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        CadastrarButton = new javax.swing.JButton();
        prazoTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Usuario");

        UsuarioComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuarioComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Obra");

        jLabel3.setText("Prazo");

        CadastrarButton.setText("Criar Emprestimo");
        CadastrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UsuarioComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ObraComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 299, Short.MAX_VALUE)
                        .addComponent(CadastrarButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(prazoTextField))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsuarioComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ObraComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prazoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(CadastrarButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UsuarioComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuarioComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsuarioComboBoxActionPerformed

    private void CadastrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarButtonActionPerformed
        // TODO add your handling code here:
        EmprestimoController emprestimoController = (EmprestimoController) Dashboard.springAppContext.getBean("emprestimoController");
        
        LocalDate prazo;
        try {
            prazo = StringToLocalDateConverter.convert(this.prazoTextField.getText());
        } catch (Exception ex) {
            Dashboard.alert(ex.toString());
            return;
        }
        //salvando emprestimo apenas com o prazo;
        EmprestimoDTO emprestimoDTO= new EmprestimoDTO();
        emprestimoDTO.setPrazo(prazo);
        //EmprestimoModel emprestimoModel = (EmprestimoModel) emprestimoController.saveEmprestimo(emprestimoDTO).getBody();
        
        //pegando o usuario
        UsuarioController usuarioController = (UsuarioController) Dashboard.springAppContext.getBean("usuarioController");
        UsuarioModel usuarioModel = (UsuarioModel) usuarioController.getById(this.UsuarioComboBox.getItemAt(this.UsuarioComboBox.getSelectedIndex())).getBody();
        
        //relacionando usuario com emprestimo
        //emprestimoController.adicionarUsuarioEmEmprestimo(emprestimoModel.getId(), usuarioModel.getId());
        
        //pegando obra
        ObraModel obraModel = this.listaDeObras.get(this.ObraComboBox.getSelectedIndex());
        
        //relacionando obra com emprestimo
        //emprestimoController.adicionarObraEmEmprestimo(emprestimoModel.getId(), obraModel.getId());
        emprestimoDTO.setUser(usuarioModel);
        ArrayList<ObraModel> obras = new ArrayList<>();
        obras.add(obraModel);
        emprestimoDTO.setObras(obras);
        emprestimoController.saveEmprestimo(emprestimoDTO);
        
        Dashboard.alert("Emprestimo cadastrado com sucesso!");
        
        
    }//GEN-LAST:event_CadastrarButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastrarEmprestimoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarEmprestimoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarEmprestimoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarEmprestimoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarEmprestimoFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CadastrarButton;
    private javax.swing.JComboBox<String> ObraComboBox;
    private javax.swing.JComboBox<String> UsuarioComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField prazoTextField;
    // End of variables declaration//GEN-END:variables
}
