package Fenetre;

import java.awt.Button;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import DataBase.VenteDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import objects.Analyse;
import objects.Vente;
import objects.devis;

public class DevisControl  extends AnchorPane implements Initializable {
	@FXML
	public TableView<Analyse> tabletickets;
	@FXML
	private TableColumn<Analyse, String> datecol;
	@FXML
	private TableColumn<Analyse, Double> sommecol;
	@FXML
	private TableColumn<Analyse, Button> detailscol;
	@FXML
	private TableColumn<Analyse, String> actionscol;
	@FXML
	private TableColumn<Analyse, String> statuscol;
	private Connection DB = null;

	VenteDB ventedb = null;
	FactureController facture = null;
	ObservableList<Analyse> item = FXCollections.observableArrayList();
	public TableView<devis> tableCaisseT = new TableView<devis>();
	public TextField aff ;
	public TextField aff2 = new TextField() ;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	  tabletickets.getColumns().clear();
	  datecol.setCellValueFactory(new PropertyValueFactory("date_heure"));
      sommecol.setCellValueFactory(new PropertyValueFactory("somme"));
      detailscol.setCellValueFactory(new PropertyValueFactory("produit"));    	
      statuscol.setCellValueFactory(new PropertyValueFactory("status"));
      actionscol.setCellValueFactory(new PropertyValueFactory("actions"));
      tabletickets.getColumns().addAll(datecol,sommecol,detailscol,statuscol,actionscol);      
   
      
      buidlTicketsTable();
 
	}

public  DevisControl( Connection db, FactureController facture) {
	
	
	this.DB=db;
	this.facture=facture;
	ventedb = new VenteDB(DB);
	//this.tableCaisseT=caisse.tableCaisse;
	//this.aff=caisse.AfficcheurSomme;

}

 private void buidlTicketsTable() 
     {
	 /*ObservableList<Analyse> ls = ventedb.displayAllTickets();
	 System.out.println(ventedb.displayAllTickets().size());
     for (Analyse v : ls)
     {
 		item.add(new Analyse(v.getDate_heure(),v.getSomme(),v.getProduit(),v.getStatus()));
     }
     tabletickets.setItems(item);
     
	 System.out.println(tabletickets.getItems().size());*/
	 //tabletickets.setItems(ventedb.displayAllTicketsAttente(caisse,tabletickets));
	 
	 //System.out.println("test");
	 
    }
}
