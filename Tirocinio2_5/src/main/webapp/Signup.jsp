<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,gestionestudente.model.Studente"%>

<%
	Studente register = (Studente) request.getAttribute("customer");
%>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>Registrazione Utente</title>	
	
	<%@ include file="fragments/head.html" %>
	<%@ include file="fragments/nav.jsp" %>
</head>

<body>
	
	<div class="container">
	
	<% if(session.getAttribute("matricola_vuota")!=null)
	   {
	%>
		<div class="alert alert-danger">
		    <strong>Spiacenti!</strong> <%=session.getAttribute("matricola_vuota")%> 
		</div>
	
	<%  
	   }  
	
		if(session.getAttribute("email_not_valid")!=null)
		{
		%>
			<div class="alert alert-danger">
			    <strong>Spiacenti! </strong> <%=session.getAttribute("email_not_valid")%> 
			</div>
		<%
		}
		
		if(session.getAttribute("username_not_valid")!=null)
		{
		%>
			<div class="alert alert-danger">
			    <strong>Spiacenti! </strong> <%=session.getAttribute("username_not_valid")%> 
			</div>
		
		<%  
		} 
		
		if(session.getAttribute("firstname_not_valid")!=null)
		   {
		%>
			<div class="alert alert-danger">
			    <strong>Spiacenti! </strong> <%=session.getAttribute("firstname_not_valid")%> 
			</div>
		
		<%  
		   }
		if(session.getAttribute("lastname_not_valid")!=null)
		   {
		%>
			<div class="alert alert-danger">
			    <strong>Spiacenti! </strong> <%=session.getAttribute("lastname_not_valid")%> 
			</div>
		
		<%  
		   }
		
		if(session.getAttribute("matricola_not_valid")!=null)
		{
		%>
			<div class="alert alert-danger">
			    <strong>Spiacenti! </strong> <%=session.getAttribute("matricola_not_valid")%> 
			</div>
		
		<%
		}
		
		if(session.getAttribute("not_equals")!=null)
		   {
		%>
			<div class="alert alert-danger">
			    <strong>Spiacenti! </strong> <%=session.getAttribute("not_equals")%> 
			</div>
		
		<%  
		   }
		
		
	   if(session.getAttribute("register_fault")!=null)
	   {
	%>
		<div class="alert alert-danger">
		    <strong>Spiacenti!</strong> <%=session.getAttribute("register_fault")%> , già sei registrato. 
		</div>
	
	<%  
	   }  
	   else 
		   if(session.getAttribute("register_completed")!=null)
		   {
		%>
			<div class="alert alert-success">
		      <strong>Congratulazione!</strong> <%=session.getAttribute("register_completed")%> , ti sei appena registrato come <%=session.getAttribute("register_completed_as_student_tutor_teacher")%>.
		    </div>
		
		<% }  
	
	Studente sessione = (Studente) request.getSession().getAttribute("customer");
	 
	if(sessione!=null)
	   {		
		
		 if(sessione.getEmail()!=null)
		 {
			  %>  
			  
			  <div class="alert alert-success">
		        <strong>Hello!</strong> <%=sessione.getNome()%> <%=sessione.getCognome()%>, login already made.
		      </div>
			
		<% }
	   }
	 else
	  {
		 session.removeAttribute("register_completed");
		 session.removeAttribute("matricola_vuota");
		 session.removeAttribute("register_fault");
		 session.removeAttribute("email_not_valid");
		 session.removeAttribute("username_not_valid");
		 session.removeAttribute("matricola_not_valid");
		 session.removeAttribute("firstname_not_valid");
		 session.removeAttribute("lastname_not_valid");
		 session.removeAttribute("not_equals");
		 %> 
    
        
		  <h2>Registrazione Nuovo Utente</h2>
		  <div class="col-sm-3"> 
		  </div>
		  <div class="col-sm-5">
		  <form name='signup_forms' action="signup" method="post"> 
		  	<input type="hidden" name="action" value="insert">
		  
		    <div class="form-group"> 
		      <label for="first_name">Nome:</label>
			  <input name="first_name" type="text" maxlength="25" required class="form-control" placeholder="Inserisci nome">
		    </div>
		    <div class="form-group">
		      <label for="last_name">Cognome:</label>
			  <input name="last_name" type="text" maxlength="25"  required class="form-control" placeholder="Inserisci Cognome">
		    </div>
		    <div class="form-group">
		      <label for="username">Username: </label>
			  <input name="username" type="text" maxlength="32" required class="form-control" placeholder="Inserisci Username">
		    </div>
		    <div class="form-group">
		      <label for="email">Email:</label>
		      <input type="text" maxlength="64" class="form-control" required placeholder="Inserisci email" name="email">
		    </div>
		    <div class="form-group">
		      <label for="pwd">Password:</label>
		      <input type="password" maxlength="25" class="form-control" required placeholder="Inserisci password" name="psw">
		    </div>
		    <div class="form-group">
		      <label for="matricola">Matricola:</label>
		      <input type="text" maxlength="10" class="form-control" placeholder="Inserisci matricola" name="matricola">
		    </div>
		    <button type="submit" value="Send" class="btn btn-success">Registrati </button> 
		    <button type="reset" value="Reset" class="btn btn-danger"> Svuota Campi </button> 
		  </form>
		  </div>
		</div>
		   
	<% }
	%>
</body>
</html>

