package entity;

import java.util.Date;

public class Reservation {

    private Table table;
    
    private String name;
    private int pax;
    
    private int contactNumber;
    private Date reservationDate;
    
    public Reservation(Table table, String name, int pax, int contactNumber,Date reservationDate){
        this.table = table;
        this.name = name;
        this.pax = pax;
        this.contactNumber = contactNumber;
        this.reservationDate = reservationDate;
        table.setVacant(false);
    }
    
	public Table getTable() {
		return table;
	}

	public int getPax() {
		return pax;
	}
	
	public String getName() {
		return name;
	}
	
	public int getContactNumber() {
		return contactNumber;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public void setPax(int pax) {
		this.pax = pax;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}


	public void setReservationDate(Date reservationDate) {  
		this.reservationDate = reservationDate;
	}
    
	
    



}
