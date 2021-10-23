package Entity;
import Controller.OrderController;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.io.Serializable;

public class Order implements Serializable{
    private int orderId;
    private int staffId;
    private boolean membership;
    private int userContact;
    private ArrayList<MenuItem> Items = new ArrayList<MenuItem>();
    private double totalPrice;
    private int tableNum;


    public Order() {
    }

    public Order(int orderId, int staffId, boolean membership, int userContact, ArrayList<MenuItem> items, double totalPrice, int tableNum) {
        this.orderId = orderId;
        this.staffId = staffId;
        this.membership = membership;
        this.userContact = userContact;
        Items = items;
        this.totalPrice = totalPrice;
        this.tableNum = tableNum;

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public boolean isMembership() {
        return membership;
    }

    public void setMembership(boolean membership) {
        this.membership = membership;
    }

    public int getUserContact() {
        return userContact;
    }

    public void setUserContact(int userContact) {
        this.userContact = userContact;
    }

    public ArrayList<MenuItem> getItems() {
        return Items;
    }

    public void setItems(ArrayList<MenuItem> items) {
        Items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

}
