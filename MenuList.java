package project;

public class MenuList {
	
	public static final int MENU_MAX = 30;
	private static Menu[] fullMenu = new Menu[MENU_MAX];
	public static int menuQuantity = 0;
	
	public MenuList() {
		for(int i=0; i<MENU_MAX; i++) {
			fullMenu[i] = new Menu(0,"NA","NA",0);
		}
	}
	
	/*public void menuCategoryList(int category) {
		for(int i=0; i<MENU_MAX; i++) {
			if (fullMenu[i].getCategory() == category) fullMenu[i].displayItem();
		}
	}*/
	
	private static void menuSort(int index) {
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
		MenuList.menuSort(index);
	}
	
	public void displayItem(int index) {
		fullMenu[index].displayItem();
	}
	
	public void displayMenu() {
		System.out.println("Appetizers:");
		for(int i=0;i<MENU_MAX;i++) {
			if(fullMenu[i].getCategory() == 1) {
				System.out.println(fullMenu[i].getMenuName());
				System.out.println("  "+fullMenu[i].getMenuDesc());
				System.out.println("  "+fullMenu[i].getPrice());
			}
		}
		
		System.out.println("Mains:");
		for(int i=0;i<MENU_MAX;i++) {
			if(fullMenu[i].getCategory() == 2) {
				System.out.println(fullMenu[i].getMenuName());
				System.out.println("  "+fullMenu[i].getMenuDesc());
				System.out.println("  "+fullMenu[i].getPrice());
			}
		}
		
		System.out.println("Desserts:");
		for(int i=0;i<MENU_MAX;i++) {
			if(fullMenu[i].getCategory() == 3) {
				System.out.println(fullMenu[i].getMenuName());
				System.out.println("  "+fullMenu[i].getMenuDesc());
				System.out.println("  "+fullMenu[i].getPrice());
			}
		}
		
		System.out.println("Drinks:");
		for(int i=0;i<MENU_MAX;i++) {
			if(fullMenu[i].getCategory() == 4) {
				System.out.println(fullMenu[i].getMenuName());
				System.out.println("  "+fullMenu[i].getMenuDesc());
				System.out.println("  "+fullMenu[i].getPrice());
			}
		}
		
		System.out.println("Promotions:");
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
}
