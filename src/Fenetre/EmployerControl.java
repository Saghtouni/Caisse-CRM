package Fenetre;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import DataBase.SalaireDB;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import objects.Employer;
import objects.Utilisateurs;

public class EmployerControl extends AnchorPane implements Initializable {

		private Connection DB = null;
		private SalaireDB salaireDB = null;
		ObservableList<Employer> employer ;
		private Employer Employer;
		private Salairecontrol salaireCont;
		public static int idEm ;
		public String datenow;
		
	 	@FXML
	    private JFXTextField textChercher;

	    @FXML
	    public TableView<Employer> tabview;

	    @FXML
	    private TableColumn<Employer, String> nomPrenom;

	    @FXML
	    private TableColumn<Employer, String> dateEntrer;

	    @FXML
	    private TableColumn<Employer, Long> Nopersonnel;

	    @FXML
	    private JFXButton select;

	    @FXML
	    private JFXButton ajouter;

	    @FXML
	    private JFXButton modifier;
	   
	    @FXML
	    private JFXButton suprimer;
	    
	    @FXML
	    private JFXButton historque;
	    
	    
	    public EmployerControl(Connection db, Salairecontrol salaireCont) throws SQLException {

	    	this.DB = db;
	    	salaireDB = new SalaireDB(DB);
	    	this.salaireCont = salaireCont;
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Employer.fxml"));
	    	fxmlLoader.setRoot(this);
	    	fxmlLoader.setController(this);

	    	try {
	    		fxmlLoader.load();
	    	} catch (IOException exception) {
	    		throw new RuntimeException(exception);
	    	}

	    }
	    

	    @FXML
	    void ajouterClicked(ActionEvent event) throws SQLException {
	    	
	    	 EnregistrerEmployer EnreEmployer = new  EnregistrerEmployer(DB, this);
	    	 Stage primaryStage = new Stage();
	    	 primaryStage.setScene(new Scene(EnreEmployer));
	         primaryStage.getIcons().add(new Image("/img/its_icoTR.png"));
	         primaryStage.setResizable(false);
	         primaryStage.setTitle("Ajouter Employer");
	         primaryStage.show();
	    }

	    @FXML
	    void modifierClicked(ActionEvent event) throws SQLException {
	    	
	    	if(tabview.getSelectionModel().getSelectedItem() != null){
	    		int id =tabview.getSelectionModel().getSelectedItem().getId();
	    		System.out.println(id);
	    		Employer = salaireDB.get(id);
	    		ModifierEmployer ModEmployer = new  ModifierEmployer(DB, id, this);
	    		
	    		ModEmployer.nom.setText(Employer.getName());
	    		ModEmployer.prenom.setText(Employer.getLastname());
	    		ModEmployer.numeroEmployer.setText(Employer.getNopersonnel());
	    		ModEmployer.classeSalaire.setText(Employer.getClasse());
	    		ModEmployer.profession.setText(Employer.getProfession());
	    		ModEmployer.salaireBase.setText(Employer.getBase_salaire()+"");
	    		ModEmployer.taux.setText(Employer.getTaux()+"");
	    		ModEmployer.adresse.setText(Employer.getAdresse());
	    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    		LocalDate dateEntrer = LocalDate.parse(Employer.getEntredate(), formatter);
	    		ModEmployer.entre.setValue(dateEntrer);
	    		ModEmployer.iban.setText(Employer.getIban());
	    		ModEmployer.banqueAdres.setText(Employer.getBanque());
	    		
	    		Stage primaryStage = new Stage();
	    		primaryStage.setScene(new Scene(ModEmployer));
	    		primaryStage.getIcons().add(new Image("/img/its_icoTR.png"));
	    		primaryStage.setResizable(false);
	    		primaryStage.setTitle("Modifier Employer");
	    		primaryStage.show();
	         
	    	}
	    }

