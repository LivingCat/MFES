package Kids2Kids;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Sale {
  private Client client;
  private VDMMap products;
  private Date date;
  private Number grandTotal;

  public void cg_init_Sale_1(final Client c, final VDMMap ps, final Date d) {

    client = c;
    products = Utils.copy(ps);
    date = d;
    grandTotal = calculateGrandTotal();
    return;
  }

  private Number calculateGrandTotal() {

    Number sum = 0L;
    for (Iterator iterator_30 = MapUtil.dom(Utils.copy(products)).iterator();
        iterator_30.hasNext();
        ) {
      Product p = (Product) iterator_30.next();
      sum =
          sum.doubleValue()
              + p.getSellPrice().doubleValue() * ((Number) Utils.get(products, p)).longValue();
    }
    return sum;
  }

  public Sale(final Client c, final VDMMap ps, final Date d) {

    cg_init_Sale_1(c, Utils.copy(ps), d);
  }

  public Client getClient() {

    return client;
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

  public Sale() {}

  public String toString() {

    return "Sale{"
        + "client := "
        + Utils.toString(client)
        + ", products := "
        + Utils.toString(products)
        + ", date := "
        + Utils.toString(date)
        + ", grandTotal := "
        + Utils.toString(grandTotal)
        + "}";
  }
}
