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

  @Override
  public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
  }

  @Override
  public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Product other = (Product) obj;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
  }

  public String toString() {  
	return name + "\n" + 
			"\tclass: " + productClass + "\n" + 
			"\tbuy price: " + buyPrice + "\n" + 
			"\tsell price: " + sellPrice;
  }
}
