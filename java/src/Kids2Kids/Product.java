package Kids2Kids;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Product {
  private ProductClass productClass;
  private String name;
  private Number buyPrice;
  private Number sellPrice;

  public void cg_init_Product_1(
      final ProductClass pClass, final String n, final Number bPrice, final Number sPrice) {

    productClass = pClass;
    name = n;
    buyPrice = bPrice;
    sellPrice = sPrice;
    return;
  }

  public Product(
      final ProductClass pClass, final String n, final Number bPrice, final Number sPrice) {

    cg_init_Product_1(pClass, n, bPrice, sPrice);
  }

  public Number getBuyPrice() {

    return buyPrice;
  }

  public Number getSellPrice() {

    return sellPrice;
  }

  public String getName() {

    return name;
  }

  public ProductClass cg_getClass() {

    return productClass;
  }

  public Product() {}

  public String toString() {
	  
	return name + "\n" + 
			"\tclass: " + productClass + "\n" + 
			"\tbuy price: " + buyPrice + "\n" + 
			"\tsell price: " + sellPrice;
  }
}
