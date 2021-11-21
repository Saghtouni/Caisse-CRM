package objects;

public class devis {
	
	
	private int id;
	
	private String date;
  
    private double totalnet;
   
    private double tva;
   
    private double totalbrut;
   
    private double reduction;
    
    private String remarque;
   
    private String unit;
    
    private int client;

	
	

	public devis(int id, String date, double totalnet, double tva, double totalbrut, String remarque, String unit,
			int client, double reduction) {
		super();
		this.id = id;
		this.date = date;
		this.totalnet = totalnet;
		this.tva = tva;
		this.totalbrut = totalbrut;
		this.remarque = remarque;
		this.unit = unit;
		this.client = client;
		this.reduction = reduction;
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

	public double getTotalnet() {
		return totalnet;
	}

	public void setTotalnet(double totalnet) {
		this.totalnet = totalnet;
	}

	public double getTva() {
		return tva;
	}

	public void setTva(double tva) {
		this.tva = tva;
	}

	public double getTotalbrut() {
		return totalbrut;
	}

	public void setTotalbrut(double totalbrut) {
		this.totalbrut = totalbrut;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public double getReduction() {
		return reduction;
	}

	public void setReduction(double reduction) {
		this.reduction = reduction;
	}
}
