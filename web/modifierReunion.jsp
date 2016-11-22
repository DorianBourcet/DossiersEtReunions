
<%@page import="com.gcf.modele.Catalogue"%>
<%@page import="com.gcf.modele.Membre"%>
<form action="ctrl.do" method="post">
    <input type="hidden" name="action" value="creationReunion" />
    <input type="hidden" name="idCreateur" value="<%=((request.getSession().getAttribute("idReunion"))%>" />
    
    <% if (request.getAttribute("message")!=null){%>
        <p class="<%=((String)request.getAttribute("typeMessage"))%>"><%=((String)request.getAttribute("message"))%></p>
    <%}%>
    <fieldset>
    <legend>Formulaire de modification d'une réunion</legend>
    <table class="nostyle">
        <tr>
            <td style="width:70px;">Date:</td>
            <td><input type="text" name="dateReunion" id="date" /></td>
           
        </tr> 
        <tr>
           <td>Heure:</td> 
           <td><% out.println("<select name=\"heureReunion\" >");
                  for(int i=0;i<24;i++){out.println(" <option value="+i+">"+i+"</option>");}
                  out.println("</select>"); %>
                     :
                <% out.println("<select name=\"minReunion\" >");
                  for(int i=0;i<60;i+=5){out.println(" <option value="+i+">"+i+"</option>");}
                  out.println("</select>"); %> </td>
        </tr>
        <tr>
            <td>Titre:</td>
            <td><input type="text" size="40" name="titreReunion" class="input-text" /></td>
        </tr>
        <tr>
            <td>Status:</td>
            <td><input type="text" size="40" name="titreReunion" class="input-text" /></td>
        </tr>   
        <tr>
            <td colspan="2" class="t-right"><input type="submit" class="input-submit" value="Créer" /></td>
        </tr>
        
        
    </table>
   <br />&nbsp<br />&nbsp<br />
    </fieldset>
</form>
