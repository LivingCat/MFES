package Kids2Kids;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PurchaseFromClient extends Purchase {
  private Client client;

  public void cg_init_PurchaseFromClient_1(final Client c, final VDMMap ps, final Date d) {

    client = c;
    cg_init_Purchase_1(Utils.copy(ps), d);
  }

  public PurchaseFromClient(final Client c, final VDMMap ps, final Date d) {

    cg_init_PurchaseFromClient_1(c, Utils.copy(ps), d);
  }

  public Client getClient() {

    return client;
  }

  public PurchaseFromClient() {}

  public String toString() {
	  
	return "Client: " + client + "\n" + super.toString();
  }
}
