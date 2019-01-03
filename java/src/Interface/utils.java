package Interface;

import java.util.Calendar;
import java.util.Scanner;

import Kids2Kids.Date;

public class utils {
	
	public static void clearScreen() {
		for(int i = 0; i < 20; i++) {
			System.out.println("");
		}
	}
	
	public static int inputInt(String msg, int min, int max) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int number;
		do {
		    System.out.print(msg);
		    while (sc.hasNextLine() && !sc.hasNextInt()) {
		    	sc.next();
		        System.out.println("That's not a valid input!");
		        System.out.println(msg);
		    }

		   	number = sc.nextInt();
		    
		    if(number < min || number > max) {
		    	System.out.println("That's not a valid input!");
		    }
		} while (number < min || number > max);
		return number;
	}
	
	public static double inputDouble(String msg, double min, double max) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		double number;
		do {
		    System.out.print(msg);
		    while (sc.hasNextLine() && !sc.hasNextDouble()) {
		    	sc.next();
		        System.out.println("That's not a valid input!");
		        System.out.println(msg);
		    }

		   	number = sc.nextDouble();
		    
		    if(number < min || number > max) {
		    	System.out.println("That's not a valid input!");
		    }
		} while (number < min || number > max);
		return number;
	}
	
	public static String inputString(String msg) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print(msg);
		return sc.nextLine();
	}
	
	public static void pressEnterToContinue() {
		System.out.println("Press \"ENTER\" to continue...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
	
	public static void printMenuTitle(String title) {
		System.out.print("+");
		for(int i = 0; i < title.length(); i++) {
			System.out.print("-+");
		}
		
		System.out.println();
		
		System.out.print("|");
		for(int i = 0; i < title.length(); i++) {
			System.out.print(title.charAt(i));
			if(i < title.length() - 1) {
				System.out.print(" ");
			}
		}
		System.out.print("|");
		
		System.out.println();
		
		System.out.print("+");
		for(int i = 0; i < title.length(); i++) {
			System.out.print("-+");
		}
		
		System.out.println();
	}
	
	public static Date today() {
		Calendar cal = Calendar.getInstance();
		
		return new Date(cal.DAY_OF_MONTH,cal.MONTH,cal.YEAR);
	}
}
