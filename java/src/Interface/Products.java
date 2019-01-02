package Interface;

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
		
		System.out.println(store.getProductClasses());
	}

}
