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

import java.sql.Connection; // Representa a conexão com o banco de dados.
import java.sql.DriverManager; // Gerencia conexões JDBC.
import java.sql.SQLException; // Trata erros relacionados ao banco de dados.

public class ConexaoUtil { // Declara a classe ConexaoUtil, que gerencia conexões ao banco de dados.
    private static ConexaoUtil conexaoUtil; // Declara uma instância única (singleton) da classe ConexaoUtil.

    // Padrão Singleton para obter a instância única
    public static ConexaoUtil getInstance() {
        if (conexaoUtil == null) {
            conexaoUtil = new ConexaoUtil();
        }
        return conexaoUtil;
    }

    // Método para criar e retornar uma conexão
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // Use o driver atualizado
        // Carrega a classe do driver do MySQL necessário para estabelecer a conexão. A exceção ClassNotFoundException é lançada se o driver não for encontrado.
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Atualize a string de conexão
        // URL de conexão JDBC contendo o protocolo, endereço do servidor, porta, nome do banco e configurações adicionais (useSSL=false para desabilitar SSL e
        String url = "jdbc:mysql://localhost:3306/banco_pi?useSSL=false&serverTimezone=UTC";
        // Nome do usuário para autenticação no banco de dados.
        String user = "root";
        //  Senha do usuário.
        String password = "root";

        // Retorne a conexão
        //Usa o DriverManager para criar uma conexão com o banco de dados utilizando os parâmetros fornecidos.
        return DriverManager.getConnection(url, user, password);
    }
} 