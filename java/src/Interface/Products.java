package Interface;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.overture.codegen.runtime.VDMMap;
import org.overture.codegen.runtime.VDMSet;

import Kids2Kids.Product;
import Kids2Kids.ProductClass;
import Kids2Kids.Store;

public class Products {
	
	private Store store;
	
	public Products(Store store) {
		this.store = store;
	}
	
	public void printProductClasses() {
		utils.clearScreen();
		utils.printMenuTitle("Product Classes");
		System.out.println();
		
		VDMSet classes = store.getProductClasses();
		
		int i = 1;
		for (Iterator<ProductClass> it = classes.iterator(); it.hasNext();) {
			ProductClass c = it.next();
			System.out.println(i + " - " + c);
		}
		
		utils.pressEnterToContinue();
	}
	
	public void printProducts() {
		utils.clearScreen();
		utils.printMenuTitle("Stock");
		System.out.println();
		
		VDMMap products = store.getStoreProducts();
		
		Set productsSet = products.entrySet();
		
		int i = 1;
		for (Iterator<Map.Entry<Product,Number>> it = productsSet.iterator(); it.hasNext();) {
			Map.Entry<Product,Number> productMaplet = it.next();
			Product p = productMaplet.getKey();
			Number q = productMaplet.getValue();
			System.out.println(i + " - " + p);
			System.out.println("\tstock: " + q);
		}
		
		utils.pressEnterToContinue();
	}

}
