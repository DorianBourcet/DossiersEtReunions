<%
    if ((request.getAttribute("listeCat")==null)||(request.getAttribute("listeProprietaire")==null))
    { pageContext.forward("ctrl.do?action=voirListeCat");
    }
%>
<script language="javascript">
    function afficherFormulaire(id){
       toggle('formulaireInscription');       
       document.forms['formInscrip'].idCatalogue.value = id;
    }
</script>
<%@page import="java.util.List"%>
<%@page import="com.gcf.modele.Catalogue"%>
<%
    List<Catalogue> listeCat = (List<Catalogue>)request.getAttribute("listeCat");
    List<String> listeProprietaire= (List<String>)request.getAttribute("listeProprietaire");
    List<Catalogue> listeCatInscrit = (List<Catalogue>)request.getAttribute("listeCatInscrit");
    List<String> listeCatInscritProprietaire= (List<String>)request.getAttribute("listeCatInscritProprietaire");
    if (request.getAttribute("message")!=null){
        out.println("<p class=\""+request.getAttribute("typeMessage")+"\">"+request.getAttribute("message")+"</p>");
    }
%>
<h1>Liste des catalogues</h1>
<br />
<form style="display:none;" id="formulaireInscription" action="ctrl.do" name="formInscrip">
    <input type="hidden" name="action" value="inscriptionCatalogue" />
    <label>Pour vous inscrire au catalogue, entrez le code secret.</label>
    <table class="nostyle">
        <tr><td>Catalogue</td><td><input type="text" name="idCatalogue" value="" readonly="readonly"/></td></tr>
        <tr><td>Code secret</td><td><input type="password" name="code" /></td><td><input type="submit" value=" S'inscrire "></td></tr>
    </table>
</form>
<br />
<table>
    <tr><th>S'inscrire</th><th>Nom du catalogue</th><th>Créateur</th><th>Date de création</th></tr>
    <%
    for (int i=0;i<listeCatInscrit.size();i++){
        out.println("<tr><td></td><td><a href=\"ctrl.do?action=changerCatCourant&idCatalogue="+listeCatInscrit.get(i).getNumeroCat()+"\">"+listeCatInscrit.get(i).getNomCatalogue()+"</a></td><td>"+listeCatInscritProprietaire.get(i)+"</td><td>"+listeCatInscrit.get(i).getdateCreation()+"</td></tr>");
    }
    for (int i=0;i<listeCat.size();i++){
        out.println("<tr><td align=\"center\"><a href=\"javascript:afficherFormulaire('"+listeCat.get(i).getNumeroCat() +"');\"><img src=\"design/add-icon.gif\"/></a></td><td>"+listeCat.get(i).getNomCatalogue()+"</td><td>"+listeProprietaire.get(i)+"</td><td>"+listeCat.get(i).getdateCreation()+"</td></tr>");
    }
    %>
</table>