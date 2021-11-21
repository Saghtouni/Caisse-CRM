package objects;

public class Produits {
	private Integer id;
	private String reference;
	private String nom;
	private Double prixAchat;
	private Double prixvente;
	private String type;
	private Integer quantite;
	private String fornisseur;
	private Double TVA;
	private String image;
	private String unit;
	private String code_bare;
	
	
	public Produits(Integer id, String reference, String nom, Double prixAchat, Double prixvente, String type,
			Integer quantite, String fornisseur, Double tVA, String image, String unit, String code_bare) {
		super();
		this.id = id;
		this.reference = reference;
		this.nom = nom;
		this.prixAchat = prixAchat;
		this.prixvente = prixvente;
		this.type = type;
		this.quantite = quantite;
		this.fornisseur = fornisseur;
		TVA = tVA;
		this.image = image;
		this.unit = unit;
		this.code_bare = code_bare;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Double getPrixAchat() {
		return prixAchat;
	}


	public void setPrixAchat(Double prixAchat) {
		this.prixAchat = prixAchat;
	}


	public Double getPrixvente() {
		return prixvente;
	}


	public void setPrixvente(Double prixvente) {
		this.prixvente = prixvente;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Integer getQuantite() {
		return quantite;
	}


	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}


	public String getFornisseur() {
		return fornisseur;
	}


	public void setFornisseur(String fornisseur) {
		this.fornisseur = fornisseur;
	}


	public Double getTVA() {
		return TVA;
	}


	public void setTVA(Double tVA) {
		TVA = tVA;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public String getCode_bare() {
		return code_bare;
	}


	public void setCode_bare(String code_bare) {
		this.code_bare = code_bare;
	}

	
	
}
