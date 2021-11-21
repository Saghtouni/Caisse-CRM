package Fenetre;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.event.ChangeListener;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;


import DataBase.ReglageDB;
import DataBase.SalaireDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import objects.Employer;
import objects.Reglage;
import objects.Salle;
import objects.salaire;

public class HistoriqueSalaireControl extends AnchorPane implements Initializable {
	
	
	@FXML
    private TableView<salaire> tabSalaire;

    @FXML
    private TableColumn<salaire, String> periode;

    @FXML
    private TableColumn<salaire, Double> salaireBrut;

    @FXML
    private TableColumn<salaire, Double> deduction;

    @FXML
    private TableColumn<salaire, Double> embourse;

    @FXML
    private TableColumn<salaire, Double> paiement;

    @FXML
    private Button pdf;

    @FXML
    private Button print;

    @FXML
    private Button certifecate;

    @FXML
    private JFXComboBox<String> dateCherche;
    
    private Connection DB = null;
    int idEmployer;
    private ObservableList<salaire> salaireEm ;
    private ObservableList<salaire> salaireEmAnne = FXCollections.observableArrayList() ;
    private SalaireDB salaireDB;
    private ReglageDB dbreglage = null;
	private Reglage myreglage =null;
	private Employer employerSA;
	private LoginControl logincontrol = null;
	ObservableList<salaire> salair = FXCollections.observableArrayList();
	
    public  HistoriqueSalaireControl(Connection db, int idEmployer) throws SQLException {
    	
    	this.DB = db;
    	this.salaireDB = new SalaireDB(this.DB);
    	this.dbreglage = new ReglageDB(this.DB);
    	this.logincontrol = new LoginControl(this.DB);
    	this.idEmployer = idEmployer;
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HistoriqueEmployer.fxml"));
    	fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);
    	LocalDate local;
    	
