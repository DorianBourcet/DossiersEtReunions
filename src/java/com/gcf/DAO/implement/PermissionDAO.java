package com.gcf.DAO.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import com.gcf.DAO.DAO;
import com.gcf.modele.Permission;


public class PermissionDAO extends DAO<Permission>{

	public PermissionDAO(Connection cnx) {
		super(cnx);
		
	}

	@Override
	public boolean create(Permission x) {
		
		String req = "INSERT INTO Permission (`idCatalogue` , `idMembre` , `valeurPermission`)"+
				"VALUES ('"+x.getIdCatalogue()+"','"+x.getIdMembre()+"','"+x.getValeurPermission()+"')";

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
                                return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return false;
	}
        @Override
        public Permission read(int id) {
            throw new UnsupportedOperationException("Not supported, ever.");
        }
        public Permission read(String idCat,String idMem){
            return this.read(Integer.parseInt(idCat),Integer.parseInt(idMem));
        }
	public Permission read(int idCat,int idMem) {
		try 
		{
			Statement stm = cnx.createStatement(); 
			ResultSet r = stm.executeQuery("SELECT * FROM Permission WHERE idCatalogue = '"+idCat+"'"
                                + " AND idMembre ='"+idMem+"'");
			if (r.next())
			{
				Permission c = new Permission(r.getString("idCatalogue"),
						r.getString("idMembre"),
						Integer.parseInt(r.getString("valeurPermission")));
						
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
	public boolean update(Permission x) {
		Statement stm=null;
		try 
		{
			String req = "UPDATE permission SET ValeurPermission = '"+x.getValeurPermission()+"',"+							
							" WHERE idCatalogue = '"+x.getIdCatalogue()+"'";
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
                                return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return false;
	}

	@Override
	public boolean delete(Permission x) {
		Statement stm = null;
		try 
		{
			stm = cnx.createStatement(); 
			int n= stm.executeUpdate("DELETE FROM Permission WHERE idCatalogue='"+x.getIdCatalogue()+"'");
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
	public List<Permission> findAll() {
		List<Permission> liste = new LinkedList<Permission>();
		try 
		{
			Statement stm = cnx.createStatement(); 
			ResultSet r = stm.executeQuery("SELECT * FROM Permission");
			while (r.next())
			{
				
				Permission p = new  Permission(r.getString("idCatalogue"),
                                                    r.getString("idMembre"),
                                                    Integer.parseInt(r.getString("valeurPermission")));
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

    public List<Permission> findAllIdMembre(String idMembre) {
        List<Permission> liste = new LinkedList<Permission>();
            try 
            {
                Statement stm = cnx.createStatement(); 
                ResultSet r = stm.executeQuery("SELECT * FROM permission WHERE idMembre = '"+idMembre+"'");
                while (r.next())
                {
                    Permission p = new  Permission( r.getString("idCatalogue"),
                                                    r.getString("idMembre"),
                                   Integer.parseInt(r.getString("valeurPermission")));
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
