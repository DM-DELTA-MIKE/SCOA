/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package deltamike.scoa.view.usuario.cadastrar;

import deltamike.scoa.controller.usuario.AlunoController;
import deltamike.scoa.controller.usuario.UsuarioController;
import deltamike.scoa.dtos.usuario.AlunoDTO;
import deltamike.scoa.model.usuario.AlunoModel;
import deltamike.scoa.model.usuario.UsuarioModel;
import deltamike.scoa.view.Dashboard;
import java.util.List;

/**
 *
 * @author rodri
 */
public class CadastrarAlunoForm extends javax.swing.JFrame {
    
    /**
     * Creates new form CadastrarAlunoForm
     */
    public CadastrarAlunoForm() {
        initComponents();
        
        //populando combo box de usuarios...
        UsuarioController controller = (UsuarioController) Dashboard.springAppContext.getBean("usuarioController");
        List<UsuarioModel> usuarios = controller.getAll().getBody();
        
        for(UsuarioModel usuario : usuarios){
            this.UsuarioComboBox.addItem(usuario.getId());
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
        anoLetivoTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cargaHorariaTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        matriculaTextField = new javax.swing.JTextField();
        CadastrarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Usuario");

        jLabel2.setText("Ano letivo");

        jLabel3.setText("Carga horaria");

        jLabel4.setText("Matricula");

        CadastrarButton.setText("Cadastrar");
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
                    .addComponent(matriculaTextField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 308, Short.MAX_VALUE)
                        .addComponent(CadastrarButton))
                    .addComponent(UsuarioComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(anoLetivoTextField)
                    .addComponent(cargaHorariaTextField))
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
                .addComponent(anoLetivoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cargaHorariaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(matriculaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(CadastrarButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CadastrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarButtonActionPerformed
        
        //pegando o controller de alunos
        AlunoController controller;
        try {
            controller = (AlunoController) Dashboard.springAppContext.getBean("alunoController");
        } catch (Exception e) {
            Dashboard.alert("Erro ao resgatar controller de alunos");
            return;
        }
        
        

//pegando o controller de usuarios
        UsuarioController usuarioController;
        try {
            usuarioController = (UsuarioController) Dashboard.springAppContext.getBean("usuarioController");
        } catch (Exception e) {
            Dashboard.alert("Erro ao resgatar controller de usuarios");
            return;
        }
        
        UsuarioModel usuario = (UsuarioModel) usuarioController.getById(this.UsuarioComboBox.getItemAt(this.UsuarioComboBox.getSelectedIndex())).getBody();
        
        if(usuario.getAluno() != null){
            Dashboard.alert("O usuario escolhido ja est?? cadastrado como um aluno!");
            return;
        }
        
        //validando ano letivo
        Integer ano;
        try {
            ano = Integer.valueOf(this.anoLetivoTextField.getText());
        } catch (NumberFormatException e) {
            Dashboard.alert("Por favor insira um  numero");
            return;
        }
        
        //validando carga horaria
        Integer carga;
        try {
            carga = Integer.valueOf(this.cargaHorariaTextField.getText());
        } catch (NumberFormatException e) {
            Dashboard.alert("Por favor insira um  numero");
            return;
        }
        
        
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setAno_letivo(ano);
        alunoDTO.setCarga_horaria(carga);
        alunoDTO.setMatricula(this.matriculaTextField.getText());
        alunoDTO.setSituacao("Matriculado");
        
        AlunoModel aluno = controller.save(alunoDTO).getBody();
        
        
        
        controller.colocarAlunoEmUsuario(aluno.getId(), usuario.getId());
        Dashboard.alert("Aluno cadastrado com sucesso");
       
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
            java.util.logging.Logger.getLogger(CadastrarAlunoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarAlunoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarAlunoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarAlunoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarAlunoForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CadastrarButton;
    private javax.swing.JComboBox<String> UsuarioComboBox;
    private javax.swing.JTextField anoLetivoTextField;
    private javax.swing.JTextField cargaHorariaTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField matriculaTextField;
    // End of variables declaration//GEN-END:variables
}
