package Kids2Kids;

import java.util.*;
import java.util.Map.Entry;

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
	  
	String sale = "Client: " + client + "\n" +
			"Date: " + date.getDate() + "\n" +
			"Products:\n";
	
	Set<Map.Entry<Product, Number>> maplets = products.entrySet();
	
	for (Iterator iterator = maplets.iterator(); iterator.hasNext();) {
		Entry<Product, Number> entry = (Entry<Product, Number>) iterator.next();
		Product p = entry.getKey();
		Number q = entry.getValue();
		
		sale += "\t- " + p.getName() + "\n";
		sale += "\t  " + p.getSellPrice() + "€ per unit, " + q + " unit(s)\n";
	}
	
	sale += "Grand Total = " + grandTotal + "€";
	
	return sale;
  }
}
