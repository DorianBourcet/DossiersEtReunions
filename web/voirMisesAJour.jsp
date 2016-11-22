<%-- 
    Document   : voirMisesAJour
    Created on : 2013-01-16, 11:35:51
    Author     : Usager
--%>
<%@page import="com.gcf.modele.Dossier"%>
<%if ((request.getAttribute("dossier")==null)||(request.getAttribute("listeMAJ")==null)){
        out.println("<h3>Erreur lors de la recherche</h3>");
        out.println("<a href=\"index.jsp?afficherPage=afficherBienvenue\"><h2>Retour à la liste de dossiers</h2></a>");
        out.close();
    }
    //Dossier et liste de MAJ qu'on aura besoin.
    Dossier d = (Dossier)request.getAttribute("dossier");
    List<MiseAJour> liste = (List<MiseAJour>)request.getAttribute("listeMAJ");
    List<String> listeAuteurs = (List<String>)request.getAttribute("listeAuteurs");
%>
<%@page import="java.util.List"%>
<%@page import="com.gcf.modele.MiseAJour"%>
<a href="index.jsp?afficherPage=afficherBienvenue"><h2>«Retour à la liste de dossiers</h2></a>
<h1>Informations du dossier <%=d.getNom()%> </h1>
<table class="nostyle">
    <tr><td>Type</td><td><%=d.getType()%></td></tr>
    <tr><td>Nom du dossier</td><td><%=d.getNom()%></td></tr>
    <tr><td>Etat</td><td><%=d.getEtat()%></td></tr>
    <tr><td>Nom du créateur</td><td><%=(String)request.getAttribute("nomCreateur")%></td></tr>
    <tr><td>Date de création</td><td><%=d.getDateCreation()%></td></tr>
    <%--Dans la ligne suivante, il faut changer !"null" par "null" ou ""... il faut encore tester.--%>
    <tr><td>Date de fermeture</td><td><%=(!"null".equals(d.getDateFermeture()))?"Ce dossier n'est pas fermé.":d.getDateFermeture()%></td></tr>
    <tr><td><a href="#">Modifier les informations</a></td></tr>
</table>
<h1>Mises à jours du dossier <%=d.getNom()%> </h1>
 
    <%       
        if (liste.isEmpty()){
            out.println("<p class=\"msg warning\">Aucune mise à jour a été trouvée pour le dossier "+d.getNom()+".</p>");
        }else{
            for(int i=0;i<liste.size();i++){
                out.println("<table class=\"nostyle\"><tr><th>Auteur</th><td>"+listeAuteurs.get(i) +"</td><th>Date</th><td>"+liste.get(i).getDate()+"</td></tr>");
                out.println("<tr><th>Nature</th><td>"+liste.get(i).getNature()+"</td></tr></table>");
                out.println("<div class=\"col50\"><p class=\"t-justify\">"+liste.get(i).getInfo() +"</p></div><div class=\"fix\"></div>");
                if (i+1<liste.size()) out.println("<hr />");
            }
        }
    out.close();
    %>
