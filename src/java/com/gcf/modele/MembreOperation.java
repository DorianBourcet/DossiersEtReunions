package com.gcf.modele;

public class MembreOperation {
    private String dateHeure;
    private String desciption;
    private String    idCatalogue;
    private String    idMembre;

    public MembreOperation(String idMem, String idCat, String dateHre, String desc) {
        idMembre    = idMem;
        idCatalogue = idCat;
        dateHeure   = dateHre;
        desciption  = desc;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
