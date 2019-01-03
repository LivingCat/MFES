package Interface;

import java.util.HashMap;
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
		
		printProducts(this.store.getStoreProducts());
		
		utils.pressEnterToContinue();
	}
	
	public HashMap<Integer,Object> printProducts(VDMMap storeProducts) {
		HashMap<Integer,Object> products = utils.createMap(storeProducts);
		
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
			
			if(className.length() == 0) {
				System.out.println("Invalid class name! A class can't have an empty name.");
			} else if(nameAlreadyExists) {
				System.out.println("Invalid class name! Another class with the same name already exists.");
			}
			
		} while (className.length() == 0 || nameAlreadyExists);
		
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
	
	public void registerNewProduct() {
		utils.clearScreen();
		utils.printMenuTitle("Register New Product");
		System.out.println();
		
		if(this.store.getNumberOfProductClasses().equals(0)) {
			System.out.println("No classes available! Try adding one first?");
			utils.pressEnterToContinue();
			return;
		}
		
		System.out.println("Choose product class: ");
		HashMap<Integer,Object> classes = this.printProductClasses();
		
		int classI = utils.inputInt("Write your choice: ", 1, classes.size());
		ProductClass productClass = (ProductClass)classes.get(classI);
		
		System.out.println();
		
		String productName = "";
		boolean nameAlreadyExists = false;
		do {
			productName = utils.inputString("New product name: ");
			nameAlreadyExists = productNameAlreadyExists(productName);
			
			if(productName.length() == 0) {
				System.out.println("Invalid product name! A product can't have an empty name.");
			} else if(nameAlreadyExists) {
				System.out.println("Invalid product name! Another product with the same name already exists.");
			}
		} while (productName.length() == 0 || nameAlreadyExists);
		
		System.out.println();
		
		double buyPrice = utils.inputDouble("Input buying price: ", 0, Double.MAX_VALUE);
		System.out.println();
		double sellPrice = utils.inputDouble("Input selling price: ", 0, Double.MAX_VALUE);
		System.out.println();
		
		int quantity = utils.inputInt("Input initial stock: ", 0, Integer.MAX_VALUE);
		
		Product product = new Product(productClass,productName,buyPrice,sellPrice);
		this.store.addProduct(product,quantity);
		
		System.out.println("Registered!");
		utils.pressEnterToContinue();
	}
	
	public boolean productNameAlreadyExists(String name) {
		Set<Product> products = this.store.getStoreProducts().keySet();
		ProductClass c = new ProductClass("placeholder");
		Product p = new Product(c,name,0,0);
		return products.contains(p);
	}
	
	public void deleteProduct() {
		utils.clearScreen();
		utils.printMenuTitle("Delete Product");
		System.out.println();
		
		int numberOfProducts = (int)this.store.getNumberOfProducts();
		
		if(numberOfProducts == 0) {
			System.out.println("No products available! Try adding one first?");
			utils.pressEnterToContinue();
			return;
		}
		
		HashMap<Integer,Object> list = printProducts(this.store.getStoreProducts());
		
		int input = utils.inputInt("Choose the product: ", 1, numberOfProducts);
		
		this.store.removeProduct(((Map.Entry<Product, Number>)list.get(input)).getKey().getName());
		
		System.out.println("Deleted!");
		utils.pressEnterToContinue();
	}
	
	public void addStock() {
		utils.clearScreen();
		utils.printMenuTitle("Add Stock");
		System.out.println();
		
		int numberOfProducts = (int)this.store.getNumberOfProducts();
		
		if(numberOfProducts == 0) {
			System.out.println("No products available! Try adding one first?");
			utils.pressEnterToContinue();
			return;
		}
		
		HashMap<Integer,Object> list = printProducts(this.store.getStoreProducts());
		
		int input = utils.inputInt("Choose the product: ", 1, numberOfProducts);
		Product product = ((Map.Entry<Product, Number>)list.get(input)).getKey();
		
		System.out.println();
		
		input = utils.inputInt("How much stock to add: ", 1, Integer.MAX_VALUE);
		Number qtt = (Number)input;
		
		this.store.addStock(product, qtt);
		
		System.out.println("Added stock to product " + product.getName() + "!");
		utils.pressEnterToContinue();
	}
}
