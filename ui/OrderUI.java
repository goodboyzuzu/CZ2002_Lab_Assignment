package ui;

import controller.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderUI {
    
    private static OrderController orderCtrl = new OrderController();
    
    public void showOrderOptions() {
        int orderChoice = 0;
        Scanner sc = new Scanner(System.in);
        
        do {
            try {
                System.out.println("(1) Create new order");
                System.out.println("(2) View current orders");
                System.out.println("(3) Add items to order");
                System.out.println("(4) Remove items from order");
                System.out.println("(5) Print order invoice");
                System.out.println("(6) Print sales report");
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
                case 5:
                    printOrderInvoice();
                    break;
                case 6:
                    //System.out.println("Sales report not available");
                    printSalesReport();
                    break;
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
        } while (orderChoice != 99);
    }
    
    private void createOrder() {
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.println("Which table is this for?");
            int tableNo = sc.nextInt();
            System.out.println("Enter your staff ID");
            int staffID = sc.nextInt();
            
            orderCtrl.createOrder(staffID, tableNo);
        }
        
        catch(InputMismatchException e) {
            System.out.println("Invalid input\n");
            sc.nextLine();
        }
    }
    
    private void viewOrder() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Tables with order:");
        orderCtrl.viewOrderedTables();
        
        try {
            System.out.println("Which table's order do you want to display?");
            orderCtrl.viewOrder(sc.nextInt());
        }
        
        catch(InputMismatchException e) {
            System.out.println("Invalid Input\n");
            sc.nextLine();
        }
    }
    
    private void addToOrder() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Tables with order:");
        orderCtrl.viewOrderedTables();
        
        try {
            System.out.println("Which table's order do you want to add items to?");
            orderCtrl.addToOrder(sc.nextInt());
        }
        
        catch(InputMismatchException e) {
            System.out.println("Invalid Input\n");
            sc.nextLine();
        }
    }
    
    private void removeFromOrder() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Tables with order:");
        orderCtrl.viewOrderedTables();
        
        try {
            System.out.println("Which table's order do you want to remove items from?");
            orderCtrl.removeFromOrder(sc.nextInt());
        }
        
        catch(InputMismatchException e) {
            System.out.println("Invalid Input\n");
            sc.nextLine();
        }
    }
    
    private void printOrderInvoice() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Tables with order:");
        orderCtrl.viewOrderedTables();
        
        try {
            System.out.println("Which table do you want to print the invoice for?");
            int tableNo = sc.nextInt();
            sc.nextLine();
            System.out.println("Is the customer a member? (Y/N)");
            String isMember = sc.nextLine();
            
            if (!(isMember.equals("Y") || isMember.equals("N")))
                throw new Exception("Please enter Y or N");
            if (isMember.equals("Y")) orderCtrl.printOrderInvoice(tableNo, true);
            else orderCtrl.printOrderInvoice(tableNo, false);
        } 
        
        catch(InputMismatchException e) {
            System.out.println("Please input an integer\n");
            sc.nextLine();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void printSalesReport() {
        orderCtrl.printSalesReport();
    }
}
