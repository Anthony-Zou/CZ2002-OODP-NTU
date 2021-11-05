package Controller;

import Entity.Table;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

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
            System.out.println("Table Id" + "\t" + "Table Capacity" + "\t" + " Table Reserved");
            System.out.println("===============================================");
            for (int i = 0; i < Table.size(); i++) {
                if(Table.get(i).isReserved()==false && Table.get(i).getCapacity()>=pax) {
                    System.out.println(Table.get(i).getId() + "\t\t" + Table.get(i).getCapacity() + "\t\t" + Table.get(i).isReserved());
                }
            }
        }
    }

    public void printTableDetails() {
        ArrayList<Table> Table = Database_Controller.readTableList();
        if (Table != null) {
            System.out.println("Table Id" + "\t" + "Table Capacity" + "\t" + "Table Reserved");
            System.out.println("===============================================");
            for (int i = 0; i < Table.size(); i++) {
                System.out.println(Table.get(i).getId() + "\t\t" + Table.get(i).getCapacity() + "\t\t" + Table.get(i).isReserved());

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

        int capacity = 0;

        do {
        	try {
                System.out.println("Capacity of Table:");
                capacity = sc.nextInt();
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid capacity!");
        		System.out.println("\n-----------------------------------\n");
        	}
        	sc.nextLine(); // clears the buffer
        } while (capacity % 2 == 1 || capacity > 10 || capacity < 2);


        Table newtable = new Table(TableNumber, capacity, false);
        Database_Controller.addTable(newtable);

        printTableDetails();
    }
    
    public void deleteTable() {
        System.out.println("< Remove a Table >\n\n");
        
        // find if the Table is in the database or not //

        
        int TableNumber = 0;
        do {
        	try {
                System.out.println("Enter the Table Number: ");
                TableNumber = sc.nextInt();
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid Table Number!");
        		System.out.println("\n-----------------------------------\n");
        	}
        	sc.nextLine(); // clears the buffer
        } while (TableNumber == 0);
        
        
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
