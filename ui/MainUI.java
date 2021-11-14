package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.*;
import helperFunction.*;

public class MainUI {
	
	private static RestaurantController restaurant = new RestaurantController();
	
	public static void main (String[] args) {
		
	    //PopulateDB.populateDB(); //remove comment block once when running program for first time or to reset data
	    
	    PopulateDB.loadFromDatabase();
	    
	    
		Scanner sc = new Scanner(System.in);
		int appChoice = 0;
		
		//MenuController.showDatabaseMenuItem();
		//System.out.println(PopulateDB.menuItemArrayList.get(0).getName());
		
		do{
		    try {
    			System.out.println("Welcome to the RRPSS App!\n");
    			System.out.println("Choose an option:");
    			System.out.println("(1) Menu Options");
    			System.out.println("(2) Order Options");
    			System.out.println("(3) Reservation Options");
    			System.out.println("(99) Quit");
    			
    			appChoice = sc.nextInt();
    			restaurant.chooseFunction(appChoice);
		    }
		    
		    catch(InputMismatchException e) {
	            System.out.println("Please input an integer\n");
	            sc.nextLine();
	        }
			
		} while (appChoice != 99);
		
		sc.close();
	}
}
