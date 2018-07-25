package it.polito.tdp.lab04.model;

public class Corso {
	String nomeCorso;
	String codiceCorso;
	int crediti;
	int pd;
	
	public Corso() {
		this.codiceCorso = codiceCorso;
	}
	
	
	public Corso(String codiceCorso, int crediti, String nomeCorso, int pd) {
		this.codiceCorso = codiceCorso;
		this.crediti = crediti;
		this.nomeCorso = nomeCorso;
		this.pd = pd;
	}
	
	public String getNomeCorso() {
		return nomeCorso;
	}
	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}
	public String getCodiceCorso() {
		return codiceCorso;
	}
	public void setCodiceCorso(String codiceCorso) {
		this.codiceCorso = codiceCorso;
	}

	public int getCrediti() {
		return crediti;
	}

	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}

	public int getPd() {
		return pd;
	}

	public void setPd(int pd) {
		this.pd = pd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceCorso == null) ? 0 : codiceCorso.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codiceCorso == null) {
			if (other.codiceCorso != null)
				return false;
		} else if (!codiceCorso.equals(other.codiceCorso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeCorso + "   " + codiceCorso + "   " + crediti + "  " + pd;
	}
	
	
	
	
}
