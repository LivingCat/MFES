package Interface;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.overture.codegen.runtime.VDMMap;

import Kids2Kids.Client;
import Kids2Kids.Date;
import Kids2Kids.Product;
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
			System.out.println();
		}
		
		System.out.println();
		
		utils.pressEnterToContinue();
	}
	
	public void createNewSale() {
		
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
	
	public VDMMap getProductsWithStock() {
		VDMMap products = this.store.getStoreProducts();
		Set<Map.Entry<Product,Number>> maplets = products.entrySet();
		
		for (Iterator<Entry<Product, Number>> iterator = maplets.iterator(); iterator.hasNext();) {
			Entry<Product, Number> entry = iterator.next();
			
			if(entry.getValue().equals(0)) {
				products.remove(entry.getKey());
			}
		}
		
		return products;
	}
	
	public VDMMap getProductsToSell(VDMMap productsAvailable) {
		VDMMap products = new VDMMap();
		Products view = new Products(this.store);
		
		int userInput = 0;
		int productQtt = 0;
		Product productChosen;
		Number stockAvailable;
		
		HashMap<Integer,Object> list = new HashMap<Integer,Object>();
		
		do {
			utils.clearScreen();
			utils.printMenuTitle("Create New Sale");
			System.out.println();
			System.out.println("Product Picking:");
			list = view.printProducts(productsAvailable);
			
			if(products.size() != 0) {
				System.out.println((list.size() + 1) +  " - FINISH PICKING");
				userInput = utils.inputInt("Input choice: ", 1, list.size() + 1);
				
				if(userInput == list.size() + 1) {
					break;
				}
			} else {
				userInput = utils.inputInt("Input choice: ", 1, list.size());
			}
	
			productChosen = ((Map.Entry<Product,Number>)list.get(userInput)).getKey();
			stockAvailable = ((Map.Entry<Product,Number>)list.get(userInput)).getValue();
			productQtt = utils.inputInt("Input product quantity: ", 1, stockAvailable.intValue());
			
			products.put(productChosen, productQtt);
			productsAvailable.remove(productChosen);
		} while(productsAvailable.size() == 1 || userInput != productsAvailable.size() + 1);
		
		return products;
	}

}
