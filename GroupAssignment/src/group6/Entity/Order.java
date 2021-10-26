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
    private ArrayList<MenuItem> alacarte = new ArrayList<MenuItem>();
    private ArrayList<Promotion> promotion = new ArrayList<Promotion>();
    private double totalPrice;
    private int tableNum;
    private boolean paid ;

    public Order() {
    }

    public Order(int orderId, int staffId, boolean membership, int userContact, ArrayList<MenuItem> alacarte, ArrayList<Promotion> promotion, double totalPrice, int tableNum, boolean paid) {
        this.orderId = orderId;
        this.staffId = staffId;
        this.membership = membership;
        this.userContact = userContact;
        this.alacarte = alacarte;
        this.promotion = promotion;
        this.totalPrice = totalPrice;
        this.tableNum = tableNum;
        this.paid = paid;
    }

    public ArrayList<MenuItem> getAlacarte() {
        return alacarte;
    }

    public void setAlacarte(ArrayList<MenuItem> alacarte) {
        this.alacarte = alacarte;
    }

    public ArrayList<Promotion> getPromotion() {
        return promotion;
    }

    public void setPromotion(ArrayList<Promotion> promotion) {
        this.promotion = promotion;
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

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
