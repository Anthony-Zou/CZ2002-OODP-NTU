package Controller;

import Entity.Order;
import Entity.Table;

import java.util.ArrayList;
import java.util.Scanner;

public class TableController {
    //region Scanner
    Scanner sc = new Scanner(System.in);
    //endregion

    /**
     * With input value of
     * @param TableNumber
     * the reservation status of the Table will be given in
     * @return
     */
    public boolean checkTableAvailabitity(int TableNumber) {
        return Database_Controller.getTableById(TableNumber).isReserved();
    }

    /**
     * With input of number of customer
     * @param pax
     * Tables that have enough capacity to serve the customer will be printed
     */
    public void printAvailableTables(int pax) {
        ArrayList<Table> Table = Database_Controller.readTableList();
        if (Table != null) {
            System.out.println("Table Id" + "\t" + " Table Capacity" + "\t" + " Table Reserved");
            for (int i = 0; i < Table.size(); i++) {
                if(Table.get(i).isReserved()==false && Table.get(i).getCapacity()>=pax) {
                    System.out.println(Table.get(i).getId() + "\t\t\t\t\t" + Table.get(i).getCapacity() + "\t\t\t\t" + Table.get(i).isReserved());
                }
            }
        }
    }

    /**
     * printTableDetails Method:
     * The printTableDetails Method creates an arraylist of Table objects with values
     * retrieved from Table.Dat file with the Database_Controller. If the ArrayList is not empty,
     * All entries in the Table will be printed out with a for loop
     *
     */
    public void printTableDetails() {
        ArrayList<Table> Table = Database_Controller.readTableList();
        if (Table != null) {
            System.out.println("Table Id" + "\t" + " Table Capacity" + "\t" + " Table Reserved");
            for (int i = 0; i < Table.size(); i++) {
                System.out.println(Table.get(i).getId() + "\t\t\t\t\t" + Table.get(i).getCapacity() + "\t\t\t\t" + Table.get(i).isReserved());

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
    public void addTable() {
        System.out.println("Add a Table");
        System.out.println("---------------------");
        //System.out.println("Enter the Number of the Table:");
        int TableNumber = 1;
        ArrayList<Table> presentTables = Database_Controller.readTableList();
        if (presentTables != null) {
            TableNumber = presentTables.size() + 1;
        }
        System.out.println("Capacity of Table:");
        int capacity = sc.nextInt();
        while(true) {
            if (capacity % 2 == 1 || capacity > 10 || capacity < 2) {
                System.out.println("Invalid Capacity,Enter again!");
                System.out.println("Capacity of Table:");
                capacity = sc.nextInt();
            }
            else{
                break;
            }
        }

        Table newtable = new Table(TableNumber, capacity, false);
        Database_Controller.addTable(newtable);

        printTableDetails();
    }

    /**
     * deleteTable method:
     * The deleteTable Method will request user to input the Table Id
     * to be deleted. It will first check the existence of the Staff with the
     * getTableById method from the Database Controller and if the object exist,
     * it will activate the deleteTable method from the Database Controller with
     * passing in the Table Id
     */
    public void deleteTable() {
        System.out.println("Remove a Table");
        System.out.println("---------------------");
        // find if the Table is in the database or not //
        System.out.println("Enter the number of the Table:");
        int TableNumber = sc.nextInt();
        if (Database_Controller.getTableById(TableNumber) == null) {
            System.out.println("Table does not exist!");

        } else {
            Database_Controller.deleteTable(TableNumber);// remove the Table from the database
            System.out.println("Table removed!");

        }
    }

    /**
     * updateTable Method:
     * with passing value of
     * @param TableNumber , the method will first check of the object existance
     * with getTableById  method from the Database Controller
     * If the object exist, User can update the boolean reserved of the Table
     * object. Finally, the updated object will be passed to the
     * updateTable method in the database controller to update the Table.Dat file
     */
    public void updateTable(int TableNumber) {

        if (Database_Controller.getTableById(TableNumber) == null) {
            System.out.println("Table does not exist!");
        } else {
            Table table = Database_Controller.getTableById(TableNumber);
            if(!table.isReserved()){
                table.setReserved(true);}
            else if(table.isReserved()){ table.setReserved(false);}
            Database_Controller.updateTable(table);
        }
    }
}