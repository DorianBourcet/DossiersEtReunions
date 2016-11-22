package com.gcf.DAO.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import com.gcf.DAO.DAO;
import com.gcf.DAO.DAO;
import com.gcf.modele.Reunion;


public class ReunionDAO extends DAO<Reunion>{

	public ReunionDAO(Connection cnx) {
		super(cnx);
		
	}

	@Override
	public boolean create(Reunion x) {
		
		String req = "INSERT INTO reunion (`DATE` , `TITRE` , `IDCREATEUR` ,`IDCATALOGUE`) "+
				"VALUES ('"+x.getDate()+"','"+x.getTitre()+"','"+x.getIdCreateur()+"','"+x.getIdCatalogue()+"')";


		Statement stm = null;
		try 
		{
			stm = cnx.createStatement(); 
			int n= stm.executeUpdate(req);
			if (n>0)
			{
				stm.close();
				return true;
			}
		}
		catch (SQLException exp)
		{
		}
		finally
		{
			if (stm!=null)
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return false;
	}

	@Override
	public Reunion read(int id){
               return  this.read(""+id);
        }
        public Reunion read(String id) {
		try 
		{
			Statement stm = cnx.createStatement(); 
			ResultSet r = stm.executeQuery("SELECT * FROM reunion WHERE idReunion = '"+id+"'");
			if (r.next())
			{
				Reunion re = new Reunion(r.getString("idReunion"),
						r.getString("date"),
						r.getString("titre"),
						r.getString("idCreateur"),
						r.getString("etat"),
						r.getString("idCatalogue"));
				r.close();
				stm.close();
				return re;
			}
		}
		catch (SQLException exp)
		{
		}
		return null;
	}
       public Reunion readTitre(String Titre) {
		try 
		{
			Statement stm = cnx.createStatement(); 
			ResultSet r = stm.executeQuery("SELECT * FROM reunion WHERE titre = '"+Titre+"'");
			if (r.next())
			{
				Reunion re = new Reunion(r.getString("idReunion"),
						r.getString("date"),
						r.getString("titre"),
						r.getString("idCreateur"),
						r.getString("etat"),
						r.getString("idCatalogue"));
				r.close();
				stm.close();
				return re;
			}
		}
		catch (SQLException exp)
		{
		}
		return null;
	}
	@Override
	public boolean update(Reunion x) {
		Statement stm=null;
		try 
		{
			String req = "UPDATE reunion SET TITRE = '"+x.getTitre()+"',"+
							"DATE = '"+x.getDate()+"'," +
							"ETAT = '"+x.getEtat()+"'"+
							" WHERE IDREUNION = '"+x.getIdReunion()+"'";
			stm = cnx.createStatement(); 
			int n= stm.executeUpdate(req);
			if (n>0)
			{
				stm.close();
				return true;
			}
		}
		catch (SQLException exp)
		{
		}
		finally
		{
			if (stm!=null)
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return false;
	}

	@Override
	public boolean delete(Reunion x) {
		Statement stm = null;
		try 
		{
			stm = cnx.createStatement(); 
			int n= stm.executeUpdate("DELETE FROM reunion WHERE idReunion='"+x.getIdReunion()+"'");
			if (n>0)
			{
				stm.close();
				return true;
			}
		}
		catch (SQLException exp)
		{
		}
		finally
		{
			if (stm!=null)
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return false;
	}

	public List<Reunion> findAll(){
            return this.findAll("", "");
        }
	 public List<Reunion> findAll(String type, String valeur) {
		List<Reunion> liste = new LinkedList<Reunion>();
		try 
		{
			Statement stm = cnx.createStatement(); 
                        String query = "SELECT * FROM reunion";
                        if (!(("".equals(type))&&("".equals(valeur)))){
                            query="SELECT * FROM reunion WHERE "+type+" = '"+valeur+"'";
                        }
                        ResultSet r = stm.executeQuery(query);
			while (r.next())
			{
				Reunion c = new Reunion(r.getString("idReunion"),
                                                        r.getString("date"),
                                                        r.getString("titre"),
                                                        r.getString("idCreateur"),
                                                        r.getString("etat"),
                                                        r.getString("idCatalogue"));
				liste.add(c);
			}
			r.close();
			stm.close();
		}
		catch (SQLException exp)
		{
		}
		return liste;
	}
        public List<Reunion> findAllMembreCatalogue(String idMembre,String idCatalogue) {
		List<Reunion> liste = new LinkedList<Reunion>();
		try 
		{
			Statement stm = cnx.createStatement(); 
			ResultSet r = stm.executeQuery("SELECT * FROM reunion WHERE idCreateur = '"+idMembre+"' AND idCatalogue = '"+idCatalogue+"'");
			while (r.next())
			{
				
				Reunion c = new Reunion(r.getString("idReunion"),
						r.getString("date"),
						r.getString("titre"),
						r.getString("idCreateur"),
						r.getString("etat"),
						r.getString("idCatalogue"));
				liste.add(c);
			}
			r.close();
			stm.close();
		}
		catch (SQLException exp)
		{
		}
		return liste;
	} 

}
