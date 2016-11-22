package com.gcf.DAO.implement;

import java.sql.Connection;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import com.gcf.DAO.DAO;
import com.gcf.modele.Membre;

public class MembreDAO extends DAO<Membre>{

	public MembreDAO(Connection c) {
		super(c);
	}

	@Override
	public boolean create(Membre x) {
		String req = "INSERT INTO membre (`nom` , `motDePasse` , `courriel`) "+
				"VALUES ('"+x.getNom()+"','"+x.getMotDePasse()+"','"+x.getCourriel()+"')";
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

	public Membre read(int id)
	{
		// TODO Auto-generated method stub
		return this.read(""+id);
	}

	public Membre read(String nom) 
	{
            try 
            {
                Statement stm = cnx.createStatement(); 
                ResultSet r = stm.executeQuery("SELECT * FROM membre WHERE nom = '"+nom+"'");
                if (r.next())
                {
                       Membre c = new Membre(   r.getString("idMembre"),
						r.getString("nom"),
						r.getString("motDePasse"),
						r.getString("courriel"),
						Integer.parseInt(r.getString("actif")),
                                                r.getString("catalogueDefaut"));
                        r.close();
                        stm.close();
                        return c;
                }
            }
            catch (SQLException exp)
            {
            }
            return null;
	}
        public Membre readID(String id) 
	{
            try 
            {
                Statement stm = cnx.createStatement(); 
                ResultSet r = stm.executeQuery("SELECT * FROM membre WHERE idMembre = '"+id+"'");
                if (r.next())
                {
                       Membre c = new Membre(   r.getString("idMembre"),
						r.getString("nom"),
						r.getString("motDePasse"),
						r.getString("courriel"),
						Integer.parseInt(r.getString("actif")),
                                                r.getString("catalogueDefaut"));
                        r.close();
                        stm.close();
                        return c;
                }
            }
            catch (SQLException exp)
            {
            }
            return null;
	}

	@Override
	public boolean update(Membre x) {
		Statement stm = null;
		try 
		{
			String req = "UPDATE membre SET nom = '"+x.getNom()+"', courriel = '"+x.getCourriel()+"', catalogueDefaut = '"+x.getCatalogueDefaut()+"'" +
							" WHERE idMembre = '"+x.getNumero()+"'";		
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
	public boolean delete(Membre x) 
	{
		{Statement stm = null;
		try 
		{
			stm = cnx.createStatement(); 
			int n= stm.executeUpdate("DELETE FROM catalogue WHERE numero='"+x.getNumero()+"'");
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
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return false;
	}
}

	@Override
	public List<Membre> findAll() 
	{
		List<Membre> liste = new LinkedList<Membre>();
		try 
		{
			Statement stm = cnx.createStatement(); 
			ResultSet r = stm.executeQuery("SELECT * FROM membre");
			while (r.next())
			{
				Membre c = new Membre(r.getString("numero"),
						r.getString("nom"),
						r.getString("motDePasse"),
						r.getString("courriel"),
						Integer.parseInt(r.getString("actif")),
                                                r.getString("catalogueDefault"));
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


