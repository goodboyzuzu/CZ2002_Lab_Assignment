package ui;

import controller.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuUI {
	
	private MenuController menuCtrl = new MenuController();
	
	public void showMenuOptions() {
		int menuChoice = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			try {
    		    System.out.println("(1) View Menu");
    			System.out.println("(2) Add Food item");
    			System.out.println("(3) Add Promotional Package");
    			System.out.println("(4) Remove Menu item");
    			System.out.println("(5) Edit Menu item");
    			System.out.println("(99) Go back to main menu");
    			
    			menuChoice = sc.nextInt();
    			sc.nextLine();
    			switch(menuChoice) {
    			case 1:
    				showMenuItem();
    				break;
    			case 2:
    				addFoodItem();
    				break;
    			case 3:
    				addPackage();
    				break;
    			case 4:
    				removeMenuItem();
    				break;
    			case 5:
    				editMenuItem();
    				break;
    			//case 6:
    			//    menuCtrl.showDatabaseMenuItem();
    			//    break;
    			case 99:
    				break;
    			default:
    				System.out.println("Please input a different number\n");
    			}
			}
			
			catch(InputMismatchException e) {
                System.out.println("Please input an integer\n");
                sc.nextLine();
            }
		} while (menuChoice != 99);
	}
	
	private void showMenuItem(){
        menuCtrl.showMenuItem();
    }


    private void addFoodItem() {
    	Scanner sc = new Scanner(System.in);
    	
    	try {
        	System.out.println("Enter food name");
            String foodName = sc.nextLine();
            System.out.println("Enter food description");
            String foodDesc = sc.nextLine();
            System.out.println("Enter food price");
            double foodPrice = sc.nextDouble();
            sc.nextLine();
            System.out.println("Course type: (1) MAIN_COURSE (2) DESSERT (3) DRINKS");
            int courseIndex = sc.nextInt();
            menuCtrl.addMenuItem(foodName,foodDesc,foodPrice, courseIndex);
                
            sc.nextLine();
    	}
    	
    	catch(InputMismatchException e) {
            System.out.println("Error: Input isn't a number\n");
            sc.nextLine();
        }
    }
    
    private void addPackage(){
    	Scanner sc = new Scanner(System.in);
    	
    	try {
            System.out.println("Enter the name of Package");
            String packageName = sc.nextLine();
            System.out.println("Enter package description");
            String packageDesc = sc.nextLine();
            System.out.println("Enter package price");
            double packagePrice = sc.nextDouble();
            sc.nextLine();
            menuCtrl.addPackage(packageName,packageDesc,packagePrice);
    	}
    	
    	catch(InputMismatchException e) {
            System.out.println("Error: Input isn't a number\n");
            sc.nextLine();
        }
    }

    private void removeMenuItem() {
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Enter the name of the item");
        showMenuItem();
        String menuName = sc.nextLine();
        menuCtrl.removeMenuItem(menuName);
    }

    private void editMenuItem() {
    	Scanner sc = new Scanner(System.in);
    	
    	String menuName;
    	System.out.println("Enter the name of the item");
    	menuName = sc.nextLine();
    	menuCtrl.editMenuItem(menuName);
    }
    
}
