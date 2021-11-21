package Fenetre;

import java.awt.Color;
import java.io.File;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
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
import com.itextpdf.layout.renderer.IRenderer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import DataBase.ReglageDB;
import DataBase.SalaireDB;
import DataBase.VenteDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.Analyse;
import objects.ItemFacture;
import objects.Reglage;

public class Salairecontrol extends ScrollPane implements Initializable {

			private Connection DB = null;
			private SalaireDB salaire = null;
			private EmployerControl EmplControl = null;
			public static int  idEm;
			private ReglageDB dbreglage = null;
			private Reglage myreglage =null;
			private LoginControl logincontrol = null;
			
			@FXML
			public AnchorPane anchorPane;

		    @FXML
		    public Label nomEntreprise;

		    @FXML
		    public Label departement;

		    @FXML
		    public Label numPersonal;

		    @FXML
		    public JFXDatePicker datePeriode;

		    @FXML
		    public JFXButton bntEmployer;

		    @FXML
		    public Label profession;

		    @FXML
		    public Label baseSalaire;

		    @FXML
		    public Label tauxOccup;

		    @FXML
		    public Label edition;

		    @FXML
		    public Label entree;

		    @FXML
		    public Label sortie;

		    @FXML
		    public JFXCheckBox IndemniteG;

		    @FXML
		    public JFXCheckBox salaireHoraireG;

		    @FXML
		    public JFXCheckBox feriesG;

		    @FXML
		    public JFXCheckBox salaireG;

		    @FXML
		    public Label brutG;

		    @FXML
		    public JFXCheckBox avsG;

		    @FXML
		    public JFXCheckBox acG;

		    @FXML
		    public JFXCheckBox aanpG;

		    @FXML
		    public JFXCheckBox pcG;

		    @FXML
		    public JFXCheckBox lppG;

		    @FXML
		    public JFXCheckBox crpG;

		    @FXML
		    public JFXCheckBox cspG;

		    @FXML
		    public JFXCheckBox iijG;

		    @FXML
		    public Label deductionsG;

		    @FXML
		    private JFXCheckBox repaG;

		    @FXML
		    public JFXCheckBox fraixG;

		    @FXML
		    public JFXCheckBox allocationG;

		    @FXML
		    public JFXCheckBox totRembourG;

		    @FXML
		    public Label netG;

		    @FXML
		    public Label salair13;

		    @FXML
		    public Label aanp;

		    @FXML
		    public Label avs;

		    @FXML
		    public Label ac;

		    @FXML
		    public Label pacFamille;

		    @FXML
		    public Label indVacan;

		    @FXML
		    public Label lpp;

		    @FXML
		    public Label crp;

		    @FXML
		    public Label csp;

		    @FXML
		    public Label ij;

		    @FXML
		    public JFXTextField tauxIndem;

		    @FXML
		    public JFXTextField taux13;

		    @FXML
		    public JFXTextField tauxAVS;

		    @FXML
		    public JFXTextField tauxAC;

		    @FXML
		    public JFXTextField tauxAANP;

		    @FXML
		    public JFXTextField tauxPC;

		    @FXML
		    public JFXTextField tauxLPP;

		    @FXML
		    public JFXTextField tauxCRP;

		    @FXML
		    public JFXTextField tauxCSP;

		    @FXML
		    public JFXTextField tauxIJ;

		    @FXML
		    public Label mntAF;

		    @FXML
		    public Label mntVacance;

		    @FXML
		    public Label mntFeries;

		    @FXML
		    public Label mntSalaire13;

		    @FXML
		    public Label mntBrut;

		    @FXML
		    public Label mntAVS;

		    @FXML
		    public Label mntAC;

		    @FXML
		    public Label mntAANP;

		    @FXML
		    public Label mntPC;

		    @FXML
		    public Label mntLPP;

		    @FXML
		    public Label mntRCRP;

		    @FXML
		    public Label mntCSP;

		    @FXML
		    public Label mntIJ;

		    @FXML
		    public Label mntTotalD;

		    @FXML
		    public Label mntnet;

		    @FXML
		    public Label mntSalaireHoraire;

		

		    @FXML
		    public Label mntVirement;

		    @FXML
		    public Label textBanqueDate;

		    @FXML
		    public JFXButton bntEnregist;
	   
		    @FXML
		    public JFXComboBox<String> classeSalaire;
		    
