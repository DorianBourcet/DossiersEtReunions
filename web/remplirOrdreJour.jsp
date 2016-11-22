

<%@page import="java.util.List"%>
<%@page import="com.gcf.modele.PointDiscussion"%>

<h1>Ordre du jour</h1>
<% if (request.getAttribute("message")!=null){%>
        <p class="<%=((String)request.getAttribute("typeMessage"))%>"><%=((String)request.getAttribute("message"))%></p>
<%}%>
<%if (request.getAttribute("listeOrdreDuJour")!=null){
    List<PointDiscussion> listePD=(List<PointDiscussion>)(request.getAttribute("listeOrdreDuJour"));
    if(listePD.isEmpty()){
      out.println("<p class=\"msg info\">Vous n'avez pas de point de discussion r<p>");
    }
    else
    {
        request.setAttribute("listePointD", listePD);
        out.println("<form method=\"POST\" action=\"ctrl.do\">");
        out.println("<table id=\"ordreDujour\" >");
        for(int i=0;i<listePD.size();i++){
            out.println("<tr class=\"t-center\"><td>Sujet:</td><td>"+listePD.get(i).getNom() +"</td><td>Type:</td><td>"+listePD.get(i).getType() +"</td></tr><tr><td>Contenu:</td><td><textarea rows=\"2\" cols=\"50\" name=\"info"+i+"\"></textarea></td></tr>");
            out.println("<input type=\"hidden\" name=\"idDossier"+i+"\" value=\""+listePD.get(i).getIdDossier()+"\" />");
        }
        out.println("<tr><td></td><td></td><td></td><td><input type=\"submit\" value=\"Enregistrer tout\" class=\"input-submit\" /></td></tr>");     
        out.println("</table>");
        out.println("<input type=\"hidden\" name=\"action\" value=\"remplirODJ\" />");
        out.println("<input type=\"hidden\" name=\"nombrePD\" value=\""+listePD.size()+"\" />");
        out.println("<input type=\"hidden\" name=\"idReunion\" value=\""+request.getParameter("idReunion") +"\" />");
        out.println("</form>");
        
        }
};    

%>