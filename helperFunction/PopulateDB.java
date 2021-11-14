package helperFunction;

import entity.*;
import controller.OrderController;
import controller.ReservationController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PopulateDB {
	
	public static ArrayList<Staff> staffArrayList = new ArrayList<Staff>();
	//public static ArrayList<MenuItem> menuItemArrayList;
	//public ArrayList<MenuItem> menuItemArrayList2 = new ArrayList<MenuItem>();
    public static ArrayList<MenuItem> menuItemArrayList = new ArrayList<MenuItem>();
    public static ArrayList<Table> tableArrayList = new ArrayList<Table>();
    public static ArrayList<Order> orderArrayList = new ArrayList<Order>();
    public static ArrayList<Order> pastOrderArrayList = new ArrayList<Order>();
    public static ArrayList<Reservation> reservationArrayList = new ArrayList<Reservation>();
	
    //public PopulateDB() {
    //    loadFromDatabase();
    //}
    
    /*public void loadFile() {
        try {
        FileInputStream menuItemFile = new FileInputStream("menuItem.db");
        ObjectInputStream menuItemIn = new ObjectInputStream(menuItemFile);
        menuItemArrayList2 = (ArrayList<MenuItem>) menuItemIn.readObject();
        menuItemFile.close();
        menuItemIn.close();
        
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }*/
    
	public static void populateDB() {

        tableArrayList.add(new Table(2,1));
        tableArrayList.add(new Table(2,2));
        tableArrayList.add(new Table(4,3));
        tableArrayList.add(new Table(4,4));
        tableArrayList.add(new Table(4,5));
        tableArrayList.add(new Table(4,6));
        tableArrayList.add(new Table(6,7));
        tableArrayList.add(new Table(6,8));
        tableArrayList.add(new Table(10,9));

        Staff emp1=new Staff("Zu Hong",1,'F',"waiter");
        Staff emp2=new Staff("Regine",2,'F',"waitress");
        Staff emp3=new Staff("Barnabas",3,'M',"Manager");
        Staff emp4=new Staff("Shaun",4,'F',"Lady Boss");
        Staff emp5=new Staff("Rayner",5,'M',"Big Boss");
        staffArrayList.add(emp1);
        staffArrayList.add(emp2);
        staffArrayList.add(emp3);
        staffArrayList.add(emp4);
        staffArrayList.add(emp5);

        Food chicken = new Food("chicken","delicious",4.30, Food.CourseType.MAIN_COURSE);
        Food kangKong = new Food("kang kong","oily",5.00, Food.CourseType.MAIN_COURSE);
        Food sugarCane = new Food("sugar cane","Refreshing",1.20, Food.CourseType.DRINKS);
        Food coke= new Food("coke","fizzy",1.20, Food.CourseType.DRINKS);
        Food cheeseCake = new Food("cheesecake","sweet",6.00, Food.CourseType.DESSERT);

        menuItemArrayList.add(chicken);
        menuItemArrayList.add(kangKong);
        menuItemArrayList.add(sugarCane);
        menuItemArrayList.add(coke);
        menuItemArrayList.add(cheeseCake);

        PromotionalPackage meal1 = new PromotionalPackage("chicken Meal","drumstick and drink",5.00);
        meal1.addItem(chicken);
        meal1.addItem(coke);
        menuItemArrayList.add(meal1);
        
        orderArrayList.add(new Order(8,1,1));
        orderArrayList.add(new Order(7,2,2));
        orderArrayList.add(new Order(10,3,3));
        orderArrayList.add(new Order(11,5,5));
        orderArrayList.add(new Order(9,2,8));
        
        tableArrayList.get(0).setServing(true); //table 1
        tableArrayList.get(1).setServing(true); //table 2
        tableArrayList.get(2).setServing(true); //table 3
        tableArrayList.get(4).setServing(true); //table 5
        tableArrayList.get(7).setServing(true); //table 8
        
        orderArrayList.get(0).addItem(meal1);
        orderArrayList.get(0).addItem(coke);
        orderArrayList.get(0).addItem(sugarCane);
        orderArrayList.get(1).addItem(meal1);
        orderArrayList.get(1).addItem(cheeseCake);
        orderArrayList.get(2).addItem(meal1);
        orderArrayList.get(3).addItem(meal1);
        orderArrayList.get(4).addItem(chicken);
        orderArrayList.get(4).addItem(kangKong);
        orderArrayList.get(4).addItem(kangKong);
        
        OrderController.setOrderID(12);
        
        //ReservationController.addReservationItem("Adam", 99999999, 2, new Date());
        //ReservationController.addReservationItem("Bob", 99999998, 2, addSeconds(new Date(),10));
        //ReservationController.addReservationItem("Cece", 99999997, 9, addSeconds(new Date(),30));
        //ReservationController.addReservationItem("Dick", 99999996, 4, addSeconds(new Date(),10));
        
//        reservationArrayList.add(new Reservation(tableArrayList.get(0), "Adam", 2, 99999999,new Date()));
//        reservationArrayList.add(new Reservation(tableArrayList.get(7), "Bob", 2, 99999998,addSeconds(new Date(),10)));
//        reservationArrayList.add(new Reservation(tableArrayList.get(8), "Cece", 9, 99999997,addSeconds(new Date(),10)));
//        reservationArrayList.add(new Reservation(tableArrayList.get(4), "Dick", 4, 99999996,addSeconds(new Date(),10)));
        
        tableArrayList.get(0).setVacant(false);
        tableArrayList.get(7).setVacant(false);
        tableArrayList.get(8).setVacant(false);
        tableArrayList.get(2).setVacant(false);
        reservationArrayList.add(new Reservation(1, "Adam", 2, 99999999,new Date()));
        reservationArrayList.add(new Reservation(8, "Bob", 2, 99999998,addSeconds(new Date(),10)));
        reservationArrayList.add(new Reservation(9, "Cece", 9, 99999997,addSeconds(new Date(),30)));
        //tableArrayList.get(2).setServing(true);
        reservationArrayList.add(new Reservation(3, "Dick", 4, 99999996,addSeconds(new Date(),10)));
        
        serialisation();
	}
	
	public static Date addSeconds(Date date, Integer seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
}
	
    public static void serialisation() {    
	    // Serialisation
	    try {
            FileOutputStream staffFile = new FileOutputStream("staffData.db");
            ObjectOutputStream staffOut = new ObjectOutputStream(staffFile);
            FileOutputStream menuItemFile = new FileOutputStream("menuItem.db");
            ObjectOutputStream menuItemOut = new ObjectOutputStream(menuItemFile);
            FileOutputStream tableFile = new FileOutputStream("tableData.db");
            ObjectOutputStream tableOut = new ObjectOutputStream(tableFile);
            FileOutputStream orderFile = new FileOutputStream("orderData.db");
            ObjectOutputStream orderOut = new ObjectOutputStream(orderFile);
            FileOutputStream pastOrderFile = new FileOutputStream("pastOrderData.db");
            ObjectOutputStream pastOrderOut = new ObjectOutputStream(pastOrderFile);
            FileOutputStream reservationFile = new FileOutputStream("reservationData.db");
            ObjectOutputStream reservationOut = new ObjectOutputStream(reservationFile);
            FileOutputStream orderIDFile = new FileOutputStream("orderID.db");
            ObjectOutputStream orderIDOut = new ObjectOutputStream(orderIDFile);
    
            staffOut.writeObject(staffArrayList);
            menuItemOut.writeObject(menuItemArrayList);
            tableOut.writeObject(tableArrayList);
            orderOut.writeObject(orderArrayList);
            pastOrderOut.writeObject(pastOrderArrayList);
            reservationOut.writeObject(reservationArrayList);
            orderIDOut.writeObject(OrderController.getOrderID());
    
            staffOut.close();
            staffFile.close();
            menuItemOut.close();
            menuItemFile.close();
            tableOut.close();
            tableFile.close();
            orderOut.close();
            orderFile.close();
            pastOrderOut.close();
            pastOrderFile.close();
            reservationOut.close();
            reservationFile.close();
            orderIDOut.close();
            orderIDFile.close();
	    }
	    
	    catch (FileNotFoundException e) {
	        System.out.println("FileNotFoundException is caught");
	    }
	    catch (IOException e) {
	        System.out.println("IOException is caught");
	    }
	}
	
	public static void loadFromDatabase() {
        try {
            FileInputStream staffFile = new FileInputStream("staffData.db");
            ObjectInputStream staffIn = new ObjectInputStream(staffFile);
            staffArrayList = (ArrayList<Staff>) staffIn.readObject();
            staffFile.close();
            staffIn.close();
            
            FileInputStream menuItemFile = new FileInputStream("menuItem.db");
            ObjectInputStream menuItemIn = new ObjectInputStream(menuItemFile);
            menuItemArrayList = (ArrayList<MenuItem>) menuItemIn.readObject();
            menuItemFile.close();
            menuItemIn.close();
            
            FileInputStream tableFile = new FileInputStream("tableData.db");
            ObjectInputStream tableIn = new ObjectInputStream(tableFile);
            tableArrayList = (ArrayList<Table>) tableIn.readObject();
            tableFile.close();
            tableIn.close();
            
            FileInputStream orderFile = new FileInputStream("orderData.db");
            ObjectInputStream orderIn = new ObjectInputStream(orderFile);
            orderArrayList = (ArrayList<Order>) orderIn.readObject();
            orderFile.close();
            orderIn.close();
            
            FileInputStream pastOrderFile = new FileInputStream("pastOrderData.db");
            ObjectInputStream pastOrderIn = new ObjectInputStream(pastOrderFile);
            pastOrderArrayList = (ArrayList<Order>) pastOrderIn.readObject();
            pastOrderFile.close();
            pastOrderIn.close();
            
            FileInputStream reservationFile = new FileInputStream("reservationData.db");
            ObjectInputStream reservationIn = new ObjectInputStream(reservationFile);
            reservationArrayList = (ArrayList<Reservation>) reservationIn.readObject();
            reservationFile.close();
            reservationIn.close();
            
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
	
	public static void saveToDatabase() {
	    try {
	        FileOutputStream staffFile = new FileOutputStream("staffData.db");
            ObjectOutputStream staffOut = new ObjectOutputStream(staffFile);

            staffOut.writeObject(staffArrayList);
	        
            FileOutputStream menuItemFile = new FileOutputStream("menuItem.db");
            ObjectOutputStream menuItemOut = new ObjectOutputStream(menuItemFile);

            menuItemOut.writeObject(tableArrayList);
            
	        FileOutputStream tableFile = new FileOutputStream("tableData.db");
            ObjectOutputStream tableOut = new ObjectOutputStream(tableFile);

            tableOut.writeObject(tableArrayList);
            
            FileOutputStream orderFile = new FileOutputStream("orderData.db");
            ObjectOutputStream orderOut = new ObjectOutputStream(orderFile);

            orderOut.writeObject(orderArrayList);
            
            FileOutputStream pastOrderFile = new FileOutputStream("pastOrderData.db");
            ObjectOutputStream pastOrderOut = new ObjectOutputStream(pastOrderFile);

            pastOrderOut.writeObject(pastOrderArrayList);
            
            FileOutputStream reservationFile = new FileOutputStream("reservationData.db");
            ObjectOutputStream reservationOut = new ObjectOutputStream(reservationFile);

            reservationOut.writeObject(reservationArrayList);
            
            FileOutputStream orderIDFile = new FileOutputStream("orderID.db");
            ObjectOutputStream orderIDOut = new ObjectOutputStream(orderIDFile);

            int orderID = 0;
            orderIDOut.writeObject(orderID);
            OrderController.setOrderID(orderID);
            
            staffOut.close();
            staffFile.close();
            menuItemOut.close();
            menuItemFile.close();
            tableOut.close();
            tableFile.close();
            orderOut.close();
            orderFile.close();
            pastOrderOut.close();
            pastOrderFile.close();
            reservationOut.close();
            reservationFile.close();
            orderIDOut.close();
            orderIDFile.close();
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
	}
}
