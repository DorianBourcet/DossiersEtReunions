/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcf.servlets;

import com.gcf.DAO.implement.ReunionDAO;
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
 * @author Usager
 */
public class FermerReunion extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idReunion=request.getParameter("idReunion");
        ReunionDAO rDAO=new ReunionDAO(Connexion.getInstance());
        Reunion reu=rDAO.read(idReunion);
        reu.setEtat("Ferme");
        String message;
        String typeMessage;
        if (rDAO.update(reu)){
            message="Reunion fermée";
            typeMessage="msg done";
        }
        else{
             message="Échec de modification";
             typeMessage="msg error";
        }
        request.setAttribute("message", message);
        request.setAttribute("typeMessage", typeMessage);
        RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?afficherPage=afficherBienvenueAvec");
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
