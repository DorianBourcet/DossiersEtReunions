package com.gcf.servlets;

import com.gcf.DAO.implement.CatalogueDAO;
import com.gcf.DAO.implement.PermissionDAO;
import com.gcf.modele.Catalogue;
import com.gcf.modele.Membre;
import com.gcf.modele.Operation;
import com.gcf.modele.Permission;
import com.gcf.util.Connexion;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class AjouterCatalogue extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nomCat=request.getParameter("nomCatalogue"),
               idCreateur=request.getParameter("idCreateur"),
               codeSecret=request.getParameter("codeSecret");
        String typeMessage ="msg error";
        String message="Creation du catalogue n'a pas été réussie.";
        
        CatalogueDAO cDAO = new CatalogueDAO(Connexion.getInstance());
        if (cDAO.readParNom(nomCat)!=null){
            message= "Il existe déjà un catalogue avec ce nom.";
        }
        else{
            Catalogue c = new Catalogue("1", nomCat, idCreateur, "default", codeSecret);
            if (cDAO.create(c)){
                message="Création du catalogue "+nomCat+" réussie.";
                typeMessage = "msg done";
                //Admin superpowers to creator.
                c=cDAO.readParNom(nomCat);//Error: por alguna razon misteriosa, esto siempre regresa null. Solucionando esto, todo funcionara.
                PermissionDAO pDAO= new PermissionDAO(Connexion.getInstance());
                if ((c!=null)&&(pDAO.create(new Permission(c.getNumeroCat(),idCreateur,Operation.admin)))){
                    message+="Le membre "+((Membre)request.getSession().getAttribute("connecte")).getNom()+" est maintenant administrateur du catalogue.";
                }else {
                    message+="\nErreur: Creation des permissions admin.";
                    typeMessage= "msg warning";
                }  
                //Si le membre n'avait pas de catalogue par defaut. On lui donne celui-ci. 
            }
        }
        request.setAttribute("message", message);
        request.setAttribute("typeMessage", typeMessage);
        RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?afficherPage=ajouterCatalogue");
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
