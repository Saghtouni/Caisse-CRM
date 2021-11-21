package Fenetre;

import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import DataBase.ClientsDB;
import DataBase.FactureDB;
import DataBase.SalaireDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.Employer;
import objects.ItemFacture;
import objects.devis;
import objects.itemDevis;
import objects.Client;

public class SelectDevis extends AnchorPane implements Initializable{

    @FXML
    private JFXTextField textChercher;

    @FXML
    private TableView<devis> tabview;

    @FXML
    private TableColumn<Integer, devis> numero;

    @FXML
    private TableColumn<String, devis> Date;

    @FXML
    private TableColumn<Double, devis> Somme;

    @FXML
    private TableColumn<String, devis> Client;

    @FXML
    private JFXButton select;

    @FXML
    private JFXButton suprimer;
    
    private Connection DB = null;
    FactureController facturControl;
	private ObservableList<devis> dataD;
	private ObservableList<itemDevis> dataID;
	private ObservableList<ItemFacture> itmFact;
	FactureDB facture;
	ClientsDB clientDB;
	Client client = null;
    public SelectDevis(Connection db, FactureController facturControl) throws SQLException {
    	this.facturControl = facturControl;
    	this.DB = db;
    	facture = new FactureDB(DB);
    	clientDB = new ClientsDB(DB);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectDevis.fxml"));
    	fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
    	} catch (IOException exception) {
    		throw new RuntimeException(exception);
    	}

    }

    @FXML
    void ChercheEmployer(KeyEvent event) {

    }
   
    @FXML
    void selectClicked(ActionEvent event) throws SQLException {
    	
    	if(tabview.getSelectionModel().getSelectedItem() != null){
    		
    		dataID = facture.getItemDevis(tabview.getSelectionModel().getSelectedItem().getId());
    		
    		client = clientDB.getClientById(tabview.getSelectionModel().getSelectedItem().getClient());
    		facturControl.nomclientF.setText(client.getNom());
    		facturControl.adresseF.setText(client.getAdresse());
    		ItemFacture itemF;
    		ItemFacture itemF0 = new ItemFacture();
    		facturControl.tableproduitsF.getItems().clear();
    		for(int i = 0; i < dataID.size(); i++) {
    			if (dataID.get(i).getPrixUni() != 0) {
    				itemF = new ItemFacture(dataID.get(i).getNom(), dataID.get(i).getQuantite(), dataID.get(i).getPrixUni(), dataID.get(i).getPrixTot(),
    							dataID.get(i).getTva(),dataID.get(i).getTaille(), dataID.get(i).getColorS(), dataID.get(i).getUnite());
    				
    				Button button = new Button();
    				button.setText(dataID.get(i).getTaille()+"");
    				button.setStyle("-fx-background-color:"+dataID.get(i).getColorS()+"; -fx-text-fill: white;");
    				itemF.setColorBnt(button);
    				facturControl.tableproduitsF.getItems().add(itemF);
    				facturControl.ClearandInitA();
    			}
    			else {
    				itemF0.setNom(dataID.get(i).getNom());
    				itemF0.setColorS( dataID.get(i).getColorS());
    				itemF0.setTaille(dataID.get(i).getTaille());
    				Button button = new Button();
    				button.setText(dataID.get(i).getTaille()+"");
    				button.setStyle("-fx-background-color:"+dataID.get(i).getColorS()+"; -fx-text-fill: white;");
    				itemF0.setColorBnt(button);
    				facturControl.tableproduitsF.getItems().add(itemF0);
    				
    			}
    		}
    		
    		facturControl.totalnetF.setText(String.format("%.2f",tabview.getSelectionModel().getSelectedItem().getTotalnet()));
    		facturControl.tvaF.setText(String.format("%.2f",tabview.getSelectionModel().getSelectedItem().getTva()));
    		facturControl.totalbrutF.setText(String.format("%.2f",tabview.getSelectionModel().getSelectedItem().getTotalbrut()));
    		facturControl.reduction.setText(String.format("%.2f",facture.getReduction(tabview.getSelectionModel().getSelectedItem().getId())));
    		facturControl.idclient.setText(tabview.getSelectionModel().getSelectedItem().getClient()+"");  
    		Stage primaryStage = (Stage) select.getScene().getWindow();
    		primaryStage.close();
    		
    		}
    }

    @FXML
    void suprimerClicked(ActionEvent event) {

    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	Alert alert1 = new Alert(AlertType.ERROR);
    	alert.setTitle("Boîte de dialogue de confirmation");
    	alert.setHeaderText("Regardez, une boîte de dialogue de confirmation");
    	alert.setContentText("Voulez vous supprimer ce employé ?");
    	Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    	stage.getIcons().add(new Image("/img/its_icoTR.png"));
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		if(tabview.getSelectionModel().getSelectedItem() != null) {
    			facture.deleteDevis(tabview.getSelectionModel().getSelectedItem().getId());
    			tabview.getItems().clear();
    			tabview.setItems(devis());
        	}
        	else {
    			alert1.setTitle("Suprimer employé");
    			alert1.setHeaderText("Résultat");
    			alert1.setContentText("il faut sélectionner un employé !");
    			Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
    			stage1.getIcons().add(new Image("/img/its_icoTR.png"));
    			alert1.showAndWait();
        	}
    	}
    	else {
    		alert1.close();
    	}
    }
    public ObservableList<devis> devis() {
    	dataD = facture.getDevis();
    	numero.setCellValueFactory(new PropertyValueFactory("id"));
    	Date.setCellValueFactory(new PropertyValueFactory("date"));
    	Somme.setCellValueFactory(new PropertyValueFactory("totalbrut"));
    	Client.setCellValueFactory(new PropertyValueFactory("remarque"));
		return dataD;
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
			/*for(int i = 0; i < dataD.size(); i++) {
				System.out.println("testt" + dataD.get(i).getRemarque());
			}*/
			tabview.setItems(devis());
	
	}
	
	

}
