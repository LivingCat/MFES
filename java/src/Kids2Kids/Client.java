package Kids2Kids;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Client {
  private String name;

  public void cg_init_Client_1(final String n) {

    name = n;
    return;
  }

  public Client(final String n) {

    cg_init_Client_1(n);
  }

  public String getName() {

    return name;
  }

  public Client() {}

  public String toString() {
	  
	return name;
  }
}
