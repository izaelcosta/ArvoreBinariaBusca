package implementacao;

import java.math.BigDecimal;

public class ABB<T extends Number>{
    
    private No<T> raiz;
    private int aux1,aux2;
    private T valor;
    
    public ABB() {
        raiz = null;
    }

    public void inserir(T valor){
    
        if(valor!=null){            
            if(raiz==null){  
                raiz = new No(valor);
            }else{
                auxInsercao(raiz, valor);
            }
        }else{
            System.out.println("Valor nulo, não será inserido na árvore");
        }
    }
    
    private void auxInsercao(No<T> no, T valor){
        //Verifica se a direção de inserção será a direita    
        if(no.getValor().doubleValue() < valor.doubleValue()){
            //Verifica se o objeto da direita é nulo
            if(no.getNoDir()!=null){
                //Vai mais a direita
                auxInsercao(no.getNoDir(),valor);
            }else{
                //Caso esteja no final insere o valor
                no.setNoDir(new No(valor));
            }
        }else{
            //Verifica se a inserção será a direita
            if(no.getValor().doubleValue() > valor.doubleValue()){
                //Verifica se o objeto da esquerda é nulo
                if(no.getNoEsq()!=null){
                    //Vai mais a direita
                    auxInsercao(no.getNoEsq(),valor);
                }else{
                    //Caso esteja no final insere o valor
                    no.setNoEsq(new No(valor));
                }
            }   
        }
    }

    public No<T> buscar(T valor){
        if(valor!=null){
           return auxBusca(raiz, valor);
        }else{
            System.out.println("Valor nulo, não pode ser buscado na árvore");
            return null;
        }
    }
    
    private No<T> auxBusca(No<T> no, T valor){
         
        //Verifica se o elemento está para a direita    
        if(no.getValor().doubleValue() < valor.doubleValue()){
            //Verifica se o objeto da direita é nulo
            if(no.getNoDir()!=null){
                //Vai mais a direita
                return auxBusca(no.getNoDir(),valor);
            }else{
                //Caso encontre
                if(valor.doubleValue() == no.getValor().doubleValue()){              
                    return no.getNoDir();
                }else{
                    return null;
                }
            }
        }else{
            //Verifica se o elemento está para a esquerda
            if(no.getValor().doubleValue() > valor.doubleValue()){
                //Verifica se o obejto da esquerda é nulo
                if(no.getNoEsq()!=null){
                    //Vai mais a direita
                    return auxBusca(no.getNoEsq(),valor);
                }else{
                    //Caso encontre
                    if(valor.doubleValue() == no.getValor().doubleValue()){
                        return no.getNoEsq();
                    }else{
                        return null;
                    }
                }
            }else{
                //Caso encontre
                if(valor.doubleValue() == no.getValor().doubleValue()){ 
                   return no;
                }else{
                   return null;
                } 
            }   
        }
    }
    
    public void remover(T valor){
    
    	if(valor != null){
            auxRemocao(raiz,valor);
        } else {
            System.out.println("Valor nulo, não pode ser removido na árvore.");
        }
    }
    
    private No<T> auxRemocao(No<T> no, T valor){
           
    	try{
	            if(valor.intValue() < no.getValor().intValue()){
	                no.setNoEsq(auxRemocao(no.getNoEsq(), valor));
	            } else if(valor.intValue() > no.getValor().intValue()){
	                no.setNoDir(auxRemocao(no.getNoDir(), valor));
	            } else if (no.getNoEsq() != null && no.getNoDir() != null) {
	                /*2 filhos*/  
	                System.out.println("Removeu No " + no.getValor());
	                no.setValor(encontraMinimo(no.getNoDir()).getValor());
	                no.setNoDir(removeMinimo(no.getNoDir()));
	            } else {  
	                System.out.println("Removeu No " + no.getValor());  
	                no = (no.getNoEsq() != null) ? no.getNoEsq() : no.getNoDir();  
	            }  
	            return no;
	            
           } catch (NullPointerException e){  
        	   System.out.println("Valor "+valor.toString()+" inexistente na árvore, impossivel de exclui-lo!!");
        	 return null;  
           }
       }
        
 
    
    private boolean existeElemento(No<T> no, T valor){
    	
        //Verifica se os valores são diferentes    
        if(no.getValor().doubleValue() != valor.doubleValue()){
            //Verifica se o objeto da direita é nulo
            if(no.getNoDir()!= null){
                //Vai mais a direita
            	existeElemento(no.getNoDir(),valor);
            }else{
                //Caso se igual retorna verdadeiro
                return true;
            }
        }else{
            //Verifica se os valores são diferentes    
            if(no.getValor().doubleValue() != valor.doubleValue()){
                //Verifica se o objeto da esquerda é nulo
                if(no.getNoEsq()!= null){
                    //Vai mais a direita
                    auxInsercao(no.getNoEsq(),valor);
                }else{
                    //Caso se igual retorna verdadeiro
                    return true;
                }
            }   
        }
        
        return false;
        
    }

    private No<T> removeMinimo(No<T> node) {  
        if (node == null) {  
            System.out.println("  ERRO ");  
        } else if (node.getNoEsq() != null) {  
            node.setNoEsq(removeMinimo(node.getNoEsq()));  
            return node;  
        } else {  
            return node.getNoDir();  
        }  
        return null;  
    }  
    
    private No<T> encontraMinimo(No<T> node) {  
        if (node != null) {  
            while (node.getNoEsq() != null) {  
                node = node.getNoEsq();  
            }  
        }  
        return node;  
    }
    
