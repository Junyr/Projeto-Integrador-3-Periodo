/*
 * Copyright (C) 2024 Marcos Gabriel Soares Cruz <marcossetecruzsoares@gmail.com>
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

package banco;

/**
 * @author Marcos Gabriel <marcossetecruzsoares@gmail.com>
 * @brief JFrame Read
 */

import java.sql.Connection; // Importa a classe para gerenciar conexões com banco de dados. 
import java.sql.PreparedStatement; // Permite criar e executar comandos SQL parametrizados.
import java.sql.ResultSet; // Representa o resultado de uma consulta SQL.
import java.sql.SQLException; // Trata exceções relacionadas ao SQL.
import java.util.LinkedList; // Estrutura de dados para listas encadeadas.
import java.util.Stack; // Estrutura de pilha.
import model.Atualizacao;  // Classe  para manipular atualizações.
import model.Cidade; // Classe específica para manipular dados de cidades.

public class BancoDados {
    // Método para inserir uma lista de cidades no banco de dados.
    public void inserirDados(LinkedList<Cidade> listaCidades) throws Exception {
        Connection conexao = null; // Declara uma variável para a conexão com o banco de dados.
        PreparedStatement stmt = null; // Declara uma variável para o comando SQL parametrizado.

        try {
            // Estabelece a conexão com o banco de dados.
            conexao = ConexaoUtil.getInstance().getConnection();
            

            // Declaração SQL para inserir dados na tabela "Cidade".
            String sql = "INSERT INTO Cidade (codigo_ibge, nome_municipio, micro_regiao, estado_sigla, regiao_geografica, area_km2, populacao, domicilios, pib_total, idh, renda_media, renda_nominal, pea_dia, idh_educacao, idh_longevidade) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Prepara o comando SQL para execução.
            stmt = conexao.prepareStatement(sql);

            // Percorrer a lista e inserir os dados
            for(int pos = 0; pos <= listaCidades.size(); pos++){
                Cidade cidade = (Cidade) listaCidades.get(pos);
                // Define os parâmetros da consulta com os valores da cidade.
                stmt.setInt(1, cidade.getCodigoIBGE());
                stmt.setString(2, cidade.getNome());
                stmt.setString(3, cidade.getMicroregiao());
                stmt.setString(4, cidade.getSigla());
                stmt.setString(5, cidade.getRegiao());
                stmt.setDouble(6, cidade.getArea());
                stmt.setInt(7, cidade.getPopulacao());
                stmt.setInt(8, cidade.getDomicilios());
                stmt.setDouble(9, cidade.getPIBTotal());
                stmt.setDouble(10, cidade.getIDHGeral());
                stmt.setDouble(11, cidade.getRendaMedia());
                stmt.setDouble(12, cidade.getRendaNominal());
                stmt.setInt(13, cidade.getPEADia());
                stmt.setDouble(14, cidade.getIDHEducacao());
                stmt.setDouble(15, cidade.getIDHLongevidade());

                // Executa a inserção no banco de dados.
                stmt.executeUpdate();
            }

            System.out.println("Dados inseridos com sucesso!");

        } catch (ClassNotFoundException | SQLException e) {
            // Tratamento de erros durante a inserção.
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            // Fecha os recursos utilizados, garantindo que não haverá vazamento de memória.
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
    // Método para buscar uma lista de cidades no banco de dados.
    public LinkedList<Cidade> returnListCidade() throws Exception {
        Connection conexao = null; // Declara a conexão.
        PreparedStatement stmt = null; // Declara o comando SQL.
        ResultSet rs = null; // Declara o conjunto de resultados.
        String query = "";
        LinkedList<Cidade> saida = new LinkedList<Cidade>();  // Lista para armazenar os resultados.
        
        try {
             // Estabelece a conexão com o banco.
            conexao = ConexaoUtil.getInstance().getConnection();

            query = "SELECT * FROM Cidade WHERE id"; // comando sql
            stmt = conexao.prepareStatement(query);
            rs = stmt.executeQuery();

            while(rs.next()){
                // Cria e retorna a cidade encontrada
                Cidade cidade = new Cidade(
                        rs.getInt("codigo_ibge"),
                        rs.getString("nome_municipio"),
                        rs.getString("micro_regiao"),
                        rs.getString("estado_sigla"),
                        rs.getString("regiao_geografica"),
                        rs.getDouble("area_km2"),
                        rs.getInt("populacao"),
                        rs.getInt("domicilios"),
                        rs.getDouble("pib_total"),
                        rs.getDouble("idh"),
                        rs.getDouble("renda_media"),
                        rs.getDouble("renda_nominal"),
                        rs.getInt("pea_dia"),
                        rs.getDouble("idh_educacao"),
                        rs.getDouble("idh_longevidade")
                );
                saida.add(cidade); // Adiciona a cidade à lista de saída.
            }
            return saida; // Retorna a lista de cidades.
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Erro ao buscar a cidade: " + e.getMessage(), e);
        } finally {
            // Fecha os recursos.
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
    // Método para retornar uma pilha de atualizações no banco de dados.
    public Stack<Atualizacao> returnListAtualizacao() throws Exception{
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "";
        Stack<Atualizacao> saida = new Stack<Atualizacao>();
        
        try {
             // Estabelece conexão com o banco.
            conexao = ConexaoUtil.getInstance().getConnection();
             // SQL para buscar todas as atualizações.
            query = "SELECT * FROM atualizacao WHERE id";
            stmt = conexao.prepareStatement(query);
            rs = stmt.executeQuery();

            while(rs.next()){
                // Cria uma instância de atualização.
                Atualizacao atualizacao = new Atualizacao(rs.getInt("codigo_ibge"), rs.getString("update_data"));
                saida.add(atualizacao); // Adiciona à pilha.
            }
            return saida; // Retorna a pilha de atualizações.
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Erro ao buscar a cidade: " + e.getMessage(), e);
        } finally {
            // Fecha os recursos.
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}
