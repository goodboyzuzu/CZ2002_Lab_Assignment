package controller;

import entity.*;
import helperFunction.PopulateDB;

import static helperFunction.FormatAsTable.formatAsTable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    private static int orderID;
    private static String menuItemFileName = "menuItem.db";
    private static String orderFileName = "orderData.db";
    private static String pastOrderFileName = "pastOrderData.db";
    private static String tableFileName = "tableData.db";
    public static ArrayList<MenuItem> menuItemList = PopulateDB.menuItemArrayList;
    public static ArrayList<Order> orderList = PopulateDB.orderArrayList;
    public static ArrayList<Order> pastOrderList = PopulateDB.pastOrderArrayList;
    public static ArrayList<Table> tableList = PopulateDB.tableArrayList;
    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat monthFormatter = new SimpleDateFormat("MM/yyyy");
    
    public OrderController() {
        loadFromDatabase();
    }
    
    public static int getOrderID() {
        return orderID;
    }
    
    public static void setOrderID(int newOrderID) {
        orderID = newOrderID;
    }
    
    public static void createOrder(int staffID, int tableNo) {
        try {
            boolean isTable = false;
            for (Table table : tableList) {
                if(tableNo == table.getTableNumber()) {
                    isTable = true;
                }
            }
            
            if(!isTable) {
                throw new Exception("Invalid Table Number\n");
            }
            if(!StaffController.isStaff(staffID)) {
                throw new Exception("Invalid Staff ID\n");
            }
            
            boolean hasOrder = false;
            for(Order order : orderList) {
                if(order.getTableNo() == tableNo)
                hasOrder = true;
            }
            if(hasOrder) {
                throw new Exception("Table already has an order\n");
            }
            
            Order newOrder = new Order(orderID, staffID, tableNo);
            orderList.add(newOrder);
            System.out.println("Order No " + orderID + " has been created for Table " + tableNo + " by " + StaffController.getNameFromID(staffID));
            orderID++; //ensure running sequence
            addToOrder(tableNo);
            tableList.get(tableNo-1).setServing(true);
            saveToDatabase();
        }
        
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void viewOrderedTables() {
        ArrayList<Integer> tableList = new ArrayList<Integer>();
        for(Order order : orderList) {
            tableList.add(order.getTableNo());
        }
        Collections.sort(tableList);
        for(Integer tableNo : tableList) {
            System.out.println(tableNo);
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
                    rows.add(Arrays.asList("Package", item.getName(), item.getDesc(), String.format("%.2f",item.getPrice()), "-",((PromotionalPackage)item).getFoodString()));
                }
            }
            for (MenuItem item : foodList) {
                if (item instanceof Food) {
                    Food foodItem = (Food) item;
                    List<String> body = Arrays.asList("Food", foodItem.getName(), foodItem.getDesc(), String.format("%.2f",foodItem.getPrice()), String.valueOf(foodItem.getCourseType()),"-");
                    rows.add(body);
                }
            }
            System.out.println(formatAsTable(rows));
            System.out.format("Total Price: %.2f\n", orderList.get(index).getTotalPrice());
        } else System.out.println("Table does not have an order\n");
    }
    
    public static void addToOrder(int tableNo) {
        Scanner sc = new Scanner(System.in);
        
        try {
            boolean hasOrder = false;
            int index = -1;
            for(Order order : orderList) {
                if(tableNo == order.getTableNo()) {
                    hasOrder = true;
                    index = orderList.indexOf(order);
                }
            }
            
            if(!hasOrder) {
                throw new Exception("Table does not have an order");
            }
            
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
            saveToDatabase();
        }
        
        catch (InputMismatchException e) {
            System.out.println("Invalid Input \n");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void removeFromOrder(int tableNo) {
        Scanner sc = new Scanner(System.in);
        
        try {
            boolean hasOrder = false;
            int index = -1;
            for(Order order : orderList) {
                if(tableNo == order.getTableNo()) {
                    hasOrder = true;
                    index = orderList.indexOf(order);
                }
            }
            
            if(!hasOrder) {
                throw new Exception("Table does not have an order\n");
            }
            
            System.out.println("Enter name of item to remove from Table " + tableNo + "'s order. Enter \"quit\" to end.");
            viewOrder(tableNo);
            
            String menuName;
            boolean isMenuItem = false;
            do {
                menuName = sc.nextLine();
                for (MenuItem item : menuItemList) {
                    if (item.getName().equals(menuName)) {
                        System.out.println(menuName + " removed from order");
                        isMenuItem = true;
                        orderList.get(index).removeItem(item);
                    }
                }
                if (!isMenuItem) System.out.println("Item not found in menu");
            } while (!(menuName.equals("quit")));
            saveToDatabase();
        }
        
        catch (InputMismatchException e) {
            System.out.println("Invalid Input\n");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void printOrderInvoice(int tableNo, boolean isMember) {
        
        try {
            boolean hasOrder = false;
            int index = -1;
            for(Order order : orderList) {
                if(tableNo == order.getTableNo()) {
                    hasOrder = true;
                    index = orderList.indexOf(order);
                }
            }
            
            if(!hasOrder) {
                throw new Exception("Table does not have an order\n");
            }
            
            System.out.println("Receipt No: " + orderList.get(index).getOrderID());
            System.out.println("Table No: " + orderList.get(index).getTableNo());
            int staffID = orderList.get(index).getStaffID();            
            System.out.println("Served by: " + StaffController.getNameFromID(staffID));
            Date orderDate = new Date();
            orderList.get(index).setDate(orderDate);
            System.out.println("Date: " + dateFormatter.format(new Date()));
            System.out.println("");
            
            List<List<String>> rows = new ArrayList<>();
            List<String> header = Arrays.asList("Name", "Price");
            rows.add(header);
            ArrayList<MenuItem> foodList = orderList.get(index).getFoodList();
            for (MenuItem item : foodList) {
                    rows.add(Arrays.asList(item.getName(), String.format("%.2f",item.getPrice())));
            }
            
            rows.add(Arrays.asList("",""));
            rows.add(Arrays.asList("Subtotal", String.format("%.2f",orderList.get(index).getTotalPrice())));
            double discount = 0;
            orderList.get(index).setMember(isMember);
            if (isMember) {
                discount = orderList.get(index).getTotalPrice() * 0.05;
                rows.add(Arrays.asList("Membership Discount","-"+String.format("%.2f", discount)));
            }
            double serviceCharge = (orderList.get(index).getTotalPrice() - discount) * 0.1;
            rows.add(Arrays.asList("Service Charge (10%)",String.format("%.2f", serviceCharge)));
            double tax = (orderList.get(index).getTotalPrice() - discount + serviceCharge) * 0.07;
            rows.add(Arrays.asList("GST (7%)",String.format("%.2f", tax)));
            double finalTotal = orderList.get(index).getTotalPrice() - discount + serviceCharge + tax;
            rows.add(Arrays.asList("Total (SGD)",String.format("%.2f", finalTotal)));
            
            System.out.println(formatAsTable(rows));
            
            orderList.get(index).setFinalTotal(finalTotal);
            pastOrderList.add(orderList.get(index));
            orderList.remove(index);
            
            ReservationController.findTablebyNo(tableNo).setServing(false);
            ReservationController.findTablebyNo(tableNo).setVacant(true);
            
            saveToDatabase();
        }
        
        catch (InputMismatchException e) {
            System.out.println("Invalid Input\n");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void printSalesReport() {
        double periodTotal = 0;
        for(Order order : pastOrderList) {
            periodTotal += order.getFinalTotal();
        }
        System.out.format("%.2f\n",periodTotal);
        
        int invoiceChoice = 0;
        Scanner sc = new Scanner(System.in);
        
        do {
            try {
                System.out.println("Select the Sales Report period");
                System.out.println("(1) Sales Report by day");
                System.out.println("(2) Sales Report by month");
                System.out.println("(99) Go back");
                
                invoiceChoice = sc.nextInt();
                sc.nextLine();
                switch(invoiceChoice) {
                case 1:
                    try {
                        System.out.println("Enter the month in format DD/MM/YYYY");
                        String dayRequest = sc.nextLine();
                        Date dayReport = dateFormatter.parse(dayRequest);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(dayReport);
                        Calendar dayCalendar = cal;
                        
                        double dayRevenue = 0;
                        for (Order pastOrder : pastOrderList) {
                            Calendar pastCal = Calendar.getInstance();
                            pastCal.setTime(pastOrder.getDate());
                            Calendar pastOrderCalendar = pastCal;
                            
                            if(dayCalendar.get(Calendar.DAY_OF_MONTH) == pastOrderCalendar.get(Calendar.DAY_OF_MONTH) && dayCalendar.get(Calendar.MONTH) == pastOrderCalendar.get(Calendar.MONTH) && dayCalendar.get(Calendar.YEAR) == pastOrderCalendar.get(Calendar.YEAR)) {
                                printPastOrder(pastOrder);
                                dayRevenue += pastOrder.getFinalTotal();
                            }
                        }
                        System.out.format("Total Revenue: %.2f\n", dayRevenue);
                    }
                    
                    catch (InputMismatchException e) {
                        System.out.println("Invalid Input");
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Enter the month in format MM/YYYY");
                        String monthRequest = sc.nextLine();
                        Date monthReport = monthFormatter.parse(monthRequest);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(monthReport);
                        Calendar monthCalendar = cal;
                        
                        double monthRevenue = 0;
                        for (Order pastOrder : pastOrderList) {
                            Calendar pastCal = Calendar.getInstance();
                            pastCal.setTime(pastOrder.getDate());
                            Calendar pastOrderCalendar = pastCal;
                            
                            if(monthCalendar.get(Calendar.MONTH) == pastOrderCalendar.get(Calendar.MONTH) && monthCalendar.get(Calendar.YEAR) == pastOrderCalendar.get(Calendar.YEAR)) {
                                printPastOrder(pastOrder);
                                monthRevenue += pastOrder.getFinalTotal();
                            }
                        }
                        System.out.format("Total Revenue: %.2f\n", monthRevenue);
                    }
                    
                    catch (InputMismatchException e) {
                        System.out.println("Invalid Input");
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
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
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
        } while (invoiceChoice != 99);
    }
    
    private static void printPastOrder(Order pastOrder) {
        System.out.println("Receipt No: " + pastOrder.getOrderID());
        int staffID = pastOrder.getStaffID();            
        System.out.println("Served by: " + StaffController.getNameFromID(staffID));
        System.out.println("Date: " + dateFormatter.format(pastOrder.getDate()));
        System.out.println("");
        
        List<List<String>> rows = new ArrayList<>();
        List<String> header = Arrays.asList("Name", "Price");
        rows.add(header);
        ArrayList<MenuItem> foodList = pastOrder.getFoodList();
        for (MenuItem item : foodList) {
                rows.add(Arrays.asList(item.getName(), String.format("%.2f",item.getPrice())));
        }
        
        rows.add(Arrays.asList("",""));
        rows.add(Arrays.asList("Subtotal", String.format("%.2f",pastOrder.getTotalPrice())));
        double discount = 0;
        if (pastOrder.isMember()) {
            discount = pastOrder.getTotalPrice() * 0.05;
            rows.add(Arrays.asList("Membership Discount","-"+String.format("%.2f", discount)));
        }
        double serviceCharge = (pastOrder.getTotalPrice() - discount) * 0.1;
        rows.add(Arrays.asList("Service Charge (10%)",String.format("%.2f", serviceCharge)));
        double tax = (pastOrder.getTotalPrice() - discount + serviceCharge) * 0.07;
        rows.add(Arrays.asList("GST (7%)",String.format("%.2f", tax)));
        double finalTotal = pastOrder.getTotalPrice() - discount + serviceCharge + tax;
        rows.add(Arrays.asList("Total (SGD)",String.format("%.2f", finalTotal)));
        
        System.out.println(formatAsTable(rows));
    }
    
    private static void loadFromDatabase() {
        try {
            FileInputStream menuItemFile = new FileInputStream(menuItemFileName);
            ObjectInputStream menuItemIn = new ObjectInputStream(menuItemFile);
            menuItemList = (ArrayList<MenuItem>) menuItemIn.readObject();
            menuItemFile.close();
            menuItemIn.close();
            
            FileInputStream tableFile = new FileInputStream(tableFileName);
            ObjectInputStream tableIn = new ObjectInputStream(tableFile);
            tableList = (ArrayList<Table>) tableIn.readObject();
            tableFile.close();
            tableIn.close();
            
            FileInputStream orderFile = new FileInputStream(orderFileName);
            ObjectInputStream orderIn = new ObjectInputStream(orderFile);
            orderList = (ArrayList<Order>) orderIn.readObject();
            orderFile.close();
            orderIn.close();
            
            FileInputStream pastOrderFile = new FileInputStream(pastOrderFileName);
            ObjectInputStream pastOrderIn = new ObjectInputStream(pastOrderFile);
            pastOrderList = (ArrayList<Order>) pastOrderIn.readObject();
            pastOrderFile.close();
            pastOrderIn.close();
            
            FileInputStream orderIDFile = new FileInputStream("orderID.db");
            ObjectInputStream orderIDIn = new ObjectInputStream(orderIDFile);
            OrderController.setOrderID((Integer) orderIDIn.readObject());
            orderIDFile.close();
            orderIDIn.close();

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }

    private static void saveToDatabase() {
        try {
            FileOutputStream tableFile = new FileOutputStream(tableFileName);
            ObjectOutputStream tableOut = new ObjectOutputStream(tableFile);

            tableOut.writeObject(tableList);
            
            FileOutputStream orderFile = new FileOutputStream(orderFileName);
            ObjectOutputStream orderOut = new ObjectOutputStream(orderFile);

            orderOut.writeObject(orderList);
            
            FileOutputStream pastOrderFile = new FileOutputStream(pastOrderFileName);
            ObjectOutputStream pastOrderOut = new ObjectOutputStream(pastOrderFile);

            pastOrderOut.writeObject(pastOrderList);
            
            FileOutputStream orderIDFile = new FileOutputStream("orderID.db");
            ObjectOutputStream orderIDOut = new ObjectOutputStream(orderIDFile);

            orderIDOut.writeObject(orderID);
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }
}
