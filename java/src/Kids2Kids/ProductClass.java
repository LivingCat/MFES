package Kids2Kids;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ProductClass {
  private String name;

  public void cg_init_ProductClass_1(final String n) {

    name = n;
    return;
  }

  public ProductClass(final String n) {

    cg_init_ProductClass_1(n);
  }

  public String getName() {

    return name;
  }

  public ProductClass() {}

  public String toString() {

    return name;
  }
}
