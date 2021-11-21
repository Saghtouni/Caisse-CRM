package Fenetre;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import DataBase.SalaireDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.Employer;

public class EnregistrerEmployer extends AnchorPane implements Initializable {
	
	private Connection DB = null;
	private SalaireDB salaire = null;
	ObservableList<Employer> employer ;
	private EmployerControl employerCont;
	
	
    @FXML
    private JFXComboBox<String> sex;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField classeSalair;

    @FXML
    private JFXTextField profession;

    @FXML
    private JFXTextField baseSalaire;

    @FXML
    private JFXTextField taux;

    @FXML
    private JFXTextArea adresse;

    @FXML
    private JFXDatePicker entre;

  
    
    @FXML
    private JFXTextField iban;
    
    @FXML
    private JFXTextField banqueAdres;

    @FXML
    private JFXButton bntEnregistrer;

    @FXML
    private Label Notification;
    
    @FXML
    private JFXTextField NumeroEmployer;
    
    public EnregistrerEmployer(Connection db,  EmployerControl employerCont) throws SQLException {

    	this.DB = db;
    	salaire = new SalaireDB(DB);
    	this.employerCont = employerCont;
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EnregistrerEmployer.fxml"));
    	fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
    	} catch (IOException exception) {
    		throw new RuntimeException(exception);
    	}

    }
    

    @FXML
    void bntEnregistrerClicked(ActionEvent event) {
    	
    	int x = 0;
    	try
    	{
    	  Double.parseDouble(baseSalaire.getText());
    	  Double.parseDouble(taux.getText());
    	  x = 1;
    	}
    	catch(NumberFormatException e)
    	{
    		x = 0;
    		Notification.setText("il faut inserer des chiffres dans Salaire de base et Taux !");
    		
    	}
    	
    	if(x == 1) {
    		
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String DateEnt = entre.getValue().format(formatter);
			
			salaire.addEmployer(sex.getSelectionModel().getSelectedItem().toString(), nom.getText(), prenom.getText(), adresse.getText(),
			NumeroEmployer.getText(), classeSalair.getText(), profession.getText(),Double.parseDouble(baseSalaire.getText()),
    		Double.parseDouble(taux.getText()), DateEnt, iban.getText(), banqueAdres.getText());
			Stage primaryStage = (Stage) bntEnregistrer.getScene().getWindow();
			employerCont.refrech();
			primaryStage.close();	 
    	}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		employer = salaire.loadEmployer();
		Notification.setText("il faut inserer tous les champs !");
		sex.getItems().add("Monsieur");
		sex.getItems().add("Madame");
		sex.getSelectionModel().select(0);
		
	}

}
