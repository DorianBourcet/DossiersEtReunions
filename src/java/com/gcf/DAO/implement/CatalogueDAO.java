package com.gcf.DAO.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.gcf.DAO.DAO;
import com.gcf.modele.*;

public class CatalogueDAO extends DAO<Catalogue>
{

	public CatalogueDAO(Connection c) 
	{
		super(c);
	}

	@Override
	public boolean create(Catalogue x) 
	{
		String req = "INSERT INTO catalogue ( `nom` , `idCreateur` , `code`,`dateCreation`) "+
				"VALUES ('"+x.getNomCatalogue()+"','"+x.getNumproprietaire()+"','"+x.getCode()+"',default)";
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
				e.printStackTrace();
			}			
		}

		return false;
	}

	public Catalogue read(int id)
	{
		return this.read(""+id);
	}
	public Catalogue read(String id)
	{
		try 
		{
			Statement stm = cnx.createStatement(); 
			ResultSet r = stm.executeQuery("SELECT * FROM catalogue WHERE idCatalogue = '"+id+"'");
			if (r.next())
			{
				Catalogue c = new Catalogue(r.getString("idCatalogue"),
						r.getString("nom"),
						r.getString("idCreateur"),
						r.getString("dateCreation"),
						r.getString("code"));						
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
	public boolean update(Catalogue x) 
	{
		Statement stm = null;
		try 
		{
			String req = "UPDATE client SET nomCatalogue = '"+x.getNomCatalogue()+"',"+
							"numProprietaire = '"+x.getNumproprietaire()+"'," +
							"code = '"+x.getCode()+"'" +
							" WHERE NUMERO = '"+x.getNumeroCat()+"'";
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
	public boolean delete(Catalogue x) 
	{Statement stm = null;
	try 
	{
		stm = cnx.createStatement(); 
		int n= stm.executeUpdate("DELETE FROM catalogue WHERE numeroCat='"+x.getNumeroCat()+"'");
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

    @Override
	public List<Catalogue> findAll() {
            return this.findAll("", "");
        }
	public List<Catalogue> findAll(String type,String valeur) {
            List<Catalogue> liste = new LinkedList<Catalogue>();
            try 
            {
                Statement stm = cnx.createStatement(); 
                String query="SELECT * FROM catalogue";  
                if (!(("".equals(type))&&("".equals(valeur)))){
                    query="SELECT * FROM catalogue WHERE "+type+" = '"+valeur+"'";
                }
                ResultSet r = stm.executeQuery(query);
                while (r.next())
                {
                        Catalogue c = new Catalogue(r.getString("idCatalogue"),
                                                    r.getString("nom"),
                                                    r.getString("idCreateur"),
                                                    r.getString("dateCreation"),
                                                    r.getString("code"));
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
    public Catalogue readParNom(String nom){
        List<Catalogue> liste = this.findAll("nom", nom);
        if (liste.isEmpty()){return null;}
        else {return liste.get(0);}
    }
}
