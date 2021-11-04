package Controller;

import Entity.Order;
import Entity.Table;

import java.util.ArrayList;
import java.util.Scanner;

public class TableController {

    Scanner sc = new Scanner(System.in);

    public void checkTableAvailabitity() {
    }

    public boolean checkTableAvailabitity(int TableNumber) {
        return Database_Controller.getTableById(TableNumber).isReserved();
    }

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

    public void printTableDetails() {
        ArrayList<Table> Table = Database_Controller.readTableList();
        if (Table != null) {
            System.out.println("Table Id" + "\t" + " Table Capacity" + "\t" + " Table Reserved");
            for (int i = 0; i < Table.size(); i++) {
                System.out.println(Table.get(i).getId() + "\t\t\t\t\t" + Table.get(i).getCapacity() + "\t\t\t\t" + Table.get(i).isReserved());

            }
        }
    }

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


    public static void main(String[] args) {
        TableController Promotion = new TableController();
        //   Promotion.addPromotion(); Promotion.addPromotion(); Promotion.addPromotion(); Promotion.addPromotion(); Promotion.addPromotion();
        //Promotion.UpdatePromotion();
        //Promotion.DeletePromotion();
        Promotion.printTableDetails();
        //Promotion.printPromotionById(1);
    }
}