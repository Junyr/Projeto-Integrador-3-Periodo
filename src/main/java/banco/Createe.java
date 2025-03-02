/*
 * Copyright (C) 2024 Marcos Gabriel Soares Cruz <marcossetecruzsoares@gmail.com    >
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
 * @brief Class Createe
 */

import java.sql.Connection; // Importa classes para gerenciar conexões ao banco de dados.
import java.sql.PreparedStatement; // Importa a classe para preparar consultas parametrizadas.
import java.sql.ResultSet; // Importa a classe para manipular resultados de consultas.
import java.sql.SQLException; // Importa exceções para tratar erros relacionados ao banco de dados.
import java.util.LinkedList; // Importa a classe para trabalhar com listas dinâmicas.
import model.Cidade; // Importa a classe "Cidade", que representa o modelo de dados utilizado.

public class Createe {

    // Método para buscar uma cidade por código IBGE ou nome.
    public static Cidade findCidade(String pesquisa) throws Exception {
        Connection conexao = null; // Objeto para gerenciar a conexão com o banco.
        PreparedStatement stmt = null; // Objeto para preparar e executar comandos SQL
        ResultSet rs = null; // Objeto para armazenar os resultados da consulta.
        String query = "";  // Armazena a consulta SQL a ser executada.
        
        try {
            // Verifica se o texto contém ";" (indicando código IBGE e nome juntos).
            if(pesquisa.contains(";")){
                String codigoNome[] = pesquisa.split(";"); // Separa o texto pelo delimitador ";".
                conexao = ConexaoUtil.getInstance().getConnection(); // Obtém a conexão do banco.
                 // Consulta SQL para buscar por código IBGE ou nome.
                query = "SELECT * FROM Cidade WHERE codigo_ibge = ? OR nome_municipio = ?";
                stmt = conexao.prepareStatement(query);
                stmt.setString(1, codigoNome[0]); // Define o código IBGE.
                stmt.setString(2, codigoNome[1]); // Define o nome do município.
                rs = stmt.executeQuery(); // Executa a consulta.
            }
            // Verifica se a pesquisa é apenas um número (código IBGE).
            if(pesquisa.matches("(\\d+)+")){
                conexao = ConexaoUtil.getInstance().getConnection();

                query = "SELECT * FROM Cidade WHERE codigo_ibge = ?";
                stmt = conexao.prepareStatement(query);
                stmt.setString(1, pesquisa);// Define o código IBGE.
                rs = stmt.executeQuery();
            }    
            // Verifica se a pesquisa é apenas texto (nome do município).
            if(pesquisa.matches("[a-zA-Zà-úÀ-Úâ-ûÂ-ÛçÇ]+(\\s[a-zA-Zà-úÀ-Úâ-ûÂ-ÛçÇ]+)*")){
                conexao = ConexaoUtil.getInstance().getConnection();

                query = "SELECT * FROM Cidade WHERE nome_municipio = ?";
                stmt = conexao.prepareStatement(query);
                stmt.setString(1, pesquisa); // Define o nome do município.
                rs = stmt.executeQuery();
            }
            // Se houver resultados, cria e retorna um objeto Cidade com os dados encontrados.
            if (rs.next()) {
                    // Cria e retorna a cidade encontrada
                    return new Cidade(
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
                }
            return null; // Retorna null se nenhuma cidade for encontrada.
        } catch (ClassNotFoundException | SQLException e) {
            // Trata exceções e encapsula a mensagem em outra exceção.
            throw new Exception("Erro ao buscar a cidade: " + e.getMessage(), e);
        } finally {
            // Fecha os recursos utilizados (ResultSet, PreparedStatement e Connection).
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
    
    // Método para buscar a cidade por código IBGE ou nome
    public static LinkedList<Cidade> findListCidade(String pesquisa) throws Exception {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "";
        LinkedList<Cidade> lista = new LinkedList<Cidade>(); // Lista para armazenar as cidades encontradas.
        
        try {
             // Verifica se a pesquisa é um texto (nome do município).
            if(pesquisa.matches("[a-zA-Zà-úÀ-Úâ-ûÂ-ÛçÇ]+(\\s[a-zA-Zà-úÀ-Úâ-ûÂ-ÛçÇ]+)*")){
                conexao = ConexaoUtil.getInstance().getConnection();

                query = "SELECT * FROM Cidade WHERE LOWER(nome_municipio) LIKE LOWER(?)";
                stmt = conexao.prepareStatement(query);
                stmt.setString(1, "%" + pesquisa + "%"); // Define o termo de busca.
                rs = stmt.executeQuery();
            }
              // Adiciona cada cidade encontrada à lista.
            while(rs.next()) {
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
                lista.add(cidade);
            }
            return lista;
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Erro ao buscar a cidade: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    // Método para criar uma nova cidade
    public static boolean createCidade(Cidade cidade) throws Exception {
        // Verifica se a cidade já existe no banco de dados.
        if (findCidade(String.valueOf(cidade.getCodigoIBGE()) + ";" + cidade.getNome()) != null) {
            // Retorna false se a cidade já existir.
            return false;
        }

        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoUtil.getInstance().getConnection();
            // Consulta SQL para inserir uma nova cidade.
            String insertQuery = "INSERT INTO Cidade (codigo_ibge, nome_municipio, micro_regiao, estado_sigla, regiao_geografica, area_km2, populacao, domicilios, pib_total, idh, renda_media, renda_nominal, pea_dia, idh_educacao, idh_longevidade) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(insertQuery);

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

            stmt.executeUpdate();
            return true; // Cidade criada com sucesso
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Erro ao criar a cidade: " + e.getMessage(), e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}
