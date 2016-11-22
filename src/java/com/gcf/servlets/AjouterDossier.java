package com.gcf.servlets;
import com.gcf.DAO.implement.DossierDAO;
import com.gcf.DAO.implement.PermissionDAO;
import com.gcf.modele.Catalogue;
import com.gcf.modele.Dossier;
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

public class AjouterDossier extends HttpServlet {
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String nom = request.getParameter("nom");
          String type = request.getParameter("type");
          String etat = "ouvert";
          String idCreateur = ((Membre)(request.getSession().getAttribute("connecte"))).getNumero();
          String idCatalogue = ((Catalogue) request.getSession().getAttribute("catalogueCourant")).getNumeroCat();
          String message="La création du dossier n'a pas été réussie.";
          String typeMessage="msg error";
    
          //Vérification des permissiones du membre.
          Permission perm = (new PermissionDAO(Connexion.getInstance())).read(idCatalogue, idCreateur);
          //int verif = perm.getValeurPermission()&Operation.creerDossier;
          if ((perm==null)||((perm.getValeurPermission()&Operation.creerDossier)!=Operation.creerDossier)){
              message="Vous  ne possedez pas le droit de création de dossiers dans ce catalogue.";
          }
          else {
            DossierDAO dDAO = new DossierDAO(Connexion.getInstance());
            if (dDAO.readParNom(nom)!=null){
              message= "Il existe déjà un catalogue avec ce nom.";
            }else {
                if((!"".equals(nom))&&(!"".equals(type)))
                {
                      if(dDAO.create(new Dossier("",nom,type,"",idCreateur,"","",idCatalogue)))
                      {
                          message="Le dossier "+nom+" a été crée.";
                          typeMessage="msg done";
                      }                       
                }
                else {
                    message="Le nom et le type du dossier ne peuvent pas être des valeurs vides.";
                }
            }
          }
          request.setAttribute("message", message);
          request.setAttribute("typeMessage", typeMessage);
          RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?afficherPage=ajouterDossier");
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
