package com.gcf.DAO.implement;
import com.gcf.DAO.DAO;
import com.gcf.modele.Dossier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DossierDAO extends DAO<Dossier>{
	public DossierDAO(Connection c) {
		super(c);
	}
    @Override
	public boolean create(Dossier d){
		String r= "INSERT INTO dossier (`nom`,`type`,`idCreateur`,`idCatalogue`) VALUES " +
				"('"+d.getNom()+"','"+d.getType()+"','"+d.getIdCreateur()+"','"+d.getIdCatalogue()+"')";
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
					e.printStackTrace();
				}
		}
		return false;
	}
    @Override
	public boolean delete(Dossier d) {
		Statement stm = null;
		try 
		{
			stm = cnx.createStatement(); 
			int n= stm.executeUpdate("DELETE FROM dossier WHERE idDossier='"+d.getIdDossier()+"'");
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
    @Override
	public Dossier read(int id) {
		return this.read(""+id);
	}
	public Dossier read(String id) {
		try 
		{
			Statement stm = cnx.createStatement(); 
			ResultSet r = stm.executeQuery("SELECT * FROM dossier WHERE idDossier = '"+id+"'");
			if (r.next())
			{
				Dossier d = new Dossier(r.getString("idDossier"),
						r.getString("nom"),
                                                r.getString("type"),
                                                r.getString("etat"),
                                                r.getString("idCreateur"),
                                                r.getString("dateCreation"),
						r.getString("dateFermeture"),
                                                r.getString("idCatalogue")
						);
				r.close();
				stm.close();
				return d;
			}
		}
		catch (SQLException exp)
		{
		}
		return null;
	}
    @Override
	public boolean update(Dossier d) {
		Statement stm = null;
		try 
		{
			String req = "UPDATE dossier SET nom = '"+d.getNom()+"',"+
							"type = '"+d.getType()+"'," +
							"etat = '"+d.getEtat()+"'," +
							"dateFermeture = '"+d.getDateFermeture()+"'" +
							" WHERE idDossier = '"+d.getIdDossier()+"'";
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
        public List<Dossier> findAll() {
            return this.findAll("", "");
        }
	
        public List<Dossier> findAll(String type,String valeur) {
		List<Dossier> liste = new LinkedList<Dossier>();
		try 
		{
			Statement stm = cnx.createStatement(); 
                        String query = "SELECT * FROM dossier";
                        if (!(("".equals(type))&&("".equals(valeur)))){
                            query="SELECT * FROM dossier WHERE "+type+" = '"+valeur+"'";
                        }
                        ResultSet r = stm.executeQuery(query);
			while (r.next())
			{
				Dossier d = new Dossier(r.getString("idDossier"),
						r.getString("nom"),
                                                r.getString("type"),
                                                r.getString("etat"),
                                                r.getString("idCreateur"),
                                                r.getString("dateCreation"),
						r.getString("dateFermeture"),
                                                r.getString("idCatalogue")
						);
				liste.add(d);
			}
			r.close();
			stm.close();
		}
		catch (SQLException exp)
		{
		}
		return liste;
	}

     public List<Dossier> findAllMembreCatalogue(String idMembre,String idCatalogue) {
		List<Dossier> liste = new LinkedList<Dossier>();
		try 
		{
			Statement stm = cnx.createStatement(); 
			ResultSet r = stm.executeQuery("SELECT * FROM dossier WHERE idCreateur = '"+idMembre+"' AND idCatalogue = '"+idCatalogue+"'");
			while (r.next())
			{
				
				Dossier d = new Dossier(r.getString("idDossier"),
						r.getString("nom"),
                                                r.getString("type"),
                                                r.getString("etat"),
                                                r.getString("idCreateur"),
                                                r.getString("dateCreation"),
						r.getString("dateFermeture"),
                                                r.getString("idCatalogue")
						);
				liste.add(d);
			}
			r.close();
			stm.close();
		}
		catch (SQLException exp)
		{
		}
		return liste;
	}   
        
    public Dossier readParNom(String nom){
        List<Dossier> liste = this.findAll("nom", nom);
        if (liste.isEmpty()){return null;}
        else {return liste.get(0);}
    }
   
}