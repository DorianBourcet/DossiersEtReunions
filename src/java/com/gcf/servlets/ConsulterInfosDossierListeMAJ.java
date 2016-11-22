package com.gcf.servlets;

import com.gcf.DAO.implement.DossierDAO;
import com.gcf.DAO.implement.MembreDAO;
import com.gcf.DAO.implement.MiseAJourDAO;
import com.gcf.modele.Dossier;
import com.gcf.modele.MiseAJour;
import com.gcf.util.Connexion;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ConsulterInfosDossierListeMAJ extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Parametres necessaires
        String idDossier = request.getParameter("idDossier");
        //Trouver le dossier pour ensuite l'afficher
        DossierDAO dDAO= new DossierDAO(Connexion.getInstance());
        Dossier d= dDAO.read(idDossier);
        //Trouver nom du créateur pour ensuite l'afficher.
        MembreDAO mDAO=new MembreDAO(Connexion.getInstance());
        request.setAttribute("nomCreateur", (mDAO.readID(d.getIdCreateur())).getNom());
        //Trouver liste de mises a jour liées au dossier
        MiseAJourDAO majDAO= new MiseAJourDAO(Connexion.getInstance());
        List<MiseAJour> listeMAJ = majDAO.findAllIdDossier(idDossier);
        //Trouver la liste des noms des auteurs des mises a jour
        List<String> listeAuteurs = new LinkedList<String>();
        for (int i=0;i<listeMAJ.size();i++){
            listeAuteurs.add(mDAO.readID(listeMAJ.get(i).getIdAuteur()).getNom());
        }  
        request.setAttribute("dossier", d);
        request.setAttribute("listeMAJ", listeMAJ); 
        request.setAttribute("listeAuteurs", listeAuteurs); 
        RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?afficherPage=voirMAJ");
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
