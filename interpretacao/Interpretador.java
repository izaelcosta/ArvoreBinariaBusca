package interpretacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

import implementacao.ABB;

public class Interpretador {

    private static final int NUM_ARQUIVOS = 2;
    private final static ArrayList<String> INSTRUCOES = new ArrayList<>(Arrays.asList("INSIRA, BUSQUE", "ENESIMO", "POSICAO", "CHEIA","COMPLETA", "IMPRIMA", "REMOVA","MEDIANA" ));
    
    String[] argumentos;
    
    public Interpretador(String[] args) {
        argumentos = args;
    }
    
    public void aplicarInstrucoes(ABB arvore){

        //Aplicado as instrucoes na arvore
        for(InstrucaoSemParametro i : this.criarInstrucoes()){
            //Aplica as instrucoes com paramentro
            aplicarInstrucao(arvore,i);    
        }
        
    }

    private void aplicarInstrucao(ABB arvore, InstrucaoSemParametro i){
        //verifica a operacoes equivalente na arvore e a executa
    	
    	
    	
        switch(i.getNome()){
            case "COMPLETA":
                System.out.println("A árvore é completa: " + arvore.ehCompleta());
            break;
            case "CHEIA":
                System.out.println("A arvore é cheia: " + arvore.ehCheia());
            break;
            case "IMPRIMA":
                System.out.println("Imprimindo a árvore: " + arvore.toString());
            break;
            case "MEDIANA":
                System.out.println("A mediana é:  " + arvore.mediana().toString());
            break;
            case "ENESIMO":
                System.out.println("O "+ ((InstrucaoComParametro)i).getParametro() +"º termo da árvore é: " + arvore.enesimoElemento(((InstrucaoComParametro)i).getParametro()));
            break;
            case "INSIRA":
                arvore.inserir(((InstrucaoComParametro)i).getParametro());
            break;
            case "POSICAO":
                System.out.println("A posição do valor " + ((InstrucaoComParametro)i).getParametro() + " é: " + arvore.posicao(((InstrucaoComParametro)i).getParametro()));
            break;
            case "REMOVA":
                arvore.remover(((InstrucaoComParametro)i).getParametro());
            break;   
        }
    }
    
    public List<InstrucaoSemParametro> criarInstrucoes(){
        
        
        Scanner scan;
        
        ArrayList<InstrucaoSemParametro> instrucoes = new ArrayList<>();
        
        if(argumentos.length<NUM_ARQUIVOS){
            JOptionPane.showMessageDialog(null, "Quantidade de arquivo passado por parâmentro é menor do que realmente deveria ser,o programa será abortado");
            System.exit(0);
        }
        
        for(int i=0; i<NUM_ARQUIVOS;i++){
            
            try {
                
                scan = new Scanner(new File(argumentos[i]));
               
                //Lendo os arquivos
                while(scan.hasNext()){
                    switch(i){
                        //Adicionando a instrução inserir
                        case 0:
                            instrucoes.add(new InstrucaoComParametro("INSIRA",scan.nextInt()));
                        break;
                        //Adicionando como uma instrução
                        case 1:
                            //Pega o valor da linha
                            String s = scan.nextLine();

                            //Verificando se eh uma instrucao com parametro
                            if (s.contains(" ")) {
                                //Ver se instrucao eh valida
                                if (INSTRUCOES.contains(s.substring(0, s.indexOf(" ")))) {
                                    try {
                                        //Adicionando a instrucao que tem parametro
                                        instrucoes.add(new InstrucaoComParametro(s.substring(0, s.indexOf(" ")), Integer.parseInt(s.substring(s.indexOf(" ") + 1, s.length()))));
                                    } catch (NumberFormatException e) {
                                        //Caso der erro ao ler o parametro inteiro
                                        System.out.println("Erro ao formar o inteiro passado por parâmetro na instrução: " + s.substring(0, s.indexOf(" ")));
                                    }
                                }
                            } else {
                                //Verifica se a instrucao eh valida
                                if (INSTRUCOES.contains(s)) {
                                    //Adiconando instrucao sem parametro
                                    instrucoes.add(new InstrucaoSemParametro(s));
                                } else {
                                    //Caso a instrucao nao esteja na lista de intrucoes
                                    System.out.println("Instrução: " + s + " é desconhecida.");
                                }
                            }
                        break;    
                    }
                }
                
                scan.close();
                
            } catch (InputMismatchException e ){
                JOptionPane.showMessageDialog(null,"Erro na leitura de um inteiro no arquivo, verifique o arquivo e tente novamente");
                System.exit(0);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,"Arquivo nao encontrado, a execução da aplicação será interrompida");
                System.exit(0);
            }
        }
    return instrucoes;    
    }
}
