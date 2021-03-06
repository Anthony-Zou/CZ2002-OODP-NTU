package Controller;

import Entity.Order;
import Entity.Reservation;
import Entity.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 Controller for Table consist of methods that print all, add delete and update table and its reservation status.
 Also, list and print tables that are not reserved at the point of time.
 @author Zou Zeren
 @version 1.0
 @since 24-22-2021
 */
public class TableController implements Controller{
    //region Scanner
    Scanner sc = new Scanner(System.in);
    //endregion

    /**
     * With input value of Table num
     * the reservation status of the Table will be given in
     * @param TableNumber
     * @return
     */
    public boolean checkTableAvailability(int TableNumber) {
        ReservationController ReservationController = new ReservationController();
        ReservationController.deleteOverdueBookings();
        return !Database_Controller.getTableById(TableNumber).isReserved();
    }

    /**
     * Tables that have enough capacity to serve the customer will be printed.
     * Every time this method is called, it will check if any reservation is overdue,
     * release that table if overdue, and print it as well.
     * Number of customers to the table  , return false if there are no available tables of the selected pax
     * @param pax
     * @return
     */
    public boolean printAvailableTables(int pax) {
        ReservationController ReservationController = new ReservationController();
        ReservationController.deleteOverdueBookings();
        ArrayList<Table> Table = Database_Controller.readTableList();
        int c = 0;

        if (Table != null) {
            System.out.printf("%-10s %-20s %-30s\n", "Table Id", "Table Capacity", "Table Reserved/Occupied");
            System.out.println("----------------------------------------------------------");
            for (int i = 0; i < Table.size(); i++) {
                if (!Table.get(i).isReserved() && Table.get(i).getCapacity() >= pax) {
                    System.out.printf("%-10s %-20s %-30s\n", Table.get(i).getId(), Table.get(i).getCapacity(), Table.get(i).isReserved());
                    c = 1;
                }
            }
        }
            if (c == 0) {
                System.out.println("No tables available at the moment.");
                return false;
            }
            else{
            return true;}
    }

    /**
     * Stores ID of tables that have enough capacity in an <code>ArrayList</code>.
     * PaxNumber of customers to the table
     * return <code>ArrayList</code> of the <code>tableId</code>s
     * @param pax
     * @return
     */
    public ArrayList<Integer> listAvailableTables(int pax) {
        ArrayList<Table> Table = Database_Controller.readTableList();
        ArrayList<Integer> availableTables = new ArrayList<Integer>();
        if (Table != null) {
            for (int i = 0; i < Table.size(); i++) {
                if (!Table.get(i).isReserved() && Table.get(i).getCapacity() >= pax) {
                    availableTables.add(Table.get(i).getId());
                }
            }
        }
        return availableTables;
    }

    /**
     * printTableDetails Method:
     * The printTableDetails Method creates an arraylist of Table objects with values
     * retrieved from Table.Dat file with the Database_Controller. If the ArrayList is not empty,
     * All entries in the Table will be printed out with a for loop
     */
    public void print() {
        ReservationController ReservationController = new ReservationController();
        ReservationController.deleteOverdueBookings();
        ArrayList<Table> Table = Database_Controller.readTableList();
        if (Table != null) {
            System.out.printf("%-10s %-20s %-30s\n", "Table Id", "Table Capacity", "Table Reserved/Occupied");
            System.out.println("----------------------------------------------------------");
            for (int i = 0; i < Table.size(); i++) {
                System.out.printf("%-10s %-20s %-30s\n", Table.get(i).getId(), Table.get(i).getCapacity(), Table.get(i).isReserved());
            }

        }
    }

    /**
     * addTable Method:
     * The addTable Method will create a Table object with
     * auto generated Table Id, capacity from user and the reserved status will be default false
     * Furthermore, the Table object will be written and save in to the Table.Dat file
     * With AddTable method in the Database controller
     */
    public void add() {
        System.out.println("Add a Table");
        System.out.println("---------------------");
        //System.out.println("Enter the Number of the Table:");
        int TableNumber = 1;
        ArrayList<Table> presentTables = Database_Controller.readTableList();
        if (presentTables != null) {
            TableNumber = presentTables.size() + 1;
        }

        int capacity = -1;
        do {
            try {
                System.out.println("Capacity of Table:");
                capacity = sc.nextInt();
                while (true) {
                    if (capacity % 2 == 1 || capacity > 10 || capacity < 2) {
                        System.out.println("Invalid Capacity,Enter again!");
                        System.out.println("Capacity of Table:");
                        capacity = sc.nextInt();
                    } else {
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a valid number! ");
                System.out.println("\n-----------------------------------\n");
                capacity = -1;
            }
            sc.nextLine();
        } while (capacity == -1);

        Table newtable = new Table(TableNumber, capacity, false);
        Database_Controller.addTable(newtable);

        print();
    }

    /**
     * deleteTable method:
     * The deleteTable Method will request user to input the Table Id
     * to be deleted. It will first check the existence of the Staff with the
     * getTableById method from the Database Controller and if the object exist,
     * it will activate the deleteTable method from the Database Controller with
     * passing in the Table Id
     */
    public void delete() {
        System.out.println("Remove a Table");
        System.out.println("---------------------");
        // find if the Table is in the database or not //
        int TableNumber;
        do {
            try {
                System.out.println("Enter the number of the Table:");
                TableNumber = sc.nextInt();
                if (Database_Controller.getTableById(TableNumber) == null) {
                    System.out.println("Table does not exist!");

                } else {
                    Database_Controller.deleteTable(TableNumber);// remove the Table from the database
                    System.out.println("Table removed!");

                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a valid number! ");
                System.out.println("\n-----------------------------------\n");
                TableNumber = -1;
            }
            sc.nextLine();
        } while (TableNumber == -1);
    }

    /**
     * updateTable Method:
     * with passing value of Table num
     * , the method will first check of the object existance
     *      *                    with getTableById  method from the Database Controller
     *      *                    If the object exist, User can update the boolean reserved of the Table
     *      *                    object. Finally, the updated object will be passed to the
     *      *                    updateTable method in the database controller to update the Table.Dat file
     * @param TableNumber
     */
    public void updateTable(int TableNumber) {

        if (Database_Controller.getTableById(TableNumber) == null) {
            System.out.println("Table does not exist!");
        } else {
            Table table = Database_Controller.getTableById(TableNumber);
            if (!table.isReserved()) {
                table.setReserved(true);
            } else if (table.isReserved()) {
                table.setReserved(false);
            }
            Database_Controller.updateTable(table);
        }
    }
}