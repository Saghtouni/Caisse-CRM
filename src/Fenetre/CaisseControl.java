package Fenetre;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import DataBase.ProduitsDB;
import DataBase.ReglageDB;
import DataBase.UtilisateursDB;
import DataBase.VenteDB;
import application.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import objects.Analyse;
import objects.ItemFacture;
import objects.Produits;
import objects.Reglage;
import objects.Utilisateurs;
import objects.Vente;

public class CaisseControl extends BorderPane implements Initializable {

	private Connection DB = null;
	public ProduitsDB produitsdb = null;
	private VenteDB ventedb = null;
	private static int cnt;
	private Produits produit;
	private Produits produitTable;
	private ObservableList<Produits> produits = FXCollections.observableArrayList();
	private ObservableList<String> type = FXCollections.observableArrayList();
	private ObservableList<String> codeBare = FXCollections.observableArrayList();
	private ObservableList<Button> butType = FXCollections.observableArrayList();
	private ObservableList<String> nomProduit = FXCollections.observableArrayList();

	ObservableList<Vente> item = FXCollections.observableArrayList();

	static ObservableList<Vente> item1 = FXCollections.observableArrayList();
	static ObservableList<Double> tva = FXCollections.observableArrayList();
	private final StringBuffer buffer = new StringBuffer();
	
	private int nbTypeC;
	private int nbTypeL;

	private double BUTTON_PADDING = 5;
	private int nbProduitTypeC;
	private int nbProduitTypeL;
	public static float prixFinal;
	public static Double somme;
	
	private LoginControl loginControl;
	public ImageView ImageUser;
	public static String nameUser;
	public static String prenomUser;
	public static String dateHeure;
	public static int nbr = 0;

	public static float getPrixFinal() {
		return prixFinal;
	}

	@FXML
	public GridPane gridPane4;

	@FXML
	public GridPane gridPane3;

	@FXML
	BorderPane borderPane;

	@FXML
	public Label NameUser;

	@FXML
	public Label FirstNameUser;

	@FXML
	public Label timeNow;

	@FXML
	public ImageView ImageLogoText;

	@FXML
	public Button switchButton;

	@FXML
	private Button checkButton;

	@FXML
	private Button xButton;

	@FXML
	private Button zButton;

	@FXML
	public Button butdeux;

	@FXML
	public Button butSix;

	@FXML
	public Button buthuit;

	@FXML
	public Button butZero;

	@FXML
	public Button butcinq;

	@FXML
	public Button butPoint;

	@FXML
	public Button butCL;

	@FXML
	public Button butQuatre;

	@FXML
	public Button butNeuf;

	@FXML
	public Button butUn;

	@FXML
	public Button butTrois;

	@FXML
	private Button butSept;

	@FXML
	private Button bntSup;

	@FXML
	private Button butBillet10;

	@FXML
	private Button butBillet20;

	@FXML
	private Button butBillet50;

	@FXML
	private Button butBillet100;

	@FXML
	private Button butBillet200;

	@FXML
	public Button bntCarteCad;

	@FXML
	public Button bntPlus;

	@FXML
	public Button bntX;

	@FXML
	public Button bntPour;

	@FXML
	public Button bntMoins;

	@FXML
	public Button bntCash;

	@FXML
	public Button bntCarte;

	@FXML
	private Button bntAfter;

	@FXML
	public AnchorPane paneBnt;

	@FXML
	public Button bntproduits;

	@FXML
	public Button returnproductbtn;

	@FXML
	public TableView<Vente> tableCaisse;

	@FXML
	public TableColumn<Vente, String> columnProduit;

	@FXML
	public TableColumn<Vente, Double> columnPrix;

	@FXML
	public TableColumn<Vente, Integer> columnQuantite;

	@FXML
	public ScrollPane scrollPane;

	@FXML
	public ScrollPane scrollPane1;

	@FXML
	public TextField AfficcheurSomme;

	@FXML
	private TextField textTest;
	
	
	private Reglage myreglage =null;
	private LoginControl logincontrol = null;
	private ReglageDB dbreglage = null;

