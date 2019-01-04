package Kids2Kids;

import java.util.*;
import java.util.Map.Entry;

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
	  
	  String purchase = "Date: " + date.getDate() + "\n" +
				"Products:\n";
		
		Set<Map.Entry<Product, Number>> maplets = products.entrySet();
		
		for (Iterator iterator = maplets.iterator(); iterator.hasNext();) {
			Entry<Product, Number> entry = (Entry<Product, Number>) iterator.next();
			Product p = entry.getKey();
			Number q = entry.getValue();
			
			purchase += "\t- " + p.getName() + "\n";
			purchase += "\t  " + p.getBuyPrice() + "€ per unit, " + q + " unit(s)\n";
		}
		
		purchase += "Grand Total = " + grandTotal + "€";
		
		return purchase;
  }
}
