package Entity;
import java.util.Objects;
import java.io.Serializable;

public class MenuItem implements Serializable{
    String itemName;
    double price;
    int quantity;

    public MenuItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menu = (MenuItem) o;
        return Double.compare(menu.price, price) == 0 && quantity == menu.quantity && Objects.equals(itemName, menu.itemName);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
