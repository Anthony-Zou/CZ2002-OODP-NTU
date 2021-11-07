package Entity;
import java.util.Objects;
import java.io.Serializable;
public class Table implements Serializable{
    //region Variables for Table
    /**
     * Variables for Table
     */
    private int Id;
    private int capacity;
    private boolean reserved;
    //endregion

    //region Constructor for Table
    /**
     * Construct empty Table object
     */
    public Table() {
    }

    /**
     * Construct Table with the following 3 parameters
     * @param id
     * @param capacity
     * @param reserved
     */
    public Table(int id, int capacity, boolean reserved) {
        Id = id;
        this.capacity = capacity;
        this.reserved = reserved;
    }
    //endregion

    //region Setter

    /**
     * Set/Update Table Object Id
     * @param id
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Set/Update Table Object Capacity
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Set/Update Table Object Reserved status
     * @param reserved
     */
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
    //endregion

    //region Getter

    /**
     * Return Table Object id
     * @return
     */
    public int getId() {
        return Id;
    }

    /**
     * Return Table Object capacity
     * @return
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Return Table Object reserved status
     * @return
     */
    public boolean isReserved() {
        return reserved;
    }
    //endregion
}
