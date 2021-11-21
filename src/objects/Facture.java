package objects;


public class Facture {
	
	private int id;
	private String date;
	private Double totalnet;
	private Double tva;
	private Double totalbrut;
	private	String remarque;
	private int client;
	
	public Facture(String date, Double totalnet, Double tva, Double totalbrut, int client,String remarque) {
		super();
		this.date = date;
		this.totalnet = totalnet;
		this.tva = tva;
		this.totalbrut = totalbrut;
		this.client = client;
		this.remarque=remarque;
	}
	public Facture() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getTotalnet() {
		return totalnet;
	}
	public void setTotalnet(Double totalnet) {
		this.totalnet = totalnet;
	}
	public Double getTva() {
		return tva;
	}
	public void setTva(Double tva) {
		this.tva = tva;
	}
	public Double getTotalbrut() {
		return totalbrut;
	}
	public void setTotalbrut(Double totalbrut) {
		this.totalbrut = totalbrut;
	}
	public int getClient() {
		return client;
	}
	public void setClient(int client) {
		this.client = client;
	}
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	@Override
	public String toString() {
		return "Facture [id=" + id + ", date=" + date + ", totalnet=" + totalnet + ", tva=" + tva + ", totalbrut="
				+ totalbrut + ", remarque=" + remarque + ", client=" + client + "]";
	}
	
	
	


}
