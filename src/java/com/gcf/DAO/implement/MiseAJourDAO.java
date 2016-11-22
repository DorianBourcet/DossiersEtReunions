/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcf.DAO.implement;

import com.gcf.DAO.DAO;
import com.gcf.modele.MiseAJour;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Usager
 */
public class MiseAJourDAO extends DAO<MiseAJour>{
    public MiseAJourDAO(Connection c) {
		super(c);
	}
    @Override
	public boolean create(MiseAJour m){
		String r="INSERT INTO `miseajour` (`date` ,`nature` ,`info` ,`idAuteur` ,`idDossier`)"
                       + "VALUES (CURRENT_TIMESTAMP , '"+m.getNature()+"', '"+m.getInfo()+"', '"+m.getIdAuteur()+"', '"+m.getIdDossier()+"')";
		Statement stm=null;
		try{
			stm = cnx.createStatement();
			int n= stm.executeUpdate(r);
			if (n>0){
				stm.close();
                                return true;
			}
		}
		catch (SQLException exp)
		{
		}
		finally{
			if (stm!=null)
				try {
					stm.close();
				} catch (SQLException e) {
				
				}
		}
		return false;
	}
    @Override
	public boolean delete(MiseAJour m) {
		Statement stm = null;
		try 
		{
			stm = cnx.createStatement(); 
			int n= stm.executeUpdate("DELETE FROM miseajour WHERE idMAJ='"+m.getIdMAJ()+"'");
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
	
			}			
		}
		return false;
	}
    @Override
	public MiseAJour read(int id) {
		return this.read(""+id);
	}
	public MiseAJour read(String id) {
		try 
		{
			Statement stm = cnx.createStatement(); 
			ResultSet r = stm.executeQuery("SELECT * FROM miseajour WHERE idMAJ = '"+id+"'");
			if (r.next())
			{
				MiseAJour m = new MiseAJour(r.getString("idMAJ"),
                                                            r.getString("date"),
                                                            r.getString("nature"),
                                                            r.getString("info"),
                                                            r.getString("idAuteur"),
                                                            r.getString("idDossier"));
				r.close();
				stm.close();
				return m;
			}
		}
		catch (SQLException exp)
		{
		}
		return null;
	}
    @Override
	public boolean update(MiseAJour m) {
		Statement stm = null;
		try 
		{
			String req = "UPDATE miseajour SET nature = '"+m.getNature()+"',"+
                                                            "info = '"+m.getInfo()+"'";
			//System.out.println("REQUETE "+req);
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
			if (stm!=null) {
                        try {
                                stm.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                    }			
		}
		return false;
	}
        public List<MiseAJour> findAll() {
            return this.findAll("", "");
        }
	

        public List<MiseAJour> findAll(String type,String valeur) {
		List<MiseAJour> liste = new LinkedList<MiseAJour>();
		try 
		{
			Statement stm = cnx.createStatement(); 
			String query = "SELECT * FROM miseajour";
                        if (!(("".equals(type))&&("".equals(valeur)))){
                            query="SELECT * FROM miseajour WHERE "+type+" = '"+valeur+"'";        
                        }
                        ResultSet r = stm.executeQuery(query);
			while (r.next())
			{	
				MiseAJour m = new MiseAJour(r.getString("idMAJ"),
                                                            r.getString("date"),
                                                            r.getString("nature"),
                                                            r.getString("info"),
                                                            r.getString("idAuteur"),
                                                            r.getString("idDossier"));
				liste.add(m);
			}
			r.close();
			stm.close();
		}
		catch (SQLException exp)
		{
		}
		return liste;
	}   
        
        public List<MiseAJour> findAllIdDossier(String idDossier){
            List<MiseAJour> liste = this.findAll("idDossier", idDossier);
            return liste;
        }
        public List<MiseAJour> findAllNature(String nature){
            List<MiseAJour> liste = this.findAll("nature", nature);
            return liste;
        }
   
}
