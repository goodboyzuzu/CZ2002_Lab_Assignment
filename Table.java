package project;

public class Table {
	private int tableNo;
	private int tableSize;
	private int staffID = 0; //if != 0 then occupied
	private boolean reserved;
	public static Menu[] order = new Menu[20];
	public static int orderQuantity = 0;
	
	public Table(int tableNo,int tableSize) {
		this.tableNo = tableNo;
		this.tableSize = tableSize;
	}
	
	public int getTableNo() {
		return tableNo;
	}
	
	public int getTableSize() {
		return tableSize;
	}
	
	public int getStaffID() {
		return staffID;
	}
	
	public boolean isReserved() {
		return reserved;
	}
	
	public void createOrder(int staff_id) {
		this.staffID = staff_id;
	}
}
