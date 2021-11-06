package Entity;
import java.util.Objects;
import java.io.Serializable;
public class Table implements Serializable{
    private int Id;
    private int capacity;
    private boolean reserved;

    public Table() {
    }

    public Table(int id, int capacity, boolean reserved) {
        Id = id;
        this.capacity = capacity;
        this.reserved = reserved;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
