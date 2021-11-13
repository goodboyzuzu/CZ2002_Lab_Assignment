package controller;

import static helperFunction.FormatAsTable.formatAsTable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import entity.Food;
import entity.MenuItem;
import entity.PromotionalPackage;
import entity.Reservation;
import entity.Table;
import helperFunction.PopulateDB;
//import ui.reservationDate;

// Barn added
public class ReservationController {
	public static final long MAX_RESERVATION_DURATION=60*5; //seconds
	public static ArrayList<Reservation> reservationArrayList = PopulateDB.reservationArrayList;
    public static ArrayList<Table> tableArrayList =PopulateDB.tableArrayList;
    
	public static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");  
	public static SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");  
	
	
	public static void showReservationItem() {
        List<List<String>> rows = new ArrayList<>();
        List<String> header = Arrays.asList("Date", "Time","Time before Expire/s","is Serving","Table No." ,"Table Size" ,"Name", "Contact", "# pax");
        rows.add(header);
        for (Reservation item : reservationArrayList) {
            if ((item instanceof Reservation)) {
            	Date currDate=new Date();
                rows.add(Arrays.asList(
                		dateFormatter.format(item.getReservationDate()),
                		timeFormatter.format(item.getReservationDate()),
                		String.valueOf(MAX_RESERVATION_DURATION-(currDate.getTime()-item.getReservationDate().getTime())/1000),
                		Boolean.toString(item.getTable().isServing()),
                		String.valueOf(item.getTable().getTableNumber()),
                		String.valueOf(item.getTable().getSize()),
                		item.getName(), 
                		String.valueOf(item.getContactNumber()), 
                		String.valueOf(item.getPax())));
                
                
            }
        }

        System.out.println(formatAsTable(rows));
    }
	
	public static void addReservationItem(String name, int contact, int pax, Date reservationDate){
		Table min_dif_Table=findBestTable(pax);
		
		
        boolean contains = false;
        if(min_dif_Table.getTableNumber()==-1) {
        	contains = true;
        	System.out.println("no Tables available");
        }
        for (Reservation item : reservationArrayList) {
            if (item.getContactNumber() == contact) {
            	contains = true;
            	System.out.println("Reservation Contact is already in the use");
            }

        }
        if (contains == false) {
        	Reservation newReservation = new Reservation(min_dif_Table, name, pax, contact,reservationDate);
        	newReservation.getTable().setVacant(false);
        	reservationArrayList.add(newReservation);
        	System.out.println("Reservation for "+newReservation.getName() +
        			" with contact: "+String.valueOf(newReservation.getContactNumber())+" has been added\n"+
        			"Allocated Table:"+String.valueOf(newReservation.getTable().getTableNumber()));
        } 
	}
	
	public static void removeReservationItem(int contact){
		boolean contains = false;
		for(Iterator<Reservation> it = reservationArrayList.iterator(); it.hasNext();){
        	    Reservation reservationItem = it.next();
		    if(reservationItem.getContactNumber()==contact){
			String removed_name=reservationItem.getName();
			String removed_contact=String.valueOf(reservationItem.getContactNumber());
			reservationItem.getTable().setVacant(true);
			it.remove();
			System.out.println("Reservation for "+removed_name +" with contact: "+removed_contact+" has been removed");
			contains = true;
		    }
		}
		if (contains==false) {
			System.out.println("Contact does not Exist. No Reservation Removed");
		}
	}
	
