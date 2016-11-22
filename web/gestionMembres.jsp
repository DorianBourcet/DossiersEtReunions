<%-- 
    Document   : gestionMembres
    Created on : 10-ene-2013, 10:10:37
    Author     : Trauko
--%>


<%@page import="com.gcf.modele.Membre"%>
<%@page import="java.util.List"%>
          
            <%                   
            List<Membre> listeMembres=(List<Membre>)(request.getAttribute("listeMembres"));
            if (listeMembres==null){ %><jsp:forward page="ctrl.do?action=getListeMembreCatalogue" /><%
            }
            String idMembre=((Membre)(request.getSession().getAttribute("connecte"))).getNumero();
            String permission="null";
            
            if(request.getParameter("profilMembre")!=null){
               if (listeMembres.isEmpty()){ 
               out.println("vacia");
              }
             else{
             int profilMembre=Integer.parseInt(request.getParameter("profilMembre"));
                out.println("<h2>Profil</h2>");
                out.println("<table class=\"nostyle\"><tr><td>Nom de l'usager:</td><td>"+listeMembres.get(profilMembre).getNom() +"</td></tr><tr><td>Courriel:</td><td>"+listeMembres.get(profilMembre).getCourriel() +"</td></tr></table>");
               }
            }
            out.println("<h1>Gestion des membres</h1>");
            out.println("<table><tr><th>Nom de l'usager</th><th>Profil</th><th>Permissions</th><th>Effacer</th></tr>");           
            for(int i=0;i<listeMembres.size();i++){
               if(idMembre.equals(listeMembres.get(i).getNumero()))
                   permission="";
                  else
                   permission="<a href=\"#\"><img src=\"design/hr.gif\" width=\"16\" height=\"16\"  /></a>";
                       out.println( "<tr><td align=\"center\">"+listeMembres.get(i).getNom() +"</td><td align=\"center\"><a href=\"ctrl.do?action=getListeMembreCatalogue&profilMembre="+i+"\"><img src=\"design/login-icon.gif\" width=\"16\" height=\"16\" /></a></td><td align=\"center\"><a href=\"index.jsp?afficherPage=modifPermMem&idMembre="+listeMembres.get(i).getNumero() +"\"><img src=\"design/edit-icon.gif\" width=\"16\" height=\"16\"  /></a></td><td align=\"center\">"+permission+"</td></tr>"); 
             }
            out.println("</table>");
        %> 
         

