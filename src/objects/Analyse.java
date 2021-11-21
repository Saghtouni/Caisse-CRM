package objects;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Analyse {
	private int id;
	private String date_heure;
	private String nomUtil;
	private String prenomUtil;
	private Button produit;
	private Double tva;
	private Double somme;
	private Button status;
	private Button actions;
	private String remarque;
	


	public Analyse(int id, String date_heure, String nomUtil, String prenomUtil, Button produit, Double tva,
			Double somme) {
		super();
		this.id = id;
		this.date_heure = date_heure;
		this.nomUtil = nomUtil;
		this.prenomUtil = prenomUtil;
		this.produit = produit;
		this.tva = tva;
		this.somme = somme;
	}
	
	public Analyse(String date_heure, Double somme, Button produit,Button status) {
		super();
		this.date_heure = date_heure;
		this.produit = produit;
		this.somme = somme;
		this.status=status;
	}
	public Analyse(String date_heure, Double somme, Button produit,Button status,Button actions) {
		super();
		this.date_heure = date_heure;
		this.produit = produit;
		this.somme = somme;
		this.status=status;
		this.actions=actions;
	}


	public Analyse(Double somme, Button produit, Button status) {
		
		this.produit = produit;
		this.somme = somme;
		this.status=status;	
		}
public Analyse(long id, String nomUtil) {
		
	this.nomUtil = nomUtil;
		this.id=(int)id;
		}

	public Analyse(Double value, String key) {
		this.date_heure = key;

		this.somme=value;
		}
	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate_heure() {
		return date_heure;
	}
	public void setDate_heure(String date_heure) {
		this.date_heure = date_heure;
	}
	public String getNomUtil() {
		return nomUtil;
	}
	public void setNomUtil(String nomUtil) {
		this.nomUtil = nomUtil;
	}
	public String getPrenomUtil() {
		return prenomUtil;
	}
	public void setPrenomUtil(String prenomUtil) {
		this.prenomUtil = prenomUtil;
	}
	public Button getProduit() {
		return produit;
	}
	public void setProduit(Button produit) {
		this.produit = produit;
	}
	public Double getTva() {
		return tva;
	}
	public void setTva(Double tva) {
		this.tva = tva;
	}
	public Double getSomme() {
		return somme;
	}
	public void setSomme(Double somme) {
		this.somme = somme;
	}

	public Button getStatus() {
		return status;
	}

	public void setStatus(Button status) {
		this.status = status;
	}

	public Button getActions() {
		return actions;
	}

	public void setActions(Button actions) {
		this.actions = actions;
	}

	

}
