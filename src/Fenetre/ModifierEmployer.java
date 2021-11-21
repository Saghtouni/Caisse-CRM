package Fenetre;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import DataBase.ReglageDB;
import DataBase.SalaireDB;
import DataBase.VenteDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.Analyse;

public class ModifierEmployer extends AnchorPane {
	
	private Connection DB = null;
	private SalaireDB salaire = null;
	private int id;
	private EmployerControl employer;
	@FXML
    public JFXTextField nom;

    @FXML
    public JFXTextField prenom;

    @FXML
    public JFXTextField classeSalaire;

    @FXML
    public JFXTextField profession;

    @FXML
    public JFXTextField salaireBase;

    @FXML
    public JFXTextField taux;

    @FXML
    public JFXTextArea adresse;

    @FXML
    public JFXDatePicker entre;



    @FXML
    public JFXTextField iban;

    @FXML
    public JFXTextArea banqueAdres;
    
    @FXML
    public JFXButton bntModifier;

    @FXML
    public Label notification;
    
    @FXML
    public JFXTextField numeroEmployer;
    
    public ModifierEmployer(Connection db, int id, EmployerControl employer) throws SQLException {
    	this.employer = employer;
    	this.id = id;
    	this.DB = db;
    	salaire = new SalaireDB(this.DB);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifierEmployer.fxml"));
    	fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
    	} catch (IOException exception) {
    		throw new RuntimeException(exception);
    	}

    }

    @FXML
    void bntModifierClicked(ActionEvent event) {
    	
    	int x = 0;
    	try
    	{
    	  Double.parseDouble(salaireBase.getText());
    	  Double.parseDouble(taux.getText());
    	  x = 1;
    	}
    	catch(NumberFormatException e)
    	{
    		x = 0;
    		notification.setText("il faut inserer des chiffres dans Salaire de base et Taux !");
    		
    	}
    	
    	if(x == 1) {
    		
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String DateEnt = entre.getValue().format(formatter);
			
			
			salaire.updatEmployer(nom.getText(), prenom.getText(), adresse.getText(), numeroEmployer.getText(),
    		classeSalaire.getText(), profession.getText(),Double.parseDouble(salaireBase.getText()),
    		Double.parseDouble(taux.getText()), DateEnt, iban.getText(), banqueAdres.getText(), id);
			Stage primaryStage = (Stage) bntModifier.getScene().getWindow();
			employer.tabview.getItems().clear();
			employer.tabview.setItems(salaire.loadEmployer());
			primaryStage.close();	 
    	}

    }
}
