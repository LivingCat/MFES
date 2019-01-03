package Interface;

import java.util.Scanner;

import Kids2Kids.Store;

public class Menus {
	
	private Store store = new Store("Kids2Kids","PT",1000);
	static public Menus menu = new Menus();

	public static void main(String[] args) {
		
		menu.store.seed();
		menu.mainMenu();           
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
		
	}
	
	public void purchasesMenu() {
		
	}
	
	public void printReport() {}
}
