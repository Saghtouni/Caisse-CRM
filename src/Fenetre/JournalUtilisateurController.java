package Fenetre;

import java.awt.print.PageFormat
;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.print.PrintService;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import DataBase.VenteDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import objects.Analyse;
import objects.Reglage;
import objects.Vente;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

import objects.Reglage;


public class JournalUtilisateurController  extends BorderPane implements Initializable  {
	


	    @FXML
	    private Label labeltotal;

	    @FXML
	    private Button btnImprimer;

	    @FXML
	    private Label user;

	    @FXML
	    private Label valeurDate;

	    @FXML
	    private Label valeurtotal;
	    
	    @FXML
	    private TableView<Analyse> tableticketsJournal;
	@FXML
	private TableColumn<Analyse, Double> sommecol;
	@FXML
	private TableColumn<Analyse, Button> detailscol;
	@FXML
	private TableColumn<Analyse, Button> statuscol;
	
	private String nameUser,prenomUser;
	ObservableList<objects.Analyse> ls = null;
	private Connection DB = null;

	
	VenteDB ventedb = null;
	CaisseControl caisse = null;
	private Reglage myreglage =null;
	private ReglageDB dbreglage = null;
	private LoginControl logincontrol = null;
	
	public JournalUtilisateurController(String nameUser, String prenomUser,Connection db) throws SQLException {
		this.nameUser=nameUser;
		this.prenomUser=prenomUser;
		this.DB=db;
		ventedb = new VenteDB(DB);
		caisse = new CaisseControl(DB);
		try {
			logincontrol = new LoginControl(db);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbreglage = new ReglageDB(db);
	}


	public void btnImprimer() {
    	ImageView img =new ImageView("/img/printer.png");
    	img.setFitWidth(75);
    	img.setFitHeight(60);
    	btnImprimer.setGraphic(img);
    }

	 @FXML
	    void btnImprimerCliked(ActionEvent event) {
		 SavePDF();
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
      		
      		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  	        String dateF = LocalDateTime.now().format(formatter).toString();
  	        
  	    	PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
  	        Document doc = new Document(pdfDoc);
  	        Paper paper = new Paper();
	        double margin = 0; 
	        DecimalFormat df = new DecimalFormat("#.##");
	        
  	        doc.setMargins(0, 0, 0, 0);


      		//------------------- HEADER  ----------------------------

      	
      		Text t11 = new Text("Journal vendeur le " + dateF  ).setFontSize(18);
      		Paragraph paraheader = new Paragraph().add(t11);	
      		paraheader.setMargins(0, 0, 2, 0).setTextAlignment(TextAlignment.CENTER).setWidth(260);
      		doc.add(paraheader); 
      		
      		Text detailstick = new Text("Vendeur  :\t\t\t\t"+ nameUser +" " + prenomUser).setFontSize(16);
       		Paragraph dt = new Paragraph().add(detailstick);
       		doc.add(dt); 
      		doc.add(new Paragraph(new Text("---------------------------------------------------------------")).setWidth(260));
  	           
      		
      		double somme = (double) 0.00;
       	
  	        for (int i = 0; i < ls.size(); i++) {
  	        	
  	        	somme+=ls.get(i).getSomme();
  	        	
  	        	Text numbertick = new Text(tableticketsJournal.getItems().get(i).getProduit().toString().substring(34).replace("'", "")+ "                ");
  	        	Text status = new Text(ls.get(i).getStatus().toString().substring(34).replace("'", "")+"                ");
  	        	Text sommeTic = new Text(ls.get(i).getSomme()+"");
  	        	Paragraph nompro = new Paragraph().add(numbertick).add(status).add(sommeTic).setWidth(260);
  	        	doc.add(nompro);
  	        }
  	        doc.add(new Paragraph(new Text("---------------------------------------------------------------")).setWidth(260));
   		
  	        Text footertick = new Text("TOTAL : "+somme+" -CHF");
  	        Paragraph ft = new Paragraph().add(footertick).setWidth(260);
  	        doc.add(ft); 	
  	        	
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
	        job.setPrintable(new OutPrinter2(text),pf);
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
		
	
	 private void buidlTicketsTable2() 
     {
		 
		try {
			ls = ventedb.displayAllTicketsUtilisateur(nameUser,prenomUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ls.size()==0)
		{
			Alert a = new Alert(Alert.AlertType.INFORMATION) ; 
            a.setContentText("Pas de tickets pour cette date !");
            a.showAndWait();
		}
		else
		{
			 tableticketsJournal.setItems(ls);
		 System.out.println("ls = "+ls.toString());
		 Double total=0.0;
		 for (Analyse a : ls )
		 {
			 total+=a.getSomme();
		 }
		 valeurtotal.setText(total.toString());
		 user.setText(nameUser+" "+prenomUser);
		
		}
		
		 
	 
    }

	 @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
		   tableticketsJournal.getColumns().clear();
		   sommecol.setCellValueFactory(new PropertyValueFactory("somme"));
		   detailscol.setCellValueFactory(new PropertyValueFactory("produit"));    	
		   statuscol.setCellValueFactory(new PropertyValueFactory("status"));
		   tableticketsJournal.getColumns().addAll(sommecol,detailscol,statuscol);   
		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
		   valeurDate.setText(LocalDateTime.now().format(formatter).toString());
		   buidlTicketsTable2();
			btnImprimer();
			setReglage();
			
		}
}