    public T enesimoElemento(int n){
        if(!(n==0 || raiz==null || n>this.Tamanho())){
            aux1=0;
            valor = null;
           return auxEnesimoElemento(raiz,n);
        }else{
			System.out.println("Verificar os seguintes casos: \n . Se a árvore está vazia; ou \n . Verificar se o valor especifico é menor que 1; ou \n . Valor inexistente na árvore");
            return null;
        }
    }

    private T auxEnesimoElemento(No<T> no,int n){
        //Caso chegue alem das folhas 
        if(no==null){
            return null;
        }else{
            //Pecorrendo a arvore de maneira simetrica
            auxEnesimoElemento(no.getNoEsq(), n); 
            //Incrementando +1 para cada valor passado na arvore
            aux1+=1;
            //Apenas quando achar o enesimo termo que iremos guardar esse valor na outra variavel
            if(aux1 == n){
                valor = no.getValor();
            }
            
            auxEnesimoElemento(no.getNoDir(),n);
        }
        //Retornando a variavel passado por paramentro
        return valor;        
    }
    
    public int posicao(T valor){
        if(valor!=null){
            if(buscar(valor)!=null){
                aux1=0;
                aux2=0;
                return auxPosicao(raiz, valor);
            }else{
                return -1;
            }
        }else{
            System.out.println( "Valor passado por parametro é nulo");
            return -1;
        }
    }

    private int auxPosicao(No<T> no, T valor){
        //Caso chegue alem das folhas
        if(no==null){
            return 0;
        }else{
            //Pecorrendo a arvore de maneira simetrica
            auxPosicao(no.getNoEsq(), valor); 
            //Incrementando +1 para cada valor passado na arvore
            aux1+=1;
            //Apenas quando achar o valor, e salvar nas outras variaveis
            if(no.getValor().equals(valor)){
                aux2 = aux1;
            }
            
            auxPosicao(no.getNoDir(),valor);
        }
        //Retornando a variavel passado por paramentro
        return aux2;
    }

    public T mediana(){
        if(raiz != null){
            int n = this.Tamanho()/2;
            if(this.Tamanho()%2==0){               
                return (T)(Number)(enesimoElemento(n).doubleValue() + enesimoElemento(n+1).doubleValue());
            }else{
                return enesimoElemento(n+1);
            }
        }else{
            System.out.println("A árvore está vazia");
            return (T)(Number)(-1);
        }
    }
    
    public boolean ehCheia(){
        //Verifica se a raiz está vazia
        if(raiz!=null){
            return auxCheia(raiz);
        }else{
            return false;
        }
    }

    private boolean auxCheia(No<T> no){
        //Verifica se o nó tem sub-arvore vazia
        if(no.getNoDir()==null || no.getNoEsq()==null){
            //Altura desse nó tem que ser igual a 1
            return auxAltura(no)==1;
        }else{
            //Verifica os valores a direita
            boolean direita = auxCheia(no.getNoDir());
            //Verifica os valores a esquerda
            boolean esquerda = auxCheia(no.getNoEsq());
            
            //Retorna verdadeiro apenas se todos os nós tiverem altura 1
            return direita && esquerda;
        }
    }

    public boolean ehCompleta(){
        if(raiz!=null){
            return Math.pow(2, this.altura()) - 1 == this.Tamanho();
        }else{
            return false;
        }
    }
   
    @Override
    public String toString(){
        if(raiz!=null){
            return auxImpressao(raiz);
        }else{
            return "Árvore vazia";
        }
    }
    
    private String auxImpressao(No<T> no){
        String fila = no.getValor()+ "|";
        String filaCerta = "";
        
        //Usando uma string como fila
        while(!fila.isEmpty()){
            //Buscando o nó de acordo com a primeira parte da string q representa um numero
            no = buscar((T)(Number)(new BigDecimal((fila.substring(0,fila.indexOf("|"))))));
            
            //Adicionando a parte inicial da string que representa a fila, na string final
            filaCerta += fila.substring(0,fila.indexOf('|'))+ " ";
            
            //Excluindo a parte incial da string(Comportamento analogo a fila)
            fila = fila.substring(fila.indexOf("|")+1, fila.length());
            //Verifica se os filhos sao nulos, caso nao adiciona os valores deles no fim da string(analago ao topo da fila)
            if(no.getNoEsq()!=null){fila = fila + no.getNoEsq().getValor().toString() + "|";}
            if(no.getNoDir()!=null){fila = fila + no.getNoDir().getValor().toString() + "|";}
        }
        //Retornando a string completa
        return filaCerta;
    }
    
    public int altura() {
        return auxAltura(raiz);
    }
    
    private int auxAltura(No<T> no) {
        //Numa arvore vazia a altura sera 0;
        if (no == null){ 
            return 0;
        }else {
            //Pega a altura da esquerda e direita
            int AlturaEsq = auxAltura(no.getNoEsq());
            int AlturaDir = auxAltura(no.getNoDir());
            
            //Compara e retorna para cima da recursao a maior mais 1;
            if (AlturaEsq < AlturaDir){ 
                return AlturaDir + 1;
            }else{ 
                return AlturaEsq + 1;
            }    
        }
    }
  
    public int Tamanho() {
        return auxTamanho(raiz);
    }
    
    private int auxTamanho(No<T> no){
        if(no==null){
            return 0;
        }else{
            //verifica o tamanho das subarvore e retorna somando para gerar o tamanho total
            return 1 + auxTamanho(no.getNoEsq()) + auxTamanho(no.getNoDir());
        }
    } 
}

