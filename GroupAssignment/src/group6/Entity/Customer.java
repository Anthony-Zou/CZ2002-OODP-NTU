package Entity;

import java.io.Serializable;

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

    /**
     * @param name
     * @param contact
     * @param memberShip these three parmameter are passed in to create the customer object
     */
    public Customer(String name, int contact, boolean memberShip) {
        this.name = name;
        this.contact = contact;
        this.memberShip = memberShip;
    }
    //endregion

    //region setter for Customer

    /**
     * @param contact is passed in to set the costumer contact
     */
    public void setContact(int contact) {
        this.contact = contact;
    }

    /**
     * @param memberShip boolean status is passed in to  set/replace the customer membership
     */
    public void setMemberShip(boolean memberShip) {
        this.memberShip = memberShip;
    }

    /**
     * @param name is passed in to set/replace the customer name
     */
    public void setName(String name) {
        this.name = name;
    }
    //endregion

    //region getter for Customer

    /**
     * @return the name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * @return the contact number of the customer
     */
    public int getContact() {
        return contact;
    }

    /**
     * @return the boolean value of the Customer Membership status
     */
    public boolean isMemberShip() {
        return memberShip;
    }
    //endregion
}
