package Entity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.io.Serializable;
public class Revenue implements Serializable{
    int Id;
    LocalDate Date;
    String ItemName;
    String ItemType;
    double price;

    public Revenue() {
    }

    public Revenue(int id, LocalDate date, String itemName, String itemType, double price) {
        Id = id;
        Date = date;
        ItemName = itemName;
        ItemType = itemType;
        this.price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
