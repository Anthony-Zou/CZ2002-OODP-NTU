package Controller;
import Entity.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReservationController {


    Scanner sc = new Scanner(System.in);
    public void checkRemoveReservationBooking() {
        int id;
        System.out.println("Enter your reservation ID:");
        while(true) {
            id = sc.nextInt();
            if(Database_Controller.getReservationById(id)!=null){
                break;
            }
            else{
                System.out.println("Reservation does not exist!");
            }
        }
        LocalDate date = Database_Controller.getReservationById(id).getDate();
        LocalDate today = LocalDate.now();
        int sameDate = date.compareTo(today);
        LocalTime time = Database_Controller.getReservationById(id).getTime();
        LocalTime afterTime=time.plusMinutes(20);
        LocalTime beforeTime=time.minusMinutes(20);
        LocalTime now=LocalTime.now();
        boolean before = afterTime.isBefore(now);
        boolean after  = beforeTime.isAfter(now);
        if(!before && !after && sameDate==0){
            System.out.println("Welcome!Reservation is available.");
            OrderController orderController= new OrderController();
            orderController.convertResToOrder(Database_Controller.getReservationById(id));
            deleteReservation(id);
        }
        else{
            System.out.println("This reservation is overdue!");
            deleteReservation(id);
        }
    }

    public void printReservationList() {
        ArrayList<Reservation> Reservation = Database_Controller.readReservationList();
        if (Reservation != null) {
            System.out.println("Reservation Id" + "\t" + " Table " + "\t" + " Customer"+ "\t" + " Date"+ "\t" + " Time"+ "\t" + " Pax");
            for (int i = 0; i < Reservation.size(); i++) {
                System.out.println(Reservation.get(i).getId() + "\t\t\t\t" + Reservation.get(i).getTableId()
                        + "\t\t\t\t" + Reservation.get(i).getCustomerName()+ "\t\t\t\t" + Reservation.get(i).getDate()
                        + "\t\t\t\t" + Reservation.get(i).getTime()+ "\t\t\t\t" + Reservation.get(i).getPax());
            }
        }
    }

    public void createReservation() {
        System.out.println("Add a Reservation");
        System.out.println("---------------------");
        
        int id = -1;
        do {
        	try {
        		System.out.println("Enter the Id of the Reservation:");
        		id=sc.nextInt();
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid number!");
        		System.out.println("\n-----------------------------------\n");
        	}
        	sc.nextLine(); // clears the buffer
        } while (id == -1);
        
        if (Database_Controller.getReservationById(id) != null) {
            System.out.println("Reservation already exists!");
            return;
        } else {

        	int pax = 0;
            do {
            	try {
            		System.out.println("Enter the number of pax:");
            		pax=sc.nextInt();
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter a valid number!");
            		System.out.println("\n-----------------------------------\n");
            	}
            	sc.nextLine(); // clears the buffer
            } while (pax == 0);
            
            TableController TableController= new TableController();
            TableController.printAvailableTables(pax);
            
            int tableId = 0;
            do {
            	try {
            		System.out.println("Enter Table ID of choices:");
            		tableId=sc.nextInt();
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter a valid ID!");
            		System.out.println("\n-----------------------------------\n");
            	}
            	sc.nextLine(); // clears the buffer
            } while (tableId == 0);

            
            CustomerController CustomerController= new CustomerController();
            CustomerController.printCustomerDetails();
            String customerName= sc.next();
            do {
            	try {
            		System.out.println("Enter customerName:");
            		customerName= sc.next();
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter a valid name!");
            		System.out.println("\n-----------------------------------\n");
            	}
            	sc.nextLine(); // clears the buffer
            } while (customerName == "");

            String str = "";
            do {
            	try {
            		System.out.println("Enter a date [dd-MM-yyyy]: ");
                    str = sc.next();
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter a valid date!");
            		System.out.println("\n-----------------------------------\n");
            	}
            	sc.nextLine(); // clears the buffer
            } while (str == "");
            
            
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate Date =  LocalDate.parse(str, df);

            String time = "";
            do {
            	try {
            		System.out.println("Enter a time  hh:mm:ss: ");//default format: hh:mm:ss
                    time = sc.next();
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter a valid date!");
            		System.out.println("\n-----------------------------------\n");
            	}
            	sc.nextLine(); // clears the buffer
            } while (str == "");
            
            
            LocalTime Time =LocalTime.parse(time);


            Table table= Database_Controller.getTableById(tableId);
            table.setReserved(true);
            Database_Controller.updateTable(table);

            Reservation Reservation = new Reservation(id,tableId,customerName,Date,Time,pax);
            Database_Controller.addReservation(Reservation);

        }
        printReservationList();
    }

    public void deleteReservation() {
        System.out.println("Remove a Reservation");
        System.out.println("---------------------");
        // find if the Reservation is in the database or not //
        
        int Number = 0;
        do {
        	try {
        		System.out.println("Enter the number of the Reservation:");
        		Number = sc.nextInt();
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid number!");
        		System.out.println("\n-----------------------------------\n");
        	}
        	sc.nextLine(); // clears the buffer
        } while (Number == 0);
        
        if (Database_Controller.getReservationById(Number) == null) {
            System.out.println("Reservation does not exist!");

        } else {
            Table table= Database_Controller.getTableById(Database_Controller.getReservationById(Number).getTableId());
            table.setReserved(false);
            Database_Controller.updateTable(table);
           Database_Controller.deleteReservation(Number);// remove the Reservation from the database
            System.out.println("Reservation removed!");

        }
    }


    public static void main(String[] args) {

    ReservationController ReservationController=new ReservationController();
      // ReservationController.printReservationList();
       //ReservationController.createReservation();
//        ReservationController.deleteReservation();
//        ReservationController.printReservationList();
        // ReservationController.createReservation();
        // TableController TableController = new TableController();
        // TableController.printTableDetails();

    }

}
