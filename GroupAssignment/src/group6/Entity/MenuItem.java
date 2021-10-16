package Entity;
import java.util.Objects;
import java.io.Serializable;

public class MenuItem implements Serializable{
    private String itemName;
    private String description;
    private double price;
    private String type;

    public MenuItem() {
    }

    public MenuItem(String itemName, String description, double price, String type) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return     Double.compare(menuItem.price, price) == 0
                && Objects.equals(itemName, menuItem.itemName)
                && Objects.equals(description, menuItem.description)
                && Objects.equals(type, menuItem.type);
    }

}
