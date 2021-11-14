package Controller;

import Entity.*;
import java.io.*;
import java.util.*;
/**
 Controller for Database write list and read list for Controller{ Customer, MenuItem, Order, Promotion, Reservation. , Staff, Table}
 @author Zou Zeren
 @version 1.0
 @since 24-22-2021
 */
public class Database_Controller {

    /**
     * constant to store the file path for storing the values in the object file .
     */
    //region File link src
    private final static String tablePath="src/group6/Data/Table.Dat";
    private final static String menuItemPath="src/group6/Data/MenuItem.Dat";
    private final static String staffPath="src/group6/Data/Staff.Dat";
    private final static String promotionPath="src/group6/Data/Promotion.Dat";
    private final static String orderPath="src/group6/Data/Order.Dat";
    private final static String reservationPath="src/group6/Data/Reservation.Dat";
    private final static String revenuePath="src/group6/Data/Revenue.Dat";
    private final static String customerPath="src/group6/Data/Customer.Dat";

    //endregion

    /**
     * Base function to write and read content to and from the .dat file .
     */
    //region File I/O

    /**
     * Method to Write arraylist objects into Dat.File
     * @param list
     * @param path
     */
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

    /**
     * Method to read and return arraylist object from Dat file.
     * @param path
     * @return
     */
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
    //endregion

    /**
     * Database function for Table CRUD
     *
    //region Table

    /**
     *
     * @param TableList
     */
    //region Table

    /**
     * Writes the arraylist of Tables into Table.Dat
     * @param TableList
     */
    public static void writeTableList(ArrayList<Table> TableList){

        writeList(TableList,tablePath);
    }

    /**
     * Read all table objects from Table.Dat
     * @return
     */
    public static ArrayList<Table> readTableList() {
        ArrayList<Table> TableList;
        TableList=(ArrayList)readList(tablePath);
        return TableList;
    }

    /**
     * Get and return the Table object from Table.dat file
     * @param number
     * @return
     */
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

    /**
     * Write the Table object passed in into the Table.Dat file
     * @param table
     */
    public static void addTable(Table table) {
       ArrayList<Table> tablelist = (ArrayList<Table>)readTableList();
       // ArrayList<Table> tablelist = new ArrayList<Table>();
        tablelist.add(table);
        writeTableList(tablelist);

    }

    /**
     * Create a local arraylist assign it with values read the array list of Table objects from Table.Dat file.
     * Then Find the object that corresponds to the tableNumber passed in and remove it from the local array list
     * passin the local array list object to the WriteTableList to overwrite the previous records.
     * @param tableNumber
     */
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

    /**
     * With the Table object passed in,
     * it will first delete the object with the same id in the Table.DAT file
     * Then it will add in the object with addTable method
     * @param table
     */
    public static void updateTable(Table table) {
        deleteTable(table.getId());
        addTable(table);
    }
    //endregion

    /**
     * Database function for Staff CRUD
     */
    //region Staff
    /**
     * Writes the arraylist of Staff into Staff.Dat
     * @param StaffList
     */
    public static void writeStaffList(ArrayList<Staff> StaffList){

        writeList(StaffList,staffPath);
    }

    /**
     * Read all Staff objects from Staff.Dat
     * @return
     */
    public static ArrayList<Staff> readStaffList() {
        ArrayList<Staff> StaffList;
        StaffList=(ArrayList)readList(staffPath);
        return StaffList;
    }

    /**
     * Get and return the Staff object from Staff.dat file
     * @param employeeId
     * @return
     */
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

    /**
     * Write the Staff object passed in into the Staff.Dat file
     * @param Staff
     */
    public static void addStaff(Staff Staff) {
        ArrayList<Staff> Stafflist = (ArrayList<Staff>)readStaffList();
         //ArrayList<Staff> Stafflist = new ArrayList<Staff>();
        Stafflist.add(Staff);
        writeStaffList(Stafflist);

    }

    /**
     * Create a local arraylist assign it with values read the array list of Staff objects from Staff.Dat file.
     * Then Find the object that corresponds to the Staff employeeId passed in and remove it from the local array list
     * passin the local array list object to the WriteTableList to overwrite the previous records.
     * @param employeeId
     */
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

    /**
     * With the Staff object passed in,
     * it will first delete the object with the same id in the Staff.DAT file
     * Then it will add in the object with addStaff method
     * @param Staff
     */
    public static void updateStaff(Staff Staff) {
        deleteStaff(Staff.getEmployeeId());
        addStaff(Staff);
    }
    //endregion

