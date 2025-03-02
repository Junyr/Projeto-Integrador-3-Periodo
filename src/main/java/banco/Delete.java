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
 * @brief class delete
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {

    public static boolean deleteCidade(int codigoIBGE) {
        String sql = "DELETE FROM cidade WHERE codigo_ibge = ?";

        try (Connection conn = ConexaoUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             // Configura o valor do parâmetro da consulta.
            stmt.setInt(1, codigoIBGE);
            // Executa o comando SQL e retorna o número de registros afetados.
            int rowsAffected = (int) stmt.executeUpdate();
            System.out.println(rowsAffected + " registro(s) deletado(s).");
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
             // Captura e exibe possíveis erros ao executar a operação.
            System.err.println("Erro ao deletar cidade: " + e.getMessage());
            return false;
        }
    }
     //Deleta múltiplas cidades do banco de dados com base em uma lista de códigos IBGE.
    public static boolean deleteByCodigosIBGE(int[] codigosIBGE) {
        // codigosIBGE um array contendo os códigos IBGE das cidades a serem deletadas.
        // `true` se ao menos um registro foi deletado, caso contrário, `false`.
        if (codigosIBGE == null || codigosIBGE.length == 0) {
            System.err.println("Nenhum código IBGE fornecido.");
            return false;
        }
        
        // Gera placeholders dinâmicos para o número de códigos fornecidos.
        String placeholders = String.join(",", "?".repeat(codigosIBGE.length));
        String sql = "DELETE FROM cidades WHERE codigo_ibge IN (" + placeholders + ")";

        try (Connection conn = ConexaoUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             // Preenche os parâmetros da consulta com os códigos IBGE fornecidos.
            for (int i = 0; i < codigosIBGE.length; i++) {
                stmt.setInt(i + 1, codigosIBGE[i]);
            }
             // Executa o comando SQL e retorna o número de registros afetados.
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " registro(s) deletado(s).");
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erro ao deletar cidades pelos códigos IBGE: " + e.getMessage());
            return false;
        }
    }
    // Deleta todas as cidades associadas a um estado específico.
    public static boolean deleteByEstado(String siglaEstado) { // siglaEstado a sigla do estado cujas cidades devem ser deletadas.
        //`true` se ao menos um registro foi deletado, caso contrário, `false`.
        if (siglaEstado == null || siglaEstado.isEmpty()) {
            System.err.println("Sigla do estado inválida.");
            return false;
        }

        String sql = "DELETE FROM cidades WHERE sigla_estado = ?";

        try (Connection conn = ConexaoUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Configura o valor do parâmetro da consulta.
            stmt.setString(1, siglaEstado);
            // Executa o comando SQL e retorna o número de registros afetados.
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " registro(s) deletado(s).");
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            // Captura e exibe possíveis erros ao executar a operação
            System.err.println("Erro ao deletar cidades do estado: " + e.getMessage());

            return false;
        }
    }
}
