package objects;

public class itemsalaire {
	
	private int id;
	private String name;
	private String lastname;
	private Double paiement;
	
	
	public itemsalaire(int id, String name, String lastname, Double paiement) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.paiement = paiement;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Double getPaiement() {
		return paiement;
	}
	public void setPaiement(Double paiement) {
		this.paiement = paiement;
	}
	
	

}
