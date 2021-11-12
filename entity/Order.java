package entity;

import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;

public class Order {
    private int orderId;
    private int staffId;
    private int tableNo;
    //private Date timestamp = new Date();
    private ArrayList<MenuItem> foodList = new ArrayList<MenuItem>();
    
    public Order(int orderId, int staffId, int tableNo) {
        this.orderId = orderId;
        this.staffId = staffId;
        this.tableNo = tableNo;
    }
    
    public int getOrderID() {
        return orderId;
    }
    
    public int getStaffID() {
        return staffId;
    }
    
    public int getTableNo() {
        return tableNo;
    }
    
    //public Date getDate() {
    //    return timestamp;
    //}
    
    public ArrayList<MenuItem> getFoodList(){
        return foodList;
    }
    
    public double getTotalPrice() {
        int totalPrice = 0;
        for (MenuItem menuItem : foodList) {
            totalPrice += menuItem.getPrice();
        }
        return totalPrice;
    }
    
    public void addItem(MenuItem menuItem){
        foodList.add(menuItem);
    }

    public boolean removeItem(String menuItem) {
        for(MenuItem item : foodList) {
            if(item.getName().equals(menuItem)) {
                int index = foodList.indexOf(item);
                foodList.remove(index);
                return true;
            }
        }
        return false;
    }
}
