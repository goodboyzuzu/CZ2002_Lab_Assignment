package controller;

import entity.*;
import helperFunction.*;
import static helperFunction.FormatAsTable.formatAsTable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MenuController {
	
    private static String menuItemFileName = "menuItem.db";
    public static ArrayList<MenuItem> menuItemList = PopulateDB.menuItemArrayList;
	//public static ArrayList<MenuItem> menuItemList2;
	
	public MenuController() {
	    //menuItemList = PopulateDB.menuItemArrayList;
	    loadFromDatabase();
	}
	
	/*public static void showDatabaseMenuItem() {
	    System.out.println(PopulateDB.menuItemArrayList.get(0).getName());
	    menuItemList = PopulateDB.menuItemArrayList;
	    System.out.println(menuItemList.get(0).getName());
	    
	    try {
            FileInputStream menuItemFile = new FileInputStream(menuItemFileName);
            ObjectInputStream menuItemIn = new ObjectInputStream(menuItemFile);
            menuItemList2 = (ArrayList<MenuItem>) menuItemIn.readObject();

            menuItemFile.close();
            menuItemIn.close();

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
	    
	    List<List<String>> rows = new ArrayList<>();
        List<String> header = Arrays.asList("Category2", "Name", "Description", "Price", "Course","Food in package");
        rows.add(header);
        for (MenuItem item : menuItemList2) {
            if (!(item instanceof Food)) {
                rows.add(Arrays.asList("Package", item.getName(), item.getDesc(), String.format("%.2f",item.getPrice()), "-",((PromotionalPackage)item).getFoodString()));
            }
        }
        for (MenuItem item : menuItemList2) {
            if (item instanceof Food) {
                Food foodItem = (Food) item;
                List<String> body = Arrays.asList("Food", foodItem.getName(), foodItem.getDesc(), String.format("%.2f",foodItem.getPrice()), String.valueOf(foodItem.getCourseType()),"-");
                rows.add(body);
            }
        }
        System.out.println(formatAsTable(rows));
    }*/
	
	public static void showMenuItem() {
        List<List<String>> rows = new ArrayList<>();
        List<String> header = Arrays.asList("Category", "Name", "Description", "Price", "Course","Food in package");
        rows.add(header);
        for (MenuItem item : menuItemList) {
            if (!(item instanceof Food)) {
                rows.add(Arrays.asList("Package", item.getName(), item.getDesc(), String.format("%.2f",item.getPrice()), "-",((PromotionalPackage)item).getFoodString()));
            }
        }
        for (MenuItem item : menuItemList) {
            if (item instanceof Food) {
                Food foodItem = (Food) item;
                List<String> body = Arrays.asList("Food", foodItem.getName(), foodItem.getDesc(), String.format("%.2f",foodItem.getPrice()), String.valueOf(foodItem.getCourseType()),"-");
                rows.add(body);
            }
        }
        System.out.println(formatAsTable(rows));
    }
	
	public static void showFoodItem() {
        List<List<String>> rows = new ArrayList<>();
        List<String> header = Arrays.asList("Category", "Name", "Description", "Price", "Course");
        rows.add(header);
        for (MenuItem item : menuItemList) {
            if (item instanceof Food) {
                Food foodItem = (Food) item;
                List<String> body = Arrays.asList("Food", foodItem.getName(), foodItem.getDesc(), String.format("%.2f",foodItem.getPrice()), String.valueOf(foodItem.getCourseType()));
                rows.add(body);
            }
        }
        System.out.println(formatAsTable(rows));
    }
	
	public static void addMenuItem(String foodName, String foodDesc, double foodPrice, int courseIndex) {
		Food newFood = new Food(foodName, foodDesc, foodPrice, Food.CourseType.values()[courseIndex - 1]);
        boolean contains = false;
        for (MenuItem item : menuItemList) {
            if (item.getName() == foodName)
                contains = true;
        }
        if (contains == false) {
            menuItemList.add(newFood);
        } else System.out.println("Item is already in the menu");
        
        saveToDatabase();
	}
	
	public static void addPackage(String packageName, String packageDesc, double packagePrice) {
		Scanner sc = new Scanner(System.in);
		
		PromotionalPackage promo = new PromotionalPackage(packageName, packageDesc, packagePrice);
	    System.out.println("Enter name of food to add. Enter \"quit\" to end");
	    showFoodItem();
	    String foodName;
	    do {
	        foodName = sc.nextLine();
	        boolean isMenuItem = false;
	        for (MenuItem item : menuItemList) {
	            if (item.getName().equals(foodName)) {
	            	System.out.println(foodName + " added to package");
	                promo.addItem((Food) item);
	                isMenuItem = true;
	            }
	        }
	        if(!isMenuItem && !(foodName.equals("quit"))) System.out.println("Food not found in menu");
	    } while (!(foodName.equals("quit")));
	    menuItemList.add(promo);
	    
	    saveToDatabase();
	}
	
	public static void removeMenuItem(String menuName) {
        boolean isMenuItem = false;
	    for(Iterator<MenuItem> it = menuItemList.iterator(); it.hasNext();){
            MenuItem menuItem = it.next();
            if((menuItem.getName()).equals(menuName)){
                it.remove();
                isMenuItem = true;
                System.out.println(menuName +" removed");
            }
        }
	    
	    if(!isMenuItem) System.out.println("Item not found in menu");
	    
	    saveToDatabase();
    }
	
	public static void editMenuItem(String menuName) {
		Scanner sc = new Scanner(System.in);
		boolean contains = false;
		try {
    		for (MenuItem item : menuItemList) {
    			if((item.getName()).equals(menuName)){
    				contains = true;
    				
    				List<List<String>> rows = new ArrayList<>();
    		        List<String> header = Arrays.asList("Category", "Name", "Description", "Price", "Course", "Food in Package");
    		        rows.add(header);
    		        
                    int choice = 0;
                    int index = menuItemList.indexOf(item);
    				if (item instanceof Food) {
    					do {
    						rows.add(Arrays.asList("Food", item.getName(), item.getDesc(), String.format("%.2f",item.getPrice()), String.valueOf(((Food)item).getCourseType()),"-"));
    						System.out.println(formatAsTable(rows));
    						
    						System.out.println("Which attribute do you want edit?");
    						System.out.println("(1) Name");
    						System.out.println("(2) Description");
    						System.out.println("(3) Price");
    						System.out.println("(4) Course Type");
    						System.out.println("(99) Go Back");
    						
    						choice = sc.nextInt();
    						sc.nextLine();
    						
    						switch(choice) {
    						case 1:
    							System.out.println("Enter the new name");
    							menuItemList.get(index).setName(sc.nextLine());
    							System.out.println("Name updated");
    							
    							rows.remove(1);
    							break;
    						case 2:
    							System.out.println("Enter the new description");
    							menuItemList.get(index).setDesc(sc.nextLine());
    							System.out.println("Description updated");
    							
    							rows.remove(1);
    							break;
    						case 3:
    							System.out.println("Enter the new price");
    							menuItemList.get(index).setPrice(sc.nextDouble());
    							System.out.println("Price updated");
    							sc.nextLine();
    							
    							rows.remove(1);
    							break;
    						case 4:
    							System.out.println("Enter the new course type");
    							System.out.println("Course type: (1) MAIN_COURSE (2) DESSERT (3) DRINKS");
    							menuItemList.get(index).setCourseType(sc.nextInt());
    							System.out.println("Course type updated");
    							sc.nextLine();
    							
    							rows.remove(1);
    							break;
    						case 99:
    							break;
    						default:
    							System.out.println("Please input a different number\n");
    						}
    						
    					} while (choice != 99);
    				} else if (item instanceof PromotionalPackage){
    					do {
    						rows.add(Arrays.asList("Package", item.getName(), item.getDesc(), String.format("%.2f",item.getPrice()), "-",((PromotionalPackage)item).getFoodString()));
    						System.out.println(formatAsTable(rows));
    						
    						System.out.println("Which attribute do you want edit?");
    						System.out.println("(1) Name");
    						System.out.println("(2) Description");
    						System.out.println("(3) Price");
    						System.out.println("(4) Food in Package");
    						System.out.println("(99) Go Back");
    						
    						choice = sc.nextInt();
    						sc.nextLine();
    						
    						switch(choice) {
    						case 1:
    							System.out.println("Enter the new name");
    							menuItemList.get(index).setName(sc.nextLine());
    							System.out.println("Name updated");
    							
    							rows.remove(1);
    							break;
    						case 2:
    							System.out.println("Enter the new description");
    							menuItemList.get(index).setDesc(sc.nextLine());
    							System.out.println("Description updated");
    							
    							rows.remove(1);
    							break;
    						case 3:
    							System.out.println("Enter the new price");
    							menuItemList.get(index).setPrice(sc.nextDouble());
    							System.out.println("Price updated");
    							sc.nextLine();
    							
    							rows.remove(1);
    							break;
    						case 4:
    							PromotionalPackage promo = new PromotionalPackage(menuItemList.get(index).getName(), menuItemList.get(index).getDesc(), menuItemList.get(index).getPrice());
    							
    							showFoodItem();
    						
    							System.out.println("All items in the package have been reset.");
    							System.out.println("Enter name of food to add. Enter \"quit\" to end");
    							
    						    String foodName;
    						    do {
    						        foodName = sc.nextLine();
    						        for (MenuItem food : menuItemList) {
    						            if (food.getName().equals(foodName)) {
    						            	System.out.println(foodName);
    						                promo.addItem((Food) food);
    						            }
    						        }
    						    } while (!(foodName.equals("quit")));
    						    menuItemList.set(index, promo);
    							
    							System.out.println("Food in Package updated");
    							
    							item = menuItemList.get(index);
    							rows.remove(1);
    							break;
    						case 99:
    							break;
    						default:
    							System.out.println("Please input a different number\n");
    						}
    					} while (choice != 99);
    				} 
    			}
    		}
		}
		
		catch(InputMismatchException e) {
            System.out.println("Invalid Input\n");
            sc.nextLine();
        }
		if (contains == false) System.out.println("Item not found in menu");
		
		saveToDatabase();
	}
	
	private static void loadFromDatabase() {
        try {
            FileInputStream menuItemFile = new FileInputStream(menuItemFileName);
            ObjectInputStream menuItemIn = new ObjectInputStream(menuItemFile);
            menuItemList = (ArrayList<MenuItem>) menuItemIn.readObject();

            menuItemFile.close();
            menuItemIn.close();

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }

    private static void saveToDatabase() {
        try {
            FileOutputStream menuItemFile = new FileOutputStream(menuItemFileName);
            ObjectOutputStream menuItemOut = new ObjectOutputStream(menuItemFile);

            menuItemOut.writeObject(menuItemList);
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }
}
