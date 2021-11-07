package Controller;
import Entity.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ArrayList;

public class ReservationController {
    //region Scanner
    Scanner sc = new Scanner(System.in);
    //endregion

    /**
     * checkRemoveReservationBooking Method:
     * It will first check if the reservation id enter exists
     * then compares the reservation date time with current date time
     * if the difference is more than 20 minutes, the reservation will be cancled
     * and table will be released
     * if the result is less than 20 minutes which means that the customer arrives in time
     * The reservarion will convert to orders with the convertResToOrder method
     * where staff will choose  available table  for the customer
     */
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
            if(sameDate>0){
                System.out.println("Before the valid Time of Reservation");
            }
            else if(sameDate==0 && after){
                System.out.println("Before the valid Time of Reservation");
            }
            else{
            System.out.println("This reservation is overdue!");
            deleteReservation(id);}
        }
    }

    /**
     * printReservationList Method:
     * The printReservationList Method creates an arraylist of Reservation objects with values
     * retrieved from Reservation.Dat file with the Database_Controller. If the ArrayList is not empty,
     * All entries in the Reservation will be printed out with a for loop
     *
     */
    public void printReservationList() {
        ArrayList<Reservation> Reservation = Database_Controller.readReservationList();
        if (Reservation != null) {
            System.out.println("Reservation Id" + "\t" + " Table " + "\t" + " Customer"+ "\t" + " Date"+ "\t" + " Time"+ "\t" + " Pax");
            for (int i = 0; i < Reservation.size(); i++) {
                System.out.println(Reservation.get(i).getId() + "\t\t\t\t\t" + Reservation.get(i).getTableId()
                        + "\t\t\t\t" + Reservation.get(i).getCustomerName()+ "\t\t\t\t" + Reservation.get(i).getDate()
                        + "\t\t\t\t" + Reservation.get(i).getTime()+ "\t\t\t\t" + Reservation.get(i).getPax());
            }
        }
    }

    /**
     * createReservation Method:
     * The createReservation Method will create a Reservation object
     * with id, tableId, customerName, Date, Time, pax
     * Furthermore, a table that have the capacity to server the pax of customer
     * will be reserved for this reservation
     */
    public void createReservation() {
        System.out.println("Add a Reservation");
        System.out.println("---------------------");
        System.out.println("Enter the Id of the Reservation:");
        int id = sc.nextInt();
        if (Database_Controller.getReservationById(id) != null) {
            System.out.println("Reservation already exists!");
            return;
        } else {

            System.out.println("Enter pax:");
            int pax=sc.nextInt();
            TableController TableController= new TableController();
            TableController.printAvailableTables(pax);
            System.out.println("Enter Table Id of choices:");
            int tableId=sc.nextInt();

            System.out.println("Enter customerName:");
            CustomerController CustomerController= new CustomerController();
            CustomerController.printCustomerDetails();
            String customerName= sc.next();
            int check=1;
            if(Database_Controller.getReservationByCustomerName(customerName)!=null){
                System.out.println("You have reserve a table!");
                check=0;
            }

            if(check==1) {
                System.out.println("Enter a date [dd-MM-yyyy]: ");
                String str = sc.next();
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate Date = LocalDate.parse(str, df);

                System.out.println("Enter a time  hh:mm:ss: ");
                String time = sc.next();  //default format: hh:mm:ss
                LocalTime Time = LocalTime.parse(time);


                Table table = Database_Controller.getTableById(tableId);
                table.setReserved(true);
                Database_Controller.updateTable(table);

                Reservation Reservation = new Reservation(id, tableId, customerName, Date, Time, pax);
                Database_Controller.addReservation(Reservation);
            }
            else{
                checkRemoveReservationBooking();
            }

        }
    }

    /**
     * deleteReservation method:
     * The deleteReservation Method will request user to input the reservation Id
     * to be deleted. It will first check the existance of the reservation with the
     * getReservationById method from the Database Controller and if the object exist,
     * it will activate the deleteReservation method from the Database Controller with
     * passing in the Reservation Id
     */
    public void deleteReservation(int Number) {
        System.out.println("Remove a Reservation");
        System.out.println("---------------------");
        Table table= Database_Controller.getTableById(Database_Controller.getReservationById(Number).getTableId());
        table.setReserved(false);
        Database_Controller.updateTable(table);
        Database_Controller.deleteReservation(Number);// remove the Reservation from the database
        System.out.println("Reservation removed!");


    }


}
