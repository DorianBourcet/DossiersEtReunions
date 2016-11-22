package com.gcf.modele;

public class Permission {
    private String idCatalogue;
    private String idMembre;
    private int valeurPermission;

    public Permission( String idC,String idM, int valeur) {
        idMembre         = idM;
        idCatalogue      = idC;
        valeurPermission = valeur;
    }

    public String getIdMembre() {
        return idMembre;
    }

    public String getIdCatalogue() {
        return idCatalogue;
    }

    public int getValeurPermission() {
        return valeurPermission;
    }

    public void setValeurPermission(int valeurPermission) {
        this.valeurPermission = valeurPermission;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
