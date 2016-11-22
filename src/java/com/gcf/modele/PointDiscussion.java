package com.gcf.modele;

public class PointDiscussion 
{
    private String etat;
    private String idDossier;
    private String idPointD;
    private String idReunion;
    private String nom;
    private String type;
    private String compteRendu;
   

    public PointDiscussion(String idPoint, String n, String t, String e, String idDos, String idR, String cptRendu) {
        idPointD  = idPoint;
        nom       = n;
        type      = t;
        etat      = e;
        idDossier = idDos;
        idReunion = idR;
        compteRendu = cptRendu;
      
        
    }

    /**
     * @return the etat
     */
    public String getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(String etat) {
        this.etat = etat;
    }

    /**
     * @return the idDossier
     */
    public String getIdDossier() {
        return idDossier;
    }

    /**
     * @param idDossier the idDossier to set
     */
    public void setIdDossier(String idDossier) {
        this.idDossier = idDossier;
    }

    /**
     * @return the idPointD
     */
    public String getIdPointD() {
        return idPointD;
    }

    /**
     * @param idPointD the idPointD to set
     */
    public void setIdPointD(String idPointD) {
        this.idPointD = idPointD;
    }

    /**
     * @return the idReunion
     */
    public String getIdReunion() {
        return idReunion;
    }

    /**
     * @param idReunion the idReunion to set
     */
    public void setIdReunion(String idReunion) {
        this.idReunion = idReunion;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    public String getCompteRendu() {
        return compteRendu;
    }
    
    public void setCompteRendu(String cptRendu) {
        this.compteRendu = cptRendu;
    }

   
}


//~ Formatted by Jindent --- http://www.jindent.com
