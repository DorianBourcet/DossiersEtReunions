<%-- 
    Document   : modifierPermissionMembre
    Created on : 2013-01-18, 18:45:57
    Author     : Usager
--%>
<%
    if (request.getAttribute("permissionMembre")==null){
        pageContext.forward("ctrl.do?action=getListePermission&idMembre="+request.getParameter("idMembre"));
    }
%>
<script language="javascript">
    function changeForm(){
       toggle('permissionPredefinies');   
       toggle('permissionPersonnalisees');   
    }
</script>
<%@page import="com.gcf.modele.Operation"%>
<h1>Modifications des permissions du membre <%=request.getParameter("idMembre")%></h1>
<br />
<%--




    Normalmente, deberiamos ir por el numero del miembro, poner su nombre arriba
    y poner una lista de los permissos que ya tiene.





--%>
<% if (request.getAttribute("message")!=null){%>
        <p class="<%=((String)request.getAttribute("typeMessage"))%>"><%=((String)request.getAttribute("message"))%></p>
<%}%>

<fieldset>
    <legend>Choissisez le type de permissions:</legend>
    
<input type="radio" name="typeForm" value="" onclick="changeForm();">Personnalisées &nbsp&nbsp
<input type="radio" name="typeForm" value="" checked="checked" onclick="changeForm();" >Prédéfinies
   <!--     <a href="javascript:changeForm();" >Changer</a> <hr />
<br />-->
<br />

<form action="ctrl.do" method="post" id="permissionPredefinies">
  <!--  <h3>Permissions prédéfinies</h3>
    <h4>Choissisez une des permissions prédéfinies</h4> -->
    <select name="permissionPredef">
        <option value="simple">Simple membre</option>
        <option value="mod">Moderateur</option>
        <option value="admin">Administrateur</option>
    </select>
    <input type="hidden" name="action" value="modifPerm1">
    <input type="submit" value=" Modifier ">
</form>
<form action="ctrl.do" method="post" id="permissionPersonnalisees" style="display:none;">
  <!--  <h3>Permissions personnalisées</h3>
    <h4>Selectionnez les nouvelles permissions</h4> -->
    <table class="nostyle">
        <tr><td><input type="checkbox" name="creerDos"/> Créer dossier</td><td><input type="checkbox" name="creerReu"/> Créer réunion</td></tr>
        <tr><td><input type="checkbox" name="creerMAJ"/> Créer mise à jour</td><td><input type="checkbox" name="creerPDD"/> Créer point de discussion</td></tr>
        <tr><td><input type="checkbox" name="modDos"/> Modifier dossier</td><td><input type="checkbox" name="modifReu"/> Modifier réunion</td></tr>  
        <tr><td><input type="checkbox" name="fermDos"/> Fermer dossier</td><td><input type="checkbox" name="fermReu"/> Fermer réunion</td></tr>
        <tr><td><input type="checkbox" name="gererPerm"/> Gérer permissions</td><td><input type="checkbox" name="gererInv"/> Gérer liste des invités aux réunions</td></tr>
        <tr><td><input type="checkbox" name="modifCat"/> Modifier catalogue</td><td><input type="checkbox" name="SuppCat"/> Supprimer catalogue</td></tr>
    </table>
    <input type="hidden" name="action" value="modifPerm2">
    <input type="submit" value=" Modifier ">
</form>
</fieldset>