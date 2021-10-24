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


    //basic function group
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




    //function group for table management
    public static void writeTableList(ArrayList<Table> TableList){

        writeList(TableList,tablePath);
    }

    public static ArrayList<Table> readTableList() {
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

    public static void addTable(Table table) {
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

    public static void updateTable(Table table) {
        deleteTable(table.getId());
        addTable(table);
    }


    //function group for Staff management

    public static void writeStaffList(ArrayList<Staff> StaffList){

        writeList(StaffList,staffPath);
    }

    public static ArrayList<Staff> readStaffList() {
        ArrayList<Staff> StaffList;
        StaffList=(ArrayList)readList(staffPath);
        return StaffList;
    }

    public static Staff getStaffByEmployeeId(int employeeId) {
        ArrayList<Staff> StaffList = (ArrayList<Staff>)readStaffList();
        for (int i = 0 ; i < StaffList.size() ; i++) {
            Staff Staff = (Staff)StaffList.get(i);
            if (Staff.getEmployeeId()==(employeeId))
            {
                return Staff;
            }
        }
        return null;
    }

    public static void addStaff(Staff Staff) {
        ArrayList<Staff> Stafflist = (ArrayList<Staff>)readStaffList();
         //ArrayList<Staff> Stafflist = new ArrayList<Staff>();
        Stafflist.add(Staff);
        writeStaffList(Stafflist);

    }

    public static void deleteStaff(int employeeId) {
        ArrayList<Staff> StaffList = (ArrayList<Staff>)readStaffList();
        for (int i = 0 ; i < StaffList.size() ; i++) {
            Staff Staff = (Staff)StaffList.get(i);
            if (Staff.getEmployeeId()==(employeeId))
            {
                StaffList.remove(i);
                break;
            }
        }
        writeStaffList(StaffList);
    }

    public static void updateStaff(Staff Staff) {
        deleteStaff(Staff.getEmployeeId());
        addStaff(Staff);
    }


    //function group for MenuItem management
    public static void writeMenuItemList(ArrayList<MenuItem> MenuItemList){

        writeList(MenuItemList,menuItemPath);
    }

    public static ArrayList<MenuItem> readMenuItemList() {
        ArrayList<MenuItem> MenuItemList;
        MenuItemList=(ArrayList)readList(menuItemPath);
        return MenuItemList;
    }

    public static MenuItem getMenuItemByName(String itemName) {
        ArrayList<MenuItem> MenuItemList = (ArrayList<MenuItem>)readMenuItemList();
        for (int i = 0 ; i < MenuItemList.size() ; i++) {
            MenuItem MenuItem = (MenuItem)MenuItemList.get(i);
            if (MenuItem.getItemName().equalsIgnoreCase(itemName))
            {
                return MenuItem;
            }
        }
        return null;
    }

    public static void addMenuItem(MenuItem MenuItem) {
        ArrayList<MenuItem> MenuItemlist = (ArrayList<MenuItem>)readMenuItemList();
        //ArrayList<MenuItem> MenuItemlist = new ArrayList<MenuItem>();
        MenuItemlist.add(MenuItem);
        writeMenuItemList(MenuItemlist);

    }

    public static void deleteMenuItem(String itemName) {
        ArrayList<MenuItem> MenuItemList = (ArrayList<MenuItem>)readMenuItemList();
        for (int i = 0 ; i < MenuItemList.size() ; i++) {
            MenuItem MenuItem = (MenuItem)MenuItemList.get(i);
            if (MenuItem.getItemName().equalsIgnoreCase(itemName))
            {
                MenuItemList.remove(i);
                break;
            }
        }
        writeMenuItemList(MenuItemList);
    }

    public static void updateMenuItem(MenuItem MenuItem) {
        deleteMenuItem(MenuItem.getItemName());
        addMenuItem(MenuItem);
    }
}
