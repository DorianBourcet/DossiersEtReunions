/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcf.servlets;

import com.gcf.DAO.implement.PermissionDAO;
import com.gcf.DAO.implement.ReunionDAO;
import com.gcf.modele.Catalogue;
import com.gcf.modele.Membre;
import com.gcf.modele.Operation;
import com.gcf.modele.Permission;
import com.gcf.modele.Reunion;
import com.gcf.util.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Trauko
 */
public class AjouterReunion extends HttpServlet {

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
        String message;
        String typeMessage;
         String idCreateur= request.getParameter("idCreateur");
         String idCatalogue= request.getParameter("idCat");
         Permission perm = (new PermissionDAO(Connexion.getInstance())).read(idCatalogue, idCreateur);
        if ((perm==null)||((perm.getValeurPermission()&Operation.creerReunion)!=Operation.creerReunion)){
              message="Vous  ne possedez pas le droit de création de dossiers dans ce catalogue.";
              typeMessage="msg error";
        }
          else {
        
         String titre= request.getParameter("titreReunion");
         String date=request.getParameter("dateReunion");
         String dateComplete= date +" "+request.getParameter("heureReunion")+":"+request.getParameter("minReunion")+":00";
        
         
         if (titre==""){
            message="Champ de titre vide ";
            typeMessage="msg error";
         }
         else if(date=="") {
            message="Champ de date vide ";
            typeMessage="msg error";
         }
         else{  
             ReunionDAO rDAO=new ReunionDAO(Connexion.getInstance());
             Reunion reu = new Reunion("1",dateComplete,titre,idCreateur,null,idCatalogue);
             if (rDAO.create(reu)){
                message="Création de la réunion "+titre+" réussie.";
                typeMessage = "msg done";
              }
             else
             {
               message="La création de la réunion "+titre+" a échouée.";
                typeMessage = "msg error";  
             }
         }
        }
        request.setAttribute("message", message);
        request.setAttribute("typeMessage", typeMessage);
        RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?afficherPage=ajouterReunion");
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
