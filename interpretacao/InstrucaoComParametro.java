/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpretacao;


/**
 *
 * @author Neto
 */
public class InstrucaoComParametro extends InstrucaoSemParametro {
    private final Integer parametro;

    public InstrucaoComParametro(String nNome, int nParametro) {
        super(nNome);
        parametro = nParametro;
    }

    public Integer getParametro() {
        return parametro;
    }
    
}