	public CaisseControl(Connection db) {
		this.DB = db;
		ventedb = new VenteDB(DB);
		produitsdb = new ProduitsDB(DB);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("caisse1.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			logincontrol = new LoginControl(db);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbreglage = new ReglageDB(db);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

	}

	@FXML
	void bntproduitsClicked(ActionEvent event) {

	}

	@FXML
	void MousTableClicked(MouseEvent event) {
		if (tableCaisse.getSelectionModel().getSelectedItem() != null)
			produitTable = produitsdb.getName(tableCaisse.getSelectionModel().getSelectedItem().getNomProduits());
	}

	@FXML

	void bntCashClicked(ActionEvent event) throws IOException {
		item1 = tableCaisse.getItems();
		CashControl cash = new CashControl(this, DB);
		Stage primaryStage = new Stage();
		primaryStage.setScene(new Scene(cash));
		primaryStage.getIcons().add(new Image("/img/its_icoTR.png"));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Encaissement cash ");
		primaryStage.show();
		prixFinal = 0;
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	@FXML
	void bntAfterClicked(ActionEvent event) {
		// insertions du ticket avec status non valide
		int id = 0;
		int quantite;
		Double tvaP = 0.0;
		Double tvaC = 0.0;
		Double prix;
		Double somme = 0.0;
		Double tva = 0.0;
		item1 = tableCaisse.getItems();

		inserNamePrenom();
		if (item1.isEmpty()) {
			// System.out.println("empty");
			return;
		}

		else {
			// System.out.println("plein");
			somme = Double.parseDouble(AfficcheurSomme.getText());
			ventedb.addVente(id, dateHeure, nameUser, prenomUser, "Ticket:" + String.valueOf(id), tva, somme,
					"non specifié");
			for (int i = 0; i < item1.size(); i++) {
				quantite = item1.get(i).getQunatite();
				prix = item1.get(i).getPrixVente();
				tvaP = item1.get(i).getTVA();
				tvaC = (double) Math.round(((prix * tvaP) / 100) * 100) / 100;
				// System.out.println("tvaP: " + tvaP);
				//ventedb.addProduitsV(item1.get(i).getNomProduits(), quantite, prix, tvaP, tvaC, ventedb.getPk());
				tva += tvaC;
				id++;
			}

			tva = (double) Math.round(tva * 100) / 100;
			ventedb.UpdateTVA(ventedb.getPk(), tva, "Ticket:");
			ventedb.updatestatusnonvalide(ventedb.getPk());
			// System.out.println("size befor clear "+tableCaisse.getItems().size());
			tableCaisse.getItems().clear();
			tableCaisse.refresh();
			AfficcheurSomme.clear();
			// System.out.println("size after clear "+tableCaisse.getItems().size());
		}
	}

	@FXML
	void ScrollPaneMouseEntred(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			scrollPane1.setContent(null);
			// System.out.println("OK");
		}
	}

	public void bntCarte() {
		ImageView img = new ImageView("/img/IconeCarteBancaire.png");
		img.setFitWidth(136);
		img.setFitHeight(94);
		bntCarte.setGraphic(img);

	}

	public void bntAfter() {
		ImageView img = new ImageView("/img/EncaisseApres.png");
		img.setFitWidth(136);
		img.setFitHeight(94);
		bntAfter.setGraphic(img);

	}

	public void bntCash() {
		ImageView img = new ImageView("/img/Iconecashfinal.png");
		img.setFitWidth(136);
		img.setFitHeight(94);
		bntCash.setGraphic(img);

	}

	public void bntReturnProduct() {
		ImageView img = new ImageView("/img/returnproduct.png");
		img.setFitWidth(75);
		img.setFitHeight(55);
		returnproductbtn.setGraphic(img);
	}

	public void checkButton() {
		ImageView img = new ImageView("/img/check.png");
		img.setFitWidth(68);
		img.setFitHeight(53);
		checkButton.setGraphic(img);
	}

	public void bntSwitch() {
		ImageView img = new ImageView("/img/switchIcon.png");
		img.setFitWidth(75);
		img.setFitHeight(60);
		switchButton.setGraphic(img);
	}

	public void loadType(int nbr) {
		GridPane grid = new GridPane();
		int nb = 0;
		int div = 0;
		int nbBut;
		if (nbr != 0 && nbr != 5)
			div = nbr ;
		else if (nbr == 0)
			div = 1;

		nbTypeC = type.size();
		nbTypeL = nbTypeC / div;

		if (nbTypeC % div != 0)
			nbTypeL += 1;

		nbBut = nbTypeC - 1;
		grid.setPadding(new Insets(BUTTON_PADDING));
		grid.setHgap(BUTTON_PADDING);
		grid.setVgap(BUTTON_PADDING);

		int r, c = 0;
		for (r = 0; r < nbTypeL; r++) {
			for (c = 0; c < div; c++) {
				Button button = new Button(type.get(nbBut--).toString());
				loadProduitsType(button, div);
				button.setPrefWidth(191);
				button.setPrefHeight(50);
				button.setStyle(
						"-fx-background-color: rgba(0,0,0,0),  linear-gradient(#000000,#000000),linear-gradient(#FFFFFF 0%,#FFFFFF 10%, #FFFFFF 50%, #FFFFFF 51%, #FFFFFF 100%);-fx-font-weight: bold;-fx-font-size: 20;");
				butType.add(button);
				grid.add(button, c, r);
				nb++;
				if (nb == nbTypeC)
					break;
			}
		}

		// **************************** Button divers
		/*
		 * Button button2 = new Button("Divers"); loadProduitsType(button2 ,div);
		 * button2.setPrefWidth(150); button2.setPrefHeight(70); button2.
		 * setStyle("-fx-background-color: rgba(0,0,0,0),  linear-gradient(#000000,#000000),linear-gradient(#FFFFFF 0%,#FFFFFF 10%, #FFFFFF 50%, #FFFFFF 51%, #FFFFFF 100%);-fx-font-weight: bold;-fx-font-size: 24;"
		 * ); butType.add(button2); c++; grid.add(button2, c, r-1);
		 * 
		 */
		scrollPane.setContent(grid);
	}

	public void loadProduitsType(Button button, int nbr) {
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				GridPane grid1 = new GridPane();
				int div = 5;
				if (nbr != 0)
					div = nbr;
				else if (nbr == 0)
					div = 1;

				int nb1 = 0;
				int nbBut1;

				for (int i = 0; i < produits.size(); i++) {
					if (produits.get(i).getType().equals(e.getSource().toString().substring(34).replace("'", "")))
						nomProduit.add(produits.get(i).getNom());
				}

				nbProduitTypeC = nomProduit.size();
				nbProduitTypeL = nbProduitTypeC / div;

				if (nbProduitTypeC % div != 0)
					nbProduitTypeL += 1;

				nbBut1 = nbProduitTypeC - 1;

				grid1.setPadding(new Insets(BUTTON_PADDING));
				grid1.setHgap(BUTTON_PADDING);
				grid1.setVgap(BUTTON_PADDING);

				for (int r = 0; r < nbProduitTypeL; r++) {
					for (int c = 0; c < div; c++) {
						int nbr = nbBut1--;
						Button button = new Button(nomProduit.get(nbr).toString());
						loadProduitTable(button, nomProduit.get(nbr));
						button.setPrefWidth(191);
						button.setPrefHeight(50);
						button.setStyle("-fx-background-color:#c7dbd1;-fx-font-weight: bold;-fx-font-size: 20;");
						grid1.add(button, c, r);
						scrollPane1.setContent(grid1);
						nb1++;
						if (nb1 == nbProduitTypeC)
							break;
					}
				}
				/*
				 * if(button.getText()=="Divers") { Button button = new Button("divers");
				 * loadProduitTable(button, "divers" ); button.setPrefWidth(150);
				 * button.setPrefHeight(70); button.
				 * setStyle("-fx-background-color:#c7dbd1;-fx-font-weight: bold;-fx-font-size: 24;"
				 * ); grid1.add(button, 0, 0); scrollPane1.setContent(grid1); }
				 */
				nomProduit.clear();
			}
		});
	}

	public void updateTicketDetails() {
		Double ttn = 0.0;
		// System.out.println("size tableCaisse "+tableCaisse.getItems().size());
		for (Vente i : tableCaisse.getItems()) {
			ttn += i.getPrixVente();
		}
		somme = ttn;
		AfficcheurSomme.setText(ttn.toString());

	}

	public void loadProduitTable(Button button, String name) {
		if (!name.equals("divers")) {
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					for (int i = 0; i < produits.size(); i++) {
						if (produits.get(i).getNom().equals(name)) {
							produitTable = produits.get(i);
							break;
						}
					}

					int k = 0, j = -1;
					boolean trouve = false;
					Vente v1 = new Vente();
					for (Vente v : tableCaisse.getItems()) {
						k++;
						if ((v.getNomProduits() == produitTable.getNom())) {
							j = k;
							trouve = true;
							v1 = v;
							break;
						}
					}
					
					if (trouve == false) {
						
						item.add(new Vente(0, produitTable.getNom(), produitTable.getPrixvente(), 1,
								produitTable.getTVA(), (produitTable.getTVA() * produitTable.getPrixvente()) / 100, 0));
						tableCaisse.setItems(item);
						updateTicketDetails();
						
					} else  {
						
						v1.setQunatite(v1.getQunatite() + 1);
						v1.setPrixVente(v1.getPrixVente() + produitTable.getPrixvente());
						tableCaisse.getItems().set(j - 1, v1);
						tableCaisse.refresh();
						updateTicketDetails();
						tableCaisse.getSelectionModel().select(j - 1);

					}

				}
			});

		} else {
			
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					for (int i = 0; i < produits.size(); i++) {
						if (produits.get(i).getNom().equals(name)) {
							produitTable = produits.get(i);
							break;
						}
					}

					int k = 0, j = -1;
					boolean trouve = false;
					Vente v1 = new Vente();
					for (Vente v : tableCaisse.getItems()) {
						k++;
						if (v.getNomProduits() == produitTable.getNom()) {
							j = k;
							trouve = true;
							v1 = v;
							break;
						}
					}
				
					if (trouve == false) {
						double prixx = 0, tvv = 0.0;
						Dialog<DiversDetails> dialog = new Dialog<>();
						dialog.setTitle("Informations produit");
						// dialog.setHeaderText("");
						DialogPane dialogPane = dialog.getDialogPane();
						ButtonType button1 = ButtonType.OK;
						ButtonType button2 = ButtonType.CANCEL;
						dialogPane.getButtonTypes().addAll(button1, button2);
						Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
				    	stage.getIcons().add(new Image("/img/its_icoTR.png"));
				    	dialogPane.setPrefWidth(300);
						TextField textField = new TextField();
						textField.setPrefHeight(50);
						TextField textField2 = new TextField();
						textField2.setPrefHeight(50);
						textField.setPromptText("Prix");
						textField2.setPromptText("TVA");
						Label l1 = new Label("Prix :");
						Label l2 = new Label("TVA :");
						dialogPane.setContent(new VBox(10, l1, textField, l2, textField2));
						Platform.runLater(textField::requestFocus);
						dialog.setResultConverter((ButtonType button) -> {
							if (button == button1) {
								
								return new DiversDetails(Double.parseDouble(textField.getText()),
										Double.parseDouble(textField2.getText()));
							} else
								return new DiversDetails(0.0, 0.0);

						});
						DiversDetails d = new DiversDetails(0.0, 0.0);
						Optional<DiversDetails> optionalResult = dialog.showAndWait();
						optionalResult.ifPresent((DiversDetails results) -> {
							d.prix = results.prix;
							d.tva = results.tva;
						});

						produitTable.setPrixvente(d.prix);
						produitTable.setTVA(d.tva);
						item.add(new Vente(0, produitTable.getNom(), produitTable.getPrixvente(), 1,
								produitTable.getTVA(), (produitTable.getTVA() * produitTable.getPrixvente()) / 100, 0));
						tableCaisse.setItems(item);
						updateTicketDetails();
					    produit = produitsdb.getDivers();
				    	produitsdb.update(produit.getId(), produit.getReference(), produit.getNom(), produit.getPrixAchat(), d.prix, produit.getType(),1, produit.getFornisseur(), d.tva,produit.getImage(), produit.getUnit(), produit.getCode_bare());
				    	produit = produitsdb.get(produit.getId());

					} else if(v1.getNomProduits() != "divers") {
							System.out.println("anicien  = "+v1.getPrixVente()+" nouveau = "+produitTable.getPrixvente()+v1.getPrixVente());
						v1.setQunatite(v1.getQunatite() + 1);
						v1.setPrixVente(v1.getPrixVente() + produitTable.getPrixvente());
						tableCaisse.getItems().set(j - 1, v1);
						tableCaisse.refresh();
						updateTicketDetails();
						tableCaisse.getSelectionModel().select(j - 1);

					}

				}
			});

		}
	}

	@FXML
	void bntPourClicked(MouseEvent event) throws IOException {

		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (tableCaisse.getSelectionModel().getSelectedItem() != null) {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fenetre/Reduction.fxml"));
			Stage primaryStage = new Stage();
			loader.setController(new ReductionController(this, DB));

			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Pourcentage produit");
			primaryStage.getIcons().add(new Image("/img/its_icoTR.png"));
			primaryStage.show();
			}

		}
	}

	@FXML
	void bntCarteCadClicked(ActionEvent event) {
		Double tva = 0.0;
		int id = 0;
		int numeroVente;
		int quantite;
		Double tvaP = 0.0;
		Double tvaC = 0.0;
		Double prix;
		Double somme = 0.0;
		String type = "Carte";
		inserNamePrenom();
		item1 = tableCaisse.getItems();
		if (item1.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notification");
			alert.setHeaderText("Aucun produit n'a été selectionné");
			alert.setContentText("Veuillez choisir au moins un produit !");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		    stage.getIcons().add(new Image("/img/its_icoTR.png"));
			alert.show();
		} else {
			somme = Double.parseDouble(AfficcheurSomme.getText());
			openCashDrawer();
			ventedb.addVente(id, dateHeure, nameUser, prenomUser, "Ticket:" + String.valueOf(id), tva, somme, type);
			SavePDF();
			tva = (double) Math.round(tva * 100) / 100;
			ventedb.UpdateTVA(ventedb.getPk(), tva, "Ticket:");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notification");
			alert.setHeaderText("Paiement ");
			alert.setContentText("Paiement effectué par carte !");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		    stage.getIcons().add(new Image("/img/its_icoTR.png"));
			alert.show();
			PauseTransition delay = new PauseTransition(Duration.seconds(3));
			delay.setOnFinished(eventt -> alert.close());
			delay.play();
			for (int i = 0; i < item1.size(); i++) {
				quantite = item1.get(i).getQunatite();
				prix = item1.get(i).getPrixVente();
				tvaP = item1.get(i).getTVA();
				tvaC = (double) Math.round(((prix * tvaP) / 100) * 100) / 100;
				// System.out.println("tvaP: " + tvaP);
				//ventedb.addProduitsV( item1.get(i).getNomProduits(), quantite, prix, tvaP, tvaC, ventedb.getPk());
				tva += tvaC;
				id++;
			}
			tableCaisse.getItems().clear();
			AfficcheurSomme.clear();
		}
		
		
	}
	
	  public void SavePDF()
      {
      	try
          {
      		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
  	        String dateF = LocalDateTime.now().format(formatter).toString();
  	        System.out.println("gettickets"+myreglage.getTickets());
  			String t = myreglage.getTickets();
			t=t.replace("\\","//" );
			String nameFile = t+"//ticket"+ventedb.getPk()+".pdf";
  			File file = new File(nameFile );
  	        file.getParentFile().mkdirs();
  	        manipulatePdf(nameFile);
  	        print(nameFile);
          }
          catch (Throwable t)
          {
              t.printStackTrace();
          }
      	
      }

	  protected void manipulatePdf(String dest) throws Exception {
     		
     		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
 	        String dateF = LocalDateTime.now().format(formatter).toString();
 	        
 	    	PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
 	        Document doc = new Document(pdfDoc);
 	        Paper paper = new Paper();
	        double margin = 0; 
 	        doc.setMargins(0, 0, 0, 0);


     		//------------------- HEADER  ----------------------------

     	
     		Text t11 = new Text("Tacos planet Gare\n").setFontSize(18);
     		Text t12 = new Text("\n"+"AV. louis-Ruchonnet 2B" + "\n"+"1003 Lausanne").setFontSize(18);
     		Text t111 = new Text("\nTél: 021 320 44 44\n\n").setFontSize(18);
     		Paragraph paraheader = new Paragraph().add(t11).add(t12).add(t111);
     		
     		paraheader.setMargins(0, 0, 2, 0).setTextAlignment(TextAlignment.CENTER).setWidth(200);
     		doc.add(paraheader); 
  		//doc.add(new Paragraph(new Text("---------------------------------------------------------------")).setMargins(0, 15, 2, 15).setTextAlignment(TextAlignment.CENTER).setWidth(260));
  		
     		//Text detailstick = new Text("Vendeur  :\t\t\t\t"+caisse.NameUser.getText()+"\nTicket N° :\t\t\t\t"+dbVente.getPk()).setFontSize(16);
     		
     		//Paragraph dt = new Paragraph().add(detailstick);
     		//dt.setMargins(0, 2, 0, 0).setTextAlignment(TextAlignment.CENTER).setWidth(260);
     		//doc.add(dt); 
     		Text detailstick2 = new Text(dateF+"\n\n");
     		detailstick2.setTextRise((float) 50);
     		Text quittance = new Text("Quittance"+"\n");
     		
     		Paragraph dt2 = new Paragraph().add(detailstick2).add(quittance);
     		dt2.setMargins(1, 0, 0, 0).setTextAlignment(TextAlignment.LEFT).setWidth(200);
     		doc.add(dt2); 
     		doc.add(new Paragraph(new Text("---------------------------------------------------------------")).setWidth(260));
 	           
     		
      	double somme = (double) 0.0;
      	
      	Text article = new Text("Article").setFontSize(16);
      	Paragraph articl = new Paragraph().add(article).setWidth(260);
      	doc.add(articl);
      	Text unite = new Text("Unité").setFontSize(16);
      	Paragraph unit = new Paragraph().add(unite).setWidth(260);
      	doc.add(unit);
      	Text prix = new Text("Prix").setFontSize(16);
      	Paragraph pri = new Paragraph().add(prix).setWidth(260);
      	doc.add(pri);
      	
 	        for (int i = 0; i < this.item1.size(); i++) {
 	        	Vente f= this.item1.get(i);
 	        	somme+=f.getTVAC();
 	        	Text nomprod = new Text(f.getNomProduits());
 	        	Paragraph nompro = new Paragraph().add(nomprod).setWidth(260);
 	        	doc.add(nompro);
 	        	Text qteprod = new Text(f.getQunatite()+"");
 	        	Paragraph qtepro = new Paragraph().add(qteprod).setWidth(260);
 	        	doc.add(qtepro);
      		Text prixprod = new Text(f.getPrixVente()+"");
      		Paragraph prixpro = new Paragraph().add(prixprod).setWidth(260);
 	        	doc.add(prixpro);	
 	        	
 	        }
 	        nbr = this.item1.size() * 3;
 	        doc.add(new Paragraph(new Text("---------------------------------------------------------------")).setWidth(260));
  		//Text tvtick = new Text("TVA :\t\t"+somme+" CHF").setFontSize(16);
  		///Paragraph tvt = new Paragraph().add(tvtick);
  		//tvt.setMargins(0, 2, 0, 2).setTextAlignment(TextAlignment.CENTER).setWidth(200);
  		//doc.add(tvt); 
 	        Text footertick = new Text("TOTAL : "+this.AfficcheurSomme.getText()+"  -CHF");
  		Paragraph ft = new Paragraph().add(footertick).setWidth(260);
  		doc.add(ft); 
  		Text paytick = new Text("Paiement : Carte");
  		Paragraph pt = new Paragraph().add(paytick).setWidth(260);
  		doc.add(pt); 
  		
  		/*Text tvaText = new Text("CHE-TVA").setFontSize(16);
  		Paragraph ptv = new Paragraph().add(tvaText);
  		pt.setMargins(2, 2, 2, 2).setTextAlignment(TextAlignment.CENTER).setWidth(260);
  		doc.add(ptv);*/ 
  		
  		doc.add(new Paragraph(new Text("---------------------------------------------------------------")).setWidth(260));
  		Text mercitick = new Text("Merci pour votre visite\nÀ bientôt !\n");
  		Paragraph mt = new Paragraph().add(mercitick).setWidth(260);
  		doc.add(mt);
  		
  		Text site = new Text("\nwww.tacosdelagare.ch");
  		Paragraph st = new Paragraph().add(site).setWidth(260);
  		doc.add(st);
  		
  		doc.close();
  		
 	    }
 	    
	
	@FXML
	void checkButtonClicked(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fenetre/TicketsAttente.fxml"));
		Stage primaryStage = new Stage();
		loader.setController(new TicketsAttenteController(DB, this));
		AnchorPane root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Ticket en attente");
		primaryStage.getIcons().add(new Image("/img/its_icoTR.png"));
		primaryStage.showAndWait();

	}

	//************************************************** tout objet à imprimer doit etre declaré final
			public void print(String path) 
		    {
				path = path.replace("//","/" );
				PDDocument document;
				
				try {	
				document = PDDocument.load(new File(path));
				String extractedText = PdfTextExtractor.getTextFromPage(new PdfReader(path), 1);
				
				PDDocument document1 = PDDocument.load(new File(path));
				
				PDFTextStripper stripper = new PDFTextStripper();
				String text = stripper.getText(document1);
				System.out.println("text ::    \n" + text.toString());
				PrintService myPrintService = PrintUtility.findPrintService(myreglage.getImptickets().substring(8));
		        PrinterJob job = PrinterJob.getPrinterJob();
		        PageFormat pf = job.defaultPage();
		        Paper paper = new Paper();
		        
		        double margin = 0; 

		        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, extractedText.length());
		        paper.setSize(paper.getHeight(), extractedText.length());
		        pf.setPaper(paper);
		        job.setPrintable(new OutputPrinter(text, nbr),pf);
		        job.setPrintService(myPrintService);
		        job.print();
					} catch (InvalidPasswordException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (PrinterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 
		    }   

			public void setReglage()
			{
		         Reglage r = dbreglage.getReglage(logincontrol.getId_utilisateur());
		         if (r != null)
		 			myreglage=r;
			}
			
			public void openCashDrawer() {

		        byte[] open = {27,112,0,100,(byte) 250};
//		      byte[] cutter = {29, 86,49};
		        PrintService myPrintService = PrintUtility.findPrintService(myreglage.getImptickets().substring(8));
		        DocPrintJob job = myPrintService.createPrintJob();
		        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		        Doc doc = new SimpleDoc(open,flavor,null);
		        PrintRequestAttributeSet aset = new 
		        HashPrintRequestAttributeSet();
		        try {
		            job.print(doc, aset);
		        } catch (PrintException ex) {
		            System.out.println(ex.getMessage());
		        }
		    }

	@FXML
	void xButtonClicked(ActionEvent event) throws IOException, SQLException {
		inserNamePrenom();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fenetre/JournalUtilisateur.fxml"));
		Stage primaryStage = new Stage();
		loader.setController(new JournalUtilisateurController(nameUser, prenomUser, DB));

		BorderPane root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.getIcons().add(new Image("/img/its_icoTR.png"));
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Journalière caissier"); 
		primaryStage.show();

	}

	@FXML
	void zButtonClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fenetre/TicketsFilter.fxml"));
		Stage primaryStage = new Stage();
		loader.setController(new TicketsFilterController(DB));

		BorderPane root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.getIcons().add(new Image("/img/its_icoTR.png"));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Historique caissiere");
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	@FXML
	void bntXClicked(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (tableCaisse.getSelectionModel().getSelectedItem() != null) {
				// System.out.println("somme a soustraire
				// "+tableCaisse.getSelectionModel().getSelectedItem().getPrixVente()+" prixf
				// "+prixFinal);
				prixFinal = (float) (prixFinal - tableCaisse.getSelectionModel().getSelectedItem().getPrixVente());
				// System.out.println("prixf after "+prixFinal);
				// System.out.println("valeur de somme "+somme);
				// AfficcheurSomme.setText(""+somme+"");
				// prixFinal = (float) Math.round((prixFinal - produitTable.getPrixvente()) *
				// 100) / 100;
				/*
				 * AfficcheurSomme.setText(""+prixFinal+""); somme =
				 * Double.parseDouble(AfficcheurSomme.getText());
				 */

				tableCaisse.getItems().remove(tableCaisse.getSelectionModel().getSelectedItem());
				updateTicketDetails();

			}
		}

	}

	@FXML
	void switchButtonClicked(ActionEvent event) throws IOException, SQLException {
		Stage primaryStage = new Stage();
		SwitchControl switchControl = new SwitchControl(DB);
		Stage primaryStage1 = (Stage) switchButton.getScene().getWindow();
		primaryStage1.close();
		// switchControl.textEmailPseudo.setText("Malek");
		primaryStage.setScene(new Scene(switchControl));
		primaryStage.getIcons().add(new Image("/img/its_icoTR.png"));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Switch caissier");
		primaryStage.show();

	}

	@FXML
	void bntMoinsClicked(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (tableCaisse.getSelectionModel().getSelectedItem() != null) {
				Double prix = (Double) tableCaisse.getSelectionModel().getSelectedItem().getPrixVente();
				

				if (tableCaisse.getSelectionModel().getSelectedItem().getQunatite() == 0) {
					tableCaisse.getItems().remove(tableCaisse.getSelectionModel().getSelectedItem());
					// tableCaisse.getSelectionModel().getSelectedItem().setQunatite(tableCaisse.getSelectionModel().getSelectedItem().getQunatite());
				} else {
					tableCaisse.getSelectionModel().getSelectedItem().setPrixVente(prix - produitTable.getPrixvente());
					tableCaisse.getSelectionModel().getSelectedItem()
							.setQunatite(tableCaisse.getSelectionModel().getSelectedItem().getQunatite() - 1);
					tableCaisse.getColumns().get(1).setVisible(false);
					tableCaisse.getColumns().get(1).setVisible(true);
					tableCaisse.getColumns().get(2).setVisible(false);
					tableCaisse.getColumns().get(2).setVisible(true);
					/*
					 * prixFinal = (float) Math.round((prixFinal - produitTable.getPrixvente()) *
					 * 100) / 100; AfficcheurSomme.setText(""+prixFinal+""); somme =
					 * Double.parseDouble(AfficcheurSomme.getText());
					 */
					updateTicketDetails();
					/*
					 * if(prixFinal==0)
					 * tableCaisse.getItems().remove(tableCaisse.getSelectionModel().getSelectedItem
					 * ());
					 */
					if (tableCaisse.getSelectionModel().getSelectedItem().getQunatite() == 0) {
						tableCaisse.getItems().remove(tableCaisse.getSelectionModel().getSelectedItem());
						// tableCaisse.getSelectionModel().getSelectedItem().setQunatite(tableCaisse.getSelectionModel().getSelectedItem().getQunatite());
					}
				}

			}
		}
	}

	@FXML
	void returnproductbtnClicked(ActionEvent event) {
		Double tva = 0.0;
		int id = 0;
		int numeroVente;
		int quantite;
		Double tvaP = 0.0;
		Double tvaC = 0.0;
		Double prix;
		Double somme = 0.0;
		String type = "Aucun";
		inserNamePrenom();
		item1 = tableCaisse.getItems();
		if (item1.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notification");
			alert.setHeaderText("Aucun produit n'a été selectionné");
			alert.setContentText("Veuillez choisir au moins un produit !");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	    	stage.getIcons().add(new Image("/img/its_icoTR.png"));
			alert.show();
		} else {
			somme = Double.parseDouble(AfficcheurSomme.getText());
			ventedb.addVente(id, dateHeure, nameUser, prenomUser, "Retour:" + String.valueOf(id), tva * (-1),
					somme * (-1), "Aucun");

			for (int i = 0; i < item1.size(); i++) {
				quantite = item1.get(i).getQunatite();
				prix = item1.get(i).getPrixVente();
				tvaP = item1.get(i).getTVA();
				tvaC = (double) Math.round(((prix * tvaP) / 100) * 100) / 100;
				System.out.println("tvaP: " + tvaP);
				//ventedb.addProduitsV(item1.get(i).getNomProduits(), quantite * (-1), prix * (-1), tvaP * (-1), tvaC,
					//	ventedb.getPk());
				tva += tvaC;
				id++;
			}
			tva = (double) Math.round(tva * 100) / 100;
			ventedb.UpdateTVA(ventedb.getPk(), tva, "Retour:");
			System.out.println("id enregistrééé   " + ventedb.getPk());
			tableCaisse.getItems().clear();
			AfficcheurSomme.clear();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notification");
			alert.setHeaderText("Retour Ticket");
			alert.setContentText("Les produits ont été remis !");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	    	stage.getIcons().add(new Image("/img/its_icoTR.png"));
			alert.show();
			PauseTransition delay = new PauseTransition(Duration.seconds(3));
			delay.setOnFinished(eventt -> alert.close());
			delay.play();
		}

	}

	@FXML
	void bntPlusClicked(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (tableCaisse.getSelectionModel().getSelectedItem() != null) {
				Double prix = (Double) tableCaisse.getSelectionModel().getSelectedItem().getPrixVente();
				//System.out.println("produit selectionné  =   "+produitTable);
				//System.out.println("prod recup "+tableCaisse.getSelectionModel().getSelectedItem());
				tableCaisse.getSelectionModel().getSelectedItem().setPrixVente(prix + produitTable.getPrixvente());
				tableCaisse.getSelectionModel().getSelectedItem()
						.setQunatite(tableCaisse.getSelectionModel().getSelectedItem().getQunatite() + 1);
				tableCaisse.getColumns().get(1).setVisible(false);
				tableCaisse.getColumns().get(1).setVisible(true);
				tableCaisse.getColumns().get(2).setVisible(false);
				tableCaisse.getColumns().get(2).setVisible(true);
				/*
				 * prixFinal = (float) Math.round((prixFinal + produitTable.getPrixvente()) *
				 * 100) / 100; AfficcheurSomme.setText(""+prixFinal+"");
				 */
				updateTicketDetails();
				somme = Double.parseDouble(AfficcheurSomme.getText());
			}
		}
	}

	public void InitialTable() {
		columnProduit.setCellValueFactory(new PropertyValueFactory("nomProduits"));
		columnPrix.setCellValueFactory(new PropertyValueFactory("prixVente"));
		columnQuantite.setCellValueFactory(new PropertyValueFactory("qunatite"));
	}

	public void clearTable() {
		tableCaisse.getItems().clear();
		AfficcheurSomme.clear();
	}

	@FXML
	public void textTestDraged(MouseEvent event) {

	}

    @FXML
    public void codeBar(KeyEvent event) {
    	buffer.append(event.getText());
    	if (event.getCode() == KeyCode.ENTER) {
    		add(buffer);
    		cnt++;
    		buffer.delete(0, buffer.length());
    	}

    }
    public void add(StringBuffer buffer) {
    System.out.println(cnt+"\n");
    for(int i = 0; i < buffer.length(); i++) {
    	buffer.deleteCharAt(i);
    }
    System.out.println(buffer.toString());
    
    
    for (int i = 0; i < produits.size(); i++) {
		if (produits.get(i).getCode_bare().equals(buffer.toString())) {
			produitTable = produits.get(i);
			break;
		}
	}

	int k = 0, j = -1;
	boolean trouve = false;
	Vente v1 = new Vente();
	for (Vente v : tableCaisse.getItems()) {
		k++;
		if (v.getNomProduits() == produitTable.getNom()) {
			j = k;
			trouve = true;
			v1 = v;
			break;
		}
	}

	if (trouve == false) {
		item.add(new Vente(0, produitTable.getNom(), produitTable.getPrixvente(), 1,
		produitTable.getTVA(), (produitTable.getTVA() * produitTable.getPrixvente()) / 100, 0));
		prixFinal = (float) Math.round((prixFinal + produitTable.getPrixvente()) * 100) / 100;
		tableCaisse.setItems(item);
		updateTicketDetails();
		AfficcheurSomme.setText(""+prixFinal+"");
		somme = Double.parseDouble(AfficcheurSomme.getText());
		 
	}else {
		System.out.println("qte recupere "+v1.getQunatite()+"\n");
			v1.setQunatite(v1.getQunatite() + 1 );
			v1.setPrixVente(v1.getPrixVente() + produitTable.getPrixvente());
			tableCaisse.getItems().set(j - 1, v1);
			tableCaisse.refresh();
			updateTicketDetails();
			tableCaisse.getSelectionModel().select(j - 1);	
			;
		  }
	
    }
    String test = "";
	public void inserNamePrenom( ) {
		nameUser = loginControl.getName();
		prenomUser = loginControl.getLastName();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		type = produitsdb.getType();
		produits = produitsdb.load();
		ImageLogoText.setImage(new Image("/img/logoText.png"));
		ImageUser.setImage(new Image("/img/user-png-icon-black-businessman-icon-256.png"));
		bntCarte();
		bntCash();
		//bntCaisse();
		bntAfter();
		bntSwitch();
		bntReturnProduct();
		setReglage();
		checkButton();
		loadType(3);
		InitialTable();
		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			timeNow.setText(LocalDateTime.now().format(formatter).toString());
			dateHeure = LocalDateTime.now().format(formatter).toString();
		}), new KeyFrame(Duration.seconds(1)));
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();
		textTest = new TextField("myEmail@example.com");
		textTest.getProperties().put("vkType", "email");
		assert (borderPane != null);
		assert (borderPane != null);

	}

	public ObservableList<Vente> getItem() {
		return item;
	}

	public void setItem(ObservableList<Vente> item) {
		this.item = item;
	}

	private static class DiversDetails {

		Double prix, tva;

		public DiversDetails(Double prix, Double tva) {
			this.prix = prix;
			this.tva = tva;
		}
	}
	



}
