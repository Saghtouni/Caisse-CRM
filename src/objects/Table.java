package objects;

public class Table {
	private int id;
	private String nomTbale;
	private String nameMachine;
	
	
	public Table(int id, String nomTbale, String nameMachine) {
		super();
		this.id = id;
		this.nomTbale = nomTbale;
		this.nameMachine = nameMachine;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNomTbale() {
		return nomTbale;
	}


	public void setNomTbale(String nomTbale) {
		this.nomTbale = nomTbale;
	}


	public String getNameMachine() {
		return nameMachine;
	}


	public void setNameMachine(String nameMachine) {
		this.nameMachine = nameMachine;
	}


	@Override
	public String toString() {
		return "Table [id=" + id + ", nomTbale=" + nomTbale + ", nameMachine=" + nameMachine + "]";
	}
	
	
	

}
