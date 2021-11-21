package Fenetre;

import java.awt.Desktop;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.swing.BorderFactory;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.text.PDFTextStripper;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfViewerPreferences;
import com.itextpdf.kernel.pdf.PdfViewerPreferences.PdfViewerPreferencesConstants;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.hyphenation.TernaryTree.Iterator;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
//import com.qoppa.pdfViewerFX.PDFViewer;
import com.itextpdf.text.pdf.parser.TextMarginFinder;
import com.qoppa.pdfText.PDFText;

import DataBase.ReglageDB;
import DataBase.VenteDB;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import   javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import objects.Produits;
import objects.Reglage;
import objects.Vente;


public class CashControl extends BorderPane implements Initializable {

	ObservableList<Vente> vente = FXCollections.observableArrayList();
	private Connection DB = null;
	VenteDB dbVente = null;
	Double tva = 0.0;
	CaisseControl caisse = null;
	com.itextpdf.kernel.geom.PageSize pagesizeDoc;
	int id;
	int numeroVente;
	int quantite;
	Double tvaP = 0.0;
	Double tvaC = 0.0;
	Double prix;
	Double somme = 0.0;
	public static int nbr = 0;


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
	private Button butdeux;

	@FXML
	private Button butSix;

	@FXML
	private Button buthuit;

	@FXML
	private Button butZero;

	@FXML
	private Button butcinq;

	@FXML
	private Button butPoint;

	@FXML
	private Button butCL;

	@FXML
	private Button butQuatre;

	@FXML
	private Button butNeuf;

	@FXML
	private Button butUn;

	@FXML
	private Button butTrois;

	@FXML
	private Button butSept;

	@FXML
	private Button butSUP;

	@FXML
	private Button butEnter;

	@FXML
	private TextField Afficheur;
	
	private Reglage myreglage =null;
	private LoginControl logincontrol = null;
	private ReglageDB dbreglage = null;

	private static boolean jobRunning = true;
	 
	public CashControl(CaisseControl caisseControl, Connection db) {
		this.DB = db;
		caisse = caisseControl;
		dbVente = new VenteDB(DB);
		try {
			logincontrol = new LoginControl(db);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbreglage = new ReglageDB(db);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cash.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

	}

	@FXML
	void butBillet100Clicked(ActionEvent event) {
		Afficheur.clear();
		Afficheur.setText(Afficheur.getText() + "100");
	}

	@FXML
	void butBillet10Clicked(ActionEvent event) {
		Afficheur.clear();
		Afficheur.setText(Afficheur.getText() + "10");
	}

	@FXML
	void butBillet200Clicked(ActionEvent event) {
		Afficheur.clear();
		Afficheur.setText(Afficheur.getText() + "200");
	}

	@FXML
	void butBillet20Clicked(ActionEvent event) {
		Afficheur.clear();
		Afficheur.setText(Afficheur.getText() + "20");
	}

	@FXML
	void butBillet50Clicked(ActionEvent event) {
		Afficheur.clear();
		Afficheur.setText(Afficheur.getText() + "50");
	}

	@FXML
	void bntClClicked(ActionEvent event) {
		Afficheur.clear();
	}

	@FXML
	void bnt0Clicked(ActionEvent event) {
		Afficheur.setText(Afficheur.getText() + "0");
	}

	@FXML
	void bnt1Clicked(ActionEvent event) {
		Afficheur.setText(Afficheur.getText() + "1");
	}

	@FXML
	void bnt2Clicked(ActionEvent event) {
		Afficheur.setText(Afficheur.getText() + "2");
	}

	@FXML
	void bnt3Clicked(ActionEvent event) {
		Afficheur.setText(Afficheur.getText() + "3");
	}

	@FXML
	void bnt4Clicked(ActionEvent event) {
		Afficheur.setText(Afficheur.getText() + "4");

	}

	@FXML
	void bnt5Clicked(ActionEvent event) {
		Afficheur.setText(Afficheur.getText() + "5");
	}

	@FXML
	void bnt6Clicked(ActionEvent event) {
		Afficheur.setText(Afficheur.getText() + "6");
	}

	@FXML
	void bnt7Clicked(ActionEvent event) {
		Afficheur.setText(Afficheur.getText() + "7");
	}

	@FXML
	void bnt8Clicked(ActionEvent event) {
		Afficheur.setText(Afficheur.getText() + "8");
	}

	@FXML
	void bnt9Clicked(ActionEvent event) {
		Afficheur.setText(Afficheur.getText() + "9");
	}

	@FXML
	void bntPointClicked(ActionEvent event) {
		Afficheur.setText(Afficheur.getText() + ".");
	}

	@FXML
	void butEnterClicked(ActionEvent event) {
		caisse.inserNamePrenom();
		if (caisse.tableCaisse.getItems().size()==0)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Paiement");
			alert.setHeaderText("Séléction vide ");
			alert.setContentText("Aucun produiti n'a été selectionné ! ");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		    stage.getIcons().add(new Image("/img/its_icoTR.png"));
			alert.show();
			PauseTransition delay = new PauseTransition(Duration.seconds(3));
			delay.setOnFinished(eventt -> {
				alert.close();
		});
			delay.play();
		}
		else {

			Double montant = Double.parseDouble(Afficheur.getText());
			Double apayer = caisse.somme;
			System.out.println("apayer " + apayer + " montnant siais" + montant);
			if (montant < apayer) {
				System.out.println("infif");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Paiement");
				alert.setHeaderText("Montant saisi insuffisant !");
				alert.setContentText("Somme manquante = "+(apayer-montant));
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			    stage.getIcons().add(new Image("/img/its_icoTR.png"));
				alert.show();
				PauseTransition delay = new PauseTransition(Duration.seconds(3));
				delay.setOnFinished(eventt -> {
					alert.close();
				});
				delay.play();
			} else {
				System.out.println("inelse");
					openCashDrawer();
					dbVente.addVente(numeroVente, caisse.dateHeure, caisse.nameUser, caisse.prenomUser,
							"Produits: " + String.valueOf(numeroVente), tva, caisse.somme, "Cash");
					SavePDF();
	
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Notification");
					alert.setHeaderText("Paiement effectué cash ! ");
					alert.setContentText("Montant à rendre = "+(montant-apayer));
					Stage primaryStage = (Stage) butEnter.getScene().getWindow();
					primaryStage.close();
					Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				    stage.getIcons().add(new Image("/img/its_icoTR.png"));
					alert.show();
					PauseTransition delay = new PauseTransition(Duration.seconds(5));
					delay.setOnFinished(eventt -> {
						alert.close();
						
					});
					
					delay.play();
					
					if (montant > apayer) {	
						for (int i = 0; i < caisse.item1.size(); i++) {
							quantite = caisse.item1.get(i).getQunatite();
							prix = caisse.item1.get(i).getPrixVente();
							tvaP = caisse.item1.get(i).getTVA();
							tvaC = (double) Math.round(((prix * tvaP) / 100) * 100) / 100;
							System.out.println("tvaP: " + tvaP);
							//dbVente.addProduitsV( caisse.item1.get(i).getNomProduits(), quantite, prix, tvaP, tvaC,dbVente.getPk());
							tva += tvaC;
							id++;
							tva = (double) Math.round(tva * 100) / 100;
							dbVente.UpdateTVA(dbVente.getPk(), tva,"Ticket:");
							}
							caisse.tableCaisse.getItems().clear();
							caisse.AfficcheurSomme.clear();
					}
			}
		}
	}

