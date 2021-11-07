package Entity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.io.Serializable;
public class Reservation implements Serializable{
    //region varibles for Reservation
    /**
     *Variables for Reservation
     */
    private int Id;
    private int tableId;
    private String customerName;
    private LocalDate Date;
    private LocalTime Time;
    private int pax;
    //endregion

    //region Constructor for Reservation
    /**
     * Create Empty object for Reservation
     */
    public Reservation() {
    }

    /**
     * Create Reservation object with the following parameters
     * @param id
     * @param tableId
     * @param customerName
     * @param date
     * @param time
     * @param pax
     */
    public Reservation(int id, int tableId, String customerName, LocalDate date, LocalTime time, int pax) {
        Id = id;
        this.tableId = tableId;
        this.customerName = customerName;
        Date = date;
        Time = time;
        this.pax = pax;
    }
    //endregion

    //region Setter for Reservation

    /**
     * Set/Update Obejct Time
     * @param time
     */
    public void setTime(LocalTime time) {
        Time = time;
    }

    /**
     * Set/Update Obejct Date
     * @param date
     */
    public void setDate(LocalDate date) {
        Date = date;
    }

    /**
     * Set/Update Obejct Customer name
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Set/Update Obejct Table Id
     * @param tableId
     */
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    /**
     * Set/Update Obejct ID
     * @param id
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Set/Update Obejct Customer Pax
     * @param pax
     */
    public void setPax(int pax) {
        this.pax = pax;
    }
    //endregion

    //region Getter for Reservation

    /**
     * Return Customer pax
     * @return
     */
    public int getPax() {
        return pax;
    }

    /**
     * return object id
     * @return
     */
    public int getId() {
        return Id;
    }

    /**
     * return table id
     * @return
     */
    public int getTableId() {
        return tableId;
    }

    /**
     * return CustomerName
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * return Object Date
     * @return
     */
    public LocalDate getDate() {
        return Date;
    }

    /**
     * return Object Time
     * @return
     */
    public LocalTime getTime() {
        return Time;
    }
    //endregion
}
