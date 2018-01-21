package gestionestudente.control;

import gestionestudente.model.Studente;
import gestionestudente.model.StudenteModel;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * UploadFile � una classe che permetter� di salvare un 
 * file curriculum nel server in modo tale da farlo diventare accessibile.
 */
public class UploadFile extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static StudenteModel model;

  static {

    model = new StudenteModel();
  }
  
  public UploadFile() {
     super();
  }

  /**
    * Il metodo doPost, tenter� di inserire un curriculum 
    * come file in una cartella specifica dell'utente che fa richiesta. 
  */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
           throws ServletException, IOException {

    if (ServletFileUpload.isMultipartContent(request)) {
      try {
        Studente sessioneStudent = (Studente) request.getSession().getAttribute("student");
        
        List<FileItem> multiparts = 
            new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

        String nameFolder = null;
        String dir = null;
        String action = null;
        String filePdf = null;
        int i = 0;

        for (FileItem item : multiparts) {
          if (!item.isFormField()) {
            String name = new File(item.getName()).getName();
  
            if (sessioneStudent != null && i == 0) {
              sessioneStudent.setMatricola(sessioneStudent.getMatricola().replaceAll("^\\s+", ""));
              sessioneStudent.setMatricola(sessioneStudent.getMatricola().replaceAll("\\s+$", ""));
              nameFolder = 
                    folder(sessioneStudent.getMatricola(),sessioneStudent.getMatricola().length());
              dir = creaDir(nameFolder);
              i = 1;
            }

            if (filePdf == null) {
              filePdf = "/Tirocinio2.5/Users/Students/" + nameFolder + "/" + name;
            } else {
              filePdf = filePdf + nameFolder + "/" + name;
            }
            item.write(new File(dir + File.separator + name));
          } else {
            if ("action".equals(item.getFieldName())) {
              action = item.getString();
            }
          }
        }

        if (action.equalsIgnoreCase("insert_curriculum")) {

          Studente bean = new Studente();
          bean.setLink_curriculum(filePdf);
          bean.setMatricola(sessioneStudent.getMatricola());
          model.doModifyCurriculum(bean);

          request.getSession().setAttribute("student", 
                 model.doRetrieveByKey(sessioneStudent.getMatricola()));
          request.setAttribute("message_success", "Curriculum inserito con successo.");
        }
      } catch (Exception ex) {
        request.setAttribute("message_danger", "File upload failed due to : " + ex);
      }
    } else {
      request.setAttribute("message_danger", 
            "Sorry this servlet only handles file upload request.");
    }

    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PersonalArea.jsp");
    dispatcher.forward(request, response);
  }
  
  /**
    * Il metodo � utile per pulire un nome dagli spazi e dagli * sostituiendoli con il _.
    * @param name tipo String, variabile che in input ha un possibile nome di una cartella
    * @param n tipo int, variabile che misura la lunghezza del nome di una cartella
    * @return nameFolder tipo String, il nome di una cartella pulita da (spazi) e * con _
    * 
   */
  public String folder(String name,int n) {
    String nameFolder = "";

    for (int i = 0; i < n; i++) {
      String comparison = name.substring(i, i + 1);

      if ((comparison.equalsIgnoreCase(" ")) || (comparison.equalsIgnoreCase("*"))) {
        nameFolder = nameFolder + "_";
      } else {
        nameFolder = nameFolder + name.substring(i, i + 1);
      }
    }
    
    return nameFolder;
  }
  
  /**
   * Il metodo aggiunge alla variabile dir il nome della cartella che gli viene passato.
   * @param name_folder tipo String, variabile che riceve il nome della cartella  
   * @return Dir tipo String, variabile che contiene il path dove verr� memorizzata una cartella
   * 
   */
  private static String creaDir(String nameFolder) {
    //String Dir = "C:/Users/ciro9/eclipse-workspace/Tirocinio2.5/WebContent/Users/Students/" 
    //  + name_folder;
    String dir = "C:/apache-tomcat-8.5.11/webapps/Tirocinio2.5/Users/Students/" + nameFolder;

    new File(dir).mkdir();
    return dir;
  }
}