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

import banco.BancoDados;
import banco.Createe;
import banco.Delete;
import java.awt.Color;
import java.text.Collator;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Atualizacao;
import model.Cidade;
import service.Arquivo;
import service.Estatistica;
import view.Login;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @author Marcos Gabriel <marcossetecruzsoares@gmail.com>
 * @brief JFrame Read
 */

public class Read extends javax.swing.JFrame {
    
    // Variavel para manipular o arquivo
    private Arquivo csv = new Arquivo(this);
    // Variavel para armazenar o resultado de calculos
    private Cidade cidade = null;
    // Variavel para armazenar a cidade selecionada
    private Cidade selectCidade = null;
    // Variavel para armazenar a lista de cidade da barra de pesquisa
    private LinkedList<Cidade> listCidades = null;
    // Stack para armazenar o historico de atualizações
    private Stack<Atualizacao> historico = new Stack<>();
    // Variavel para utilizar o bando de dados
    private BancoDados banco = new BancoDados();
    // Variavel para calculos de estatistica
    private Estatistica calculo = new Estatistica();
    
    // get para poder manipular o historico depois
    public Stack<Atualizacao> getHistorico() {
        return historico;
    }
    
    public Read() {
        try {
            LinkedList<Cidade> list = csv.In();
            list.sort(Comparator.comparing(Cidade::getNome, Collator.getInstance(Locale.forLanguageTag("pt-BR"))));
            // Para inserir os dados no banco
            banco.inserirDados(list);
            // Inicialização dos componentes
            initComponents();
            // Chamar o metodo para aparecer os dados na tabela
            pesquisar();
            // Responsavel por colocar a janela no meio da tela
            this.setLocationRelativeTo(null);
            // Responsavel por deixa em tela cheia
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            // Responsavel por colocar o titulo na janela
            this.setTitle("Tela Principal");
            // Responsavel por mudar o icone da janela
            ImageIcon icon = new ImageIcon("src/main/resources/image/icon.png");
            this.setIconImage(icon.getImage());
        // Para a possivel mensagem de erro
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }   
    
    public void pesquisar(){
        try{
            // Variavel para modificar o modelo da tabela
            DefaultTableModel modelo = (DefaultTableModel)jTable_Cidade.getModel();
            // Para resetar a tabela
            modelo.setRowCount(0);
            // Caso uma cidade que for resultado de um calculo não estiver nulo
            if(cidade != null){
                // .addRow para adicionar na linha
                 modelo.addRow(new Object[] {
                        cidade.getCodigoIBGE(),
                        cidade.getNome(),
                        cidade.getMicroregiao(),
                        cidade.getSigla(),
                        cidade.getRegiao(),
                        cidade.getArea(),
                        cidade.getPopulacao(),
                        cidade.getDomicilios(),
                        cidade.getPIBTotal(),
                        cidade.getIDHGeral(),
                        cidade.getRendaMedia(),
                        cidade.getRendaNominal(),
                        cidade.getPEADia(),
                        cidade.getIDHEducacao(),
                        cidade.getIDHLongevidade(),
                    });
                 cidade = null;
            // Caso tenha uma lista cidades gerado pela barra de pesquisa
            } else if(listCidades != null){
                listCidades.sort(Comparator.comparing(Cidade::getNome, Collator.getInstance(Locale.forLanguageTag("pt-BR"))));
                for(int pos = 0; pos < listCidades.size(); pos++){
                    modelo.addRow(new Object[] {
                        listCidades.get(pos).getCodigoIBGE(),
                        listCidades.get(pos).getNome(),
                        listCidades.get(pos).getMicroregiao(),
                        listCidades.get(pos).getSigla(),
                        listCidades.get(pos).getRegiao(),
                        listCidades.get(pos).getArea(),
                        listCidades.get(pos).getPopulacao(),
                        listCidades.get(pos).getDomicilios(),
                        listCidades.get(pos).getPIBTotal(),
                        listCidades.get(pos).getIDHGeral(),
                        listCidades.get(pos).getRendaMedia(),
                        listCidades.get(pos).getRendaNominal(),
                        listCidades.get(pos).getPEADia(),
                        listCidades.get(pos).getIDHEducacao(),
                        listCidades.get(pos).getIDHLongevidade(),
                    });
                }
                listCidades = null;
            // Caso não tenha nenhum dos dois
            } else {
                // Pego a lista do banco de dados
                LinkedList<Cidade> tabelLista = banco.returnListCidade();
                // Ordeno ela com .sort e Comparator usando o nome
                tabelLista.sort(Comparator.comparing(Cidade::getNome, Collator.getInstance(Locale.forLanguageTag("pt-BR"))));
                // Laço de repetição para colocar tudo na tabela
                for(int pos = 0; pos < tabelLista.size(); pos++){
                    modelo.addRow(new Object[] {
                        tabelLista.get(pos).getCodigoIBGE(),
                        tabelLista.get(pos).getNome(),
                        tabelLista.get(pos).getMicroregiao(),
                        tabelLista.get(pos).getSigla(),
                        tabelLista.get(pos).getRegiao(),
                        tabelLista.get(pos).getArea(),
                        tabelLista.get(pos).getPopulacao(),
                        tabelLista.get(pos).getDomicilios(),
                        tabelLista.get(pos).getPIBTotal(),
                        tabelLista.get(pos).getIDHGeral(),
                        tabelLista.get(pos).getRendaMedia(),
                        tabelLista.get(pos).getRendaNominal(),
                        tabelLista.get(pos).getPEADia(),
                        tabelLista.get(pos).getIDHEducacao(),
                        tabelLista.get(pos).getIDHLongevidade(),
                    });
                }
            }
            // Caso a pesquisa seja feita a cidade selecionada deve ficar nulo
            selectCidade = null;
            // O resultado eu mudo o modelo para o modelo que eu customizei com as linhas
            jTable_Cidade.setModel(modelo);
            // Os botões que so podem ser acionados caso acha uma selectCidade, desativo eles
            jButton_Atualizar.setEnabled(false);
            jButton_Deletar.setEnabled(false);
            jButton_Densidade.setEnabled(false);
            jButton_PIBpC.setEnabled(false);
            jComboBox_IDH.setEnabled(false);
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField_Pesquisa = new javax.swing.JTextField();
        jButton_Voltar = new javax.swing.JButton();
        jButton_Novo = new javax.swing.JButton();
        jButton_Atualizar = new javax.swing.JButton();
        jButton_Deletar = new javax.swing.JButton();
        jComboBox_EstatisticaPIBpC = new javax.swing.JComboBox<>();
        jComboBox_EstatisticaIDHG = new javax.swing.JComboBox<>();
        jComboBox_EstatisticaIDHE = new javax.swing.JComboBox<>();
        jComboBox_EstatisticaIDHL = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Cidade = new javax.swing.JTable();
        jButton_Densidade = new javax.swing.JButton();
        jButton_PIBpC = new javax.swing.JButton();
        jComboBox_IDH = new javax.swing.JComboBox<>();
        jButton_Grafico = new javax.swing.JButton();
        jButton_Exportar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(1098, 519));

        jPanel1.setBackground(new java.awt.Color(249, 246, 246));
        jPanel1.setAutoscrolls(true);
        jPanel1.setFocusTraversalPolicyProvider(true);
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 600));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        jTextField_Pesquisa.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_Pesquisa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_Pesquisa.setForeground(Color.GRAY);
        jTextField_Pesquisa.setText("Digite o codigo do IBGE ou municipio");
        jTextField_Pesquisa.setToolTipText("");
        jTextField_Pesquisa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_PesquisaFocusLost(evt);
            }
        });
        jTextField_Pesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_PesquisaMouseClicked(evt);
            }
        });
        jTextField_Pesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_PesquisaKeyPressed(evt);
            }
        });

        jButton_Voltar.setBackground(new java.awt.Color(249, 246, 246));
        jButton_Voltar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton_Voltar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Setinha de voltar.png"))); // NOI18N
        jButton_Voltar.setBorder(null);
        jButton_Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_VoltarActionPerformed(evt);
            }
        });

        jButton_Novo.setBackground(new java.awt.Color(24, 100, 204));
        jButton_Novo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton_Novo.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Novo.setText("Novo");
        jButton_Novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NovoActionPerformed(evt);
            }
        });

        jButton_Atualizar.setBackground(new java.awt.Color(24, 100, 204));
        jButton_Atualizar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton_Atualizar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Atualizar.setText("Atualizar");
        jButton_Atualizar.setEnabled(false);
        jButton_Atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AtualizarActionPerformed(evt);
            }
        });

        jButton_Deletar.setBackground(new java.awt.Color(24, 100, 204));
        jButton_Deletar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton_Deletar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Deletar.setText("Deletar");
        jButton_Deletar.setEnabled(false);
        jButton_Deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeletarActionPerformed(evt);
            }
        });

        jComboBox_EstatisticaPIBpC.setBackground(new java.awt.Color(24, 100, 204));
        jComboBox_EstatisticaPIBpC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBox_EstatisticaPIBpC.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_EstatisticaPIBpC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estatistica PIBpC", "Melhor PIBpC", "Pior PIBpC", "Classificacao" }));
        jComboBox_EstatisticaPIBpC.setToolTipText("");
        jComboBox_EstatisticaPIBpC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_EstatisticaPIBpCActionPerformed(evt);
            }
        });

        jComboBox_EstatisticaIDHG.setBackground(new java.awt.Color(24, 100, 204));
        jComboBox_EstatisticaIDHG.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBox_EstatisticaIDHG.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_EstatisticaIDHG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estatistica IDHG", "Melhor IDHG", "Pior IDHG", "Classificacao" }));
        jComboBox_EstatisticaIDHG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_EstatisticaIDHGActionPerformed(evt);
            }
        });

        jComboBox_EstatisticaIDHE.setBackground(new java.awt.Color(24, 100, 204));
        jComboBox_EstatisticaIDHE.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBox_EstatisticaIDHE.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_EstatisticaIDHE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estatistica IDHE", "Melhor IDHE", "Pior IDHE", "Classificacao" }));
        jComboBox_EstatisticaIDHE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_EstatisticaIDHEActionPerformed(evt);
            }
        });

        jComboBox_EstatisticaIDHL.setBackground(new java.awt.Color(24, 100, 204));
        jComboBox_EstatisticaIDHL.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBox_EstatisticaIDHL.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_EstatisticaIDHL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estatistica IDHL", "Melhor IDHL", "Pior IDHL", "Classificacao" }));
        jComboBox_EstatisticaIDHL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_EstatisticaIDHLActionPerformed(evt);
            }
        });

        jTable_Cidade.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable_Cidade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo IBGE", "Municipios", "Microregiao", "Estado", "Regiao", "Area", "Populacao", "Domicilios", "PIB Total", "IDH Geral", "Renda Media", "Renda Nominal", "PEA Dia", "IDH Educação", "IDH Longevidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Cidade.setAutoscrolls(false);
        jTable_Cidade.setMinimumSize(new java.awt.Dimension(600, 300));
        jTable_Cidade.setPreferredSize(new java.awt.Dimension(1300, 4000));
        jTable_Cidade.setShowHorizontalLines(true);
        jTable_Cidade.setShowVerticalLines(true);
        jTable_Cidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_CidadeMouseClicked(evt);
            }
        });
        jTable_Cidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable_CidadeKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Cidade);

        jButton_Densidade.setBackground(new java.awt.Color(24, 100, 204));
        jButton_Densidade.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton_Densidade.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Densidade.setText("Densidade");
        jButton_Densidade.setEnabled(false);
        jButton_Densidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DensidadeActionPerformed(evt);
            }
        });

        jButton_PIBpC.setBackground(new java.awt.Color(24, 100, 204));
        jButton_PIBpC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton_PIBpC.setForeground(new java.awt.Color(255, 255, 255));
        jButton_PIBpC.setText("PIBpC");
        jButton_PIBpC.setEnabled(false);
        jButton_PIBpC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PIBpCActionPerformed(evt);
            }
        });

        jComboBox_IDH.setBackground(new java.awt.Color(24, 100, 204));
        jComboBox_IDH.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBox_IDH.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_IDH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Classificação IDH", "IDHG", "IDHE", "IDHL" }));
        jComboBox_IDH.setEnabled(false);
        jComboBox_IDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_IDHActionPerformed(evt);
            }
        });

        jButton_Grafico.setBackground(new java.awt.Color(24, 100, 204));
        jButton_Grafico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton_Grafico.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Grafico.setText("Grafico");
        jButton_Grafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GraficoActionPerformed(evt);
            }
        });

        jButton_Exportar.setBackground(new java.awt.Color(24, 100, 204));
        jButton_Exportar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton_Exportar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Exportar.setText("Exportar");
        jButton_Exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField_Pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Atualizar)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Deletar)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Grafico, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_Voltar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_Densidade)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_PIBpC)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_IDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_EstatisticaPIBpC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_EstatisticaIDHG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_EstatisticaIDHE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_EstatisticaIDHL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_Pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Novo)
                        .addComponent(jButton_Atualizar)
                        .addComponent(jButton_Deletar)
                        .addComponent(jButton_Grafico)
                        .addComponent(jButton_Exportar))
                    .addComponent(jButton_Voltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_EstatisticaPIBpC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_EstatisticaIDHG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_EstatisticaIDHE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_EstatisticaIDHL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Densidade)
                    .addComponent(jButton_PIBpC)
                    .addComponent(jComboBox_IDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel1);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Metodo para o botão para voltar
    private void jButton_VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_VoltarActionPerformed
        Login loginTela = new Login();
        this.dispose();
        loginTela.setVisible(true);
    }//GEN-LAST:event_jButton_VoltarActionPerformed

    // Metodo para o botão novo
    private void jButton_NovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NovoActionPerformed
        // Para abrir a janela
        Create createTela = new Create(this);
        createTela.setVisible(true);
    }//GEN-LAST:event_jButton_NovoActionPerformed

    // Metodo para o botão atualizar
    private void jButton_AtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AtualizarActionPerformed
        // Caso o selectCidade não esteja nulo
        if(selectCidade != null){
            // Inicializo a tela update com o this e com historico para atualização
            Update updateTela = new Update(this);
            // Passo o selectCidadde para o update
            updateTela.setSelectCidade(selectCidade);
            // Depois de ter passado eu deixo o selectCidade nulo
            selectCidade = null;
            // Abro a janela
            updateTela.setVisible(true);
        }
    }//GEN-LAST:event_jButton_AtualizarActionPerformed

    // Metodo para o botão deletar
    private void jButton_DeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeletarActionPerformed
        // Caso selectCidade não seja nulo
        if(selectCidade != null){
            // Com o JOptionPane eu mostro um janela onde o usuario pode clicar sim ou não
            if(JOptionPane.showConfirmDialog(this, "Deseja realmente excluir esse contato?", "Confirmação", 
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                // Caso seja sim chama o metodo do banco de daos para deletar
                if(Delete.deleteCidade(selectCidade.getCodigoIBGE())){
                    // Caso tenha sucesso em deletar vai mostrar uma mensagem de sucesso
                    JOptionPane.showMessageDialog(this, "Cidade excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    // selectCidade em null
                    selectCidade = null;
                    // Chamo o metodo pesquisar() para atualizar a tabela
                    pesquisar();
                    
                    // Mensagens de erro
                } else JOptionPane.showMessageDialog(this, "Erro ao excluir cidade", "Erro", JOptionPane.ERROR_MESSAGE);
            } 
        } else JOptionPane.showMessageDialog(this, "Selecione uma cidade para realizar a exclusao", "Erro", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButton_DeletarActionPerformed

    // Metodo para uma ação do teclado na barra de pesquisa
    private void jTextField_PesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_PesquisaKeyPressed
        if(evt.getKeyCode() == 10) { // Para o botão ENTER
            try{
                // Verificação para caso seja numeros com o tamanho de 7 ou para caso seja letras, podendo ser mais de uma palavra
                if(jTextField_Pesquisa.getText().matches("\\d{7}") || 
                            jTextField_Pesquisa.getText().matches("[a-zA-Zà-úÀ-Úâ-ûÂ-ÛçÇ]+(\\s[a-zA-Zà-úÀ-Úâ-ûÂ-ÛçÇ]+)*$")){
                    // Chamo o metodo que retorna uma lista de cidade com base no que o usuario digitou
                    listCidades = Createe.findListCidade(jTextField_Pesquisa.getText());
                    // Chamo a pesquisar() para atualizar a tabela
                    pesquisar();
                } else JOptionPane.showMessageDialog(this, "Cidade não encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(evt.getKeyCode() == 27){ // Para o botão ESC
            // Basicamente deixo todas as variaveis de pesquisa nulo, mudo o texto da barra de pesquisa e chamo o metodo pesquisar() para resetar
            jTextField_Pesquisa.setText("Digite o codigo do IBGE ou municipio");
            jTextField_Pesquisa.setForeground(Color.GRAY);
            cidade = null;
            selectCidade = null;
            listCidades = null;
            pesquisar();
        }
    }//GEN-LAST:event_jTextField_PesquisaKeyPressed

    // Metodo para quando o usuario clicar na barra de pesquisa
    private void jTextField_PesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_PesquisaMouseClicked
        // Se o texto da barra de pesquisa for "Digite o codigo do IBGE ou municipio", vai limpar o texto e deixar a cor do texto preto
        if(jTextField_Pesquisa.getText().equals("Digite o codigo do IBGE ou municipio")){
            jTextField_Pesquisa.setText("");
            jTextField_Pesquisa.setForeground(Color.BLACK);
        }
        
    }//GEN-LAST:event_jTextField_PesquisaMouseClicked

    // Metodo para quando o usuario clica fora da barra de pesquisa
    private void jTextField_PesquisaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_PesquisaFocusLost
        // Se a barra estiver vazia, vai voltar ao texto original e cinza
        if(jTextField_Pesquisa.getText().equals("")) {
            jTextField_Pesquisa.setText("Digite o codigo do IBGE ou municipio");
            jTextField_Pesquisa.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_jTextField_PesquisaFocusLost

    // Metodo para caso o usuario clique na tabela
    private void jTable_CidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_CidadeMouseClicked
        // Pega a linha selecionada e coloca em uma variavel
        int linha = jTable_Cidade.getSelectedRow();
        // Se a linha não for -1
        if(linha != -1){
            // Vai pegar cada valor de cada linha e coluna e armazenar em uma variavel
            int codigoIbge = (int) jTable_Cidade.getValueAt(linha, 0);
            String nomeMunicipio = (String) jTable_Cidade.getValueAt(linha, 1);
            String microRegiao = (String) jTable_Cidade.getValueAt(linha, 2);
            String estadoSigla = (String) jTable_Cidade.getValueAt(linha, 3);
            String regiaoGeografica = (String) jTable_Cidade.getValueAt(linha, 4);
            double areaKm = (double) jTable_Cidade.getValueAt(linha, 5);
            int populacao = (int) jTable_Cidade.getValueAt(linha, 6);
            int domicilios = (int) jTable_Cidade.getValueAt(linha, 7);
            double PIBTotal = (double) jTable_Cidade.getValueAt(linha, 8);
            double IDHG = (double) jTable_Cidade.getValueAt(linha, 9);
            double rendaMedia = (double) jTable_Cidade.getValueAt(linha, 10);
            double rendaNominal = (double) jTable_Cidade.getValueAt(linha, 11);
            int PEADia = (int) jTable_Cidade.getValueAt(linha, 12);
            double IDHE = (double) jTable_Cidade.getValueAt(linha, 13);
            double IDHL = (double) jTable_Cidade.getValueAt(linha, 14);

            // Depois apenas instancio todos os dados no selectCidade
            selectCidade = new Cidade(codigoIbge, nomeMunicipio, microRegiao, estadoSigla, regiaoGeografica, areaKm,
                populacao, domicilios, PIBTotal, IDHG, rendaMedia, rendaNominal, PEADia,
                IDHE, IDHL);
        }
        // Todos os botões que necessitam de uma cidade selecionada estão habilitados
        jButton_Atualizar.setEnabled(true);
        jButton_Deletar.setEnabled(true);
        jButton_Densidade.setEnabled(true);
        jButton_PIBpC.setEnabled(true);
        jComboBox_IDH.setEnabled(true);
    }//GEN-LAST:event_jTable_CidadeMouseClicked

    // Metodo para o botão densidade
    private void jButton_DensidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DensidadeActionPerformed
        // Se a selectCidade não estiver nulo, vai mostrar uma mensagem onde fala qual a densidade demografica da cidade tal
        if(selectCidade != null) JOptionPane.showMessageDialog(this, "Densidade Demográfica de " + selectCidade.getNome() +
                " é " + String.format("%.2f", selectCidade.Densidade()) + " hab/km²", "Densidade", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton_DensidadeActionPerformed

    // Metodo para o botão PIBpC
    private void jButton_PIBpCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PIBpCActionPerformed
        // Se a selectCidade não estiver nulo, vai mostrar uma mensagem onde fala qual o PIBpC da cidade tal
        if(selectCidade != null) JOptionPane.showMessageDialog(this, "PIB per capita de " + selectCidade.getNome() +
                " é " + String.format("R$ %.2f", selectCidade.PIBpC()) + " por pessoa", "PIBpC", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton_PIBpCActionPerformed

    // Metodo para a combo box Estatistica IDH
    private void jComboBox_IDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_IDHActionPerformed
        // Pego qual opção o usuario digitou
        String opcaoSelecionada =  String.valueOf(jComboBox_IDH.getSelectedItem());
        try{
            // Dependendo da opção, mostro uma mensagem de qual a classificação da cidade tal
            switch(opcaoSelecionada){
                case "IDHG"->{
                    if(selectCidade != null) JOptionPane.showMessageDialog(this, "A classificação do índice de Desenvolvimento Humano Geral de " + selectCidade.getNome() +
                            " é " + selectCidade.classIDH(selectCidade.getIDHGeral()), "Classificação IDHG", JOptionPane.INFORMATION_MESSAGE);
                }
                case "IDHE"->{
                    if(selectCidade != null)JOptionPane.showMessageDialog(this, "A classificação do índice relacionado ao nível educacional da população de " + selectCidade.getNome() +
                            " é " + selectCidade.classIDH(selectCidade.getIDHEducacao()), "Classificação IDHE", JOptionPane.INFORMATION_MESSAGE);
                }
                case "IDHL"->{
                    if(selectCidade != null)JOptionPane.showMessageDialog(this, "A classificação do índice relacionado à expectativa de vida de " + selectCidade.getNome() +
                            " é " + selectCidade.classIDH(selectCidade.getIDHLongevidade()), "Classificação IDHL", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            jComboBox_IDH.setSelectedIndex(0);
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jComboBox_IDHActionPerformed

    // Metodo para a combo box Estatistica PIBpC
    private void jComboBox_EstatisticaPIBpCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_EstatisticaPIBpCActionPerformed
        // Pego qual opção o usuario digitou
        String opcaoSelecionada =  String.valueOf(jComboBox_EstatisticaPIBpC.getSelectedItem());
        // Caso não seja a primeira opção
        if(!opcaoSelecionada.equals("Estatistica PIBpC")){
            try{
                // Pego a lista do banco de dados
                LinkedList<Cidade> tabelLista = banco.returnListCidade();
                // Dependendo do tipo da opção eu mudo o metodo que utilizo
                switch(opcaoSelecionada){
                    case "Melhor PIBpC"->{
                        // Chamo o metodo do banco de dados para achar a cidade com o Melhor PIBpC
                        cidade = Createe.findCidade(String.valueOf(calculo.MelhorPIBpC(tabelLista).getCodigoIBGE()));
                        pesquisar();
                    }
                    case "Pior PIBpC"->{
                        // Chamo o metodo do banco de dados para achar a cidade com o Pior PIBpC
                        cidade = Createe.findCidade(String.valueOf(calculo.PiorPIBpC(tabelLista).getCodigoIBGE()));;
                        pesquisar();
                    }
                    case "Classificacao"->{
                        // Se for classificação, precisa ter uma cidade selecionada, mostra uma janela falando qual a classificação dela
                        if(selectCidade != null) JOptionPane.showMessageDialog(this, "A cidade selecionada esta classificada na posição: " + 
                                calculo.Posicao(tabelLista, selectCidade, "PIBpC"), "Classificação PIBpC", JOptionPane.INFORMATION_MESSAGE);
                        else JOptionPane.showMessageDialog(this, "Selecione uma cidade primeiro", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(this, "Não foi possivel localizar", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Isso é para voltar ao primeiro item
        jComboBox_EstatisticaPIBpC.setSelectedIndex(0); 
    }//GEN-LAST:event_jComboBox_EstatisticaPIBpCActionPerformed

    // Metodo para a combo box Estatistica IDHG
    private void jComboBox_EstatisticaIDHGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_EstatisticaIDHGActionPerformed
        // Pego qual opção o usuario digitou
        String opcaoSelecionada =  String.valueOf(jComboBox_EstatisticaIDHG.getSelectedItem());
        // Caso não seja a primeira opção
        if(!opcaoSelecionada.equals("Estatistica IDHG")){
            try{
                // Pego a lista do banco de dados
                LinkedList<Cidade> tabelLista = banco.returnListCidade();
                // Dependendo do tipo da opção eu mudo o metodo que utilizo
                switch(opcaoSelecionada){
                    case "Melhor IDHG"->{
                        // Chamo o metodo do banco de dados para achar a cidade com o Melhor IDHG
                        cidade = Createe.findCidade(String.valueOf(calculo.MelhorIDH(tabelLista, "IDHG").getCodigoIBGE()));
                        pesquisar();
                    }
                    case "Pior IDHG"->{
                        // Chamo o metodo do banco de dados para achar a cidade com o Pior IDHG
                        cidade = Createe.findCidade(String.valueOf(calculo.PiorIDH(tabelLista, "IDHG").getCodigoIBGE()));
                        pesquisar();
                    }
                    case "Classificacao"->{
                        // Se for classificação, precisa ter uma cidade selecionada, mostra uma janela falando qual a classificação dela
                        if(selectCidade != null) JOptionPane.showMessageDialog(this, "A cidade selecionada esta classificada na posição: " + 
                                calculo.Posicao(tabelLista, selectCidade, "IDHG"), "Classificação IDHG", JOptionPane.INFORMATION_MESSAGE);
                        else JOptionPane.showMessageDialog(this, "Selecione uma cidade primeiro", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(this, "Não foi possivel localizar", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Isso é para voltar ao primeiro item
        jComboBox_EstatisticaIDHG.setSelectedIndex(0);
    }//GEN-LAST:event_jComboBox_EstatisticaIDHGActionPerformed

    // Metodo para a combo box Estatistica IDHE
    private void jComboBox_EstatisticaIDHEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_EstatisticaIDHEActionPerformed
        // Pego qual opção o usuario digitou
        String opcaoSelecionada =  String.valueOf(jComboBox_EstatisticaIDHE.getSelectedItem());
        // Caso não seja a primeira opção
        if(!opcaoSelecionada.equals("Estatistica IDHE")){
            try{
                // Pego a lista do banco de dados
                LinkedList<Cidade> tabelLista = banco.returnListCidade();
                // Dependendo do tipo da opção eu mudo o metodo que utilizo
                switch(opcaoSelecionada){
                    case "Melhor IDHE"->{
                        // Chamo o metodo do banco de dados para achar a cidade com o Melhor IDHE
                        cidade = Createe.findCidade(String.valueOf(calculo.MelhorIDH(tabelLista, "IDHE").getCodigoIBGE()));
                        pesquisar();
                    }
                    case "Pior IDHE"->{
                        // Chamo o metodo do banco de dados para achar a cidade com o Pior IDHE
                        cidade = Createe.findCidade(String.valueOf(calculo.PiorIDH(tabelLista, "IDHE").getCodigoIBGE()));
                        pesquisar();
                    }
                    case "Classificacao"->{
                        // Se for classificação, precisa ter uma cidade selecionada, mostra uma janela falando qual a classificação dela
                        if(selectCidade != null) JOptionPane.showMessageDialog(this, "A cidade selecionada esta classificada na posição: " + 
                                calculo.Posicao(tabelLista, selectCidade, "IDHE"), "Classificação IDHE", JOptionPane.INFORMATION_MESSAGE);
                        else JOptionPane.showMessageDialog(this, "Selecione uma cidade primeiro", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(this, "Não foi possivel localizar", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Isso é para voltar ao primeiro item
        jComboBox_EstatisticaIDHE.setSelectedIndex(0);
    }//GEN-LAST:event_jComboBox_EstatisticaIDHEActionPerformed

    // Metodo para a combo box Estatistica IDHL
    private void jComboBox_EstatisticaIDHLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_EstatisticaIDHLActionPerformed
        // Pego qual opção o usuario digitou
        String opcaoSelecionada =  String.valueOf(jComboBox_EstatisticaIDHL.getSelectedItem());
        // Caso não seja a primeira opção
        if(!opcaoSelecionada.equals("Estatistica IDHL")){
            try{
                // Pego a lista do banco de dados
                LinkedList<Cidade> tabelLista = banco.returnListCidade();
                // Dependendo do tipo da opção eu mudo o metodo que utilizo
                switch(opcaoSelecionada){
                    case "Melhor IDHL"->{
                        // Chamo o metodo do banco de dados para achar a cidade com o Melhor IDHL
                        cidade = Createe.findCidade(String.valueOf(calculo.MelhorIDH(tabelLista, "IDHL").getCodigoIBGE()));
                        pesquisar();
                    }
                    case "Pior IDHL"->{
                        // Chamo o metodo do banco de dados para achar a cidade com o Pior IDHL
                        cidade = Createe.findCidade(String.valueOf(calculo.PiorIDH(tabelLista, "IDHL").getCodigoIBGE()));
                        pesquisar();
                    }
                    case "Classificacao"->{
                        // Se for classificação, precisa ter uma cidade selecionada, mostra uma janela falando qual a classificação dela
                        if(selectCidade != null) JOptionPane.showMessageDialog(this, "A cidade selecionada esta classificada na posição: " + 
                                calculo.Posicao(tabelLista, selectCidade, "IDHL"), "Classificação IDHL", JOptionPane.INFORMATION_MESSAGE);
                        else JOptionPane.showMessageDialog(this, "Selecione uma cidade primeiro", "Erro", JOptionPane.ERROR_MESSAGE);                
                    }
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Isso é para voltar ao primeiro item
        jComboBox_EstatisticaIDHL.setSelectedIndex(0);
    }//GEN-LAST:event_jComboBox_EstatisticaIDHLActionPerformed

    // Metodo para o botão Grafico
    private void jButton_GraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GraficoActionPerformed
        GraficoTela graphicTela = new GraficoTela();
        graphicTela.setVisible(true);
    }//GEN-LAST:event_jButton_GraficoActionPerformed

    // Metodo para o botão Exportar
    private void jButton_ExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExportarActionPerformed
        try {
            csv.Out();
            JOptionPane.showMessageDialog(this, "Exportado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);  
        }
    }//GEN-LAST:event_jButton_ExportarActionPerformed
    
    // Metodos para caso o usuario clique ESC, a tabela resetar
    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
        if(evt.getKeyCode() == 27){
            jTextField_Pesquisa.setText("Digite o codigo do IBGE ou municipio");
            cidade = null;
            selectCidade = null;
            listCidades = null;
            pesquisar();
        }
    }//GEN-LAST:event_jPanel1KeyPressed

    private void jTable_CidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable_CidadeKeyPressed
        if(evt.getKeyCode() == 27){
            jTextField_Pesquisa.setText("Digite o codigo do IBGE ou municipio");
            cidade = null;
            selectCidade = null;
            listCidades = null;
            pesquisar();
        }
    }//GEN-LAST:event_jTable_CidadeKeyPressed

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
            java.util.logging.Logger.getLogger(Read.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Read.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Read.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Read.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run()  {
                new Read().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Atualizar;
    private javax.swing.JButton jButton_Deletar;
    private javax.swing.JButton jButton_Densidade;
    private javax.swing.JButton jButton_Exportar;
    private javax.swing.JButton jButton_Grafico;
    private javax.swing.JButton jButton_Novo;
    private javax.swing.JButton jButton_PIBpC;
    private javax.swing.JButton jButton_Voltar;
    private javax.swing.JComboBox<String> jComboBox_EstatisticaIDHE;
    private javax.swing.JComboBox<String> jComboBox_EstatisticaIDHG;
    private javax.swing.JComboBox<String> jComboBox_EstatisticaIDHL;
    private javax.swing.JComboBox<String> jComboBox_EstatisticaPIBpC;
    private javax.swing.JComboBox<String> jComboBox_IDH;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Cidade;
    private javax.swing.JTextField jTextField_Pesquisa;
    // End of variables declaration//GEN-END:variables
}
