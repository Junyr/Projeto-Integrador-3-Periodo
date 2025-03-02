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

package service;

import java.util.LinkedList;
import model.Cidade;


/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @brief Class Estatistica
 */

public class Estatistica {
    // Metodo para o melhor PIBpC
    public Cidade MelhorPIBpC(LinkedList<Cidade> lista) throws Exception{
        Cidade saida = null;
        // Inicializando a variaveil fora do loop.
        double maior = 0;
        // Loop responsavel por varrer a lista para armazenar o maior PIBpc.
        for(int pos = 0; pos < lista.size(); pos++){
            if(lista.get(pos).PIBpC() > maior){
                /** Armazeno o maior na variavel, para poder comparar com os outros valores.
                 * Uso a variavel maior para comparação porque não posso inicializar uma cidade sem os parametros*/
                maior = lista.get(pos).PIBpC();
                saida = lista.get(pos);
            }
        }
        return saida;
    }
    
    // Metodo para o pior PIBpC
    public Cidade PiorPIBpC(LinkedList<Cidade> lista) throws Exception{
        // Inicializando as variaveis fora do loop. Nesse caso eu pego do maior para o menor por isso reaproveito o metodo de cima
        Cidade saida = MelhorPIBpC(lista);
        // Loop responsavel por varrer a lista para armazenar o maior PIBpc.
        for(int pos = 0; pos < lista.size(); pos++){
            if(lista.get(pos).PIBpC() < saida.PIBpC()){
                // Armazeno o maior na variavel, para poder comparar com os outros valores.
                saida = lista.get(pos);
            }
        }
        return saida;
    }
    
    // Metodo para o melhor IDH.
    public Cidade MelhorIDH(LinkedList<Cidade> lista, String escolha) throws Exception{
        // Inicializando a variaveil fora do loop.
        Cidade saida = null;
        double menorIDH = 0;
        // Uso um switch para achar qual IDH estaremos trabalhando
        switch(escolha){
            // Todos funcionam da mesma forma, a unica diferança é o tipo de idh que vou usar
            case "IDHG"->{
                for(int pos = 0; pos < lista.size(); pos++){
                    if(lista.get(pos).getIDHGeral() > menorIDH){
                        menorIDH = lista.get(pos).getIDHGeral();
                        saida = lista.get(pos);
                    }
                }
                return saida;
            }
            case "IDHE"->{
                for(int pos = 0; pos < lista.size(); pos++){
                    if(lista.get(pos).getIDHEducacao() > menorIDH){
                        menorIDH = lista.get(pos).getIDHGeral();
                        saida = lista.get(pos);
                    }
                }
                return saida;
            }
            case "IDHL"->{
                for(int pos = 0; pos < lista.size(); pos++){
                    if(lista.get(pos).getIDHLongevidade() > menorIDH){
                        menorIDH = lista.get(pos).getIDHGeral();
                        saida = lista.get(pos);
                    }
                }
                return saida;
            }
        }
        // Se eu não achar lanço uma exceção
        throw new Exception("Não foi possivel localizar o melhor IDH");
    }
    
    // Metodo para o pior IDH
    public Cidade PiorIDH(LinkedList<Cidade> lista, String escolha) throws Exception{
        /** Esse metodo funciona da mesma forma so que eu coloco como maior IDH 1, porque não tem IDH acima de 1.
         * E a comparação vai ser feita do maior para o menor*/
        Cidade saida = null;
        double maiorIDH = 1;
        switch(escolha){
            case "IDHG"->{
                for(int pos = 0; pos < lista.size(); pos++){
                    if(lista.get(pos).getIDHGeral() < maiorIDH){
                        maiorIDH = lista.get(pos).getIDHGeral();
                        saida = lista.get(pos);
                    }
                }
                return saida;
            }
            case "IDHE"->{
                for(int pos = 0; pos < lista.size(); pos++){
                    if(lista.get(pos).getIDHEducacao() < maiorIDH){
                        maiorIDH = lista.get(pos).getIDHGeral();
                        saida = lista.get(pos);
                    }
                }
                return saida;
            }
            case "IDHL"->{
                for(int pos = 0; pos < lista.size(); pos++){
                    if(lista.get(pos).getIDHLongevidade() < maiorIDH){
                        maiorIDH = lista.get(pos).getIDHGeral();
                        saida = lista.get(pos);
                    }
                }
                return saida;
            }
        }
        throw new Exception("Não foi possivel localizar o pior IDH");
    }
    
