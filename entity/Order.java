package entity;

import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;

public class Order {
    private int orderId;
    private int staffId;
    private int tableNo;
    //private Date timestamp = new Date();
    private double finalTotal; //after GST
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
    
    public double getFinalTotal() {
        return finalTotal;
    }
    
    public ArrayList<MenuItem> getFoodList(){
        return foodList;
    }
    
    public double getTotalPrice() {
        double totalPrice = 0;
        for (MenuItem menuItem : foodList) {
            totalPrice += menuItem.getPrice();
        }
        return totalPrice;
    }
    
    public void addItem(MenuItem menuItem){
        foodList.add(menuItem);
    }

    public void removeItem(MenuItem menuItem) {
        boolean removed = false;
        for(MenuItem item : foodList) {
            if(item.getName().equals(menuItem.getName())) {
                int index = foodList.indexOf(item);
                foodList.remove(index);
                removed = true;
                break;
            }
        }
        
        if(!removed) System.out.println("Item does not exist in order");
    }
    
    public void setFinalTotal(double finalTotal) {
        this.finalTotal = finalTotal;
    }
}
