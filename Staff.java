package project;

public class Staff {
	private String staffName;
	private int staffGender; // 0: male, 1: female
	private int staffID;
	private int staffTitle; // 0: waiter, 1: cashier, 2: cook, 3: manager
	
	public Staff(String staff_name, int staff_gender, int staff_id, int staff_title) {
		this.staffName = staff_name;
		this.staffGender = staff_gender;
		this.staffID = staff_id;
		this.staffTitle = staff_title;
	}
	
	public String getName() {
		return staffName;
	}
	
	public int getGender() {
		return staffGender;
	}
	
	public int getStaffID() {
		return staffID;
	}
	
	public int getTitle() {
		return staffTitle;
	}
}
