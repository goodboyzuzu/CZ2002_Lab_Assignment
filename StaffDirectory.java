package project;

public class StaffDirectory {
	
	private static int STAFF_COUNT = 10;
	private static Staff[] staffList = new Staff[STAFF_COUNT];
	
	public StaffDirectory() {
		//name, gender(0:male, 1:female), ID, title(0:waiter, 1:cashier, 2:cook, 3:manager)
		staffList[0] = new Staff("Amanda",1,10805,0);
		staffList[1] = new Staff("Bob",0,11212,0);
		staffList[2] = new Staff("Charlie",0,11509,3);
		staffList[3] = new Staff("Daphne",1,10113,1);
		staffList[4] = new Staff("Eric",0,11801,2);
		staffList[5] = new Staff("Fred",0,12514,1);
		staffList[6] = new Staff("Georgia",1,10518,0);
		staffList[7] = new Staff("Holly",1,10308,0);
		staffList[8] = new Staff("Isaac",0,12101,2);
		staffList[9] = new Staff("Jessica",1,11407,3);
	}
	
	public static int findIndex(int inputID) {
		for(int i=0; i<STAFF_COUNT;i++) {
			if (staffList[i].getStaffID() == inputID) {
				return i;
			}
		}
		return -1;
	}
	
	public String getStaffName(int inputID) {
		for(int i=0; i<STAFF_COUNT; i++) {
			if(staffList[i].getStaffID() == inputID) {
				return staffList[i].getName();
			} 
		}
		return "NA";
	}
	
	public static void displayStaff(int index) {
		System.out.println("Name: "+staffList[index].getName());
		int staffTitle = staffList[index].getTitle();
				
		switch(staffTitle) {
		case 0:
			System.out.println("Title: Waiter");
			break;
		case 1:
			System.out.println("Title: Cashier");
			break;
		case 2:
			System.out.println("Title: Cook");
			break;
		case 3:
			System.out.println("Title: Manager");
			break;
		}
	}
}
