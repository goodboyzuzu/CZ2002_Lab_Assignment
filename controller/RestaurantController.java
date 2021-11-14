package controller;

import ui.*;
import helperFunction.PopulateDB;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RestaurantController {
	
	private MenuUI menuUI;
	private OrderUI orderUI;
	private ReservationUI reservationUI;

	public RestaurantController() {
        menuUI = new MenuUI();
        orderUI = new OrderUI();
        reservationUI = new ReservationUI();
    }
	
	public void chooseFunction(int choice) {

        switch (choice) {
            case 1:
                menuUI.showMenuOptions();
                break;
            case 2:
                orderUI.showOrderOptions();
                break;
            case 3:
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                Date date = new Date();  
                System.out.println(formatter.format(date)); 
                reservationUI.showReservationOptions();
                break;
            case 99:
            	System.out.println("Terminating program...");
            	//PopulateDB.saveToDatabase();
        		System.exit(1);
            default:
            	System.out.println("Please input a different number\n");
        }
	}
}
