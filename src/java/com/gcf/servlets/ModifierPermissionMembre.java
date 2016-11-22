/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcf.servlets;

import com.gcf.DAO.implement.PermissionDAO;
import com.gcf.modele.Catalogue;
import com.gcf.util.Connexion;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ModifierPermissionMembre extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idMembre= request.getParameter("idMembre");
        String message = "La modification des permission a échouée.";
        String typeMessage = "msg error";
        if ("getListePermission".equals(request.getParameter("action"))){
            String idCat=((Catalogue)(request.getSession().getAttribute("catalogueCourant"))).getNumeroCat();
            int permis = (new PermissionDAO(Connexion.getInstance()).read(idCat, idMembre)).getValeurPermission();
            //(WARNING:lo de arriba es infinito, probablemente ya lo ejecutaste. En mi defensa aqui hay un warning... pude haberte dicho ahora que esta al lado, fuck it, ya estoy cansado... anyways good luck con esto.)Lo de arriba no funciona, possiblemente error en el DAO, teoria mas possible: error mio.
           //PS:lo arregle. dejo el warning for shit and giggles.
            request.setAttribute("permissionMembre", String.valueOf(permis));
        }else {
            //aqui modificas las permissiones del miembro
            //idMembre ya existe...
            //si action=modifPerm1 : el usuario escojio un permiso predefinido
            //si action=modifPerm2 : el usuario escojio un permiso personalizado
            //GOOD LUCK
            //no se te olvide modificar los mensajes de error o done.
            request.setAttribute("message", message);
            request.setAttribute("typeMessage", typeMessage);
        }
        RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?afficherPage=modifPermMem");
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
