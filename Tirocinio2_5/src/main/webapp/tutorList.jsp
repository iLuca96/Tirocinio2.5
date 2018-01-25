<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendaleModel,gestionesegreteria.model.Segreteria"%>

<%

ProfessoreTutorAziendaleModel professoreModel;

professoreModel = new ProfessoreTutorAziendaleModel();

Collection<?> teachers = professoreModel.doRetrieveAllTutor("");

%>    
<html>
<head>
<title>
	Lista Tutor Aziendale
</title>

<%@ include file="fragments/head.html" %>
<%@ include file="fragments/nav.jsp" %>
<style class="cp-pen-styles">

.heading {
  text-align: center;
}
.heading h1 {
  background: -webkit-linear-gradient(#fff, #999);
  -webkit-text-fill-color: transparent;
  -webkit-background-clip: text;
  text-align: center;
  margin: 0 0 5px 0;
  font-weight: 900;
  font-size: 4rem;
  color: #fff;
}
.heading h4 {
  color: #a990cc;
  text-align: center;
  margin: 0 0 35px 0;
  font-weight: 400;
  font-size: 24px;
}

.list-group-wrapper {
  position: relative;
}

.list-group {
  overflow: auto;
  height: 50vh;
  border: 2px solid #dce4ec;
  border-radius: 5px;
}

.list-group-item {
  margin-top: 1px;
  border-left: none;
  border-right: none;
  border-top: none;
  border-bottom: 2px solid #dce4ec;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>
</head>
<body>
<div class="container">
  <fieldset>
  <legend><h2>Lista Tutor Aziendale</h2></legend>			
	<div class="list-group-wrapper">
	    <ul class="list-group">
	    <%
	  	int i = 0;
	  	if (teachers.size() > 0) {
				Iterator<?> it_teachers = teachers.iterator(); %>
	    <%                        
			 while (it_teachers.hasNext()) {
				ProfessoreTutorAziendale teacher = (ProfessoreTutorAziendale) it_teachers.next();
				if(teacher.getEmail().length()>0)
				{
					if(i%2==0)
					{
					%>
				      <li class="list-group-item">
					      <div class="row">
				  <%} %>
					        <div class="col-md-6">
					            <div class="well well-sm">
					                <div class="row">
					                    <div class="col-xs-3 col-md-3 text-center">
					                        <img src="<%=teacher.getImmagine_profilo()%>" alt="immagine del profilo" class="img-rounded img-responsive" />
					                    </div>
					                    <div class="col-xs-9 col-md-9 section-box">
					                        <h3>
					                        <p>
					                            <i class="fa fa-user"> <%=teacher.getNome()%> <%=teacher.getCognome()%> </i> <a href="ShowProfile.jsp?id=<%=teacher.getUsername()%>"> Mostra Altro</a>
					                        </p>
					                        <p>
					                            <i class="fa fa-envelope"> <%=teacher.getEmail()%> </i>
					                        </p>
					                        <p>
					                            <i class="fa fa-link"> <a href="<%=teacher.getSitoweb()%>" target="_blank"> <%=teacher.getSitoweb()%></a> </i> 
					                        </p>
					                        </h3>
					                    </div>
					                </div>
					            </div>
					        </div>
					<%
					if(i%2==1)
					{
					%>
					     </div>
				    </li>
				    <%
					}
					i++;
				}
			}
		} 
		else
		{
			i=1;
		%>
			<li class="list-group-item"> <strong> <h3> Nessun Profilo Tutor Aziendale trovato. </h3></strong> </li>
		<%}%>
	 </ul>
  </div>
  
  </fieldset>
  <strong>Vedi Anche:</strong><br>
  <a href="teacherList.jsp" style="color: black; text-decoration: none;"> 
	<i class="fa fa-address-card fa-5x"> </i> <br>
	Elenco Professori
  </a>  
</div>
</body>
</html>