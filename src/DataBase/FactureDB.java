package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import DialogueAlerte.DialogueAlerteSql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import objects.Client;
import objects.Facture;
import objects.ItemFacture;
import objects.devis;
import objects.itemDevis;

public class FactureDB {

	private Connection DB = null;
	private ObservableList<Facture> data = FXCollections.observableArrayList();
	private ObservableList<devis> dataD = FXCollections.observableArrayList();
	private ObservableList<itemDevis> dataID = FXCollections.observableArrayList();
	private static int pkF ;
	private static int pkD ;
	

	public FactureDB(Connection dB2) {
		DB=dB2;
	}

	public void add(ObservableList<ItemFacture> itemsF, String date, Double totalnet, Double tva, Double totalbrut,
			Integer client, String remarque, String unit) throws SQLException {

		PreparedStatement stm2;
		int idrecup = 0;
		// data.add(new Facture(date, totalnet, tva, totalbrut, client, remarque));
		try {
			stm2 = DB.prepareStatement(
					"INSERT INTO Facture (date, totalnet, tva, totalbrut, client, remarque, unit) Values(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stm2.setString(1, date);
			stm2.setDouble(2, totalnet);
			stm2.setDouble(3, tva);
			stm2.setDouble(4, totalbrut);
			stm2.setInt(5, client);
			stm2.setString(6, remarque);
			stm2.setString(7, unit);
			stm2.executeUpdate();
			// RETURN les id généres automatiquement afin de l'utilisr pour inserer les
			// details des produits dans produitcaisse
			ResultSet rs = stm2.getGeneratedKeys();
			rs.next();
			this.pkF = rs.getInt(1);
			//System.out.println("id facture crée"+idrecup);
			stm2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PreparedStatement stm = null;
		
		for (ItemFacture i : itemsF) {
			if(i.getPrixunitaire() != null) {
				stm = DB.prepareStatement("INSERT INTO itemfacture (nom, quantite, prixunitaire, prittotal, numerofacture, tva, unit) Values(?,?,?,?,?,?,?)");
				stm.setString(1, i.getNom());
				stm.setDouble(2, i.getQuantite());
				stm.setDouble(3, i.getPrixunitaire());
				stm.setDouble(4, i.getPrixtotal());
				stm.setInt(5, this.pkF);
				stm.setDouble(6, (i.getTva()*i.getQuantite()/100)); // TVA !!!! 
				stm.setString(7, i.getUnit());
				stm.executeUpdate();
			} else {
				stm = DB.prepareStatement("INSERT INTO itemfacture (nom, quantite, prixunitaire, prittotal, numerofacture, tva, unit) Values(?,?,?,?,?,?,?)");
				stm.setString(1, i.getNom());
				stm.setInt(2, 0);
				stm.setDouble(3, 0.0);
				stm.setDouble(4, 0.0);
				stm.setInt(5, this.pkF);
				stm.setDouble(6, 0.0); // TVA !!!! 
				stm.setString(7, "");
				stm.executeUpdate();
			}
		}
		stm.close();

	}
	
	public void addDevis(ObservableList<ItemFacture> itemsF, String date, Double totalnet, Double tva, Double totalbrut,
			Integer client, String remarque, String unit, double reduction) throws SQLException {

		PreparedStatement stm2;
		int idrecup = 0;
		// data.add(new Facture(date, totalnet, tva, totalbrut, client, remarque));
		try {
			stm2 = DB.prepareStatement(
					"INSERT INTO devis (date, totalnet, tva, totalbrut, client, remarque, unit, reduction) Values(?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stm2.setString(1, date);
			stm2.setDouble(2, totalnet);
			stm2.setDouble(3, tva);
			stm2.setDouble(4, totalbrut);
			stm2.setInt(5, client);
			stm2.setString(6, remarque);
			stm2.setString(7, unit);
			stm2.setDouble(8, reduction);
			stm2.executeUpdate();
			ResultSet rs = stm2.getGeneratedKeys();
			rs.next();
			this.pkD = rs.getInt(1);
			stm2.close();
			System.out.print(date+"  "+totalnet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PreparedStatement stm = null;
		
		for (ItemFacture i : itemsF) {
			if(i.getPrixunitaire() != null) {
				stm = DB.prepareStatement("INSERT INTO itemdevis (nom, quantite, prixunitaire, prittotal, tva, unit, taille, colord, numerodevis) Values(?,?,?,?,?,?,?,?,?)");
				stm.setString(1, i.getNom());
				stm.setDouble(2, i.getQuantite());
				stm.setDouble(3, i.getPrixunitaire());
				stm.setDouble(4, i.getPrixtotal());
				stm.setDouble(5, i.getTva()); // TVA !!!! 
				stm.setString(6, i.getUnit());
				
				if(i.getTaille() != 0)
					stm.setInt(7, i.getTaille());
				else
					stm.setInt(7, 11);
				
				if(i.getColor() != null)
					stm.setString(8,  toRGBCode(i.getColor()));
				else
					stm.setString(8,  toRGBCode(Color.BLACK));
				
				stm.setInt(9, this.pkD);
				stm.executeUpdate();
			}
			else {
				stm = DB.prepareStatement("INSERT INTO itemdevis (nom, quantite, prixunitaire, prittotal, tva, unit, taille, colord, numerodevis) Values(?,?,?,?,?,?,?,?,?)");
				stm.setString(1, i.getNom());
				stm.setInt(2, 0);
				stm.setDouble(3, 0.0);
				stm.setDouble(4, 0.0);
				stm.setDouble(5, 0.0); // TVA !!!! 
				stm.setString(6, "");
				stm.setInt(7, i.getTaille());
				stm.setString(8, toRGBCode(i.getColor()));
				stm.setInt(9, this.pkD);
				stm.executeUpdate();
			}

		}
		stm.close();

	}
	
	 public static String toRGBCode( Color color )
     {
         return String.format( "#%02X%02X%02X",
             (int)( color.getRed() * 255 ),
             (int)( color.getGreen() * 255 ),
             (int)( color.getBlue() * 255 ) );
     }
	public ObservableList<devis> getDevis() {
		PreparedStatement stm;
		try {
			stm = DB.prepareStatement("SELECT * FROM devis");
	
		ResultSet Rs = stm.executeQuery();	
		while(Rs.next()) {
			dataD.add(new devis ((Integer) Rs.getObject(1),(String) Rs.getObject(2), (double) Rs.getObject(3), (double) Rs.getObject(4), (double) Rs.getObject(5),
					(String) Rs.getObject(6), (String)Rs.getObject(7), (Integer) Rs.getObject(8), (double) Rs.getObject(9)));
			System.out.println("cleine :" +(String)Rs.getObject(6));
		}
		Rs.close();
		stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataD;
		
	}
	
	public ObservableList<itemDevis> getItemDevis(int numeroDevis) {
			PreparedStatement stm;
		
		try {
			stm = DB.prepareStatement("SELECT * FROM itemdevis where numerodevis="+ numeroDevis);
	
			ResultSet Rs = stm.executeQuery();	
			while(Rs.next()) {
				dataID.add(new itemDevis ((Integer) Rs.getObject(1),(String) Rs.getObject(2), (double) Rs.getObject(3), (double) Rs.getObject(4), (double) Rs.getObject(5),
						(double) Rs.getObject(6), (String)Rs.getObject(7), (Integer) Rs.getObject(8), (String) Rs.getObject(9), (Integer) Rs.getObject(10)));
				System.out.println("Numero devis :" +(Integer)Rs.getObject(8));
		}
			Rs.close();
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataID;
		
	}
	
	public Double getReduction(int numeroDevis) {
		Double reduction = 0.0;
		PreparedStatement stm1;
		
		try {
			stm1 = DB.prepareStatement("SELECT * FROM devis where id="+ numeroDevis);
	
			ResultSet Rs1 = stm1.executeQuery();	
			while(Rs1.next()) {
				reduction = (double) Rs1.getObject(9);
				
		}
			Rs1.close();
			stm1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reduction;
	}
	
	// nombre de facture !!!
	public Integer getFactureLastId() throws SQLException
	{
		Integer x=0;
		
		PreparedStatement stm = DB.prepareStatement("SELECT id FROM Facture ORDER BY ID DESC LIMIT 1");
		ResultSet Rs = stm.executeQuery();	
		while(Rs.next()) {
			x=(Integer) Rs.getObject(1)+1;
		}
		Rs.close();
		stm.close();	
		return x;
	}
	
	public Integer getDevisLastId() throws SQLException
	{
		Integer x=0;
		
		PreparedStatement stm = DB.prepareStatement("SELECT id FROM devis ORDER BY ID DESC LIMIT 1");
		ResultSet Rs = stm.executeQuery();	
		while(Rs.next()) {
			x=(Integer) Rs.getObject(1)+1;
		}
		Rs.close();
		stm.close();	
		return x;
	}
	
	 public void deleteDevis(int id) {
		   PreparedStatement stm = null;
		try {
			stm = DB.prepareStatement("DELETE FROM devis WHERE id="+ id);
			stm.executeUpdate();
			stm.close();
			stm = DB.prepareStatement("DELETE FROM itemdevis WHERE numerodevis="+id);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();	
		} 
	 }
	
	public static int getPkF() {
		return pkF;
	}

	public static void setPkF(int pkF) {
		FactureDB.pkF = pkF;
	}

	public static int getPkD() {
		return pkD;
	}

	public static void setPkD(int pkD) {
		FactureDB.pkD = pkD;
	}


}
