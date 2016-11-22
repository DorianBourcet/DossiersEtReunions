<%@page import="com.gcf.modele.Reunion"%>
<%@page import="com.gcf.modele.Dossier"%>
<%@page import="java.util.List"%>
<% if (request.getAttribute("listeDossiers")==null){
%>  <jsp:forward page="/ctrl.do">
        <jsp:param name="action" value="getListesDossiersReunions"/>
    </jsp:forward>
<%
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd">

<body>
       <h3 class="tit">Bienvenue au catalogue</h3>
			<div class="col50">
			
				<p class="t-justify">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus et risus. Maecenas non nunc. Proin eleifend viverra sapien. Donec id augue. Duis erat nunc, volutpat a, bibendum quis, placerat vitae, enim. Etiam consectetur, velit in viverra tempus, urna augue sollicitudin tellus, vitae interdum arcu mi at est. Donec ornare, libero vitae facilisis molestie, mi sapien venenatis felis, sed mattis lectus nisi ac massa. Cras suscipit, neque ac auctor interdum, pede nibh porta lectus, nec aliquet nulla ipsum ac nibh. Morbi feugiat ipsum id metus. In urna sapien, porttitor sed, consectetur quis, lacinia eu, ante. Donec at ipsum. Sed arcu tellus, dapibus sit amet, auctor nec, rutrum non, lacus. Nam ut lorem eu orci placerat iaculis. Proin bibendum. Suspendisse consequat.</p>
				
			</div> <!-- /col50 -->

			<div class="col50 f-right">
			
				<p class="t-justify">
                                We're no strangers to love 
                                You know the rules and so do I 
                                A full commitment's what I'm thinking of 
                                You wouldn't get this from any other guy 
                                I just wanna tell you how I'm feeling 
                                Gotta make you understand 

                                Never gonna give you up, 
                                Never gonna let you down 
                                Never gonna run around and desert you 
                                Never gonna make you cry, 
                                Never gonna say goodbye 
                                Never gonna tell a lie and hurt you 

                                We've known each other for so long 
                                Your heart's been aching but you're too shy to say it 
                                Inside we both know what's been going on 
                                We know the game and we're gonna play it 
                                And if you ask me how I'm feeling 
                                Don't tell me you're too blind to see
                                wat
                                </p>	
			</div>
                        <div class="fix"></div>

    <%--Tabs dossier & reunion change places depending on how long the last div is. --%>
    <div class="tabs box">
        <ul>
                <li><a href="#tab01"><span>Dossiers</span></a></li>
                <li><a href="#tab02"><span>Reunions</span></a></li>
        </ul>
    </div> <!-- /tabs -->

    <!-- Tab01 -->
    <div id="tab01">				
        <%
        //Liste de dossier
        List<Dossier> listeDossiers= (List<Dossier>)(request.getAttribute("listeDossiers"));
        
        if (listeDossiers.isEmpty()){
            out.println("<p class=\"msg error\">I'l n'y a aucun dossier associé à ce catalogue.<p>");
        }
        else {
            //Permission de fermer dossier.
            Boolean pFermDos = (Boolean)request.getAttribute("pFermDos");            
            out.print("<table><tr><th>Dossier</th><th>État</th><th>Informations</th>");
            if (pFermDos.booleanValue())out.print("<th>Fermer</th>");
            out.println("</tr>");  
            for(int i=0;i<listeDossiers.size();i++){
                String bg=(i%2==0)?"class=\"bg\"":"";
                out.print("<tr"+bg+"><td>"+listeDossiers.get(i).getNom()+"</td><td>"+listeDossiers.get(i).getEtat()+"</td><td align=\"center\"><a href=\"ctrl.do?action=voirMAJ&idDossier="+listeDossiers.get(i).getIdDossier()+"\"><img src=\"design/edit-icon.gif\" /></a></td>");
                if (pFermDos.booleanValue())out.print("<td align=\"center\"><a href=\"#\"><img src=\"design/block.png\" /></a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
        %>
    </div> <!-- /tab01 -->

    <!-- Tab02 -->
    <div id="tab02">
        <%//<p>Injecter liste de reunions en balise a </p>
        List<Reunion> listeReunions= (List<Reunion>)(request.getAttribute("listeReunions"));
        if (listeReunions.isEmpty()){
            out.println("<p class=\"msg error\">I'l n'y a aucune réunion associée à ce catalogue.<p>");
        }
        else {
            //Permission de fermer reunion
            Boolean pFermReu = (Boolean)request.getAttribute("pFermReu");
            out.println("<table><tr><th>Reunion</th><th>État</th><th>Date</th><th>Modifier</th>");
            if (pFermReu.booleanValue())out.print("<th>Fermer</th>");
            out.println("</tr>"); 
            for(int i=0;i<listeReunions.size();i++){
                out.println("<tr><td>"+listeReunions.get(i).getTitre()+"</td><td>"+listeReunions.get(i).getEtat()+"</td><td>"+listeReunions.get(i).getDate()+"</td><td align=\"center\"><a href=\"ctrl.do?action=consulterInfoReunion&idReunion="+listeReunions.get(i).getIdReunion()+ "\"><img src=\"design/edit-icon.gif\" /></a></td>");
                if (pFermReu.booleanValue())out.print("<td align=\"center\"><a href=\"ctrl.do?action=fermerReunion&idReunion="+ listeReunions.get(i).getIdReunion() +"\"><img src=\"design/block.png\" /></a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            if(request.getAttribute("message")!=null){
              out.println("<p class=\""+((String)(request.getAttribute("typeMessage")))+"\">"+((String)(request.getAttribute("message"))) +"</p>");
            }
        }
        %>
    </div>                    
</body>

