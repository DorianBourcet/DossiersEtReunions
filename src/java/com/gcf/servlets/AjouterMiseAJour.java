package com.gcf.servlets;

import com.gcf.DAO.implement.MiseAJourDAO;
import com.gcf.modele.Membre;
import com.gcf.modele.MiseAJour;
import com.gcf.util.Connexion;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjouterMiseAJour extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Message et son type
        String message="Erreur lors des mises à jour.";
        String typeMessage = "msg error";
        int nombreMAJ=0;
        //Nombre de Point de discussions
        int nombrePD = Integer.parseInt(request.getParameter("nombrePD"));
        //ID du créateur
        String idCreateur = ((Membre)request.getSession().getAttribute("connecte")).getNumero();
        //idreunion
        String idReunion = request.getParameter("idReunion");
        //Insertion de chaque mise a jour
        MiseAJourDAO majDAO = new MiseAJourDAO(Connexion.getInstance());
        for (int i=0;i<nombrePD;i++){
            String idDossier = request.getParameter("idDossier"+i);
            //String nature = request.getParameter("nature"+i);
            String info = request.getParameter("info"+i);
            if ((!"".equals(idDossier)&&(!"".equals(info)))){
                if (majDAO.create(new MiseAJour("","","null",info,idCreateur,idDossier))){
                    nombreMAJ++;
                }
            }
        }
        if (nombreMAJ==0){
            message="Aucune mise à jour a été crée";
            typeMessage = "msg warning";
        } else {
            if (nombreMAJ>0){
                message=""+nombreMAJ+" mises à jour ont été crées.";
                typeMessage = "msg done";
            }
        }
        request.setAttribute("message", message);
        request.setAttribute("typeMessage", typeMessage);
        RequestDispatcher r = this.getServletContext().getRequestDispatcher("/ctrl.do?action=consulterInfoReunion&idReunion="+idReunion);
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
