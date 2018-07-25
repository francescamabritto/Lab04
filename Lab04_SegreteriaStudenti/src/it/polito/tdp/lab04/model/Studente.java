package it.polito.tdp.lab04.model;

public class Studente {
	int matricola;
	String cognome;
	String nome;
	String cds;
	
	
	public Studente(int matricola2) {
		this.matricola = matricola2;
	}
	
	
	public Studente(int matricola, String cognome, String nome, String cds) {
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
		this.cds = cds;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public int getMatricola() {
		return matricola;
	}
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}
	public String getCds() {
		return cds;
	}
	public void setCds(String cds) {
		this.cds = cds;
	}


	@Override
	public String toString() {
		return nome + " " + cognome + " " + matricola + "\n";
	}
	
	
	
	
}
