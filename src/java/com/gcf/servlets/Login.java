package com.gcf.servlets;
import com.gcf.DAO.implement.CatalogueDAO;
import com.gcf.DAO.implement.MembreDAO;
import com.gcf.modele.Catalogue;
import com.gcf.modele.Membre;
import com.gcf.util.Connexion;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Chargement du pilote :
            Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
            
        } catch (ClassNotFoundException ex) {
            request.setAttribute("message", "Impossible de charger le pilote");
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/login.jsp");
            r.forward(request, response);
        }
        Connexion.setUrl(this.getServletContext().getInitParameter("URLbaseDonnees"));
        String  u = request.getParameter("username"),
                p = request.getParameter("password");     
        if (u==null || u.trim().equalsIgnoreCase(""))
        {
            //Utilisateur non saisi :
            request.setAttribute("message", "Username obligatoire");
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/login.jsp");
            r.forward(request, response);
            return;
        }
        
        MembreDAO mDAO=new MembreDAO(Connexion.getInstance());
        Membre member=mDAO.read(u.trim());
        Catalogue catDefaut=null;
        if (member!=null){
           if (member.getMotDePasse().equals(p))
                {
                    //Connexion OK
                    if (member.getCatalogueDefaut()!=null){
                        CatalogueDAO cDAO=new CatalogueDAO(Connexion.getInstance());
                        catDefaut=cDAO.read(member.getCatalogueDefaut());
                      }
                    HttpSession session = request.getSession(true);
                    member.setMotDePasse("*****");//pour sécurité.
                    session.setAttribute("connecte", member);
                    session.removeAttribute("catalogueCourant");
                    if (catDefaut!=null)
                        {
                        session.setAttribute("catalogueCourant",catDefaut);
                        }
                    RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
                    r.forward(request, response);
                }
                else
                {
                    //Mot de passe incorrect
                    request.setAttribute("message", "Mot de passe incorrect.");
                    RequestDispatcher r = this.getServletContext().getRequestDispatcher("/login.jsp");
                    r.forward(request, response);
                }
        }
        else{
            //Utilisateur inexistant
            request.setAttribute("message", "Utilisateur "+u+" inexistant.");
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/login.jsp");
            r.forward(request, response);
        }
     }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
