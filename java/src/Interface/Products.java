package Interface;

import java.util.HashMap;
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
	
	public void listProductClasses() {
		utils.clearScreen();
		utils.printMenuTitle("Product Classes");
		System.out.println();
		
		printProductClasses();
		
		utils.pressEnterToContinue();
	}
	
	public HashMap<Integer,Object> printProductClasses() {
		
		HashMap<Integer,Object> classes = utils.createMap(this.store.getProductClasses());
		
		for(int i = 1; i <= classes.size(); i++) {
			System.out.println(i + " - " + classes.get(i));
		}
		
		return classes;
	}
	
	public void listProducts() {
		utils.clearScreen();
		utils.printMenuTitle("Stock");
		System.out.println();
		
		printProducts();
		
		utils.pressEnterToContinue();
	}
	
	public HashMap<Integer,Object> printProducts() {
		HashMap<Integer,Object> products = utils.createMap(store.getStoreProducts());
		
		for(int i = 1; i <= products.size(); i++) {
			Map.Entry<Product,Number> productMaplet = (Map.Entry<Product,Number>)products.get(i);
			Product p = productMaplet.getKey();
			Number q = productMaplet.getValue();
			System.out.println(i + " - " + p);
			System.out.println("\tstock: " + q);
		}
		
		return products;
	}
	
	public void registerNewClass() {
		utils.clearScreen();
		utils.printMenuTitle("Register New Class");
		System.out.println();
		
		String className;
		boolean nameAlreadyExists = false;
		do {
			className = utils.inputString("New class name: ");
			nameAlreadyExists = classNameAlreadyExists(className);
			
			if(nameAlreadyExists) {
				System.out.println("Invalid class name! Another class with the same name already exists.");
			}
		} while (nameAlreadyExists);
		
		ProductClass newClass = new ProductClass(className);
		this.store.addProductClass(newClass);
		
		System.out.println("Registered!");
		utils.pressEnterToContinue();
	}
	
	public boolean classNameAlreadyExists(String name) {
		VDMSet classes = this.store.getProductClasses();
		ProductClass c = new ProductClass(name);
		return classes.contains(c);
	}
	
	public void deleteClass() {
		utils.clearScreen();
		utils.printMenuTitle("Delete Class");
		System.out.println();
		
		int numberOfClasses = (int)this.store.getNumberOfProductClasses();
		
		if(numberOfClasses == 0) {
			System.out.println("No classes available! Try adding one first?");
			utils.pressEnterToContinue();
			return;
		}
		
		HashMap<Integer,Object> list = printProductClasses();
		
		int input = utils.inputInt("Choose the class: ", 1, numberOfClasses);
		
		this.store.removeProductClass(((ProductClass)list.get(input)).getName());
		
		System.out.println("Deleted!");
		utils.pressEnterToContinue();
	}

}
