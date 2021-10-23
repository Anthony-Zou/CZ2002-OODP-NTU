package Entity;
import java.util.ArrayList;
import java.util.Objects;
import java.io.Serializable;
public class Promotion implements Serializable{
    private int Id;
    private String Name;
    private ArrayList<MenuItem> Items = new ArrayList<MenuItem>();
    private String Decription;
    private double price;

    public Promotion() {
    }

    public Promotion(int id, String name, ArrayList<MenuItem> items, String decription, double price) {
        Id = id;
        Name = name;
        Items = items;
        Decription = decription;
        this.price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<MenuItem> getItems() {
        return Items;
    }

    public void setItems(ArrayList<MenuItem> items) {
        Items = items;
    }

    public String getDecription() {
        return Decription;
    }

    public void setDecription(String decription) {
        Decription = decription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
