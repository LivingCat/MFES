package Interface;

import Kids2Kids.Store;

public class StoreMenu {
	
	private Store store;
	
	public StoreMenu(Store store) {
		this.store = store;
	}
	
	public void printMainTitle() {
		System.out.println("--------------------------------------------------------------");
		System.out.println(",--. ,--.,--.   ,--.        ,---. ,--. ,--.,--.   ,--.        ");
		System.out.println("|  .'   /`--' ,-|  | ,---. '.-.  \\|  .'   /`--' ,-|  | ,---.  ");
		System.out.println("|  .   ' ,--.' .-. |(  .-'  .-' .'|  .   ' ,--.' .-. |(  .-'  ");
		System.out.println("|  |\\   \\|  |\\ `-' |.-'  `)/   '-.|  |\\   \\|  |\\ `-' |.-'  `) ");
		System.out.println("`--' '--'`--' `---' `----' '-----'`--' '--'`--' `---' `----'  ");
		System.out.println("--------------------------------------------------------------"); 
	}
	
	public void printMainMenu() {
		System.out.println("1. Products");
		System.out.println("2. Sales");
		System.out.println("3. Purchases");
		System.out.println("4. Print Report");
		System.out.println("5. Exit");
	}
	
	public void printProductsMenu() {
		utils.printMenuTitle("PRODUCTS");
		System.out.println();
		System.out.println("1. List Product Classes");
		System.out.println("2. List Stock");
		System.out.println("3. Register New Product Class");
		System.out.println("4. Remove Product Class");
		System.out.println("5. Register New Product");
		System.out.println("6. Remove Product");
		System.out.println("7. Add Stock");
		System.out.println("8. Back");
	}
	
	public void printSalesMenu() {
		utils.printMenuTitle("SALES");
		System.out.println();
		System.out.println("1. List past sales");
		System.out.println("2. Create new sale");
		System.out.println("3. Back");
	}
	
	public void printPurchasesMenu() {
		utils.printMenuTitle("PURCHASES");
		System.out.println();
		System.out.println("1. List past purchases");
		System.out.println("2. Create new client purchase");
		System.out.println("3. Create new supplier purchase");
		System.out.println("4. Back");
	}
	
	public void mainMenu() {
		
		while(true) {
			utils.clearScreen();
			printMainTitle();
			System.out.println();
			printMainMenu();
			int choice = utils.inputInt("Please write your choice: ",1,5);
			
			switch(choice) {
			case 1: 
				productsMenu();
				break;
			case 2:
				salesMenu();
				break;
			case 3:
				purchasesMenu();
				break;
			case 4:
				printReport();
				break;
			case 5:
				return;
			}
		}
	}
	
	public void productsMenu() {
		
		while(true) {
			utils.clearScreen();
			printProductsMenu();
			int choice = utils.inputInt("Please write your choice: ",1,8);
			Products view =  new Products(store);
			
			switch(choice) {
			case 1: 
				view.listProductClasses();
				break;
			case 2:
				view.listProducts();
				break;
			case 3:
				view.registerNewClass();
				break;
			case 4:
				view.deleteClass();
				break;
			case 5:
				view.registerNewProduct();
				break;
			case 6:
				view.deleteProduct();
				break;
			case 7:
				view.addStock();
				break;
			case 8:
				return;
			}
		}
		
	}
	
	public void salesMenu() {
		
		while(true) {
			utils.clearScreen();
			printSalesMenu();
			int choice = utils.inputInt("Please write your choice: ",1,3);
			Sales view =  new Sales(store);
			
			switch(choice) {
			case 1: 
				view.listSales();
				break;
			case 2:
				view.createNewSale();
				break;
			case 3:
				return;
			}
		}
		
	}
	
	public void purchasesMenu() {
		
	}
	
	public void printReport() {}
}
