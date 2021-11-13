package entity;

import java.util.ArrayList;

public class PromotionalPackage extends MenuItem {
	private ArrayList<Food> foodArrayList;

    public PromotionalPackage(String PackageName, String desc, double price){
        super(PackageName,desc,price);
        this.foodArrayList = new ArrayList<Food>();
    }
    public void addItem(Food food){
        foodArrayList.add(food);
    }

    public String getFoodString(){
        String result="";
        for (Food food : foodArrayList){
            result +=food.getName()+", ";
        }
        result = result.substring(0, result.length()-2);
        return result;
    }
}
