package com.gcf.servlets;

import com.gcf.DAO.implement.CatalogueDAO;
import com.gcf.DAO.implement.MembreDAO;
import com.gcf.DAO.implement.PermissionDAO;
import com.gcf.modele.Catalogue;
import com.gcf.modele.Membre;
import com.gcf.modele.Permission;
import com.gcf.util.Connexion;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ConsulterListeCatalogues", urlPatterns = {"/ConsulterListeCatalogues"})
public class ConsulterListeCatalogue extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idMembre=((Membre)request.getSession().getAttribute("connecte")).getNumero();
        //Trouver liste de tous les catalogues
        List<Catalogue> listeCat =(new CatalogueDAO(Connexion.getInstance())).findAll();
        //Trouver liste des IDs des catalogues auquels le membre est inscrit
        List<Permission> listeCatInscrit = (new PermissionDAO(Connexion.getInstance()).findAllIdMembre(idMembre));
        //Séparation de tous les catalogues et ceux auquel l'utilisateur est déjà inscrit.
        List<Catalogue> listeCataloguesDejaInscrit = new LinkedList<Catalogue>();
        for (int i=0;i<listeCatInscrit.size();i++){
            String idCat=listeCatInscrit.get(i).getIdCatalogue();
            for (int j=0;j<listeCat.size();j++){
                if (listeCat.get(j).getNumeroCat().equals(idCat)){
                    listeCataloguesDejaInscrit.add(listeCat.get(j));
                    listeCat.remove(j);
                }
            }
        }
        MembreDAO mDAO=new MembreDAO(Connexion.getInstance());
        List<String> listeProprietaire = new LinkedList<String>();
        for (int i=0;i<listeCat.size();i++){
            listeProprietaire.add(mDAO.readID(listeCat.get(i).getNumproprietaire()).getNom());
        }
        List<String> listeCatInscritProprietaire = new LinkedList<String>();
        for (int i=0;i<listeCataloguesDejaInscrit.size();i++){
            listeCatInscritProprietaire.add(mDAO.readID(listeCataloguesDejaInscrit.get(i).getNumproprietaire()).getNom());
        }
        request.setAttribute("listeCatInscrit", listeCataloguesDejaInscrit);
        request.setAttribute("listeCatInscritProprietaire", listeCatInscritProprietaire);
        request.setAttribute("listeCat", listeCat);
        request.setAttribute("listeProprietaire", listeProprietaire);
        RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?afficherPage=voirListeCat");
        r.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
