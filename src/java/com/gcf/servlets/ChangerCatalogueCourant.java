package com.gcf.servlets;

import com.gcf.DAO.implement.CatalogueDAO;
import com.gcf.DAO.implement.MembreDAO;
import com.gcf.modele.Membre;
import com.gcf.util.Connexion;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangerCatalogueCourant extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       //Numero du nouveau catalogue courant.
       String idCatalogue = request.getParameter("idCatalogue");
       //On change la valeur de catalogue par defaut du membre courant.
       Membre m = (Membre)request.getSession().getAttribute("connecte");
       m.setCatalogueDefaut(idCatalogue);
       (new MembreDAO(Connexion.getInstance())).update(m);
       //Pour éviter que l'utilisateur ait à ce reconnecter.
       request.getSession().removeAttribute("catalogueCourant");
       request.getSession().setAttribute("catalogueCourant", (new CatalogueDAO(Connexion.getInstance())).read(idCatalogue));
       RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
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
