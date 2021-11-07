package Entity;
import Controller.OrderController;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.io.Serializable;

public class Order implements Serializable{
    //region variables for Orders
    /**
     * variables for Orders
     */
    private int orderId;
    private int staffId;
    private boolean membership;
    private int userContact;
    private ArrayList<MenuItem> alacarte = new ArrayList<MenuItem>();
    private ArrayList<Promotion> promotion = new ArrayList<Promotion>();
    private double totalPrice;
    private int tableNum;
    private boolean paid ;
    private LocalDate Date;
    private LocalTime Time;
    //endregion

    //region Orders Constructors

    /**
     * create empty object of orders
     */
    public Order() {
    }

    /**
     *
     * @param orderId
     * @param staffId
     * @param membership
     * @param userContact
     * @param alacarte
     * @param promotion
     * @param totalPrice
     * @param tableNum
     * @param paid
     * @param date
     * @param time
     * 11 objects are passed in to create the object of orders
     */
    public Order(int orderId, int staffId, boolean membership, int userContact, ArrayList<MenuItem> alacarte, ArrayList<Promotion> promotion, double totalPrice, int tableNum, boolean paid, LocalDate date, LocalTime time) {
        this.orderId = orderId;
        this.staffId = staffId;
        this.membership = membership;
        this.userContact = userContact;
        this.alacarte = alacarte;
        this.promotion = promotion;
        this.totalPrice = totalPrice;
        this.tableNum = tableNum;
        this.paid = paid;
        Date = date;
        Time = time;
    }
    //endregion

    //region Getter for Order
    /**
     * @return An ArrayList of MenuItem Object and renamed as alacarte
     */
    public ArrayList<MenuItem> getAlacarte() {
        return alacarte;
    }

    /**
     * @return An ArrayList of Promotion Object and renamed as promotio
     * *
     */
    public ArrayList<Promotion> getPromotion() {
        return promotion;
    }

    /**
     * @return order Id of Order object
     * *
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @return StaffId of Order object
     */
    public int getStaffId() {
        return staffId;
    }

    /**
     * @return boolean membership of Order object
     */
    public boolean isMembership() {
        return membership;
    }

    /**
     * @return customer contact of Order object
     */
    public int getUserContact() {
        return userContact;
    }

    /**
     * @return totalorder price  of Order object
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @return tableNum  of Order object
     */
    public int getTableNum() {
        return tableNum;
    }

    /**
     * @return date of Order object
     */
    public LocalDate getDate() {
        return Date;
    }

    /**
     * @return time of order object
     * *
     */
    public LocalTime getTime() {
        return Time;
    }

    /**
     * @return payment status of Order object
     */
    public boolean isPaid() {
        return paid;
    }
    //endregion

    //region Setter for orders

    /** @param date is passed in to set/replace the object date
     */
    public void setDate(LocalDate date) {
        Date = date;
    }

    /**
     * @param time is passed in to set/replace the object time
     */
    public void setTime(LocalTime time) {
        Time = time;
    }

    /**
     * @param totalPrice is passed in to set/replace the object total price
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @param tableNum  is passed in to set/replace the object table number
     */
    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    /**
     * @param alacarte arraylist of object  is passed in to set/replace the object arraylist of menuitem
     */
    public void setAlacarte(ArrayList<MenuItem> alacarte) {
        this.alacarte = alacarte;
    }

    /**
     * @param promotion arraylist of object  is passed in to set/replace the object arraylist of promotion
     */
    public void setPromotion(ArrayList<Promotion> promotion) {
        this.promotion = promotion;
    }

    /**
     * @param orderId is passed in to set/replace the object order id
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @param staffId is passed in to set/replace the object staffid
     */
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    /**
     * @param membership is passed in to set/replace the object customer membership
     */
    public void setMembership(boolean membership) {
        this.membership = membership;
    }

    /**
     * @param userContact is passed in to set/replace the object customer contact
     */
    public void setUserContact(int userContact) {
        this.userContact = userContact;
    }

    /**
     * @param paid is passed in to set/replace the object payment status
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    //endregion



}
