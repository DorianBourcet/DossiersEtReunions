
<%@page import="com.gcf.modele.Dossier"%>
<%@page import="java.util.List"%>


<%
 List<Dossier> listeDossier= (List<Dossier>)(request.getAttribute("listeDossiers"));
    out.println("<h3 class=\"tit\">Liste des dossiers<span class=\"tag\"></span></h3><ul>");   
    for(int i=0;i<listeDossier.size();i++){
        out.println("<li><a href=\"ctrl.do?action=voirMAJ&idDossier="+listeDossier.get(i).getIdDossier()+"\">"+listeDossier.get(i).getNom()+"</a></li>");    
    }
     out.println("</ul>");
       
%>