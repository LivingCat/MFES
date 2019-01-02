package Interface;

import java.util.Calendar;
import java.util.Scanner;

import Kids2Kids.Date;

public class utils {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void clearScreen() {
		for(int i = 0; i < 20; i++) {
			System.out.println("");
		}
	}
	
	public static int userInput(int min, int max) {
		int number;
		do {
		    System.out.print("Please write your choice: ");
		    while (sc.hasNextLine() && !sc.hasNextInt()) {
		    	sc.next();
		        System.out.println("That's not a valid option!");
		        System.out.println("Please write your choice: ");
		    }

		   	number = sc.nextInt();
		    
		    if(number < min || number > max) {
		    	System.out.println("That's not a valid option!");
		    }
		} while (number < min || number > max);
		return number;
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
	
	public static void closeScanner() {
		sc.close();
	}
	
	public static Date today() {
		Calendar cal = Calendar.getInstance();
		
		return new Date(cal.DAY_OF_MONTH,cal.MONTH,cal.YEAR);
	}
}
