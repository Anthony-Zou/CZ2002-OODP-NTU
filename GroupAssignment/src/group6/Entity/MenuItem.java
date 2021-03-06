package Entity;

import java.io.Serializable;
/**
 Entity class for the Menu Item
 @author Zou Zeren
 @version 1.0
 @since 24-22-2021
 */
public class MenuItem implements Serializable {
    //region Declare variables for MenuItems
    /**
     * variables for MenuItem
     */
    private String itemName;
    private String description;
    private double price;
    private String type;
    //endregion

    //region MenuItem Constructor

    /**
     * Create empty object of Customer
     */
    public MenuItem() {
    }

    /** These four parameter are passed in to create the customer object
     * @param itemName
     * @param description
     * @param price
     * @param type
     */
    public MenuItem(String itemName, String description, double price, String type) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    /**
     * itemName is passed in to set the MenuItem name
     * @param itemName
     */
    //endregion

    //region Setter for MenuItem

    /**
     * set the itemName
     * @param itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**set the Description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * set the price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param type is passed in to set the type
     */
    public void setType(String type) {
        this.type = type;
    }
    //endregion

    //region Getter for Menu Item

    /**
     * @return description of menuitem
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return ItemName of menuitem
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @return price of the menu item
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return type of the menuItem
     */
    public String getType() {
        return type;
    }
    //endregion
}
