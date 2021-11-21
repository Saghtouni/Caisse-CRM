package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Reglage;
import objects.Salle;
import objects.Utilisateurs;

public class ReglageDB {
	private Connection DB = null;
	private ObservableList<Salle> salle = FXCollections.observableArrayList();
	public ReglageDB(Connection dB) {
		super();
		DB = dB;
	}

	public void addReglage(String tickets, String imptickets, String factures, String impfactures, String historiques,
			String imphistoriques, String image, String devise, int droitsuppression, int nbrTable, int id_utilisateur, String nameMachine , String adresseMac) {
		PreparedStatement stm;
		try {
			stm = DB.prepareStatement(
					"INSERT INTO public.reglage ( tickets, imptickets, factures, impfactures, historiques, imphistoriques, image, devise, droitsuppression, tabl, id_utilisateur, namemachine, adressemac)\r\n"
							+ "				VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?)");
			stm.setString(1, tickets);
			stm.setString(2, imptickets);
			stm.setString(3, factures);
			stm.setString(4, impfactures);
			stm.setString(5, historiques);
			stm.setString(6, imphistoriques);
			stm.setString(7, image);
			stm.setString(8, devise);
			stm.setInt(9, droitsuppression);
			stm.setInt(10,nbrTable );
			stm.setInt(11, id_utilisateur);
			stm.setString(12, nameMachine);
			stm.setString(13, adresseMac);
			stm.executeUpdate();
			System.out.println("stm    = " + stm);
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Reglage getReglage(int id_utilisateur) {
		PreparedStatement stm;
		Reglage r = null;
		try {
			stm = DB.prepareStatement("Select * from reglage where id_utilisateur=?");
			stm.setInt(1, id_utilisateur);
			System.out.println("stm    = " + stm);
			ResultSet Rs = stm.executeQuery();
			while (Rs.next()) {
				r = new Reglage((Integer) Rs.getObject(1), (String) Rs.getObject(2), (String) Rs.getObject(3),
						(String) Rs.getObject(4), (String) Rs.getObject(5), (String) Rs.getObject(6),
						(String) Rs.getObject(7), (String) Rs.getObject(8), (String) Rs.getObject(9), (Integer) Rs.getObject(10),
						(Integer) Rs.getObject(11), (Integer) Rs.getObject(12), (String)Rs.getObject(13),  (String)Rs.getObject(14));

			}

			Rs.close();
			stm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	public void addTable(String nameTable, int etat, String nameMachine) {
		
		PreparedStatement stm;
		try {
			stm = DB.prepareStatement("INSERT INTO tablr ( name, etat, namemachine) VALUES ( ?, ?, ?)");
			stm.setString(1, nameTable);
			stm.setInt(2, etat);
			stm.setString(3, nameMachine);
		
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public void supTable(String nameMachine) {

		   PreparedStatement stm;
		try {
			stm = DB.prepareStatement("DELETE FROM tablr WHERE namemachine="+ "'" +nameMachine +"'");
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	   

	}
		
	public void updateTale(String nameSalle, int etat) {
		PreparedStatement stm;
		try {
			stm = DB.prepareStatement("UPDATE salle SET etat = ? WHERE name="+ "'" +nameSalle +"'");
			stm.setInt(1, etat);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void update(int idr, String tickets, String imptickets, String factures, String impfactures,
			String historiques, String imphistoriques, String image, String devise,  int sup,int nbrTable, int idd, String nameMachine, String adresseMac) {

		PreparedStatement stm;
		try {
			stm = DB.prepareStatement("UPDATE public.reglage\r\n"
					+ "	SET  tickets=?, imptickets=?, factures=?, impfactures=?, historiques=?,"
					+ " imphistoriques=?, image=?, devise=?, droitsuppression=?, tabl= ?, namemachine = ?, adressemac = ?\r\n"
					+ "	WHERE id = ? AND id_utilisateur=?");
	
			stm.setString(1, tickets);
			stm.setString(2, imptickets);
			stm.setString(3, factures);
			stm.setString(4, impfactures);
			stm.setString(5, historiques);
			stm.setString(6, imphistoriques);
			stm.setString(7, image);
			stm.setString(8, devise);
			stm.setInt(9, sup);
			stm.setInt(10, nbrTable);
			stm.setString(11, nameMachine);
			stm.setString(12, adresseMac);
			stm.setInt(13, idr);
			stm.setInt(14, idd);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void addSalle(String nameSalle, String nameMachine) {
		
		PreparedStatement stm;
		try {
			stm = DB.prepareStatement("INSERT INTO salle ( name, namemachine ) VALUES ( ?, ?)");
			stm.setString(1, nameSalle);
			stm.setString(2, nameMachine);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/*public ObservableList<Salle> getSalle(String nameMachine) {
		try {
			//System.out.println("1st connection");
			PreparedStatement stm = DB.prepareStatement("select * from salle WHERE namemachine="+ "'" +nameMachine +"'");
			ResultSet Rs = stm.executeQuery();
			while(Rs.next()) {
				salle.add(new Salle((int) Rs.getObject(1), (String) Rs.getObject(2), (String) Rs.getObject(3)));
			}
			Rs.close();
			stm.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}
			return salle;
	}
*/
	public void supSalle(String nameSalle, String nameMachine) {

		   PreparedStatement stm;
		try {
			stm = DB.prepareStatement("DELETE FROM salle WHERE name="+ "'" +nameSalle +"'"+"AND namemachine="+ "'" +nameMachine +"'");
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	   

	}
}
