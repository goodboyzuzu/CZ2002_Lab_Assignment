package entity;

import java.util.Date;

public class Table {
    private int size;
    private int tableNumber;
    private boolean isVacant;
    private Date reservationDate;

    public Table(int size, int tableNumber){
        this.size = size;
        this.tableNumber = tableNumber;
        this.isVacant = true;
        this.reservationDate = null;
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
}
