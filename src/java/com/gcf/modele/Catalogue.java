package com.gcf.modele;

public class Catalogue
{
  private String numeroCat, nomCatalogue, numProprietaire, dateCreation, code;
  
  public Catalogue(String numeroCat, String nomCatalogue, String numProprietaire, String dateCreation, String code)
  {
	  this.numeroCat = numeroCat;
	  this.nomCatalogue = nomCatalogue;
	  this.numProprietaire = numProprietaire;
	  this.dateCreation = dateCreation;
	  this.code = code;	  
  }
  
  public String getNumeroCat()
  {
	return numeroCat;  
  }
  
  public void setNumeroCat(String numeroCat)
  {
	  this.numeroCat = numeroCat;	  
  }
  
  
  public String getNomCatalogue()
  {
	return nomCatalogue;  
  }
  
  public void setNomCatalogue(String nomCatalogue)
  {
	  this.nomCatalogue = nomCatalogue;	  
  }
  
  public String getNumproprietaire()
  {
	  return numProprietaire;
  }
  
  public void setNumProprietaire(String numProprietaire)
  {
	  this.numProprietaire = numProprietaire;
  }
  
  public String getdateCreation()
  {
	  return dateCreation;
  }
  
  public void setDateCreatione(String dateCreation)
  {
	  this.dateCreation = dateCreation;
  }
  
  public String getCode()
  {
	  return code;	  
  }
  
  public void setCode(String code)
  {
	  this.code = code;
  }
}
