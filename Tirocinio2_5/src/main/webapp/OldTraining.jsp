<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestionestudente.model.Studente"%>
    
<html>
<head>
<title>
	Tiroini Precedenti
</title>

<%@ include file="fragments/head.html" %>
<%@ include file="fragments/nav.jsp" %>
</head>
<body>

<%  
	if(sessione_student!=null)		
	{	 if(sessione_student.getEmail().length()>0)
		 { 
		  %>  
			<div class="container">
			<h2>Inserimento Tirocini Precedenti</h2>
			<div class="col-sm-3"> 
			</div>
			<div class="col-sm-5"> 
				<form action="oldTraining" method="post" enctype="multipart/form-data"> 
				  	<input type="hidden" name="action" value="insert_old_training">
					  
				    <div class="form-group"> 
				      <label for="company">Company:</label>
					  <input name="company" type="text" maxlength="64" required class="form-control" placeholder="Inserisci Company">
				    </div>
				    <div class="form-group">
				      <label for="first_last_name">Nome Cognome Tutor Aziendale:</label>
					  <input name="first_last_name" type="text" maxlength="128"  required class="form-control" placeholder="Inserisci Nome Cognome">
				    </div>
				    <div class="form-group">
				      <label for="job">Durata Lavoro:</label>
				      <input type="text" maxlength="100" class="form-control" required placeholder="Inserisci durata del lavoro (ore)" name="job">
				    </div>
				    <div class="form-group">
				      <label for="document">Carica Documento:</label>
				      <input type="file" required accept="application/pdf" name="document" style="margin-bottom: 0.5em;">
				    </div>
				    <div class="form-group">
				      <label for="mansioni">Compiti e mansioni svolte:</label>
				      <textarea maxlength="150" rows="7" cols="25" class="form-control" required placeholder="Inserisci matricola" name="mansioni"></textarea>
				    </div>
				    <button type="submit" value="Send" class="btn btn-success">Invia </button> 
				    <button type="reset" value="Reset" class="btn btn-danger"> Svuota Campi </button> 
				</form>
			</div>
			
			</div>
<% 		}
   }
   else
   {%>
		<script>  window.location.href = "index.jsp"; </script> 
 <%}%>
	 	
</body>
</html>