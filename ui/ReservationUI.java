package ui;

import java.util.Date;
import java.util.Scanner;

import controller.*;

public class ReservationUI {
	private static ReservationController reservationCtrl;
	
	public void showReservationOptions() {
		int menuChoice = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("(1) View Reservation");
			System.out.println("(2) Add Reservation");
			System.out.println("(3) Remove Reservation");
			System.out.println("(4) Edit Reservation");
			System.out.println("(99) Go back to main menu");
			
			menuChoice = sc.nextInt();
			sc.nextLine();
			switch(menuChoice) {
			case 1:
				//System.out.println("chosen"+menuChoice);
				reservationCtrl.checkExpiredReservation();
				reservationCtrl.optimiseReservation();
				reservationCtrl.showReservationItem();
				break;
			case 2:
				//System.out.println("chosen"+menuChoice);
				reservationCtrl.checkExpiredReservation();
				reservationCtrl.optimiseReservation();
				addReservationItem();
				break;
			case 3:
				//System.out.println("chosen"+menuChoice);
				reservationCtrl.checkExpiredReservation();
				reservationCtrl.optimiseReservation();
				removeReservationItem();
				break;
			case 4:
				//System.out.println("chosen"+menuChoice);
				editReservationItem();
				reservationCtrl.checkExpiredReservation();
				reservationCtrl.optimiseReservation();
				break;
			case 99:
				break;
			default:
				System.out.println("Please input a different number\n");
			}
		} while (menuChoice != 99);
	}
	
    private void addReservationItem() {
    	Scanner sc = new Scanner(System.in);
    	try {
    		System.out.println("Enter Name");
	        String Name = sc.nextLine();
	        System.out.println("Enter Contact");
	        int contact = sc.nextInt();
	        if (String.valueOf(contact).length()!=8) {
	        	throw new Exception("Invalid Contact Number!");
	        }
	        System.out.println("Enter pax #");
	        int pax = sc.nextInt();
	        if (pax>10) {
	        	throw new Exception("Max Capacity per table is 10!");
	        }  else if (pax<=0) {
	        	throw new Exception("Invalid Pax number!");
	        }
	        sc.nextLine();
	        reservationCtrl.addReservationItem(Name, contact, pax, new Date());
    	}
    	catch (Exception e) {
    		if (e.getMessage()==null) {
    			System.out.println("Invalid Input");
    		}else {
    			System.out.println(e.getMessage());
    		}
    		
    		System.out.println("Reservation Denied\n");
    	}

        
    }
    
    private void removeReservationItem() {
    	Scanner sc = new Scanner(System.in);
    	try {
	        System.out.println("Enter Contact");
	        int contact = sc.nextInt();
	        
	        if (String.valueOf(contact).length()!=8) {
	        	throw new Exception("Invalid Contact Number!");
	        }
	        sc.nextLine();
	        reservationCtrl.removeReservationItem(contact);
	        reservationCtrl.optimiseReservation();
    	}
    	catch (Exception e) {
    		if (e.getMessage()==null) {
    			System.out.println("Invalid Input");
    		}else {
    			System.out.println(e.getMessage());
    		}
    		
    		System.out.println("Reservation Denied\n");
    	}

        
    }
    private void editReservationItem() {
    	Scanner sc = new Scanner(System.in);
    	
    	try {
	    	System.out.println("Enter the contact of Reservation");
	    	int contact = sc.nextInt();
	        if (String.valueOf(contact).length()!=8) {
	        	throw new Exception("Invalid Contact Number!");
	        }
	    	reservationCtrl.editReservationItem(contact);
    	}
    	catch (Exception e) {
    		if (e.getMessage()==null) {
    			System.out.println("Invalid Input");
    		}else {
    			System.out.println(e.getMessage());
    		}
    		
    		System.out.println("Reservation Denied\n");
    	}
    }
	

}
