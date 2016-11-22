package com.gcf.DAO.implement;

import java.sql.Connection;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;


import com.gcf.DAO.DAO;
import com.gcf.modele.Catalogue;
import com.gcf.modele.PointDiscussion;

public class PointDiscussionDAO extends DAO<PointDiscussion>{

	public PointDiscussionDAO(Connection c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(PointDiscussion x) {
        	String req;	
                if("-".equals(x.getIdDossier())){
                   req = "INSERT INTO pointdiscussion ( `nom` , `type`,  `idReunion`) " +
                                    "VALUES ('"+x.getNom()+"','"+x.getType()+"','"+x.getIdReunion()+"')";
                    }
                else{
                    req = "INSERT INTO pointdiscussion ( `nom` , `type`, `idDossier`, `idReunion`) " +
                    "VALUES ('"+x.getNom()+"','"+x.getType()+"','"+x.getIdDossier()+"','"+x.getIdReunion()+"')";
                }
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

	public PointDiscussion read(int id)
	{
		// TODO Auto-generated method stub
		return this.read(""+id);
	}

	public PointDiscussion read(String id)
	{
            try
            {
                Statement stm = cnx.createStatement();
                ResultSet r = stm.executeQuery("SELECT * FROM PointDiscussion WHERE idPointDiscussion = '"+id+"'");
                if (r.next())
                {
                       PointDiscussion p = new PointDiscussion(r.getString("idPointDiscussion"),
						r.getString("nom"),
						r.getString("type"),
						r.getString("etat"),
						r.getString("idDossier"),
                                                r.getString("idReunion"),
                                                r.getString("compteRendu"));
                        r.close();
                        stm.close();
                        return p;
                }
            }
            catch (SQLException exp)
            {
            }
            return null;
	}

	@Override
	public boolean update(PointDiscussion x) {
		Statement stm = null;
		try
		{
			String req = "UPDATE PointDiscussion SET nom = '"+x.getNom()+"',"+"type = '"+x.getType()+"'," + "etat = '"+x.getEtat()+"'" +"'," + "compteRendu = '"+x.getCompteRendu()+"'" +
							" WHERE idPointDiscussion = '"+x.getIdPointD()+"'";
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
	public boolean delete(PointDiscussion x)
	{
		{Statement stm = null;
		try
		{
			stm = cnx.createStatement();
			int n= stm.executeUpdate("DELETE FROM catalogue WHERE numero='"+x.getIdPointD()+"'");
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
        public List<PointDiscussion> findAll(){
          return this.findAll("");
        }
	public List<PointDiscussion> findAll(String id)
	{
		List<PointDiscussion> liste = new LinkedList<PointDiscussion>();
		try
		{
			Statement stm = cnx.createStatement();
                        String comdSQL;
                        if (id.equals("")){
                            
                            comdSQL="SELECT * FROM PointDiscussion";}
                        else
                        {
                            comdSQL="SELECT * FROM PointDiscussion WHERE idReunion = '"+id+"'";}
                        
                        ResultSet r = stm.executeQuery(comdSQL);
			while (r.next())
			{
				PointDiscussion p = new PointDiscussion(r.getString("idPointDiscussion"),
						r.getString("nom"),
						r.getString("type"),
						r.getString("etat"),
						r.getString("idDossier"),
                                                r.getString("idReunion"),
                                                r.getString("compteRendu"));
				liste.add(p);
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



