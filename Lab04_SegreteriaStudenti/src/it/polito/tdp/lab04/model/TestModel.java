package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		Corso c = new Corso();
		c.setCodiceCorso("01KSUPG");
		List<Studente> studentiIscrittiAlCorso01KSUPG = new ArrayList<>(model.getIscrittiCorsi(c));
		
		for(Studente s: studentiIscrittiAlCorso01KSUPG ) {
			//System.out.println(s.getMatricola()+" "+s.getCognome()+" "+s.getNome());
		}
		/*
		 * 	Write here your test model
		 */
		CorsoDAO corsoDao = new CorsoDAO();
		Studente stud = corsoDao.getStudenteDaMatricola(146101);
		
		//System.out.println(stud.getNome()+"  "+stud.cognome);
		
		int m = 148072;
		List<String> corsiStud = new ArrayList<>(model.getCorsiSudenteX(m));
		//System.out.println(corsiStud);
		
		
		
				
		if(model.iscriviStudente(146101, "Sistemi informativi aziendali")==true);
			System.out.println("fino a qui tutto bene");
	}

}
