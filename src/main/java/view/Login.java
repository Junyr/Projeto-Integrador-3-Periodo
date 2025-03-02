/*
 * Copyright (C) 2024 Willian Junior <willianjunior.c.f@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package view;

import banco.UsuarioDAO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Usuario;
import view.CRUD.Read;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @author Marcos Gabriel <marcossetecruzsoares@gmail.com>
 * @brief JFrame Login
 */

public class Login extends javax.swing.JFrame {

    public Login() {
        // Inicialização dos componentes
        initComponents();
        // Responsavel por colocar a janela no meio da tela
        this.setLocationRelativeTo(null);
        // Responsavel por colocar o titulo na janela
        this.setTitle("Login");
        // Responsavel por mudar o icone da janela
        ImageIcon icon = new ImageIcon("src/main/resources/image/icon.png");
        this.setIconImage(icon.getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_Login = new javax.swing.JTextField();
        jButton_Cadastrar = new javax.swing.JButton();
        jButton_Entrar = new javax.swing.JButton();
        jPasswordField_Senha = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(249, 246, 246));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Fundo Login.png"))); // NOI18N

        jTextField_Login.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_Login.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_Login.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_LoginKeyPressed(evt);
            }
        });

        jButton_Cadastrar.setBackground(new java.awt.Color(24, 100, 204));
        jButton_Cadastrar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_Cadastrar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Cadastrar.setText("Cadastrar");
        jButton_Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CadastrarActionPerformed(evt);
            }
        });

        jButton_Entrar.setBackground(new java.awt.Color(24, 100, 204));
        jButton_Entrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_Entrar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Entrar.setText("Entrar");
        jButton_Entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EntrarActionPerformed(evt);
            }
        });

        jPasswordField_Senha.setBackground(new java.awt.Color(255, 255, 255));
        jPasswordField_Senha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPasswordField_Senha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField_SenhaKeyPressed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Senha");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Username");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Login");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Não possui conta?");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField_Login, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6)
                                    .addComponent(jPasswordField_Senha, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton_Cadastrar))
                                    .addComponent(jButton_Entrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(68, 68, 68))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordField_Senha, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Cadastrar)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Metodo para validar usuario
    private void validarUsuario(){
        // Validação para caso o usuario digite errado, aceita CPF e email
        if(jTextField_Login.getText().matches("^.+@(gmail\\.com|hotmail\\.com|outlook\\.(com|com\\.br))$") ||
                Usuario.ValidarCPF(String.valueOf(jTextField_Login.getText()))){
            // Validação para caso o usuario digite errado, tamanho minimo 5 e maximo 50
            if(String.valueOf(jPasswordField_Senha.getPassword()).matches(".{5,50}")){
                // Armazeno os textos em variaveis
                String login = jTextField_Login.getText();
                String senha = String.valueOf(jPasswordField_Senha.getPassword());
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                /** Chamo o metodo da classe UsuarioDAO para verificar se o login e senha fornecido estão presentes no banco
                 * Caso não esteja de acordo com o banco, vai retorna nulo*/
                Usuario usuario = usuarioDAO.verificarLogin(login, senha);
                
                // Caso não for nulo
                if(usuario != null){
                    // login bem sucedido
                    JOptionPane.showMessageDialog(this, "Bem-vindo, " + usuario.getNome() + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    // Da mesma forma que fiz no cadastro para aparecer a tela principal
                    Read menu = new Read();
                    this.dispose();
                    menu.setVisible(true);
                    // Erros que podem acontecer se não passar pelas verificações
                } else JOptionPane.showMessageDialog(this, "Email ou senha incorreto", "Erro", JOptionPane.ERROR_MESSAGE);
            } else JOptionPane.showMessageDialog(this, "Senha invalida", "Erro", JOptionPane.ERROR_MESSAGE);
        } else JOptionPane.showMessageDialog(this, "Email invalido", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    // Metodo para o botão Entrar
    private void jButton_EntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EntrarActionPerformed
        // Apenas chamo o metodo para validar
        validarUsuario();
    }//GEN-LAST:event_jButton_EntrarActionPerformed

    // Metodo para o botão Voltar
    private void jButton_CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CadastrarActionPerformed
        Cadastrar cadastrarTela = new Cadastrar();
        this.dispose();
        cadastrarTela.setVisible(true);
    }//GEN-LAST:event_jButton_CadastrarActionPerformed

    // Metodos para quando aperta ENTER chama a validação do usuario
    private void jPasswordField_SenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField_SenhaKeyPressed
        if(evt.getKeyCode() == 10) {
            validarUsuario();
        }
    }//GEN-LAST:event_jPasswordField_SenhaKeyPressed

    private void jTextField_LoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_LoginKeyPressed
        if(evt.getKeyCode() == 10) {
            validarUsuario();
        }
    }//GEN-LAST:event_jTextField_LoginKeyPressed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cadastrar;
    private javax.swing.JButton jButton_Entrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField_Senha;
    private javax.swing.JTextField jTextField_Login;
    // End of variables declaration//GEN-END:variables
}
