package project;

import java.util.Scanner;

public class RRPSS_App {
	
	public static StaffDirectory newStaffList = new StaffDirectory();
	public static MenuList newMenuList = new MenuList();
	//public static int staffIndex = -1; // to remove if login is removed
	public static Table[] restaurant = new Table[10];
	public static Order[] orders = new Order[10];

	public static void main (String[] args) {
		
		for (int i=0; i<10; i++) {
			restaurant[i] = new Table(i+1,(i+2)%2*2); //2 tables each of size {2,4,6,8,10}
			orders[i] = new Order(i+1);
		}
		
		System.out.println("Welcome to the RRPSS App! Please input your ID number:");
		
		Scanner input = new Scanner(System.in);
		
		int staffIndex = -1;
		//remove login later to be used for order system
		do { 
			int userID = input.nextInt();
			if (StaffDirectory.findIndex(userID) != -1) {
				staffIndex = StaffDirectory.findIndex(userID);
				StaffDirectory.displayStaff(staffIndex);
			} else {
			System.out.println("You have inputted an invalid ID. Please try again.");
			}
		} while (staffIndex == -1);
		
		appMain();
		input.close();
	}
	
	public static void appMain() {
		Scanner input = new Scanner(System.in);
		int appChoice = 0;
		
		do{
			System.out.println("\nWhat would you like to do now?");
			System.out.println("(1) Menu Items");
			System.out.println("(2) Create order");
			System.out.println("(3) View/edit order");
			System.out.println("(4) Create reservation booking");
			System.out.println("(5) Check table availability");
			System.out.println("(6) Print order invoice");
			System.out.println("(7) Print sales report");
			System.out.println("(99) Quit");
			
			appChoice = input.nextInt();
			switch(appChoice) {
			case 1: //menu items
				appMenu();
				break;
			case 2: //create order
				appCreateOrder();
				break;
			case 3: //view/edit order
				appViewOrder();
				break;
			case 4: //reservation
				//wip
				break;
			case 5: //table availability
				System.out.println("Which table do you want to check?");
				int tempTableNo = input.nextInt();
				if (restaurant[tempTableNo-1].getStaffID() != 0) System.out.println("Table " + tempTableNo + " is occupied.");
				else System.out.println("Table " + tempTableNo + " is not occupied.");
				break;
			case 6: //print order invoice
				appInvoice(); //wip - need to add to items sales report
				break;
			case 7: //print sales report
				//wip
				break;
			case 99:
				System.out.println("Terminating program...");
				System.exit(1);
			default:
				System.out.println("Please input a different number");
			}
		} while (appChoice != 99);
		input.close();
	}
	
	public static void appMenu() {
		Scanner input = new Scanner(System.in);
		int menuChoice;
		
		do {
			System.out.println("(1) View Menu");
			System.out.println("(2) Add Menu item");
			System.out.println("(3) Remove Menu item");
			System.out.println("(4) Edit Menu item");
			System.out.println("(99) Go back to main menu");
			
			menuChoice = input.nextInt();
			String dummy = input.nextLine(); //clear \n left in input
			switch(menuChoice) {
			case 1: //view menu
				newMenuList.displayMenu();
				break;
			case 2: //add item
				System.out.println("Choose the category:");
				System.out.println("1:Appetizer, 2:Main, 3:Dessert, 4:Drinks, 5:Promotion");
				int category = input.nextInt();
				dummy = input.nextLine(); //clear \n left in input
				
				System.out.println("Type the name of the new item");
				String name = input.nextLine();
				
				System.out.println("Type the description of the new item");
				String description = input.nextLine();
				
				System.out.println("Type the price of the new item");
				double price = input.nextDouble();
				
				newMenuList.menuAdd(category, name, description, price);
				
				System.out.println("New menu item added");
				break;
			case 3: //remove item
				int menuRemoveIndex = -1;
				String removeName;
				do {
					System.out.println("Type the name of the item to be removed");
					
					removeName = input.nextLine();
					if (newMenuList.findIndex(removeName) != -1) {
						menuRemoveIndex = newMenuList.findIndex(removeName);
						newMenuList.menuRemove(menuRemoveIndex);
					} else if (removeName.compareTo("Back") != 0){
					System.out.println("You have inputted an invalid name. Please try again, or type Back to go back.");
					}
				} while (menuRemoveIndex == -1 && removeName.compareTo("Back") != 0);
				
				if(removeName.compareTo("Back") != 0) System.out.println("Menu item removed");
				break;
			case 4: //edit item
				int menuEditIndex = -1;
				String editName;
				
				do {
					System.out.println("Type the name of the item to be editted");

					editName = input.nextLine();
					if (newMenuList.findIndex(editName) != -1) {
						menuEditIndex = newMenuList.findIndex(editName);
					} else if (editName.compareTo("Back") != 0){
					System.out.println("You have inputted an invalid name. Please try again, or type Back to go back.");
					}
				} while (menuEditIndex == -1 && editName.compareTo("Back") != 0);
				
				if(editName.compareTo("Back") == 0) break;
				
				int menuEditChoice;
				do {
					newMenuList.displayItem(menuEditIndex);
					
					System.out.println("What do you want to edit?");
					System.out.println("1:Category, 2:Name, 3:Description, 4:Price, 99:Go back");
					menuEditChoice = input.nextInt();
					dummy = input.nextLine(); //clear \n left in input
					
					switch(menuEditChoice) {
					case 1:
						System.out.println("Choose the category:");
						System.out.println("1:Appetizer, 2:Main, 3:Dessert, 4:Drinks, 5:Promotion");
						int menuEditCategory = input.nextInt();
						newMenuList.menuUpdateCategory(menuEditIndex, menuEditCategory);
						
						System.out.println("Category updated");
						break;
					case 2:
						System.out.println("Type the new name:");
						String menuEditName = input.nextLine();
						newMenuList.menuUpdateName(menuEditIndex, menuEditName);
						
						System.out.println("Name updated");
						break;
					case 3:
						System.out.println("Type the new description");
						String menuEditDesc = input.nextLine();
						newMenuList.menuUpdateDesc(menuEditIndex, menuEditDesc);
						
						System.out.println("Description updated");
						break;
					case 4:
						System.out.println("Type the new price");
						double menuEditPrice = input.nextDouble();
						newMenuList.menuUpdatePrice(menuEditIndex, menuEditPrice);
						
						System.out.println("Price updated");
						break;
					}
				} while (menuEditChoice != 99);
				break;
			case 99: //return to main menu
				appMain();
				break;
			}
		} while (menuChoice != 99);
		input.close();
	}
	
