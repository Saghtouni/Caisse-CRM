package Fenetre;


import java.io.BufferedInputStream;






import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Destination;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
 
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.lang.model.element.ElementKind;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Destination;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.qoppa.pdfWriter.PDFPage;

import DataBase.FactureDB;
import DataBase.ProduitsDB;
import DataBase.ReglageDB;
import DataBase.VenteDB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import objects.ItemFacture;
import objects.Produits;
import objects.Reglage;


import com.qoppa.pdf.settings.ImageCompression;

public class FactureController extends TabPane  implements Initializable {
	
	    @FXML
	    private TextArea remarqueF;

	    @FXML
	    private HBox vboxtotal12;

	    @FXML
	    private Label timeNow1;

	    @FXML
	    public Label totalnetF;

	    @FXML
	    public Label tvaF;

	    @FXML
	    private Label timeNow11;

	    @FXML
	    public Label totalbrutF;

	    @FXML
	    private Button btnImprimerF;

	    @FXML
	    private Button bntEnregistrerF;

	    @FXML
	    public Label nomclientF;

	    @FXML
	    public Label adresseF;

	    @FXML
	    private Label telephoneF;

	    @FXML
	    private Button btnclientF;

	    @FXML
	    private Label numFactF;

	    @FXML
	    private Button selectDevisF;

	    @FXML
	    public TableView<ItemFacture> tableproduitsF;

	    @FXML
	    public TableColumn<ItemFacture, String> columnNomF;
	   
	    @FXML
	    public TableColumn<ItemFacture, Integer> columnQteF;

	    @FXML
	    public TableColumn<ItemFacture, String> columnunitF;

	    @FXML
	    public TableColumn<ItemFacture, Double> columnPrixtotalF;

	    @FXML
	    private TableColumn<ItemFacture, Button> columnColor; 
	    
	    @FXML
	    private TextField produitrechercheF;

	    @FXML
	    private ListView<String> listeproduitsF;

	    @FXML
	    private TextField qteF;

	    @FXML
	    private Button btnajouterF;

	    @FXML
	    private Button btnsupprimerF;

	    @FXML
	    private TextArea remarqueD;

	    @FXML
	    private HBox vboxtotal1;

	    @FXML
	    private Label totalnetD;

	    @FXML
	    private Label tvaD;

	    @FXML
	    private Label totalbrutD;

	    @FXML
	    private Button btnImprimerD;

	    @FXML
	    private Button bntEnregistrerD;

	    @FXML
	    private Label nomclientD;

	    @FXML
	    private Label adresseD;

	    @FXML
	    private Label telephoneD;

	    @FXML
	    private Button btnclientD;

	    @FXML
	    private Label numDevis;

	    @FXML
	    private TableView<ItemFacture> tableproduitsD;

	    @FXML
	    private TableColumn<ItemFacture, String> columnNomD;

	    @FXML
	    private TableColumn<ItemFacture, Integer> columnQteD;

	    @FXML
	    private TableColumn<ItemFacture, String> columnunitD;

	    @FXML
	    private TableColumn<ItemFacture, Double> columnPrixtotalD;

	    @FXML
	    private TableColumn<ItemFacture, Button> columnColorD;
	    
	    @FXML
	    private TextField produitrechercheD;

	    @FXML
	    private ListView<String> listeproduitsD;

	    @FXML
	    private TextField qteD;

	    @FXML
	    private Button btnajouterD;

	    @FXML
	    private Button btnsupprimerD;

	    @FXML
	    private TextArea remarqueA;

	    @FXML
	    private HBox vboxtotal11;

	    @FXML
	    private Label totalnetA;

	    @FXML
	    private Label tvaA;

	    @FXML
	    private Label totalbrutA;

	    @FXML
	    private Button btnImprimerA;

	    @FXML
	    private Button bntEnregistrerA;

	    @FXML
	    private Label nomclientA;

	    @FXML
	    private Label adresseA;

	    @FXML
	    private Label telephoneA;

	    @FXML
	    private Button btnclientA;

	    @FXML
	    private Label numAcompteA;

	    @FXML
	    private TableView<ItemFacture> tableproduitsA;

	    @FXML
	    private TableColumn<ItemFacture, String> columnNomA;

	    @FXML
	    private TableColumn<ItemFacture, Integer> columnQteA;

	    @FXML
	    private TableColumn<ItemFacture, String> columnunitA;
	  
	    @FXML
	    private TableColumn<ItemFacture, Double> columnPrixtotalA;

	   
	    
	    @FXML
	    private TextField produitrechercheA;

	    @FXML
	    private ListView<String> listeproduitsA;

	    @FXML
	    private TextField qteA;

	    @FXML
	    private Button btnajouterA;

	    @FXML
	    private Button btnsupprimerA;

	    @FXML
	    private JFXTimePicker jfx;
	    
	    @FXML
	    public Label reduction;
	    
	    @FXML
	    private Label reductiond;
	    
	    
	    @FXML
	    private TextField nbrreduct;
	    
	    @FXML
	    private TextField nbrreductD;
	    

	    @FXML
	    private Button bntreduction;
	    
	    @FXML
	    private Button bntreductionD;
	    
	    @FXML
	    public JFXTextField titreF;
	    
	    @FXML
	    public JFXTextField titreD;
	    
	    @FXML
	    public JFXTextField titreA;
	    
	    @FXML
	    public JFXTextField qntF;
	    
	    @FXML
	    public JFXTextField qntD;
	    
	    @FXML
	    public JFXTextField qntA;
	    
	    @FXML
	    public JFXTextField prixF;
	    
	    @FXML
	    public JFXTextField prixD;
	    
	    @FXML
	    public JFXTextField prixA;
	    
	    @FXML
	    public JFXTextField unitA;
	    
	    @FXML
	    public JFXTextField unitF;
	    
	    @FXML
	    public JFXTextField unitD;
	    
	    @FXML
	    public JFXButton bntAdTitleF;
	    
	    @FXML
	    public JFXButton bntAdTitleD;
	    
	    @FXML
	    public JFXButton bntAdTitleA;

	    @FXML
	    private ComboBox<Integer> tailleTF;
	    
	    @FXML
	    private ComboBox<Integer> tailleTD;
	    
	    @FXML
	    private ComboBox<Integer> tailleTA;
	    
	    @FXML
	    private JFXCheckBox testAjoutF;
	    
	    @FXML
	    private JFXCheckBox testAjoutD;
	    
	    @FXML
	    private ColorPicker ColorF;
	    
	    @FXML
	    private ColorPicker ColorD;
	    
	 
		public Label idclient = new Label();

		private Connection DB = null;

		private ProduitsDB produitsdb = null;

		private FactureDB facturedb = null;
		
		private LoginControl logincontrol = null;
		private ReglageDB dbreglage = null;
		public static int nbr = 0;

