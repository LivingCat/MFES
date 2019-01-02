package Kids2Kids;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PurchaseFromSupplier extends Purchase {
  private Supplier supplier;

  public void cg_init_PurchaseFromSupplier_1(final Supplier s, final VDMMap ps, final Date d) {

    supplier = s;
    cg_init_Purchase_1(Utils.copy(ps), d);
  }

  public PurchaseFromSupplier(final Supplier s, final VDMMap ps, final Date d) {

    cg_init_PurchaseFromSupplier_1(s, Utils.copy(ps), d);
  }

  public Supplier getSupplier() {

    return supplier;
  }

  public PurchaseFromSupplier() {}

  public String toString() {

    return "PurchaseFromSupplier{" + "supplier := " + Utils.toString(supplier) + "}";
  }
}
