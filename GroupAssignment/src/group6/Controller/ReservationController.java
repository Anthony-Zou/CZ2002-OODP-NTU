package Controller;
import Entity.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ArrayList;

public class ReservationController {


    Scanner sc = new Scanner(System.in);
    public void checkRemoveReservationBooking() {
        String resT = "00:20:00";
        LocalTime reservationT = LocalTime.parse(resT);
        int id;
        System.out.println("Enter your reservation ID:");
        id = sc.nextInt();
        LocalTime time = Database_Controller.getReservationById(id).getTime();

        //boolean before =
    }

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

            System.out.println("Enter a date [dd-MMM-yyyy]: ");
            String str = sc.next();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("d-MMM-yyyy");
            LocalDate Date =  LocalDate.parse(str, df);

            System.out.println("Enter a time  hh:mm:ss: ");
            String time=sc.next();  //default format: hh:mm:ss
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
        System.out.println("Enter the number of the Reservation:");
        int Number = sc.nextInt();
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
//
    ReservationController ReservationController=new ReservationController();
      // ReservationController.printReservationList();
       //ReservationController.createReservation();
        ReservationController.deleteReservation();
        ReservationController.printReservationList();
        ReservationController.createReservation();
        TableController TableController = new TableController();
        TableController.printTableDetails();
//
    }

}
