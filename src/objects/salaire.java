package objects;

public class salaire {
	
	private int id;
	private String periode;
	private Double horaireSalaire;
	private Double vacances;
	private Double feries;
	private Double salaire13;
	private Double salaire_brut;
	private Double avs;
	private Double ac;
	private Double aanp;
	private Double pc;
	private Double lpp;
	private Double crpTransitoire;
	private Double csp;
	private Double ij;
	private Double deduction;
	private Double salaireNet;
	private Double repa;
	private Double frais;
	private Double allocation;
	private Double remboursement;
	private Double paiement;
	private int idEmployer;
	
	public salaire(int id, String periode, Double horaireSalaire, Double vacances, Double feries, Double salaire13,
			Double salaire_brut, Double avs, Double ac, Double aanp, Double pc, Double lpp, Double crpTransitoire,
			Double csp, Double ij, Double deduction, Double salaireNet, Double repa, Double frais, Double allocation,
			Double remboursement, Double paiement, int idEmployer) {
		super();
		this.id = id;
		this.periode = periode;
		this.horaireSalaire = horaireSalaire;
		this.vacances = vacances;
		this.feries = feries;
		this.salaire13 = salaire13;
		this.salaire_brut = salaire_brut;
		this.avs = avs;
		this.ac = ac;
		this.aanp = aanp;
		this.pc = pc;
		this.lpp = lpp;
		this.crpTransitoire = crpTransitoire;
		this.csp = csp;
		this.ij = ij;
		this.deduction = deduction;
		this.salaireNet = salaireNet;
		this.repa = repa;
		this.frais = frais;
		this.allocation = allocation;
		this.remboursement = remboursement;
		this.paiement = paiement;
		this.idEmployer = idEmployer;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPeriode() {
		return periode;
	}


	public void setPeriode(String periode) {
		this.periode = periode;
	}


	public Double getHoraireSalaire() {
		return horaireSalaire;
	}


	public void setHoraireSalaire(Double horaireSalaire) {
		this.horaireSalaire = horaireSalaire;
	}


	public Double getVacances() {
		return vacances;
	}


	public void setVacances(Double vacances) {
		this.vacances = vacances;
	}


	public Double getFeries() {
		return feries;
	}


	public void setFeries(Double feries) {
		this.feries = feries;
	}


	public Double getSalaire13() {
		return salaire13;
	}


	public void setSalaire13(Double salaire13) {
		this.salaire13 = salaire13;
	}


	public Double getSalaire_brut() {
		return salaire_brut;
	}


	public void setSalaire_brut(Double salaire_brut) {
		this.salaire_brut = salaire_brut;
	}


	public Double getAvs() {
		return avs;
	}


	public void setAvs(Double avs) {
		this.avs = avs;
	}


	public Double getAc() {
		return ac;
	}


	public void setAc(Double ac) {
		this.ac = ac;
	}


	public Double getAanp() {
		return aanp;
	}


	public void setAanp(Double aanp) {
		this.aanp = aanp;
	}


	public Double getPc() {
		return pc;
	}


	public void setPc(Double pc) {
		this.pc = pc;
	}


	public Double getLpp() {
		return lpp;
	}


	public void setLpp(Double lpp) {
		this.lpp = lpp;
	}


	public Double getCrpTransitoire() {
		return crpTransitoire;
	}


	public void setCrpTransitoire(Double crpTransitoire) {
		this.crpTransitoire = crpTransitoire;
	}


	public Double getCsp() {
		return csp;
	}


	public void setCsp(Double csp) {
		this.csp = csp;
	}


	public Double getIj() {
		return ij;
	}


	public void setIj(Double ij) {
		this.ij = ij;
	}


	public Double getDeduction() {
		return deduction;
	}


	public void setDeduction(Double deduction) {
		this.deduction = deduction;
	}


	public Double getSalaireNet() {
		return salaireNet;
	}


	public void setSalaireNet(Double salaireNet) {
		this.salaireNet = salaireNet;
	}


	public Double getRepa() {
		return repa;
	}


	public void setRepa(Double repa) {
		this.repa = repa;
	}


	public Double getFrais() {
		return frais;
	}


	public void setFrais(Double frais) {
		this.frais = frais;
	}


	public Double getAllocation() {
		return allocation;
	}


	public void setAllocation(Double allocation) {
		this.allocation = allocation;
	}


	public Double getRemboursement() {
		return remboursement;
	}


	public void setRemboursement(Double remboursement) {
		this.remboursement = remboursement;
	}


	public Double getPaiement() {
		return paiement;
	}


	public void setPaiement(Double paiement) {
		this.paiement = paiement;
	}
	
	
	

}
