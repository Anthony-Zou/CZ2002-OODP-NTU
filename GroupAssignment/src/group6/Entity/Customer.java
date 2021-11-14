package Entity;

import java.io.Serializable;

/**
Entity class for the Customer
 @author Zou Zeren
 @version 1.0
 @since 24-22-2021
 */
public class Customer implements Serializable {

    //region declare variables for Customers
    /**
     * variables for Customer
     */
    private String name;
    private int contact;
    private boolean memberShip;
    //endregion

    //region Customer constructor

    /**
     * Create empty object of Customer
     */
    public Customer() {
    }

    /**these three parmameter are passed in to create the customer object
     * @param name
     * @param contact
     * @param memberShip
     */
    public Customer(String name, int contact, boolean memberShip) {
        this.name = name;
        this.contact = contact;
        this.memberShip = memberShip;
    }
    //endregion

    //region setter for Customer

    /**contact is passed in to set the costumer contact
     * @param contact
     */
    public void setContact(int contact) {
        this.contact = contact;
    }

    /** memberShip boolean status is passed in to  set/replace the customer membership
     * @param memberShip
     */
    public void setMemberShip(boolean memberShip) {
        this.memberShip = memberShip;
    }

    /**name is passed in to set/replace the customer name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    //endregion

    //region getter for Customer

    /**
     * return the name of the customer
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * return the contact number of the customer
     * @return
     */
    public int getContact() {
        return contact;
    }

    /**
     * return the boolean value of the Customer Membership status
     * @return
     */
    public boolean isMemberShip() {
        return memberShip;
    }
    //endregion
}
