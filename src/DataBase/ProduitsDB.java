package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DialogueAlerte.DialogueAlerteSql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.ItemFacture;
import objects.Produits;
import objects.Utilisateurs;

public class ProduitsDB {
	
	private Connection DB = null;

	public ProduitsDB(Connection dB) {
		super();
		DB = dB;
	}

	private ObservableList<Produits> data = FXCollections.observableArrayList();
	private ObservableList<String> type = FXCollections.observableArrayList();
	private ObservableList<String> NomProduit = FXCollections.observableArrayList();
	private ObservableList<Produits> dataType = FXCollections.observableArrayList();
	private Produits produits;
	private DialogueAlerteSql dialogueAlerteSql;
	
	public ObservableList<Produits> load(){
		try {
			PreparedStatement stm = DB.prepareStatement("select *from produits");
			ResultSet Rs = stm.executeQuery();
			while(Rs.next()) {
				data.add(new Produits((Integer) Rs.getObject(1),  (String) Rs.getObject(2),(String) Rs.getObject(3), (Double) Rs.getObject(4), (Double) Rs.getObject(5), (String) Rs.getObject(6), (Integer) Rs.getObject(7),  (String) Rs.getObject(8),(Double) Rs.getObject(9), (String) Rs.getObject(10), (String) Rs.getObject(11), (String) Rs.getObject(12)));
			}
			Rs.close();
			stm.close();
			} catch (SQLException e) {
			e.printStackTrace();	
			}
			return data;	
   }
	
	
	public void add( String reference,  String nom, Double prixA,  Double prixV,  String type, int quantite,  String fornisseur, Double tva, String image,String unit,  String code_bare) {
		
		PreparedStatement stm;
				try {
					stm = DB.prepareStatement("INSERT INTO produits (reference, nom, prixAchat, prixVente, type, quantite, fornisseur, tva, image, unit, code_bare) Values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );
					stm.setString(1, reference);
					stm.setString(2, nom);
					stm.setDouble(3, prixA);
					stm.setDouble(4, prixV);
					stm.setString(5,  type);
					stm.setInt(6, quantite);
					stm.setString(7, fornisseur);
					stm.setDouble(8, tva);
					stm.setString(9, image);
					stm.setString(10, unit);
					stm.setString(11, code_bare);
					stm.executeUpdate();
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				load();
	 	}
	
	 public void update(int id, String reference,  String nom, Double prixA,  Double prixV,  String type, int quantite,  String fornisseur, Double tva, String image, String unit, String code_bare) {
		   PreparedStatement stm;
		  
			try {
					//System.out.println("OK");
					stm = DB.prepareStatement("UPDATE produits SET  reference = ?, nom = ?, prixachat = ?, prixvente = ?, type = ?, quantite = ?, fornisseur = ?, tva = ?,image = ?, unit = ?, code_bare = ? WHERE id =" + id );
					stm.setString(1, reference);
					stm.setString(2, nom);
					stm.setDouble(3, prixA);
					stm.setDouble(4, prixV);
					stm.setString(5,  type);
					stm.setInt(6, quantite);
					stm.setString(7, fornisseur);
					stm.setDouble(8, tva);
					stm.setString(9, image);
					stm.setString(10, unit);
					stm.setString(11, code_bare);
					stm.executeUpdate();
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	   }
	 
	 public Produits get(int id) {
		   try { 
				PreparedStatement stm = DB.prepareStatement("select *from produits where id= "+ id );
				ResultSet Rs = stm.executeQuery();	
				while(Rs.next()) {
					produits = new Produits((Integer) Rs.getObject(1),  (String) Rs.getObject(2),(String) Rs.getObject(3), (Double) Rs.getObject(4), (Double) Rs.getObject(5), (String) Rs.getObject(6), (Integer) Rs.getObject(7),  (String) Rs.getObject(8),(Double) Rs.getObject(9), (String) Rs.getObject(10),(String) Rs.getObject(11), (String) Rs.getObject(12));	
				}
				Rs.close();
				stm.close();	
				} catch (SQLException e) {
				e.printStackTrace();	
				}
				return produits; 
	   		}
	 public Produits getDivers() {
		   try { 
				PreparedStatement stm = DB.prepareStatement("select *from produits where nom='divers' " );
				ResultSet Rs = stm.executeQuery();	
				while(Rs.next()) {
					produits = new Produits((Integer) Rs.getObject(1),  (String) Rs.getObject(2),(String) Rs.getObject(3), (Double) Rs.getObject(4), (Double) Rs.getObject(5), (String) Rs.getObject(6), (Integer) Rs.getObject(7),  (String) Rs.getObject(8),(Double) Rs.getObject(9), (String) Rs.getObject(10),(String) Rs.getObject(11),  (String) Rs.getObject(12));	
				}
				Rs.close();
				stm.close();	
				} catch (SQLException e) {
				e.printStackTrace();	
				}
				return produits; 
	   		}
	 public void delete(int id) {
		   PreparedStatement stm = null;
		try {
			stm = DB.prepareStatement("DELETE FROM produits WHERE id="+ id);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			dialogueAlerteSql = new DialogueAlerteSql(e, "Dialogue d'exception", "Regardez, la boîte de dialogue d'exception", "problème de connexion à la base de données!");
		} 
	 }
	 
	 public ObservableList<String> getType(){
			try {
				type.clear();
				PreparedStatement stm = DB.prepareStatement("SELECT DISTINCT type FROM produits ORDER BY type DESC");
				ResultSet Rs = stm.executeQuery();
				while(Rs.next()) {
					type.add((String) Rs.getObject(1));
				}
				Rs.close();
				stm.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			//System.out.println("size  = "+type.size());
				return type;	
	   }
	 
	 public ObservableList<String> getNomProduits(String type){
			try {
				PreparedStatement stm = DB.prepareStatement("SELECT distinct nom FROM produits WHERE type ="+ "'" +type +"'" + "ORDER BY nom DESC");
				ResultSet Rs = stm.executeQuery();
				while(Rs.next()) {
					NomProduit.add((String) Rs.getObject(1));
				}
				Rs.close();
				stm.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
				return NomProduit;	
	   }
	 
	 public Produits getName(String nomProduit) {
		   try {
				PreparedStatement stm = DB.prepareStatement("select *from produits where nom= "+ "'" + nomProduit + "'" );
				ResultSet Rs = stm.executeQuery();
				while(Rs.next()) {
					produits = new Produits((Integer) Rs.getObject(1),  (String) Rs.getObject(2),(String) Rs.getObject(3), (Double) Rs.getObject(4), (Double) Rs.getObject(5), (String) Rs.getObject(6), (Integer) Rs.getObject(7),  (String) Rs.getObject(8),(Double) Rs.getObject(9), (String) Rs.getObject(10), (String) Rs.getObject(11), (String) Rs.getObject(12));	
				}
				Rs.close();
				stm.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
				return produits;
	   		}
	 
	  public ObservableList<Produits> searchProduits(String s) throws SQLException{
		  	try {
				
				PreparedStatement stm = DB.prepareStatement("select * from produits where lower(nom) like '%"+s.toLowerCase()+"%' ");
				//System.out.println("requete  =  "+stm);
				ResultSet Rs = stm.executeQuery();
				while(Rs.next()) 
				{
					data.add(new Produits((Integer) Rs.getObject(1),  (String) Rs.getObject(2),(String) Rs.getObject(3), (Double) Rs.getObject(4), (Double) Rs.getObject(5), (String) Rs.getObject(6), (Integer) Rs.getObject(7),  (String) Rs.getObject(8),(Double) Rs.getObject(9), (String) Rs.getObject(10), (String) Rs.getObject(11), (String) Rs.getObject(12)));
				}
				Rs.close();
				stm.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
				return data;
	        
	    }
	  public ItemFacture getPrixTVA(String s) throws SQLException{
		  ItemFacture x = new ItemFacture() ;
		  	try {
				
				PreparedStatement stm = DB.prepareStatement("select prixvente,tva from produits where nom = ?");
				//System.out.println("requete  =  "+stm);
				stm.setString(1, s);
				ResultSet Rs = stm.executeQuery();
				
				while(Rs.next()) 
				{
					x.setPrixunitaire((Double) Rs.getObject(1));
					x.setTva((Double) Rs.getObject(2));

				}
				Rs.close();
				stm.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
				return x;
	        
	    }
	  public String getUnit(String s) throws SQLException{
		  String name = null;
		  	try {
				
				PreparedStatement stm = DB.prepareStatement("select unit from produits where nom = ?");
				stm.setString(1, s);
				//System.out.println("requete  =  "+stm);
				ResultSet Rs = stm.executeQuery();
				
				while(Rs.next()) 
				{
			
					name = (String)Rs.getObject(1);

				}
				Rs.close();
				stm.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
				return name;
	        
	    }
	  public String getCategorie(String nom){
		  String f="";
			try {
				PreparedStatement stm = DB.prepareStatement("SELECT distinct type FROM produits WHERE nom ="+ "'" +nom +"' ");
				//System.out.println("requete  =  "+stm);
				ResultSet Rs = stm.executeQuery();
				while(Rs.next()) {
					f=(String) Rs.getObject(1);
				}
				Rs.close();
				stm.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
				return f;	
	   }

	  
}
