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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Atualizacao;
import model.Cidade;

/**
 * @author Marcos Gabriel <marcossetecruzsoares@gmail.com>
 * @brief JFrame Read
 */

public class UpdateBD {
    
    // Método para verificar se uma cidade existe pelo código IBGE
    public static boolean cidadeExiste(int codigoIbge) throws Exception {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConexaoUtil.getInstance().getConnection();
            String query = "SELECT COUNT(*) AS total FROM Cidade WHERE codigo_ibge = ?";
            stmt = conexao.prepareStatement(query);
            stmt.setInt(1, codigoIbge);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
            return false;
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Erro ao verificar cidade: " + e.getMessage(), e);
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

    // Método para atualizar os dados de uma cidade pelo código IBGE
    public static boolean atualizarCidade(Cidade cidade) throws Exception {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoUtil.getInstance().getConnection();
            String query = "UPDATE Cidade SET " +
                "populacao = ?, domicilios = ?, pib_total = ?, idh = ?, renda_media = ?, " +
                "renda_nominal = ?, pea_dia = ?, idh_educacao = ?, idh_longevidade = ? " +
                "WHERE codigo_ibge = ?";
            stmt = conexao.prepareStatement(query);

            stmt.setInt(1, cidade.getPopulacao());
            stmt.setInt(2, cidade.getDomicilios());
            stmt.setDouble(3, cidade.getPIBTotal());
            stmt.setDouble(4, cidade.getIDHGeral());
            stmt.setDouble(5, cidade.getRendaMedia());
            stmt.setDouble(6, cidade.getRendaNominal());
            stmt.setInt(7, cidade.getPEADia());
            stmt.setDouble(8, cidade.getIDHEducacao());
            stmt.setDouble(9, cidade.getIDHLongevidade());
            stmt.setInt(10, cidade.getCodigoIBGE());

            int rowsUpdatedCidade = stmt.executeUpdate();
            
            Atualizacao atualizacao = new Atualizacao(cidade.getCodigoIBGE());
            
            String sql = "INSERT INTO atualizacao (codigo_ibge, update_data) "
                       + "VALUES (?, ?)";
            stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, atualizacao.getCodigoIBGE());
            stmt.setString(2, atualizacao.getUpdateData());
            
            int rowsUpdatedAtualizacao = stmt.executeUpdate();
            
            return rowsUpdatedCidade > 0 && rowsUpdatedAtualizacao > 0;
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Erro ao atualizar cidade: " + e.getMessage(), e);
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