    // Metodo para achar a posição dentro de um ranking
    public int Posicao(LinkedList<Cidade> lista, Cidade cidade, String escolha) throws Exception{
        LinkedList<Cidade> rankingLista = new LinkedList<Cidade>();
        // Incializo a lista.size() em uma variavel pois a lista.size() vai ficar alterando
        int tamanhoLista = lista.size();
        switch(escolha){
            case "PIBpC"->{
                // Percoro a lista de cima para baixo
                for(int pos = tamanhoLista - 1; rankingLista.size() != tamanhoLista; pos--){
                    // Se a cidade não for nulo
                    if(lista.get(pos) != null){
                        // Pego a cidade com o maior PIBpC
                        Cidade ranking = MelhorPIBpC(lista);
                        // Coloco ele no ranking
                        rankingLista.add(ranking);
                        // Depois eu removo, se não a cidade com o maior PIBpC sempre sera ele
                        lista.remove(ranking);
                    }
                }
                // Percorro a lista rankead para achar a pos dele, coloco + 1 no final porque a pos começa com 0
                for(int pos = 0; pos < rankingLista.size(); pos++){
                    if(rankingLista.get(pos).getCodigoIBGE().equals(cidade.getCodigoIBGE())) return pos + 1;
                }
            }
            // Os outros funcionam da mesma forma, apenas mudando o tipo
            case "IDHG"->{
                for(int pos = tamanhoLista - 1; rankingLista.size() != tamanhoLista; pos--){
                    if(lista.get(pos) != null){
                        Cidade ranking = MelhorIDH(lista, "IDHG");
                        rankingLista.add(ranking);
                        lista.remove(ranking);
                    }
                }
                for(int pos = 0; pos < rankingLista.size(); pos++){
                    if(rankingLista.get(pos).getCodigoIBGE().equals(cidade.getCodigoIBGE())) return pos + 1;
                }
            }
            case "IDHE"->{
                for(int pos = tamanhoLista - 1; rankingLista.size() != tamanhoLista; pos--){
                    if(lista.get(pos) != null){
                        Cidade ranking = MelhorIDH(lista, "IDHE");
                        rankingLista.add(ranking);
                        lista.remove(ranking);
                    }
                }
                for(int pos = 0; pos < rankingLista.size(); pos++){
                    if(rankingLista.get(pos).getCodigoIBGE().equals(cidade.getCodigoIBGE())) return pos + 1;
                }
            }
            case "IDHL"->{
                for(int pos = tamanhoLista - 1; rankingLista.size() != tamanhoLista; pos--){
                    if(lista.get(pos) != null){
                        Cidade ranking = MelhorIDH(lista, "IDHL");
                        rankingLista.add(ranking);
                        lista.remove(ranking);
                    }
                }
                for(int pos = 0; pos < rankingLista.size(); pos++){
                    if(rankingLista.get(pos).getCodigoIBGE().equals(cidade.getCodigoIBGE())) return pos + 1;
                }
            }
        }
        throw new Exception("Não foi possivel achar a posição");
    }
    
    // Metodo para o Grafico
    public LinkedList<Cidade> RankingListaPopulacao(LinkedList<Cidade> lista) throws Exception{
        // Inicializo uma lista para o ranking
        LinkedList<Cidade> rankingLista = new LinkedList<Cidade>();
        // Inicializo as variaveis fora do loop
        Cidade ranking = null;
        int popMaior = 0;
        // Quero o ranking apenas do Top 5
        while(rankingLista.size() != 5){
            // Nesse for eu pego a cidade com a mairo população
            for(int pos = 0; pos < lista.size(); pos++){
                if(lista.get(pos).getPopulacao() > popMaior){
                    popMaior = lista.get(pos).getPopulacao();
                    ranking = lista.get(pos);
                }
            }
            // Coloco ela na lista
            rankingLista.add(ranking);
            // Depois removo ela
            lista.remove(ranking);
            // Reseto a variavel popMaior, pois caso não faça isso o numero da maior população vai continuar nela
            popMaior = 0;
        }
        return rankingLista;
    }
}