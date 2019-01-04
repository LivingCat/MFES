package Interface;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.overture.codegen.runtime.VDMMap;

import Kids2Kids.Client;
import Kids2Kids.Date;
import Kids2Kids.Product;
import Kids2Kids.Store;
import Kids2Kids.Supplier;

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
	
	public VDMMap getProductsCheapEnoughToBuy() {
		VDMMap products = this.store.getStoreProducts();
		Set<Map.Entry<Product,Number>> maplets = products.entrySet();
		
		for (Iterator<Entry<Product, Number>> iterator = maplets.iterator(); iterator.hasNext();) {
			Entry<Product, Number> entry = iterator.next();
			Product p = entry.getKey();
			
			if(p.getBuyPrice().doubleValue() > this.store.getCash().doubleValue()) {
				iterator.remove();
			}
		}
		
		return products;
	}
	
	public void createNewClientPurchase() {
		utils.clearScreen();
		utils.printMenuTitle("Create New Purchase");
		System.out.println();
		
		VDMMap productsAvailable = this.store.getStoreProducts();
		if(productsAvailable.size() == 0) {
			System.out.println("Our store is empty! Please come back later...");
			utils.pressEnterToContinue();
			return;
		}
		
		productsAvailable = getProductsCheapEnoughToBuy();
		
		if(productsAvailable.size() == 0) {
			System.out.println("You don't have enough cash money to buy any of the products... Please sell some stuff and come back later!");
			utils.pressEnterToContinue();
			return;
		}
		
		productsAvailable = this.store.getStoreProducts();
		
		String clientName;
		do {
			clientName = utils.inputString("Input client name: ");
			
			if(clientName.length() == 0) {
				System.out.println("Invalid client name! A client can't have an empty name.");
			}
			
		} while (clientName.length() == 0);
		
		Client client = new Client(clientName);
		Date date = utils.today();
		VDMMap products = getProductsToBuy(productsAvailable);
		
		this.store.buy(products, client, date);
		
		System.out.println("Purchase successfully created!");
		utils.pressEnterToContinue();
	}
	
	public VDMMap getProductsToBuy(VDMMap productsAvailable) {
		VDMMap products = new VDMMap();
		Products view = new Products(this.store);
		double cashAvailable = this.store.getCash().doubleValue();
		
		int userInput = 0;
		int productQtt = 0;
		Product productChosen;
		
		HashMap<Integer,Object> list = new HashMap<Integer,Object>();
		
		do {
			utils.clearScreen();
			utils.printMenuTitle("Create New Purchase");
			System.out.println();
			System.out.println("Cash available = " + cashAvailable + "€");
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
			productQtt = utils.inputInt("Input product quantity: ", 1, Integer.MAX_VALUE);
			
			if(utils.round(productQtt*productChosen.getBuyPrice().doubleValue(), 2) > cashAvailable) {
				System.out.println("You don't have enough money for that! Please pick another product or less quantity.");
				utils.pressEnterToContinue();
				continue;
			}
			
			products.put(productChosen, productQtt);
			productsAvailable.remove(productChosen);
			cashAvailable -= utils.round(productQtt*productChosen.getBuyPrice().doubleValue(), 2);
		} while(productsAvailable.size() == 1 || userInput != productsAvailable.size() + 1);
		
		return products;
	}
	
	public void createNewSupplierPurchase() {
		utils.clearScreen();
		utils.printMenuTitle("Create New Purchase");
		System.out.println();
		
		VDMMap productsAvailable = this.store.getStoreProducts();
		if(productsAvailable.size() == 0) {
			System.out.println("Our store is empty! Please come back later...");
			utils.pressEnterToContinue();
			return;
		}
		
		productsAvailable = getProductsCheapEnoughToBuy();
		
		if(productsAvailable.size() == 0) {
			System.out.println("You don't have enough cash money to buy any of the products... Please sell some stuff and come back later!");
			utils.pressEnterToContinue();
			return;
		}
		
		productsAvailable = this.store.getStoreProducts();
		
		String supplierName;
		do {
			supplierName = utils.inputString("Input supplier name: ");
			
			if(supplierName.length() == 0) {
				System.out.println("Invalid supplier name! A supplier can't have an empty name.");
			}
			
		} while (supplierName.length() == 0);
		
		Supplier supplier = new Supplier(supplierName);
		Date date = utils.today();
		VDMMap products = getProductsToBuy(productsAvailable);
		
		this.store.buy(products, supplier, date);
		
		System.out.println("Purchase successfully created!");
		utils.pressEnterToContinue();
	}

}
