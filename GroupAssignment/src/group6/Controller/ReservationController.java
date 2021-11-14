package Controller;

import Entity.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.ArrayList;
/**
 Controller for Reservation that hold methods of delete Overdue Bookings, check Reservation Bookings, print Reservation List,
 Create Resrevation, Delete Reservation, and Populate Reservation
 @author Zou Zeren
 @version 1.0
 @since 24-22-2021
 */
public class ReservationController {
    //region Scanner
    Scanner sc = new Scanner(System.in);
    //endregion

    /**
     * Releases tables that were reserved but the reservations are overdue.
     * Overdue reservations are deleted.
     */
    public void deleteOverdueBookings() {
        ArrayList<Reservation> resList = Database_Controller.readReservationList();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        Reservation res = new Reservation();
        Table table = new Table();
        for (int i = 0; i < resList.size(); i++) {
            res = resList.get(i);
            if (date.isAfter(res.getDate()) || (date.isEqual(res.getDate()) && time.isAfter(res.getTime().plusMinutes(5)))) {
                table = Database_Controller.getTableById(res.getTableId());
                table.setReserved(false);
                Database_Controller.updateTable(table);
                Database_Controller.deleteReservation(res.getId());
            }
        }
    }

    /**
     * Checks if customer has a valid reservation. If there is a valid reservation,
     * compares the reservation date & time with the current date & time.
     * If the customer arrives more than 20 minutes before the booked timing,
     * they will not be allowed to order yet.
     * If the customer arrives in time, +/- 20 minutes from the booked timing,
     * the reservation will be converted to an order via the <code>convertResToOrder</code> method
     * where staff will assign the reserved table to the customer.
     * If customer arrives more than 20 minutes after the booked timing,
     * the reservation would already have been deleted.
     * @param name
     */
    public void checkReservationBooking(String name) {
        deleteOverdueBookings();
        Reservation res = Database_Controller.getReservationByCustomerName(name);

        // If customer has no valid reservation/reservation overdue and deleted
        if (res == null) {
            System.out.println("Reservation does not exist!");
            return;
        }

        LocalDate date = res.getDate();
        LocalDate today = LocalDate.now();
        int sameDate = date.compareTo(today);
        LocalTime time = res.getTime();
        LocalTime afterTime = time.plusMinutes(5);
        LocalTime beforeTime = time.minusMinutes(20);
        LocalTime now = LocalTime.now();
        boolean before = afterTime.isBefore(now);
        boolean after = beforeTime.isAfter(now);

        if (!before && !after && sameDate == 0) {
            // If customer is not too early or too late, within +/- 20 min of booked timing
            System.out.println("Welcome! Reservation is available.");
            OrderController orderController = new OrderController();
            orderController.convertResToOrder(res);
            deleteReservation(res.getId());
        } else {
            // If customer arrives too early, > 20 min before booked timing
            if (sameDate > 0) {
                System.out.println("Before the valid Time of Reservation");
            } else if (sameDate == 0 && after) {
                System.out.println("Before the valid Time of Reservation");
            }
        }
    }

