package project;

public class MenuList {
	
	public static final int MENU_MAX = 30;
	public Menu[] fullMenu = new Menu[MENU_MAX];
	public int menuQuantity = 0;
	
	public MenuList() {
		for(int i=0; i<MENU_MAX; i++) {
			fullMenu[i] = new Menu(0,"NA","NA",0);
		}
		
		fullMenu[0] = new Menu(2,"Chicken Chop","Meat",10.24);
		fullMenu[1] = new Menu(3,"Ice Cream","Soft",0.50);
		fullMenu[2] = new Menu(4,"Coke", "Cold", 1.24);
		fullMenu[3] = new Menu(1,"Salad","Vegetables",3.50);
		fullMenu[4] = new Menu(2,"Chicken Rice","Chicken and Rice", 3.00);
		fullMenu[5] = new Menu(3,"Ice Kacang","Shaved ice",1.20);
		fullMenu[6] = new Menu(4,"Pepsi","Cold", 1.24);
	}
	
	/*public void menuCategoryList(int category) {
		for(int i=0; i<MENU_MAX; i++) {
			if (fullMenu[i].getCategory() == category) fullMenu[i].displayItem();
		}
	}*/
	
	private void menuSort(int index) {
		Menu tempMenuItem = new Menu(0,"NA","NA",0);
		
		for (int i=index; i<menuQuantity; i++) { //bubble sort
			tempMenuItem = fullMenu[i];
			fullMenu[i] = fullMenu[i+1];
			fullMenu[i+1] = tempMenuItem;
		}
	}
	
	public int findIndex(String inputName) {
		for(int i=0; i<MENU_MAX;i++) {
			if (fullMenu[i].getMenuName().compareTo(inputName) == 0) {
				return i;
			}
		}
		return -1;
	}
	
	public void menuAdd(int category, String name, String description, double price) { //assume menuQuantity never exceeds MENU_MAX
		fullMenu[menuQuantity] = new Menu(category, name, description, price);
		menuQuantity++;
	}
	
	public void menuRemove(int index) {
		fullMenu[index] = new Menu(0,"NA","NA",0);
		menuQuantity--;
		menuSort(index);
	}
	
	public void displayItem(int index) {
		fullMenu[index].displayItem();
	}
	
	public void displayMenu() {
		System.out.println("\nAppetizers:");
		for(int i=0;i<MENU_MAX;i++) {
			if(fullMenu[i].getCategory() == 1) {
				System.out.println(fullMenu[i].getMenuName());
				System.out.println("  "+fullMenu[i].getMenuDesc());
				System.out.println("  "+fullMenu[i].getPrice());
			}
		}
		
		System.out.println("\nMains:");
		for(int i=0;i<MENU_MAX;i++) {
			if(fullMenu[i].getCategory() == 2) {
				System.out.println(fullMenu[i].getMenuName());
				System.out.println("  "+fullMenu[i].getMenuDesc());
				System.out.println("  "+fullMenu[i].getPrice());
			}
		}
		
		System.out.println("\nDesserts:");
		for(int i=0;i<MENU_MAX;i++) {
			if(fullMenu[i].getCategory() == 3) {
				System.out.println(fullMenu[i].getMenuName());
				System.out.println("  "+fullMenu[i].getMenuDesc());
				System.out.println("  "+fullMenu[i].getPrice());
			}
		}
		
		System.out.println("\nDrinks:");
		for(int i=0;i<MENU_MAX;i++) {
			if(fullMenu[i].getCategory() == 4) {
				System.out.println(fullMenu[i].getMenuName());
				System.out.println("  "+fullMenu[i].getMenuDesc());
				System.out.println("  "+fullMenu[i].getPrice());
			}
		}
		
		System.out.println("\nPromotions:");
		for(int i=0;i<MENU_MAX;i++) {
			if(fullMenu[i].getCategory() == 5) {
				System.out.println(fullMenu[i].getMenuName());
				System.out.println("  "+fullMenu[i].getMenuDesc());
				System.out.println("  "+fullMenu[i].getPrice());
			}
		}
	}
	
	public void menuUpdateCategory(int index, int newCategory) {
		fullMenu[index].setCategory(newCategory);
	}
	
	public void menuUpdateName(int index, String newName) {
		fullMenu[index].setName(newName);
	}
	
	public void menuUpdateDesc(int index, String newDesc) {
		fullMenu[index].setDesc(newDesc);
	}
	
	public void menuUpdatePrice(int index, double newPrice) {
		fullMenu[index].setPrice(newPrice);
	}
	
	public int getMenuCategory(int index) {
		return fullMenu[index].getCategory();
	}
	
	public String getMenuName(int index) {
		return fullMenu[index].getMenuName();
	}
	
	public String getMenuDesc(int index) {
		return fullMenu[index].getMenuDesc();
	}
	
	public double getMenuPrice(int index) {
		return fullMenu[index].getPrice();
	}
}