		    @FXML
		    public Label sex;

		    @FXML
		    public Label nomPrenom;

		    @FXML
		    public Label adresse;
		    
		    @FXML
		    public JFXTextField tauxSalaire;
		    
		    @FXML
		    public JFXTextField tauxJFer;
		    
		    @FXML
		    public JFXTextField qntSalair; 
		    
		    @FXML
		    public JFXTextField qntJFer; 
		    
		    @FXML
		    public JFXTextField mntPanier; 
		    
		    @FXML
		    public JFXTextField mntFrais;  
		    
		    @FXML
		    public JFXTextField mntAlloc; 
		    
		    @FXML
		    public Label mntTotalRemb; 
		    
		    @FXML
		    public Label paiement;  
		    
		    @FXML
		    private Button bntSuprimer;
		    

	    public Salairecontrol(Connection db) throws SQLException {

	    	this.DB = db;
	    	salaire = new SalaireDB(DB);
	    	dbreglage = new ReglageDB(DB);
	    	
	    	try {
				logincontrol = new LoginControl(DB);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Salaire2.fxml"));
	    	fxmlLoader.setRoot(this);
	    	fxmlLoader.setController(this);

	    	try {
	    		fxmlLoader.load();
	    	} catch (IOException exception) {
	    		throw new RuntimeException(exception);
	    	}

	    }
	    
	    public void Insert() {
	    	
	    	avs.setText(mntBrut.getText());
			ac.setText(mntBrut.getText());
			aanp.setText(mntBrut.getText());
			pacFamille.setText(mntBrut.getText());
			lpp.setText(mntBrut.getText());
			crp.setText(mntBrut.getText());
			csp.setText(mntBrut.getText());
			ij.setText(mntBrut.getText());
			
			BigDecimal mntAv = new BigDecimal((Double.parseDouble(avs.getText()) * Double.parseDouble(tauxAVS.getText())) / 100 ).setScale(2, RoundingMode.HALF_UP);
			mntAVS.setText(mntAv + "");
			
			BigDecimal mntAc = new BigDecimal((Double.parseDouble(avs.getText()) * Double.parseDouble(tauxAC.getText())) / 100 ).setScale(2, RoundingMode.HALF_UP);
			mntAC.setText(mntAc + "");
			
			BigDecimal mntAanp = new BigDecimal((Double.parseDouble(avs.getText()) * Double.parseDouble(tauxAANP.getText())) / 100 ).setScale(2, RoundingMode.HALF_UP);
			mntAANP.setText(mntAanp + "");
			
			BigDecimal mntPc = new BigDecimal((Double.parseDouble(avs.getText()) * Double.parseDouble(tauxPC.getText())) / 100 ).setScale(2, RoundingMode.HALF_UP);
			mntPC.setText(mntPc + "");
			
			BigDecimal mntLPp = new BigDecimal((Double.parseDouble(avs.getText()) * Double.parseDouble(tauxLPP.getText())) / 100 ).setScale(2, RoundingMode.HALF_UP);
			mntLPP.setText(mntLPp + "");
			
			BigDecimal mntRcrp = new BigDecimal((Double.parseDouble(avs.getText()) * Double.parseDouble(tauxCRP.getText())) / 100 ).setScale(2, RoundingMode.HALF_UP);
			mntRCRP.setText(mntRcrp + "");
			
			BigDecimal mntCsp = new BigDecimal((Double.parseDouble(avs.getText()) * Double.parseDouble(tauxCSP.getText())) / 100 ).setScale(2, RoundingMode.HALF_UP);
			mntCSP.setText(mntCsp + "");
			
			BigDecimal mntIj = new BigDecimal((Double.parseDouble(avs.getText()) * Double.parseDouble(tauxIJ.getText())) / 100 ).setScale(2, RoundingMode.HALF_UP);
			mntIJ.setText(mntIj + "");
			
			BigDecimal TotalD = new BigDecimal(mntIj.doubleValue() + mntRcrp.doubleValue()
								+ mntLPp.doubleValue() + mntPc.doubleValue() + mntAanp.doubleValue() + mntAc.doubleValue()
								+ mntAv.doubleValue()).setScale(2, RoundingMode.HALF_UP);
			mntTotalD.setText("-"+ TotalD + "");
			
			BigDecimal mntN = new BigDecimal(Double.parseDouble(mntBrut.getText()) + Double.parseDouble(mntTotalD.getText()) ).setScale(2, RoundingMode.HALF_UP);
			mntnet.setText( mntN + "" );
			paiement.setText(mntN +"");
			mntVirement.setText(mntN +"");
	    	
	    }
	    

	    @FXML
	    void qntJFerEntred(KeyEvent event) {
	    	int x = 0;
	    	try
	    	{
	    	 
	    	  Double.parseDouble(qntJFer.getText());
	    	  x = 1;
	    	}
	    	catch(NumberFormatException e)
	    	{
	    		x = 0;
	    		mntFeries.setText("0");
	    		Alert alert1 = new Alert(AlertType.ERROR);
	    		alert1.setTitle("Quantité Jours fériés");
    			alert1.setHeaderText("Résultat");
    			alert1.setContentText("il faut inseré un chifre pas de texte !");
    			Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
    			stage1.getIcons().add(new Image("/img/its_icoTR.png"));
    			alert1.showAndWait();
	    		
	    	}
	    	
	    	if(x == 1 && qntJFer.getText() != "") {
	    		Double salaireF = Double.parseDouble(tauxJFer.getText()) * Double.parseDouble(qntJFer.getText());
	    		mntFeries.setText(salaireF+"");
	    		
	    		if(mntSalaireHoraire.getText() != "" ) {
	    			BigDecimal bd2 = new BigDecimal((Double.parseDouble(mntSalaireHoraire.getText()) + Double.parseDouble(mntSalaire13.getText()) +   
    						Double.parseDouble(mntVacance.getText()) + Double.parseDouble(mntFeries.getText()))).setScale(2, RoundingMode.HALF_UP);
		    		mntBrut.setText(bd2+"");
		    		Insert();
	    			
	    		}	
	    		else {
	    			
	    			BigDecimal bd2 = new BigDecimal(( Double.parseDouble(mntSalaire13.getText()) +   
    						Double.parseDouble(mntVacance.getText()) + Double.parseDouble(mntFeries.getText()))).setScale(2, RoundingMode.HALF_UP);
	    			mntBrut.setText(bd2+"");
	    			Insert();
	    			
	    		}
	    	}

	    }

	    @FXML
	    void qntSalairEntre(KeyEvent event) {
	    	int x = 0;
	    	try
	    	{
	    	 
	    	  Double.parseDouble(qntSalair.getText());
	    	  x = 1;
	    	}
	    	catch(NumberFormatException e)
	    	{
	    		x = 0;
	    		mntSalaireHoraire.setText("0");
	    		Alert alert1 = new Alert(AlertType.ERROR);
	    		alert1.setTitle("Quantité Salaire");
    			alert1.setHeaderText("Résultat");
    			alert1.setContentText("il faut inseré un chifre pas de texte !");
    			Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
    			stage1.getIcons().add(new Image("/img/its_icoTR.png"));
    			alert1.showAndWait();
	    		
	    	}
	    	
	    	if (x == 1 && qntSalair.getText() != "") {
	    		BigDecimal salaire = new BigDecimal( Double.parseDouble(qntSalair.getText()) * Double.parseDouble(tauxSalaire.getText())).setScale(2, RoundingMode.HALF_UP);
	    		
	    		mntSalaireHoraire.setText(salaire+"");
	    		indVacan.setText(salaire+"");
	    		salair13.setText(salaire+"");
	    		BigDecimal bd = new BigDecimal(salaire.doubleValue() *  Double.parseDouble(taux13.getText()) / 100).setScale(2, RoundingMode.HALF_UP);
	    		mntSalaire13.setText(bd.doubleValue()+"");
	    		BigDecimal bd1 = new BigDecimal(salaire.doubleValue() *  Double.parseDouble(tauxIndem.getText()) / 100).setScale(2, RoundingMode.HALF_UP);
	    		mntVacance.setText(bd1.doubleValue() +"" );
	    		
	    		
	    		if(mntFeries.getText() != "" ) {
	    			BigDecimal bd3 = new BigDecimal((Double.parseDouble(mntSalaireHoraire.getText()) + Double.parseDouble(mntSalaire13.getText()) + 
					         Double.parseDouble(mntVacance.getText()) + Double.parseDouble(mntFeries.getText()))).setScale(2, RoundingMode.HALF_UP);
	    			mntBrut.setText(bd3+"");
	    			Insert();
	    			
	    		}
	    			else {
	    				
	    				BigDecimal bd4 = new BigDecimal((Double.parseDouble(mntSalaireHoraire.getText()) + Double.parseDouble(mntSalaire13.getText()) +
   							 Double.parseDouble(mntVacance.getText()))).setScale(2, RoundingMode.HALF_UP);	
	    				mntBrut.setText(bd4+"");
	    				Insert();
	    			
	    		}
	    	}

	    }
	    @FXML
	    void mntAllocKey(KeyEvent event) {
	    	
	    	
	    	try
	    	{
	    	 
	    	  Double.parseDouble(mntAlloc.getText());
	    
	    	}
	    	catch(NumberFormatException e)
	    	{
	    	
	    		mntAlloc.setText("0");
	    		Alert alert1 = new Alert(AlertType.ERROR);
	    		alert1.setTitle("Allocations familiales ");
    			alert1.setHeaderText("Résultat");
    			alert1.setContentText("il faut inseré un chifre pas de texte !");
    			Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
    			stage1.getIcons().add(new Image("/img/its_icoTR.png"));
    			alert1.showAndWait();
	    		
	    	}
	    
	    }

	    @FXML
	    void mntFraisKey(KeyEvent event) {

	    	try
	    	{
	    	 
	    	  Double.parseDouble(mntFrais.getText());
	    	
	    	}
	    	catch(NumberFormatException e)
	    	{
	    		mntFrais.setText("0");
	    		Alert alert1 = new Alert(AlertType.ERROR);
	    		alert1.setTitle("Frais de véhicule forfait");
    			alert1.setHeaderText("Résultat");
    			alert1.setContentText("il faut inseré un chifre pas de texte !");
    			Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
    			stage1.getIcons().add(new Image("/img/its_icoTR.png"));
    			alert1.showAndWait();
	    		
	    	}
	    
	    }

	    @FXML
	    void mntPanierKey(KeyEvent event) {
	    
	    	try
	    	{
	    	 
	    	  Double.parseDouble(mntPanier.getText());
	    	
	    	}
	    	catch(NumberFormatException e)
	    	{
	    		
	    		mntPanier.setText("0");
	    		Alert alert1 = new Alert(AlertType.ERROR);
	    		alert1.setTitle("Panier repas (montant)");
    			alert1.setHeaderText("Résultat");
    			alert1.setContentText("il faut inseré un chifre pas de texte !");
    			Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
    			stage1.getIcons().add(new Image("/img/its_icoTR.png"));
    			alert1.showAndWait();
	    		
	    	}
	    }
	
	    

	    @FXML
	    void addRembClicked(ActionEvent event) {
	    	BigDecimal bd4 = new BigDecimal((Double.parseDouble(mntPanier.getText()) + Double.parseDouble(mntFrais.getText()) +
						     Double.parseDouble(mntAlloc.getText()) + Double.parseDouble(paiement.getText()))).setScale(2, RoundingMode.HALF_UP);
	    	BigDecimal bd5 = new BigDecimal((Double.parseDouble(mntPanier.getText()) + Double.parseDouble(mntFrais.getText()) +
					         Double.parseDouble(mntAlloc.getText()) + Double.parseDouble(mntVirement.getText()))).setScale(2, RoundingMode.HALF_UP);
	    	BigDecimal bd6 = new BigDecimal((Double.parseDouble(mntPanier.getText()) + Double.parseDouble(mntFrais.getText()) +
			                 Double.parseDouble(mntAlloc.getText()) )).setScale(2, RoundingMode.HALF_UP);
	    	mntTotalRemb.setText(bd6 + "");
			paiement.setText(bd4 + "");
			mntVirement.setText(bd5 + "");
			
	    }

	    
	    @FXML
	    void dateclcked(ActionEvent event) {
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    	System.out.println(datePeriode.getValue().format(formatter).toString());
	    	edition.setText(datePeriode.getValue().format(formatter).toString());
	    }
	
	    @FXML
	    void IndemniteGclick(ActionEvent event) {

	    }

	    @FXML
	    void acGClicked(ActionEvent event) {

	    }

	    @FXML
	    void acGclciked(ActionEvent event) {

	    }

	    @FXML
	    void acGclicked(ActionEvent event) {

	    }

	    @FXML
	    void allocationGclicked(ActionEvent event) {

	    }

	    @FXML
	    void avsGclicked(ActionEvent event) {

	    }

	    @FXML
	    void bntEmployerClicked(ActionEvent event) throws SQLException {
	    	
	    	EmployerControl Employer = new  EmployerControl(DB, this);
	    	 Stage primaryStage = new Stage();
	    	 primaryStage.setScene(new Scene(Employer));
	         primaryStage.getIcons().add(new Image("/img/its_icoTR.png"));
	         primaryStage.setResizable(false);
	         primaryStage.setTitle("Login ITS-CASH");
	         primaryStage.show();

	    }

	    @FXML
	    void bntEnregistClicked(ActionEvent event) {
	    	
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    	salaire.addSalaire(datePeriode.getValue().format(formatter).toString(), Double.parseDouble(mntSalaireHoraire.getText()), Double.parseDouble(mntVacance.getText())
	    					   , Double.parseDouble(mntFeries.getText()), Double.parseDouble(mntSalaire13.getText()), Double.parseDouble(mntBrut.getText())
	    					   , Double.parseDouble(mntAVS.getText()), Double.parseDouble(mntAC.getText()), Double.parseDouble(mntAANP.getText())
	    					   , Double.parseDouble(mntPC.getText()),  Double.parseDouble(mntLPP.getText()),  Double.parseDouble(mntRCRP.getText())
	    					   , Double.parseDouble(mntCSP.getText()), Double.parseDouble(mntIJ.getText()), Double.parseDouble(mntTotalD.getText())
	    					   , Double.parseDouble(mntnet.getText()), Double.parseDouble(mntPanier.getText()), Double.parseDouble(mntFrais.getText())
	    					   , Double.parseDouble(mntAlloc.getText()), Double.parseDouble(mntTotalRemb.getText()), Double.parseDouble(paiement.getText())
	    					   , idEm);
	    	
	    	 SavePDFFac();
	    	
	    }

	    @FXML
	    void crpGclcked(ActionEvent event) {

	    }

	    @FXML
	    void crpGclicked(ActionEvent event) {

	    }

	    @FXML
	    void feriesGclick(ActionEvent event) {

	    }

	    @FXML
	    void salaireGclicked(ActionEvent event) {

	    }

	    @FXML
	    void salaireHoraireGClick(ActionEvent event) {

	    }

	    @FXML
	    void totRembourGclik(ActionEvent event) {

	    }
	    
	    protected void manipulatePdfFact(String dest) throws Exception {
	    	
	    		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
	    		Document doc = new Document(pdfDoc, PageSize.A4);
	    		doc.setMargins(0, 0, 0, 0);
	 
	    		Text decompte = new Text("Décompte de salaire").setFontSize(13);
	    		Text entreprise = new Text("ITSFORWARD").setFontSize(13);
	    		Paragraph paraDecompte = new Paragraph().add(decompte);
	    		paraDecompte.setBold();
	    		Paragraph paraentreprise = new Paragraph().add(entreprise);
	    		paraDecompte.setFixedPosition(8, 815, 240);
	    		paraentreprise.setFixedPosition(400, 815, 240);
	    		doc.add(paraDecompte).add(paraentreprise);
   	    
	    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMMM-yyyy");
				
	    		Text periode = new Text("Période : September 2021 "//+datePeriode.getValue().format(formatter).toString()
	    								+ "\n" + "Département : " + departement.getText()
	    								+ "\n" + "No personnel : " + numPersonal.getText()).setFontSize(10);
	    		Paragraph parPeriode = new Paragraph().add(periode);
	    		parPeriode.setFontColor(ColorConstants.GRAY);
	    		parPeriode.setFixedPosition(8, 720, 240);
	    		doc.add(parPeriode);
	    		
	    		Text employer = new Text(sex.getText() + ", " + nomPrenom.getText() + "\n" + adresse.getText()).setFontSize(10);
	    		Paragraph parEmployer = new Paragraph().add(employer);
	    		parEmployer.setFixedPosition(400, 720, 240);
	    		doc.add(parEmployer);
	    		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    		
	    		Text classe = new Text("Classe salaire : " + classeSalaire.getValue()
	    							   + "\n" + "Profession : " + profession.getText()
	    							   + "\n" + "Salaire de base  : " + baseSalaire.getText() +" Fr/h"
	    							   + "\n" + "Taux d'occupation selon contrat : " + tauxOccup.getText()
	    							   + "\n" + "Date édition :01-10-2021 " //+   datePeriode.getValue().format(formatter1).toString()
	    							   + "\n" + "Date entrée : " + entree.getText()
	    							  /* + "\n" + "Date sortie :" + sortie.getText()).setFontSize(10)*/);
	    		Paragraph parClasse = new Paragraph().add(classe);
	    		parClasse.setFixedPosition(8, 580, 240);
	    		doc.add(parClasse);
	    		
	    		Table table = new Table(new float[6]).useAllAvailableWidth();
	    		table.setMargins(270, 8, 10, 8); 
	    		
	    		Cell cell = new Cell().add(new Paragraph("Genres de salaire").setBold().setFontSize(12));
	    		cell.setTextAlignment(TextAlignment.CENTER);
	    		//cell.setBackgroundColor(new DeviceRgb(0,131,228));
	    		table.addCell(cell);
	        
	    		Cell cell1 = new Cell().add(new Paragraph("Base").setBold().setFontSize(12));
	    		cell1.setTextAlignment(TextAlignment.CENTER);
	    		//cell1.setBackgroundColor(new DeviceRgb(0,131,228));
	    		table.addCell(cell1);
	        
	    		Cell cell2 = new Cell().add(new Paragraph("Taux %").setBold().setFontSize(12));
	    		cell2.setTextAlignment(TextAlignment.CENTER);
	    		//cell2.setBackgroundColor(new DeviceRgb(0,131,228));
	    		table.addCell(cell2);
	        
	    		Cell cell3 = new Cell().add(new Paragraph("Taux").setBold().setFontSize(12));
	    		cell3.setTextAlignment(TextAlignment.CENTER);
	    		//cell3.setBackgroundColor(new DeviceRgb(0,131,228));
	    		table.addCell(cell3);
	        
	    		Cell cell4 = new Cell().add(new Paragraph("Quantité").setBold().setFontSize(12));
	    		cell4.setTextAlignment(TextAlignment.CENTER);
	    		//cell4.setBackgroundColor(new DeviceRgb(0,131,228));
	    		table.addCell(cell4);
	    		
	    		Cell cell5 = new Cell().add(new Paragraph("Montant").setBold().setFontSize(12));
	    		cell5.setTextAlignment(TextAlignment.CENTER);
	    		//cell5.setBackgroundColor(new DeviceRgb(0,131,228));
	    		table.addCell(cell5);
	     
	    		table.addCell(new Cell().add(new Paragraph("1030 Salaire horaire")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(tauxSalaire.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(qntSalair.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntSalaireHoraire.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		
	    		table.addCell(new Cell().add(new Paragraph("1038 Indemnité vacances")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(indVacan.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(tauxIndem.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntVacance.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    	
	    		table.addCell(new Cell().add(new Paragraph("1057 Jours fériés (heures)")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(tauxJFer.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(qntJFer.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntFeries.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		
	    		table.addCell(new Cell().add(new Paragraph("1201 13ème salaire paiement mensuel")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(salair13.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(taux13.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntSalaire13.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		
	    		table.addCell(new Cell().add(new Paragraph("1999 SALAIRE BRUT")).setBold().setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntBrut.getText())).setBold().setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		
	    		table.addCell(new Cell().add(new Paragraph("2030 AVS-Retenue")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(avs.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(tauxAVS.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntAVS.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		
	    		table.addCell(new Cell().add(new Paragraph("2090 AC-Retenue")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(avs.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(tauxAC.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntAC.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		
	    		table.addCell(new Cell().add(new Paragraph("2140 AANP")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(avs.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(tauxAC.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntAC.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		
	    		table.addCell(new Cell().add(new Paragraph("2194 Complément PC Famille VD Retenue")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(avs.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(tauxAC.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntPC.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		
	    		table.addCell(new Cell().add(new Paragraph("2353 LPP/CRP SO Retenue")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(avs.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(tauxLPP.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntLPP.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		
	    		table.addCell(new Cell().add(new Paragraph("2423 Rente transitoire/Retraite anticipée")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(avs.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(tauxCRP.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntRCRP.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		
	    		/*table.addCell(new Cell().add(new Paragraph("2503 CSP FVE Retenue")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(avs.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(tauxCSP.getText())).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntCSP.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));*/
	    	
	    		table.addCell(new Cell().add(new Paragraph("2735 IJ maladie Autre % matrice Retenue")).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(avs.getText())).setBorder(Border.NO_BORDER));;
	    		table.addCell(new Cell().add(new Paragraph(tauxIJ.getText())).setBorder(Border.NO_BORDER));;
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntIJ.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		
	    		table.addCell(new Cell().add(new Paragraph("2998 TOTAL DES DEDUCTIONS")).setBold().setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(new SolidBorder(1f))
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntTotalD.getText())).setBold().setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		
	    		table.addCell(new Cell().add(new Paragraph("2999 SALAIRE NET")).setBorderBottom(Border.NO_BORDER)
											.setBorderLeft(new SolidBorder(1f))
											.setBorderRight(Border.NO_BORDER)
											.setBorderTop(Border.NO_BORDER));;
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntnet.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
						
	    		table.addCell(new Cell().add(new Paragraph("3151 Panier repas (montant)")).setBorderBottom(Border.NO_BORDER)
											.setBorderLeft(new SolidBorder(1f))
											.setBorderRight(Border.NO_BORDER)
											.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntPanier.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    	
	    		table.addCell(new Cell().add(new Paragraph("3103 Frais de véhicule forfait")).setBorderBottom(Border.NO_BORDER)
	    									.setBorderLeft(new SolidBorder(1f))
	    									.setBorderRight(Border.NO_BORDER)
	    									.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntFrais.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		

	    		table.addCell(new Cell().add(new Paragraph("1330 Allocations familiales")).setBorderBottom(Border.NO_BORDER)
	    									.setBorderLeft(new SolidBorder(1f))
	    									.setBorderRight(Border.NO_BORDER)
	    									.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntAlloc.getText())).setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		
	    		table.addCell(new Cell().add(new Paragraph("3199 TOTAL AUTRES REMBOURSEMENTS")).setBold().setBorderBottom(Border.NO_BORDER)
	    					  .setBorderLeft(new SolidBorder(1f))
	    				      .setBorderRight(Border.NO_BORDER)
	    				      .setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(mntTotalRemb.getText())).setBold().setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		
	    		table.addCell(new Cell().add(new Paragraph("Paiement")).setBold().setBorderBottom(new SolidBorder(1f))
	    									.setBorderLeft(new SolidBorder(1f))
	    									.setBorderRight(Border.NO_BORDER)
	    									.setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(new SolidBorder(1f))
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(new SolidBorder(1f))
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(new SolidBorder(1f))
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph("")).setBorderBottom(new SolidBorder(1f))
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(Border.NO_BORDER)
						.setBorderTop(Border.NO_BORDER));
	    		table.addCell(new Cell().add(new Paragraph(paiement.getText())).setBold().setBorderBottom(new SolidBorder(1f))
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(new SolidBorder(1f))
						.setBorderTop(Border.NO_BORDER));
	    	
	    		doc.add(table);
	      
	    		Text virm = new Text("Virement : "+paiement.getText());
	    		Paragraph parVirm = new Paragraph().add(virm);
	    		parVirm.setMarginLeft(8);
	    		doc.add(parVirm);
	    		
	    	
	    		Text emplDetail = new Text(textBanqueDate.getText());
	    		Paragraph parEmplDetail = new Paragraph().add(emplDetail);
	    		parEmplDetail.setMarginLeft(8);
	    		doc.add(parEmplDetail);
	    		
	    		Text tex1 = new Text("Ce décompte est établi par l'employeur sur la base des informations transmises par l'employer."
	    							+ "\n" + "Nous vous prions de le vérifier immédiatement et de signaler toute erreur  à l'entreprise.");
	    		Paragraph parTex1 = new Paragraph().add(tex1);
	    		parTex1.setMarginLeft(8);
	    		doc.add(parTex1);
	    		
	    		doc.close();
	      
	    }
	    
	public void SavePDFFac() {
			
			try
	        {
				String t = myreglage.getFactures();
				t=t.replace("\\","//" );
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String nameFile = t+"//Salaire "+LocalDateTime.now().format(formatter).toString()+ " "+ nomPrenom.getText() +".pdf";
				System.out.println("path   "+nameFile);
				//String imgESE =   myreglage.getImage();
				File file = new File(nameFile );
		        file.getParentFile().mkdirs();
		        manipulatePdfFact(nameFile );
	        }
	        catch (Throwable t)
	        {
	            t.printStackTrace();
	        }
			
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
			setReglage();
			
		}
	    
	    

}