	public static void editReservationItem(int contact) {
		Scanner sc = new Scanner(System.in);
		boolean contains = false;
		for (Reservation item : reservationArrayList) {
			if((item.getContactNumber())==contact){
				contains = true;
				
				List<List<String>> rows = new ArrayList<>();
		        List<String> header = Arrays.asList("Date2", "Time","Time before Expire/s","is Serving","Table No." ,"Table Size" ,"Name", "Contact", "# pax");
		        rows.add(header);
		        
                int choice = 0;
                int index = reservationArrayList.indexOf(item);
				if (item instanceof Reservation) {
					do {
						Date currDate=new Date();
						rows.add(Arrays.asList(
		                		dateFormatter.format(item.getReservationDate()),
		                		timeFormatter.format(item.getReservationDate()),
		                		String.valueOf(MAX_RESERVATION_DURATION-(currDate.getTime()-item.getReservationDate().getTime())/1000),
		                		Boolean.toString(item.getTable().isServing()),
		                		String.valueOf(item.getTable().getTableNumber()),
		                		String.valueOf(item.getTable().getSize()),
		                		item.getName(), 
		                		String.valueOf(item.getContactNumber()), 
		                		String.valueOf(item.getPax())));
						System.out.println(formatAsTable(rows));
						
						System.out.println("Which attribute do you want edit?");
						System.out.println("(1) Name");
						System.out.println("(2) Contact");
						System.out.println("(3) Pax");
						System.out.println("(4) Reschedule");
						System.out.println("(99) Go Back");
						
						choice = sc.nextInt();
						sc.nextLine();
						
						switch(choice) {
						case 1:
							System.out.println("Enter the new name");
							reservationArrayList.get(index).setName(sc.nextLine());
							System.out.println("Name updated");
							
							rows.remove(1);
							break;
						case 2:
							try {
								System.out.println("Enter the new Contact");
								int newContact=sc.nextInt();
						        if (String.valueOf(newContact).length()!=8) {
						        	throw new Exception("Invalid Contact Number!");
						        }
								boolean contains_newContact = false;
						        for (Reservation reservationItem : reservationArrayList) {
						            if (reservationItem.getContactNumber() == newContact) {
						            	contains_newContact = true;
						            	System.out.println("Reservation Contact is already in the use");
						            }
	
						        }
						        if (contains_newContact == false) {
									reservationArrayList.get(index).setContactNumber(newContact);
									System.out.println("Contact updated");
						        } 
	
								
								
							}
					    	catch (Exception e) {
					    		if (e.getMessage()==null) {
					    			System.out.println("Invalid Input");
					    		}else {
					    			System.out.println(e.getMessage());
					    		}
					    		
					    		System.out.println("Reservation Denied\n");
					    	}
							rows.remove(1);
							break;
						case 3:
							try {
								System.out.println("Enter the new Pax");
						        int pax = sc.nextInt();
						        if (pax>10) {
						        	throw new Exception("Max Capacity per table is 10!");
						        }  else if (pax<=0) {
						        	throw new Exception("Invalid Pax number!");
						        }
						        Table min_dif_Table=findBestTable(pax);
						        if(!reservationArrayList.get(index).getTable().equals(min_dif_Table) &&
						        		min_dif_Table.getTableNumber()!=-1) {
						        	reservationArrayList.get(index).getTable().setVacant(true);
						        	min_dif_Table.setVacant(false);
						        	reservationArrayList.get(index).setTable(min_dif_Table);
									reservationArrayList.get(index).setPax(pax);
									System.out.println("Pax updated");
						        }else if(reservationArrayList.get(index).getTable().getSize()>=pax){
						        	reservationArrayList.get(index).setPax(pax);
						        	System.out.println("Pax updated");
						        }
						        else if(min_dif_Table.getTableNumber()==-1){
						        	System.out.println("no Tables available");
						        }

								sc.nextLine();	
							}
				
					    	catch (Exception e) {
					    		if (e.getMessage()==null) {
					    			System.out.println("Invalid Input");
					    		}else {
					    			System.out.println(e.getMessage());
					    		}
					    		
					    		System.out.println("Reservation Edit Denied\n");
					    	}
							rows.remove(1);
							break;
						case 4:
							reservationArrayList.get(index).setReservationDate(new Date());
							System.out.println("Reservation Rescheduled\n");
							rows.remove(1);
							break;
						case 99:
							break;
						default:
							System.out.println("Please input a different number\n");
						}
						
					} while (choice != 99);
				} 
			}
		}
		if (contains == false) System.out.println("Item not found");
	}
	
	public static void checkExpiredReservation(){
		Date currDate=new Date();
		long difference;
        
		//Only remove the reservation if table is not occupied
        for(Iterator<Reservation> it = reservationArrayList.iterator(); it.hasNext();){
        	Reservation reservationItem = it.next();
        	difference= (currDate.getTime()-reservationItem.getReservationDate().getTime())/1000;
            if(difference>MAX_RESERVATION_DURATION && !reservationItem.getTable().isServing()){
            	String removed_name=reservationItem.getName();
            	String removed_contact=String.valueOf(reservationItem.getContactNumber());
            	reservationItem.getTable().setVacant(true);
                it.remove();
                System.out.println("Reservation for "+removed_name +" with contact: "+removed_contact+" has been removed");
            }
        }

	}
	
	public static void optimiseReservation(){
		for (Reservation reservationItem : reservationArrayList) {
			int min_diff=reservationItem.getTable().getSize()-reservationItem.getPax();
			Table min_dif_Table=findBestTable(reservationItem.getPax());
			
            if (!reservationItem.getTable().equals(min_dif_Table) &&              //if the new min_diff_Table is different from original table
            		min_dif_Table.getTableNumber()!=-1 &&                         //if min_diff_Table is not Table number -1
            		min_diff> (min_dif_Table.getSize()-reservationItem.getPax())) //if the new table has smaller difference than existing
            {
            	reservationItem.getTable().setVacant(true);
            	min_dif_Table.setVacant(false);
            	reservationItem.setTable(min_dif_Table);
            }
        }
		printVacantTables();

	}
	
	public static void printVacantTables(){
		
		List<List<String>> rows = new ArrayList<>();
        List<String> header = Arrays.asList("Table Avail", "Table Size");
        rows.add(header);
		for (Table tableItem:tableArrayList) {
			if (tableItem.isVacant() && tableItem instanceof Table) {
                rows.add(Arrays.asList(
                		String.valueOf(tableItem.getTableNumber()),
                		"("+String.valueOf(tableItem.getSize())+")"
                		));
			}
		}

        System.out.println(formatAsTable(rows));
        
	}
	
	public static Table findBestTable(int pax) {
		int min_diff=1000;
		Table min_dif_Table=new Table(20, -1);//just to initalise a random table
		
		for (Table tableItem:tableArrayList) {
			if (tableItem.isVacant()) {
				int diff=tableItem.getSize()-pax;
				if(diff<min_diff && diff>=0) {
					min_diff=diff;
					min_dif_Table=tableItem;
				}
			}
		}

		return min_dif_Table;
	}

	
	
	


}
