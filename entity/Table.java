package entity;

import java.io.Serializable;
import java.util.Date;

public class Table implements Serializable{
    private int size;
    private int tableNumber;
    private boolean isVacant;
    private boolean isServing;
    private Date reservationDate;

    public Table(int size, int tableNumber){
        this.size = size;
        this.tableNumber = tableNumber;
        this.isVacant = true;
        this.reservationDate = null;
        this.isServing = false;
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
    
    public boolean isServing() {
        return isServing;
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
    
    public void setServing(boolean serving) {
        isServing = serving;
    }
}