	public static void appCreateOrder() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Which table is this order for?");
		int tableNo = input.nextInt();
		
		System.out.println("Please input your staff ID:");
		int staffID = -1;
		int staffIndex = -1;
		
		do { 
			staffID = input.nextInt();
			if (StaffDirectory.findIndex(staffID) != -1) {
				staffIndex = StaffDirectory.findIndex(staffID);
				StaffDirectory.displayStaff(staffIndex);
			} else {
			System.out.println("You have inputted an invalid ID. Please try again.");
			}
		} while (staffIndex == -1);
		
		restaurant[tableNo-1].createOrder(staffID);
		
		String menuName;
		menuName = input.nextLine(); //clear \n left in input
		int menuIndex = -1;
		do {
			System.out.println("Type the name of the item you want to add. Type Finish to finalise the order");

			menuName = input.nextLine();
			if (newMenuList.findIndex(menuName) != -1) {
				menuIndex = newMenuList.findIndex(menuName);
				orders[tableNo-1].menuAdd(newMenuList.getMenuCategory(menuIndex), newMenuList.getMenuName(menuIndex),newMenuList.getMenuDesc(menuIndex),newMenuList.getMenuPrice(menuIndex));				
			} else if (menuName.compareTo("Finish") != 0){
			System.out.println("You have inputted an invalid name. Please try again, or type Finish to go back.");
			}
		} while (menuName.compareTo("Finish") != 0);
		
		appMain();
	}
	
	public static void appViewOrder() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Which table is this order from?");
		int tableNo = input.nextInt();
		
		int orderChoice;
		
		do {
			System.out.println("(1) View Order");
			System.out.println("(2) Edit Order");
			System.out.println("(99) Back to main menu");
			
			orderChoice = input.nextInt();
			switch(orderChoice) {
			case 1: //view order
				System.out.println("Table No:"+orders[tableNo-1].getTableNo());
				int tableStaffID = restaurant[tableNo-1].getStaffID();
				System.out.println("Staff: "+newStaffList.getStaffName(tableStaffID));
				orders[tableNo-1].displayMenu();
				System.out.println("Order Total: "+orders[tableNo-1].orderTotal());
				break;
			case 2: //edit order
				int editOrderChoice;
				
				do {
					System.out.println("(1) Add item");
					System.out.println("(2) Remove item");
					System.out.println("(99) Back to order menu");
					
					editOrderChoice = input.nextInt();
					switch(editOrderChoice) {
					case 1: //add item
						String menuName;
						menuName = input.nextLine(); //clear \n left in input
						int menuIndex = -1;
						do {
							System.out.println("Type the name of the item you want to add. Type Finish to finalise the order");

							menuName = input.nextLine();
							if (newMenuList.findIndex(menuName) != -1) {
								menuIndex = newMenuList.findIndex(menuName);
								orders[tableNo-1].menuAdd(newMenuList.getMenuCategory(menuIndex), newMenuList.getMenuName(menuIndex),newMenuList.getMenuDesc(menuIndex),newMenuList.getMenuPrice(menuIndex));				
							} else if (menuName.compareTo("Finish") != 0){
							System.out.println("You have inputted an invalid name. Please try again, or type Finish to go back.");
							}
						} while (menuName.compareTo("Finish") != 0);
						break;
					case 2: //remove item
						
						int orderRemoveIndex = -1;
						String removeName;
						do {
							System.out.println("Type the name of the item to be removed");
							
							removeName = input.nextLine();
							if (orders[tableNo-1].findIndex(removeName) != -1) {
								orderRemoveIndex = orders[tableNo-1].findIndex(removeName);
								orders[tableNo-1].menuRemove(orderRemoveIndex);
							} else if (removeName.compareTo("Back") != 0){
							System.out.println("You have inputted an invalid name. Please try again, or type Finish to go back.");
							}
						} while (orderRemoveIndex == -1 && removeName.compareTo("Finish") != 0);
						
						if(removeName.compareTo("Finish") != 0) System.out.println("Menu item removed");
						
					}
				} while (editOrderChoice != 99);
				break;
			}
		} while (orderChoice != 99);
	}
	
	public static void appInvoice() {
		Scanner input = new Scanner(System.in);
		System.out.println("Which table is this order from?");
		int tableNo = input.nextInt();
		
		System.out.println("Table No:"+orders[tableNo-1].getTableNo());
		int tableStaffID = restaurant[tableNo-1].getStaffID();
		System.out.println("Staff: "+newStaffList.getStaffName(tableStaffID));
		orders[tableNo-1].displayMenu();
		System.out.println("Order Total: "+orders[tableNo-1].orderTotal());
		
		//add to report array
		
		orders[tableNo-1] = new Order(tableNo);
		
		input.close();
		appMain();
	}
}
