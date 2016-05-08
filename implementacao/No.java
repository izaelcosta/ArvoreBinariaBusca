package implementacao;

public class No<T extends Number> {
    
    private No<T> noEsq;
    private T valor;
    private No<T> noDir;

    public No(T nValor) {
        this.valor = nValor;
        this.noDir = null;
        this.noEsq = null;
    }
    
    public No<T> getNoEsq() {
        return noEsq;
    }
    
    public T getValor() {
        return valor;
    }
    
    public No<T> getNoDir() {
        return noDir;
    }
        
    public void setNoEsq(No<T> nNoEsq) {
        this.noEsq = nNoEsq;
    }
    
    public void setValor(T nValor) {
        this.valor = nValor;
    }
    
    public void setNoDir(No<T> nNoDir) {
        this.noDir = nNoDir;
    }
    
}
