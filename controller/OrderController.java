package controller;

import entity.*;
import helperFunction.PopulateDB;

import static helperFunction.FormatAsTable.formatAsTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    private static int orderID = 11; 
    public static ArrayList<MenuItem> menuItemList = PopulateDB.menuItemArrayList;
    public static ArrayList<Order> orderList = PopulateDB.orderArrayList;
    public static ArrayList<Order> pastOrderList = PopulateDB.pastOrderArrayList;
    
    public static int getOrderID() {
        return orderID;
    }
    
    public static void setOrderID(int newOrderID) {
        orderID = newOrderID;
    }
    
    public static void createOrder(int staffID, int tableNo) {
        ArrayList<Table> tableList = PopulateDB.tableArrayList;
        boolean isTable = false;
        for (Table table : tableList) {
            if(tableNo == table.getTableNumber()) {
                isTable = true;
            }
        }
        
        if(isTable) {
            boolean hasOrder = false;
            if(StaffController.isStaff(staffID)) {
               for(Order order : orderList) {
                   if(order.getTableNo() == tableNo)
                       hasOrder = true;
               }
               
               if(!hasOrder) {
                   Order newOrder = new Order(orderID, staffID, tableNo);
                   orderList.add(newOrder);
                   System.out.println("Order No " + orderID + " has been created for Table " + tableNo + " by " + StaffController.getNameFromID(staffID));
                   orderID++; //ensure running sequence
                   addToOrder(tableNo);
               } else System.out.println("Table already has an order");
            } else System.out.println("Invalid Staff ID");
        } else System.out.println("Invalid Table number");
    }
    
    public static void viewOrderedTables() {
        ArrayList<Integer> tableList = new ArrayList<Integer>();
        for(Order order : orderList) {
            tableList.add(order.getTableNo());
        }
        Collections.sort(tableList);
        for(Integer tableNo : tableList) {
            System.out.println("Table " + tableNo);
        }
    }
    
    public static void viewOrder(int tableNo) {
        boolean hasOrder = false;
        int index = -1;
        for(Order order : orderList) {
            if(tableNo == order.getTableNo()) {
                hasOrder = true;
                index = orderList.indexOf(order);
            }
        }
        
        if(hasOrder) {
            System.out.println("Table " + tableNo);
            int staffID = orderList.get(index).getStaffID();
            System.out.println("Server: " + StaffController.getNameFromID(staffID) + '\n');
            
            List<List<String>> rows = new ArrayList<>();
            List<String> header = Arrays.asList("Category", "Name", "Description", "Price", "Course","Food in package");
            rows.add(header);
            ArrayList<MenuItem> foodList = orderList.get(index).getFoodList();
            for (MenuItem item : foodList) {
                if (!(item instanceof Food)) {
                    rows.add(Arrays.asList("Package", item.getName(), item.getDesc(), String.valueOf(item.getPrice()), "-",((PromotionalPackage)item).getFoodString()));
                }
            }
            for (MenuItem item : foodList) {
                if (item instanceof Food) {
                    Food foodItem = (Food) item;
                    List<String> body = Arrays.asList("Food", foodItem.getName(), foodItem.getDesc(), String.valueOf(foodItem.getPrice()), String.valueOf(foodItem.getCourseType()),"-");
                    rows.add(body);
                }
            }
            System.out.println(formatAsTable(rows));
            System.out.println("Total Price: " + orderList.get(index).getTotalPrice() + '\n');
        } else System.out.println("Table does not have an order\n");
    }
    
    public static void addToOrder(int tableNo) {
        Scanner sc = new Scanner(System.in);
        
        boolean hasOrder = false;
        int index = -1;
        for(Order order : orderList) {
            if(tableNo == order.getTableNo()) {
                hasOrder = true;
                index = orderList.indexOf(order);
            }
        }
        
        if(hasOrder) {
            System.out.println("Enter name of item to add to Table " + tableNo + "'s order. Enter \"quit\" to end.");
            MenuController.showMenuItem();
            
            String menuName;
            do {
                menuName = sc.nextLine();
                for (MenuItem item : menuItemList) {
                    if (item.getName().equals(menuName)) {
                        System.out.println(menuName + " added to order");
                        orderList.get(index).addItem(item);
                    }
                }
                
            } while (!(menuName.equals("quit")));
        } else System.out.println("Table does not have an order\n");
    }
    
    public static void removeFromOrder(int tableNo) {
        Scanner sc = new Scanner(System.in);
        
        boolean hasOrder = false;
        int index = -1;
        for(Order order : orderList) {
            if(tableNo == order.getTableNo()) {
                hasOrder = true;
                index = orderList.indexOf(order);
            }
        }
        
        if(hasOrder) {
            System.out.println("Enter name of item to remove from Table " + tableNo + "'s order. Enter \"quit\" to end.");
            viewOrder(tableNo);
            
            String menuName;
            do {
                menuName = sc.nextLine();
                for (MenuItem item : menuItemList) {
                    if (item.getName().equals(menuName)) {
                        System.out.println(menuName + " removed from order");
                        orderList.get(index).removeItem(item);
                    }
                }
            } while (!(menuName.equals("quit")));
        } else System.out.println("Table does not have an order\n");
    }
    
    public static void printOrderInvoice(int tableNo, boolean isMember) {
        Scanner sc = new Scanner(System.in);
        
        boolean hasOrder = false;
        int index = -1;
        for(Order order : orderList) {
            if(tableNo == order.getTableNo()) {
                hasOrder = true;
                index = orderList.indexOf(order);
            }
        }
        
        if(hasOrder) {
            System.out.println("Receipt No: " + orderList.get(index).getOrderID());
            System.out.println("Table No: " + orderList.get(index).getTableNo());
            int staffID = orderList.get(index).getStaffID();            
            System.out.println("Served by: " + StaffController.getNameFromID(staffID));
            //System.out.println("Date: " + );
            System.out.println("");
            
            List<List<String>> rows = new ArrayList<>();
            List<String> header = Arrays.asList("Name", "Price");
            rows.add(header);
            ArrayList<MenuItem> foodList = orderList.get(index).getFoodList();
            for (MenuItem item : foodList) {
                    rows.add(Arrays.asList(item.getName(), String.valueOf(item.getPrice())));
            }
            
            rows.add(Arrays.asList("",""));
            rows.add(Arrays.asList("Subtotal", String.valueOf(orderList.get(index).getTotalPrice())));
            double discount = orderList.get(index).getTotalPrice() * 0.05;
            rows.add(Arrays.asList("Membership Discount","-"+String.format("%.2f", discount)));
            double tax = (orderList.get(index).getTotalPrice() - discount) * 0.07;
            rows.add(Arrays.asList("GST (7%)",String.format("%.2f", tax)));
            double finalTotal = orderList.get(index).getTotalPrice() - discount + tax;
            rows.add(Arrays.asList("Total",String.format("%.2f", finalTotal)));
            
            System.out.println(formatAsTable(rows));
            
            orderList.get(index).setFinalTotal(finalTotal);
            pastOrderList.add(orderList.get(index));
            orderList.remove(index);
        } else System.out.println("Table does not have an order\n");
    }
}
