/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcf.controleur;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usager
 */
public class controleurFrontal extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("login".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/login");
            r.forward(request, response);
        }
        if ("inscriptionMembre".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/AjouterMembre");
            r.forward(request, response);
        }
        if ("creationCatalogue".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/AjouterCatalogue");
            r.forward(request, response);
        }
        if ("logout".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/logout");
            r.forward(request, response);
        }
        if ("creationReunion".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/AjouterReunion");
            r.forward(request, response);
        }
        if ("creationDossier".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/AjouterDossier");
            r.forward(request, response);
        }
        if ("getListesDossiersReunions".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/ChercherListeDossiersReunions");
            r.forward(request, response);
        }
        if ("getListeMembreCatalogue".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/ChercherMembreCatalogue");
            r.forward(request, response);
        }
        if ("voirMAJ".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/ConsulterInfosDossierListeMAJ");
            r.forward(request, response);
        }
        if ("voirListeCat".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/ConsulterListeCatalogue");
            r.forward(request, response);
        }
        if ("inscriptionCatalogue".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/InscriptionCatalogue");
            r.forward(request, response);
        }
        if ("consulterInfoReunion".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/ConsulterInfoReunion");
            r.forward(request, response);
        }
        if ("modifPerm1".equals(action)||"modifPerm2".equals(action)||"getListePermission".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/ModifierPermissionMembre");
            r.forward(request, response);
        }
        if ("changerCatCourant".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/ChangerCatalogueCourant");
            r.forward(request, response);
        }
        if ("remplirODJ".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/AjouterMiseAJour");
            r.forward(request, response);
        }
        if ("fermerReunion".equals(action)){
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/FermerReunion");
            r.forward(request, response);
        }
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
