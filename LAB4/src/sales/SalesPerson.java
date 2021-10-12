package sales;
public class SalesPerson{
String firstName;
String lastName;
int totalSales;

    public SalesPerson(String firstName, String lastName, int totalSales) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalSales = totalSales;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "SalesPerson{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", totalSales=" + totalSales +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        SalesPerson that = (SalesPerson) object;
        return totalSales == that.totalSales && java.util.Objects.equals(firstName, that.firstName) && java.util.Objects.equals(lastName, that.lastName);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, totalSales);
    }
}