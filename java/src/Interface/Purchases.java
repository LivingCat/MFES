package Interface;

import java.util.HashMap;

import org.overture.codegen.runtime.VDMMap;

import Kids2Kids.Client;
import Kids2Kids.Date;
import Kids2Kids.Store;

public class Purchases {
	
private Store store;
	
	public Purchases(Store store) {
		this.store = store;
	}
	
	public void listPurchases() {
		
		utils.clearScreen();
		utils.printMenuTitle("List Past Purchases");
		System.out.println();
		
		HashMap<Integer,Object> purchases = utils.createMap(this.store.getPurchases());
		
		for(int i = 1; i <= purchases.size(); i++) {
			System.out.println(i + " - " + purchases.get(i));
			System.out.println();
		}
		
		System.out.println();
		
		utils.pressEnterToContinue();
	}
	
	public void VDMMap getProductsCheapEnoughToBuy() {
		
	}
	
	public void createNewClientPurchase() {
		utils.clearScreen();
		utils.printMenuTitle("Create New Sale");
		System.out.println();
		
		VDMMap productsAvailable = getProductsWithStock();
		
		if(productsAvailable.size() == 0) {
			System.out.println("Our store is empty! Please come back later...");
			utils.pressEnterToContinue();
			return;
		}
		
		String clientName;
		do {
			clientName = utils.inputString("Input client name: ");
			
			if(clientName.length() == 0) {
				System.out.println("Invalid client name! A client can't have an empty name.");
			}
			
		} while (clientName.length() == 0);
		
		Client client = new Client(clientName);
		Date date = utils.today();
		VDMMap products = getProductsToSell(productsAvailable);
		
		this.store.sell(products, client, date);
		
		System.out.println("Sale successfully created!");
		utils.pressEnterToContinue();
	}
	
	public void createNewSupplierPurchase() {}

}
