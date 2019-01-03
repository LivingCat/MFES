package Interface;

import Kids2Kids.Store;

public class MainMenu {

	public static void main(String[] args) { 
		mainMenu();
	}
	
	public static void printMainTitle() {
		System.out.println("--------------------------------------------------------------");
		System.out.println(",--. ,--.,--.   ,--.        ,---. ,--. ,--.,--.   ,--.        ");
		System.out.println("|  .'   /`--' ,-|  | ,---. '.-.  \\|  .'   /`--' ,-|  | ,---.  ");
		System.out.println("|  .   ' ,--.' .-. |(  .-'  .-' .'|  .   ' ,--.' .-. |(  .-'  ");
		System.out.println("|  |\\   \\|  |\\ `-' |.-'  `)/   '-.|  |\\   \\|  |\\ `-' |.-'  `) ");
		System.out.println("`--' '--'`--' `---' `----' '-----'`--' '--'`--' `---' `----'  ");
		System.out.println("--------------------------------------------------------------"); 
	}
	
	public static void printMainMenu() {
		System.out.println("1. Open demo store");
		System.out.println("2. Create new store");
		System.out.println("3. Exit");
	}
	
	public static void mainMenu() {
		
		while(true) {
			utils.clearScreen();
			printMainTitle();
			System.out.println();
			printMainMenu();
			int choice = utils.inputInt("Please write your choice: ",1,3);
			
			switch(choice) {
			case 1: 
				demoStore();
				break;
			case 2:
				newStore();
				break;
			case 3:
				return;
			}
		}
	}
	
	public static void demoStore() {
		Store store = new Store("Kids2Kids","PT",1000);
		store.seed();
		StoreMenu menu = new StoreMenu(store);
		menu.mainMenu();
	}
	
	public static void newStore() {
		utils.clearScreen();
		utils.printMenuTitle("Create new Store");
		System.out.println();
		
		String storeName = "";
		do {
			storeName = utils.inputString("Input store name: ");
			
			if(storeName.length() == 0) {
				System.out.println("Invalid store name! A store can't have an empty name.");
			}
		} while (storeName.length() == 0);
		
		System.out.println("");
		
		String storeLocation = "";
		do {
			storeLocation = utils.inputString("Input store location: ");
			
			if(storeLocation.length() == 0) {
				System.out.println("Invalid store location! A store location can't have an empty name.");
			}
		} while (storeLocation.length() == 0);
		
		System.out.println("");
		
		double cash = utils.inputDouble("Starting cash for the store: ", 0, Double.MAX_VALUE);
		
		Store store = new Store(storeName,storeLocation,cash);
		
		System.out.println("Store created successfully!");
		utils.pressEnterToContinue();
		
		StoreMenu menu = new StoreMenu(store);
		menu.mainMenu();
	}
}
