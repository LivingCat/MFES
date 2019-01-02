package Kids2Kids;

import java.util.Scanner;

public class Interface {
	
	private Store store = new Store("Kids2Kids","PT",1000);

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
		System.out.println("1. Products");
		System.out.println("2. Sales");
		System.out.println("3. Purchases");
		System.out.println("4. Print Report");
	}
	
	public static int userInput(int min, int max) {
		Scanner sc = new Scanner(System.in);
		int number;
		do {
		    System.out.print("Please write your choice: ");
		    while (!sc.hasNextInt()) {
		        System.out.println("That's not a valid option!");
		        sc.next();
		    }
		    number = sc.nextInt();
		    
		    if(number < min || number > max) {
		    	System.out.println("That's not a valid option!");
		    }
		} while (number < min || number > max);
		sc.close();
		return number;
	}
	
	public static void mainMenu() {
		printMainTitle();
		
		
	}
}
