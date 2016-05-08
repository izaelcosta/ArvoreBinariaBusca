
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import implementacao.ABB;
import interpretacao.Interpretador;

public class Main {
	
	public static void main (String[] args){
		
		ABB<Integer> arvore = new ABB<Integer>();
		JFileChooser arquivo1 = new JFileChooser();
		JFileChooser arquivo2 = new JFileChooser();
		String[] arquivoCaminho = new String[2];
		
		JOptionPane.showMessageDialog(null, "Selecione o arquivo contendo a lista de inteiros");
		arquivo1.showOpenDialog(null);
		arquivoCaminho[0] = arquivo1.getSelectedFile().getPath().toString();
		System.out.println("Caminho da lista de inteiros: "+arquivoCaminho[0]);
		
		JOptionPane.showMessageDialog(null, "Selecione o arquivo contendo as instruções");
		arquivo2.showOpenDialog(null);
		arquivoCaminho[1] = arquivo2.getSelectedFile().getPath().toString();
		System.out.println("Caminho das instruções: "+arquivoCaminho[1]);
		
		JOptionPane.showMessageDialog(null, "Informações apresentadas no console!");

		System.out.println("Informações: ");
		Interpretador inter = new Interpretador(arquivoCaminho); 
		inter.aplicarInstrucoes(arvore);
		
	}

}
