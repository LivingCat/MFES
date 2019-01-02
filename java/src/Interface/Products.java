package Interface;

import java.util.Iterator;

import org.overture.codegen.runtime.VDMSet;

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
		for (Iterator it = classes.iterator(); it.hasNext();) {
			ProductClass c = (ProductClass) it.next();
			System.out.println(i + " - " + c);
		}
	}

}
