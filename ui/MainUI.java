package ui;

import java.util.Scanner;

import controller.*;
import helperFunction.*;

public class MainUI {
	
	private static RestaurantController restaurant = new RestaurantController();
	private static Scanner sc = new Scanner(System.in);
	
	public static void main (String[] args) {
		
		PopulateDB.populateDB();
		
		int appChoice = 0;
		
		do{
			System.out.println("Welcome to the RRPSS App!\n");
			System.out.println("Choose an option:");
			System.out.println("(1) Menu Options");
			System.out.println("(2) Order Options");
			System.out.println("(3) Reservation Options");
			System.out.println("(4) Print order invoice");
			System.out.println("(5) Print sales report");
			System.out.println("(99) Quit");
			
			appChoice = sc.nextInt();
			restaurant.chooseFunction(appChoice);
			
		} while (appChoice != 99);
		
		//sc.close();
	}
}
