package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	Model model = new Model();
	CorsoDAO corsoDao = new CorsoDAO();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbCorsi"
    private ComboBox<String> cmbCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorsi"
    private Button btnCercaIscrittiCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnCompletamento"
    private Button btnCompletamento; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscriviti"
    private Button btnIscriviti; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void doCercaCorsi(ActionEvent event) {
    		try {
	    		List<String> corsiStudente = model.getCorsiSudenteX(Integer.parseInt(txtMatricola.getText()));
	    		txtResult.appendText(corsiStudente.toString()+"\n");
	    		if(corsiStudente.isEmpty()) {
	    			throw new Exception();
	    		}
    		}
    		catch(NumberFormatException nfe) {
    			nfe.printStackTrace();
    			this.txtResult.appendText("Matricola non valida. Inserire una matricola numerica.\n");
    			return;
    		}
    		catch(Exception e) {
    			e.printStackTrace();
    			this.txtResult.appendText("Studente non iscritto a nessun corso\n");
    			return;
    		}
    		
    }

    @FXML
    void doCercaIscrittiCorsi(ActionEvent event) {
    		try {
	    		String nomeCorsoSelezionato = cmbCorsi.getValue();
	    		if(nomeCorsoSelezionato==null) {
	    			throw new Exception();
	    		}
	    		Corso corsoSelezionato = corsoDao.getCorso(nomeCorsoSelezionato);
	    		List<Studente> iscrittiCorsi = model.getIscrittiCorsi(corsoSelezionato);
	    		txtResult.appendText(iscrittiCorsi.toString());
    		}catch(Exception e) {
    			e.printStackTrace();
    			this.txtResult.appendText("Nessun corso selezionato\n");
    			return;
    		}
    }

    @FXML
    void doCompletamentoAutomatico(ActionEvent event) {
    		try {
		    		int matricolaS = Integer.parseInt(txtMatricola.getText());
		    		Studente s = corsoDao.getStudenteDaMatricola(matricolaS);
		    		if(s==null) {
		    			throw new Exception();
		    		}
		    		txtNome.appendText(s.getNome());
		    		txtCognome.appendText(s.getCognome());
	    		}
	    		catch(NumberFormatException nfe) {
	    			nfe.printStackTrace();
	    			this.txtResult.appendText("Matricola non valida. Inserire una matricola numerica.\n");
	    			return;
	    		}
	    		catch(Exception e) {
	    			e.printStackTrace();
	    			this.txtResult.appendText("Studente inesistente per la matricola fornita.\n");
	    			return;
	    		}
    	}

    @FXML
    void doIscriviti(ActionEvent event) {
    		
    		try {
	    		int matricola = Integer.parseInt(txtMatricola.getText());
	    		String nomeCorso = cmbCorsi.getValue();
	    		if(nomeCorso==null) {
	    			throw new Exception();
	    		}
	    		
	    		if(model.getStudenteIscritto(matricola, nomeCorso) == true) {
	    			txtResult.appendText("Studente già iscritto al corso\n");
	    		}
	    		
	    		else {
	    			txtResult.appendText("Studente da iscrivere\n");
	    			if(model.iscriviStudente(matricola, nomeCorso) == true)
	    				txtResult.appendText("Studente iscritto!!\n");
	    		}
    		}
    		catch(NumberFormatException nfe){
    			nfe.printStackTrace();
    			this.txtResult.appendText("Matricola non valida. Inserire una matricola numerica.\n");
    			return;
    		}
    		catch(Exception e) {
    			e.printStackTrace();
    			this.txtResult.appendText("Corso non selezionato\n");
    			return;
    		}
    		
    }

    @FXML
    void doReset(ActionEvent event) {
    		txtNome.clear();
    		txtCognome.clear();
    		txtMatricola.clear();
    		txtResult.clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorsi != null : "fx:id=\"btnCercaIscrittiCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCompletamento != null : "fx:id=\"btnCompletamento\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscriviti != null : "fx:id=\"btnIscriviti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
		List<String> corsoCmb = model.getCorsiDisponibili();
		this.cmbCorsi.getItems().setAll(corsoCmb);		
	}
    
    
}
