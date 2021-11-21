package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Fenetre.Salairecontrol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Employer;
import objects.Produits;
import objects.Utilisateurs;
import objects.salaire;

public class SalaireDB {
	
	public Employer Employer = null;
	private Connection DB = null;
	private ObservableList<Employer> data = FXCollections.observableArrayList();
	private ObservableList<salaire> dataSalaire = FXCollections.observableArrayList();
	
	public SalaireDB(Connection dB) {
		super();
		DB = dB;
	}
	
	
	
	public void addEmployer( String sex, String name,  String lastname, String adresse,  String nopersonnel,  String classe,  String profession, 
							Double base_salaire, Double taux, String entredate, String iban, String banque) {
		  
				System.out.println("Heloo");
				PreparedStatement stm;
		   

				try {
					stm = DB.prepareStatement("INSERT INTO employer (sex, name, lastname, adresse, nopersonnel, classe, profession, base_salaire, taux, entrerdate, IBAN, banque ) Values(  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );
					stm.setString(1, sex);
					stm.setString(2, name);
					stm.setString(3, lastname);
					stm.setString(4, adresse);
					stm.setString(5, nopersonnel);
					stm.setString(6, classe);
					stm.setString(7, profession);
					stm.setDouble(8, base_salaire);
					stm.setDouble(9, taux);
					stm.setString(10, entredate);
					stm.setString(11, iban);
					stm.setString(12, banque);
					stm.executeUpdate();
					stm.close();
				} catch (SQLException e) {
					e.toString();
				}  
	   }
	
	public ObservableList<Employer> loadEmployer(){
		try {
			//System.out.println("1st connection");
			PreparedStatement stm = DB.prepareStatement("select * from employer");
			ResultSet Rs = stm.executeQuery();
			while(Rs.next()) {
				data.add(new Employer((Integer) Rs.getObject(1), (String) Rs.getObject(2), (String) Rs.getObject(3), (String) Rs.getObject(4),
						(String) Rs.getObject(5), (String) Rs.getObject(6), (String) Rs.getObject(7),(String) Rs.getObject(8),
						(Double) Rs.getObject(9) , (Double) Rs.getObject(10), (String) Rs.getObject(11)
						, (String) Rs.getObject(12), (String) Rs.getObject(13)));
			}
			Rs.close();
			stm.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}
			return data;
   }


	 public long getLAstID() {
		 long id = 0;
		   try {
			   
				PreparedStatement stm = DB.prepareStatement("select nextval('employer_id_seq'::regclass)");
				//System.out.println("requete  =  "+stm);
				ResultSet Rs = stm.executeQuery();
				while(Rs.next()) 
				{
					id = (long) Rs.getObject(1);
					System.out.println(id);
				}
				Rs.close();
				stm.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
		   return id;
	   }
	 
	 public ObservableList<Employer> searchUEmpl(String s) throws SQLException{
		  	try {
				
				PreparedStatement stm = DB.prepareStatement("select * from employer where lower(name) like '%"+s+"%' ");
				//System.out.println("requete  =  "+stm);
				ResultSet Rs = stm.executeQuery();
				while(Rs.next()) 
				{
					data.add(new Employer((Integer) Rs.getObject(1), (String) Rs.getObject(2), (String) Rs.getObject(3), (String) Rs.getObject(4),
							(String) Rs.getObject(5), (String) Rs.getObject(6), (String) Rs.getObject(7),(String) Rs.getObject(8),
							(Double) Rs.getObject(9) , (Double) Rs.getObject(10), (String) Rs.getObject(11)
							, (String) Rs.getObject(12), (String) Rs.getObject(13)));
				}
				Rs.close();
				stm.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
				return data;
	        
	    }

	   public Employer get(int id) {  
		   try {
				PreparedStatement stm = DB.prepareStatement("select *from employer where id= "+id);
				ResultSet Rs = stm.executeQuery();	
				while(Rs.next()) {
					Employer = new Employer((Integer) Rs.getObject(1), (String) Rs.getObject(2), (String) Rs.getObject(3), (String) Rs.getObject(4),
							(String) Rs.getObject(5), (String) Rs.getObject(6), (String) Rs.getObject(7),(String) Rs.getObject(8),
							(Double) Rs.getObject(9) , (Double) Rs.getObject(10), (String) Rs.getObject(11)
							, (String) Rs.getObject(12), (String) Rs.getObject(13));
					
				}
				
				Rs.close();
				stm.close();
				} catch (SQLException e) {
				}
				
				return Employer;
	   		}
	   
		public void updatEmployer( String name,  String lastname, String adresse, String numeroEmployer,   String classe,  String profession, 
				Double base_salaire, Double taux, String entredate,  String iban, String banque, int id) {
			  
			System.out.println("Heloo");
			PreparedStatement stm;
			   
			   
					try {
						stm = DB.prepareStatement("UPDATE employer SET name = ?, lastname = ?, adresse = ?, nopersonnel = ?, classe = ?, profession = ?, base_salaire = ?"
								+ ", taux = ?, entrerdate = ?, IBAN = ?, banque = ?  WhERE id=" +id );
						stm.setString(1, name);
						stm.setString(2, lastname);
						stm.setString(3, adresse);
						stm.setString(4, numeroEmployer);
						stm.setString(5, classe);
						stm.setString(6, profession);
						stm.setDouble(7, base_salaire);
						stm.setDouble(8, taux);
						stm.setString(9, entredate);
						stm.setString(10, iban);
						stm.setString(11, banque);
						stm.executeUpdate();
						stm.close();
					} catch (SQLException e) {
						e.toString();
					}  
		   }
		
		  public void delete(int id) {
			   PreparedStatement stm;
			try {
				stm = DB.prepareStatement("DELETE FROM employer WHERE id="+ id);
				stm.executeUpdate();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		   }
		  
		  public void addSalaire(String periode ,Double  horaire_salaire, Double vacances, Double feries, Double salaire13, Double salaire_brut, Double AVS,
				  				Double AC, Double AANP, Double PC, Double LPP, Double CRP_transitoire, Double CSP, Double IJ, Double deduction, Double salaire_net,
				  				Double repa, Double frais, Double allocation, Double remboursement, Double paiement, int id ) {
			  
				PreparedStatement stm;

				try {
					stm = DB.prepareStatement("INSERT INTO salaire (periode, horaire_salaire, vacances, feries, salaire13, salaire_brut, AVS, AC, AANP, PC,"
											 + " LPP, CRP_transitoire, CSP, IJ, deduction, salaire_net , repa, frais, allocation, remboursement , paiement, id_employer)"
											 + " Values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );
					stm.setString(1, periode);
					stm.setDouble(2, horaire_salaire);
					stm.setDouble(3, vacances);
					stm.setDouble(4, feries);
					stm.setDouble(5, salaire13);
					stm.setDouble(6, salaire_brut);
					stm.setDouble(7, AVS);
					stm.setDouble(8, AC);
					stm.setDouble(9, AANP);
					stm.setDouble(10, PC);
					stm.setDouble(11, LPP);
					stm.setDouble(12, CRP_transitoire);
					stm.setDouble(13, CSP);
					stm.setDouble(14, IJ);
					stm.setDouble(15, deduction);
					stm.setDouble(16, salaire_net);
					stm.setDouble(17, repa);
					stm.setDouble(18, frais);
					stm.setDouble(19, allocation);
					stm.setDouble(20, remboursement);
					stm.setDouble(21, paiement);
					stm.setInt(22, id);
					System.out.println("ID BASE :" +id);
					stm.executeUpdate();
					stm.close();
				} catch (SQLException e) {
					e.toString();
				}  
			  
			  
		  }
		
			public ObservableList<salaire> loadSalaire(int id){
				try {
					//System.out.println("1st connection");
					PreparedStatement stm = DB.prepareStatement("select * from salaire where id_employer =" +id);
					ResultSet Rs = stm.executeQuery();
					while(Rs.next()) {
						dataSalaire.add(new salaire((Integer) Rs.getObject(1), (String) Rs.getObject(2),(Double) Rs.getObject(3), (Double) Rs.getObject(4), (Double) Rs.getObject(5),
													(Double) Rs.getObject(6), (Double) Rs.getObject(7), (Double) Rs.getObject(8), (Double) Rs.getObject(9), (Double) Rs.getObject(10),
													(Double) Rs.getObject(11), (Double) Rs.getObject(12), (Double) Rs.getObject(13), (Double) Rs.getObject(14), (Double) Rs.getObject(15),
													(Double) Rs.getObject(16), (Double) Rs.getObject(17), (Double) Rs.getObject(18), (Double) Rs.getObject(19), (Double) Rs.getObject(20),
													(Double) Rs.getObject(21), (Double) Rs.getObject(22), (Integer) Rs.getObject(23)));
					}
					Rs.close();
					stm.close();
					} catch (SQLException e) {
					e.printStackTrace();
					}
					return dataSalaire;
		   }
}
