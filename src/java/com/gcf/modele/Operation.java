package com.gcf.modele;

public class Operation {
    public final static int consulterCatalogue=0x00000001;
    public final static int supprimerCatalogue=0x000002;
    public final static int modifCatalogue=0x000004;
    public final static int creerDossier=0x000008;
    public final static int creerReunion=0x000010;
    public final static int creerMAJ=0x000020;
    public final static int creerPointDiscussion=0x000040;
    public final static int fermerDossier=0x000080;
    public final static int fermerReunion=0x000100;
    public final static int modifDossier=0x000200;
    public final static int modifReunion=0x000400;
    public final static int gererMembres=0x000800; //Gerer les permissions des membres du catalogue.
    public final static int gererListeInvites=0x001000;
    
    public final static int admin=0xffffff;
    public final static int moderateur=consulterCatalogue+creerDossier+creerReunion+creerMAJ+creerPointDiscussion+modifDossier+modifReunion+gererListeInvites;
    public final static int membreCatalogue=consulterCatalogue;
    
    
    
    
    private String nom;
    private int    numReference;

    public Operation(String n,int num) {
        numReference = num;
        nom          = n;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
