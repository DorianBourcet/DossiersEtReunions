<%-- 
    Document   : FormulaireCreationPointDeDiscussion
    Created on : 2013-01-17, 19:29:04
    Author     : usager
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd">

<h1>Formulaire de création de Point de discussion</h1>
<form action="ctrl.do" method="post">
<input type="hidden" name="action" value="creationPointDiscussion" />
    <fieldset> 
   <legend>Remplissez le formulaire</legend>
   <table class="nostyle">
        <tr>
            <td style="width:70px;">Nom du poit de discussion:</td>
            <td><input type="text" size="40" name="nomPD" class="input-text" /></td>
        </tr>
        <tr>
            <td style="width:70px;">Type:</td>
            <td><input type="text" size="40" name="typePD" class="input-text" /></td>
        </tr>
        <tr>
            <td style="width:70px;">Type:</td>
            <td><input type="text" size="40" name="etatPointDiscussion" class="input-text" /></td>
        </tr>
   </table>
   </fieldset>    
</form>
