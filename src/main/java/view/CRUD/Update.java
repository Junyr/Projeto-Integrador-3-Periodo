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

package view.CRUD;

import javax.swing.JOptionPane;
import model.Cidade;
import banco.*;
import java.util.Stack;
import javax.swing.ImageIcon;
import model.Atualizacao;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @author Marcos Gabriel <marcossetecruzsoares@gmail.com>
 * @brief JFrame Update
 */

public class Update extends javax.swing.JFrame {
    
    // Variavel para receber a cidade selecioda
    private Cidade selectCidade = null;
    // Variavel para receber os dados da tela principal
    private Read telaPrincipal;
    // Stack para armazenar os updates que eu receber da tela principal
    private Stack<Atualizacao> historico = new Stack<>();
    
    public Update() {
        // Inicialização dos componentes
        initComponents();
        // Responsavel por colocar a janela no meio da tela
        this.setLocationRelativeTo(null);
        // Responsavel por colocar o titulo na janela
        this.setTitle("Atualização");
        // Responsavel por mudar o icone da janela
        ImageIcon icon = new ImageIcon("src/main/resources/data/icon.png");
        this.setIconImage(icon.getImage());
    }
    
    // Construtor para receber os dados da tela principal
    public Update(Read read) {
        // Inicialização dos componentes
        initComponents();
        // Responsavel por colocar a janela no meio da tela
        this.setLocationRelativeTo(null);
        // Responsavel por colocar o titulo na janela
        this.setTitle("Atualização");
        // Responsavel por mudar o icone da janela
        ImageIcon icon = new ImageIcon("src/main/resources/data/icon.png");
        this.setIconImage(icon.getImage());
        // Armazenar os dados da tela principal aqui no JFrame
        this.telaPrincipal = read;
    }

