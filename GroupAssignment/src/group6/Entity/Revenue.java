package Entity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.io.Serializable;
public class Revenue implements Serializable{
    //region Variables for Revenue
    /**
     * Variables for Revenue
     */
    int Id;
    LocalDate Date;
    String ItemName;
    String ItemType;
    double price;
    //endregion
    //region Constructor for Revenue

    /**
     * Construct empty Revenue object
     */
    public Revenue() {
    }

    /**
     * Construct Revenue with the following 5 paramenters
     * @param id
     * @param date
     * @param itemName
     * @param itemType
     * @param price
     */
    public Revenue(int id, LocalDate date, String itemName, String itemType, double price) {
        Id = id;
        Date = date;
        ItemName = itemName;
        ItemType = itemType;
        this.price = price;
    }
    //endregion

    //region Setter for Revenue

    /**
     * Set/Update object Id
     * @param id
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Set/Update object Date
     * @param date
     */
    public void setDate(LocalDate date) {
        Date = date;
    }

    /**
     * Set/Update object ItemName
     * @param itemName
     */
    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    /**
     * Set/Update object ItemType
     * @param itemType
     */
    public void setItemType(String itemType) {
        ItemType = itemType;
    }

    /**
     * Set/Update object price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
   //endregion

    //region for getter

    /**
     * return obejct id
     * @return
     */
    public int getId() {
        return Id;
    }

    /**
     * return obejct Date
     * @return
     */
    public LocalDate getDate() {
        return Date;
    }

    /**
     * return obejct Item name
     * @return
     */
    public String getItemName() {
        return ItemName;
    }

    /**
     * return obejct type
     * @return
     */
    public String getItemType() {
        return ItemType;
    }

    /**
     * return obejct Price
     * @return
     */
    public double getPrice() {
        return price;
    }
    //endregion
}
