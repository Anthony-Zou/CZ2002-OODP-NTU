package Entity;
import java.util.ArrayList;
import java.util.Objects;
import java.io.Serializable;
/**
 Entity class for the Promotion
 @author Zou Zeren
 @version 1.0
 @since 24-22-2021
 */
public class Promotion extends MenuItem implements Serializable{
    //region variables for Promotion
    /** variables for Promotion
     * */
    //private int Id;
    private String Name;
    private ArrayList<MenuItem> Items = new ArrayList<MenuItem>();
    private String Decription;
    private double price;
    //private String type;
    //endregion

    //region Constructor for Promotion

    /**
     * Create Empty object for promotion
     */
    public Promotion() {
    }

    /**
     * Create promotion object with the following parameters
     //* @param id
     * @param name
     * @param items
     * @param decription
     * @param price
     */
    public Promotion(String name, ArrayList<MenuItem> items, String decription, double price) {
        //Id = id;
        Name = name;
        Items = items;
        Decription = decription;
        this.price = price;
        //this.type = "promotion";
    }
    //endregion

    //region Setter for Promotion


    /**
     * Set/Update the Promotion object name with
     * @param name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Set/Update the Promotion object array list with
     * @param items
     */
    public void setItems(ArrayList<MenuItem> items) {
        Items = items;
    }

    /**
     * Set/Update the Promotion object description with
     * @param decription
     */
    public void setDecription(String decription) {
        Decription = decription;
    }

    /**
     * Set/Update the Promotion object price with
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    //endregion

    //region for getter or Promotion

    /**
     * Return object price
     * @return
     */
    public double getPrice() {
        return price;
    }

    //public String getType() {
      //  return this.type;
    //}

    /**
     * Return object description
     * @return
     */
    public String getDecription() {
        return Decription;
    }

    /**
     * Return object arraylist menuitems
     * @return
     */
    public ArrayList<MenuItem> getItems() {
        return Items;
    }

    /**
     * Return object Id
     * @return
     */
    //public int getId() {
     //   return Id;
    //}

    /**
     * Return object name
     * @return
     */
    public String getName() {
        return Name;
    }
    //endregion
}