    // set para alocar a cidade selecionada da tela pincipal aqui e colocar todos os dados ja no formulario
    public void setSelectCidade(Cidade selectCidade) {
        this.selectCidade = selectCidade;
        jTextField_Populacao.setText(String.valueOf(selectCidade.getPopulacao()));
        jTextField_Domicilios.setText(String.valueOf(selectCidade.getDomicilios()));
        jTextField_PIBTotal.setText(String.valueOf(selectCidade.getPIBTotal()));
        jTextField_RendaMedia.setText(String.valueOf(selectCidade.getRendaMedia()));
        jTextField_RendaNominal.setText(String.valueOf(selectCidade.getRendaNominal()));
        jTextField_PEADia.setText(String.valueOf(selectCidade.getPEADia()));
        jTextField_IDHG.setText(String.valueOf(selectCidade.getIDHGeral()));
        jTextField_IDHE.setText(String.valueOf(selectCidade.getIDHEducacao()));
        jTextField_IDHL.setText(String.valueOf(selectCidade.getIDHLongevidade()));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField_Populacao = new javax.swing.JTextField();
        jTextField_Domicilios = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField_PIBTotal = new javax.swing.JTextField();
        jTextField_RendaMedia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_RendaNominal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField_PEADia = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField_IDHG = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField_IDHE = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField_IDHL = new javax.swing.JTextField();
        jButton_Atualizar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jButton_Voltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));

        jTextField_Populacao.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_Populacao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_Populacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_PopulacaoKeyPressed(evt);
            }
        });

        jTextField_Domicilios.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_Domicilios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_Domicilios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_DomiciliosKeyPressed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("População");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Domicilios");

        jTextField_PIBTotal.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_PIBTotal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_PIBTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_PIBTotalKeyPressed(evt);
            }
        });

        jTextField_RendaMedia.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_RendaMedia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_RendaMedia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_RendaMediaKeyPressed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("PIB Total");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Renda Média");

        jTextField_RendaNominal.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_RendaNominal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_RendaNominal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_RendaNominalKeyPressed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Renda Nominal");

        jTextField_PEADia.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_PEADia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_PEADia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_PEADiaKeyPressed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("PEA Dia");

        jTextField_IDHG.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_IDHG.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_IDHG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_IDHGKeyPressed(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("IDH Genero");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("IDH Educação");

        jTextField_IDHE.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_IDHE.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_IDHE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_IDHEKeyPressed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("IDH Longevidade");

        jTextField_IDHL.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_IDHL.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_IDHL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_IDHLKeyPressed(evt);
            }
        });

        jButton_Atualizar.setBackground(new java.awt.Color(24, 100, 204));
        jButton_Atualizar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_Atualizar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Atualizar.setText("Atualizar");
        jButton_Atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AtualizarActionPerformed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Atualização");

        jButton_Voltar.setBackground(new java.awt.Color(249, 246, 246));
        jButton_Voltar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton_Voltar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Setinha de voltar.png"))); // NOI18N
        jButton_Voltar.setBorder(null);
        jButton_Voltar.setBorderPainted(false);
        jButton_Voltar.setFocusPainted(false);
        jButton_Voltar.setFocusable(false);
        jButton_Voltar.setHideActionText(true);
        jButton_Voltar.setRequestFocusEnabled(false);
        jButton_Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_VoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_RendaMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_RendaNominal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_PEADia, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_IDHG, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_IDHE, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_IDHL, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addGap(80, 80, 80)
                            .addComponent(jButton_Voltar))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7)
                                .addComponent(jTextField_Populacao, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField_Domicilios, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jTextField_PIBTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton_Atualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField_Domicilios, jTextField_IDHE, jTextField_IDHG, jTextField_IDHL, jTextField_PEADia, jTextField_PIBTotal, jTextField_Populacao, jTextField_RendaMedia, jTextField_RendaNominal});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jButton_Voltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Populacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField_Domicilios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_PIBTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(32, 32, 32)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField_RendaMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField_PEADia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_RendaNominal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_IDHG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_IDHE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_IDHL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField_Domicilios, jTextField_IDHE, jTextField_IDHG, jTextField_IDHL, jTextField_PEADia, jTextField_PIBTotal, jTextField_Populacao, jTextField_RendaMedia, jTextField_RendaNominal});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Metodo para validar a atualização
    private void validarAtualizacao(){
        // Para o codigoIBGE, o tamnho permitido é 7 digitos
        if(jTextField_Populacao.getText().matches("^[1-9]\\d*")){
            // Para a domicilios apenas numero sem decimal, não podendo começar com 0
            if(jTextField_Domicilios.getText().matches("^[1-9]\\d*")){
                // Para PIB Total apenas numero tendo ou não decimal, não podendo começar com 0
                if(jTextField_PIBTotal.getText().matches("^[1-9]\\d*(\\.\\d+)?")){
                    // Para renda media apenas numero tendo ou não decimal, não podendo começar com 0
                    if(jTextField_RendaMedia.getText().matches("^[1-9]\\d*(\\.\\d+)?")){
                        // Para renda nominal apenas numero tendo ou não decimal, não podendo começar com 0
                        if(jTextField_RendaNominal.getText().matches("^[1-9]\\d*(\\.\\d+)?(E\\d+)?$")){
                            // Para a PEA dia apenas numero sem decimal, não podendo começar com 0
                            if(jTextField_PEADia.getText().matches("^[1-9]\\d*")){
                                // Para os IDHs, pode começar com 0 com no maximo 3 numeros decimais ou 1
                                if(jTextField_IDHG.getText().matches("^0\\.\\d+{1,3}|1(\\.00)?")){
                                    if(jTextField_IDHE.getText().matches("^0\\.\\d+{1,3}|1(\\.00)?")){
                                        if(jTextField_IDHL.getText().matches("^0\\.\\d+{1,3}|1(\\.00)?")){
                                            // Caao passe pelas verificações, vai armazenar todas em variaveis com os respectios tipos
                                            int populacao = Integer.parseInt(jTextField_Populacao.getText());
                                            int domicilios = Integer.parseInt(jTextField_Domicilios.getText());
                                            double pibTotal = Double.parseDouble(jTextField_PIBTotal.getText());
                                            double rendaMedia = Double.parseDouble(jTextField_RendaMedia.getText());
                                            double rendaNominal = Double.parseDouble(jTextField_RendaNominal.getText());
                                            int peaDia = Integer.parseInt(jTextField_PEADia.getText());
                                            double idhg = Double.parseDouble(jTextField_IDHG.getText());
                                            double idhe = Double.parseDouble(jTextField_IDHE.getText());
                                            double idhl = Double.parseDouble(jTextField_IDHL.getText());

                                            // É necessário permite a entrada do código ibge na tela de update para que possamos alterar os valores no banco
                                            Cidade upCidade = new Cidade(selectCidade.getCodigoIBGE(), populacao, domicilios, pibTotal, idhg, rendaMedia, rendaNominal, peaDia, idhe, idhl);
                                            try {
                                                //Deve verificar se ja tem essa cidade no banco de dados
                                                if(UpdateBD.cidadeExiste(upCidade.getCodigoIBGE())){
                                                    // Chama o metodo de atualizarCidade da classe UpdateBD
                                                    UpdateBD.atualizarCidade(upCidade);
                                                    // Mensagem de bem sucedido
                                                    JOptionPane.showMessageDialog(this, "Atualização feita com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                                    // Chamo o metodo pesquiar() da tela para atualizar a tabela
                                                    telaPrincipal.pesquisar();
                                                    this.dispose();
                                                    
                                                // Mensagens de possiveis erros    
                                                } else JOptionPane.showMessageDialog(this, "Cidade n�o encontrada tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
                                            } catch (Exception ex) {
                                                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                         } else JOptionPane.showMessageDialog(this, "Informe um numero valido para o IDH Longevidade", "Erro", JOptionPane.ERROR_MESSAGE);
                                     } else JOptionPane.showMessageDialog(this, "Informe um numero valido para o IDH Educação", "Erro", JOptionPane.ERROR_MESSAGE);
                                 } else JOptionPane.showMessageDialog(this, "Informe um numero valido para o IDH Genero", "Erro", JOptionPane.ERROR_MESSAGE);
                             } else JOptionPane.showMessageDialog(this, "Informe um numero valido para o PEA Dia", "Erro", JOptionPane.ERROR_MESSAGE);
                         } else JOptionPane.showMessageDialog(this, "Informe um numero valido para a Renda Nominal", "Erro", JOptionPane.ERROR_MESSAGE);
                     } else JOptionPane.showMessageDialog(this, "Informe um numero valido para a Renda Media", "Erro", JOptionPane.ERROR_MESSAGE);
                 } else JOptionPane.showMessageDialog(this, "Informe um numero valido para o PIB Total", "Erro", JOptionPane.ERROR_MESSAGE);
             } else JOptionPane.showMessageDialog(this, "Informe um numero valido para os Domicilios", "Erro", JOptionPane.ERROR_MESSAGE);
        } else JOptionPane.showMessageDialog(this, "Informe um numero valido para os Populacao", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    // Metodo para o botão atualizar
    private void jButton_AtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AtualizarActionPerformed
        // Chamo o metodo para validar
        validarAtualizacao();
    }//GEN-LAST:event_jButton_AtualizarActionPerformed

    // Metodo para o botão voltar
    private void jButton_VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_VoltarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton_VoltarActionPerformed

    // Metodos para campo campo aceita ENTER para chamar a validação
    private void jTextField_PopulacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_PopulacaoKeyPressed
        if(evt.getKeyCode() == 10){
            validarAtualizacao();
        }
    }//GEN-LAST:event_jTextField_PopulacaoKeyPressed

    private void jTextField_DomiciliosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_DomiciliosKeyPressed
        if(evt.getKeyCode() == 10){
            validarAtualizacao();
        }
    }//GEN-LAST:event_jTextField_DomiciliosKeyPressed

    private void jTextField_PIBTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_PIBTotalKeyPressed
        if(evt.getKeyCode() == 10){
            validarAtualizacao();
        }
    }//GEN-LAST:event_jTextField_PIBTotalKeyPressed

    private void jTextField_RendaMediaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_RendaMediaKeyPressed
        if(evt.getKeyCode() == 10){
            validarAtualizacao();
        }
    }//GEN-LAST:event_jTextField_RendaMediaKeyPressed

    private void jTextField_RendaNominalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_RendaNominalKeyPressed
        if(evt.getKeyCode() == 10){
            validarAtualizacao();
        }
    }//GEN-LAST:event_jTextField_RendaNominalKeyPressed

    private void jTextField_PEADiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_PEADiaKeyPressed
        if(evt.getKeyCode() == 10){
            validarAtualizacao();
        }
    }//GEN-LAST:event_jTextField_PEADiaKeyPressed

    private void jTextField_IDHGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_IDHGKeyPressed
        if(evt.getKeyCode() == 10){
            validarAtualizacao();
        }
    }//GEN-LAST:event_jTextField_IDHGKeyPressed

    private void jTextField_IDHEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_IDHEKeyPressed
        if(evt.getKeyCode() == 10){
            validarAtualizacao();
        }
    }//GEN-LAST:event_jTextField_IDHEKeyPressed

    private void jTextField_IDHLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_IDHLKeyPressed
        if(evt.getKeyCode() == 10){
            validarAtualizacao();
        }
    }//GEN-LAST:event_jTextField_IDHLKeyPressed

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
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Update().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Atualizar;
    private javax.swing.JButton jButton_Voltar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField_Domicilios;
    private javax.swing.JTextField jTextField_IDHE;
    private javax.swing.JTextField jTextField_IDHG;
    private javax.swing.JTextField jTextField_IDHL;
    private javax.swing.JTextField jTextField_PEADia;
    private javax.swing.JTextField jTextField_PIBTotal;
    private javax.swing.JTextField jTextField_Populacao;
    private javax.swing.JTextField jTextField_RendaMedia;
    private javax.swing.JTextField jTextField_RendaNominal;
    // End of variables declaration//GEN-END:variables
}
