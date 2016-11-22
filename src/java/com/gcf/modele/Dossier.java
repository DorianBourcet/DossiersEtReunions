package com.gcf.modele;

public class Dossier {
    private String idDossier;
    private String nom;
    private String type;
    private String etat;
    private String idCreateur;
    private String dateCreation;
    private String dateFermeture;
    private String idCatalogue;

    public Dossier(String idDossier, String nom, String type, String etat, String idCreateur, String dateCreation, String dateFermeture, String idCatalogue) {
        this.idDossier = idDossier;
        this.nom = nom;
        this.type = type;
        this.etat = etat;
        this.idCreateur = idCreateur;
        this.dateCreation = dateCreation;
        this.dateFermeture = dateFermeture;
        this.idCatalogue = idCatalogue;
    }

    public String getIdDossier() {
        return idDossier;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public String getEtat() {
        return etat;
    }

    public String getIdCreateur() {
        return idCreateur;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public String getDateFermeture() {
        return dateFermeture;
    }

    public String getIdCatalogue() {
        return idCatalogue;
    }

    public void setIdDossier(String idDossier) {
        this.idDossier = idDossier;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setIdCreateur(String idCreateur) {
        this.idCreateur = idCreateur;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateFermeture(String dateFermeture) {
        this.dateFermeture = dateFermeture;
    }

    public void setIdCatalogue(String idCatalogue) {
        this.idCatalogue = idCatalogue;
    }
}

