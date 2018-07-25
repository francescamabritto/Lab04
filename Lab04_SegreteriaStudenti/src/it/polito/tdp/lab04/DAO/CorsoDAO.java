package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso corsoDaAggiungere = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(corsoDaAggiungere);
				
			}
			conn.close();
			return corsi;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String nomeCorso) {
		List<Corso> corsi = new ArrayList<>(this.getTuttiICorsi());
		
		for(Corso c: corsi) {
			if(c.getNomeCorso().compareTo(nomeCorso)==0) {
				//System.out.println(c);
				return c;
			}
		}
		return null;
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		List<Studente>iscritti = new ArrayList<>();
		
				final String sql = "SELECT i.matricola, cognome, nome, CDS FROM iscrizione as i, studente as s WHERE i.matricola = s.matricola AND i.codins = ?";
				
				try {
					Connection conn = ConnectDB.getConnection();
					PreparedStatement st = conn.prepareStatement(sql);
					
					st.setString(1, corso.getCodiceCorso());
					ResultSet rs = st.executeQuery();

					while (rs.next()) {

						int matricola = rs.getInt("matricola");
						String cognome = rs.getString("cognome");
						String nome = rs.getString("nome");
						String cds = rs.getString("CDS");
						//System.out.println(matricola);

						Studente studenteDaAggiungere = new Studente(matricola, cognome, nome, cds);
						
						// Aggiungi il nuovo oggetto Corso alla lista corsi
						iscritti.add(studenteDaAggiungere);
					}
				conn.close();
				return iscritti;

				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException("Errore Db");
				}
		
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(int matricola, String codiceCorso) {
		final String sql = "INSERT INTO iscritticorsi.iscrizione(matricola, codins) VALUES (?, ?)";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			st.setString(2, codiceCorso);
			
			int numeroInserite = st.executeUpdate();
			
			System.out.println("Ho inserito "+numeroInserite+" righe");
			
			conn.close();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}

	public Studente getStudenteDaMatricola(int matricolaS) {
		
		final String sql = "SELECT cognome, nome, cds FROM studente WHERE matricola = ?";
		Studente res = null; 
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricolaS);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {

				//int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");
				
				  res = new Studente(matricolaS, cognome, nome, cds);
			
			}
			
			conn.close();
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<String> getCorsiDelloStudente(int matricola) {
		
		List <String> nomiCorsi = new ArrayList<>();
		final String sql = "SELECT nome FROM corso as c, iscrizione as i WHERE matricola = ? AND c.codins = i.codins";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {

				//int matricola = rs.getInt("matricola");
				String nome = rs.getString("nome");
				
				Corso c = new Corso();
				c.setNomeCorso(nome);
				nomiCorsi.add(c.getNomeCorso());
			}
			
			conn.close();
			return nomiCorsi;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	
}
