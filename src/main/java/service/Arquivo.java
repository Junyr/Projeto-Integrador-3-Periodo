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

import banco.BancoDados;
import model.Cidade;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.Collator;
import java.text.ParseException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Stack;
import model.Atualizacao;
import view.CRUD.Read;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @brief Class Arquivo
 */

// Classe responsavel pela importação e exportação do arquivo a ou de uma lista.
public class Arquivo {

    // LinkedList para armazenar a lista criada ao ler o .csv
    private LinkedList<Cidade> listaRegiao = new LinkedList();
    // Read para poder pegar o update
    private Read read;
    // Banco para poder pegar os dados do banco;
    private BancoDados banco = new BancoDados();

    // Construtor para importação
    public Arquivo() {
    }
    
    // Construtor para exportação
    public Arquivo(Read read) {
        this.read = read;
    }
    
    /** O Charset é usado para definir como o arquivo vai ser lido */
    Charset enconding;
    
    // Metodo para ler o arquivo
    public LinkedList In() throws ParseException, Exception{
        enconding = Charset.forName("UTF-8");
       /** FileInputStream abre o arquivo em um determinado endereço e o InputStreamReader
        * vai especificar qual o enconding que irei utilizar, sendo o windows-1252,
        * logo após o BufferedReader armazenara essa leitura em buffer. */
       try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(
       "src/main/resources/data/CSV Normalizado.csv"), enconding))) {
           
           /** A varivael itemCsv vai ser responsavel por ler a linha. */
           String itemCsv = br.readLine();
           
           // Loop responsavel por ler todas as linhas do .csv
           while(itemCsv != null){
               // Separo todos os Strings com .split usando ";" como parametro
               String[] fields = itemCsv.split(";");
               
               // Esse bloco transformara os Strings em seus respectivos tipos
               int codigoIBGE = Integer.parseInt(fields[0]);
               // Uso replace nesses por conta do arquivo .csv conter "" entre os nomes, uso replaceAll para remove-los
               String nome = fields[1].replaceAll("\"", "");
               String microregiao = fields[2].replaceAll("\"", "");
               String sigla = fields[3].replaceAll("\"", "");
               String regiao = fields[4].replaceAll("\"", "");
               double area = Double.parseDouble(fields[5]);
               /** Por algum motivo a população e domicilios esta como double e uso .replaceAll("\\.\\d+$", "")
                * para remover os numero depois do ponto */
               int populacao = Integer.parseInt(fields[6].replaceAll("\\.\\d+$", ""));
               int domicilios = Integer.parseInt(fields[7].replaceAll("\\.\\d+$", ""));
               double PIBTotal = Double.parseDouble(fields[8]);
               double IDHGeral = Double.parseDouble(fields[9]);
               double RendaMedia = Double.parseDouble(fields[10]);
               double RendaNominal = Double.parseDouble(fields[11]);
               int PEADia = Integer.parseInt(fields[12].replaceAll("\\.\\d+$", ""));
               double IDHEducacao = Double.parseDouble(fields[13]);
               double IDHLongevidade = Double.parseDouble(fields[14]);
               
               // Instanciando a classe para pôr na lista CSVIn.
               Cidade cid = new Cidade(codigoIBGE, nome, microregiao, sigla,
                    regiao, area, populacao, domicilios, PIBTotal, IDHGeral, RendaMedia,
                    RendaNominal, PEADia, IDHEducacao, IDHLongevidade);
               
               listaRegiao.add(cid);
               
               //Assim passa para a proxima linha até aparecer null
               itemCsv = br.readLine();
            }
           br.close();
           
       }  catch(IOException e){
                System.out.println("Arquivo não encontrado: " + e.getMessage());
       }
            return listaRegiao;
       } 
    
    // Metodo para exportar o arquivo.
    public void Out() throws Exception{
        enconding = Charset.forName("windows-1252");
        // Segue a mesma logica do In() so que com OutputStreamWriter e FileOutputStream, mas desempenham o mesmo papel.
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream
        ("src/main/resources/data/CSV Exportado.csv"), enconding))){
            /** Caso vocês abram o .csv em um documento de texto do windows, perceberão que o ; é responsavel por separar
             * as celulas no excel, por isso eu ponho dessa forma, alem de ser mais facil de identificar a separação.
             * Definindo o titulo. */
            bw.write("Código IBGE" + ";"
                    + "Municí­pios" + ";"
                    + "Microregião" + ";"
                    + "Estado" + ";"
                    + "Região Geográfica" + ";"
                    + "Área km²" + ";"
                    + "População" + ";"
                    + "Domicílios" + ";"
                    + "PIB Total (R$ mil)" + ";"
                    + "IDH - Índice de Desenv. Humano" + ";"
                    + "Renda Média" + ";"
                    + "Renda Nominal" + ";"
                    + "PEA Dia" + ";"
                    + "IDH - Dimensção Educação" + ";"
                    + "IDH - Dimensção Longevidade" + ";" 
                    + "Atualização");
           bw.newLine();
           // Pego a lista do banco de dados
           LinkedList<Cidade> out = banco.returnListCidade();
           // Ordeno ela com .sort e Comparator que esta comparando o nome da cidade
           out.sort(Comparator.comparing(Cidade::getNome, Collator.getInstance(Locale.forLanguageTag("pt-BR"))));
           // Uso a pilha(stack) para as atualizações e pego ela do read
           Stack<Atualizacao> atualizacao = banco.returnListAtualizacao();
           // Loop que vai escrever cada linha
           for(int pos = 0; pos < out.size(); pos++){
               // Caso atualizacaoPos seja -1 significa que não tem atualizações
               int atualizacaoPos = -1;
               
               // Faço um laço de repetição para achar a atualização correspondente a cidade da pos do for
               for(int posStack = 0; posStack < atualizacao.size(); posStack++){
                   if(atualizacao.get(posStack).getCodigoIBGE() == out.get(pos).getCodigoIBGE()) atualizacaoPos = posStack;
               }
               // Se houver atualização a unica dirença é no final vou pôr a atualização
               if(atualizacaoPos != -1){
                   /** Da mesma forma que eu separei a String usando ";" estarei usando para pular a celula. 
                    * Uso o for por posição para varrer a lista e não precisar remover um elemento para renovar o dado.*/
                   bw.write(out.get(pos).getCodigoIBGE() + ";"
                       + out.get(pos).getNome() + ";"
                       + out.get(pos).getMicroregiao() + ";"
                       + out.get(pos).getSigla() + ";"
                       + out.get(pos).getRegiao() + ";"
                       + String.format("%.2f", out.get(pos).getArea()) + ";"
                       + out.get(pos).getPopulacao() + ";"
                       + out.get(pos).getDomicilios() + ";"
                       + String.format("%.2f", out.get(pos).getPIBTotal()) + ";" 
                       + String.format("%.2f", out.get(pos).getIDHGeral()) + ";"
                       + String.format("%.2f", out.get(pos).getRendaMedia()) + ";"
                       + String.format("%.2f", out.get(pos).getRendaNominal()) + ";"
                       + out.get(pos).getPEADia() + ";"
                       + String.format("%.2f", out.get(pos).getIDHEducacao()) + ";"
                       + String.format("%.2f", out.get(pos).getIDHLongevidade()) + ";"
                       + atualizacao.get(atualizacaoPos).getUpdateData());
                   //Aqui eu removo a atulização da pilha
                    atualizacao.removeElementAt(atualizacaoPos);
                    bw.newLine();
               } else {
                    bw.write(out.get(pos).getCodigoIBGE() + ";"
                            + out.get(pos).getNome() + ";"
                            + out.get(pos).getMicroregiao() + ";"
                            + out.get(pos).getSigla() + ";"
                            + out.get(pos).getRegiao() + ";"
                            + String.format("%.2f", out.get(pos).getArea()) + ";"
                            + out.get(pos).getPopulacao() + ";"
                            + out.get(pos).getDomicilios() + ";"
                            + String.format("%.2f", out.get(pos).getPIBTotal()) + ";" 
                            + String.format("%.2f", out.get(pos).getIDHGeral()) + ";"
                            + String.format("%.2f", out.get(pos).getRendaMedia()) + ";"
                            + String.format("%.2f", out.get(pos).getRendaNominal()) + ";"
                            + out.get(pos).getPEADia() + ";"
                            + String.format("%.2f", out.get(pos).getIDHEducacao()) + ";"
                            + String.format("%.2f", out.get(pos).getIDHLongevidade()));
                    bw.newLine();
                }
           }
           bw.close();
       } catch(IOException e){
           throw new Exception("Não foi possivel exportar: " + e.getMessage());
       }
   }
}