    /**
     * Database function for MenuItem CRUD
     */
    //region MenuItem
    /**
     * Writes the arraylist of MenuItem into MenuItem.Dat
     * @param MenuItemList
     */
    public static void writeMenuItemList(ArrayList<MenuItem> MenuItemList){

        writeList(MenuItemList,menuItemPath);
    }

    /**
     * Read all MenuItem objects from MenuItem.Dat
     * @return
     */
    public static ArrayList<MenuItem> readMenuItemList() {
        ArrayList<MenuItem> MenuItemList;
        MenuItemList=(ArrayList)readList(menuItemPath);
        return MenuItemList;
    }

    /**
     * Get and return the MenuItem object from MenuItem.dat file
     * @param itemName
     * @return
     */
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

    /**
     * Get and return the MenuItem object  index from MenuItem.dat file
     * @param itemName
     * @return
     */
    public static int getMenuItemIndex(String itemName) {
        ArrayList<MenuItem> MenuItemList = (ArrayList<MenuItem>)readMenuItemList();
        for (int i = 0 ; i < MenuItemList.size() ; i++) {
            MenuItem MenuItem = (MenuItem)MenuItemList.get(i);
            if (MenuItem.getItemName().equalsIgnoreCase(itemName))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Write the MenuItem object passed in into the MenuItem.Dat file
     * @param MenuItem
     */
    public static void addMenuItem(MenuItem MenuItem) {
       ArrayList<MenuItem> MenuItemlist = (ArrayList<MenuItem>)readMenuItemList();
  //      ArrayList<MenuItem> MenuItemlist = new ArrayList<MenuItem>();
        MenuItemlist.add(MenuItem);
        writeMenuItemList(MenuItemlist);

    }

    /**
     * Create a local arraylist assign it with values read the array list of MenuItem objects from MenuItem.Dat file.
     * Then Find the object that corresponds to the MenuItem name passed in and remove it from the local array list
     * passin the local array list object to the WriteMenuItemList to overwrite the previous records.
     * @param itemName
     */
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

    /**
     * With the MenuItem object passed in,
     * it will first delete the object with the same id in the MenuItem.DAT file
     * Then it will add in the object with addMenuItem method
     * @param MenuItem
     */
    public static void updateMenuItem(MenuItem MenuItem) {
        deleteMenuItem(MenuItem.getItemName());
        addMenuItem(MenuItem);
    }
    //endregion

    /**
     * Database function for Customer  CRUD */
    //region Customer
    /**
     * Writes the arraylist of Customer into Customer.Dat
     * @param CustomerList
     */
    public static void writeCustomerList(ArrayList<Customer> CustomerList){

        writeList(CustomerList,customerPath);
    }

    /**
     * Read all Staff objects from Customer.Dat
     * @return
     */
    public static ArrayList<Customer> readCustomerList() {
        ArrayList<Customer> CustomerList;
        CustomerList=(ArrayList)readList(customerPath);
        return CustomerList;
    }

    /**
     * Get and return the Customer object from Customer.dat file
     * @param name
     * @return
     */
    public static Customer getCustomerByName(String name) {
        ArrayList<Customer> CustomerList = (ArrayList<Customer>)readCustomerList();
        for (int i = 0 ; i < CustomerList.size() ; i++) {
            Customer Customer = (Customer)CustomerList.get(i);
            if (Customer.getName().equalsIgnoreCase(name))
            {
                return Customer;
            }
        }
        return null;
    }

    /**
     * Get and return the Contact object from Contact.dat file
     * @param contact
     * @return
     */
    public static Customer getCustomerByContact(int contact) {
        ArrayList<Customer> CustomerList = (ArrayList<Customer>)readCustomerList();
        for (int i = 0 ; i < CustomerList.size() ; i++) {
            Customer Customer = (Customer)CustomerList.get(i);
            if (Customer.getContact()==(contact))
            {
                return Customer;
            }
        }
        return null;
    }

    /**
     * Write the Customer object passed in into the Customer.Dat file
     * @param Customer
     */
    public static void addCustomer(Customer Customer) {
        ArrayList<Customer> Customerlist = (ArrayList<Customer>)readCustomerList();
        //ArrayList<Customer> Customerlist = new ArrayList<Customer>();
        Customerlist.add(Customer);
        writeCustomerList(Customerlist);

    }

    /**
     * Create a local arraylist assign it with values read the array list of Customer objects from Customer.Dat file.
     * Then Find the object that corresponds to the Customer name passed in and remove it from the local array list
     * pass in the local array list object to the WriteCustomerList to overwrite the previous records.
     * @param name
     */
    public static void deleteCustomer(String name) {
        ArrayList<Customer> CustomerList = (ArrayList<Customer>)readCustomerList();
        for (int i = 0 ; i < CustomerList.size() ; i++) {
            Customer Customer = (Customer)CustomerList.get(i);
            if (Customer.getName().equalsIgnoreCase(name))
            {
                CustomerList.remove(i);
                break;
            }
        }
        writeCustomerList(CustomerList);
    }

    /**
     * With the Customer object passed in,
     * it will first delete the object with the same id in the Customer.DAT file
     * Then it will add in the object with addCustomer method
     * @param Customer
     */
    public static void updateCustomer(Customer Customer) {
        deleteCustomer(Customer.getName());
        addCustomer(Customer);
    }
    //endregion

    /**
     * Database function for Promotion CRUD */
    //region Promotion
    /**
     * Writes the arraylist of Promotion into Promotion.Dat
     * @param PromotionList
     */
    public static void writePromotionList(ArrayList<Promotion> PromotionList){

        writeList(PromotionList,promotionPath);
    }

    /**
     * Read all Promotion objects from Promotion.Dat
     * @return
     */
    public static ArrayList<Promotion> readPromotionList() {
        ArrayList<Promotion> PromotionList;
        PromotionList=(ArrayList)readList(promotionPath);
        return PromotionList;
    }

    /**
     * Get and return the Promotion object from Promotion.dat file
     * @param Name
     * @return
     */
    public static Promotion getPromotionByName(String Name) {
        ArrayList<Promotion> PromotionList = (ArrayList<Promotion>)readPromotionList();
        for (int i = 0 ; i < PromotionList.size() ; i++) {
            Promotion Promotion = (Promotion)PromotionList.get(i);
            if (Promotion.getName().equalsIgnoreCase(Name))
            {
                return Promotion;
            }
        }
        return null;
    }

    /**
     * Get and return the Promotion object index from Promotion.dat file
     * @param Name
     * @return
     */
    public static int getPromotionIndex(String Name) {
        ArrayList<Promotion> PromotionList = (ArrayList<Promotion>)readPromotionList();
        for (int i = 0 ; i < PromotionList.size() ; i++) {
            Promotion Promotion = (Promotion)PromotionList.get(i);
            if (Promotion.getName().equalsIgnoreCase(Name))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Write the Promotion object passed in into the Promotion.Dat file
     * @param Promotion
     */
    public static void addPromotion(Promotion Promotion) {
        ArrayList<Promotion> Promotionlist = (ArrayList<Promotion>)readPromotionList();
        //ArrayList<Promotion> Promotionlist = new ArrayList<Promotion>();
        Promotionlist.add(Promotion);
        writePromotionList(Promotionlist);

    }

    /**
     * Create a local arraylist assign it with values read the array list of Promotion objects from Promotion.Dat file.
     * Then Find the object that corresponds to the Promotion employeeId passed in and remove it from the local array list
     * pass in the local array list object to the WritePromotionList to overwrite the previous records.
     * @param Name
     */
    public static void deletePromotion(String Name) {
        ArrayList<Promotion> PromotionList = (ArrayList<Promotion>)readPromotionList();
        for (int i = 0 ; i < PromotionList.size() ; i++) {
            Promotion Promotion = (Promotion)PromotionList.get(i);
            if (Promotion.getName().equalsIgnoreCase(Name))
            {
                PromotionList.remove(i);
                break;
            }
        }
        writePromotionList(PromotionList);
    }

    /**
     * With the Promotion object passed in,
     * it will first delete the object with the same id in the Promotion.DAT file
     * Then it will add in the object with addPromotion method
     * @param Promotion
     */
    public static void updatePromotion(Promotion Promotion) {
        deletePromotion(Promotion.getName());
        addPromotion(Promotion);
    }
    //endregion

    /**
     * Database function for Reservation CRUD */
    //region Reservation
    /**
     * Writes the arraylist of Reservation into Reservation.Dat
     * @param ReservationList
     */
    public static void writeReservationList(ArrayList<Reservation> ReservationList){

        writeList(ReservationList,reservationPath);
    }

    /**
     * Read all Reservation objects from Reservation.Dat
     * @return
     */
    public static ArrayList<Reservation> readReservationList() {
        ArrayList<Reservation> ReservationList;
        ReservationList=(ArrayList)readList(reservationPath);
        return ReservationList;
    }

    /**
     * Get and return the Reservation object from Reservation.dat file
     * @param Id
     * @return
     */
    public static Reservation getReservationById(int Id) {
        ArrayList<Reservation> ReservationList = (ArrayList<Reservation>)readReservationList();
        for (int i = 0 ; i < ReservationList.size() ; i++) {
            Reservation Reservation = (Reservation)ReservationList.get(i);
            if (Reservation.getId()==(Id))
            {
                return Reservation;
            }
        }
        return null;
    }

    /**
     * Get and return the Reservation object from Reservation.dat file with customer name
     * @param customerName
     * @return
     */
    public static Reservation getReservationByCustomerName(String customerName) {
        ArrayList<Reservation> ReservationList = (ArrayList<Reservation>)readReservationList();
        for (int i = 0 ; i < ReservationList.size() ; i++) {
            Reservation Reservation = (Reservation)ReservationList.get(i);
            if (Reservation.getCustomerName().equalsIgnoreCase(customerName))
            {
                return Reservation;
            }
        }
        return null;
    }

    /**
     * Write the Staff object passed in into the Reservation.Dat file
     * @param Reservation
     */
    public static void addReservation(Reservation Reservation) {
        ArrayList<Reservation> Reservationlist = (ArrayList<Reservation>)readReservationList();
        //ArrayList<Reservation> Reservationlist = new ArrayList<Reservation>();
        Reservationlist.add(Reservation);
        writeReservationList(Reservationlist);

    }

    /**
     * Create a local arraylist assign it with values read the array list of Reservation objects from Reservation.Dat file.
     * Then Find the object that corresponds to the Reservation Id passed in and remove it from the local array list
     * pass in the local array list object to the WriteReservationList to overwrite the previous records.
     * @param Id
     */
    public static void deleteReservation(int Id) {
        ArrayList<Reservation> ReservationList = (ArrayList<Reservation>)readReservationList();
        for (int i = 0 ; i < ReservationList.size() ; i++) {
            Reservation Reservation = (Reservation)ReservationList.get(i);
            if (Reservation.getId()==(Id))
            {
                ReservationList.remove(i);
                break;
            }
        }
        writeReservationList(ReservationList);
    }

    /**
     * With the Reservation object passed in,
     * it will first delete the object with the same id in the Reservation.DAT file
     * Then it will add in the object with addReservation method
     * @param Reservation
     */
    public static void updateReservation(Reservation Reservation) {
        deleteReservation(Reservation.getId());
        addReservation(Reservation);
    }
    //endregion

    /**
     * Database function for Order CRUD */
    //region Order
    /**
     * Writes the arraylist of Order into Order.Dat
     * @param OrderList
     */
    public static void writeOrderList(ArrayList<Order> OrderList){

        writeList(OrderList,orderPath);
    }

    /**
     * Read all Order objects from Order.Dat
     * @return
     */
    public static ArrayList<Order> readOrderList() {
        ArrayList<Order> OrderList;
        OrderList=(ArrayList)readList(orderPath);
        return OrderList;
    }

    /**
     * Get and return the Order object from Order.dat file
     * @param Id
     */
    public static Order getOrderById(int Id) {
        ArrayList<Order> OrderList = (ArrayList<Order>)readOrderList();
        for (int i = 0 ; i < OrderList.size() ; i++) {
            Order Order = (Order)OrderList.get(i);
            if (Order.getOrderId()==(Id))
            {
                return Order;
            }
        }
        return null;
    }

    /**
     * Write the Order object passed in into the Order.Dat file
     * @param Order
     */
    public static void addOrder(Order Order) {
         ArrayList<Order> Orderlist = (ArrayList<Order>)readOrderList();
      //  ArrayList<Order> Orderlist = new ArrayList<Order>();
        Orderlist.add(Order);
        writeOrderList(Orderlist);

    }

    /**
     * Create a local arraylist assign it with values read the array list of Order objects from Order.Dat file.
     * Then Find the object that corresponds to the Order employeeId passed in and remove it from the local array list
     * passin the local array list object to the WriteOrderList to overwrite the previous records.
     * @param Id
     */
    public static void deleteOrder(int Id) {
        ArrayList<Order> OrderList = (ArrayList<Order>)readOrderList();
        for (int i = 0 ; i < OrderList.size() ; i++) {
            Order Order = (Order)OrderList.get(i);
            if (Order.getOrderId()==(Id))
            {
                OrderList.remove(i);
                break;
            }
        }
        writeOrderList(OrderList);
    }

    /**
     * With the Order object passed in,
     * it will first delete the object with the same id in the Order.DAT file
     * Then it will add in the object with addOrder method
     * @param Order
     */
    public static void updateOrder(Order Order) {
        deleteOrder(Order.getOrderId());
        addOrder(Order);
    }
    //endregion



}
