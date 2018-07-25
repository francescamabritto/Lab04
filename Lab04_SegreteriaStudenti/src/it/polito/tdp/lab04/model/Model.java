/**
 * Prendo id dello studente, se esiste nell mappa uso quello esistente. 
 * Se non esiste creo una nuova coppia ID - Studente e lo aggiungo nella mappa
 * 
 * Creo una classe IdentityMap
 * 
 * class TableNameIdMap {
       private Map<Key, TableName> map ; }
 * 
 * Se è possibile conviene usare la mappa prima di creare la query
 * 
 * */


package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model {

	public Model() {
		
		
	}
	
	CorsoDAO corsoDao = new CorsoDAO();

	public List<String> getCorsiDisponibili() {
		
		List<String> corsiDisponibili = new ArrayList<>();
		
		for(Corso c: corsoDao.getTuttiICorsi()) {
			corsiDisponibili.add(c.nomeCorso);
		}
		return corsiDisponibili;
	}


	public List<Studente> getIscrittiCorsi(Corso corso) {	
		List<Studente> iscritti = new ArrayList<>();
		iscritti = corsoDao.getStudentiIscrittiAlCorso(corso);	
		return iscritti;
	}


	public List<String> getCorsiSudenteX(int matricola) {
		List<String> corsi = new ArrayList<>();
		corsi = corsoDao.getCorsiDelloStudente(matricola);
		return corsi;
	}


	public boolean getStudenteIscritto(int matricola, String nomeCorso) {
		boolean studIscritto = false;	
		for(String c: corsoDao.getCorsiDelloStudente(matricola)) {
			if(c.equalsIgnoreCase(nomeCorso)){
				studIscritto = true;
			}
		}
		return studIscritto;
		
	}


	public boolean iscriviStudente(int matricola, String nomeCorso) {
		
		
		Corso corso = corsoDao.getCorso(nomeCorso);
		
		/*
		for(Corso c: corsoDao.getTuttiICorsi()) {
			if(nomeCorso.compareTo(c.getNomeCorso())==0) {
				corso = c;
			}
		}*/
		
		if (corso!=null && (corsoDao.iscriviStudenteACorso(matricola, corso.getCodiceCorso())==true)) {
			return true;
		}
		
		return false;
	}
	
	

}