		private ObservableList<String> categories = FXCollections.observableArrayList();
		private ObservableList<String> nomProduits = FXCollections.observableArrayList();
		private ObservableList<String> LSN = FXCollections.observableArrayList();
		private ObservableList<Produits> LSP = FXCollections.observableArrayList();
		ObservableList<ItemFacture> itemsF = FXCollections.observableArrayList();
		ObservableList<ItemFacture> itemsD = FXCollections.observableArrayList();
		ObservableList<ItemFacture> itemsA = FXCollections.observableArrayList();
		private Reglage myreglage =null;
		int numeroVente;
		Double tva1 = 0.0;
		VenteDB ventedb = null; 
		private static boolean jobRunning = true;
		private  static int nbrFacture;
		Color colorFacture;
		int tailleFacture;
		Color colorDevis;
		int tailleDevis;
		
		
		public FactureController(Connection dB) {
			this.DB = dB;
			produitsdb = new ProduitsDB(DB);
			facturedb = new FactureDB(DB);
			ventedb = new VenteDB(DB);
			try {
				logincontrol = new LoginControl(dB);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dbreglage = new ReglageDB(dB);
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FacDevAao.fxml"));
			fxmlLoader.setRoot(this);
			fxmlLoader.setController(this);

			try {
				fxmlLoader.load();
			} catch (IOException exception) {
				throw new RuntimeException(exception);
			}

		}
	    
		
		@FXML
		void tailleTFClicked(ActionEvent event) {
			tailleFacture = tailleTF.getValue();			 
		 }
		
		@FXML
		void tailleTDClicked(ActionEvent event) {
			tailleDevis = tailleTD.getValue();
					 
		 }
		@FXML
		void ColorFClicked(ActionEvent event) {
			colorFacture = ColorF.getValue();
		 }
		
		@FXML
		void ColorDClicked(ActionEvent event) {
			colorDevis = ColorD.getValue();
		 }
		
		@FXML
		void testAjoutClicked(ActionEvent event) {
			if (testAjoutF.isSelected()) {
			System.out.println("OK" );
			}
		 }
		
		
		@FXML
		void testAjoutDClicked(ActionEvent event) {
			if (testAjoutD.isSelected()) {
			System.out.println("OK" );
			}
		 }
		
		 @FXML
		 void bntAdTitleFClicked(ActionEvent event) {
			 ItemFacture itemF = new ItemFacture();
			 itemF.setColor(colorFacture);
			 itemF.setTaille(tailleFacture);
			 Double qnt = 0.0;
			 double prixU = 0.0;
			 int taille = 0;
		
			 if(qntF.getText().equals("")) {
				 System.out.println("qnt null");
			 }else {
				 try
			    	{
					 qnt = Double.parseDouble(qntF.getText());
					 itemF.setQuantite(qnt);
					
			    	}
			    	catch(NumberFormatException e)
			    	{
			    		
			    		Alert alert1 = new Alert(AlertType.ERROR);
			    		alert1.setTitle("Ajouter Titre");
		    			alert1.setHeaderText("Résultat");
		    			alert1.setContentText("il faut inseré un chifre pas de texte !");
		    			Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
		    			stage1.getIcons().add(new Image("/img/its_icoTR.png"));
		    			alert1.showAndWait();
			    		
			    	}
			    
			    }

			 if(prixF.getText().equals("")) {
				 System.out.println("prix null");
			 }else {
				 try
			    	{
					 prixU = Double.parseDouble(prixF.getText());
					 itemF.setPrixunitaire(prixU);
					 itemF.setTva(7.7);
					 BigDecimal bd4 = new BigDecimal(prixU * qnt).setScale(2, RoundingMode.HALF_UP);
					 itemF.setPrixtotal(bd4.doubleValue());
					 
			    	}
			    	catch(NumberFormatException e)
			    	{
			    		
			    		Alert alert1 = new Alert(AlertType.ERROR);
			    		alert1.setTitle("Ajouter Titre");
		    			alert1.setHeaderText("Résultat");
		    			alert1.setContentText("il faut inseré un chifre pas de texte !");
		    			Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
		    			stage1.getIcons().add(new Image("/img/its_icoTR.png"));
		    			alert1.showAndWait();
			    		
			    	}
			    
			    }

			 if(unitF.getText().equals("")) {
				 System.out.println("unit null");
			 }else {
				
				 itemF.setUnit(unitF.getText());
			    
			    }
			 itemF.setNom(titreF.getText());
			 
			 Button button = new Button();
			 
			 if(tailleFacture!=0)
				 button.setText(tailleFacture+"");
			 else
				 button.setText("11");
			
			 if(colorFacture != null)
				 button.setStyle("-fx-background-color:"+toRGBCode(colorFacture)+"; -fx-text-fill: white;");
			 else 
				 button.setStyle("-fx-background-color: black; -fx-text-fill: white;");
			 
			 itemF.setColorBnt(button);
			 tableproduitsF.getItems().add(itemF);
			
			 if(prixU != 0.0)
				 ClearandInitF();
		 }
		
		     public static String toRGBCode( Color color )
		     {
		         return String.format( "#%02X%02X%02X",
		             (int)( color.getRed() * 255 ),
		             (int)( color.getGreen() * 255 ),
		             (int)( color.getBlue() * 255 ) );
		     }
		 
		 @FXML
		 void bntAdTitleDClicked(ActionEvent event) {
			 ItemFacture item = new ItemFacture();
			 item.setColor(colorDevis);
			 item.setTaille(tailleDevis);
			 Double qnt = 0.0;
			 double prixU = 0.0;
		
			 if(qntD.getText().equals("")) {
				 System.out.println("qnt null");
			 }else {
				 try
			    	{
					 qnt = Double.parseDouble(qntD.getText());
					 item.setQuantite(qnt);
					
			    	}
			    	catch(NumberFormatException e)
			    	{
			    		
			    		Alert alert1 = new Alert(AlertType.ERROR);
			    		alert1.setTitle("Frais de véhicule forfait");
		    			alert1.setHeaderText("Résultat");
		    			alert1.setContentText("il faut inseré un chifre pas de texte !");
		    			Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
		    			stage1.getIcons().add(new Image("/img/its_icoTR.png"));
		    			alert1.showAndWait();
			    		
			    	}
			    
			    }

			 if(prixD.getText().equals("")) {
				 System.out.println("prix null");
			 }else {
				 try
			    	{
					 prixU = Double.parseDouble(prixD.getText());
					 item.setPrixunitaire(prixU);
					 item.setTva(7.7);
					 BigDecimal bd4 = new BigDecimal(prixU * qnt).setScale(2, RoundingMode.HALF_UP);
					 item.setPrixtotal(bd4.doubleValue());
					 
			    	}
			    	catch(NumberFormatException e)
			    	{
			    		
			    		Alert alert1 = new Alert(AlertType.ERROR);
			    		alert1.setTitle("Frais de véhicule forfait");
		    			alert1.setHeaderText("Résultat");
		    			alert1.setContentText("il faut inseré un chifre pas de texte !");
		    			Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
		    			stage1.getIcons().add(new Image("/img/its_icoTR.png"));
		    			alert1.showAndWait();
			    		
			    	}
			    
			    }

			 if(unitD.getText().equals("")) {
				 System.out.println("unit null");
			 }else {
				
					 item.setUnit(unitD.getText());
			    
			    }
			
			 item.setNom(titreD.getText());
			 
			 Button button = new Button();
			 
			 if(tailleDevis!=0)
				 button.setText(tailleDevis+"");
			 else
				 button.setText("11");
			
			 if(colorDevis != null)
				 button.setStyle("-fx-background-color:"+toRGBCode(colorDevis)+"; -fx-text-fill: white;");
			 else 
				 button.setStyle("-fx-background-color: black; -fx-text-fill: white;");
			 
			 item.setColorBnt(button);
			 tableproduitsD.getItems().add(item);
			 
			 if(prixU != 0.0)
				 ClearandInitD();
		 }
		 
		 @FXML
		 void bntAdTitleAClicked(ActionEvent event) {
			 ItemFacture item = new ItemFacture();
			 Double qnt = 0.0;
			 double prixU = 0.0;
		
			 if(qntA.getText().equals("")) {
				 System.out.println("qnt null");
			 }else {
				 try
			    	{
					 qnt = Double.parseDouble(qntA.getText());
					 item.setQuantite(qnt);
					
			    	}
			    	catch(NumberFormatException e)
			    	{
			    		
			    		Alert alert1 = new Alert(AlertType.ERROR);
			    		alert1.setTitle("Frais de véhicule forfait");
		    			alert1.setHeaderText("Résultat");
		    			alert1.setContentText("il faut inseré un chifre pas de texte !");
		    			Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
		    			stage1.getIcons().add(new Image("/img/its_icoTR.png"));
		    			alert1.showAndWait();
			    		
			    	}
			    
			    }

			 if(prixA.getText().equals("")) {
				 System.out.println("prix null");
			 }else {
				 try
			    	{
					 prixU = Double.parseDouble(prixA.getText());
					 item.setPrixunitaire(prixU);
					 item.setTva(7.7);
					 BigDecimal bd4 = new BigDecimal(prixU * qnt).setScale(2, RoundingMode.HALF_UP);
					 item.setPrixtotal(bd4.doubleValue());
			    	}
			    	catch(NumberFormatException e)
			    	{	    		
			    		Alert alert1 = new Alert(AlertType.ERROR);
			    		alert1.setTitle("Frais de véhicule forfait");
		    			alert1.setHeaderText("Résultat");
		    			alert1.setContentText("il faut inseré un chifre pas de texte !");
		    			Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
		    			stage1.getIcons().add(new Image("/img/its_icoTR.png"));
		    			alert1.showAndWait();			    		
			    	}
			    
			    }

			 if(unitA.getText().equals("")) {
				 System.out.println("unit null");
			 }else {
				
					 item.setUnit(unitA.getText());
			    
			    }
			 
			 item.setNom(titreA.getText());
			 tableproduitsA.getItems().add(item);
			 
			 if(prixU != 0.0)
				 ClearandInitA();
		 }
		 
	    @FXML
	    void recupererproduitsA(KeyEvent event) throws SQLException {
	    	listeproduitsA.getItems().clear();
			LSN.clear();
			LSP.clear();
			String s = produitrechercheA.getText();

			if (s != null) {
				LSP = produitsdb.searchProduits(s);
			} else {
				LSP = produitsdb.load();
			}
			for (Produits p : LSP)
				LSN.add(p.getNom());
			listeproduitsA.setItems(LSN);
	    }

	    @FXML
	    void recupererproduitsD(KeyEvent event) throws SQLException {
	    	listeproduitsD.getItems().clear();
			LSN.clear();
			LSP.clear();
			String s = produitrechercheD.getText();

			if (s != null) {
				LSP = produitsdb.searchProduits(s);
			} else {
				LSP = produitsdb.load();
			}
			for (Produits p : LSP)
				LSN.add(p.getNom());
			listeproduitsD.setItems(LSN);
	    }

	    @FXML
	    void recupererproduitsF(KeyEvent event) throws SQLException {
	    	listeproduitsF.getItems().clear();
			LSN.clear();
			LSP.clear();
			String s = produitrechercheF.getText();

			if (s != null) {
				LSP = produitsdb.searchProduits(s);
			} else {
				LSP = produitsdb.load();
			}
			for (Produits p : LSP)
				LSN.add(p.getNom());
			listeproduitsF.setItems(LSN);
	    }
	    
	    @FXML
	    void ImprimerFClicked(ActionEvent event) {

	    }
	    
	    @FXML
	    void ImprimerAClicked(ActionEvent event) {

	    }


	    @FXML
	    void btnImprimerDClicked(ActionEvent event) {

	    }
	    
	    @FXML
	    void ajouterFClicked(ActionEvent event) throws SQLException  {
	    	if (qteF.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR, "La quantité est vide !");
				alert.showAndWait();
			} else {

				//String nomp = comboproduit.getSelectionModel().getSelectedItem();
				String nomp = listeproduitsF.getSelectionModel().getSelectedItem();
				Double q = Double.parseDouble(qteF.getText());
				ItemFacture recupere = produitsdb.getPrixTVA(nomp);
				
				Double pu = recupere.getPrixunitaire();
				boolean k = false;
				int i = 0;
				for (ItemFacture itemm : tableproduitsF.getItems()) {
					
					if (itemm.getNom().equals(nomp) && pu.equals(itemm.getPrixunitaire()) ) {
						int j = tableproduitsF.getItems().indexOf(itemm);
						itemm.setQuantite(itemm.getQuantite() + q);
						itemm.setPrixtotal(itemm.getQuantite() * itemm.getPrixunitaire());
						tableproduitsF.getItems().set(j, itemm);
						k = true;
						tableproduitsF.getSelectionModel().select(j);
						ClearandInitF();
						Alert alert = new Alert(AlertType.INFORMATION,
								"Ce produit existe déja ! ça quantité a été augmenté ! ");
						alert.showAndWait();
						break;
					}
				}
				if (k == false && !listeproduitsF.getSelectionModel().getSelectedItems().isEmpty()) {
					String unite = produitsdb.getUnit(nomp);
					ItemFacture f = new ItemFacture(nomp, q, pu, pu * q,recupere.getTva(), unite);
					tableproduitsF.getItems().add(f);

					ClearandInitF();
					tableproduitsF.getSelectionModel().clearSelection();
				}
				
				

			}
	    }