	    @FXML
	    void selectClicked(ActionEvent event) {
	    	if(tabview.getSelectionModel().getSelectedItem() != null){
	    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    		LocalDate dateJour = LocalDate.parse(LocalDateTime.now().format(formatter).toString(), formatter);
	    		salaireCont.datePeriode.setValue(dateJour);
	    		salaireCont.numPersonal.setText(tabview.getSelectionModel().getSelectedItem().getNopersonnel()+"");
	    		salaireCont.classeSalaire.getItems().add(tabview.getSelectionModel().getSelectedItem().getClasse());
	    		salaireCont.classeSalaire.getSelectionModel().select(0);
	    		salaireCont.profession.setText(tabview.getSelectionModel().getSelectedItem().getProfession());
	    		salaireCont.baseSalaire.setText(tabview.getSelectionModel().getSelectedItem().getBase_salaire()+"");
	    		salaireCont.tauxOccup.setText(tabview.getSelectionModel().getSelectedItem().getTaux()+" %");
	    		salaireCont.entree.setText(tabview.getSelectionModel().getSelectedItem().getEntredate());
	    		salaireCont.sex.setText(tabview.getSelectionModel().getSelectedItem().getSex());
	    		salaireCont.nomPrenom.setText(tabview.getSelectionModel().getSelectedItem().getLastname() + " " + tabview.getSelectionModel().getSelectedItem().getName());
	    		salaireCont.adresse.setText(tabview.getSelectionModel().getSelectedItem().getAdresse());
	    		salaireCont.edition.setText(LocalDateTime.now().format(formatter).toString());
	    		salaireCont.textBanqueDate.setText("IBAN : "+  tabview.getSelectionModel().getSelectedItem().getBanque().replace("\n", ", "));
	    		salaireCont.tauxSalaire.setText(tabview.getSelectionModel().getSelectedItem().getBase_salaire()+"");
	    		salaireCont.tauxJFer.setText(tabview.getSelectionModel().getSelectedItem().getBase_salaire()+"");
	    		salaireCont.idEm = tabview.getSelectionModel().getSelectedItem().getId();
	    		System.out.println(salaireCont.idEm);
	    		Stage primaryStage = (Stage) select.getScene().getWindow();
				primaryStage.close();	
	    		
	    	}
	    	else {
	    		Alert alert1 = new Alert(AlertType.ERROR);
	    		alert1.setTitle("selectionner employé");
    			alert1.setHeaderText("Résultat");
    			alert1.setContentText("il faut sélectionner un employé !");
    			Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
    			stage1.getIcons().add(new Image("/img/its_icoTR.png"));
    			alert1.showAndWait();
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
	    			salaireDB.delete(tabview.getSelectionModel().getSelectedItem().getId());
	        		refrech();
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

	    @FXML
	    void ChercheEmployer(KeyEvent event) throws SQLException {
	    	if(textChercher.getText().toLowerCase().equals(""))
			{
				this.tabview.getItems().clear();
				this.tabview.setItems(salaireDB.loadEmployer());
				
			}
			else
			{
				
				String s = textChercher.getText().toLowerCase();
				this.tabview.getItems().clear();
		    	this.tabview.setItems(salaireDB.searchUEmpl(s));
		    	
			}
			
			
	    }
	    
	    @FXML
	    void historqueClicked(ActionEvent event) throws SQLException {
	    	
	    	if(tabview.getSelectionModel().getSelectedItem() != null){
	    		int id =tabview.getSelectionModel().getSelectedItem().getId();
	    		System.out.println(id);
	    		Stage primaryStage = new Stage();
	    		HistoriqueSalaireControl histEmployer =  new HistoriqueSalaireControl(this.DB, id);
	    		primaryStage.setScene(new Scene(histEmployer));
	    		primaryStage.getIcons().add(new Image("/img/its_icoTR.png"));
	    		primaryStage.setResizable(false);
	    		primaryStage.setTitle("Modifier Employer");
	    		primaryStage.show();
	    	}

	    }
	    public void refrech() {
	    	this.tabview.getItems().clear();
	    	this.tabview.setItems(employer());
	    }

	    
	    public ObservableList<Employer> employer(){
	    	employer = salaireDB.loadEmployer();
			nomPrenom.setCellValueFactory(new PropertyValueFactory("name"));
			dateEntrer.setCellValueFactory(new PropertyValueFactory("entredate"));
			Nopersonnel.setCellValueFactory(new PropertyValueFactory("nopersonnel"));
			return employer;
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
		
			
			tabview.setItems(employer());
			
			
		}
}
