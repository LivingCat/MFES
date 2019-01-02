package Kids2Kids;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Purchase {
  protected VDMMap products;
  protected Date date;
  protected Number grandTotal;

  protected void cg_init_Purchase_1(final VDMMap ps, final Date d) {

    products = Utils.copy(ps);
    date = d;
    grandTotal = calculateGrandTotal();
    return;
  }

  protected Number calculateGrandTotal() {

    Number sum = 0L;
    for (Iterator iterator_29 = MapUtil.dom(Utils.copy(products)).iterator();
        iterator_29.hasNext();
        ) {
      Product p = (Product) iterator_29.next();
      sum =
          sum.doubleValue()
              + p.getBuyPrice().doubleValue() * ((Number) Utils.get(products, p)).longValue();
    }
    return sum;
  }

  protected Purchase(final VDMMap ps, final Date d) {

    cg_init_Purchase_1(Utils.copy(ps), d);
  }

  public VDMMap getProducts() {

    return Utils.copy(products);
  }

  public Date getDate() {

    return date;
  }

  public Number getGrandTotal() {

    return grandTotal;
  }

  public Purchase() {}

  public String toString() {

    return "Purchase{"
        + "products := "
        + Utils.toString(products)
        + ", date := "
        + Utils.toString(date)
        + ", grandTotal := "
        + Utils.toString(grandTotal)
        + "}";
  }
}
