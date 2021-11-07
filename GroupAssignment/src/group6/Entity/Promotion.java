package Entity;
import java.util.ArrayList;
import java.util.Objects;
import java.io.Serializable;
public class Promotion implements Serializable{
    //region variables for Promotion
    /** variables for Promotion
     * */
    private int Id;
    private String Name;
    private ArrayList<MenuItem> Items = new ArrayList<MenuItem>();
    private String Decription;
    private double price;
    //endregion

    //region Constructor for Promotion

    /**
     * Create Empty object for promotion
     */
    public Promotion() {
    }

    /**
     * Create promotion object with the following parameters
     * @param id
     * @param name
     * @param items
     * @param decription
     * @param price
     */
    public Promotion(int id, String name, ArrayList<MenuItem> items, String decription, double price) {
        Id = id;
        Name = name;
        Items = items;
        Decription = decription;
        this.price = price;
    }
    //endregion

    //region Setter for Promotion

    /**
     * Set/Update the object id with
     * @param id
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Set/Update the object name with
     * @param name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Set/Update the object with
     * @param items
     */
    public void setItems(ArrayList<MenuItem> items) {
        Items = items;
    }
    public void setDecription(String decription) {
        Decription = decription;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    //endregion

    //region for getter or Promotion
    public double getPrice() {
        return price;
    }
    public String getDecription() {
        return Decription;
    }
    public ArrayList<MenuItem> getItems() {
        return Items;
    }
    public int getId() {
        return Id;
    }
    public String getName() {
        return Name;
    }
    //endregion
}
