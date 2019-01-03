package Interface;

import java.util.HashMap;

import Kids2Kids.Store;

public class Sales {
	
	private Store store;
	
	public Sales(Store store) {
		this.store = store;
	}
	
	public void listSales() {
		
		utils.clearScreen();
		utils.printMenuTitle("List Past Sales");
		System.out.println();
		
		HashMap<Integer,Object> sales = utils.createMap(this.store.getSales());
		
		for(int i = 1; i <= sales.size(); i++) {
			System.out.println(i + " - " + sales.get(i));
		}
		
		System.out.println();
		
		utils.pressEnterToContinue();
	}
	
	public void createNewSale() {}

}
