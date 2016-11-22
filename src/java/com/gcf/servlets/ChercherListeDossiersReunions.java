package com.gcf.servlets;

import com.gcf.DAO.implement.DossierDAO;
import com.gcf.DAO.implement.PermissionDAO;
import com.gcf.DAO.implement.ReunionDAO;
import com.gcf.modele.Catalogue;
import com.gcf.modele.Membre;
import com.gcf.modele.Operation;
import com.gcf.modele.Permission;
import com.gcf.util.Connexion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class ChercherListeDossiersReunions extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String afficherBienvenue=("/index.jsp?afficherPage=afficherBienvenue");
        String pageRequest;
        if (request.getParameter("doVoirDossier")!=null){
            pageRequest="/index.jsp?afficherPage=afficherListeDossier";}
        else if (request.getParameter("doVoirReunion")!=null){
            pageRequest="/index.jsp?afficherPage=afficherListeReunion";
            }
        else{
            pageRequest=afficherBienvenue;
        }
        //Vérification des permissiones du membre.
        String idCreateur = ((Membre)(request.getSession().getAttribute("connecte"))).getNumero();
        String idCatalogue = ((Catalogue) request.getSession().getAttribute("catalogueCourant")).getNumeroCat();
        int perm = (new PermissionDAO(Connexion.getInstance())).read(idCatalogue, idCreateur).getValeurPermission();
        Boolean pFermDos=false , pFermReu=false;
        if ((perm&Operation.fermerDossier)==Operation.fermerDossier){
              pFermDos = true;
        } 
        if ((perm&Operation.fermerReunion)==Operation.fermerReunion){
              pFermReu = true;
        }
        request.setAttribute("pFermDos", pFermDos);
        request.setAttribute("pFermReu", pFermReu);
        //Liste des dossiers liés au catalogue.
        request.setAttribute("listeDossiers",(new DossierDAO(Connexion.getInstance())).findAll("idCatalogue", idCatalogue)); 
        //Liste des réunions liées au catalogue.
        request.setAttribute("listeReunions",(new ReunionDAO(Connexion.getInstance())).findAll("idCatalogue", idCatalogue));
        this.getServletContext().getRequestDispatcher(pageRequest).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
