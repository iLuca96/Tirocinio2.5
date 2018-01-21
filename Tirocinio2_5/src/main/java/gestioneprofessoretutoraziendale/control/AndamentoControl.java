package gestioneprofessoretutoraziendale.control;

import gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale;
import gestioneutente.model.Andamento;
import gestioneutente.model.AndamentoModel;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * AndamentoControl è una classe che si occupa di
 * modificare/inserire la data e l'ora di un lavoro di uno studente.
 *
 */
public class AndamentoControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  static ProfessoreTutorAziendale sessione_teacher;
  static ProfessoreTutorAziendale sessione_tutor;
  
  static String return_path = "/index.jsp";

  public AndamentoControl() {
   super();
  }

  /**
  * Il metodo doGet cerca di modificare/inserire la data e l'ora di un lavoro di uno studente.
  */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String action = request.getParameter("action");

    AndamentoModel andamentoModel = null;
    
    try {
      if (action != null) {

        if (action.equalsIgnoreCase("insert_time_work")) {
          sessione_teacher = (ProfessoreTutorAziendale)
              request.getSession().getAttribute("teacher");
          sessione_tutor = (ProfessoreTutorAziendale)
                 request.getSession().getAttribute("tutor");

          boolean control = false;

          if (sessione_teacher != null) {
            if (sessione_teacher.getEmail().length() > 0) {
              control = true;
            }
          }

          if (sessione_tutor != null) {
            if (sessione_tutor.getEmail().length() > 0) {
              control = true;
            }
          }

          if (control) {
            String id = request.getParameter("id");
            int idInt = Integer.parseInt(id);

            String data = request.getParameter("data");
            String oraInizio = request.getParameter("ora_inizio");
            String oraFine = request.getParameter("ora_fine");

            if (id != null) {
              andamentoModel = new AndamentoModel();

              Andamento bean = new Andamento();
              bean.setDataT(data);
              bean.setOra_inizio(oraInizio);
              bean.setOra_fine(oraFine);
              bean.setTirocinioId(idInt);
              andamentoModel.doSave(bean);

              request.setAttribute("message_success_training", "Ore di lavoro Aggiunte.");

              return_path = "/TrendTraining.jsp";
            }
          }
        } else if (action.equalsIgnoreCase("modify_time_work")) {
          sessione_teacher = (ProfessoreTutorAziendale)
              request.getSession().getAttribute("teacher");
          sessione_tutor = (ProfessoreTutorAziendale) 
              request.getSession().getAttribute("tutor");

          boolean control = false;

          if (sessione_teacher != null) {
            if (sessione_teacher.getEmail().length() > 0) {
              control = true;
            }
          }

          if (sessione_tutor != null) {
            if (sessione_tutor.getEmail().length() > 0) {
              control = true;
            }
          }

          if (control) {
            String id = request.getParameter("id");
            int idInt = Integer.parseInt(id);
            String data = request.getParameter("data");
            String oraInizio = request.getParameter("ora_inizio");
            String oraFine = request.getParameter("ora_fine");
             
            if (id != null) {
              andamentoModel = new AndamentoModel();
              Andamento bean = new Andamento();
              bean.setId(idInt);
              bean.setDataT(data);
              bean.setOra_inizio(oraInizio);
              bean.setOra_fine(oraFine);
              bean.setTirocinioId(idInt);
              andamentoModel.doModify(bean);

              request.setAttribute("message_success_training", "Ore di lavoro Modificate.");

              return_path = "/ModifyTimeTrend.jsp";
            }
          }
        }
      }
    } catch (SQLException e) {
      System.out.println("Error:" + e.getMessage());
    }

    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(return_path);
    dispatcher.forward(request, response);
  }

  /**
  * Il metodo riceve una richiesta dal client e 
  * richiama il medoto doGet(request, response) per svolgere la procedura aspettata.
  */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}