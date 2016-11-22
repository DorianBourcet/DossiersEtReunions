package com.gcf.modele;

public class Membre 
{
	private String numero, nom, motDePasse, courriel, catalogueDefaut;
	private int actif;
	
	//constructeurs
	public Membre(String numero, String nom, String motDePasse, String courriel, int actif, String catalogueDefaut)
	{
		this.numero =numero;
		this.nom = nom;
		this.motDePasse = motDePasse;  
		this.courriel = courriel;
		this.actif = actif;
                this.catalogueDefaut=catalogueDefaut;
	}
	
	public String getNumero()
	{
		return numero;
	}
	
	public void setNumero(String numero)
	{
	   this.numero = numero;	
		
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public void setNom(String nom)
	{
	   this.nom = nom;	
		
	}
	
	public String getMotDePasse()
	{
		return motDePasse;
	}
	
	public void setMotDePasse(String motDePasse)
	{
	   this.motDePasse = motDePasse;	
		
	}
	
	public String getCourriel()
	{
		return courriel;
	}
	
	public void setCourriel(String courriel)
	{
		this.courriel= courriel;
	}

	public int getActif()
	{
		return actif;
	}
	

    /**
     * @return the catalogueDefaut
     */
    public String getCatalogueDefaut() {
        return catalogueDefaut;
    }

    /**
     * @param catalogueDefaut the catalogueDefaut to set
     */
    public void setCatalogueDefaut(String catalogueDefaut) {
        this.catalogueDefaut = catalogueDefaut;
    }
}
