package ui;

import controller.*;
import java.util.Scanner;

public class MenuUI {
	
	private static MenuController menuCtrl;
	private static Scanner sc = new Scanner(System.in);
	
	public void showMenuOptions() {
		int menuChoice = 0;
		
		do {
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
			case 99:
				break;
			default:
				System.out.println("Please input a different number\n");
			}
		} while (menuChoice != 99);
		
		//sc.close();
	}
	
	private void showMenuItem(){
        menuCtrl.showMenuItem();
		//System.out.println(menuCtrl.menuItemList.size());
		
		//System.out.println(menuCtrl.menuItemList.get(0).getName());
    }


    private static void addFoodItem() {
    	//Scanner sc = new Scanner(System.in);
    	
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
        //sc.close();
    }
    
    private void addPackage(){
    	//Scanner sc = new Scanner(System.in);
    	
        System.out.println("Enter the name of Package");
        String packageName = sc.nextLine();
        System.out.println("Enter package description");
        String packageDesc = sc.nextLine();
        System.out.println("Enter package price");
        double packagePrice = sc.nextDouble();
        sc.nextLine();
        menuCtrl.addPackage(packageName,packageDesc,packagePrice);
        
        //sc.close();
    }

    private void removeMenuItem() {
        System.out.println("Enter the name of the item");
        showMenuItem();
        String menuName = sc.nextLine();
        menuCtrl.removeMenuItem(menuName);
    }

    private void editMenuItem() {
    	//choose food or package
    		//if food:
    		//enter name of item
    		//display item
    		//choose which attribute to edit
    		//loop or back
    	
    		//if package:
    		//show package list
    		//enter package name
    		//choose which attribute to edit
    			//if edit individual item - maybe reuse add/remove MenuItem method
    }
    
}
