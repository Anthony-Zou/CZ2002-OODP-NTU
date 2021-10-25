package Entity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.io.Serializable;
public class Reservation implements Serializable{
    private int Id;
    private int tableId;
    private String customerName;
    private LocalDate Date;
    private LocalTime Time;
    private int pax;

    public Reservation() {
    }

    public Reservation(int id, int tableId, String customerName, LocalDate date, LocalTime time, int pax) {
        Id = id;
        this.tableId = tableId;
        this.customerName = customerName;
        Date = date;
        Time = time;
        this.pax = pax;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public LocalTime getTime() {
        return Time;
    }

    public void setTime(LocalTime time) {
        Time = time;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }
}
