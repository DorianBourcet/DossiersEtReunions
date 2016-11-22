/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcf.servlets;

import com.gcf.DAO.implement.CatalogueDAO;
import com.gcf.DAO.implement.MembreDAO;
import com.gcf.DAO.implement.PermissionDAO;
import com.gcf.modele.Catalogue;
import com.gcf.modele.Membre;
import com.gcf.modele.Permission;
import com.gcf.util.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Trauko
 */
public class ChercherMembreCatalogue extends HttpServlet {
    

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
                
                 String idCatalogueCourant=(((Catalogue)(request.getSession().getAttribute("catalogueCourant"))).getNumeroCat());
                 PermissionDAO pDAO=new PermissionDAO(Connexion.getInstance());
                 List<Permission> listePermission=pDAO.findAll(); 
                 MembreDAO mDAO=new MembreDAO(Connexion.getInstance());
                 List<Membre> listeMembres=new LinkedList<Membre>();
                 for(int i=0;i<listePermission.size();i++){
                    if (listePermission.get(i).getIdCatalogue().equals(idCatalogueCourant)){
                     listeMembres.add(mDAO.readID(listePermission.get(i).getIdMembre()));
                       }  
                 }
                 request.setAttribute("listeMembres", listeMembres);
                 RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?afficherPage=gestionMembres");    
                 r.forward(request, response);
                 
                  
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
