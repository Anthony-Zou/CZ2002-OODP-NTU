package Controller;
import Entity.*;
import java.io.*;
import java.util.*;
public class Database_Controller {
    /**
     * constant to store the file path for storing the values in the object file .
     */

    private final static String tablePath="src/group6/Data/Table.Dat";
    private final static String menuItemPath="src/group6/Data/MenuItem.Dat";
    private final static String staffPath="src/group6/Data/Staff.Dat";
    private final static String promotionPath="src/group6/Data/Promotion.Dat";
    private final static String orderPath="src/group6/Data/Order.Dat";
    private final static String reservationPath="src/group6/Data/Reservation.Dat";
    private final static String revenuePath="src/group6/Data/revenue.Dat";
    private final static String testPath="src/group6/Data/Table.Dat";



    public static void writeList(List list, String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            //ioe.printStackTrace();
        }

    }
    public static List readList(String path) {
        List list = null;
        FileInputStream fis;
        try {
            fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList)ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //System.out.println("ADMIN NOT FOUND, NOT ATTEMPTING TO LOAD");
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }





    public static void writeTableList(ArrayList<Table> TableList){

        writeList(TableList,tablePath);
    }
    public static ArrayList<Table> readTableList()
    {
        ArrayList<Table> TableList;
        TableList=(ArrayList)readList(tablePath);
        return TableList;
    }
    public static Table getTableById(int number) {
        ArrayList<Table> tableList = (ArrayList<Table>)readTableList();
        for (int i = 0 ; i < tableList.size() ; i++) {
            Table table = (Table)tableList.get(i);
            if (table.getId()==(number))
            {
                return table;
            }
        }
        return null;
    }
    public static void addTable(Table table)
    {
       ArrayList<Table> tablelist = (ArrayList<Table>)readTableList();
       // ArrayList<Table> tablelist = new ArrayList<Table>();
        tablelist.add(table);
        writeTableList(tablelist);

    }


    public static void deleteTable(int tableNumber) {
        ArrayList<Table> TableList = (ArrayList<Table>)readTableList();
        for (int i = 0 ; i < TableList.size() ; i++) {
            Table Table = (Table)TableList.get(i);
            if (Table.getId()==tableNumber)
            {
                TableList.remove(i);
                break;
            }
        }
        writeTableList(TableList);
    }
    public static void updateTable(Table table)
    {
        deleteTable(table.getId());
        addTable(table);
    }
    public static void updateTableReserved(Table table)
    {
        deleteTable(table.getId());
        addTable(table);
    }
}
