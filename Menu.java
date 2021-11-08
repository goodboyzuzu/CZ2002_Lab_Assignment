package project;

public class Menu {
	private int menuCategory; //1: appetizer, 2:main, 3:dessert, 4:drinks, 5:promo
	private String menuItem;
	private String menuDesc;
	private double menuPrice;
	
	public Menu(int category, String name, String description, double price) {
		this.menuCategory = category;
		this.menuItem = name;
		this.menuDesc = description;
		this.menuPrice = price;
	}
	
	public int getCategory() {
		return menuCategory;
	}
	
	public String getMenuName() {
		return menuItem;
	}
	
	public String getMenuDesc() {
		return menuDesc;
	}
	
	public double getPrice() {
		return menuPrice;
	}
	
	public void setCategory(int newCategory) {
		menuCategory = newCategory;
	}
	
	public void setName(String newName) {
		menuItem = newName;
	}
	
	public void setDesc(String newDesc) {
		menuDesc = newDesc;
	}
	
	public void setPrice(double newPrice) {
		menuPrice = newPrice;
	}
	
	public void displayItem() {
		switch(menuCategory) {
		case 1:
			System.out.println("Category: Appetizer");
			break;
		case 2:
			System.out.println("Category: Main");
			break;
		case 3:
			System.out.println("Category: Dessert");
			break;
		case 4:
			System.out.println("Category: Drink");
			break;
		case 5:
			System.out.println("Category: Promotion");
			break;
		}
		
		System.out.println("Name: " + menuItem);
		System.out.println("Desc: " + menuDesc);
		System.out.format("Price: %.2f \n", menuPrice);
	}
}
