package controller;

import entity.*;
import helperFunction.*;
import static helperFunction.FormatAsTable.formatAsTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MenuController {
	
	public static ArrayList<MenuItem> menuItemList = (ArrayList<MenuItem>) PopulateDB.menuItemArrayList.clone();
	
	public static void showMenuItem() {
        List<List<String>> rows = new ArrayList<>();
        List<String> header = Arrays.asList("Category", "Name", "Description", "Price", "Course","Food in package");
        rows.add(header);
        for (MenuItem item : menuItemList) {
            if (!(item instanceof Food)) {
                rows.add(Arrays.asList("Package", item.getName(), item.getDesc(), String.valueOf(item.getPrice()), "-",((PromotionalPackage)item).getFoodString()));
            }
        }
        for (MenuItem item : menuItemList) {
            if (item instanceof Food) {
                Food foodItem = (Food) item;
                List<String> body = Arrays.asList("Food", foodItem.getName(), foodItem.getDesc(), String.valueOf(foodItem.getPrice()), String.valueOf(foodItem.getCoursetype()),"-");
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
                List<String> body = Arrays.asList("Food", foodItem.getName(), foodItem.getDesc(), String.valueOf(foodItem.getPrice()), String.valueOf(foodItem.getCoursetype()));
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
        }
	}
	
	public static void addPackage(String packageName, String packageDesc, double packagePrice) {
		Scanner sc = new Scanner(System.in);
		
		PromotionalPackage promo = new PromotionalPackage(packageName, packageDesc, packagePrice);
	    System.out.println("Enter name of food to add. Enter \"quit\" to end");
	    showFoodItem();
	    String foodName;
	    do {
	        foodName = sc.nextLine();
	        for (MenuItem item : menuItemList) {
	            if (item.getName().equals(foodName)) {
	            	System.out.println(foodName);
	                promo.addItem((Food) item);
	            }
	        }
	    } while (!(foodName.equals("quit")));
	    menuItemList.add(promo);
	    
	    showMenuItem();
	    sc.close();
	}
	
	public void removeMenuItem(String menuName){
        for(Iterator<MenuItem> it = menuItemList.iterator(); it.hasNext();){
            MenuItem menuItem = it.next();
            if((menuItem.getName()).equals(menuName)){
                it.remove();
            }
        }
    }
}
