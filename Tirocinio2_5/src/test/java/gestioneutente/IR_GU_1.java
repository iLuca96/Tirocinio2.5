package gestioneutente;

import gestioneutente.control.SignupControl;
import junit.framework.TestCase;
import org.junit.Test;

public class IR_GU_1 extends TestCase {

  @Test
  public void testIR_GU_1_2() {

    String email = "A.Rossirfefefververvezgregergjjfjrkjkkjnsfkgkgkgkfngkgwi"
        + "epcjvtdkdkdskjedejwcijwkdfcjrniucfnwsiucniaewouncier"
        + "uncferifri611974g5@studenti.unisa.itesfwergr";
    int m = email.length();

    assertEquals(152, m);
  }
  
  @Test
  public void testIR_GU_1_9() {

    SignupControl signup = new SignupControl();
    String username = "RossiM@*.";
    boolean actual = signup.validateUsername_Matricola(username);
    
    assertEquals(false, actual);
  }

  @Test
  public void testIR_GU_1_13() {

    SignupControl signup = new SignupControl();
    String matricola = "05121aab*/";
    boolean actual = signup.validateUsername_Matricola(matricola);
    
    assertEquals(false, actual);
  }
}
