package gestioneprofessoretutoraziendale.control;

import gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale;
import gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendaleModel;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * La Classe ProfileControl � una classe contenente 4 metodi,
 *  essa svolge come compito principale di inserire/modificare 
 *  un profilo personale di Professore/Tutor Aziendale.
 */
public class ProfileControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  static ProfessoreTutorAziendaleModel Tutormodel;

  static {

    Tutormodel = new ProfessoreTutorAziendaleModel();

  }
  
  static String return_path = "/CreateProfile.jsp";

  public ProfileControl() {
    super();
  }
  
  /**
  * Il metodo doPost, tenter� di inserire/modificare un profilo 
  * personale di Professore/Tutor Aziendale. 
  */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String action = null;
    if (ServletFileUpload.isMultipartContent(request)) {
      try {
        List<FileItem> multiparts = new 
            ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

        String nameFolder = null;
        String dir = null;
        String imageProfile = null;
        String nome = null;
        String cognome = null;
        String company = null;
        String indirizzo = null;
        String telefono = null;
        String fax = null;
        String email = null;
        String luogo = null;
        String sitoweb = null;
        String chisono = null;

        int i = 0;

        ProfessoreTutorAziendale sessioneTeacher = (ProfessoreTutorAziendale)
            request.getSession().getAttribute("teacher");
        ProfessoreTutorAziendale sessioneTutor = (ProfessoreTutorAziendale) 
            request.getSession().getAttribute("tutor");

        for (FileItem item : multiparts) {
          if (!item.isFormField()) {
            String name = new File(item.getName()).getName();

            if (sessioneTeacher != null && i == 0) {
              if (sessioneTeacher.getEmail().length() > 0) {
                sessioneTeacher.setUsername(sessioneTeacher.getUsername()
                    .replaceAll("^\\s+", "")); //toglie lo spazio all'inizio
                sessioneTeacher.setUsername(sessioneTeacher.getUsername()
                    .replaceAll("\\s+$", "")); //toglie lo spazio alla fine
                nameFolder = folder(sessioneTeacher.getUsername(),
                    sessioneTeacher.getUsername().length());
                dir = creaDir(nameFolder);
                i = 1;
              }
            }
            if (sessioneTutor != null && i == 0) {
              if (sessioneTutor.getEmail().length() > 0) {
                sessioneTutor.setUsername(sessioneTutor.getUsername()
                    .replaceAll("^\\s+", "")); //toglie lo spazio all'inizio
                sessioneTutor.setUsername(sessioneTutor.getUsername()
                    .replaceAll("\\s+$", "")); //toglie lo spazio alla fine
                nameFolder = folder(sessioneTutor.getUsername(),
                    sessioneTutor.getUsername().length());
                dir = creaDir(nameFolder);
                i = 1;
              }
            }

            if (imageProfile == null) {
              imageProfile = "/Tirocinio2.5/Users/TeacherTutor/" + nameFolder + "/" + name;
            } else {
              imageProfile = imageProfile + nameFolder + "/" + name;
            }

            item.write(new File(dir + File.separator + name));
          } else {
            if ("action".equals(item.getFieldName())) {
              action = item.getString();
            }

            if ("first_name".equals(item.getFieldName())) {
              nome = item.getString();
            }

            if ("last_name".equals(item.getFieldName())) {
              cognome = item.getString();
            }

            if ("company".equals(item.getFieldName())) {
              company = item.getString();
            }

            if ("address".equals(item.getFieldName())) {
              indirizzo = item.getString();
            }

            if ("phone".equals(item.getFieldName())) {
              telefono = item.getString();
            }

            if ("fax".equals(item.getFieldName())) {
              fax = item.getString();
            }

            if ("email".equals(item.getFieldName())) {
              email = item.getString();
            }

            if ("city".equals(item.getFieldName())) {
              luogo = item.getString();
            }

            if ("website".equals(item.getFieldName())) {
              sitoweb = item.getString();
            }

            if ("whoiam".equals(item.getFieldName())) {
              chisono = item.getString();
            }
          }
        }

        if (action.equalsIgnoreCase("createProfile") || action.equalsIgnoreCase("EditProfile")) {

          if (validateEmail(email)) {
            if (validateTelFax(fax)) {
              if (validateTelFax(telefono)) {
                ProfessoreTutorAziendale bean = new ProfessoreTutorAziendale();
                bean.setNome(nome);
                bean.setCognome(cognome);
                if (sessioneTeacher != null) {
                  bean.setTipo("Professore");
                  bean.setUsername(sessioneTeacher.getUsername());
                }
            
                if (sessioneTutor != null) {
                  bean.setTipo("Tutor Aziendale");
                  bean.setUsername(sessioneTutor.getUsername());
                }
                bean.setCompany(company);
                bean.setIndirizzo(indirizzo);
                bean.setTelefono(telefono);
                bean.setFax(fax);
                bean.setEmail(email);
                bean.setCitta(luogo);
                bean.setSitoweb(sitoweb);
                bean.setChisono(chisono);
                bean.setImmagine_profilo(imageProfile);
                Tutormodel.doModifyProfile(bean);

                if (action.equalsIgnoreCase("EditProfile")) {
                  request.setAttribute("message_success_profile", ""
                      + "Profilo Modificato con successo.");
                  return_path = "/EditProfile.jsp";
                } else {
                  request.setAttribute("message_success_profile", "Profilo Creato con successo.");
                }

                if (sessioneTeacher != null) {
                  request.getSession().setAttribute("teacher", Tutormodel
                      .doRetrieveByKey(sessioneTeacher.getUsername()));
                }
                if (sessioneTutor != null) {
                  request.getSession().setAttribute("tutor", Tutormodel
                      .doRetrieveByKey(sessioneTutor.getUsername()));
                }
              } else {
                request.setAttribute("telefono_not_valid_profile", ""
                    + "Numero di telefono inserito non valido.");
                if (action.equalsIgnoreCase("EditProfile")) {
                  return_path = "/EditProfile.jsp";
                }
              }
            } else {
              request.setAttribute("fax_not_valid_profile", ""
                  + "Numero del fax inserito non valido.");
              if (action.equalsIgnoreCase("EditProfile")) {
                return_path = "/EditProfile.jsp";
              }
            } 
          } else {
            request.setAttribute("email_not_valid_profile", ""
                + "Email inserita non valida.");
            if (action.equalsIgnoreCase("EditProfile")) {
              return_path = "/EditProfile.jsp";
            }
          }
        }
      } catch (Exception ex) {
        request.setAttribute("message_danger_profile", "File upload failed due to : " + ex);

        if (action.equalsIgnoreCase("EditProfile")) {
          return_path = "/EditProfile.jsp";
        }
      }
    } else {
      request.setAttribute("message_danger", 
          "Sorry this servlet only handles file upload request.");
    }

    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(return_path);

    dispatcher.forward(request, response);
  }
  
  /**
   * Il metodo � utile per pulire un nome dagli spazi e dagli * sostituiendoli con il _.
   * @param name tipo String, variabile che in input ha un possibile nome di una cartella
   * @param n tipo int, variabile che contiene la lunghezza del nome di una cartella
   * @return name_folder tipo String, variabile che restituisce il nome di una cartella 
   *     pulita da (spazi) e * con _
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
   */
  
  private static String creaDir(String nameFolder) {
    //String Dir = "C:/Users/ciro9/eclipse-workspace/
    //Tirocinio2.5/WebContent/Users/TeacherTutor/" + name_folder;
    String dir = "C:/apache-tomcat-9.0.0.M17/webapps/Tirocinio2.5/Users/TeacherTutor/" + nameFolder;

    new File(dir).mkdir();
    return dir;
  }
  
  /**
  * Il metodo confronta l'email passata con una espressione regolare,
  *  per verificare se la variabile passata � una email valida.
  * @param email tipo Boolean, Variabile che viene cofrontata 
  *     con le espressioni regolari per verificare se � una email valida
  * @return true/false valore boolean che se � false allora il 
  *     parametro passato non � una email valida, true altrimenti.
 */
  public boolean validateEmail(String email) {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
    Matcher matcher = pattern.matcher(email);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
  * Il metodo confronta il numero di telefono e il fax passati con una espressione regolare,
  *  per verificare se la variabile passata � valida.
  * @param numero tipo String, Variabile che viene cofrontata 
  *     con le espressioni regolari per verificare se � un numero valido
  * @return true/false valore boolean che se � false allora il 
  *     parametro passato non � un numero valido, true altrimenti.
 */
  public boolean validateTelFax(String numero) {
    Pattern pattern = Pattern.compile("(\\((00|\\+)39\\)|(00|\\+)39)?"
        + "(38[890]|34[7-90]|36[680]|33[3-90]|32[89])\\d{7}$");
    Matcher matcher = pattern.matcher(numero);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }
}