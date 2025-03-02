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

package banco;

/**
 * @author Marcos Gabriel <marcossetecruzsoares@gmail.com>
 * @brief JFrame Read
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

public class UsuarioDAO {

    public boolean inserirUsuario(Usuario usuario) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            // Obter conexão com o banco
            conexao = ConexaoUtil.getInstance().getConnection();

            // Query para inserir usuário
            String sql = "INSERT INTO usuario (nome_usuario, cpf, email, senha) VALUES (?, ?, ?, ?)";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCPF());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());

            // Executa o comando
            int linhasAfetadas = stmt.executeUpdate();

            // Retorna true se o usuário foi inserido com sucesso
            return linhasAfetadas > 0;

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
            return false;

        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
    
    // Método para verificar se o e-mail/CPF e senha existem no banco de dados
    public Usuario verificarLogin(String login, String senha) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obter conexão com o banco
            conexao = ConexaoUtil.getInstance().getConnection();

            // Query para verificar login por e-mail ou CPF
            String sql = "SELECT * FROM usuario WHERE (email = ? OR cpf = ?) AND senha = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, login); // Pode ser e-mail
            stmt.setString(2, login); // Ou CPF
            stmt.setString(3, senha); // Senha

            // Executa a query
            rs = stmt.executeQuery();

            // Verifica se encontrou algum resultado
            if (rs.next()) {
                // Cria um objeto Usuario com os dados retornados
                return new Usuario(
                    rs.getString("nome_usuario"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erro ao verificar login: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return null; // Retorna null se não encontrar o usuário
    }

}
