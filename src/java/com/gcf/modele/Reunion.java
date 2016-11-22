package com.gcf.modele;

public class Reunion {
    private String date;
    private String etat;
    private String idCatalogue;
    private String idCreateur;
    private String idReunion;
    private String titre;

    public Reunion(String idReu, String dateReunion, String titr, String idCreator, String e, String idCat) {
        idReunion   = idReu;
        date        = dateReunion;
        titre  = titr;
        idCreateur  = idCreator;
        etat        = e;
        idCatalogue = idCat;
    }

    public String getIdReunion() {
        return idReunion;
    }

    public String getDate() {
        return date;
    }

    public String getTitre() {
        return titre;
    }

    public String getIdCreateur() {
        return idCreateur;
    }

    public String getEtat() {
        return etat;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getIdCatalogue() {
        return idCatalogue;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
