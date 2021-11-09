package project;

public class Order extends MenuList {
	private int tableNo;
	
	
	public Order(int table_no) {
		super();
		tableNo = table_no;
		
		for(int i=0; i<MENU_MAX; i++) { //overwrite hardcoded menu items
			fullMenu[i] = new Menu(0,"NA","NA",0);
		}
	}
	
	public int getTableNo() {
		return tableNo;
	}
	
	public double orderTotal() {
		double total = 0;
		for (int i=0; i<this.menuQuantity;i++) {
			total += fullMenu[i].getPrice();
		}
		
		return total;
	}
	
	public void displayMenu() { //overwrite list by category
		for(int i=0;i<MENU_MAX;i++) {
			if(fullMenu[i].getCategory() != 0) {
				System.out.println(fullMenu[i].getMenuName());
				System.out.println("  "+fullMenu[i].getMenuDesc());
				System.out.println("  "+fullMenu[i].getPrice());
			}
		}
	}
}
