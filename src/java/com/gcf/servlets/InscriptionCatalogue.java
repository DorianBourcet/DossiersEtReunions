package com.gcf.servlets;

import com.gcf.DAO.implement.CatalogueDAO;
import com.gcf.DAO.implement.PermissionDAO;
import com.gcf.modele.Membre;
import com.gcf.modele.Operation;
import com.gcf.modele.Permission;
import com.gcf.util.Connexion;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InscriptionCatalogue extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Message de confimation
        String message = "Une erreur c'est produit lors de l'inscription.";
        String typeMessage = "msg error";
        //Get parameters
        String idCatalogue = request.getParameter("idCatalogue");
        String codeSecret  = request.getParameter("code");
        String idMembre    = ((Membre)request.getSession().getAttribute("connecte")).getNumero();
        
        //Verification. Si le membre est deja inscrit
        if ((new PermissionDAO(Connexion.getInstance())).read(idCatalogue, idMembre)!=null){
            message="Vous êtes déja inscrit à ce catalogue.";   
        }else{
            //Verification. Code est bon.
            if (codeSecret.equals((new CatalogueDAO(Connexion.getInstance())).read(idCatalogue).getCode())){
                if ((new PermissionDAO(Connexion.getInstance()).create(new Permission(idCatalogue,idMembre,Operation.membreCatalogue)))){
                    message="Vous avez été inscrit au catalogue.";
                    typeMessage="msg done";
                }else {
                    message="Erreur lors de la création de la permission.";
                    typeMessage="msg error";
                }
            }
            //Message de confimation
        }
        request.setAttribute("message", message);
        request.setAttribute("typeMessage", typeMessage);
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
