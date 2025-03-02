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

package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @brief Class Atualizacao
 */

// Classe responsavel por armazenar a data e hora da atualização
public class Atualizacao {
    
    /** Basicamente nessa classe vai ter o codigoIBGE para poder achar qual cidade que foi atualizado e a 
     * updateData que é justamente a data e hora da atualização*/
    private int codigoIBGE;
    private String updateData;
    
    //O construtor so tem o codigoIBGE como parametro, o updateData é gerado automaticamente
    public Atualizacao(int codigoIBGE){
        this.codigoIBGE = codigoIBGE;
        this.updateData = updateData();
    }
    
    // O Construtor com os dois parametros
    public Atualizacao(int codigoIBGE, String updateData){
        this.codigoIBGE = codigoIBGE;
        this.updateData = updateData;
    }

    public int getCodigoIBGE() {
        return codigoIBGE;
    }

    public String getUpdateData() {
        return updateData;
    }
    
    public LocalDateTime dataHoraAtual(){
        return LocalDateTime.now();
    }
    
    public DateTimeFormatter dataHoraFormato() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }
    
    /** Esse get vai usar um formato especifico de data e hora, ambas presentes logo acima
     * A variavel dataHora vai receber a data e hora atual através do metodo dataHoraAtual() 
     * A variavel formato vai receber o formato que eu defini através do metodo dataHoraFormato()
     * Depois apenas retorno uma String formatada de acordo com os requisitos do cliente */
    public String updateData() {
        LocalDateTime dataHora = dataHoraAtual();
        DateTimeFormatter formato = dataHoraFormato();
        return dataHora.format(formato);
    }
}
