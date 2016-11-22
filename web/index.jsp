<?xml version="1.0"?>
<%@page import="com.gcf.modele.Operation"%>
<%@page import="com.gcf.util.Connexion"%>
<%@page import="com.gcf.modele.Permission"%>
<%@page import="com.gcf.DAO.implement.PermissionDAO"%>
<%@page import="com.gcf.modele.Catalogue" %>
<%@page import="com.gcf.modele.Membre"%>
<%
    if ((session.getAttribute("connecte"))==null){
        %><jsp:forward page="/login.jsp" /><%
    }
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta http-equiv="content-language" content="en" />
	<meta name="robots" content="noindex,nofollow" />
	<link rel="stylesheet" media="screen,projection" type="text/css" href="style/reset.css" /> <!-- RESET -->
	<link rel="stylesheet" media="screen,projection" type="text/css" href="style/main.css" /> <!-- MAIN STYLE SHEET -->
	<link rel="stylesheet" media="screen,projection" type="text/css" href="style/2col.css" title="2col" /> <!-- DEFAULT: 2 COLUMNS -->
	<link rel="alternate stylesheet" media="screen,projection" type="text/css" href="style/1col.css" title="1col" /> <!-- ALTERNATE: 1 COLUMN -->
	<!--[if lte IE 6]><link rel="stylesheet" media="screen,projection" type="text/css" href="css/main-ie6.css" /><![endif]--> <!-- MSIE6 -->
	<link rel="stylesheet" media="screen,projection" type="text/css" href="style/style2.css" /> <!-- GRAPHIC THEME -->
	<link rel="stylesheet" media="screen,projection" type="text/css" href="style/mystyle.css" /> <!-- WRITE YOUR CSS CODE HERE -->
	<link rel="stylesheet" type="text/css" media="screen,projection" href="style/jsDatePick_ltr.min.css" />
        <script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/switcher.js"></script>
	<script type="text/javascript" src="js/toggle.js"></script>
	<script type="text/javascript" src="js/ui.core.js"></script>
	<script type="text/javascript" src="js/ui.tabs.js"></script>
	
        <script type="text/javascript" src="js/jsDatePick.jquery.min.1.3.js"></script>
        <script type="text/javascript">
	window.onload = function(){
		new JsDatePick({
			useMode:2,
			target:"date",
			dateFormat:"%Y-%M-%d"
                     			/*selectedDate:{			
				day:5,						
				month:9,
				year:2006
			},
			yearsRange:[1978,2020],
			limitToToday:false,
			cellColorScheme:"beige",
			dateFormat:"%m-%d-%Y",
			imgPath:"img/",
			weekStartDay:1*/
		});
	};
    </script>
        <script type="text/javascript">
	$(document).ready(function(){
		$(".tabs > ul").tabs();
	});
	</script>
     
        
	<title>Gestion de dossiers et de catalogues</title>
</head>

<body>
   
