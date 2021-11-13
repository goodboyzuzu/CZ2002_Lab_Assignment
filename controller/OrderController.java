package controller;

import entity.*;
import helperFunction.PopulateDB;

import static helperFunction.FormatAsTable.formatAsTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrderController {
    private static int orderID = 11; 
    public static ArrayList<Order> orderList = PopulateDB.orderArrayList;
    
    public static int getOrderID() {
        return orderID;
    }
    
    public void createOrder(int staffID, int tableNo) {
        StaffController staffCtrl = new StaffController();
        boolean hasOrder = false;
        if(staffCtrl.isStaff(staffID)) {
           for(Order order : orderList) {
               if(order.getTableNo() == tableNo)
                   hasOrder = true;
           }
           
           if(!hasOrder) {
               Order newOrder = new Order(orderID, staffID, tableNo);
               orderList.add(newOrder);
               System.out.println("Order No " + orderID + " has been created for Table " + tableNo + " by " +staffCtrl.getNameFromID(staffID));
               orderID++; //ensure running sequence
           } else System.out.println("Table already has an order");
        } else System.out.println("Invalid Staff ID");
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
            StaffController staffCtrl = new StaffController();
            System.out.println("Server: " + staffCtrl.getNameFromID(staffID) + '\n');
            
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
            System.out.println("Total Price: " + (orderList.get(index).getTotalPrice()));
        }
    }
}
