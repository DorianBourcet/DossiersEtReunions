<%-- 
    Document   : FormulaireCreationDossier
    Created on : 7 janv. 2013, 10:45:15
    Author     : Gilbert
--%>
<%@page import="com.gcf.modele.Dossier"%>
<%@page import="com.gcf.modele.Membre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1>Formulaire de création de dossier</h1>
 <form method="POST" action="ctrl.do">
     <input type="hidden" name="action" value="creationDossier" />
     <% if (request.getAttribute("message")!=null){%>
        <p class="<%=((String)request.getAttribute("typeMessage"))%>"><%=((String)request.getAttribute("message"))%></p>
     <%}%>
     <fieldset>
        <legend>Remplissez le formulaire</legend>
    <table class="nostyle">          
        <tr>
          <td>Nom du dossier:</td>
	  <td><input type="text" size="40" name="nom" class="input-text" /></td>
       </tr>
        <tr>
          <td>Type:</td>
	  <td><input type="text" size="40" name="type" class="input-text" /></td>
       </tr>    
        <tr>
         <td colspan="2" class="t-right"><input type="submit" name="OkCreerDossier" class="input-text" value="Créer le dossier"/></td>
        </tr>       
</table>
 </fieldset> 
</form> 