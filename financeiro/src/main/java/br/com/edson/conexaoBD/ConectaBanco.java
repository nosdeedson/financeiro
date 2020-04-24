package br.com.edson.conexaoBD;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class ConectaBanco {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			EntityManagerFactory emf = 
					Persistence.createEntityManagerFactory("financeiroPU");
			emf.close();
			JOptionPane.showMessageDialog(null, "ok");
			
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "falhou");
		}
	}

}
