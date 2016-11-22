<%-- 
    Document   : formulaireCreationCatalogue
    Created on : 7 janv. 2013, 10:13:19
    Author     : Carlos
--%>

<%@page import="com.gcf.modele.Membre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1>Formulaire de création de catalogue</h1>
<form action="ctrl.do" method="post">
    <input type="hidden" name="action" value="creationCatalogue" />
    <input type="hidden" name="idCreateur" value="<%=(((Membre)(request.getSession().getAttribute("connecte"))).getNumero())%>" />
    <% if (request.getAttribute("message")!=null){%>
        <p class="<%=((String)request.getAttribute("typeMessage"))%>"><%=((String)request.getAttribute("message"))%></p>
    <%}%>
    <fieldset> 
    <legend>Remplissez le formulaire</legend>
    <table class="nostyle">
        <tr>
            <td style="width:70px;">Nom du catalogue:</td>
            <td><input type="text" size="40" name="nomCatalogue" class="input-text" /></td>
        </tr>
        <tr>
            <td>Code secret:</td>
            <td><input type="text" size="40" name="codeSecret" class="input-text" /></td>
        </tr>
        <tr>
            <td colspan="2" class="t-right"><input type="submit" class="input-submit" value="Créer" /></td>
        </tr>
    </table>
      
    </fieldset>
  
</form>
  