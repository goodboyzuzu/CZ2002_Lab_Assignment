package controller;

import ui.*;

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
            case 1: //view menu
                menuUI.showMenuOptions();
                break;
            case 2:
                orderUI.showOrderOptions();
            	System.out.println("Order WIP");
                break;
            case 3:
                //reservationUI.showReservationOptions();
            	System.out.println("Reservation WIP");
                break;
            case 99:
            	System.out.println("Terminating program...");
        		System.exit(1);
            default:
            	System.out.println("Please input a different number\n");
        }
	}
}
