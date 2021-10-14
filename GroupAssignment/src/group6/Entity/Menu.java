package Entity;

import java.util.Objects;

public class Menu {
    String itemName;
    double price;
    int quantity;


    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return quantity + "  " + itemName + "  " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Double.compare(menu.price, price) == 0 && quantity == menu.quantity && Objects.equals(itemName, menu.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, price, quantity);
    }


    public Menu(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public void upDateMenu(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public void RemoveMenu(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }


}