	    @FXML
	    void btnajouterDClicked(ActionEvent event) throws SQLException  {
	    	if (qteD.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR, "La quantité est vide !");
				alert.showAndWait();
			} else {

				//String nomp = comboproduit.getSelectionModel().getSelectedItem();
				String nomp = listeproduitsD.getSelectionModel().getSelectedItem();
				Double q = Double.parseDouble(qteD.getText());
				ItemFacture recupere = produitsdb.getPrixTVA(nomp);
				
				Double pu = recupere.getPrixunitaire();
				boolean k = false;
				int i = 0;
				for (ItemFacture itemm : tableproduitsD.getItems()) {
					
					if (itemm.getNom().equals(nomp) &&  pu.equals(itemm.getPrixunitaire())) {
						int j = tableproduitsD.getItems().indexOf(itemm);
						itemm.setQuantite(itemm.getQuantite() + q);
						itemm.setPrixtotal(itemm.getQuantite() * itemm.getPrixunitaire());
						tableproduitsD.getItems().set(j, itemm);
						k = true;
						tableproduitsD.getSelectionModel().select(j);
						ClearandInitF();
						Alert alert = new Alert(AlertType.INFORMATION,
								"Ce produit existe déja ! ça quantité a été augmenté ! ");
						alert.showAndWait();
						break;
					}
				}
				if (k == false && !listeproduitsD.getSelectionModel().getSelectedItems().isEmpty()) {
					String unite = produitsdb.getUnit(nomp);
					ItemFacture f = new ItemFacture(nomp, q, pu, pu * q,recupere.getTva(), unite);
					
					tableproduitsD.getItems().add(f);

					ClearandInitD();
					tableproduitsD.getSelectionModel().clearSelection();
				}
				
				

			}
	    }

	    @FXML
	    void ajouterAClicked(ActionEvent event) throws SQLException  {
	    	if (qteA.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR, "La quantité est vide !");
				alert.showAndWait();
			} else {

				//String nomp = comboproduit.getSelectionModel().getSelectedItem();
				String nomp = listeproduitsA.getSelectionModel().getSelectedItem();
				Double q = Double.parseDouble(qteA.getText());
				ItemFacture recupere = produitsdb.getPrixTVA(nomp);
				//ObservableList<Produits> produit = produitsdb.searchProduits(nomp);
				Double pu = recupere.getPrixunitaire();
				boolean k = false;

				for (ItemFacture itemm : tableproduitsA.getItems()) {

					if (itemm.getNom().equals(nomp)  && pu.equals(itemm.getPrixunitaire())) {
						int j = tableproduitsA.getItems().indexOf(itemm);
						itemm.setQuantite(itemm.getQuantite() + q);
						itemm.setPrixtotal(itemm.getQuantite() * itemm.getPrixunitaire());
						tableproduitsA.getItems().set(j, itemm);
						k = true;
						tableproduitsA.getSelectionModel().select(j);
						ClearandInitA();
						Alert alert = new Alert(AlertType.INFORMATION,
								"Ce produit existe déja ! ça quantité a été augmenté ! ");
						alert.showAndWait();
						break;
					}
				}
				if (k == false && !listeproduitsA.getSelectionModel().getSelectedItems().isEmpty()) {
					String unite = produitsdb.getUnit(nomp);
					ItemFacture f = new ItemFacture(nomp, q, pu, pu * q,recupere.getTva(), unite);
					tableproduitsA.getItems().add(f);

					ClearandInitA();
					tableproduitsA.getSelectionModel().clearSelection();
				}
				
				

			}
	    }

	    @FXML
	    void bntEnregistrerFClicked(ActionEvent event) throws SQLException {
	    	ObservableList<ItemFacture> mesprods = tableproduitsF.getItems();
			if (!nomclientF.getText().equals("")) {
				if (mesprods != null ) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					String dateHeure = LocalDateTime.now().format(formatter).toString();
					Double ttn = Double.parseDouble(totalnetF.getText().replace(",", "."));
					Double tvv = Double.parseDouble(tvaF.getText().replace(",", "."));
					Double ttb = Double.parseDouble(totalbrutF.getText().replace(",", "."));
					Integer idc = Integer.parseInt(idclient.getText());
					ventedb.addVente(idc, dateHeure, logincontrol.getName(), logincontrol.getLastName(), "Facture: " + String.valueOf(idc), tvv, ttb,
							"non specifié");
				
					for (int i = 0; i < tableproduitsF.getItems().size(); i++) {
						if(tableproduitsF.getItems().get(i).getQuantite() != null && tableproduitsF.getItems().get(i).getPrixunitaire() != null) {
							Double quantite = tableproduitsF.getItems().get(i).getQuantite();
							Double prix = tableproduitsF.getItems().get(i).getPrixunitaire();
							Double tvaP = tableproduitsF.getItems().get(i).getTva();
							Double tvaC = (double) Math.round(((prix * tvaP) / 100) * 100) / 100;
							System.out.println("tvaP: " + tvaP);
							ventedb.addProduitsV( tableproduitsF.getItems().get(i).getNom(), quantite, prix, tvaP, tvaC,ventedb.getPk());
							Double tva = 0.0;
							tva += tvaC;
							
							tva = (double) Math.round(tva * 100) / 100;
							BigDecimal bd4 = new BigDecimal(tva).setScale(2, RoundingMode.HALF_UP);
							ventedb.UpdateTVA(ventedb.getPk(), bd4.doubleValue(),"Facture: ");
						}
					}
					facturedb.add(tableproduitsF.getItems(), dateHeure, ttn, tvv, ttb, idc, remarqueF.getText().toString(),"");
					SavePDFFac();
					qteF.clear();
					//combocategorie.getSelectionModel().select(0);
					//comboproduit.getSelectionModel().select(0);
					listeproduitsF.getSelectionModel().select(0);
					tableproduitsF.getItems().clear();
					nomclientF.setText("");
					//telephoneF.setText("");
					adresseF.setText("");
					idclient.setText("");
					remarqueF.setText("");
					qteF.setText("");
					nbrreduct.setText("");
					DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
					String dateF = LocalDateTime.now().format(formatter1).toString();
					int nbr = 0;
					try {
						nbr = facturedb.getFactureLastId();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					numFactF.setText(dateF+"-"+nbr);
				
					updateFactDetails();
					initListeProduitsF();
					Alert alert = new Alert(AlertType.INFORMATION, "Facture sauvegardé avec sucées !");
					alert.showAndWait();

				} else {
					Alert alert = new Alert(AlertType.WARNING, "Veuillez ajouter au moins un produits !");
					alert.showAndWait();
				}

			} else {
				Alert alert = new Alert(AlertType.WARNING, "Veuillez selectionner un client !");
				alert.showAndWait();
			}
	    }
	    
	    @FXML
	    void bntEnregistrerDClicked(ActionEvent event) throws SQLException {
	    	ObservableList<ItemFacture> mesprods = tableproduitsD.getItems();
			if (!nomclientD.getText().equals("")) {
				if (mesprods != null) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					String dateHeure = LocalDateTime.now().format(formatter).toString();
					Double ttn = Double.parseDouble(totalnetD.getText().replace(",", "."));
					Double tvv = Double.parseDouble(tvaD.getText().replace(",", "."));
					Double ttb = Double.parseDouble(totalbrutD.getText().replace(",", "."));
					Integer idc = Integer.parseInt(idclient.getText());
					Double red = Double.parseDouble(reductiond.getText().replace(",", "."));
					facturedb.addDevis(tableproduitsD.getItems(), dateHeure, ttn, tvv, ttb, idc, nomclientD.getText().toString(),"", red);
					SavePDFDev();
					qteD.clear();
					//combocategorie.getSelectionModel().select(0);
					//comboproduit.getSelectionModel().select(0);
					listeproduitsD.getSelectionModel().select(0);
					tableproduitsD.getItems().clear();
					nomclientD.setText("");
					//telephoneD.setText("");
					adresseD.setText("");
					idclient.setText("");
					remarqueD.setText("");
					DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
					String dateF = LocalDateTime.now().format(formatter1).toString();
					int nbr = 0;
					try {
						nbr = facturedb.getDevisLastId() + 1;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					numDevis.setText(dateF+"-"+nbr);
					updateDevtDetails();
					initListeProduitsD();
					Alert alert = new Alert(AlertType.INFORMATION, "Facture sauvegardé avec sucées !");
					alert.showAndWait();

				} else {
					Alert alert = new Alert(AlertType.WARNING, "Veuillez ajouter au moins un produits !");
					alert.showAndWait();
				}

			} else {
				Alert alert = new Alert(AlertType.WARNING, "Veuillez selectionner un client !");
				alert.showAndWait();
			}
	    }
	    
	    @FXML
	    void bntEnregistrerAClicked(ActionEvent event) throws SQLException {
	    	ObservableList<ItemFacture> mesprods = tableproduitsA.getItems();
			if (!nomclientA.getText().equals("")) {
				if (mesprods != null ) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					String dateHeure = LocalDateTime.now().format(formatter).toString();
					Double ttn = Double.parseDouble(totalnetA.getText().replace(",", "."));
					Double tvv = Double.parseDouble(tvaA.getText().replace(",", "."));
					Double ttb = Double.parseDouble(totalbrutA.getText().replace(",", "."));
					Integer idc = Integer.parseInt(idclient.getText());
					ventedb.addVente(idc, dateHeure, logincontrol.getName(), logincontrol.getLastName(), "Acompte: " + String.valueOf(idc), tvv, ttb,
							"non specifié");
					for (int i = 0; i < tableproduitsF.getItems().size(); i++) {
						if(tableproduitsF.getItems().get(i).getQuantite() != null && tableproduitsF.getItems().get(i).getPrixunitaire() != null) {
							Double quantite = tableproduitsF.getItems().get(i).getQuantite();
							Double prix = tableproduitsF.getItems().get(i).getPrixunitaire();
							Double tvaP = tableproduitsF.getItems().get(i).getTva();
							Double tvaC = (double) Math.round(((prix * tvaP) / 100) * 100) / 100;
							System.out.println("tvaP: " + tvaP);
							ventedb.addProduitsV( tableproduitsF.getItems().get(i).getNom(), quantite, prix, tvaP, tvaC,ventedb.getPk());
							Double tva = 0.0;
							tva += tvaC;
							
							tva = (double) Math.round(tva * 100) / 100;
							BigDecimal bd4 = new BigDecimal(tva).setScale(2, RoundingMode.HALF_UP);
							ventedb.UpdateTVA(ventedb.getPk(), bd4.doubleValue(),"Acompte: ");
						}
					}
					facturedb.add(tableproduitsA.getItems(), dateHeure, ttn, tvv, ttb, idc, remarqueA.getText().toString(),"");
					SavePDFAcom();
					qteA.clear();
					//combocategorie.getSelectionModel().select(0);
					//comboproduit.getSelectionModel().select(0);
					listeproduitsA.getSelectionModel().select(0);
					tableproduitsA.getItems().clear();
					nomclientA.setText("");
					//telephoneA.setText("");
					adresseA.setText("");
					idclient.setText("");
					remarqueA.setText("");
					DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
					String dateF = LocalDateTime.now().format(formatter1).toString();
					int nbr = 0;
					try {
						nbr = facturedb.getFactureLastId() ;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					numAcompteA.setText(dateF+"-"+nbr);
					updateFactDetails();
					initListeProduitsA();
					Alert alert = new Alert(AlertType.INFORMATION, "Facture sauvegardé avec sucées !");
					alert.showAndWait();

				} else {
					Alert alert = new Alert(AlertType.WARNING, "Veuillez ajouter au moins un produits !");
					alert.showAndWait();
				}

			} else {
				Alert alert = new Alert(AlertType.WARNING, "Veuillez selectionner un client !");
				alert.showAndWait();
			}
	    }

	    @FXML
	    void btnclientFClicked(ActionEvent event) throws SQLException {
	    	EnregistrerClientController enrClient = new EnregistrerClientController(DB, nomclientF, adresseF, telephoneF,
					idclient);
			Stage primaryStage = new Stage();
			primaryStage.setScene(new Scene(enrClient));
			primaryStage.getIcons().add(new Image("/img/its_icoTR.png"));
			primaryStage.setResizable(false);
			primaryStage.setTitle("Sélection ou ajouter client");
			primaryStage.showAndWait();


	    }
	    

	    @FXML
	    void btnclientDClicked(ActionEvent event) throws SQLException {
	    	EnregistrerClientController enrClient = new EnregistrerClientController(DB, nomclientD, adresseD, telephoneD,
					idclient);
			Stage primaryStage = new Stage();
			primaryStage.setScene(new Scene(enrClient));
			primaryStage.getIcons().add(new Image("/img/its_icoTR.png"));
			primaryStage.setResizable(false);
			primaryStage.setTitle("Sélection ou ajouter client");
			primaryStage.showAndWait();

	    }


	    @FXML
	    void btnclientAClicked(ActionEvent event) throws SQLException {
	    	EnregistrerClientController enrClient = new EnregistrerClientController(DB, nomclientA, adresseA, telephoneA,
					idclient);
			Stage primaryStage = new Stage();
			primaryStage.setScene(new Scene(enrClient));
			primaryStage.getIcons().add(new Image("/img/its_icoTR.png"));
			primaryStage.setResizable(false);
			primaryStage.setTitle("Sélection ou ajouter client");
			primaryStage.showAndWait();
	    }


	    @FXML
	    void supprimerFClicked(ActionEvent event) {
	    	ItemFacture selectedFact = tableproduitsF.getSelectionModel().getSelectedItem();

			if (selectedFact != null) {
				tableproduitsF.getItems().remove(selectedFact);
				ClearandInitF();
				tableproduitsF.getSelectionModel().clearSelection();

			}
	    }

	    @FXML
	    void supprimerAClicked(ActionEvent event) {
	    	ItemFacture selectedFact = tableproduitsA.getSelectionModel().getSelectedItem();

			if (selectedFact != null) {
				tableproduitsA.getItems().remove(selectedFact);
				ClearandInitA();
				tableproduitsA.getSelectionModel().clearSelection();

			}
	    }
	    @FXML
	    void btnsupprimerDClicked(ActionEvent event) {
	    	ItemFacture selectedFact = tableproduitsD.getSelectionModel().getSelectedItem();

			if (selectedFact != null) {
				tableproduitsD.getItems().remove(selectedFact);
				ClearandInitD();
				tableproduitsD.getSelectionModel().clearSelection();

			}
	    }


	    @FXML
	    void selectDevisFClicked(ActionEvent event) throws SQLException {
	    	SelectDevis select = new SelectDevis(DB, this);
	    	Stage primaryStage = new Stage();
			primaryStage.setScene(new Scene(select));
			primaryStage.getIcons().add(new Image("/img/its_icoTR.png"));
			primaryStage.setResizable(false);
			primaryStage.setTitle("Sélection ou ajouter client");
			primaryStage.showAndWait();

	    }
	    
		public void btnImprimer1() {
			ImageView img = new ImageView("/img/printer.png");
			img.setFitWidth(75);
			img.setFitHeight(60);
			btnImprimerF.setGraphic(img);
		}
		
		public void btnImprimer2() {
			ImageView img = new ImageView("/img/printer.png");
			img.setFitWidth(75);
			img.setFitHeight(60);
			btnImprimerD.setGraphic(img);
		}
		public void btnImprimer3() {
			ImageView img = new ImageView("/img/printer.png");
			img.setFitWidth(75);
			img.setFitHeight(60);
			btnImprimerA.setGraphic(img);
		}
		
		
		public void bntEnregistrer1() {
			ImageView img = new ImageView("/img/Save.png");
			img.setFitWidth(75);
			img.setFitHeight(60);
			bntEnregistrerF.setGraphic(img);
		}
		
		public void bntEnregistrer2() {
			ImageView img = new ImageView("/img/Save.png");
			img.setFitWidth(75);
			img.setFitHeight(60);
			bntEnregistrerD.setGraphic(img);
		}
		
		public void bntEnregistrer3() {
			ImageView img = new ImageView("/img/Save.png");
			img.setFitWidth(75);
			img.setFitHeight(60);
			bntEnregistrerA.setGraphic(img);
		}
		public void btnImprimer() {
			ImageView img = new ImageView("/img/iconeSave.png");
			img.setFitWidth(75);
			img.setFitHeight(60);
			btnImprimerF.setGraphic(img);
			btnImprimerD.setGraphic(img);
			btnImprimerA.setGraphic(img);
		}
		
		public void InitialTableProduitsF() {
			columnNomF.setCellValueFactory(new PropertyValueFactory("nom"));
			columnPrixtotalF.setCellValueFactory(new PropertyValueFactory("prixtotal"));
			columnunitF.setCellValueFactory(new PropertyValueFactory("unit"));
			columnQteF.setCellValueFactory(new PropertyValueFactory("quantite"));
			columnColor.setCellValueFactory(new PropertyValueFactory("colorBnt"));
			
		}
		
		public void InitialTableProduitsD() {
		columnNomD.setCellValueFactory(new PropertyValueFactory("nom"));
		columnPrixtotalD.setCellValueFactory(new PropertyValueFactory("prixtotal"));
		columnunitD.setCellValueFactory(new PropertyValueFactory("unit"));
		columnQteD.setCellValueFactory(new PropertyValueFactory<ItemFacture, Integer>("quantite"));
		columnColorD.setCellValueFactory(new PropertyValueFactory("colorBnt"));
		
		}
		public void InitialTableProduitsA() {
		columnNomA.setCellValueFactory(new PropertyValueFactory("nom"));
		columnPrixtotalA.setCellValueFactory(new PropertyValueFactory("prixtotal"));
		columnunitA.setCellValueFactory(new PropertyValueFactory("unit"));
		columnQteA.setCellValueFactory(new PropertyValueFactory<ItemFacture, Integer>("quantite"));
		
		// columnQte.setEditable(true);
		}
		
		public void initListeProduitsA()
		{
			listeproduitsA.getItems().clear();
			LSN.clear();
			LSP.clear();
			LSP = produitsdb.load();
			for (Produits p : LSP)
				LSN.add(p.getNom());
			listeproduitsA.setItems(LSN);
		}
		
		public void initListeProduitsF()
		{
			listeproduitsF.getItems().clear();
			LSN.clear();
			LSP.clear();
			LSP = produitsdb.load();
			for (Produits p : LSP)
				LSN.add(p.getNom());
			listeproduitsF.setItems(LSN);
		}
		
		public void initListeProduitsD()
		{
			listeproduitsD.getItems().clear();
			LSN.clear();
			LSP.clear();
			LSP = produitsdb.load();
			for (Produits p : LSP)
				LSN.add(p.getNom());
			listeproduitsD.setItems(LSN);
		}

		

	    @FXML
	    void bntreductionClick(ActionEvent event) {
	    	updateFactReduction();
	    }
	    
	    @FXML
	    void bntreductionClickD(ActionEvent event) {
	    	updateFactReductionD();
	    }
	    
		public void	updateFactReduction() {
			Double tva = 7.7;
			Double ttn = 0.0, tv = 0.0, ttb = 0.0, ttr = 0.0;
			if(isInteger(nbrreduct.getText())) {
				for (ItemFacture i : tableproduitsF.getItems()) {
					if(i.getPrixtotal() != null) {
						ttn += i.getPrixtotal();
						
					}
				}
				
				ttr = ((ttn * Integer.parseInt(nbrreduct.getText()))/ 100);
				tv = ((ttn - ttr) * tva) / 100 ;
				ttb = (ttn - ttr) + tv;
				totalnetF.setText(String.format("%.2f",ttn));
				tvaF.setText(String.format("%.2f",tv));
				reduction.setText(String.format("%.2f",-ttr));
				totalbrutF.setText(String.format("%.2f",ttb ));
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
	     		alert.setTitle("Information");
	     		alert.setHeaderText("Taux de réduction");
	     		alert.setContentText("Il faut inserer un taux de réduction pas de texte ! ");
	     		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	     		stage.getIcons().add(new Image("/img/its_icoTR.png"));
	     		alert.showAndWait();
			}
			
		}
		
		public void	updateFactReductionD() {
			Double tva = 7.7;
			Double ttn = 0.0, tv = 0.0, ttb = 0.0, ttr = 0.0;
			if(isInteger(nbrreductD.getText())) {
				for (ItemFacture i : tableproduitsD.getItems()) {
					if(i.getTva() != null && i.getPrixtotal()!= null) {
						ttn += i.getPrixtotal();
						
					}
				}
				ttr = ((ttn * Integer.parseInt(nbrreductD.getText()))/ 100);
				tv = ((ttn - ttr) * tva) / 100 ;
				ttb = (ttn - ttr) + tv;
				totalnetD.setText(String.format("%.2f",ttn));
				tvaD.setText(String.format("%.2f",tv));
				reductiond.setText(String.format("%.2f",-ttr));
				totalbrutD.setText(String.format("%.2f",ttb));
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
	     		alert.setTitle("Information");
	     		alert.setHeaderText("Taux de réduction");
	     		alert.setContentText("Il faut inserer un taux de réduction pas de texte ! ");
	     		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	     		stage.getIcons().add(new Image("/img/its_icoTR.png"));
	     		alert.showAndWait();
			}
			
		}
		
		public static boolean isInteger(String s) {
		    try { 
		        Integer.parseInt(s); 
		    } catch(NumberFormatException e) { 
		        return false; 
		    } catch(NullPointerException e) {
		        return false;
		    }
		    // only got here if we didn't return false
		    return true;
		}
		
		
		public void updateFactDetails() {
			Double ttn = 0.0, tv = 0.0, ttb = 0.0;
			for (ItemFacture i : tableproduitsF.getItems()) {
				if(i.getTva() != null) {
					ttn += i.getPrixtotal();
					tv += ((i.getTva() * i.getPrixtotal() ) / 100);
				}
				
			}
			//round(tv, 2);
			ttb = ttn + tv;
			totalnetF.setText(String.format("%.2f",ttn));
			reduction.setText(String.format("%.2f",-0.00));
			tvaF.setText(String.format("%.2f",tv));
			totalbrutF.setText(String.format("%.2f",ttb));

		}
		public void updateDevtDetails() {
			Double ttn = 0.0, tv = 0.0, ttb = 0.0;
			for (ItemFacture i : tableproduitsD.getItems()) {
				if(i.getTva() != null && i.getPrixtotal()!= null) {
					ttn += i.getPrixtotal();
					tv += ((i.getTva() * i.getPrixtotal()) / 100);
				}	
			}
			ttb = ttn + tv;
			totalnetD.setText(String.format("%.2f",ttn));
			tvaD.setText(String.format("%.2f",tv));
			reductiond.setText(String.format("%.2f",-0.00));
			totalbrutD.setText(String.format("%.2f",ttb));


		}
		public void updateAcomptDetails() {
			Double ttn = 0.0, tv = 0.0, ttb = 0.0;
			for (ItemFacture i : tableproduitsA.getItems()) {
				if(i.getTva() != null) {
					ttn += i.getPrixtotal();
					tv += ((i.getTva() * i.getPrixtotal()) / 100);
				}	
			}
			ttb = ttn + tv;
			totalnetA.setText(String.format("%.2f",ttn));
			tvaA.setText(String.format("%.2f",tv));
			totalbrutA.setText(String.format("%.2f",ttb));
		}
		
		public void ClearandInitF() {
			qteF.clear();
			//combocategorie.getSelectionModel().select(0);
			//comboproduit.getSelectionModel().select(0);
			tableproduitsF.refresh();
			LSN.clear();
			LSP.clear();
			initListeProduitsF();
			updateFactDetails();
		}
		public void ClearandInitD() {
			qteD.clear();
			//combocategorie.getSelectionModel().select(0);
			//comboproduit.getSelectionModel().select(0);
			tableproduitsD.refresh();
			LSN.clear();
			LSP.clear();
			initListeProduitsD();
			updateDevtDetails();
		}

		public void ClearandInitA() {
			qteA.clear();
			//combocategorie.getSelectionModel().select(0);
			//comboproduit.getSelectionModel().select(0);
			tableproduitsA.refresh();
			LSN.clear();
			LSP.clear();
			initListeProduitsA();
			updateAcomptDetails();
		}

		public void SavePDFFac() {
			
			try
	        {
				String t = myreglage.getFactures();
				t=t.replace("\\","//" );
				String nameFile = t+"//Facture"+numFactF.getText()+".pdf";
				System.out.println("path   "+nameFile);
				String imgESE =   myreglage.getImage();
				File file = new File(nameFile );
		        file.getParentFile().mkdirs();
		        manipulatePdfFact(nameFile ,imgESE);
	        }
	        catch (Throwable t)
	        {
	            t.printStackTrace();
	        }
			
		}
		
		public void SavePDFDev() {
			
			try
	        {
				String t = myreglage.getFactures();
				t=t.replace("\\","//" );
				String nameFile = t+"//Devis"+numDevis.getText()+".pdf";
				System.out.println("path   "+nameFile);
				String imgESE =   myreglage.getImage();
				File file = new File(nameFile );
		        file.getParentFile().mkdirs();
		        manipulatePdfFactDev(nameFile ,imgESE);
	        }
	        catch (Throwable t)
	        {
	            t.printStackTrace();
	        }
			
		}
		
		public void SavePDFAcom() {
			
			try
	        {
				String t = myreglage.getFactures();
				t=t.replace("\\","//" );
				/*File file = new File(DEST);
		        file.getParentFile().mkdirs();

		        new FullPageTable().manipulatePdf(DEST);*/
				String nameFile = t+"//Acompte"+numAcompteA.getText()+".pdf";
				System.out.println("path   "+nameFile);
				String imgESE =   myreglage.getImage();
				File file = new File(nameFile );
		        file.getParentFile().mkdirs();
		        
		        manipulatePdfAcom(nameFile ,imgESE);
	        }
	        catch (Throwable t)
	        {
	            t.printStackTrace();
	        }
			
		}
			
	
	    protected void manipulatePdfFact(String dest, String imgESE) throws Exception {
	    	PdfWriter pdfWriter = new PdfWriter(dest);
	    	 PdfDocument pdfDoc = new PdfDocument(pdfWriter);
	    	
	         Document doc = new Document(pdfDoc, PageSize.A4);
	         doc.setMargins(5, 5, 5, 5);
	        
	         nbrFacture++;
    		// Creating an ImageData object       
    	      String logoits = myreglage.getImage();       
    	      ImageData data = ImageDataFactory.create(logoits);              
    	      ImageData imSE = ImageDataFactory.create(imgESE ); 
    	      // Creating an Image object        
    	      com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(data);  
    	      com.itextpdf.layout.element.Image imS = new com.itextpdf.layout.element.Image(imSE);   
    	      image.scaleToFit(200, 100);
    	      image.setFixedPosition(10,400);
    	      imS.scaleToFit(200, 100);
    	      imS.setFixedPosition(10,400);
    	      //imS.setFixedPosition(400, 900);
    	      // Adding image to the document       
    	      doc.add(image);              
    	      doc.add(imS);  
    	      
    		
    		
    		//------------------- HEADER  ----------------------------

    	    /*Text nomE = new Text("F-R Plâtrerie Peinture Sàrl").setFontSize(13);
    	    Text adresseE = new Text("\n"+"Croix du péage 1").setFontSize(13);
    	    Text adresseEp = new Text("\n"+"1029 Villars-ste-croix").setFontSize(13);
    	    Text telephoneE = new Text("\n"+"+41 21 525 66 64").setFontSize(13);
    	   
    	    Paragraph paraEntre = new Paragraph().add(nomE).add(adresseE).add(adresseEp).add(telephoneE);
    	    paraEntre.setFixedPosition(38, 650, 240);
    	    doc.add(paraEntre);*/
    	    
      		Text nom2 = new Text(nomclientF.getText()).setFontSize(12);
      		Text adr2 = new Text("\n"+adresseF.getText()).setFontSize(12);
      		//Text tel2 = new Text("\n"+telephoneF.getText()).setFontSize(13);
    		Paragraph paraheader = new Paragraph().add(nom2).add(adr2);
    		paraheader.setFixedPosition(350, 620, 240);
    		doc.add(paraheader); 
    		
    		Paragraph tvaN= new Paragraph("TVA N° CHE-268.681.922");
    		tvaN.setFontSize(12);
    		tvaN.setFixedPosition(38,650, 240);
     		doc.add(tvaN);
     		
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	        String dateF = LocalDateTime.now().format(formatter).toString();
	        Paragraph ds= new Paragraph("Villars st croix, le "+dateF);
    		ds.setFontSize(12);
    		ds.setFixedPosition(38, 580, 240);
    		doc.add(ds);
    		
    		String lp = "Facture N° "+dateF+"-"+facturedb.getPkF();
    		Paragraph lps= new Paragraph(lp+"\n\n");
    		lps.setBold();
    		lps.setFontSize(15);
    		//lps.setFontColor(
    		lps.setRelativePosition(200,270,0,20);
    		doc.add(lps); 

	 
	        // ---------------------------TABLE ----------------
    		//float[] columnWidths = { 5, 1, 1, 1, 1 };
    	
    	

            Table table = new Table(new float[5]).useAllAvailableWidth();
            
    	    
            table.setMargins(270, 8, 10, 8); 
            Cell cell = new Cell().add(new Paragraph("Désignation").setBold().setFontSize(12));
	        cell.setTextAlignment(TextAlignment.CENTER);
	        //cell.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell);
	        
	        Cell cell1 = new Cell().add(new Paragraph("Quantité").setBold().setFontSize(12));
	        cell1.setTextAlignment(TextAlignment.CENTER);
	        //cell1.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell1);
	        
	        Cell cell2 = new Cell().add(new Paragraph("Unité").setBold().setFontSize(12));
	        cell2.setTextAlignment(TextAlignment.CENTER);
	        //cell2.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell2);
	        
	        Cell cell3 = new Cell().add(new Paragraph("Prix Unitaire").setBold().setFontSize(12));
	        cell3.setTextAlignment(TextAlignment.CENTER);
	        //cell3.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell3);
	        
	        Cell cell4 = new Cell().add(new Paragraph("Prix Total").setBold().setFontSize(12));
	        cell4.setTextAlignment(TextAlignment.CENTER);
	        //cell4.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell4);
	         
	            
	        for (int i = 0; i < tableproduitsF.getItems().size(); i++) {
	        	PdfPage lastPage = pdfDoc.getLastPage();
	        	System.out.println("\nY:      "+doc.getPageEffectiveArea(new PageSize(lastPage.getPageSize())).getY()+ "\nHeight:           "+ doc.getPageEffectiveArea(new PageSize(lastPage.getPageSize())).getHeight());
	        	 Color colorset = null;
	        	 ItemFacture f= tableproduitsF.getItems().get(i);
	        	 
	        	 if(f.getTaille() == 0)
     				f.setTaille(11);
	        	 
	        	 if(f.getColor() == null)
	        		 f.setColor(colorset.BLACK);
	        	 
	        	 if(f.getColorS()!=null) {
	        		 Color c = Color.web(f.getColorS());
	        		 f.setColor(c);
	        	 }
	        	 double R = f.getColor().getRed() *255;
	        	 double G = f.getColor().getGreen() *255;
	        	 double B = f.getColor().getBlue() *255;
	        	 com.itextpdf.kernel.colors.Color myColor = new DeviceRgb((int)R , (int)G, (int)B);
	        	
	        	 if((i != tableproduitsF.getItems().size() -1) && f.getPrixunitaire() != null) {
	        		 
	        		
	        			System.out.print(doc.getTopMargin());
	        			 table.addCell(new Cell().add(new Paragraph(f.getNom())).setBorderBottom(Border.NO_BORDER)
		     						.setBorderLeft(new SolidBorder(0f))
		    						.setBorderRight(Border.NO_BORDER)
		    						.setBorderTop(Border.NO_BORDER).setFontColor(myColor).setFontSize(f.getTaille()));
		        			 
		        			 table.addCell(new Cell().add(new Paragraph(f.getQuantite().toString())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
		        			 table.addCell(new Cell().add(new Paragraph(f.getUnit().toString())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
		        			 table.addCell(new Cell().add(new Paragraph(String.format("%.2f",f.getPrixunitaire()))).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
		        			 table.addCell(new Cell().add(new Paragraph(String.format("%.2f",f.getPrixtotal()))).setBorderBottom(Border.NO_BORDER)
		        			 													  .setBorderLeft(Border.NO_BORDER)
		        			 													  .setBorderRight(new SolidBorder(0f))
		        			 													  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setFontColor(myColor).setFontSize(f.getTaille()));
	        		 }
					else if((i == tableproduitsF.getItems().size() -1) && f.getPrixunitaire() != null ) {
						 table.addCell(new Cell().add(new Paragraph(f.getNom())).setBorderBottom(new SolidBorder(0f))
								  .setBorderLeft(new SolidBorder(0f))
								  .setBorderRight(Border.NO_BORDER)
								  .setBorderTop(Border.NO_BORDER).setFontColor(myColor).setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph(f.getQuantite().toString())).setBorderBottom(new SolidBorder(0f) )
																	      .setBorderLeft(Border.NO_BORDER)
																		  .setBorderRight(Border.NO_BORDER)
																		  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph(f.getUnit().toString())).setBorderBottom(new SolidBorder(0f))
																      .setBorderLeft(Border.NO_BORDER)
																	  .setBorderRight(Border.NO_BORDER)
																	  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph(String.format("%.2f",f.getPrixunitaire()))).setBorderBottom(new SolidBorder(0f))
																						 .setBorderLeft(Border.NO_BORDER)
																						 .setBorderRight(Border.NO_BORDER)
																						 .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph(String.format("%.2f",f.getPrixtotal()))).setBorderBottom(new SolidBorder(0f))
																					  .setBorderLeft(Border.NO_BORDER)
																					  .setBorderRight(new SolidBorder(0f))
																					  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setFontColor(myColor).setFontSize(f.getTaille()));
					}
					else if((i == tableproduitsF.getItems().size() -1) && f.getPrixunitaire() == null) {
								table.addCell(new Cell().add(new Paragraph(f.getNom())).setBorderBottom(new SolidBorder(0f))
								  .setBorderLeft(new SolidBorder(0f))
								  .setBorderRight(Border.NO_BORDER)
								  .setBorderTop(Border.NO_BORDER).setFontColor(myColor).setBold().setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(new SolidBorder(0f) )
																	      .setBorderLeft(Border.NO_BORDER)
																		  .setBorderRight(Border.NO_BORDER)
																		  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(new SolidBorder(0f))
																      .setBorderLeft(Border.NO_BORDER)
																	  .setBorderRight(Border.NO_BORDER)
																	  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(new SolidBorder(0f))
																						 .setBorderLeft(Border.NO_BORDER)
																						 .setBorderRight(Border.NO_BORDER)
																						 .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(new SolidBorder(0f))
																					  .setBorderLeft(Border.NO_BORDER)
																					  .setBorderRight(new SolidBorder(0f))
																					  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setFontColor(myColor).setFontSize(f.getTaille()));
					}
	        	 else {
	        		 Paragraph  p;
	        		 
	        			 p = new Paragraph(f.getNom()).setFontSize(f.getTaille()).setBold().setFontColor(myColor);  
	        		 table.addCell(new Cell().add(p).setBorderBottom(Border.NO_BORDER)
							  .setBorderLeft(new SolidBorder(0f))
							  .setBorderRight(Border.NO_BORDER)
							  .setBorderTop(Border.NO_BORDER));
	        		 table.addCell(new Cell().add(new Paragraph(" ")).setBorder(Border.NO_BORDER));
	        		 table.addCell(new Cell().add(new Paragraph(" ")).setBorder(Border.NO_BORDER));
	        		 table.addCell(new Cell().add(new Paragraph(" ")).setBorder(Border.NO_BORDER));
	        		 table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER)
									   .setBorderLeft(Border.NO_BORDER)
						               .setBorderRight(new SolidBorder(0f))
						               .setBorderTop(Border.NO_BORDER));
	        	 }
	          
	            
	        }
	        
	        doc.add(table);
	        float[] columnWidths = {10, 10};
	        Table tableTot = new Table(UnitValue.createPercentArray(columnWidths));
	        
    		if((nbrreduct.getText() != "" && isInteger(nbrreduct.getText())) || (Double.parseDouble(reduction.getText().replace(",", ".")) < 0)) {
    	
        		tableTot.addCell(new Cell().add(new Paragraph("TOTAL : ")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
					.setBorderRight(Border.NO_BORDER)
					.setBorderTop(new SolidBorder(1f)).setBold());
        		tableTot.addCell(new Cell().add(new Paragraph(totalnetF.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
					.setBorderRight(new SolidBorder(1f))
					.setBorderTop(new SolidBorder(1f)).setTextAlignment(TextAlignment.RIGHT));
        		
        		tableTot.addCell(new Cell().add(new Paragraph("RABAIS "+nbrreduct.getText()+"% : ")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
					.setBorderRight(Border.NO_BORDER)
					.setBorderTop(Border.NO_BORDER).setBold());
        		tableTot.addCell(new Cell().add(new Paragraph(reduction.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
					.setBorderRight(new SolidBorder(1f))
					.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        	
        		tableTot.addCell(new Cell().add(new Paragraph("TVA 7.7% : ")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
					.setBorderRight(Border.NO_BORDER)
					.setBorderTop(Border.NO_BORDER).setBold());
        		tableTot.addCell(new Cell().add(new Paragraph(tvaF.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
					.setBorderRight(new SolidBorder(1f))
					.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        		
        		tableTot.addCell(new Cell().add(new Paragraph("TOTAL TTC : ")).setBorderBottom(new SolidBorder(1f))
						.setBorderLeft(new SolidBorder(1f))
					.setBorderRight(Border.NO_BORDER)
					.setBorderTop(Border.NO_BORDER).setBold());
        		tableTot.addCell(new Cell().add(new Paragraph(totalbrutF.getText())).setBorderBottom(new SolidBorder(1f))
						.setBorderLeft(Border.NO_BORDER)
					.setBorderRight(new SolidBorder(1f))
					.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        		tableTot.setMarginLeft(410);
        		
    		} else {
    			tableTot.addCell(new Cell().add(new Paragraph("TOTAL : ")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
					.setBorderRight(Border.NO_BORDER)
					.setBorderTop(new SolidBorder(1f)).setBold());
        		tableTot.addCell(new Cell().add(new Paragraph(totalnetF.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
					.setBorderRight(new SolidBorder(1f))
					.setBorderTop(new SolidBorder(1f)).setTextAlignment(TextAlignment.RIGHT));
   	
        		tableTot.addCell(new Cell().add(new Paragraph("TVA 7.7% : ")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
					.setBorderRight(Border.NO_BORDER)
					.setBorderTop(Border.NO_BORDER).setBold());
        		tableTot.addCell(new Cell().add(new Paragraph(tvaF.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
					.setBorderRight(new SolidBorder(1f))
					.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        		
        		tableTot.addCell(new Cell().add(new Paragraph("TOTAL TTC : ")).setBorderBottom(new SolidBorder(1f))
						.setBorderLeft(new SolidBorder(1f))
					.setBorderRight(Border.NO_BORDER)
					.setBorderTop(Border.NO_BORDER).setBold());
        		tableTot.addCell(new Cell().add(new Paragraph(totalbrutF.getText())).setBorderBottom(new SolidBorder(1f))
						.setBorderLeft(Border.NO_BORDER)
					.setBorderRight(new SolidBorder(1f))
					.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        		tableTot.setMarginLeft(423);
    		}

    		Text esp1 = new Text("\n     ").setFontSize(12);
    		Text esp2 = new Text("\n     ").setFontSize(12);
    		Text esp3 = new Text("\n     ").setFontSize(12);
    		Paragraph espace = new Paragraph().add(esp1).add(esp2).add(esp3);//.setRelativePosition(0,300,0,0);
    		doc.add(espace);
    		doc.add(tableTot);
    		Paragraph rqfooter3 = new Paragraph(remarqueF.getText()); 
    		rqfooter3.setFontSize(14);
    		//rqfooter3.setRelativePosition(0,260,0,0);
    		doc.add(rqfooter3);
    	
    	
    		Text banque = new Text("\nVersement sur le compte UBS\nF-R Plâtrerie Peinture SÂRL").setFontSize(12);
    		Text iban = new Text("\nIBAN: CH79 0024 3243 5355 7401 M").setFontSize(12);
    		Paragraph parBanque = new Paragraph().add(banque).add(iban);//.setRelativePosition(0,300,0,0);
    		doc.add(parBanque);
    		
    		
	        doc.close();
	      
	    }
	    
	    
		
	    protected void manipulatePdfFactDev(String dest, String imgESE) throws Exception {
	    	 PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
	         Document doc = new Document(pdfDoc, new PageSize(595, 842));
	         doc.setMargins(0, 0, 0, 0);
	         nbrFacture++;
    		// Creating an ImageData object       
    	      String logoits = myreglage.getImage();       
    	      ImageData data = ImageDataFactory.create(logoits);              
    	      ImageData imSE = ImageDataFactory.create(imgESE ); 
    	      // Creating an Image object        
    	      com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(data);  
    	      com.itextpdf.layout.element.Image imS = new com.itextpdf.layout.element.Image(imSE);   
    	      image.scaleToFit(400, 200);
    	      image.setFixedPosition(100,700);
    	      imS.scaleToFit(400, 200);
    	      imS.setFixedPosition(100,700);
    	      //imS.setFixedPosition(400, 900);
    	      // Adding image to the document       
    	      doc.add(image);              
    	      doc.add(imS);  
    	      
    		
    		
    		//------------------- HEADER  ----------------------------

    	    /*Text nomE = new Text("F-R Plâtrerie Peinture Sàrl").setFontSize(13);
    	    Text adresseE = new Text("\n"+"Croix du péage 1").setFontSize(13);
    	    Text adresseEp = new Text("\n"+"1029 Villars-ste-croix").setFontSize(13);
    	    Text telephoneE = new Text("\n"+"+41789115346").setFontSize(13);
    	    
    	    Paragraph paraEntre = new Paragraph().add(nomE).add(adresseE).add(adresseEp).add(telephoneE);
    	    paraEntre.setFixedPosition(38, 650, 240);
    	   // doc.add(paraEntre);*/
    	    
      		Text nom2 = new Text(nomclientD.getText()).setFontSize(12);
      		Text adr2 = new Text("\n"+adresseD.getText()).setFontSize(12);
      		//Text tel2 = new Text("\n"+telephoneD.getText()).setFontSize(13);
    		Paragraph paraheader = new Paragraph().add(nom2).add(adr2);
    		paraheader.setFixedPosition(350, 620, 240);
    		doc.add(paraheader); 
    		
    		Paragraph tvaN= new Paragraph("TVA N° CHE-268.681.922");
    		tvaN.setFontSize(12);
    		tvaN.setFixedPosition(38,650, 240);
     		doc.add(tvaN);
     		
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	        String dateF = LocalDateTime.now().format(formatter).toString();
	        Paragraph ds= new Paragraph("Villars st croix, le "+dateF);
    		ds.setFontSize(12);
    		ds.setFixedPosition(38, 580, 240);
    		doc.add(ds);
    	
    		
    		String lp = "Devis N° "+dateF+"-"+facturedb.getPkD();
    		Paragraph lps= new Paragraph(lp+"\n\n");
    		lps.setFontSize(15);
    		lps.setBold();
    		lps.setRelativePosition(200,270,0,20);
    		doc.add(lps); 

	 
	        // ---------------------------TABLE ----------------
    		//float[] columnWidths = { 5, 1, 1, 1, 1 };
    	
    		Color Color = null;
    		Color = Color.BLACK;
        	 
        	 double R1 = Color.getRed() *255;
        	 double G1 = Color.getGreen() *255;
        	 double B1 = Color.getBlue() *255;
        	 com.itextpdf.kernel.colors.Color myColor1 = new DeviceRgb((int)R1 , (int)G1, (int)B1);

            Table table = new Table(new float[5]).useAllAvailableWidth();
            
    	    
            table.setMargins(270, 8, 10, 8); 
            Cell cell = new Cell().add(new Paragraph("Désignation").setBold().setFontSize(12).setFontColor(myColor1));
	        cell.setTextAlignment(TextAlignment.CENTER);
	        //cell.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell);
	        
	        Cell cell1 = new Cell().add(new Paragraph("Quantité").setBold().setFontSize(12).setFontColor(myColor1));
	        cell1.setTextAlignment(TextAlignment.CENTER);
	        //cell1.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell1);
	        
	        Cell cell2 = new Cell().add(new Paragraph("Unité").setBold().setFontSize(12).setFontColor(myColor1));
	        cell2.setTextAlignment(TextAlignment.CENTER);
	        //cell2.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell2);
	        
	        Cell cell3 = new Cell().add(new Paragraph("Prix Unitaire").setBold().setFontSize(12).setFontColor(myColor1));
	        cell3.setTextAlignment(TextAlignment.CENTER);
	        //cell3.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell3);
	        
	        Cell cell4 = new Cell().add(new Paragraph("Prix Total").setBold().setFontSize(12).setFontColor(myColor1));
	        cell4.setTextAlignment(TextAlignment.CENTER);
	        //cell4.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell4);
	         
	         
	            
	        for (int i = 0; i < tableproduitsD.getItems().size(); i++) {
	        	
	        	 Color colorset = null;
	        	 ItemFacture f= tableproduitsD.getItems().get(i);
	        	 
	        	 if(f.getTaille() == 0)
     				f.setTaille(11);
	        	 
	        	 if(f.getColor() == null)
	        		 f.setColor(colorset.BLACK);
	        	 
	        	 if(f.getColorS()!=null) {
	        		 Color c = Color.web(f.getColorS());
	        		 f.setColor(c);
	        	 }
	        	 double R = f.getColor().getRed() *255;
	        	 double G = f.getColor().getGreen() *255;
	        	 double B = f.getColor().getBlue() *255;
	        	 com.itextpdf.kernel.colors.Color myColor = new DeviceRgb((int)R , (int)G, (int)B);
	        	
	        	 if((i != tableproduitsD.getItems().size() -1) && f.getPrixunitaire() != null) {
	        		 
	        		
	        			System.out.print(doc.getTopMargin());
	        			 table.addCell(new Cell().add(new Paragraph(f.getNom())).setBorderBottom(Border.NO_BORDER)
		     						.setBorderLeft(new SolidBorder(0f))
		    						.setBorderRight(Border.NO_BORDER)
		    						.setBorderTop(Border.NO_BORDER).setFontColor(myColor).setFontSize(f.getTaille()));
		        			 
		        			 table.addCell(new Cell().add(new Paragraph(f.getQuantite().toString())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
		        			 table.addCell(new Cell().add(new Paragraph(f.getUnit().toString())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
		        			 table.addCell(new Cell().add(new Paragraph(String.format("%.2f",f.getPrixunitaire()))).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
		        			 table.addCell(new Cell().add(new Paragraph(String.format("%.2f",f.getPrixtotal()))).setBorderBottom(Border.NO_BORDER)
		        			 													  .setBorderLeft(Border.NO_BORDER)
		        			 													  .setBorderRight(new SolidBorder(0f))
		        			 													  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setFontColor(myColor).setFontSize(f.getTaille()));
	        		 }
					else if((i == tableproduitsD.getItems().size() -1) && f.getPrixunitaire() != null ) {
						 table.addCell(new Cell().add(new Paragraph(f.getNom())).setBorderBottom(new SolidBorder(0f))
								  .setBorderLeft(new SolidBorder(0f))
								  .setBorderRight(Border.NO_BORDER)
								  .setBorderTop(Border.NO_BORDER).setFontColor(myColor).setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph(f.getQuantite().toString())).setBorderBottom(new SolidBorder(0f) )
																	      .setBorderLeft(Border.NO_BORDER)
																		  .setBorderRight(Border.NO_BORDER)
																		  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph(f.getUnit().toString())).setBorderBottom(new SolidBorder(0f))
																      .setBorderLeft(Border.NO_BORDER)
																	  .setBorderRight(Border.NO_BORDER)
																	  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph(String.format("%.2f",f.getPrixunitaire()))).setBorderBottom(new SolidBorder(0f))
																						 .setBorderLeft(Border.NO_BORDER)
																						 .setBorderRight(Border.NO_BORDER)
																						 .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph(String.format("%.2f",f.getPrixtotal()))).setBorderBottom(new SolidBorder(0f))
																					  .setBorderLeft(Border.NO_BORDER)
																					  .setBorderRight(new SolidBorder(0f))
																					  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setFontColor(myColor).setFontSize(f.getTaille()));
					}
					else if((i == tableproduitsD.getItems().size() -1) && f.getPrixunitaire() == null) {
								table.addCell(new Cell().add(new Paragraph(f.getNom())).setBorderBottom(new SolidBorder(0f))
								  .setBorderLeft(new SolidBorder(0f))
								  .setBorderRight(Border.NO_BORDER)
								  .setBorderTop(Border.NO_BORDER).setFontColor(myColor).setBold().setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(new SolidBorder(0f) )
																	      .setBorderLeft(Border.NO_BORDER)
																		  .setBorderRight(Border.NO_BORDER)
																		  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(new SolidBorder(0f))
																      .setBorderLeft(Border.NO_BORDER)
																	  .setBorderRight(Border.NO_BORDER)
																	  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(new SolidBorder(0f))
																						 .setBorderLeft(Border.NO_BORDER)
																						 .setBorderRight(Border.NO_BORDER)
																						 .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontColor(myColor).setFontSize(f.getTaille()));
								 table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(new SolidBorder(0f))
																					  .setBorderLeft(Border.NO_BORDER)
																					  .setBorderRight(new SolidBorder(0f))
																					  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setFontColor(myColor).setFontSize(f.getTaille()));
					}
	        	 else {
	        		 Paragraph  p;
	        		 
	        			 p = new Paragraph(f.getNom()).setFontSize(f.getTaille()).setBold().setFontColor(myColor);  
	        		 table.addCell(new Cell().add(p).setBorderBottom(Border.NO_BORDER)
							  .setBorderLeft(new SolidBorder(0f))
							  .setBorderRight(Border.NO_BORDER)
							  .setBorderTop(Border.NO_BORDER));
	        		 table.addCell(new Cell().add(new Paragraph(" ")).setBorder(Border.NO_BORDER));
	        		 table.addCell(new Cell().add(new Paragraph(" ")).setBorder(Border.NO_BORDER));
	        		 table.addCell(new Cell().add(new Paragraph(" ")).setBorder(Border.NO_BORDER));
	        		 table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER)
									   .setBorderLeft(Border.NO_BORDER)
						               .setBorderRight(new SolidBorder(0f))
						               .setBorderTop(Border.NO_BORDER));
	        	 }
	          
	            
	        }
	     
	        doc.add(table);
	      
	      
    		float[] columnWidths = {10, 10};
 	        Table tableTot = new Table(UnitValue.createPercentArray(columnWidths));
 	        
     		if((nbrreductD.getText() != "" && isInteger(nbrreductD.getText())) || (Double.parseDouble(reductiond.getText().replace(",", ".")) < 0)) {
     	
         		tableTot.addCell(new Cell().add(new Paragraph("TOTAL : ")).setBorderBottom(Border.NO_BORDER)
 						.setBorderLeft(new SolidBorder(1f))
 					.setBorderRight(Border.NO_BORDER)
 					.setBorderTop(new SolidBorder(1f)).setBold());
         		tableTot.addCell(new Cell().add(new Paragraph(totalnetD.getText())).setBorderBottom(Border.NO_BORDER)
 						.setBorderLeft(Border.NO_BORDER)
 					.setBorderRight(new SolidBorder(1f))
 					.setBorderTop(new SolidBorder(1f)).setTextAlignment(TextAlignment.RIGHT));
         		
         		tableTot.addCell(new Cell().add(new Paragraph("RABAIS "+nbrreductD.getText()+"% : ")).setBorderBottom(Border.NO_BORDER)
 						.setBorderLeft(new SolidBorder(1f))
 					.setBorderRight(Border.NO_BORDER)
 					.setBorderTop(Border.NO_BORDER).setBold());
         		tableTot.addCell(new Cell().add(new Paragraph(reductiond.getText())).setBorderBottom(Border.NO_BORDER)
 						.setBorderLeft(Border.NO_BORDER)
 					.setBorderRight(new SolidBorder(1f))
 					.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
         	
         		tableTot.addCell(new Cell().add(new Paragraph("TVA 7.7% : ")).setBorderBottom(Border.NO_BORDER)
 						.setBorderLeft(new SolidBorder(1f))
 					.setBorderRight(Border.NO_BORDER)
 					.setBorderTop(Border.NO_BORDER).setBold());
         		tableTot.addCell(new Cell().add(new Paragraph(tvaD.getText())).setBorderBottom(Border.NO_BORDER)
 						.setBorderLeft(Border.NO_BORDER)
 					.setBorderRight(new SolidBorder(1f))
 					.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
         		
         		tableTot.addCell(new Cell().add(new Paragraph("TOTAL TTC : ")).setBorderBottom(new SolidBorder(1f))
 						.setBorderLeft(new SolidBorder(1f))
 					.setBorderRight(Border.NO_BORDER)
 					.setBorderTop(Border.NO_BORDER).setBold());
         		tableTot.addCell(new Cell().add(new Paragraph(totalbrutD.getText())).setBorderBottom(new SolidBorder(1f))
 						.setBorderLeft(Border.NO_BORDER)
 					.setBorderRight(new SolidBorder(1f))
 					.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
         		tableTot.setMarginLeft(410);
         		
     		} else {
     			tableTot.addCell(new Cell().add(new Paragraph("TOTAL : ")).setBorderBottom(Border.NO_BORDER)
 						.setBorderLeft(new SolidBorder(1f))
 					.setBorderRight(Border.NO_BORDER)
 					.setBorderTop(new SolidBorder(1f)).setBold());
         		tableTot.addCell(new Cell().add(new Paragraph(totalnetD.getText())).setBorderBottom(Border.NO_BORDER)
 						.setBorderLeft(Border.NO_BORDER)
 					.setBorderRight(new SolidBorder(1f))
 					.setBorderTop(new SolidBorder(1f)).setTextAlignment(TextAlignment.RIGHT));
    	
         		tableTot.addCell(new Cell().add(new Paragraph("TVA 7.7% : ")).setBorderBottom(Border.NO_BORDER)
 						.setBorderLeft(new SolidBorder(1f))
 					.setBorderRight(Border.NO_BORDER)
 					.setBorderTop(Border.NO_BORDER).setBold());
         		tableTot.addCell(new Cell().add(new Paragraph(tvaD.getText())).setBorderBottom(Border.NO_BORDER)
 						.setBorderLeft(Border.NO_BORDER)
 					.setBorderRight(new SolidBorder(1f))
 					.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
         		
         		tableTot.addCell(new Cell().add(new Paragraph("TOTAL TTC : ")).setBorderBottom(new SolidBorder(1f))
 						.setBorderLeft(new SolidBorder(1f))
 					.setBorderRight(Border.NO_BORDER)
 					.setBorderTop(Border.NO_BORDER).setBold());
         		tableTot.addCell(new Cell().add(new Paragraph(totalbrutD.getText())).setBorderBottom(new SolidBorder(1f))
 						.setBorderLeft(Border.NO_BORDER)
 					.setBorderRight(new SolidBorder(1f))
 					.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
         		tableTot.setMarginLeft(423);
     		}

     		
     		doc.add(tableTot);
    		Paragraph rqfooter3 = new Paragraph(remarqueD.getText()); 
    		rqfooter3.setFontSize(14);
    		//rqfooter3.setRelativePosition(0,260,0,0);
    		doc.add(rqfooter3);
    		Text banque1 = new Text(" ");
    		Text banque2 = new Text(" ");
    		
    		Text banque = new Text("\n"+"\n"+"Nous espérons que notre offre, calculée au meilleur prix, retiendra toute votre attention et que vous serez en mesure de nous en confier l'exécution, à laquelle nous vouerons tous nos soins." + 
	        		"\n" + 
	        		"Dans cet espoir et restant à votre entière disposition pour tous renseignements, nous vous présentons, Madame, Monsieur, nos plus respectueuses salutations.").setFontSize(12);
    		Paragraph parBanque = new Paragraph().add(banque).add(banque1).add(banque2);//.setRelativePosition(0,300,0,0);
    		doc.add(parBanque);
	        doc.close();
	        
	        
	      
	    }
	    
	    
	    protected void manipulatePdfAcom(String dest, String imgESE) throws Exception {
	    	 PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
	         Document doc = new Document(pdfDoc, new PageSize(595, 842));
	         doc.setMargins(0, 0, 0, 0);
	         nbrFacture++;
    		// Creating an ImageData object       
    	      String logoits = myreglage.getImage();       
    	      ImageData data = ImageDataFactory.create(logoits);              
    	      ImageData imSE = ImageDataFactory.create(imgESE ); 
    	      // Creating an Image object        
    	      com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(data);  
    	      com.itextpdf.layout.element.Image imS = new com.itextpdf.layout.element.Image(imSE);   
    	      image.scaleToFit(400, 200);
    	      image.setFixedPosition(100,700);
    	      imS.scaleToFit(400, 200);
    	      imS.setFixedPosition(100,700);
    	      //imS.setFixedPosition(400, 900);
    	      // Adding image to the document       
    	      doc.add(image);              
    	      doc.add(imS);  
    	      
    		
    		
    		//------------------- HEADER  ----------------------------

    	   /* Text nomE = new Text("F-R Plâtrerie Peinture Sàrl").setFontSize(13);
    	    Text adresseE = new Text("\n"+"Croix du péage 1").setFontSize(13);
    	    Text adresseEp = new Text("\n"+"1029 Villars-ste-croix").setFontSize(13);
    	    Text telephoneE = new Text("\n"+"+41 21 525 66 64").setFontSize(13);
    	    
    	    Paragraph paraEntre = new Paragraph().add(nomE).add(adresseE).add(adresseEp).add(telephoneE);
    	    paraEntre.setFixedPosition(38, 650, 240);
    	    doc.add(paraEntre);*/
    	    
      		Text nom2 = new Text(nomclientA.getText()).setFontSize(12);
      		Text adr2 = new Text("\n"+adresseA.getText()).setFontSize(12);
    		Paragraph paraheader = new Paragraph().add(nom2).add(adr2);
    		paraheader.setFixedPosition(400, 620, 240);
    		doc.add(paraheader); 
    		
    		Paragraph tvaN= new Paragraph("TVA N° CHE-268.681.922");
    		tvaN.setFontSize(12);
    		tvaN.setFixedPosition(38,650, 240);
     		doc.add(tvaN);
     		
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	        String dateF = LocalDateTime.now().format(formatter).toString();
	        Paragraph ds= new Paragraph("Villars st croix, le "+dateF);
    		//ds.setBold();
    		ds.setFontSize(12);
    		ds.setFixedPosition(38, 580, 240);
    		doc.add(ds);
    		
    		String lp = "Acompte N° "+dateF+"-"+nbrFacture;
    		Paragraph lps= new Paragraph(lp+"\n\n");
    		lps.setBold();
    		lps.setFontSize(15);
    		//lps.setFontColor(
    		lps.setRelativePosition(200,270,0,20);
    		doc.add(lps); 

	 
	        // ---------------------------TABLE ----------------
    		//float[] columnWidths = { 5, 1, 1, 1, 1 };
    	
    		

            Table table = new Table(new float[5]).useAllAvailableWidth();
            
    	    
            table.setMargins(270, 8, 10, 8); 
            Cell cell = new Cell().add(new Paragraph("Désignation").setBold().setFontSize(12));
	        cell.setTextAlignment(TextAlignment.CENTER);
	        //cell.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell);
	        
	        Cell cell1 = new Cell().add(new Paragraph("Quantité").setBold().setFontSize(12));
	        cell1.setTextAlignment(TextAlignment.CENTER);
	        //cell1.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell1);
	        
	        Cell cell2 = new Cell().add(new Paragraph("Unité").setBold().setFontSize(12));
	        cell2.setTextAlignment(TextAlignment.CENTER);
	        //cell2.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell2);
	        
	        Cell cell3 = new Cell().add(new Paragraph("Prix Unitaire").setBold().setFontSize(12));
	        cell3.setTextAlignment(TextAlignment.CENTER);
	        //cell3.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell3);
	        
	        Cell cell4 = new Cell().add(new Paragraph("Prix Total").setBold().setFontSize(12));
	        cell4.setTextAlignment(TextAlignment.CENTER);
	        //cell4.setBackgroundColor(new DeviceRgb(0,131,228));
	        table.addCell(cell4);
	         
	         
	            
	        for (int i = 0; i < tableproduitsA.getItems().size(); i++) {
	        	 ItemFacture f= tableproduitsA.getItems().get(i);
	        	 if(f.getPrixunitaire() != null) {
	        		 if( i != (tableproduitsA.getItems().size() -1)) {
	        			 table.addCell(new Cell().add(new Paragraph(f.getNom())).setBorderBottom(Border.NO_BORDER)
	     						.setBorderLeft(new SolidBorder(0f))
	    						.setBorderRight(Border.NO_BORDER)
	    						.setBorderTop(Border.NO_BORDER));
	        			 
	        			 table.addCell(new Cell().add(new Paragraph(f.getQuantite().toString())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
	        			 table.addCell(new Cell().add(new Paragraph(f.getUnit().toString())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
	        			 table.addCell(new Cell().add(new Paragraph(String.format("%.2f",f.getPrixunitaire()))).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
	        			 table.addCell(new Cell().add(new Paragraph(String.format("%.2f",f.getPrixtotal()))).setBorderBottom(Border.NO_BORDER)
	        			 													  .setBorderLeft(Border.NO_BORDER)
	        			 													  .setBorderRight(new SolidBorder(0f))
	        			 													  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	        		 }
					else if(i == (tableproduitsF.getItems().size() -1)) {
						 table.addCell(new Cell().add(new Paragraph(f.getNom())).setBorderBottom(new SolidBorder(0f))
						  .setBorderLeft(new SolidBorder(0f))
						  .setBorderRight(Border.NO_BORDER)
						  .setBorderTop(Border.NO_BORDER));
						 table.addCell(new Cell().add(new Paragraph(f.getQuantite().toString())).setBorderBottom(new SolidBorder(0f) )
															      .setBorderLeft(Border.NO_BORDER)
																  .setBorderRight(Border.NO_BORDER)
																  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
						 table.addCell(new Cell().add(new Paragraph(f.getUnit().toString())).setBorderBottom(new SolidBorder(0f))
														      .setBorderLeft(Border.NO_BORDER)
															  .setBorderRight(Border.NO_BORDER)
															  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
						 table.addCell(new Cell().add(new Paragraph(String.format("%.2f",f.getPrixunitaire()))).setBorderBottom(new SolidBorder(0f))
																				 .setBorderLeft(Border.NO_BORDER)
																				 .setBorderRight(Border.NO_BORDER)
																				 .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
						 table.addCell(new Cell().add(new Paragraph(String.format("%.2f",f.getPrixtotal()))).setBorderBottom(new SolidBorder(0f))
																			  .setBorderLeft(Border.NO_BORDER)
																			  .setBorderRight(new SolidBorder(0f))
																			  .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
					}
	        	 }
	        	 else {
	        		 Paragraph  p;
	        		 if(tailleTA.getSelectionModel().getSelectedItem() != null)
	        			 p = new Paragraph(f.getNom()).setUnderline().setFontSize(tailleTA.getSelectionModel().getSelectedItem()).setBold();  
	        		 else 
	        			 p = new Paragraph(f.getNom()).setUnderline().setFontSize(12).setBold();  
	        		 table.addCell(new Cell().add(p).setBorderBottom(Border.NO_BORDER)
							  .setBorderLeft(new SolidBorder(0f))
							  .setBorderRight(Border.NO_BORDER)
							  .setBorderTop(Border.NO_BORDER));
	        		 table.addCell(new Cell().add(new Paragraph(" ")).setBorder(Border.NO_BORDER));
	        		 table.addCell(new Cell().add(new Paragraph(" ")).setBorder(Border.NO_BORDER));
	        		 table.addCell(new Cell().add(new Paragraph(" ")).setBorder(Border.NO_BORDER));
	        		 table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER)
									   .setBorderLeft(Border.NO_BORDER)
						               .setBorderRight(new SolidBorder(0f))
						               .setBorderTop(Border.NO_BORDER));
	        	 }
	          
	            
	        }
	     
	        doc.add(table);
	      
	       
	        Text ttn1 = new Text("\nTOTAL :        ").setFontSize(12).setBold();
    		Text ttn2 = new Text(totalnetA.getText()).setFontSize(12);
    		Text tva1 = new Text("\nTVA 7.7% :    ").setFontSize(12).setBold();
    		Text tva2 = new Text(tvaA.getText()).setFontSize(12);
    		Text ttb1 = new Text("\nTOTAL TTC : ").setFontSize(12).setBold();
    		Text ttb2 = new Text(totalbrutA.getText()).setFontSize(12);
    	
   
    		Paragraph parfooter = new Paragraph().add(ttn1).add(ttn2).add(tva1).add(tva2).add(ttb1).add(ttb2);
    		parfooter.setMarginLeft(420);  //.setRelativePosition(380,260,0,0);
    		doc.add(parfooter);
    		

    		Paragraph rqfooter3 = new Paragraph(remarqueA.getText()); 
    		rqfooter3.setFontSize(14);
    		//rqfooter3.setRelativePosition(0,260,0,0);
    		doc.add(rqfooter3);
    		
    		Text banque = new Text("\nVersement sur le compte UBS\nF-R Plâtrerie Peinture SÂRL").setFontSize(12);
    		Text iban = new Text("\nIBAN: CH79 0024 3243 5355 7401 M").setFontSize(12);
    		Paragraph parBanque = new Paragraph().add(banque).add(iban);//.setRelativePosition(0,300,0,0);
    		doc.add(parBanque);
    		
    		
	        doc.close();
	      
	    }

		
		public void setReglage()
		{
	         Reglage r = dbreglage.getReglage(logincontrol.getId_utilisateur());
	         if (r != null)
	 		{
	 			myreglage=r;
	 		
	 		}
			
		}
		
		public void initialFacture() throws SQLException {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			String dateF = LocalDateTime.now().format(formatter).toString();
			int nbr = facturedb.getFactureLastId() ;
			numFactF.setText(dateF+"-"+nbr);
			// set textfield to numeric entry
			numFactF.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					String s = "";
					for (char c : newValue.toCharArray()) {
						if (((int) c >= 48 && (int) c <= 57 || (int) c == 46)) {
							s += c;
						}
					}
					//qteF.setText(s);
				}
			});
		
			/*tableproduitsF.getSelectionModel().selectedItemProperty().addListener(
					(ObservableValue<? extends ItemFacture> observable, ItemFacture oldValue, ItemFacture newValue) -> {
						if (newValue.getPrixunitaire() != null) {
							initListeProduitsF();
							int jj = listeproduitsF.getItems().indexOf(newValue.getNom());
							listeproduitsF.getSelectionModel().select(jj);
							listeproduitsF.getFocusModel().focus(jj);
							listeproduitsF.scrollTo(jj);
							qteF.setText(newValue.getQuantite().toString());		
						}

					});*/
		}
		
		public void initialDevis() {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				String dateF = LocalDateTime.now().format(formatter).toString();
				int nbr = facturedb.getDevisLastId() + 1;
				numDevis.setText(dateF+"-"+nbr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// set textfield to numeric entry
			qteD.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					String s = "";
					for (char c : newValue.toCharArray()) {
						if (((int) c >= 48 && (int) c <= 57 || (int) c == 46)) {
							s += c;
						}
					}
					//qteD.setText(s);
				}
			});
		
			tableproduitsD.getSelectionModel().selectedItemProperty().addListener(
					(ObservableValue<? extends ItemFacture> observable, ItemFacture oldValue, ItemFacture newValue) -> {
						if (newValue != null) {
							initListeProduitsD();
							int jj = listeproduitsD.getItems().indexOf(newValue.getNom());
							listeproduitsD.getSelectionModel().select(jj);
							listeproduitsD.getFocusModel().focus(jj);
							listeproduitsD.scrollTo(jj);
							qteD.setText(newValue.getQuantite().toString());		
						}

					});
		}
		
		public void initialAcompte() {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				String dateF = LocalDateTime.now().format(formatter).toString();
				int nbr = facturedb.getFactureLastId() ;
				numAcompteA.setText(dateF+"-"+nbr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// set textfield to numeric entry
			qteA.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					String s = "";
					for (char c : newValue.toCharArray()) {
						if (((int) c >= 48 && (int) c <= 57 || (int) c == 46)) {
							s += c;
						}
					}
					//qteA.setText(s);
				}
			});
		
			tableproduitsA.getSelectionModel().selectedItemProperty().addListener(
					(ObservableValue<? extends ItemFacture> observable, ItemFacture oldValue, ItemFacture newValue) -> {
						if (newValue != null) {
							initListeProduitsA();
							int jj = listeproduitsA.getItems().indexOf(newValue.getNom());
							listeproduitsA.getSelectionModel().select(jj);
							listeproduitsA.getFocusModel().focus(jj);
							listeproduitsA.scrollTo(jj);
							qteA.setText(newValue.getQuantite().toString());		
						}

					});
			
		}
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			InitialTableProduitsF();
			InitialTableProduitsD();
			InitialTableProduitsA();
			initListeProduitsF();
			initListeProduitsD();
			initListeProduitsA();
			btnImprimer();
			setReglage();
			try {
				initialFacture();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			initialDevis();
			initialAcompte();
			btnImprimer1();
			btnImprimer2();
			btnImprimer3();
			bntEnregistrer1();
			bntEnregistrer2();
			bntEnregistrer3();
			
			for(int i = 12; i < 20; i++) {
				tailleTF.getItems().add(i);
				tailleTD.getItems().add(i);
				tailleTA.getItems().add(i);
			}
			
			}
		
}

