package objects;

public class Employer {
	
	private int id;
	private String sex;
	private String name;
	private String lastname;
	private String adresse;
	private String nopersonnel;
	private String classe;
	private String profession;
	private Double base_salaire;
	private Double taux;
	private String entredate;
	private String iban;
	private String banque;
	
	
	public Employer(int id, String sex, String name, String lastname, String adresse, String nopersonnel, String classe,
			String profession, Double base_salaire, Double taux, String entredate, String iban,
			String banque) {
		super();
		this.id = id;
		this.sex = sex;
		this.name = name;
		this.lastname = lastname;
		this.adresse = adresse;
		this.nopersonnel = nopersonnel;
		this.classe = classe;
		this.profession = profession;
		this.base_salaire = base_salaire;
		this.taux = taux;
		this.entredate = entredate;
		this.iban = iban;
		this.banque = banque;
	}
	
	public Employer(String sex, String name, String lastname, String adresse, String nopersonnel, String classe,
			String profession, Double base_salaire, Double taux, String entredate, String iban,
			String banque) {
		super();
		this.sex = sex;
		this.name = name;
		this.lastname = lastname;
		this.adresse = adresse;
		this.nopersonnel = nopersonnel;
		this.classe = classe;
		this.profession = profession;
		this.base_salaire = base_salaire;
		this.taux = taux;
		this.entredate = entredate;
		this.iban = iban;
		this.banque = banque;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getNopersonnel() {
		return nopersonnel;
	}


	public void setNopersonnel(String nopersonnel) {
		this.nopersonnel = nopersonnel;
	}


	public String getClasse() {
		return classe;
	}


	public void setClasse(String classe) {
		this.classe = classe;
	}


	public String getProfession() {
		return profession;
	}


	public void setProfession(String profession) {
		this.profession = profession;
	}


	public Double getBase_salaire() {
		return base_salaire;
	}


	public void setBase_salaire(Double base_salaire) {
		this.base_salaire = base_salaire;
	}


	public Double getTaux() {
		return taux;
	}


	public void setTaux(Double taux) {
		this.taux = taux;
	}


	public String getEntredate() {
		return entredate;
	}


	public void setEntredate(String entredate) {
		this.entredate = entredate;
	}


	public String getIban() {
		return iban;
	}


	public void setIban(String iban) {
		this.iban = iban;
	}


	public String getBanque() {
		return banque;
	}


	public void setBanque(String banque) {
		this.banque = banque;
	}
	
	
	
	
}