    	try {
    		fxmlLoader.load();
    	} catch (IOException exception) {
    		throw new RuntimeException(exception);
    	}

	}
    
    @FXML
    void certifecateClicked(ActionEvent event) {
    	SavePDCert();
    	
    }

    @FXML
    void pdfClicked(ActionEvent event) {

    }

    @FXML
    void printClicked(ActionEvent event) {

    }


    @FXML
    void dateChercheClicked1(ActionEvent event) {
    	salaireEmAnne.clear();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
    	for(int i = 0; i < salaireEm.size(); i++) {
    		if(dateCherche.getValue().equals(salaireEm.get(1).getPeriode().substring(6)))
    			salaireEmAnne.add(salaireEm.get(i));
    	}
    	tabSalaire.setItems(salaireEmAnne);
    }
    protected void manipulatePdfCert(String dest) throws Exception {
    	
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc, new PageSize(595, 842));
		salair = tabSalaire.getItems();
		doc.setMargins(0, 0, 0, 0);

		ObservableList<Date> date = FXCollections.observableArrayList();
		Double salrT = 0.0, Deduction = 0.0, salrN = 0.0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date dateTemp;
		
		for(int i = 0; i < salair.size(); i++) {	
			date.add(sdf.parse(salair.get(i).getPeriode()));	
			salrT +=  salair.get(i).getSalaire_brut();
			Deduction += salair.get(i).getDeduction();
			salrN += salair.get(i).getSalaireNet();
		}
	
		for(int i = 0; i < date.size(); i++) {
			
			for(int j = 0; j < date.size() - 1; j++) {
				
				if(date.get(j).compareTo(date.get(j+1)) > 0) {
					dateTemp = date.get(j);
					date.set(j, date.get(j+1));
					date.set(j+1, dateTemp);
				
				}
				
			}
		}
		
		
		Text decompte = new Text("Certificat de salaire").setFontSize(13);
		Text entreprise = new Text("F-R Décoration Peinture Sàrl").setFontSize(13);
		Paragraph paraDecompte = new Paragraph().add(decompte);
		paraDecompte.setBold();
		Paragraph paraentreprise = new Paragraph().add(entreprise);
		paraDecompte.setFixedPosition(8, 815, 240);
		paraentreprise.setFixedPosition(400, 815, 240);
		doc.add(paraDecompte).add(paraentreprise);
   
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		
		Text periode = new Text("Année : " + dateCherche.getValue() + "  du : "+ sdf.format(date.get(0)) + " au : " +sdf.format(date.get(date.size()-1))).setFontSize(10);
		Paragraph parPeriode = new Paragraph().add(periode);
		parPeriode.setFontColor(ColorConstants.GRAY);
		parPeriode.setFixedPosition(8, 720, 240);
		doc.add(parPeriode);
		
		Text employer = new Text(employerSA.getSex()+ ", " + employerSA.getLastname()  + " " + employerSA.getName() + "\n" + employerSA.getAdresse()).setFontSize(10);
		Paragraph parEmployer = new Paragraph().add(employer);
		parEmployer.setFixedPosition(400, 680, 240);
		doc.add(parEmployer);
		
		BigDecimal bd1 = new BigDecimal(salrT).setScale(2, RoundingMode.HALF_UP);
		Text salaireT = new Text("SALAIRE BRUT : "  + bd1 +"\n").setFontSize(10);
		Paragraph parSalaireT = new Paragraph().add(salaireT);
		parSalaireT.setFixedPosition(8, 640, 240);
		doc.add(parSalaireT);
		
		BigDecimal bd2 = new BigDecimal(Deduction).setScale(2, RoundingMode.HALF_UP);
		Text deduction = new Text("DEDUCTIONS   : "  + bd2 +"\n").setFontSize(10);
		                          
		Paragraph parDeduction = new Paragraph().add(deduction);
		parDeduction.setFixedPosition(8, 620, 240);
		doc.add(parDeduction);
		
		BigDecimal bd3 = new BigDecimal(salrN).setScale(2, RoundingMode.HALF_UP);
		Text salaireN = new Text("SALAIRE NET   : "  + bd3+"\n").setFontSize(10);
						        
		Paragraph parsalaireN = new Paragraph().add(salaireN);
		parsalaireN.setFixedPosition(8, 600, 240);
		doc.add(parsalaireN);
		
		try {

			File pdfFile = new File(dest);
			if (pdfFile.exists()) {

				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(pdfFile);
				} else {
					System.out.println("Awt Desktop is not supported!");
				}

			} else {
				System.out.println("File is not exists!");
			}

			System.out.println("Done");

		  } catch (Exception ex) {
			ex.printStackTrace();
		  }
		doc.close();
    }
    
    public void SavePDCert() {
		
		try
        {
			String t = myreglage.getFactures();
			t=t.replace("\\","//" );
			String nameFile = t+"//Certificat de salaire" +".pdf";
			System.out.println("path   "+nameFile);
			String imgESE =   myreglage.getImage();
			File file = new File(nameFile );
	        file.getParentFile().mkdirs();
	        manipulatePdfCert(nameFile );
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
		
	}
	
    public void bntPdf() {
		ImageView img = new ImageView("/img/pdf.png");
		img.setFitWidth(75);
		img.setFitHeight(55);
		pdf.setGraphic(img);
	}
    
    public void bntCertifecate() {
		ImageView img = new ImageView("/img/contract.png");
		img.setFitWidth(75);
		img.setFitHeight(55);
		certifecate.setGraphic(img);
	}
    
    public void bntPrint() {
		ImageView img = new ImageView("/img/printer.png");
		img.setFitWidth(75);
		img.setFitHeight(55);
		print.setGraphic(img);
	}
    
    
    public ObservableList<salaire> SalaireEmployer(){
    	
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
    	
    	for(int i = 0; i < salaireEm.size(); i++) {
    		if(LocalDateTime.now().format(formatter).equals(salaireEm.get(1).getPeriode().substring(6)))
    			salaireEmAnne.add(salaireEm.get(i));
    	}
    	periode.setCellValueFactory(new PropertyValueFactory("periode"));
    	salaireBrut.setCellValueFactory(new PropertyValueFactory("salaire_brut"));
    	deduction.setCellValueFactory(new PropertyValueFactory("deduction"));
    	embourse.setCellValueFactory(new PropertyValueFactory("remboursement"));
    	paiement.setCellValueFactory(new PropertyValueFactory("paiement"));
		return salaireEmAnne;
    }
    public void setReglage()
	{
         Reglage r = dbreglage.getReglage(logincontrol.getId_utilisateur());
         
         if (r != null)
 		{
 			myreglage=r;
 		
 		}
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		salaireEm = salaireDB.loadSalaire(idEmployer);
		employerSA = salaireDB.get(idEmployer);
		tabSalaire.setItems(SalaireEmployer());
		bntPdf();
		bntCertifecate();
		bntPrint();
		setReglage();
		for(int i = 0; i < 100; i++) {
			int nbr = 2020 + i;
			dateCherche.getItems().add(nbr +"");
		}
		  
		dateCherche.getSelectionModel().select(0);
	}
}
