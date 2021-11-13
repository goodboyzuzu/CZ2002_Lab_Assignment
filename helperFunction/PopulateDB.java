package helperFunction;

import entity.*;

//import java.text.SimpleDateFormat;
import java.util.ArrayList;

import controller.OrderController;

public class PopulateDB {
	
	public static ArrayList<Staff> staffArrayList = new ArrayList<Staff>();
    public static ArrayList<MenuItem> menuItemArrayList = new ArrayList<MenuItem>();
    public static ArrayList<Table> tableArrayList = new ArrayList<Table>();
    public static ArrayList<Order> orderArrayList = new ArrayList<Order>();
    public static ArrayList<Order> pastOrderArrayList = new ArrayList<Order>();
	
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
        
        OrderController.setOrderID(11);
	}
}
