package gestioneutente;

import junit.framework.TestCase;
import org.junit.Test;

public class IR_GU_6 extends TestCase {

  @Test
  public void testIR_GU_6_4() {

    String nome = "Marioerfegerodcodoweocmpifpooedsfcrrmckslcmoewo";
    int m = nome.length();
    
    assertEquals(47, m);
  }
  
  @Test
  public void testIR_GU_6_10() {

    String password = "Pascdhdfskfnswencscjseojsefesohdsdw12";
    int m = password.length();
    
    assertEquals(37, m);
  }

  @Test
  public void testIR_GU_6_12() {

    String matricola = "05121239195265";
    int m = matricola.length();
    
    assertEquals(14, m);
  }
}