	@FXML
	void butSUPlClicked(ActionEvent event) {
		Afficheur.deleteText(Afficheur.getLength() - 1, Afficheur.getLength());
	}

	public void butBillet10() {
		ImageView img = new ImageView("/img/Billet10.jpg");
		img.setFitWidth(110);
		img.setFitHeight(169);
		butBillet10.setGraphic(img);

	}

	public void butBillet20() {
		ImageView img = new ImageView("/img/Billet20.jpg");
		img.setFitWidth(110);
		img.setFitHeight(169);
		butBillet20.setGraphic(img);

	}

	public void butBillet50() {
		ImageView img = new ImageView("/img/Billet50.jpg");
		img.setFitWidth(110);
		img.setFitHeight(169);
		butBillet50.setGraphic(img);

	}

	public void butBillet100() {
		ImageView img = new ImageView("/img/billet100-.jpg");
		img.setFitWidth(110);
		img.setFitHeight(169);
		butBillet100.setGraphic(img);

	}

	public void butBillet200() {
		ImageView img = new ImageView("/img/Billet200-.jpg");
		img.setFitWidth(110);
		img.setFitHeight(169);
		butBillet200.setGraphic(img);

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
			String nameFile = t+"//ticket"+dbVente.getPk()+".pdf";
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
	        DecimalFormat df = new DecimalFormat("#.##");
	        
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
   	           
       		
        	double somme = (double) 0.00;
        	
        	Text article = new Text("Article").setFontSize(16);
        	Paragraph articl = new Paragraph().add(article).setWidth(260);
        	doc.add(articl);
        	Text unite = new Text("Unité").setFontSize(16);
        	Paragraph unit = new Paragraph().add(unite).setWidth(260);
        	doc.add(unit);
        	Text prix = new Text("Prix").setFontSize(16);
        	Paragraph pri = new Paragraph().add(prix).setWidth(260);
        	doc.add(pri);
        	
   	        for (int i = 0; i < caisse.item1.size(); i++) {
   	        	Vente f= caisse.item1.get(i);
   	        	somme+=f.getTVAC();
   	        	Text nomprod = new Text(f.getNomProduits());
   	        	Paragraph nompro = new Paragraph().add(nomprod).setWidth(260);
   	        	doc.add(nompro);
   	        	Text qteprod = new Text(f.getQunatite()+"");
   	        	Paragraph qtepro = new Paragraph().add(qteprod).setWidth(260);
   	        	doc.add(qtepro);
   	        	String dx=df.format(f.getPrixVente());
   		        Double pr =Double.valueOf(dx);
        		Text prixprod = new Text(pr+"");
        		Paragraph prixpro = new Paragraph().add(prixprod).setWidth(260);
   	        	doc.add(prixpro);	
   	        	
   	        }
   	        nbr = caisse.item1.size() * 3;
   	        doc.add(new Paragraph(new Text("---------------------------------------------------------------")).setWidth(260));
    		//Text tvtick = new Text("TVA :\t\t"+somme+" CHF").setFontSize(16);
    		///Paragraph tvt = new Paragraph().add(tvtick);
    		//tvt.setMargins(0, 2, 0, 2).setTextAlignment(TextAlignment.CENTER).setWidth(200);
    		//doc.add(tvt); 
   	        Text footertick = new Text("TOTAL : "+caisse.AfficcheurSomme.getText()+"  -CHF");
    		Paragraph ft = new Paragraph().add(footertick).setWidth(260);
    		doc.add(ft); 
    		Text paytick = new Text("Paiement : Espéces");
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

	        paper.setImageableArea(margin, margin, 0, 0);
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
//	      byte[] cutter = {29, 86,49};
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
		
		public static int getNbr() {
			return nbr;
		}

		public static void setNbr(int nbr) {
			CashControl.nbr = nbr;
		}
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			vente = dbVente.loadProduitsV();
			butBillet50();
			butBillet20();
			butBillet10();
			butBillet100();
			butBillet200();
			setReglage();
		}

}


