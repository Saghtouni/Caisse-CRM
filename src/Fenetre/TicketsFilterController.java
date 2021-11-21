package Fenetre;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import DataBase.VenteDB;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import objects.Analyse;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TableColumn;

public class TicketsFilterController  extends AnchorPane implements Initializable{
	@FXML
	private TableView<Analyse> tableticketsFiltered;
	@FXML
	private TableColumn<Analyse, String> datecol;
	@FXML
	private TableColumn<Analyse, Double> sommecol;
	@FXML
	private TableColumn<Analyse, Button> detailscol;
	@FXML
	private TableColumn<Analyse, Button> actionscol;
	@FXML
	private DatePicker datedebut;
	@FXML
	private DatePicker datefin;
	@FXML
	private Button btnRechercher;
	@FXML
	private Label dateD;
	@FXML
	private Label dateF;
	
	private Connection DB = null;
	VenteDB ventedb = null;
	private LoginControl logincontrol = null;
    DateTimeFormatter fOut = DateTimeFormatter.ofPattern( "dd-MM-yyyy" , Locale.UK );
    
	public  TicketsFilterController(Connection db) {
		this.DB=db;
		ventedb = new VenteDB(DB);
		try {
			logincontrol = new LoginControl(db);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 private void buidlTicketsTable() 
	     {

		 tableticketsFiltered.setItems(ventedb.displayAllTickets());
		 
	    }
	@FXML
	public void btnRechercherClicked(ActionEvent event) {
        LocalDate datedeb=datedebut.getValue();
        LocalDate datef=datefin.getValue();
        String output1 = datedeb.format( fOut );
        String output2 = datef.format( fOut );
        //System.out.println("Deb "+output1+" fin "+output2);
   	 tableticketsFiltered.setItems(ventedb.displayAllTicketsFiltered(output1,output2, logincontrol.getName(), logincontrol.getLastName()));


	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		tableticketsFiltered.getColumns().clear();
		datecol.setCellValueFactory(new PropertyValueFactory("date_heure"));
	   sommecol.setCellValueFactory(new PropertyValueFactory("somme"));
	   detailscol.setCellValueFactory(new PropertyValueFactory("produit"));    	
	   actionscol.setCellValueFactory(new PropertyValueFactory("status"));
	   tableticketsFiltered.getColumns().addAll(datecol,sommecol,detailscol,actionscol);      
		datedebut.setEditable(true); 
		datefin.setEditable(true); 
		LocalDate nowd =LocalDateTime.now().toLocalDate();
		Date date = java.sql.Date.valueOf(nowd);
		//System.out.println(date);
		datedebut.setValue(nowd);
		datefin.setValue(nowd);
       LocalDate datedeb=datedebut.getValue();
       LocalDate datef=datefin.getValue();
       String output1 = datedeb.format( fOut );
       String output2 = datef.format( fOut );
       //System.out.println("Deb "+output1+" fin "+output2);
       tableticketsFiltered.setItems(ventedb.displayAllTicketsFiltered(output1,output2, logincontrol.getName(), logincontrol.getLastName()));
	
		
	}
	

}