<div id="main">

	<!-- Tray -->
	<div id="tray" class="box">

		<p class="f-left box">

			<!-- Switcher -->
			<span class="f-left" id="switcher">
				<a href="#" rel="1col" class="styleswitch ico-col1" title="Display one column"><img src="design/switcher-1col.gif" alt="1 Column" /></a>
				<a href="#" rel="2col" class="styleswitch ico-col2" title="Display two columns"><img src="design/switcher-2col.gif" alt="2 Columns" /></a>
			</span>

			Catalogue: <% if(request.getSession().getAttribute("catalogueCourant")!=null){
                                            out.println("<a href=\"#\" name=\"indexCatalogue\" >" + (((Catalogue)request.getSession().getAttribute("catalogueCourant")).getNomCatalogue()) +"</a>"); 
                                           }
                                              else {
                                            out.println("Aucun");
                                            }
                                   %>

		</p>

            <p class="f-right">Usager: <strong><a href="#" id="indexUsager"><%=(((Membre)request.getSession().getAttribute("connecte")).getNom())%></a></strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><a href="ctrl.do?action=logout" id="logout">Deconnection</a></strong></p>

	</div> <!--  /tray -->

	<hr class="noscreen" />

	<!-- Menu -->
	<div id="menu" class="box">

		<ul class="box f-right">
			<li><a href="http://www.crosemont.qc.ca"><span><strong>Site du collège &raquo;</strong></span></a></li>
		</ul>

		<ul class="box">
			<% if(request.getSession().getAttribute("catalogueCourant")!=null)
                        {%>
                            <li id="menu-active"><a href="index.jsp?afficherPage=ajouterDossier"><span>Créer un dossier</span></a></li> <!-- Active -->
                            <li><a href="index.jsp?afficherPage=ajouterReunion"><span>Créer une reunion</span></a></li>
                            <%
                                if (((((new PermissionDAO(Connexion.getInstance())).read(((Catalogue) request.getSession().getAttribute("catalogueCourant")).getNumeroCat(), ((Membre)(request.getSession().getAttribute("connecte"))).getNumero())).getValeurPermission()&Operation.creerDossier)==Operation.creerDossier)){out.println("<li><a href=\"ctrl.do?action=getListeMembreCatalogue\"><span>Gérer de membres</span></a></li>");}
                            %>    
                          <!--  <li><a href="#"><span>Lorem ipsum</span></a></li>
                            <li><a href="#"><span>Lorem ipsum</span></a></li>
                            <li><a href="#"><span>Lorem ipsum</span></a></li>
                            <li><a href="#"><span id="gestionUsager" >Lorem ipsum</span></a></li>-->
                        <%}%>
		</ul>

	</div> <!-- /header -->

	<hr class="noscreen" />

	<!-- Columns -->
	<div id="cols" class="box">

		<!-- Aside (Left Column) -->
		<div id="aside" class="box">

			<div class="padding box">

				<!-- Logo (Max. width = 200px) -->
				<p id="logo"><%
                                if(request.getSession().getAttribute("catalogueCourant")!=null)
                                    {out.println("<a href=\"index.jsp?afficherPage=afficherBienvenueAvec\">");}
                                else{out.println("<a href=\"index.jsp?afficherPage=afficherBienvenueSans\">");}  %>
                                <img src="design/Logo.png" alt="Our logo" title="Visit Site" /></a></p>

				<!-- Search -->
				<form action="ctrl.do" method="get" id="search">
					<fieldset>
						<legend>Rechercher un catalogue</legend>
                                                <input type="hidden" name="action" value="voirListeCat" />
						<p><input type="text" size="17" name="" class="input-text" />&nbsp;<input type="submit" value="OK" class="input-submit-02" /><br />
						<a href="javascript:toggle('search-options');" class="ico-drop">Recherche avancée</a></p>

						<!-- Advanced search -->
						<div id="search-options" style="display:none;">

							<p>
                                                            <label><input type="radio" name="type" checked="checked" /> Par nom</label><br />
                                                            <label><input type="radio" name="type" /> Par créateur</label><br />
							</p>

						</div> <!-- /search-options -->

					</fieldset>
				</form>

				<!-- Create a new project -->
				<p id="btn-create" class="box"><a href="index.jsp?afficherPage=ajouterCatalogue"><span>Creer un catalogue</span></a></p>

			</div> <!-- /padding -->

			<ul class="box">
                                <li><a href="ctrl.do?action=voirListeCat">Liste des catalogues</a></li>
				<% if(request.getSession().getAttribute("catalogueCourant")!=null)
                                {%>
                                <li><a href="ctrl.do?action=getListesDossiersReunions&doVoirDossier=liste">Liste de dossiers</a></li>
				<li><a href="ctrl.do?action=getListesDossiersReunions&doVoirReunion=liste">Liste de réunions</a></li>
                                
				<li id="submenu-active"><a href="#">Fonctions</a> <!-- Active -->
					<ul>
						<li><a href="index.jsp?afficherPage=ajouterDossier">Créer un dossier</a></li>
						<li><a href="index.jsp?afficherPage=ajouterReunion">Créer une réunion</a></li>
						<li><a href="index.jsp?afficherPage=gestionMembres">Gérer les Membres</a></li>
						
					</ul>
				</li>
                                <%}%>
				<!--
                                <li><a href="#">Lorem ipsum</a></li>
				<li><a href="#">Lorem ipsum</a>
					<ul>
						<li><a href="#">Lorem ipsum</a></li>
						<li><a href="#">Lorem ipsum</a></li>
						<li><a href="#">Lorem ipsum</a></li>
					</ul>
                                 </li>
				<li><a href="#">Lorem ipsum</a></li>
                                -->
                        </ul>

		</div> <!-- /aside -->

		<hr class="noscreen" />

		<!-- Content (Right Column) -->
		<div id="content" class="box">  
                    <%
                        if (request.getParameter("afficherPage")==null)
                            { 
                                if(request.getSession().getAttribute("catalogueCourant")!=null)
                                    {
                                        %>
                                           <jsp:include page="bienvenueAvecCatalogue.jsp" />
                                       <%
                                     }
                                else 
                                    {
                                       %>
                                          <jsp:include page="bienvenueSansCatalogue.jsp" />
                                      <% 
                                    }
                            }
                        else 
                            {
                            String afficherPage = (String)request.getParameter("afficherPage");
                                                   
                            if ("ajouterCatalogue".equals(afficherPage))
                              {
                                %>
                                <jsp:include page="formulaireCreationCatalogue.jsp" />
                                <%
                              }
                            if ("afficherBienvenueAvec".equals(afficherPage))
                              {
                                %>
                                <jsp:include page="bienvenueAvecCatalogue.jsp" />
                                <%
                            }
                            if ("afficherBienvenueSans".equals(afficherPage))
                              {
                                %>
                                <jsp:include page="bienvenueSansCatalogue.jsp" />
                                <%
                            }
                            if ("afficherBienvenue".equals(afficherPage))
                              {
                                %>
                                <jsp:include page="bienvenueAvecCatalogue.jsp" />
                                <%
                            }
                            if ("ajouterDossier".equals(afficherPage))
                              {
                                %>
                                <jsp:include page="FormulaireCreationDossier.jsp" />
                                <%
                              }
       
                            if ("ajouterReunion".equals(afficherPage))
                              {
                                %>
                                <jsp:include page="formulaireCreationReunion.jsp" />
                                <%
                              }
                             if ("gestionMembres".equals(afficherPage))
                              {
                                %>
                                <jsp:include page="gestionMembres.jsp" />
                                <%
                              }
                             if ("voirMAJ".equals(afficherPage))
                              {
                                %>
                                <jsp:include page="voirMisesAJour.jsp" />
                                <%
                              }
                             if ("voirListeCat".equals(afficherPage))
                              {
                                %>
                                <jsp:include page="voirListeCatalogues.jsp" />
                                <%
                              }
                             if ("voirInfoReunion".equals(afficherPage))
                              {
                                %>
                                <jsp:include page="VoirInfoReunion.jsp" />
                                <%
                              }
                             if ("modifPermMem".equals(afficherPage))
                              {
                                %>
                                <jsp:include page="modifierPermissionMembre.jsp" />
                                <%
                              }
                            if ("afficherListeDossier".equals(afficherPage))
                              {
                                %>
                                <jsp:include page="VoirListeDossier.jsp" />
                                <%
                              }
                            if ("afficherListeReunion".equals(afficherPage))
                              {
                                %>
                                <jsp:include page="voirListeReunion.jsp" />
                                <%
                              }
                             if ("remplirOrdreJour".equals(afficherPage))
                              {
                                %>
                                <jsp:include page="remplirOrdreJour.jsp" />
                                <%
                              }
                           }
                     %>
			<br />
                    
                       <!-- <h1>Styles</h1> -->

			<!-- Headings 
			<h2>Heading H2</h2>
			<h3>Heading H3</h3>
			<h4>Heading H4</h4>
			<h5>Heading H5</h5>-->

			<!-- System Messages 
			<h3 class="tit">System Messages</h3>

			<p class="msg warning">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>

			<p class="msg info">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>

			<p class="msg done">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>

			<p class="msg error">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                        -->
                        
			<!-- Tabs 
			<h3 class="tit">Tabs</h3>

			<div class="tabs box">
				<ul>
					<li><a href="#tab01"><span>Lorem ipsum</span></a></li>
					<li><a href="#tab02"><span>Lorem ipsum</span></a></li>
					<li><a href="#tab03"><span>Lorem ipsum</span></a></li>
				</ul>
			</div>  /tabs -->

			<!-- Tab01 
			<div id="tab01">
			
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
			
			</div> /tab01 -->

			<!-- Tab02 
			<div id="tab02">

				<p>Donec ornare, libero vitae facilisis molestie, mi sapien venenatis felis, sed mattis lectus nisi ac massa.</p>

			</div>  /tab02 -->

			<!-- Tab03 
			<div id="tab03">

				<p>Nam ut lorem eu orci placerat iaculis.</p>

			</div>  /tab03 -->

			<!-- 2 columns 
			<h3 class="tit">2 Columns (50-50)</h3>
			
			<div class="col50">
			
				<p class="t-justify">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus et risus. Maecenas non nunc. Proin eleifend viverra sapien. Donec id augue. Duis erat nunc, volutpat a, bibendum quis, placerat vitae, enim. Etiam consectetur, velit in viverra tempus, urna augue sollicitudin tellus, vitae interdum arcu mi at est. Donec ornare, libero vitae facilisis molestie, mi sapien venenatis felis, sed mattis lectus nisi ac massa. Cras suscipit, neque ac auctor interdum, pede nibh porta lectus, nec aliquet nulla ipsum ac nibh. Morbi feugiat ipsum id metus. In urna sapien, porttitor sed, consectetur quis, lacinia eu, ante. Donec at ipsum. Sed arcu tellus, dapibus sit amet, auctor nec, rutrum non, lacus. Nam ut lorem eu orci placerat iaculis. Proin bibendum. Suspendisse consequat.</p>
				
			</div>  /col50 

			<div class="col50 f-right">
			
				<p class="t-justify">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus et risus. Maecenas non nunc. Proin eleifend viverra sapien. Donec id augue. Duis erat nunc, volutpat a, bibendum quis, placerat vitae, enim. Etiam consectetur, velit in viverra tempus, urna augue sollicitudin tellus, vitae interdum arcu mi at est. Donec ornare, libero vitae facilisis molestie, mi sapien venenatis felis, sed mattis lectus nisi ac massa. Cras suscipit, neque ac auctor interdum, pede nibh porta lectus, nec aliquet nulla ipsum ac nibh. Morbi feugiat ipsum id metus. In urna sapien, porttitor sed, consectetur quis, lacinia eu, ante. Donec at ipsum. Sed arcu tellus, dapibus sit amet, auctor nec, rutrum non, lacus. Nam ut lorem eu orci placerat iaculis. Proin bibendum. Suspendisse consequat.</p>
				
			</div>  /col50

			<div class="fix"></div>
                         -->
			<!-- 3 columns 
			<h3 class="tit">3 Columns (33-33-33)</h3>
			
			<div class="col33">
			
				<p class="t-justify">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus et risus. Maecenas non nunc. Proin eleifend viverra sapien. Donec id augue. Duis erat nunc, volutpat a, bibendum quis, placerat vitae, enim. Etiam consectetur, velit in viverra tempus, urna augue sollicitudin tellus, vitae interdum arcu mi at est. Donec ornare, libero vitae facilisis molestie, mi sapien venenatis felis, sed mattis lectus nisi ac massa. Cras suscipit, neque ac auctor interdum, pede nibh porta lectus, nec aliquet nulla ipsum ac nibh. Morbi feugiat ipsum id metus. In urna sapien, porttitor sed, consectetur quis, lacinia eu, ante. Donec at ipsum. Sed arcu tellus, dapibus sit amet, auctor nec, rutrum non, lacus. Nam ut lorem eu orci placerat iaculis. Proin bibendum. Suspendisse consequat.</p>
				
			</div>  /col33 

			<div class="col33 center">
			
				<p class="t-justify">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus et risus. Maecenas non nunc. Proin eleifend viverra sapien. Donec id augue. Duis erat nunc, volutpat a, bibendum quis, placerat vitae, enim. Etiam consectetur, velit in viverra tempus, urna augue sollicitudin tellus, vitae interdum arcu mi at est. Donec ornare, libero vitae facilisis molestie, mi sapien venenatis felis, sed mattis lectus nisi ac massa. Cras suscipit, neque ac auctor interdum, pede nibh porta lectus, nec aliquet nulla ipsum ac nibh. Morbi feugiat ipsum id metus. In urna sapien, porttitor sed, consectetur quis, lacinia eu, ante. Donec at ipsum. Sed arcu tellus, dapibus sit amet, auctor nec, rutrum non, lacus. Nam ut lorem eu orci placerat iaculis. Proin bibendum. Suspendisse consequat.</p>
				
			</div>  /col33 

			<div class="col33">
			
				<p class="t-justify">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus et risus. Maecenas non nunc. Proin eleifend viverra sapien. Donec id augue. Duis erat nunc, volutpat a, bibendum quis, placerat vitae, enim. Etiam consectetur, velit in viverra tempus, urna augue sollicitudin tellus, vitae interdum arcu mi at est. Donec ornare, libero vitae facilisis molestie, mi sapien venenatis felis, sed mattis lectus nisi ac massa. Cras suscipit, neque ac auctor interdum, pede nibh porta lectus, nec aliquet nulla ipsum ac nibh. Morbi feugiat ipsum id metus. In urna sapien, porttitor sed, consectetur quis, lacinia eu, ante. Donec at ipsum. Sed arcu tellus, dapibus sit amet, auctor nec, rutrum non, lacus. Nam ut lorem eu orci placerat iaculis. Proin bibendum. Suspendisse consequat.</p>
				
			</div>  /col33 
			
			<div class="fix"></div>
                        -->
			<!-- Text Alignment 
			<h3 class="tit">Text Alignment</h3>
			<p class="t-left">Left <span class="tag">(.t-left)</span></p>
			<p class="t-center">Center <span class="tag">(.t-center)</span></p>
			<p class="t-right">Right <span class="tag">(.t-right)</span></p>
                        -->
			<!-- Size and Highlight 
			<h3 class="tit">Text Size and Highlight</h3>
			<p>Lorem ipsum dolor sit amet</p>
			<p><strong>Lorem ipsum dolor sit amet</strong> <span class="tag">(&lt;strong&gt;)</span></p>
			<p><em>Lorem ipsum dolor sit amet</em> <span class="tag">(&lt;em&gt;)</span></p>
			<p><abbr>Lorem ipsum dolor sit amet</abbr>  <span class="tag">(&lt;abbr&gt;)</span></p>
			<p><acronym>Lorem ipsum dolor sit amet</acronym>  <span class="tag">(&lt;acronym&gt;)</span></p>
			<p><code>Lorem ipsum dolor sit amet</code> <span class="tag">(&lt;code&gt;)</span></p>
			<p><span class="help">Lorem ipsum dolor sit amet</span>  <span class="tag">(.help)</span></p>
			<p><span class="high">Lorem ipsum dolor sit amet</span> <span class="tag">(.high)</span></p>
			<p><span class="low">Lorem ipsum dolor sit amet</span> <span class="tag">(.low)</span></p>
			<p><span class="bigger">Lorem ipsum dolor sit amet</span> <span class="tag">(.bigger)</span></p>
			<p><span class="smaller">Lorem ipsum dolor sit amet</span> <span class="tag">(.smaller)</span></p>
                        -->
			<!-- Unordered List 
			<h3 class="tit">Unordered List - <span class="tag">(ul.nostyle)</span></h3>
			<ul class="nostyle">
				<li>Lorem ipsum dolor sit amet</li>
				<li>Lorem ipsum dolor sit amet</li>
				<li>Lorem ipsum dolor sit amet
				<ul>
					<li>Lorem ipsum dolor sit amet</li>
					<li>Lorem ipsum dolor sit amet</li>
					<li>Lorem ipsum dolor sit amet
					<ul>
						<li>Lorem ipsum dolor sit amet</li>
						<li>Lorem ipsum dolor sit amet</li>
						<li>Lorem ipsum dolor sit amet</li>
						<li>Lorem ipsum dolor sit amet</li>
						<li>Lorem ipsum dolor sit amet</li>
					</ul>
					</li>
					<li>Lorem ipsum dolor sit amet</li>
					<li>Lorem ipsum dolor sit amet</li>
				</ul>
				</li>
				<li>Lorem ipsum dolor sit amet</li>
				<li>Lorem ipsum dolor sit amet</li>
			</ul>
                        -->
			<!-- Unordered List 
			<h3 class="tit">Unordered List</h3>
			<ul>
				<li>Lorem ipsum dolor sit amet</li>
				<li>Lorem ipsum dolor sit amet</li>
				<li>Lorem ipsum dolor sit amet
				<ul>
					<li>Lorem ipsum dolor sit amet</li>
					<li>Lorem ipsum dolor sit amet</li>
					<li>Lorem ipsum dolor sit amet
					<ul>
						<li>Lorem ipsum dolor sit amet</li>
						<li>Lorem ipsum dolor sit amet</li>
						<li>Lorem ipsum dolor sit amet</li>
						<li>Lorem ipsum dolor sit amet</li>
						<li>Lorem ipsum dolor sit amet</li>
					</ul>
					</li>
					<li>Lorem ipsum dolor sit amet</li>
					<li>Lorem ipsum dolor sit amet</li>
				</ul>
				</li>
				<li>Lorem ipsum dolor sit amet</li>
				<li>Lorem ipsum dolor sit amet</li>
			</ul>
                        -->    
			<!-- Ordered List (OL) 
			<h3 class="tit">Ordered List</h3>
			<ol>
				<li>Lorem ipsum dolor sit amet</li>
				<li>Lorem ipsum dolor sit amet</li>
				<li>Lorem ipsum dolor sit amet
				<ol>
					<li>Lorem ipsum dolor sit amet</li>
					<li>Lorem ipsum dolor sit amet</li>
					<li>Lorem ipsum dolor sit amet
					<ol>
						<li>Lorem ipsum dolor sit amet</li>
						<li>Lorem ipsum dolor sit amet</li>
						<li>Lorem ipsum dolor sit amet</li>
						<li>Lorem ipsum dolor sit amet</li>
						<li>Lorem ipsum dolor sit amet</li>
					</ol>
					</li>
					<li>Lorem ipsum dolor sit amet</li>
					<li>Lorem ipsum dolor sit amet</li>
				</ol>
				</li>
				<li>Lorem ipsum dolor sit amet</li>
				<li>Lorem ipsum dolor sit amet</li>
			</ol>
                        -->
			<!-- Table 
			<h3 class="tit">Table <span class="tag">(table.nostyle)</span></h3>
			<table class="nostyle">
				<tr>
				    <th>Lorem ipsum</th>
				    <th>Lorem ipsum</th>
				    <th>Lorem ipsum</th>
				    <th>Lorem ipsum</th>
				    <th>Lorem ipsum</th>
				</tr>
				<tr>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				</tr>
				<tr>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				</tr>
				<tr>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				</tr>
				<tr>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				</tr>
				<tr>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				</tr>
			</table>
                        -->
			<!-- Table (TABLE)
			<h3 class="tit">Table</h3>
			<table>
				<tr>
				    <th>Lorem ipsum</th>
				    <th>Lorem ipsum</th>
				    <th>Lorem ipsum</th>
				    <th>Lorem ipsum</th>
				    <th>Lorem ipsum</th>
				</tr>
				<tr>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				</tr>
				<tr class="bg">
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				</tr>
				<tr>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				</tr>
				<tr class="bg">
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				</tr>
				<tr>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				    <td>Lorem ipsum</td>
				</tr>
			</table>
                        -->
			<!-- Form 
			<h3 class="tit">Form</h3>
			<fieldset>
				<legend>Legend</legend>
				<table class="nostyle">
					<tr>
						<td style="width:70px;">Input:</td>
						<td><input type="text" size="40" name="" class="input-text" /></td>
					</tr>
					<tr>
						<td>Input:</td>
						<td><input type="text" size="40" name="" class="input-text" disabled="disabled" /></td>
					</tr>
					<tr>
						<td class="va-top">Input:</td>
						<td><textarea cols="75" rows="7" class="input-text"></textarea></td>
					</tr>
					<tr>
						<td>Input:</td>
						<td>
							<label><input type="checkbox" /> Lorem ipsum</label> &nbsp;
							<label><input type="checkbox" /> Lorem ipsum</label> &nbsp;
							<label><input type="checkbox" /> Lorem ipsum</label>
						</td>
					</tr>
					<tr>
						<td>Input:</td>
						<td>
							<label><input type="radio" /> Lorem ipsum</label> &nbsp;
							<label><input type="radio" /> Lorem ipsum</label> &nbsp;
							<label><input type="radio" /> Lorem ipsum</label>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="t-right"><input type="submit" class="input-submit" value="Submit" /></td>
					</tr>
				</table>
			</fieldset>
                        -->
			<!-- Definition List 
			<h3 class="tit">Definition List <span class="tag">(dl.nostyle)</span></h3>
			<dl class="nostyle">
				<dt>Lorem ipsum</dt>
				<dd>Lorem ipsum dolor sit amet.</dd>
				<dt>Lorem ipsum</dt>
				<dd>Lorem ipsum dolor sit amet.</dd>
				<dt>Lorem ipsum</dt>
				<dd>Lorem ipsum dolor sit amet.</dd>
			</dl>
                        -->
			<!-- Definition List 
			<h3 class="tit">Definition List</h3>
			<dl>
				<dt>Lorem ipsum</dt>
				<dd>Lorem ipsum dolor sit amet.</dd>
				<dt>Lorem ipsum</dt>
				<dd>Lorem ipsum dolor sit amet.</dd>
				<dt>Lorem ipsum</dt>
				<dd>Lorem ipsum dolor sit amet.</dd>
			</dl>
                            -->
		</div> <!-- /content -->

	</div> <!-- /cols -->

	<hr class="noscreen" />

	<!-- Footer -->
	<div id="footer" class="box">

		<p class="f-left">&copy; 2013 <a href="#">College de Rosemont</a>, Tous les droits reservés &reg;</p>

		<!-- <p class="f-right">Templates by <a href="http://www.adminizio.com/">Adminizio</a></p>-->

	</div> <!-- /footer -->

</div> <!-- /main -->

</body>
</html>
