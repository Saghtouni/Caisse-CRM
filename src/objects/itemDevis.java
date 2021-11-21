package objects;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class itemDevis {
	
	int id;
	String nom;
	double quantite;
	double prixUni;
	double prixTot;
	double tva;
	String unite;
	private int taille;
	private Color color;
	private String colorS;
	private Button colorBnt;
	int numDevis;
	
	
	
	public itemDevis(int id, String nom, double quantite, double prixUni, double prixTot, double tva, String unite,
			int taille, String colorS, int numDevis) {
		super();
		this.id = id;
		this.nom = nom;
		this.quantite = quantite;
		this.prixUni = prixUni;
		this.prixTot = prixTot;
		this.tva = tva;
		this.unite = unite;
		this.taille = taille;
		this.colorS = colorS;
		this.numDevis = numDevis;
	}


	public itemDevis(int id, String nom, double quantite, double prixUni, double prixTot, double tva, String unite,
			int numDevis) {
		super();
		this.id = id;
		this.nom = nom;
		this.quantite = quantite;
		this.prixUni = prixUni;
		this.prixTot = prixTot;
		this.tva = tva;
		this.unite = unite;
		this.numDevis = numDevis;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public double getQuantite() {
		return quantite;
	}


	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}


	public double getPrixUni() {
		return prixUni;
	}


	public void setPrixUni(double prixUni) {
		this.prixUni = prixUni;
	}


	public double getPrixTot() {
		return prixTot;
	}


	public void setPrixTot(double prixTot) {
		this.prixTot = prixTot;
	}


	public double getTva() {
		return tva;
	}


	public void setTva(double tva) {
		this.tva = tva;
	}


	public String getUnite() {
		return unite;
	}


	public void setUnite(String unite) {
		this.unite = unite;
	}


	public int getNumDevis() {
		return numDevis;
	}


	public void setNumDevis(int numDevis) {
		this.numDevis = numDevis;
	}
	
	public int getTaille() {
		return taille;
	}


	public void setTaille(int taille) {
		this.taille = taille;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public Button getColorBnt() {
		return colorBnt;
	}
	

	public void setColorBnt(Button colorBnt) {
		this.colorBnt = colorBnt;
	}

	public String getColorS() {
		return colorS;
	}


	public void setColorS(String colorS) {
		this.colorS = colorS;
	}

	
}
