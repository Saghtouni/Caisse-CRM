package objects;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class ItemFacture {
	
	
	private int id;
	private  String nom;
	private  Double quantite;
	private Double prixunitaire;
	private  Double prixtotal;
	private Double tva;
	private String unit;
	private int taille;
	private Color color;
	private Button colorBnt;
	private String colorS;
	private int numeroFacture;
	
	
	

	public ItemFacture(int id, String nom, Double quantite, Double prixunitaire, Double prixtotal, Double tva,
			String unit, int taille, Color color) {
		super();
		this.id = id;
		this.nom = nom;
		this.quantite = quantite;
		this.prixunitaire = prixunitaire;
		this.prixtotal = prixtotal;
		this.tva = tva;
		this.unit = unit;
		this.taille = taille;
		this.color = color;
	}

	public ItemFacture(String nom, Double quantite, Double prixunitaire, Double prixtotal, Double tva,
			int taille,  String colorS, String unit) {
		super();
		this.nom = nom;
		this.quantite = quantite;
		this.prixunitaire = prixunitaire;
		this.prixtotal = prixtotal;
		this.tva = tva;
		this.unit = unit;
		this.taille = taille;
		this.colorS = colorS;
	}
	public ItemFacture( String nom, Double quantite, Double prixunitaire, Double prixtotal, Double tva,
			String unit) {
		super();
		this.nom = nom;
		this.quantite = quantite;
		this.prixunitaire = prixunitaire;
		this.prixtotal = prixtotal;
		this.tva = tva;
		this.unit = unit;
	}


	public ItemFacture() {
		// TODO Auto-generated constructor stub
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

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Double getPrixunitaire() {
		return prixunitaire;
	}

	public void setPrixunitaire(Double prixunitaire) {
		this.prixunitaire = prixunitaire;
	}

	public Double getPrixtotal() {
		return prixtotal;
	}

	public void setPrixtotal(Double prixtotal) {
		this.prixtotal = prixtotal;
	}

	public Double getTva() {
		return tva;
	}

	public void setTva(Double tva) {
		this.tva = tva;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public int getNumeroFacture() { 
		return numeroFacture;
	}

	public void setNumeroFacture(int numeroFacture) {
		this.numeroFacture = numeroFacture;
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
