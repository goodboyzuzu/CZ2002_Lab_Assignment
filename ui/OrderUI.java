package ui;

import controller.*;
import java.util.Scanner;

public class OrderUI {
    
    private static OrderController orderCtrl;
    
    public void showOrderOptions() {
        int orderChoice = 0;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("(1) Create new order");
            System.out.println("(2) View current orders");
            System.out.println("(3) Add items to order");
            System.out.println("(4) Remove items from order");
            System.out.println("(99) Go back to main menu");
            
            orderChoice = sc.nextInt();
            sc.nextLine();
            switch(orderChoice) {
            case 1:
                createOrder();
                break;
            case 2:
                viewOrder();
                break;
            case 3:
                addToOrder();
                break;
            case 4:
                removeFromOrder();
                break;
            case 99:
                break;
            default:
                System.out.println("Please input a different number\n");
            }
        } while (orderChoice != 99);
    }
    
    private void createOrder() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Which table is this for?");
        int tableNo = sc.nextInt();
        System.out.println("Enter your staff ID");
        int staffID = sc.nextInt();
        
        orderCtrl.createOrder(staffID, tableNo);
    }
    
    private void viewOrder() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Tables with order:");
        orderCtrl.viewOrderedTables();
        
        System.out.println("Which table's order do you want to display?");
        orderCtrl.viewOrder(sc.nextInt());
    }
    
    private void addToOrder() {
        
    }
    
    private void removeFromOrder() {
        
    }
}
