package Controller;

import Entity.*;
import java.io.*;
import java.util.*;

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
    //endregion

    /**
     * Database function for Table CRUD
     */
    //region Table
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
    //endregion

    /**
     * Database function for Staff CRUD
     */
    //region Staff
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
    //endregion

    /**
     * Database function for MenuItem CRUD
     */
    //region MenuItem
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
    //endregion

    /**
     * Database function for Customer  CRUD */
    //region Customer
    public static void writeCustomerList(ArrayList<Customer> CustomerList){

        writeList(CustomerList,customerPath);
    }

    public static ArrayList<Customer> readCustomerList() {
        ArrayList<Customer> CustomerList;
        CustomerList=(ArrayList)readList(customerPath);
        return CustomerList;
    }

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

    public static void addCustomer(Customer Customer) {
       // ArrayList<Customer> Customerlist = (ArrayList<Customer>)readCustomerList();
         ArrayList<Customer> Customerlist = new ArrayList<Customer>();
        Customerlist.add(Customer);
        writeCustomerList(Customerlist);

    }

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

    public static void updateCustomer(Customer Customer) {
        deleteCustomer(Customer.getName());
        addCustomer(Customer);
    }
    //endregion

    /**
     * Database function for Promotion CRUD */
    //region Promotion
    public static void writePromotionList(ArrayList<Promotion> PromotionList){

        writeList(PromotionList,promotionPath);
    }

    public static ArrayList<Promotion> readPromotionList() {
        ArrayList<Promotion> PromotionList;
        PromotionList=(ArrayList)readList(promotionPath);
        return PromotionList;
    }

    public static Promotion getPromotionById(int Id) {
        ArrayList<Promotion> PromotionList = (ArrayList<Promotion>)readPromotionList();
        for (int i = 0 ; i < PromotionList.size() ; i++) {
            Promotion Promotion = (Promotion)PromotionList.get(i);
            if (Promotion.getId()==(Id))
            {
                return Promotion;
            }
        }
        return null;
    }

    public static void addPromotion(Promotion Promotion) {
        // ArrayList<Promotion> Promotionlist = (ArrayList<Promotion>)readPromotionList();
        ArrayList<Promotion> Promotionlist = new ArrayList<Promotion>();
        Promotionlist.add(Promotion);
        writePromotionList(Promotionlist);

    }

    public static void deletePromotion(int Id) {
        ArrayList<Promotion> PromotionList = (ArrayList<Promotion>)readPromotionList();
        for (int i = 0 ; i < PromotionList.size() ; i++) {
            Promotion Promotion = (Promotion)PromotionList.get(i);
            if (Promotion.getId()==(Id))
            {
                PromotionList.remove(i);
                break;
            }
        }
        writePromotionList(PromotionList);
    }

    public static void updatePromotion(Promotion Promotion) {
        deletePromotion(Promotion.getId());
        addPromotion(Promotion);
    }
    //endregion

    /**
     * Database function for Order CRUD */
    //region Order
    public static void writeOrderList(ArrayList<Order> OrderList){

        writeList(OrderList,orderPath);
    }

    public static ArrayList<Order> readOrderList() {
        ArrayList<Order> OrderList;
        OrderList=(ArrayList)readList(orderPath);
        return OrderList;
    }

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

    public static void addOrder(Order Order) {
        // ArrayList<Order> Orderlist = (ArrayList<Order>)readOrderList();
        ArrayList<Order> Orderlist = new ArrayList<Order>();
        Orderlist.add(Order);
        writeOrderList(Orderlist);

    }

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

    public static void updateOrder(Order Order) {
        deleteOrder(Order.getOrderId());
        addOrder(Order);
    }
    //endregion

    /**
     * Database function for Reservation CRUD */
    //region Reservation
    public static void writeReservationList(ArrayList<Reservation> ReservationList){

        writeList(ReservationList,reservationPath);
    }

    public static ArrayList<Reservation> readReservationList() {
        ArrayList<Reservation> ReservationList;
        ReservationList=(ArrayList)readList(reservationPath);
        return ReservationList;
    }

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

    public static void addReservation(Reservation Reservation) {
        // ArrayList<Reservation> Reservationlist = (ArrayList<Reservation>)readReservationList();
        ArrayList<Reservation> Reservationlist = new ArrayList<Reservation>();
        Reservationlist.add(Reservation);
        writeReservationList(Reservationlist);

    }

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

    public static void updateReservation(Reservation Reservation) {
        deleteReservation(Reservation.getId());
        addReservation(Reservation);
    }
    //endregion

    /**
     * Database function for Revenue CRUD */
    //region Order
    public static void writeRevenueList(ArrayList<Revenue> RevenueList){

        writeList(RevenueList,revenuePath);
    }

    public static ArrayList<Revenue> readRevenueList() {
        ArrayList<Revenue> RevenueList;
        RevenueList=(ArrayList)readList(revenuePath);
        return RevenueList;
    }

    public static Revenue getRevenueById(int Id) {
        ArrayList<Revenue> RevenueList = (ArrayList<Revenue>)readRevenueList();
        for (int i = 0 ; i < RevenueList.size() ; i++) {
            Revenue Revenue = (Revenue)RevenueList.get(i);
            if (Revenue.getId()==(Id))
            {
                return Revenue;
            }
        }
        return null;
    }

    public static void addRevenue(Revenue Revenue) {
        // ArrayList<Revenue> Revenuelist = (ArrayList<Revenue>)readRevenueList();
        ArrayList<Revenue> Revenuelist = new ArrayList<Revenue>();
        Revenuelist.add(Revenue);
        writeRevenueList(Revenuelist);

    }

    public static void deleteRevenue(int Id) {
        ArrayList<Revenue> RevenueList = (ArrayList<Revenue>)readRevenueList();
        for (int i = 0 ; i < RevenueList.size() ; i++) {
            Revenue Revenue = (Revenue)RevenueList.get(i);
            if (Revenue.getId()==(Id))
            {
                RevenueList.remove(i);
                break;
            }
        }
        writeRevenueList(RevenueList);
    }

    public static void updateRevenue(Revenue Revenue) {
        deleteRevenue(Revenue.getId());
        addRevenue(Revenue);
    }
    //endregion
}
