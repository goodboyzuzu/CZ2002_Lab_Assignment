package entity;

import java.util.Date;

public class Table {
    private int size;
    private int tableNumber;
    private boolean isVacant;
    private Date reservationDate;
    //Barn added
    private boolean isServing;
    //Barn end
    public Table(int size, int tableNumber){
        this.size = size;
        this.tableNumber = tableNumber;
        this.isVacant = true;
        this.reservationDate = null;
        //Barn added
        this.isServing=false;
        //Barn end
    }

    public int getSize() {
        return size;
    }
    
    public int getTableNumber() {
        return tableNumber;
    }

    public boolean isVacant() {
        return isVacant;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setVacant(boolean vacant) {
        isVacant = vacant;
    }
    //Barn added
	public boolean isServing() {
		return isServing;
	}

	public void setServing(boolean isServing) {
		this.isServing = isServing;
	}
	//Barn end
}
