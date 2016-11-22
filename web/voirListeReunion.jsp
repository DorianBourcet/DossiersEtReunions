
<%@page import="com.gcf.modele.Reunion"%>

<%@page import="java.util.List"%>


<%
 List<Reunion> listeReunions= (List<Reunion>)(request.getAttribute("listeReunions"));
    out.println("<h3 class=\"tit\">Liste de reunion<span class=\"tag\"></span></h3><ul>");   
    for(int i=0;i<listeReunions.size();i++){
        out.println("<li><a href=\"ctrl.do?action=consulterInfoReunion&idReunion="+listeReunions.get(i).getIdReunion()+ "\">"+listeReunions.get(i).getTitre()+"</a></li>");    
    }
     out.println("</ul>");
       
%>
