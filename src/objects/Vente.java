package objects;

import java.sql.Date;

public class Vente {
	private Integer id;
	private String nomProduits;
	private Double prixVente;
	private Integer qunatite;
	private Double TVA;
	private Double TVAC;
	private Integer numeroVente;
	
	
	public Vente(Integer id, String nomProduits, Double prixVente, Integer qunatite, Double tVA,Integer numeroVente) {
		super();
		this.id = id;
		this.nomProduits = nomProduits;
		this.prixVente = prixVente;
		this.qunatite = qunatite;
		TVA = tVA;
		//TVAC = tVAC;
		this.numeroVente = numeroVente;
	}
	
	public Vente(int i, String nom, Double prixvente2, int j, Double tva2, double d, int k) {
		super();
		this.id = i;
		this.nomProduits = nom;
		this.prixVente = prixvente2;
		this.qunatite = j;
		TVA = tva2;
		TVAC = d;
		this.numeroVente = k;
		
	}
	public Vente(String nom, Double prixvente2,int k) {
		super();

		this.nomProduits = nom;
		this.prixVente = prixvente2;
		this.qunatite = k;

		
	}

	public Vente() {
		super();
		this.id = 0;
		this.nomProduits = "";
		this.prixVente = 0.0;
		this.qunatite = 0;
		TVA = 0.0;
		//TVAC = tVAC;
		this.numeroVente = 0;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomProduits() {
		return nomProduits;
	}
	public void setNomProduits(String nomProduits) {
		this.nomProduits = nomProduits;
	}
	public Double getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(Double prixVente) {
		this.prixVente = prixVente;
	}
	public Integer getQunatite() {
		return qunatite;
	}
	public void setQunatite(Integer qunatite) {
		this.qunatite = qunatite;
	}
	public Double getTVA() {
		return TVA;
	}
	public void setTVA(Double tVA) {
		TVA = tVA;
	}
	public Double getTVAC() {
		return TVAC;
	}
	public void setTVAC(Double tVAC) {
		TVAC = tVAC;
	}
	public Integer getNumeroVente() {
		return numeroVente;
	}
	public void setNumeroVente(Integer numeroVente) {
		this.numeroVente = numeroVente;
	}

	@Override
	public String toString() {
		return "Vente [id=" + id + ", nomProduits=" + nomProduits + ", prixVente=" + prixVente + ", qunatite="
				+ qunatite + ", TVA=" + TVA + ", TVAC=" + TVAC + ", numeroVente=" + numeroVente + "]";
	}


	

	
}
	