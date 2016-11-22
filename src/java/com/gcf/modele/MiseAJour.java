package com.gcf.modele;

public class MiseAJour {
    private String date;
    private String idAuteur;
    private String idDossier;
    private String idMAJ;
    private String info;
    private String nature;

    public MiseAJour(String idMiseAJ, String d, String nat, String inf, String idAut, String idDos) {
        idMAJ     = idMiseAJ;
        date      = d;
        nature    = nat;
        info      = inf;
        idAuteur  = idAut;
        idDossier = idDos;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(String idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(String idDossier) {
        this.idDossier = idDossier;
    }

    public String getIdMAJ() {
        return idMAJ;
    }

    public void setIdMAJ(String idMAJ) {
        this.idMAJ = idMAJ;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
