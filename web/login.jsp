<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
<link href="./style/style.css" rel="stylesheet" type="text/css" /> 
</head>
     
<body>
   
   <div id="all">
   		<div id="form">
   			<div id="entete">
           			Se connecter 
            </div>
            <hr />
          <div id="corps">
		  <form method="POST" action="ctrl.do">
             <table >
             	<tr>
                	<td ><label>Usager:</label></td>
                </tr>
                <tr>
                	<td><input type="text" name="username" class="inputForm" /></td>
                </tr>
                <tr>
               		 <td><label>Mot de passe:</label></td>
                </tr>
                <tr>
                    <td><input type="password" name="password" class="inputForm" /></td>
                </tr>
                 <tr>
                    <td>
                    	<input type="checkbox" name="checkSession" id="check" /><label>Se souvenir de moi</label>
                    	<input type="submit" name="bOk" value="Connexion" />
                    </td>
                </tr>
                 <tr><td>&nbsp; <% if(request.getAttribute("message")!=null){
                             out.println((String)request.getAttribute("message"));
                            }
                         %>
                     </td></tr>
             </table>
             <input type="hidden" name="action" value="login" />
          </form>
          </div>
          <div id="pied">
           <hr />
           <table><tr> 
           <td ><a href="./formulaireMembreSysteme.jsp">Creer une compte</a></td><td  ><a href="./formulaireRecuperePass.jsp" id="aMotDePasse"  >Avez-vous oubliez votre mot de passe?</a></td>
               </tr></table>
          </div>
        </div>
   </div>
</body>
</html>
