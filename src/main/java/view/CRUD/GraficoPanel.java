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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import service.Estatistica;
import banco.BancoDados;
import java.awt.Font;
import java.util.LinkedList;
import model.Cidade;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @brief JPanel GraficoPanel
 */

public class GraficoPanel extends javax.swing.JPanel {

    // As variaveis que vou usar
    private BancoDados banco = new BancoDados();
    private Estatistica calculo = new Estatistica();
    
    public GraficoPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Método para renderizar o componente no painel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Converte o contexto gráfico para Graphics2D para ter mais controle sobre o desenho
        Graphics2D g2d = (Graphics2D) g;
        try {
            // Obtenho uma lista de cidades ordenadas por população
            LinkedList<Cidade> rankingLista = calculo.RankingListaPopulacao(banco.returnListCidade());

            // Definimos as coordenadas iniciais para o início das barras no gráfico
            int xInicial = 200; // Posição X inicial das barras
            int yInicial = 90; // Posição Y inicial da primeira barra
            int alturaBarra = 30; // Altura de cada barra
            int espacamento = 10; // Espaço entre as barras

            // Calculamos a escala para ajustar o tamanho das barras ao tamanho máximo permitido
            double escala = (double) 300 / rankingLista.get(0).getPopulacao();
            
            // Array de cores para alternar entre elas em cada barra
            Color[] cores = {Color.CYAN, Color.ORANGE, Color.RED, Color.YELLOW, Color.MAGENTA};
            
            // Laço para percorrer a lista de cidades e desenhar cada barra
            for(int pos = 0; pos < rankingLista.size(); pos++){
                // Configurar cor da barra atual
                g2d.setColor(cores[pos]);
                
                // Calcula a largura da barra com base na população e na escala
                int larguraBarra = (int) (rankingLista.get(pos).getPopulacao() * escala);
                
                // Desenha a barra na posição calculada
                g2d.fillRect(xInicial, // Posição X da barra
                        yInicial + pos * (alturaBarra + espacamento), // Posição Y ajustada com base no índice
                        larguraBarra, // Largura da barra (proporcional à população)
                        alturaBarra); // Altura fixa da barra

                // Configura a fonte (nome, estilo e tamanho)
                g2d.setFont(new Font("Arial", Font.BOLD, 12));

                // Configura a cor do texto para preto
                g2d.setColor(Color.BLACK);
                
                // Desenha o nome da cidade à esquerda da barra
                g2d.drawString(rankingLista.get(pos).getNome(), // Nome da cidade
                        xInicial - 160, // Posição X ajustada para exibir à esquerda da barra
                        yInicial + pos * (alturaBarra + espacamento) + alturaBarra / 2 + 5); // Posição Y centralizada verticalmente na barra

                 // Desenha o valor da população ao final da barra
                g2d.drawString(String.valueOf(rankingLista.get(pos).getPopulacao()), // População da cidade
                        xInicial + larguraBarra + 5, // Posição X logo após o final da barra
                        yInicial + pos * (alturaBarra + espacamento) + alturaBarra / 2 + 5); // Posição Y centralizada verticalmente na barra
            }
        } catch (Exception ex) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