    /**
     * printReservationList Method:
     * The printReservationList Method creates an arraylist of Reservation objects with values
     * retrieved from Reservation.Dat file with the Database_Controller. If the ArrayList is not empty,
     * All entries in the Reservation will be printed out with a for loop
     */
    public void printReservationList() {
        deleteOverdueBookings();
        ArrayList<Reservation> Reservation = Database_Controller.readReservationList();
        if (Reservation != null) {
            System.out.println("Reservation #    Table #     Customer         Date           Time           Pax");
            System.out.println("------------------------------------------------------------------------------------");
            for (int i = 0; i < Reservation.size(); i++) {
                if (Reservation.get(i).getTableId() == -1)
                    continue;
//                System.out.println(Reservation.get(i).getId() + "\t\t\t\t\t" + Reservation.get(i).getTableId()
//                        + "\t\t\t\t" + Reservation.get(i).getCustomerName() + "\t\t\t\t" + Reservation.get(i).getDate()
//                        + "\t\t\t\t" + Reservation.get(i).getTime() + "\t\t\t\t" + Reservation.get(i).getPax());

                System.out.printf("%-17d%-12d%-17s%-15s%-15s%d\n", Reservation.get(i).getId(), Reservation.get(i).getTableId(), Reservation.get(i).getCustomerName(),Reservation.get(i).getDate()
                        , Reservation.get(i).getTime(), Reservation.get(i).getPax());
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
//        printReservationList();

        // If all tables are fully reserved, exit this operation
        TableController TableController = new TableController();
        if(!TableController.printAvailableTables(2))
            return;

        int check;
        do{
            try {
                System.out.println("Add a Reservation");
                System.out.println("---------------------");

                int id = 1;
                ArrayList<Reservation> presentReservation = Database_Controller.readReservationList();
                if (presentReservation != null) {
                   if( presentReservation.size()>=1){
                    id = presentReservation.get(presentReservation.size()-1).getId() + 1;}
                }

                // Enter pax
                int pax;
                do {
                    System.out.println("Enter pax:");
                    pax = sc.nextInt();
                    if (pax < 2 || pax > 10) {
                        System.out.println("Please enter pax from 2 to 10!");
                    }
                } while (pax < 2 || pax > 10);

                // Show available tables for given pax
                 TableController = new TableController();
                if (!TableController.printAvailableTables(pax)){
                    System.out.println("No tables available at the moment!");
                    return;
                }

                // Choose table
                int tableId;
                do {
                    System.out.println("Enter Table Id of choice:");
                    tableId = sc.nextInt();
                    if (!TableController.listAvailableTables(pax).contains(tableId)) {
                        System.out.println("Table " + tableId + " unavailable! Please choose another table.");
                    }
                } while (!TableController.listAvailableTables(pax).contains(tableId));

                // Enter customer details
                System.out.println("Enter customerName:");
                CustomerController CustomerController = new CustomerController();
               // CustomerController.print();
                Scanner sc = new Scanner(System.in);
                String customerName = sc.nextLine();
                check = 1;
                if (Database_Controller.getReservationByCustomerName(customerName) != null) {
                    System.out.println("You have already reserved a table!");
                    check = 0;
                } else if (Database_Controller.getCustomerByName(customerName) == null) {
                    // Add customer if he/she is not in database yet
                    CustomerController.add();
                    // Retrieve new customer's name as per input during registration
                    ArrayList<Customer> Customers = Database_Controller.readCustomerList();
                    Customer newCustomer = Customers.get(Customers.size() - 1);
                    customerName = newCustomer.getName();
                }

                if (check == 1) {

                    // Enter reservation date
                    LocalDate Date;
                    LocalTime Time;
                    do {
                        do {
                            try {
                                System.out.println("Enter a date [DD-MM-YYYY]: ");
                                //sc.nextLine();
                                String str = sc.nextLine();
                                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                Date = LocalDate.parse(str, df);
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("Please input correct format!");
                            }
                            sc.nextLine();
                        } while(true);

                        if (Date.compareTo(LocalDate.now())!=0) {
                            System.out.println("Reservations can only be made today!");
                        }
                    } while (Date.compareTo(LocalDate.now())!=0);

                    // Enter reservation time
                    do {
                        do {
                            try {
                                System.out.println("Enter a time [hh:mm]: ");
                                String time = sc.nextLine();  //default format: hh:mm:ss
                                Time = LocalTime.parse(time);
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("Please input correct format!");
                            }
                            sc.nextLine();
                        } while(true);

                        if (Time.isAfter(LocalTime.now().plusMinutes(20))) {
                            System.out.println("Reservations must be made at most 20 minutes in advance. Choose an earlier time.");
                            return;
                        }
                        if (Time.isBefore(LocalTime.now())) {
                            System.out.println("Reservations must be made after current time. Choose a later time.");
                            return;
                        }
                    } while (Time.isBefore(LocalTime.now())&&Time.isAfter(LocalTime.now().plusMinutes(20)));

                    // Reserve table
                    Table table = Database_Controller.getTableById(tableId);
                    table.setReserved(true);
                    Database_Controller.updateTable(table);

                    // Save the new reservation
                    Reservation Reservation = new Reservation(id, tableId, customerName, Date, Time, pax);
                    Database_Controller.addReservation(Reservation);
                    System.out.println("Table " + tableId + " reserved for " + customerName + " at " + Date + " " + Time);
                } else {
                    checkReservationBooking(customerName);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a valid number!");
                System.out.println("\n-----------------------------------\n");
                check=-1;
            }
            sc.nextLine(); //clears buffer
        }while(check==-1);
    }

    /**
     * deleteReservation method:
     * The deleteReservation Method will request user to input the reservation Id
     * to be deleted. It will first check the existance of the reservation with the
     * getReservationById method from the Database Controller and if the object exist,
     * it will activate the deleteReservation method from the Database Controller with
     * passing in the Reservation Id
     * @param Number
     */
    public void deleteReservation(int Number) {
        System.out.println("Removing Reservation");
        System.out.println("---------------------");
        Reservation res = Database_Controller.getReservationById(Number);
        if(res == null){
            System.out.println("Reservation " + Number + " does not exist.");
            return;
        }
        Table table = Database_Controller.getTableById(res.getTableId());
        String Name=Database_Controller.getReservationById(Number).getCustomerName();
        table.setReserved(false);
        Database_Controller.updateTable(table);
        Database_Controller.deleteReservation(Number);// remove the Reservation from the database
        System.out.println("Reservation for "+Name+" is removed!");


    }

    /**
     * Methods that populate reservation with written time that is with in preset allowed range
     */
    public void populateReservation(){
        for (int i =0; i<19;i++){

            String name="Customer "+(i+1);
            Random rand = new Random();
            LocalDate Date;
            LocalTime Time;
            String str = "14-11-2021";
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            Date = LocalDate.parse(str, df);
            String time = "18:08";  //default format: hh:mm:ss
            Time = LocalTime.parse(time);
            Reservation Reservation = new Reservation((i+1), (i+1), name, Date, Time, 2);
            Table table = Database_Controller.getTableById(i+1);
            table.setReserved(true);
            Database_Controller.updateTable(table);
            Database_Controller.addReservation(Reservation);
         //  Database_Controller.deleteReservation(i+1);

        }
        printReservationList();

    }


}
